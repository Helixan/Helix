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
@SuppressWarnings("unused")
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
     * Constructs an AABB using two opposite corners.
     *
     * @param corner1 The first corner of the AABB.
     * @param corner2 The second corner diagonally opposite to the first.
     */
    public AABB(Location3D corner1, Location3D corner2) {
        this(corner1.getX(), corner1.getY(), corner1.getZ(), corner2.getX(), corner2.getY(), corner2.getZ());
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
     * Compares this AABB to another object for equality.
     *
     * @param o The object to compare with.
     * @return true if the other object is a AABB with the same min and max locations, false otherwise.
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
     * Computes the hash code for the AABB object.
     *
     * @return The hash code of the AABB.
     */
    @Override
    public int hashCode() {
        return Objects.hash(min, max);
    }


    /**
     * Provides a string representation of the AABB object.
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
}