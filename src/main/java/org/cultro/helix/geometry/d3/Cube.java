package org.cultro.helix.geometry.d3;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * The Cube class represents a cube in three-dimensional space, defined by its minimum and maximum corners.
 * It provides methods to calculate the cube's volume, surface area, and to iterate over discrete Location3D points
 * within the cube.
 */
@SuppressWarnings("unused")
public class Cube implements Iterable<Location3D> {

    private final Location3D minCorner;
    private final Location3D maxCorner;


    /**
     * Constructs a Cube using two opposite corners.
     *
     * @param corner1 The first corner of the cube.
     * @param corner2 The second corner diagonally opposite to the first.
     */
    public Cube(Location3D corner1, Location3D corner2) {
        this.minCorner = new Location3D(Math.min(corner1.getX(), corner2.getX()), Math.min(corner1.getY(), corner2.getY()), Math.min(corner1.getZ(), corner2.getZ()));
        this.maxCorner = new Location3D(Math.max(corner1.getX(), corner2.getX()), Math.max(corner1.getY(), corner2.getY()), Math.max(corner1.getZ(), corner2.getZ()));
    }


    /**
     * Retrieves the minimum corner of the cube.
     *
     * @return The minimum corner as a Location3D object.
     */
    public Location3D getMinCorner() {
        return minCorner;
    }


    /**
     * Retrieves the maximum corner of the cube.
     *
     * @return The maximum corner as a Location3D object.
     */
    public Location3D getMaxCorner() {
        return maxCorner;
    }


    /**
     * Calculates the volume of the cube.
     *
     * @return The volume of the cube.
     */
    public double getVolume() {
        return (maxCorner.getX() - minCorner.getX()) *  (maxCorner.getY() - minCorner.getY()) * (maxCorner.getZ() - minCorner.getZ());
    }


    /**
     * Calculates the surface area of the cube.
     *
     * @return The surface area of the cube.
     */
    public double getSurfaceArea() {
        double length = maxCorner.getX() - minCorner.getX();
        double width = maxCorner.getY() - minCorner.getY();
        double height = maxCorner.getZ() - minCorner.getZ();
        return 2 * (width * length + height * length + height * width);
    }


    /**
     * Provides an iterator over Location3D objects within the cube.
     *
     * @return An Iterator over Location3D objects.
     */
    @Override
    public Iterator<Location3D> iterator() {
        return new CubeIterator();
    }


    /**
     * Compares this Cube to another object for equality.
     *
     * @param o The object to compare with.
     * @return true if the other object is a Cube with the same min and max locations, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cube that = (Cube) o;
        return Objects.equals(minCorner, that.minCorner) && Objects.equals(maxCorner, that.maxCorner);
    }


    /**
     * Computes the hash code for the Cube object.
     *
     * @return The hash code of the Cube.
     */
    @Override
    public int hashCode() {
        return Objects.hash(minCorner, maxCorner);
    }


    /**
     * Provides a string representation of the Cube object.
     *
     * @return A string representation of the Cube.
     */
    @Override
    public String toString() {
        return "Cube{" +
                "minCorner=" + minCorner +
                ", maxCorner=" + maxCorner +
                '}';
    }


    /**
     * Inner class that provides an Iterator implementation to iterate over Location3D objects within the cube.
     */
    private class CubeIterator implements Iterator<Location3D> {
        private int currentX;
        private int currentY;
        private int currentZ;
        private boolean hasNext;


        /**
         * Constructs a CubeIterator.
         * Initializes the iterator to the starting position.
         */
        public CubeIterator() {
            currentX = (int) Math.floor(minCorner.getX());
            currentY = (int) Math.floor(minCorner.getY());
            currentZ = (int) Math.floor(minCorner.getZ());

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
         * Retrieves the next Location3D point in the cube.
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
         * Updates the iterator to the next valid Location3D within the cube.
         *
         * @return true if a next location is available, false otherwise.
         */
        private boolean updateNext() {
            while (currentX <= maxCorner.getX()) {
                while (currentY <= maxCorner.getY()) {
                    while (currentZ <= maxCorner.getZ()) {
                        if (currentX >= minCorner.getX() && currentY >= minCorner.getY() && currentZ >= minCorner.getZ()) {
                            return true;
                        }
                        currentZ++;
                    }
                    currentZ = (int) Math.floor(minCorner.getZ());
                    currentY++;
                }
                currentY = (int) Math.floor(minCorner.getY());
                currentX++;
            }
            return false;
        }
    }
}