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


    /**
     *
     * @return the x coordinate of the SimpleLocation
     */
    public double getX() {
        return x;
    }


    /**
     * Sets the x coordinate of the SimpleLocation to a specified value
     *
     * @param x the new value for the x coordinate of the location
     */
    public void setX(double x) {
        this.x = x;
    }


    /**
     *
     * @return the y coordinate of the SimpleLocation
     */
    public double getY() {
        return y;
    }


    /**
     * Sets the y coordinate of the SimpleLocation to a specified value
     *
     * @param y the new value for the y coordinate of the location
     */
    public void setY(double y) {
        this.y = y;
    }


    /**
     *
     * @return the z coordinate of the SimpleLocation
     */
    public double getZ() {
        return z;
    }


    /**
     * Sets the z coordinate of the SimpleLocation to a specified value
     *
     * @param z the new value for the z coordinate of the location
     */
    public void setZ(double z) {
        this.z = z;
    }


    /**
     * Adds the coordinates of the specified SimpleLocation to this SimpleLocation
     * Modifies the current SimpleLocation object and returns a reference to it
     *
     * @param locationToAdd the SimpleLocation object containing the coordinates to be added
     * @return a reference to this modified SimpleLocation object after the addition
     * @throws IllegalArgumentException if the locationToAdd is null
     */
    public SimpleLocation add(SimpleLocation locationToAdd) {
        Validate.notNull(locationToAdd);
        this.x += locationToAdd.x;
        this.y += locationToAdd.y;
        this.z += locationToAdd.z;
        return this;
    }


    /**
     * Adds the coordinates specified to this SimpleLocation
     * Modifies the current SimpleLocation object and returns a reference to it
     *
     * @param x the x coordinate to be added
     * @param y the y coordinate to be added
     * @param z the z coordinate to be added
     * @return a reference to this modified SimpleLocation object after the addition
     */
    public SimpleLocation add(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }


    /**
     * Subtracts the coordinates of the specified SimpleLocation from this SimpleLocation
     * Modifies the current SimpleLocation object and returns a reference to it
     *
     * @param locationToSubtract the SimpleLocation object containing the coordinates to be subtracted
     * @return a reference to this modified SimpleLocation object after the subtraction
     * @throws IllegalArgumentException if the locationToSubtract is null
     */
    public SimpleLocation subtract(SimpleLocation locationToSubtract) {
        Validate.notNull(locationToSubtract);
        this.x -= locationToSubtract.x;
        this.y -= locationToSubtract.y;
        this.z -= locationToSubtract.z;
        return this;
    }


    /**
     * Subtracts the coordinates specified from this SimpleLocation
     * Modifies the current SimpleLocation object and returns a reference to it
     *
     * @param x the x coordinate to be subtracted
     * @param y the y coordinate to be subtracted
     * @param z the z coordinate to be subtracted
     * @return a reference to this modified SimpleLocation object after the subtraction
     */
    public SimpleLocation subtract(double x, double y, double z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        return this;
    }


    /**
     * Multiplies the coordinates of the specified SimpleLocation by this SimpleLocation
     * Modifies the current SimpleLocation object and returns a reference to it
     *
     * @param locationToMultiply the SimpleLocation object containing the coordinates to be multiplied
     * @return a reference to this modified SimpleLocation object after the multiplication
     * @throws IllegalArgumentException if the locationToMultiply is null
     */
    public SimpleLocation multiply(SimpleLocation locationToMultiply) {
        Validate.notNull(locationToMultiply);
        this.x *= locationToMultiply.x;
        this.y *= locationToMultiply.y;
        this.z *= locationToMultiply.z;
        return this;
    }


    /**
     * Multiplies the coordinates specified by this SimpleLocation
     * Modifies the current SimpleLocation object and returns a reference to it
     *
     * @param x the x coordinate to be multiplied
     * @param y the y coordinate to be multiplied
     * @param z the z coordinate to be multiplied
     * @return a reference to this modified SimpleLocation object after the multiplication
     */
    public SimpleLocation multiply(double x, double y, double z) {
        this.x *= x;
        this.y *= y;
        this.z *= z;
        return this;
    }


    /**
     * Divides the coordinates of this SimpleLocation by the coordinates of the specified SimpleLocation
     * Modifies the current SimpleLocation object and returns a reference to it
     *
     * @param locationToDivideBy the SimpleLocation object containing the coordinates to be divided by
     * @return a reference to this modified SimpleLocation object after the division
     * @throws IllegalArgumentException if the locationToDivideBy is null
     */
    public SimpleLocation divide(SimpleLocation locationToDivideBy) {
        Validate.notNull(locationToDivideBy);
        this.x /= locationToDivideBy.x;
        this.y /= locationToDivideBy.y;
        this.z /= locationToDivideBy.z;
        return this;
    }


    /**
     * Divides this SimpleLocation by the coordinates specified
     * Modifies the current SimpleLocation object and returns a reference to it
     *
     * @param x the x coordinate to be divided by
     * @param y the y coordinate to be divided by
     * @param z the z coordinate to be divided by
     * @return a reference to this modified SimpleLocation object after the division
     */
    public SimpleLocation divide(double x, double y, double z) {
        this.x /= x;
        this.y /= y;
        this.z /= z;
        return this;
    }


    /**
     * Sets all the coordinates in this SimpleLocation to zero
     * Modifies the current SimpleLocation object and returns a reference to it
     *
     * @return a reference to this modified SimpleLocation object after all coordinates are set to zero
     */
    public SimpleLocation zero() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        return this;
    }


    /**
     * Checks if this SimpleLocation object is equal to the specified object
     *
     * @param o the object to compare for equality
     * @return true if the specified object is equal to this SimpleLocation object, false otherwise
     */
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


    /**
     * Generates a hash code for this SimpleLocation object
     *
     * @return the hash code value for this SimpleLocation object
     */
    @Override
    public int hashCode() {
        return Objects.hash(1756240379, x, y, z);
    }


    /**
     * Creates a readable string representation of this SimpleLocation object
     *
     * @return a string representation of this SimpleLocation object
     */
    @Override
    public String toString() {
        return "SimpleLocation{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
