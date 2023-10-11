package org.cultro.roulette.geometry.dn;

import org.cultro.roulette.geometry.Vector;
import org.cultro.roulette.lang.Validate;

/**
 * The NDVector class represents a vector with an arbitrary number of dimensions.
 * It implements the Vector interface and provides various vector operations such as
 * addition, subtraction, dot product, magnitude, normalization, and scaling.
 */
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
        return elements;
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

    public void setComponent(int component, double value) {
        elements[component] = value; //todo make sure that this checks if in bound with validates maybe?
    }
}
