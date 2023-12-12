package org.cultro.roulette.util;

import org.cultro.roulette.geometry.d2.Location2D;
import org.cultro.roulette.geometry.d3.AABB;
import org.cultro.roulette.geometry.d3.Location3D;
import org.cultro.roulette.geometry.d3.Vector3D;
import org.cultro.roulette.geometry.dn.NDLocation;
import org.cultro.roulette.geometry.dn.NDVector;
import org.cultro.roulette.geometry.dn.Orthotope;
import org.cultro.roulette.lang.Validate;

@SuppressWarnings("unused")
public final class GeometryUtils {

    public static Location3D findIntersection(AABB boundingBox, Location3D origin, Vector3D direction) {
        double txMin = (boundingBox.getMin().getX() - origin.getX()) / direction.getX();
        double txMax = (boundingBox.getMax().getX() - origin.getX()) / direction.getX();

        double tyMin = (boundingBox.getMin().getY() - origin.getY()) / direction.getY();
        double tyMax = (boundingBox.getMax().getY() - origin.getY()) / direction.getY();

        double tzMin = (boundingBox.getMin().getZ() - origin.getZ()) / direction.getZ();
        double tzMax = (boundingBox.getMax().getZ() - origin.getZ()) / direction.getZ();

        double tMin = Math.max(Math.max(Math.min(txMin, txMax), Math.min(tyMin, tyMax)), Math.min(tzMin, tzMax));
        double tMax = Math.min(Math.min(Math.max(txMin, txMax), Math.max(tyMin, tyMax)), Math.max(tzMin, tzMax));

        if (tMin > tMax || tMax < 0) {
            return null;
        }

        double x = origin.getX() + tMin * direction.getX() ;
        double y = origin.getY() + tMin * direction.getY();
        double z = origin.getZ() + tMin * direction.getZ();

        return new Location3D(x, y, z);
    }

    public static NDLocation findIntersection(Orthotope orthotope, NDLocation origin, NDVector direction) {
        Validate.isGreaterThan(origin.getDimension(), 0, "Cannot calculate intersection in the 0th dimension.");
        Validate.isEquivalent(orthotope.getDimension(), origin.getDimension(), "You cannot calculate the intersection point of an orthotope if the ray origin exists in a different dimension from the orthotope.");
        Validate.isEquivalent(orthotope.getDimension(), direction.getDimension(), "You cannot calculate the intersection point of an orthotope if the ray direction vector exists in a different dimension from the orthotope.");
        Validate.isGreaterThan(direction.getDimension(), 0, "You cannot have the direction vector equal the zero vector.");

        int dimension = origin.getDimension();
        NDLocation orthotopeMin = orthotope.getMin();
        NDLocation orthotopeMax = orthotope.getMax();

        double tMax = Math.max((orthotopeMin.getComponent(0) - origin.getComponent(0)) / direction.getComponent(0),
                (orthotopeMax.getComponent(0) - origin.getComponent(0)) / direction.getComponent(0));
        double tMin = Math.min((orthotopeMin.getComponent(0) - origin.getComponent(0)) / direction.getComponent(0),
                (orthotopeMax.getComponent(0) - origin.getComponent(0)) / direction.getComponent(0));

        for (int i = 0; i < dimension; i++) {
            double first = (orthotopeMin.getComponent(i) - origin.getComponent(i)) / direction.getComponent(i);
            double second = (orthotopeMax.getComponent(i) - origin.getComponent(i)) / direction.getComponent(i);
            if (tMin < Math.max(first, second)) {
                tMin = Math.max(first, second);
            }
            if (tMax > Math.min(first, second)) {
                tMax = Math.min(first, second);
            }
        }

        if (tMin > tMax || tMax < 0) {
            return null;
        }

        NDLocation temp = new NDLocation(dimension);
        for (int i = 0; i < dimension; i++) {
            temp.setComponent(i, origin.getComponent(i) + tMin * direction.getComponent(i));
        }
        return temp; //todo check to see if this works
    }

    public static double distance(NDLocation first, NDLocation second) {
        Validate.notNull(first, "You cannot calculate distance with a null location.");
        Validate.notNull(second, "You cannot calculate distance with a null location.");
        Validate.isEquivalent(first.getDimension(), second.getDimension(), "You cannot calculate distance between locations that exist in different dimensions.");
        double result = 0;
        for (int i = 0; i < first.getDimension(); i++) {
            double di = first.getComponent(i) - second.getComponent(i);
            result += di * di;
        }
        return Math.sqrt(result);
    }

    public static double distanceSquared(NDLocation first, NDLocation second) {
        Validate.notNull(first, "You cannot calculate distance squared with a null location.");
        Validate.notNull(second, "You cannot calculate distance squared with a null location.");
        Validate.isEquivalent(first.getDimension(), second.getDimension(), "You cannot calculate distance squared between locations that exist in different dimensions.");
        double result = 0;
        for (int i = 0; i < first.getDimension(); i++) {
            double di = first.getComponent(i) - second.getComponent(i);
            result += di * di;
        }
        return result;
    }

    public static double distance(Location3D first, Location3D second) {
        Validate.notNull(first, "You cannot calculate distance with a null location.");
        Validate.notNull(second, "You cannot calculate distance with a null location.");
        double dx = first.getX() - second.getX();
        double dy = first.getY() - second.getY();
        double dz = first.getZ() - second.getZ();
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    public static double distanceSquared(Location3D first, Location3D second) {
        Validate.notNull(first, "You cannot calculate distance squared with a null location.");
        Validate.notNull(second, "You cannot calculate distance squared with a null location.");
        double dx = first.getX() - second.getX();
        double dy = first.getY() - second.getY();
        double dz = first.getZ() - second.getZ();
        return dx * dx + dy * dy + dz * dz;
    }

    public static double distance(double x1, double y1, double z1, double x2, double y2, double z2) {
        double dx = x1 - x2;
        double dy = y1 - y2;
        double dz = z1 - z2;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    public static double distanceSquared(double x1, double y1, double z1, double x2, double y2, double z2) {
        double dx = x1 - x2;
        double dy = y1 - y2;
        double dz = z1 - z2;
        return dx * dx + dy * dy + dz * dz;
    }

    public static double distance(Location2D first, Location2D second) {
        Validate.notNull(first, "You cannot calculate distance with a null location.");
        Validate.notNull(second, "You cannot calculate distance with a null location.");
        double dx = first.getX() - second.getX();
        double dy = first.getY() - second.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static double distanceSquared(Location2D first, Location2D second) {
        Validate.notNull(first, "You cannot calculate distance squared with a null location.");
        Validate.notNull(second, "You cannot calculate distance squared with a null location.");
        double dx = first.getX() - second.getX();
        double dy = first.getY() - second.getY();
        return dx * dx + dy * dy;
    }

    public static double distance(double x1, double y1, double x2, double y2) {
        double dx = x1 - x2;
        double dy = y1 - y2;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static double distanceSquared(double x1, double y1, double x2, double y2) {
        double dx = x1 - x2;
        double dy = y1 - y2;
        return dx * dx + dy * dy;
    }
}
