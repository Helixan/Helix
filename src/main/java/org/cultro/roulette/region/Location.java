package org.cultro.roulette.region;

import org.cultro.roulette.lang.Validate;

import java.util.Objects;

public final class Location {

    private double x;
    private double y;
    private double z;

    public Location(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Location(double x, double y) {
        this(x, y, 0);
    }

    public Location(double x) {
        this(x, 0, 0);
    }

    public Location() {
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

    public Location add(Location locationToAdd) {
        Validate.notNull(locationToAdd);
        this.x += locationToAdd.x;
        this.y += locationToAdd.y;
        this.z += locationToAdd.z;
        return this;
    }

    public Location add(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    public Location subtract(Location locationToAdd) {
        Validate.notNull(locationToAdd);
        this.x -= locationToAdd.x;
        this.y -= locationToAdd.y;
        this.z -= locationToAdd.z;
        return this;
    }

    public Location subtract(double x, double y, double z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        return this;
    }

    public Location multiply(Location locationToAdd) {
        Validate.notNull(locationToAdd);
        this.x *= locationToAdd.x;
        this.y *= locationToAdd.y;
        this.z *= locationToAdd.z;
        return this;
    }

    public Location multiply(double x, double y, double z) {
        this.x *= x;
        this.y *= y;
        this.z *= z;
        return this;
    }

    public Location divide(Location locationToAdd) {
        Validate.notNull(locationToAdd);
        this.x /= locationToAdd.x;
        this.y /= locationToAdd.y;
        this.z /= locationToAdd.z;
        return this;
    }

    public Location divide(double x, double y, double z) {
        this.x /= x;
        this.y /= y;
        this.z /= z;
        return this;
    }

    public Location zero() {
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
        if (!(o instanceof Location)) {
            return false;
        }
        Location location = (Location) o;
        return Double.compare(location.x, x) == 0 && Double.compare(location.y, y) == 0 && Double.compare(location.z, z) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(1756240379, x, y, z);
    }
}
