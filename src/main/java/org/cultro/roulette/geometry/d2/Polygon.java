package org.cultro.roulette.geometry.d2;

import org.cultro.roulette.lang.Validate;

import java.util.Arrays;

public class Polygon {

    private final Location2D[] corners;

    public Polygon(Location2D... corners) {
        Validate.notNull(corners, "The corners for a polygon cannot be null");
        Validate.isGreaterThanOrEqualTo(1, corners.length, "A polygon must have at least one corner");
        this.corners = corners;
    }

    public Location2D[] getCorners() {
        return corners;
    }

    private double getArea() {
        double area = 0;

        int j;
        for (int i = 0; i < corners.length; i++) {
            j = (i + 1) % corners.length;
            area += corners[i].getX() * corners[j].getY() - corners[j].getX() * corners[i].getY();
        }
        return area / 2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Polygon polygon = (Polygon) o;
        return Arrays.equals(corners, polygon.corners);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(corners);
    }

    @Override
    public String toString() {
        return "Polygon{" +
                "corners=" + Arrays.toString(corners) +
                '}';
    }
}
