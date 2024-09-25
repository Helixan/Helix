package org.cultro.roulette.geometry.d2;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Rectangle implements Iterable<Location2D> {

    private final Location2D minCorner;

    private final Location2D maxCorner;

    public Rectangle(Location2D corner1, Location2D corner2) {
        this.minCorner = new Location2D(Math.min(corner1.getX(), corner2.getX()), Math.min(corner1.getY(), corner2.getY()));
        this.maxCorner = new Location2D(Math.max(corner1.getX(), corner2.getX()), Math.max(corner1.getY(), corner2.getY()));
    }

    public Location2D getMinCorner() {
        return minCorner;
    }

    public Location2D getMaxCorner() {
        return maxCorner;
    }

    public double getArea() {
        return (maxCorner.getX() - minCorner.getX()) * (maxCorner.getY() - minCorner.getY());
    }

    public double getPerimeter() {
        return 2 * (maxCorner.getX() - minCorner.getX()) + 2 * (maxCorner.getY() - minCorner.getY());
    }

    @Override
    public Iterator<Location2D> iterator() {
        return new RectangleIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Rectangle that = (Rectangle) o;
        return Objects.equals(minCorner, that.minCorner) && Objects.equals(maxCorner, that.maxCorner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(minCorner, maxCorner);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "minCorner=" + minCorner +
                ", maxCorner=" + maxCorner +
                '}';
    }

    private class RectangleIterator implements Iterator<Location2D> {
        private int currentX;
        private int currentY;
        private boolean hasNext;

        public RectangleIterator() {
            currentX = (int) Math.floor(minCorner.getX());
            currentY = (int) Math.floor(minCorner.getY());

            hasNext = updateNext();
        }

        @Override
        public boolean hasNext() {
            return hasNext;
        }

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

        private boolean updateNext() {
            while (currentX <= maxCorner.getX()) {
                while (currentY <= maxCorner.getY()) {
                    if (currentX >= minCorner.getX() && currentY >= minCorner.getY()) {
                        return true;
                    }
                    currentY++;
                }
                currentY = (int) Math.floor(minCorner.getY());
                currentX++;
            }
            return false;
        }
    }

}
