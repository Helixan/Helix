package org.cultro.helix.geometry.dn;

import org.cultro.helix.geometry.Location;
import org.cultro.helix.lang.Validate;

import java.util.Arrays;

/**
 * The NDLocation class represents a location or position in an n-dimensional space.
 * It implements the Location interface and provides various location operations such
 * as addition, subtraction, multiplication, and division by another location in the
 * same dimension.
 * <p>
 * The class allows working with locations in spaces with any number of dimensions.
 */
@SuppressWarnings("unused")
public class NDLocation implements Location {

    private final double[] elements;


    /**
     * Constructs a new NDLocation with the specified elements.
     *
     * @param elements The elements representing the location in each dimension.
     */
    public NDLocation(double... elements) {
        if (elements == null) {
            this.elements = new double[0];
        } else {
            this.elements = elements;
        }
    }

    /**
     * Constructs a new NDLocation with empty elements.
     *
     * @param size The size of the empty location.
     */
    public NDLocation(int size) {
        Validate.isGreaterThanOrEqualTo(size, 0, "A location cannot be a negative size.");
        this.elements = new double[size];
    }

    /**
     * Retrieves the component of the location at the specified index.
     *
     * @param index The index of the component.
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
     * @return An array containing all the elements representing the location.
     */
    @Override
    public double[] getElements() {
        return Arrays.copyOf(elements, elements.length);
    }


    /**
     * Retrieves the dimension of the location.
     *
     * @return The dimension of the location.
     */
    @Override
    public int getDimension() {
        return elements.length;
    }


    /**
     * Adds another location to this location component-wise.
     *
     * @param location The location to add.
     * @return A new location that is the result of the addition.
     * @throws IllegalArgumentException If the locations have different dimensions or the provided location is null.
     */
    @Override
    public NDLocation add(Location location) {
        Validate.notNull(location, "You cannot add a location to a null location.");
        Validate.isEquivalent(location.getDimension(), this.getDimension(), "You cannot add locations that exist in different dimensions.");
        double[] addedElements = new double[elements.length];

        for (int i = 0; i < elements.length; i++) {
            addedElements[i] = this.getComponent(i) + location.getComponent(i);
        }
        return new NDLocation(addedElements);
    }


    /**
     * Subtracts another location from this location component-wise.
     *
     * @param location The location to subtract.
     * @return A new location that is the result of the subtraction.
     * @throws IllegalArgumentException If the locations have different dimensions or the provided location is null.
     */
    @Override
    public NDLocation subtract(Location location) {
        Validate.notNull(location, "You cannot subtract a null location from a location.");
        Validate.isEquivalent(location.getDimension(), this.getDimension(), "You cannot subtract locations that exist in different dimensions.");
        double[] subtractedElements = new double[elements.length];

        for (int i = 0; i < elements.length; i++) {
            subtractedElements[i] = this.getComponent(i) - location.getComponent(i);
        }
        return new NDLocation(subtractedElements);
    }


    /**
     * Multiplies this location by another location component-wise.
     *
     * @param location The location to multiply by.
     * @return A new location that is the result of the multiplication.
     * @throws IllegalArgumentException If the locations have different dimensions or the provided location is null.
     */
    @Override
    public NDLocation multiply(Location location) {
        Validate.notNull(location, "You cannot multiply a location by a null location.");
        Validate.isEquivalent(location.getDimension(), this.getDimension(), "You cannot multiply locations that exist in different dimensions.");
        double[] multipliedElements = new double[elements.length];

        for (int i = 0; i < elements.length; i++) {
            multipliedElements[i] = this.getComponent(i) * location.getComponent(i);
        }
        return new NDLocation(multipliedElements);
    }


    /**
     * Divides this location by another location component-wise.
     *
     * @param location The location to divide by.
     * @return A new location that is the result of the division.
     * @throws IllegalArgumentException If the locations have different dimensions or the provided location is null.
     */
    @Override
    public NDLocation divide(Location location) {
        Validate.notNull(location, "You cannot divide a location by a null location.");
        Validate.isEquivalent(location.getDimension(), this.getDimension(), "You cannot divide locations that exist in different dimensions.");
        double[] dividedElements = new double[elements.length];

        for (int i = 0; i < elements.length; i++) {
            dividedElements[i] = this.getComponent(i) / location.getComponent(i);
        }
        return new NDLocation(dividedElements);
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


    /**
     * Compares this NDLocation to another object for equality.
     *
     * @param o The object to compare with.
     * @return true if the other object is a NDLocation with the same elements, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NDLocation that = (NDLocation) o;
        return Arrays.equals(elements, that.elements);
    }


    /**
     * Computes the hash code for the NDLocation object.
     *
     * @return The hash code of the NDLocation.
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(elements);
    }


    /**
     * Provides a string representation of the NDLocation object.
     *
     * @return A string representation of the NDLocation.
     */
    @Override
    public String toString() {
        return "NDLocation{" +
                "elements=" + Arrays.toString(elements) +
                '}';
    }
}