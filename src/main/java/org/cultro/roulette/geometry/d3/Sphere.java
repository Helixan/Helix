package org.cultro.roulette.geometry.d3;

import org.cultro.roulette.util.GeometryUtils;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Represents a sphere in three-dimensional space. This class encapsulates a 3D location that serves as the sphere's center
 * and a radius. It provides methods to retrieve the sphere's center, radius, volume, and surface area.
 * <p>
 * The Sphere class also implements Iterable, allowing iteration over discrete Location3D points within the sphere.
 */
@SuppressWarnings("unused")
public class Sphere implements Iterable<Location3D> {

    private final Location3D center;
    private final double radius;


    /**
     * Constructs a new Sphere object.
     *
     * @param center The center of the sphere, represented as a Location3D object.
     * @param radius The radius of the sphere.
     */
    public Sphere(Location3D center, double radius) {
        this.center = center;
        this.radius = radius;
    }


    /**
     * Retrieves the center of the sphere.
     *
     * @return The center of the sphere as a Location3D object.
     */
    public Location3D getCenter() {
        return center;
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
     * Calculates and returns the volume of the sphere.
     *
     * @return The volume of the sphere.
     */
    public double getVolume() {
        return (4 * Math.PI * Math.pow(radius, 3)) / 3.0;
    }


    /**
     * Calculates and returns the surface area of the sphere.
     *
     * @return The surface area of the sphere.
     */
    public double getSurfaceArea() {
        return 4 * Math.PI * Math.pow(radius, 2);
    }


    /**
     * Provides an iterator over Location3D objects within the sphere.
     *
     * @return An Iterator over Location3D objects.
     */
    @Override
    public Iterator<Location3D> iterator() {
        return new SphereIterator();
    }


    /**
     * Compares this Sphere to another object for equality.
     *
     * @param o The object to compare with.
     * @return true if the other object is a Sphere with the same center and radius, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Sphere that = (Sphere) o;
        return Double.compare(radius, that.radius) == 0 && Objects.equals(center, that.center);
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
     * Inner class that provides an Iterator implementation to iterate over Location3D objects within the sphere.
     */
    private class SphereIterator implements Iterator<Location3D> {
        private int currentX;
        private int currentY;
        private int currentZ;
        private boolean hasNext;


        /**
         * Constructs a SphereIterator.
         * Initializes the iterator to the starting position.
         */
        public SphereIterator() {
            currentX = (int) Math.floor(center.getX() - radius);
            currentY = (int) Math.floor(center.getY() - radius);
            currentZ = (int) Math.floor(center.getZ() - radius);

            hasNext = updateNext();
        }


        /**
         * Checks if there are more points to iterate over.
         *
         * @return true if there are more points, false otherwise.
         */
        @Override
        public boolean hasNext() {
            return hasNext;
        }


        /**
         * Retrieves the next Location3D point in the sphere.
         *
         * @return The next Location3D object.
         * @throws NoSuchElementException If there are no more elements to iterate over.
         */
        @Override
        public Location3D next() {
            if (!hasNext) {
                throw new NoSuchElementException();
            }
            Location3D nextLocation = new Location3D(currentX, currentY, currentZ);
            currentZ++;
            hasNext = updateNext();
            return nextLocation;
        }


        /**
         * Updates the iterator to the next valid Location3D within the sphere.
         *
         * @return true if a next location is available, false otherwise.
         */
        private boolean updateNext() {
            while (currentX <= center.getX() + radius) {
                while (currentY <= center.getY() + radius) {
                    while (currentZ <= center.getZ() + radius) {
                        double distanceSquared = GeometryUtils.distanceSquared(center, new Location3D(currentX, currentY, currentZ));
                        if (distanceSquared <= radius * radius) {
                            return true;
                        }
                        currentZ++;
                    }
                    currentZ = (int) Math.floor(center.getZ() - radius);
                    currentY++;
                }
                currentY = (int) Math.floor(center.getY() - radius);
                currentX++;
            }
            return false;
        }
    }
}