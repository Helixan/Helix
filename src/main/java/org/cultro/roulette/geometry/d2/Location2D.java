package org.cultro.roulette.geometry.d2;

import org.cultro.roulette.geometry.Location;
import org.cultro.roulette.lang.Validate;

/**
 * The Location2D class represents a two-dimensional location or position in Euclidean space.
 * It implements the Location interface and provides various location operations such as
 * addition, subtraction, multiplication, and division by another location in the same dimension.
 * <p>
 * This class is specifically designed for 2D locations and is suitable for use in
 * two-dimensional geometric calculations and graphics applications.
 */
@SuppressWarnings("unused")
public class Location2D implements Location {

    private final double[] elements = new double[2];


    /**
     * Constructs a new Location2D with the specified coordinates.
     *
     * @param x The x-coordinate of the location.
     * @param y The y-coordinate of the location.
     */
    public Location2D(double x, double y) {
        elements[0] = x;
        elements[1] = y;
    }


    /**
     * Gets the x-coordinate of the 2D location.
     *
     * @return The x-coordinate of the location.
     */
    public double getX() {
        return elements[0];
    }


    /**
     * Gets the y-coordinate of the 2D location.
     *
     * @return The y-coordinate of the location.
     */
    public double getY() {
        return elements[1];
    }


    /**
     * Retrieves the component of the location at the specified index.
     *
     * @param index The index of the component (0 for x, 1 for y).
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
     * @return An array containing the x and y coordinates of the location.
     */
    @Override
    public double[] getElements() {
        return elements;
    }


    /**
     * Retrieves the dimension of the 2D location, which is always 2.
     *
     * @return The dimension of the location, which is 2.
     */
    @Override
    public int getDimension() {
        return 2;
    }


    /**
     * Adds another location to this location component-wise.
     *
     * @param location The location to add.
     * @return A new location that is the result of the addition.
     * @throws IllegalArgumentException If the provided location has a different dimension or if the provided location is null.
     */
    @Override
    public Location2D add(Location location) {
        Validate.notNull(location, "You cannot add a location to a null location.");
        Validate.isEquivalent(location.getDimension(), this.getDimension(), "You cannot add locations that exist in different dimensions.");
        return new Location2D(elements[0] + location.getComponent(0), elements[1] + location.getComponent(1));
    }


    /**
     * Subtracts another location from this location component-wise.
     *
     * @param location The location to subtract.
     * @return A new location that is the result of the subtraction.
     * @throws IllegalArgumentException If the provided location has a different dimension or if the provided location is null.
     */
    @Override
    public Location2D subtract(Location location) {
        Validate.notNull(location, "You cannot subtract a null location from a location.");
        Validate.isEquivalent(location.getDimension(), this.getDimension(), "You cannot subtract locations that exist in different dimensions.");
        return new Location2D(elements[0] - location.getComponent(0), elements[1] - location.getComponent(1));
    }


    /**
     * Multiplies this location by another location component-wise.
     *
     * @param location The location to multiply by.
     * @return A new location that is the result of the multiplication.
     * @throws IllegalArgumentException If the provided location has a different dimension or if the provided location is null.
     */
    @Override
    public Location2D multiply(Location location) {
        Validate.notNull(location, "You cannot multiply a location by a null location.");
        Validate.isEquivalent(location.getDimension(), this.getDimension(), "You cannot multiply locations that exist in different dimensions.");
        return new Location2D(elements[0] * location.getComponent(0), elements[1] * location.getComponent(1));
    }


    /**
     * Divides this location by another location component-wise.
     *
     * @param location The location to divide by.
     * @return A new location that is the result of the division.
     * @throws IllegalArgumentException If the provided location has a different dimension or if the provided location is null.
     */
    @Override
    public Location2D divide(Location location) {
        Validate.notNull(location, "You cannot divide a location by a null location.");
        Validate.isEquivalent(location.getDimension(), this.getDimension(), "You cannot divide locations that exist in different dimensions.");
        return new Location2D(elements[0] / location.getComponent(0), elements[1] / location.getComponent(1));
    }

    @Override
    public void setComponent(int component, double value) {
        Validate.isValidIndex(elements, component, "The targeted component is out of bounds.");
        elements[component] = value;
    }
}