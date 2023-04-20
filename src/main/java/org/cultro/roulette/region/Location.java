package org.cultro.roulette.region;

import org.cultro.roulette.lang.Validate;

public class Location {

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

    public Location subtract(Location locationToAdd) {
        Validate.notNull(locationToAdd);
        this.x -= locationToAdd.x;
        this.y -= locationToAdd.y;
        this.z -= locationToAdd.z;
        return this;
    }

    public Location multiply(Location locationToAdd) {
        Validate.notNull(locationToAdd);
        this.x *= locationToAdd.x;
        this.y *= locationToAdd.y;
        this.z *= locationToAdd.z;
        return this;
    }

    public Location divide(Location locationToAdd) {
        Validate.notNull(locationToAdd);
        this.x /= locationToAdd.x;
        this.y /= locationToAdd.y;
        this.z /= locationToAdd.z;
        return this;
    }
}
