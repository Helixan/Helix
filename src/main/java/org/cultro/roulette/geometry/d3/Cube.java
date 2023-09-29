package org.cultro.roulette.geometry.d3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Cube implements Iterable<Location3D> {

    private final Location3D minCorner;
    private final Location3D maxCorner;

    public Cube(Location3D minCorner, Location3D maxCorner) {
        this.minCorner = minCorner;
        this.maxCorner = maxCorner;
    }

    @Override
    public Iterator<Location3D> iterator() {
        return new CubeIterator();
    }

    private class CubeIterator implements Iterator<Location3D> {
        private int currentX;
        private int currentY;
        private int currentZ;
        private boolean hasNext;

        public CubeIterator() {
            currentX = (int) Math.floor(minCorner.getX());
            currentY = (int) Math.floor(minCorner.getY());
            currentZ = (int) Math.floor(minCorner.getZ());

            hasNext = updateNext();
        }

        @Override
        public boolean hasNext() {
            return hasNext;
        }

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

        private boolean updateNext() {
            while (currentX <= maxCorner.getX()) {
                while (currentY <= maxCorner.getY()) {
                    while (currentZ <= maxCorner.getZ()) {
                        if (currentX >= minCorner.getX() && currentX >= minCorner.getY() && currentZ >= minCorner.getZ()) {
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