package org.cultro.roulette.geometry.d2;

import org.cultro.roulette.geometry.Vector;
import org.cultro.roulette.lang.Validate;

/**
 * The Vector2D class represents a two-dimensional vector with components (x, y).
 * It implements the Vector interface and provides common vector operations such as dot product, magnitude, normalization,
 * addition, subtraction, and scaling.
 */
@SuppressWarnings("unused")
public class Vector2D implements Vector {

    private final double[] elements = new double[3];


    /**
     * Constructs a two-dimensional vector with the specified x and y components.
     *
     * @param x The x component of the vector.
     * @param y The y component of the vector.
     */
    public Vector2D(double x, double y) {
        elements[0] = x;
        elements[1] = y;
    }


    /**
     * Get the x component of the vector.
     *
     * @return The x component of the vector.
     */
    public double getX() {
        return elements[0];
    }


    /**
     * Get the y component of the vector.
     *
     * @return The y component of the vector.
     */
    public double getY() {
        return elements[1];
    }


    /**
     * Get a specific component of the vector by index.
     *
     * @param index The index of the component to retrieve (0 for x, 1 for y).
     * @return The component at the specified index or 0 if the index is out of bounds.
     */
    @Override
    public double getComponent(int index) {
        if (index >= elements.length) {
            return 0;
        }
        return elements[index];
    }


    /**
     * Get an array containing the vector's components [x, y].
     *
     * @return An array containing the vector's components.
     */
    @Override
    public double[] getElements() {
        return elements;
    }


    /**
     * Get the dimension of the vector, which is 2 for a two-dimensional vector.
     *
     * @return The dimension of the vector.
     */
    @Override
    public int getDimension() {
        return 2;
    }


    /**
     * Calculate the dot product of this vector with another vector.
     *
     * @param vector The other vector to calculate the dot product with.
     * @return The dot product of the two vectors.
     * @throws IllegalArgumentException If the provided vector is null or has a different dimension.
     */
    @Override
    public double dot(Vector vector) {
        Validate.notNull(vector, "You cannot take the dot product of a vector and a null vector.");
        Validate.isEquivalent(vector.getDimension(), this.getDimension(), "You cannot take the dot product of vectors that exist in different dimensions.");
        double result = 0.0;
        for (int i = 0; i < 2; i++) {
            result += elements[i] * vector.getComponent(i);
        }
        return result;
    }


    /**
     * Calculate the magnitude (length) of the vector.
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
     * Normalize the vector to have a magnitude of 1.
     *
     * @return A new Vector2D representing the normalized vector.
     * @throws IllegalArgumentException If the vector has zero magnitude.
     */
    @Override
    public Vector2D normalize() {
        double magnitude = magnitude();
        Validate.isGreaterThan(magnitude, 0.0, "You cannot normalize the zero vector");
        return new Vector2D(elements[0] / magnitude, elements[1] / magnitude);
    }


    /**
     * Add another vector to this vector.
     *
     * @param vector The vector to be added.
     * @return A new Vector2D representing the result of the addition.
     * @throws IllegalArgumentException If the provided vector is null or has a different dimension.
     */
    @Override
    public Vector2D add(Vector vector) {
        Validate.notNull(vector, "You cannot add a vector to a null vector.");
        Validate.isEquivalent(vector.getDimension(), 2, "You cannot add vectors that exist in different dimensions.");
        return new Vector2D(elements[0] + vector.getComponent(0), elements[1] + vector.getComponent(1));
    }


    /**
     * Subtract another vector from this vector.
     *
     * @param vector The vector to be subtracted.
     * @return A new Vector2D representing the result of the subtraction.
     * @throws IllegalArgumentException If the provided vector is null or has a different dimension.
     */
    @Override
    public Vector2D subtract(Vector vector) {
        Validate.notNull(vector, "You cannot subtract a null vector from a vector.");
        Validate.isEquivalent(vector.getDimension(), 2, "You cannot subtract vectors that exist in different dimensions.");
        return new Vector2D(elements[0] - vector.getComponent(0), elements[1] - vector.getComponent(1));
    }


    /**
     * Scale this vector by a scalar factor.
     *
     * @param scalar The scalar factor by which to scale the vector.
     * @return A new Vector2D representing the scaled vector.
     */
    @Override
    public Vector2D scale(double scalar) {
        return new Vector2D(elements[0] * scalar, elements[1] * scalar);
    }

    /**
     * Sets the value of the specified component of this vector.
     *
     * @param component The index of the component to be set.
     * @param value The value to be assigned to the specified component.
     * @throws IllegalArgumentException If the provided component is out of bounds.
     */
    @Override
    public void setComponent(int component, double value) {
        Validate.isValidIndex(elements, component, "The targeted component is out of bounds.");
        elements[component] = value;
    }
}

