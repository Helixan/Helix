package org.cultro.helix.geometry.d3;

import org.cultro.helix.lang.Validate;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * The Sphere class represents a sphere in three-dimensional space, defined by its center and radius.
 * It provides methods to calculate the sphere's volume, surface area, and to iterate over discrete
 * {@code Location3D} points within the sphere.
 *
 * <p>Example usage:
 * <pre>{@code
 * Location3D center = new Location3D(0, 0, 0);
 * double radius = 5.0;
 * Sphere sphere = new Sphere(center, radius);
 * System.out.println("Volume: " + sphere.getVolume()); // Outputs approximately 523.5987755982989
 * System.out.println("Surface Area: " + sphere.getSurfaceArea()); // Outputs approximately 314.1592653589793
 * }</pre>
 *
 * @see Location3D
 */
@SuppressWarnings("unused")
public class Sphere implements Iterable<Location3D> {

    private final Location3D center;
    private final double radius;

    /**
     * Constructs a new {@code Sphere} with the specified center and radius.
     *
     * <p>The radius must be positive. The center cannot be {@code null}.
     *
     * @param center The center of the sphere.
     * @param radius The radius of the sphere.
     * @throws IllegalArgumentException if {@code center} is {@code null} or {@code radius} is not positive.
     */
    public Sphere(Location3D center, double radius) {
        Validate.notNull(center, "Sphere center cannot be null.");
        Validate.isGreaterThan(radius, 0.0, "Sphere radius must be positive.");

        this.center = new Location3D(center.getX(), center.getY(), center.getZ());
        this.radius = radius;
    }

    /**
     * Retrieves the center of the sphere.
     *
     * <p>Returns a defensive copy to preserve immutability.
     *
     * @return A new {@code Location3D} object representing the center of the sphere.
     */
    public Location3D getCenter() {
        return new Location3D(center.getX(), center.getY(), center.getZ());
    }

    /**
     * Retrieves the radius of the sphere.
     *
     * @return The radius of the sphere.
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Calculates the volume of the sphere.
     *
     * <p>The volume is computed using the formula:
     * <pre>{@code
     * Volume = (4/3) * π * radius³
     * }</pre>
     *
     * @return The volume of the sphere.
     */
    public double getVolume() {
        return (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
    }

    /**
     * Calculates the surface area of the sphere.
     *
     * <p>The surface area is computed using the formula:
     * <pre>{@code
     * Surface Area = 4 * π * radius²
     * }</pre>
     *
     * @return The surface area of the sphere.
     */
    public double getSurfaceArea() {
        return 4.0 * Math.PI * Math.pow(radius, 2);
    }

    /**
     * Determines whether the sphere contains the specified point.
     *
     * <p>A point is considered inside the sphere if its distance from the center is less than or equal to the radius.
     *
     * @param point The {@code Location3D} point to check.
     * @return {@code true} if the point is within or on the sphere, {@code false} otherwise.
     * @throws IllegalArgumentException if {@code point} is {@code null}.
     */
    public boolean containsPoint(Location3D point) {
        Validate.notNull(point, "Point cannot be null.");

        double distanceSquared = Math.pow(point.getX() - center.getX(), 2)
                + Math.pow(point.getY() - center.getY(), 2)
                + Math.pow(point.getZ() - center.getZ(), 2);

        return distanceSquared <= Math.pow(radius, 2);
    }

    /**
     * Provides an iterator over {@code Location3D} objects within the sphere.
     *
     * <p>The iterator traverses all integer grid points within the sphere, starting from the minimum bounding box
     * and moving along the X, Y, and Z axes.
     *
     * @return An {@code Iterator} over {@code Location3D} objects.
     */
    @Override
    public Iterator<Location3D> iterator() {
        return new SphereIterator();
    }

    /**
     * Compares this Sphere to another object for equality.
     *
     * <p>Two spheres are considered equal if they have the same center and radius.
     *
     * @param o The object to compare with.
     * @return {@code true} if the other object is a Sphere with the same center and radius, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sphere sphere = (Sphere) o;
        return Double.compare(sphere.radius, radius) == 0 &&
                Objects.equals(center, sphere.center);
    }

    /**
     * Computes the hash code for the Sphere object.
     *
     * @return The hash code of the Sphere.
     */
    @Override
    public int hashCode() {
        return Objects.hash(center, radius);
    }

    /**
     * Provides a string representation of the Sphere object.
     *
     * @return A string representation of the Sphere.
     */
    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }

    /**
     * Inner class that provides an {@code Iterator} implementation to iterate over {@code Location3D} objects within the sphere.
     */
    private class SphereIterator implements Iterator<Location3D> {
        private int currentX;
        private int currentY;
        private int currentZ;
        private final int minX;
        private final int maxX;
        private final int minY;
        private final int maxY;
        private final int minZ;
        private final int maxZ;
        private Location3D nextLocation;
        private boolean hasNext;

        /**
         * Constructs a {@code SphereIterator}.
         *
         * <p>Initializes the iterator to the starting position at the minimum bounding box of the sphere.
         */
        public SphereIterator() {
            this.minX = (int) Math.floor(center.getX() - radius);
            this.maxX = (int) Math.ceil(center.getX() + radius);
            this.minY = (int) Math.floor(center.getY() - radius);
            this.maxY = (int) Math.ceil(center.getY() + radius);
            this.minZ = (int) Math.floor(center.getZ() - radius);
            this.maxZ = (int) Math.ceil(center.getZ() + radius);

            this.currentX = minX;
            this.currentY = minY;
            this.currentZ = minZ;

            advanceToNextValid();
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
         * Retrieves the next {@code Location3D} point in the sphere.
         *
         * @return The next {@code Location3D} object.
         * @throws NoSuchElementException If there are no more elements to iterate over.
         */
        @Override
        public Location3D next() {
            if (!hasNext) {
                throw new NoSuchElementException("No more points to iterate over.");
            }

            Location3D returnLocation = nextLocation;

            currentZ++;
            advanceToNextValid();

            return returnLocation;
        }

        /**
         * Advances the iterator to the next valid {@code Location3D} within the sphere.
         */
        private void advanceToNextValid() {
            while (currentX <= maxX) {
                while (currentY <= maxY) {
                    while (currentZ <= maxZ) {
                        Location3D point = new Location3D(currentX, currentY, currentZ);
                        if (containsPoint(point)) {
                            nextLocation = point;
                            hasNext = true;
                            return;
                        }
                        currentZ++;
                    }
                    currentZ = minZ;
                    currentY++;
                }
                currentY = minY;
                currentX++;
            }
            hasNext = false;
        }
    }
}
