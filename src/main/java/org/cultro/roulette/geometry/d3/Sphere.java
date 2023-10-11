package org.cultro.roulette.geometry.d3;

import org.cultro.roulette.util.GeometryUtils;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

@SuppressWarnings("unused")
public class Sphere implements Iterable<Location3D> {

    private final Location3D center;
    private final double radius;

    public Sphere(Location3D center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public Location3D getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    public double getVolume() {
        return (4 * Math.PI * Math.pow(radius, 3)) / 3.0;
    }

    public double getSurfaceArea() {
        return 4 * Math.PI * Math.pow(radius, 2);
    }

    @Override
    public Iterator<Location3D> iterator() {
        return new SphereIterator();
    }

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

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(center, radius);
    }

    private class SphereIterator implements Iterator<Location3D> {
        private int currentX;
        private int currentY;
        private int currentZ;
        private boolean hasNext;

        public SphereIterator() {
            currentX = (int) Math.floor(center.getX() - radius);
            currentY = (int) Math.floor(center.getY() - radius);
            currentZ = (int) Math.floor(center.getZ() - radius);

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
