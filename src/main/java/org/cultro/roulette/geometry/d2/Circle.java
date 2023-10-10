package org.cultro.roulette.geometry.d2;

import org.cultro.roulette.util.GeometryUtils;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

@SuppressWarnings("unused")
public class Circle implements Iterable<Location2D> {

    private final Location2D center;
    private final double radius;

    public Circle(Location2D center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public Location2D getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    public double getCircumference() {
        return 2 * Math.PI * radius;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(center, radius);
    }

    @Override
    public String toString() {
        return "Circle{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }

    @Override
    public Iterator<Location2D> iterator() {
        return new Circle.CircleIterator();
    }

    private class CircleIterator implements Iterator<Location2D> {
        private int currentX;
        private int currentY;
        private boolean hasNext;

        public CircleIterator() {
            currentX = (int) Math.floor(center.getX() - radius);
            currentY = (int) Math.floor(center.getY() - radius);

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
