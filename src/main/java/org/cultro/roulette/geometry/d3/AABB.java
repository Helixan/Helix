package org.cultro.roulette.geometry.d3;

import java.util.Objects;

/**
 * The AABB (Axis-Aligned Bounding Box) class represents a 3D bounding box defined by
 * two opposite corners: a minimum corner and a maximum corner. This class is used to
 * define the spatial extent of an object in a 3D space.
 * <p>
 * An AABB is axis-aligned, meaning that its edges are parallel to the coordinate axes
 * (X, Y, and Z), making it an efficient structure for collision detection and containment
 * checks.
 */
public class AABB {

    private final Location3D min;
    private final Location3D max;


    /**
     * Constructs an AABB given the coordinates of two opposite corners.
     *
     * @param x1 The X-coordinate of the first corner.
     * @param y1 The Y-coordinate of the first corner.
     * @param z1 The Z-coordinate of the first corner.
     * @param x2 The X-coordinate of the second corner.
     * @param y2 The Y-coordinate of the second corner.
     * @param z2 The Z-coordinate of the second corner.
     */
    public AABB(double x1, double y1, double z1, double x2, double y2, double z2) {
        this.min = new Location3D(Math.min(x1, x2), Math.min(y1, y2), Math.min(z1, z2));
        this.max = new Location3D(Math.max(x1, x2), Math.max(y1, y2), Math.max(z1, z2));
    }


    /**
     * Gets the minimum corner of the AABB.
     *
     * @return The minimum corner of the AABB.
     */
    public Location3D getMin() {
        return min;
    }


    /**
     * Gets the maximum corner of the AABB.
     *
     * @return The maximum corner of the AABB.
     */
    public Location3D getMax() {
        return max;
    }


    /**
     * Returns a string representation of the AABB in the format:
     * "AABB{min=MIN_LOCATION, max=MAX_LOCATION}".
     *
     * @return A string representation of the AABB.
     */
    @Override
    public String toString() {
        return "AABB{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }


    /**
     * Compares this AABB to another object for equality. Two AABBs are considered equal
     * if their minimum and maximum corners are equal.
     *
     * @param o The object to compare to this AABB.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AABB aabb = (AABB) o;
        return Objects.equals(min, aabb.min) && Objects.equals(max, aabb.max);
    }


    /**
     * Computes the hash code of this AABB based on its minimum and maximum corners.
     *
     * @return The hash code of this AABB.
     */
    @Override
    public int hashCode() {
        return Objects.hash(min, max);
    }
}