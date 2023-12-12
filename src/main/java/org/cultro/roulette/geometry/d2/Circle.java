package org.cultro.roulette.geometry.d2;

import org.cultro.roulette.util.GeometryUtils;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * The Circle class represents a geometric circle in a 2D space.
 * It provides methods to get the circle's center, radius, area, and circumference.
 * The class also implements the Iterable interface, allowing iteration over
 * Location2D objects that represent points on the circle's perimeter.
 */
@SuppressWarnings("unused")
public class Circle implements Iterable<Location2D> {

    private final Location2D center;
    private final double radius;


    /**
     * Constructs a new Circle with the specified center and radius.
     *
     * @param center The center of the circle as a Location2D object.
     * @param radius The radius of the circle.
     */
    public Circle(Location2D center, double radius) {
        this.center = center;
        this.radius = radius;
    }


    /**
     * Retrieves the center of the circle.
     *
     * @return The center of the circle as a Location2D object.
     */
    public Location2D getCenter() {
        return center;
    }


    /**
     * Retrieves the radius of the circle.
     *
     * @return The radius of the circle.
     */
    public double getRadius() {
        return radius;
    }


    /**
     * Calculates the area of the circle.
     *
     * @return The area of the circle.
     */
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }


    /**
     * Calculates the circumference of the circle.
     *
     * @return The circumference of the circle.
     */
    public double getCircumference() {
        return 2 * Math.PI * radius;
    }


    /**
     * Compares this circle to the specified object. The result is true if and only if
     * the argument is not null and is a Circle object that represents the same
     * circle (same center and radius) as this object.
     *
     * @param o The object to compare this Circle against.
     * @return true if the given object represents a Circle equivalent to this circle, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Circle that = (Circle) o;
        return Double.compare(radius, that.radius) == 0 && Objects.equals(center, that.center);
    }


    /**
     * Returns a hash code value for the circle.
     *
     * @return A hash code value for this circle.
     */
    @Override
    public int hashCode() {
        return Objects.hash(center, radius);
    }


    /**
     * Returns a string representation of the Circle.
     *
     * @return A string representation of the Circle.
     */
    @Override
    public String toString() {
        return "Circle{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }


    /**
     * Returns an iterator over the points on the perimeter of the circle.
     *
     * @return An Iterator for Location2D objects on the circle's perimeter.
     */
    @Override
    public Iterator<Location2D> iterator() {
        return new Circle.CircleIterator();
    }


    /**
     * An iterator over Location2D objects that represent all integer points within the circle and on the edge of the circle.
     * It iterates through points within the circle's bounding box, starting from the lower left and moving to the upper right.
     */
    private class CircleIterator implements Iterator<Location2D> {
        private int currentX;
        private int currentY;
        private boolean hasNext;


        /**
         * Constructs a new CircleIterator. This iterator starts from the lower left point of the circle's bounding box
         * and iterates over all integer points within and on the edge of the circle.
         */
        public CircleIterator() {
            currentX = (int) Math.floor(center.getX() - radius);
            currentY = (int) Math.floor(center.getY() - radius);
            hasNext = updateNext();
        }


        /**
         * Checks if there is another integer point within the circle or on its edge.
         *
         * @return true if there is another integer point to iterate over, false otherwise.
         */
        @Override
        public boolean hasNext() {
            return hasNext;
        }


        /**
         * Returns the next integer point within the circle or on its edge.
         *
         * @return The next Location2D object representing an integer point within or on the edge of the circle.
         * @throws NoSuchElementException if there are no more points to iterate over.
         */
        @Override
        public Location2D next() {
            if (!hasNext) {
                throw new NoSuchElementException();
            }
            Location2D nextLocation = new Location2D(currentX, currentY);
            currentY++;
            hasNext = updateNext();
            return nextLocation;
        }


        /**
         * Updates the iterator to the next integer point within the circle or on its edge.
         * This method iterates through the points within the bounding box of the circle, checking each point
         * to see if it lies within or on the edge of the circle.
         *
         * @return true if the next point is within or on the edge of the circle, false otherwise.
         */
        private boolean updateNext() {
            while (currentX <= center.getX() + radius) {
                while (currentY <= center.getY() + radius) {
                    double distanceSquared = GeometryUtils.distanceSquared(center, new Location2D(currentX, currentY));
                    if (distanceSquared <= radius * radius) {
                        return true;
                    }
                    currentY++;
                }
                currentY = (int) Math.floor(center.getY() - radius);
                currentX++;
            }
            return false;
        }
    }
}
