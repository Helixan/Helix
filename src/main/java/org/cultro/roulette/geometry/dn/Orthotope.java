package org.cultro.roulette.geometry.dn;

import org.cultro.roulette.lang.Validate;

/**
 * The Orthotope class represents a geometric object in n-dimensional space, defined by its minimum and maximum corners
 * represented as NDLocation objects.
 * An Orthotope is similar to an axis-aligned bounding box (AABB).
 */
public class Orthotope {

    private final NDLocation max;
    private final NDLocation min;


    /**
     * Constructs an Orthotope using two NDLocation objects representing the minimum and maximum corners.
     *
     * @param first The first NDLocation object.
     * @param second The second NDLocation object.
     * @throws IllegalArgumentException If either NDLocation is null or if they have different dimensions.
     */
    public Orthotope(NDLocation first, NDLocation second) {
        Validate.notNull(first, "An orthotope cannot be created from a null location.");
        Validate.notNull(second, "An orthotope cannot be created from a null location.");
        Validate.isEquivalent(first.getDimension(), second.getDimension(), "An orthotope cannot be created from two locations in different dimensions.");

        double[] max = new double[first.getDimension()];
        double[] min = new double[second.getDimension()];

        for (int i = 0; i < first.getDimension(); i++) {
            double firstComponent = first.getComponent(i);
            double secondComponent = second.getComponent(i);
            if (firstComponent > secondComponent) {
                max[i] = firstComponent;
                min[i] = secondComponent;
            } else {
                max[i] = secondComponent;
                min[i] = firstComponent;
            }
        }
        this.max = new NDLocation(max);
        this.min = new NDLocation(min);
    }


    /**
     * Gets the NDLocation representing the maximum corner of the Orthotope.
     *
     * @return The NDLocation of the maximum corner.
     */
    public NDLocation getMax() {
        return max;
    }


    /**
     * Gets the elements of the maximum NDLocation as an array.
     *
     * @return An array of elements representing the maximum corner.
     */
    public double[] getMaxElements() {
        return max.getElements();
    }


    /**
     * Gets the NDLocation representing the minimum corner of the Orthotope.
     *
     * @return The NDLocation of the minimum corner.
     */
    public NDLocation getMin() {
        return min;
    }


    /**
     * Gets the elements of the minimum NDLocation as an array.
     *
     * @return An array of elements representing the minimum corner.
     */
    public double[] getMinElements() {
        return min.getElements();
    }
}
