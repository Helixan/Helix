package org.cultro.roulette.region;

import org.cultro.roulette.lang.Validate;

import java.io.Serializable;
import java.util.Objects;

@SuppressWarnings("unused")
public final class SimpleLocation implements Serializable {

    private double x;
    private double y;
    private double z;


    /**
     * Constructs a SimpleLocation object with the specified x, y, and z coordinates.
     *
     * @param x the double in which the x coordinate will be equal to
     * @param y the double in which the y coordinate will be equal to
     * @param z the double in which the z coordinate will be equal to
     */
    public SimpleLocation(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }


    /**
     * Constructs a SimpleLocation object with the specified x and y coordinates. All other coordinates default
     * to a value of zero
     *
     * @param x the double in which the x coordinate will be equal to
     * @param y the double in which the y coordinate will be equal to
     */
    public SimpleLocation(double x, double y) {
        this(x, y, 0);
    }


    /**
     * Constructs a SimpleLocation object with the specified x coordinate. All other coordinates default
     * to a value of zero
     *
     * @param x the double in which the x coordinate will be equal to
     */
    public SimpleLocation(double x) {
        this(x, 0, 0);
    }


    /**
     * Constructs a SimpleLocation object with the coordinates copied from the specified SimpleLocation
     *
     * @param location the SimpleLocation object from which to copy the coordinates
     */
    public SimpleLocation(SimpleLocation location) {
        this(location.x, location.y, location.z);
    }


    /**
     * Constructs a SimpleLocation object with the coordinates all set to a default value of zero
     */
    public SimpleLocation() {
        this(0, 0, 0);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public SimpleLocation add(SimpleLocation locationToAdd) {
        Validate.notNull(locationToAdd);
        this.x += locationToAdd.x;
        this.y += locationToAdd.y;
        this.z += locationToAdd.z;
        return this;
    }

    public SimpleLocation add(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    public SimpleLocation subtract(SimpleLocation locationToAdd) {
        Validate.notNull(locationToAdd);
        this.x -= locationToAdd.x;
        this.y -= locationToAdd.y;
        this.z -= locationToAdd.z;
        return this;
    }

    public SimpleLocation subtract(double x, double y, double z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        return this;
    }

    public SimpleLocation multiply(SimpleLocation locationToAdd) {
        Validate.notNull(locationToAdd);
        this.x *= locationToAdd.x;
        this.y *= locationToAdd.y;
        this.z *= locationToAdd.z;
        return this;
    }

    public SimpleLocation multiply(double x, double y, double z) {
        this.x *= x;
        this.y *= y;
        this.z *= z;
        return this;
    }

    public SimpleLocation divide(SimpleLocation locationToAdd) {
        Validate.notNull(locationToAdd);
        this.x /= locationToAdd.x;
        this.y /= locationToAdd.y;
        this.z /= locationToAdd.z;
        return this;
    }

    public SimpleLocation divide(double x, double y, double z) {
        this.x /= x;
        this.y /= y;
        this.z /= z;
        return this;
    }

    public SimpleLocation zero() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SimpleLocation)) {
            return false;
        }
        SimpleLocation location = (SimpleLocation) o;
        return Double.compare(location.x, x) == 0 && Double.compare(location.y, y) == 0 && Double.compare(location.z, z) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(1756240379, x, y, z);
    }

    @Override
    public String toString() {
        return "SimpleLocation{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
