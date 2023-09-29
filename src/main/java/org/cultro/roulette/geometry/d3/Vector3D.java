package org.cultro.roulette.geometry.d3;

import org.cultro.roulette.geometry.Vector;
import org.cultro.roulette.lang.Validate;

/**
 * The Vector3D class represents a three-dimensional vector in Euclidean space.
 * It implements the Vector interface and provides various vector operations
 * such as addition, subtraction, dot product, magnitude, normalization, and scaling.
 * <p>
 * This class is specifically designed for 3D vectors and is suitable for use in
 * three-dimensional geometric calculations and graphics applications.
 */
public class Vector3D implements Vector {

    private final double[] elements = new double[3];


    /**
     * Constructs a new Vector3D with the specified components.
     *
     * @param x The x-component of the vector.
     * @param y The y-component of the vector.
     * @param z The z-component of the vector.
     */
    public Vector3D(double x, double y, double z) {
        elements[0] = x;
        elements[1] = y;
        elements[2] = z;
    }


    /**
     * Gets the x-component of the 3D vector.
     *
     * @return The x-component of the vector.
     */
    public double getX() {
        return elements[0];
    }


    /**
     * Gets the y-component of the 3D vector.
     *
     * @return The y-component of the vector.
     */
    public double getY() {
        return elements[1];
    }


    /**
     * Gets the z-component of the 3D vector.
     *
     * @return The z-component of the vector.
     */
    public double getZ() {
        return elements[2];
    }


    /**
     * Retrieves the component of the vector at the specified index.
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
     * Retrieves all the elements of the 3D vector.
     *
     * @return An array containing the x, y, and z components of the vector.
     */
    @Override
    public double[] getElements() {
        return elements;
    }


    /**
     * Retrieves the dimension of the 3D vector, which is always 3.
     *
     * @return The dimension of the vector, which is 3.
     */
    @Override
    public int getDimension() {
        return 3;
    }


    /**
     * Calculates the dot product of this 3D vector with another vector.
     *
     * @param vector The vector to calculate the dot product with.
     * @return The dot product of the two vectors.
     * @throws IllegalArgumentException If the provided vector is null or has a different dimension.
     */
    @Override
    public double dot(Vector vector) {
        Validate.notNull(vector, "You cannot take the dot product of a vector and a null vector.");
        Validate.isEquivalent(vector.getDimension(), this.getDimension(), "You cannot take the dot product of vectors that exist in different dimensions.");
        double result = 0.0;
        for (int i = 0; i < 3; i++) {
            result += elements[i] * vector.getComponent(i);
        }
        return result;
    }


    /**
     * Calculates the magnitude (length) of the 3D vector.
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
     * Normalizes the 3D vector to have a magnitude of 1.
     *
     * @return A new normalized vector.
     * @throws IllegalArgumentException If the vector has zero magnitude.
     */
    @Override
    public Vector3D normalize() {
        double magnitude = magnitude();
        Validate.isGreaterThan(magnitude, 0.0, "You cannot normalize the zero vector");
        return new Vector3D(elements[0] / magnitude, elements[1] / magnitude, elements[2] / magnitude);
    }


    /**
     * Calculate the cross product of this vector with another vector.
     *
     * @param vector The other vector to calculate the cross product with.
     * @return A new Vector3D representing the cross product of the two vectors.
     * @throws IllegalArgumentException If the provided vector is null or has a different dimension.
     */
    public Vector3D cross(Vector vector) {
        Validate.notNull(vector, "Cross product cannot be calculated with a null vector.");
        Validate.isEquivalent(vector.getDimension(), 3, "Cross product is defined only for 3D vectors.");

        double x = elements[1] * vector.getComponent(2) - elements[2] * vector.getComponent(1);
        double y = elements[2] * vector.getComponent(0) - elements[0] * vector.getComponent(2);
        double z = elements[0] * vector.getComponent(1) - elements[1] * vector.getComponent(0);

        return new Vector3D(x, y, z);
    }


    /**
     * Adds another vector to this 3D vector.
     *
     * @param vector The vector to add.
     * @return A new vector that is the result of the addition.
     * @throws IllegalArgumentException If the provided vector is null or has a different dimension.
     */
    @Override
    public Vector3D add(Vector vector) {
        Validate.notNull(vector, "You cannot add a vector to a null vector.");
        Validate.isEquivalent(vector.getDimension(), 3, "You cannot add vectors that exist in different dimensions.");
        return new Vector3D(elements[0] + vector.getComponent(0), elements[1] + vector.getComponent(1), elements[2] + vector.getComponent(2));
    }


    /**
     * Subtracts another vector from this 3D vector.
     *
     * @param vector The vector to subtract.
     * @return A new vector that is the result of the subtraction.
     * @throws IllegalArgumentException If the provided vector is null or has a different dimension.
     */
    @Override
    public Vector3D subtract(Vector vector) {
        Validate.notNull(vector, "You cannot subtract a null vector from a vector.");
        Validate.isEquivalent(vector.getDimension(), 3, "You cannot subtract vectors that exist in different dimensions.");
        return new Vector3D(elements[0] - vector.getComponent(0), elements[1] - vector.getComponent(1), elements[2] - vector.getComponent(2));
    }


    /**
     * Scales the 3D vector by a scalar factor.
     *
     * @param scalar The scalar factor to scale the vector by.
     * @return A new scaled vector.
     */
    @Override
    public Vector3D scale(double scalar) {
        return new Vector3D(elements[0] * scalar, elements[1] * scalar, elements[2] * scalar);
    }
}