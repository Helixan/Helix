package org.cultro.helix.geometry.dn;

import org.cultro.helix.geometry.Vector;
import org.cultro.helix.lang.Validate;

import java.util.Arrays;

/**
 * The NDVector class represents a vector with an arbitrary number of dimensions.
 * It implements the Vector interface and provides various vector operations such as
 * addition, subtraction, dot product, magnitude, normalization, and scaling.
 */
@SuppressWarnings("unused")
public class NDVector implements Vector {

    private final double[] elements;


    /**
     * Constructs a new NDVector with the specified elements.
     *
     * @param elements The elements of the vector.
     */
    public NDVector(double... elements) {
        if (elements == null) {
            this.elements = new double[0];
        } else {
            this.elements = elements;
        }
    }

    /**
     * Constructs a new NDVector with empty elements.
     *
     * @param size The size of the empty vector.
     */
    public NDVector(int size) {
        Validate.isGreaterThanOrEqualTo(size, 0, "A vector cannot be a negative size.");
        this.elements = new double[size];
    }


    /**
     * Retrieves the component of the vector at the specified index.
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
     * Retrieves all the elements of the vector.
     *
     * @return An array containing all the elements of the vector.
     */
    @Override
    public double[] getElements() {
        return Arrays.copyOf(elements, elements.length);
    }


    /**
     * Retrieves the dimension of the vector.
     *
     * @return The dimension of the vector.
     */
    @Override
    public int getDimension() {
        return elements.length;
    }


    /**
     * Calculates the dot product of this vector with another vector.
     *
     * @param vector The vector to calculate the dot product with.
     * @return The dot product of the two vectors.
     * @throws IllegalArgumentException If the provided vector is null or has a different dimension.
     */
    @Override
    public double dot(Vector vector) {
        Validate.notNull(vector, "You cannot take the dot product of a vector and a null vector.");
        Validate.isEquivalent(vector.getDimension(), this.getDimension(), "You cannot take the dot product of vectors that exist in different dimensions.");
        double result = 0;
        for (int i = 0; i < elements.length; i++) {
            result += elements[i] * vector.getComponent(i);
        }
        return result;
    }


    /**
     * Calculates the magnitude (length) of the vector.
     *
     * @return The magnitude of the vector.
     */
    @Override
    public double magnitude() {
        double resultSquared = 0;
        for (double element : elements) {
            resultSquared += element * element;
        }
        return Math.sqrt(resultSquared);
    }


    /**
     * Normalizes the vector to have a magnitude of 1.
     *
     * @return A new normalized vector.
     * @throws IllegalArgumentException If the vector has zero magnitude.
     */
    @Override
    public Vector normalize() {
        double magnitude = magnitude();
        Validate.isGreaterThan(magnitude, 0.0, "You cannot normalize the zero vector");

        double[] normalizedElements = new double[elements.length];
        for (int i = 0; i < elements.length; i++) {
            normalizedElements[i] = elements[i] / magnitude;
        }
        return new NDVector(normalizedElements);
    }


    /**
     * Adds another vector to this vector.
     *
     * @param vector The vector to add.
     * @return A new vector that is the result of the addition.
     * @throws IllegalArgumentException If the provided vector is null or has a different dimension.
     */
    @Override
    public NDVector add(Vector vector) {
        Validate.notNull(vector, "You cannot add a vector to a null vector.");
        Validate.isEquivalent(vector.getDimension(), this.getDimension(), "You cannot add vectors that exist in different dimensions.");
        double[] addedElements = new double[elements.length];

        for (int i = 0; i < elements.length; i++) {
            addedElements[i] = this.getComponent(i) + vector.getComponent(i);
        }
        return new NDVector(addedElements);
    }


    /**
     * Subtracts another vector from this vector.
     *
     * @param vector The vector to subtract.
     * @return A new vector that is the result of the subtraction.
     * @throws IllegalArgumentException If the provided vector is null or has a different dimension.
     */
    @Override
    public NDVector subtract(Vector vector) {
        Validate.notNull(vector, "You cannot subtract a null vector from a vector.");
        Validate.isEquivalent(vector.getDimension(), this.getDimension(), "You cannot subtract vectors that exist in different dimensions.");
        double[] subtractedElements = new double[elements.length];

        for (int i = 0; i < elements.length; i++) {
            subtractedElements[i] = this.getComponent(i) - vector.getComponent(i);
        }
        return new NDVector(subtractedElements);
    }


    /**
     * Scales the vector by a scalar factor.
     *
     * @param scalar The scalar factor to scale the vector by.
     * @return A new scaled vector.
     */
    @Override
    public Vector scale(double scalar) {
        double[] scaledElements = new double[elements.length];

        for (int i = 0; i < elements.length; i++) {
            scaledElements[i] = elements[i] * scalar;
        }
        return new NDVector(scaledElements);
    }

    /**
     * Sets the value of the specified component of this vector.
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
     * Compares this NDVector to another object for equality.
     *
     * @param o The object to compare with.
     * @return true if the other object is a NDVector with the same elements, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NDVector ndVector = (NDVector) o;
        return Arrays.equals(elements, ndVector.elements);
    }


    /**
     * Computes the hash code for the NDVector object.
     *
     * @return The hash code of the NDVector.
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(elements);
    }


    /**
     * Provides a string representation of the NDVector object.
     *
     * @return A string representation of the NDVector.
     */
    @Override
    public String toString() {
        return "NDVector{" +
                "elements=" + Arrays.toString(elements) +
                '}';
    }
}
