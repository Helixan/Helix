package org.cultro.roulette.geometry;

/**
 * The Vector interface represents a mathematical vector in n-dimensional space.
 * Implementing classes should provide methods for retrieving vector components,
 * calculating dot products, magnitudes, and performing vector operations.
 */
public interface Vector {

    /**
     * Retrieves the component of the vector at the specified index.
     *
     * @param index The index of the vector component.
     * @return The value of the vector component at the specified index.
     */

    double getComponent(int index);


    /**
     * Retrieves all the elements representing the vector's components.
     *
     * @return An array containing the vector's components.
     */
    double[] getElements();


    /**
     * Retrieves the dimension of the vector.
     *
     * @return The dimension of the vector.
     */
    int getDimension();


    /**
     * Calculates the dot product of this vector with another vector.
     *
     * @param vector The vector to calculate the dot product with.
     * @return The dot product of the two vectors.
     */
    double dot(Vector vector);


    /**
     * Calculates the magnitude (length) of the vector.
     *
     * @return The magnitude of the vector.
     */
    double magnitude();


    /**
     * Normalizes the vector to have a magnitude of 1.
     *
     * @return A new normalized vector.
     */
    Vector normalize();


    /**
     * Adds another vector to this vector.
     *
     * @param vector The vector to add.
     * @return A new vector that is the result of the addition.
     */
    Vector add(Vector vector);


    /**
     * Subtracts another vector from this vector.
     *
     * @param vector The vector to subtract.
     * @return A new vector that is the result of the subtraction.
     */
    Vector subtract(Vector vector);


    /**
     * Scales the vector by a scalar factor.
     *
     * @param scalar The scalar factor to scale the vector by.
     * @return A new scaled vector.
     */
    Vector scale(double scalar);
}