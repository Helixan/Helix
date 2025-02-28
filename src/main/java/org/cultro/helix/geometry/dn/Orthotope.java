package org.cultro.helix.geometry.dn;

import org.cultro.helix.lang.Validate;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * The Orthotope class represents an n-dimensional hyperrectangle (orthotope) in multi-dimensional space,
 * defined by two opposite {@code NDLocation} points: the minimum and maximum corners.
 * It provides methods to calculate the orthotope's volume, surface area, and to iterate over discrete
 * {@code NDLocation} points within the orthotope.
 *
 * <p>An orthotope is a generalization of a rectangle (2D) and a rectangular prism (3D) to n dimensions.
 * It is defined by the minimum and maximum coordinates along each dimension.
 *
 * <p>Example usage:
 * <pre>{@code
 * NDLocation corner1 = new NDLocation(new double[]{0, 0, 0});
 * NDLocation corner2 = new NDLocation(new double[]{3, 4, 5});
 * Orthotope orthotope = new Orthotope(corner1, corner2);
 * System.out.println("Volume: " + orthotope.getVolume()); // Outputs 60.0
 * System.out.println("Surface Area: " + orthotope.getSurfaceArea()); // Outputs 94.0
 * }</pre>
 *
 * @see NDLocation
 */
@SuppressWarnings("unused")
public class Orthotope implements Iterable<NDLocation> {

    private final NDLocation min;
    private final NDLocation max;

    /**
     * Constructs a new {@code Orthotope} with the specified minimum and maximum corners.
     *
     * <p>The two corners provided should be opposite each other in the n-dimensional space.
     * This constructor calculates the minimum and maximum coordinates to define the orthotope's bounds.
     *
     * @param first  The first corner of the orthotope.
     * @param second The second corner diagonally opposite to the first.
     * @throws IllegalArgumentException if either {@code first} or {@code second} is {@code null},
     *                                  if they have different dimensions,
     *                                  or if both corners are identical (zero volume).
     */
    public Orthotope(NDLocation first, NDLocation second) {
        Validate.notNull(first, "An orthotope cannot be created from a null location.");
        Validate.notNull(second, "An orthotope cannot be created from a null location.");
        Validate.isEquivalent(first.getDimension(), second.getDimension(),
                "An orthotope cannot be created from two locations in different dimensions.");

        int dimension = first.getDimension();
        double[] minCoords = new double[dimension];
        double[] maxCoords = new double[dimension];

        for (int i = 0; i < dimension; i++) {
            double firstComponent = first.getComponent(i);
            double secondComponent = second.getComponent(i);
            if (firstComponent > secondComponent) {
                maxCoords[i] = firstComponent;
                minCoords[i] = secondComponent;
            } else {
                maxCoords[i] = secondComponent;
                minCoords[i] = firstComponent;
            }
        }

        this.min = new NDLocation(minCoords);
        this.max = new NDLocation(maxCoords);

        // Ensure that the orthotope has a non-zero volume
        boolean hasZeroLength = false;
        for (int i = 0; i < dimension; i++) {
            if (minCoords[i] == maxCoords[i]) {
                hasZeroLength = true;
                break;
            }
        }
        Validate.isFalse(hasZeroLength, "An orthotope cannot have zero length along any dimension.");
    }

    /**
     * Retrieves the minimum corner of the orthotope.
     *
     * <p>The minimum corner has the smallest coordinates along each dimension.
     *
     * @return A defensive copy of the minimum corner as an {@code NDLocation} object.
     */
    public NDLocation getMin() {
        return new NDLocation(min.getElements());
    }

    /**
     * Retrieves the maximum corner of the orthotope.
     *
     * <p>The maximum corner has the largest coordinates along each dimension.
     *
     * @return A defensive copy of the maximum corner as an {@code NDLocation} object.
     */
    public NDLocation getMax() {
        return new NDLocation(max.getElements());
    }

    /**
     * Calculates the volume of the orthotope.
     *
     * <p>The volume is the product of the lengths along each dimension.
     *
     * @return The volume of the orthotope.
     */
    public double getVolume() {
        double volume = 1.0;
        int dimension = min.getDimension();
        for (int i = 0; i < dimension; i++) {
            volume *= (max.getComponent(i) - min.getComponent(i));
        }
        return volume;
    }

    /**
     * Calculates the surface area of the orthotope.
     *
     * <p>In n-dimensions, the surface area is the sum of the areas of all (n-1)-dimensional faces.
     * Each pair of opposite faces contributes twice the product of the lengths along all dimensions
     * except one.
     *
     * @return The surface area of the orthotope.
     */
    public double getSurfaceArea() {
        double surfaceArea = 0.0;
        int dimension = min.getDimension();

        for (int i = 0; i < dimension; i++) {
            double faceArea = 1.0;
            for (int j = 0; j < dimension; j++) {
                if (j != i) {
                    faceArea *= (max.getComponent(j) - min.getComponent(j));
                }
            }
            surfaceArea += 2 * faceArea; // Two opposite faces per dimension
        }

        return surfaceArea;
    }

