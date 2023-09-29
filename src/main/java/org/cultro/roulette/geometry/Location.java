package org.cultro.roulette.geometry;

/**
 * The Location interface represents a location or position in n-dimensional space.
 * Implementing classes should provide methods for retrieving components, getting
 * the dimension, and performing mathematical operations on locations.
 */
public interface Location {

    /**
     * Retrieves the component of the location at the specified index.
     *
     * @param index The index of the component.
     * @return The component value at the specified index.
     */

    double getComponent(int index);


    /**
     * Retrieves all the elements representing the location in each dimension.
     *
     * @return An array containing all the elements representing the location.
     */
    double[] getElements();


    /**
     * Retrieves the dimension of the location.
     *
     * @return The dimension of the location.
     */
    int getDimension();


    /**
     * Adds another location to this location.
     *
     * @param location The location to add.
     * @return A new location resulting from the addition.
     */
    Location add(Location location);


    /**
     * Subtracts another location from this location.
     *
     * @param location The location to subtract.
     * @return A new location resulting from the subtraction.
     */
    Location subtract(Location location);


    /**
     * Multiplies this location by another location component-wise.
     *
     * @param location The location to multiply by.
     * @return A new location resulting from the multiplication.
     */
    Location multiply(Location location);


    /**
     * Divides this location by another location component-wise.
     *
     * @param location The location to divide by.
     * @return A new location resulting from the division.
     */
    Location divide(Location location);
}