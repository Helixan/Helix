package org.cultro.roulette.geometry.d3;

import org.cultro.roulette.geometry.Location;
import org.cultro.roulette.lang.Validate;

/**
 * The Location3D class represents a three-dimensional location or position in Euclidean space.
 * It implements the Location interface and provides various location operations such as
 * addition, subtraction, multiplication, and division by another location in the same dimension.
 * <p>
 * This class is specifically designed for 3D locations and is suitable for use in
 * three-dimensional geometric calculations and graphics applications.
 */
@SuppressWarnings("unused")
public class Location3D implements Location {

    private final double[] elements = new double[3];


    /**
     * Constructs a new Location3D with the specified coordinates.
     *
     * @param x The x-coordinate of the location.
     * @param y The y-coordinate of the location.
     * @param z The z-coordinate of the location.
     */
    public Location3D(double x, double y, double z) {
        elements[0] = x;
        elements[1] = y;
        elements[2] = z;
    }


    /**
     * Gets the x-coordinate of the 3D location.
     *
     * @return The x-coordinate of the location.
     */
    public double getX() {
        return elements[0];
    }


    /**
     * Gets the y-coordinate of the 3D location.
     *
     * @return The y-coordinate of the location.
     */
    public double getY() {
        return elements[1];
    }


    /**
     * Gets the z-coordinate of the 3D location.
     *
     * @return The z-coordinate of the location.
     */
    public double getZ() {
        return elements[2];
    }


    /**
     * Retrieves the component of the location at the specified index.
     *
     * @param index The index of the component (0 for x, 1 for y, 2 for z).
     * @return The component value at the specified index, or 0 if the index is out of bounds.
     */
    @Override
    public double getComponent(int index) {
        if (index >= elements.length) {
            return 0;
        }
        return elements[index];
    }


    /**
     * Retrieves all the elements representing the location in each dimension.
     *
     * @return An array containing the x, y, and z coordinates of the location.
     */
    @Override
    public double[] getElements() {
        return elements;
    }


    /**
     * Retrieves the dimension of the 3D location, which is always 3.
     *
     * @return The dimension of the location, which is 3.
     */
    @Override
    public int getDimension() {
        return 3;
    }


    /**
     * Adds another location to this location component-wise.
     *
     * @param location The location to add.
     * @return A new location that is the result of the addition.
     * @throws IllegalArgumentException If the provided location has a different dimension or if the provided location is null.
     */
    @Override
    public Location3D add(Location location) {
        Validate.notNull(location, "You cannot add a location to a null location.");
        Validate.isEquivalent(location.getDimension(), this.getDimension(), "You cannot add locations that exist in different dimensions.");
        return new Location3D(elements[0] + location.getComponent(0), elements[1] + location.getComponent(1), elements[2] + location.getComponent(2));
    }


    /**
     * Subtracts another location from this location component-wise.
     *
     * @param location The location to subtract.
     * @return A new location that is the result of the subtraction.
     * @throws IllegalArgumentException If the provided location has a different dimension or if the provided location is null.
     */
    @Override
    public Location3D subtract(Location location) {
        Validate.notNull(location, "You cannot subtract a null location from a location.");
        Validate.isEquivalent(location.getDimension(), this.getDimension(), "You cannot subtract locations that exist in different dimensions.");
        return new Location3D(elements[0] - location.getComponent(0), elements[1] - location.getComponent(1), elements[2] - location.getComponent(2));
    }


    /**
     * Multiplies this location by another location component-wise.
     *
     * @param location The location to multiply by.
     * @return A new location that is the result of the multiplication.
     * @throws IllegalArgumentException If the provided location has a different dimension or if the provided location is null.
     */
    @Override
    public Location3D multiply(Location location) {
        Validate.notNull(location, "You cannot multiply a location by a null location.");
        Validate.isEquivalent(location.getDimension(), this.getDimension(), "You cannot multiply locations that exist in different dimensions.");
        return new Location3D(elements[0] * location.getComponent(0), elements[1] * location.getComponent(1), elements[2] * location.getComponent(2));
    }


    /**
     * Divides this location by another location component-wise.
     *
     * @param location The location to divide by.
     * @return A new location that is the result of the division.
     * @throws IllegalArgumentException If the provided location has a different dimension or if the provided location is null.
     */
    @Override
    public Location3D divide(Location location) {
        Validate.notNull(location, "You cannot divide a location by a null location.");
        Validate.isEquivalent(location.getDimension(), this.getDimension(), "You cannot divide locations that exist in different dimensions.");
        return new Location3D(elements[0] / location.getComponent(0), elements[1] / location.getComponent(1), elements[2] / location.getComponent(2));
    }


    /**
     * Sets the value of the specified component of this location.
     *
     * @param component The index of the component to be set.
     * @param value The value to be assigned to the specified component.
     * @throws IllegalArgumentException If the provided component index is out of bounds.
     */
    @Override
    public void setComponent(int component, double value) {
        Validate.isValidIndex(elements, component, "The targeted component is out of bounds.");
        elements[component] = value;
    }
}