    /**
     * Retrieves the number of dimensions of the orthotope.
     *
     * <p>This corresponds to the number of coordinates in the {@code NDLocation} points defining the orthotope.
     *
     * @return The dimension of the orthotope.
     */
    public int getDimension() {
        return min.getDimension();
    }

    /**
     * Determines whether the orthotope contains the specified point.
     *
     * <p>A point is considered inside the orthotope if its coordinates are within the bounds
     * defined by {@code min} and {@code max}, inclusive.
     *
     * @param point The {@code NDLocation} point to check.
     * @return {@code true} if the point is within or on the orthotope, {@code false} otherwise.
     * @throws IllegalArgumentException if {@code point} is {@code null}.
     */
    public boolean containsPoint(NDLocation point) {
        Validate.notNull(point, "Point cannot be null.");
        Validate.isEquivalent(point.getDimension(), this.getMin().getDimension(),
                "Point dimension does not match orthotope dimension.");

        int dimension = min.getDimension();
        for (int i = 0; i < dimension; i++) {
            double coord = point.getComponent(i);
            if (coord < min.getComponent(i) || coord > max.getComponent(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Calculates the length of the space diagonal of the orthotope.
     *
     * @return The space diagonal length.
     */
    public double getSpaceDiagonalLength() {
        double sumOfSquares = 0.0;
        int dimension = min.getDimension();
        for (int i = 0; i < dimension; i++) {
            double diff = max.getComponent(i) - min.getComponent(i);
            sumOfSquares += diff * diff;
        }
        return Math.sqrt(sumOfSquares);
    }


    /**
     * Provides an iterator over {@code NDLocation} objects within the orthotope.
     *
     * <p>The iterator traverses all integer grid points within the orthotope's bounding box,
     * starting from the minimum corner and moving along each dimension.
     *
     * @return An {@code Iterator} over {@code NDLocation} objects.
     */
    @Override
    public Iterator<NDLocation> iterator() {
        return new OrthotopeIterator();
    }

    /**
     * Compares this Orthotope to another object for equality.
     *
     * <p>Two orthotopes are considered equal if they have the same minimum and maximum locations.
     *
     * @param o The object to compare with.
     * @return {@code true} if the other object is an Orthotope with the same min and max locations, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Orthotope orthotope = (Orthotope) o;
        return Objects.equals(min, orthotope.min) && Objects.equals(max, orthotope.max);
    }

    /**
     * Computes the hash code for the Orthotope object.
     *
     * @return The hash code of the Orthotope.
     */
    @Override
    public int hashCode() {
        return Objects.hash(min, max);
    }

    /**
     * Provides a string representation of the Orthotope object.
     *
     * @return A string representation of the Orthotope.
     */
    @Override
    public String toString() {
        return "Orthotope{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }

    /**
     * Inner class that provides an {@code Iterator} implementation to iterate over {@code NDLocation} objects within the orthotope.
     */
    private class OrthotopeIterator implements Iterator<NDLocation> {
        private final int dimension;
        private final int[] current;
        private final int[] start;
        private final int[] end;
        private boolean hasNext;

        /**
         * Constructs an {@code OrthotopeIterator}.
         *
         * <p>Initializes the iterator to the starting position at the minimum corner of the orthotope.
         */
        public OrthotopeIterator() {
            this.dimension = min.getDimension();
            this.current = new int[dimension];
            this.start = new int[dimension];
            this.end = new int[dimension];

            for (int i = 0; i < dimension; i++) {
                start[i] = (int) Math.floor(min.getComponent(i));
                end[i] = (int) Math.ceil(max.getComponent(i));
                current[i] = start[i];
            }

            hasNext = Arrays.stream(current).allMatch(c -> c <= end[getDimension() - 1]);
        }

        /**
         * Checks if there are more points to iterate over.
         *
         * @return {@code true} if there are more points, {@code false} otherwise.
         */
        @Override
        public boolean hasNext() {
            return hasNext;
        }

        /**
         * Retrieves the next {@code NDLocation} point in the orthotope.
         *
         * @return The next {@code NDLocation} object.
         * @throws NoSuchElementException If there are no more elements to iterate over.
         */
        @Override
        public NDLocation next() {
            if (!hasNext) {
                throw new NoSuchElementException("No more points to iterate over.");
            }

            double[] coords = new double[dimension];
            for (int i = 0; i < dimension; i++) {
                coords[i] = current[i];
            }
            NDLocation nextLocation = new NDLocation(coords);

            increment();

            return nextLocation;
        }

        /**
         * Increments the current coordinates to the next point in the orthotope.
         *
         * <p>The incrementation follows lexicographical order across dimensions.
         */
        private void increment() {
            for (int i = dimension - 1; i >= 0; i--) {
                if (current[i] < end[i]) {
                    current[i]++;
                    if (dimension - (i + 1) >= 0) {
                        System.arraycopy(start, i + 1, current, i + 1, dimension - (i + 1));
                    }
                    return;
                }
            }
            hasNext = false;
        }
    }
}
