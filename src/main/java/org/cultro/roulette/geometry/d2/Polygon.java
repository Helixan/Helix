package org.cultro.roulette.geometry.d2;

import org.cultro.roulette.lang.Validate;

import java.util.*;

/**
 * The Polygon class represents a polygon in a 2D Euclidean space.
 * It is defined by an ordered list of vertices (Location2D objects).
 * The class provides methods to calculate the polygon's area, perimeter,
 * and allows iteration over all integer points within or on the boundary of the polygon.
 */
@SuppressWarnings("unused")
public class Polygon implements Iterable<Location2D> {

    private final List<Location2D> vertices;

    /**
     * Constructs a new {@code Polygon} with the specified list of vertices.
     *
     * <p>This constructor creates a polygon defined by an ordered {@code List} of {@code Location2D} vertices.
     * The order of the vertices should follow either a clockwise or counter-clockwise direction to
     * correctly define the polygon's shape. The polygon must have at least three vertices, and none
     * of the vertices can be {@code null}.
     *
     * @param vertices the {@code List} of vertices defining the polygon, provided as {@code Location2D} objects
     * @throws IllegalArgumentException if {@code vertices} is {@code null},
     *                                  contains fewer than three vertices,
     *                                  or if any vertex in the list is {@code null}
     */
    public Polygon(List<Location2D> vertices) {
        Validate.notNull(vertices, "Vertices list cannot be null.");
        Validate.isGreaterThanOrEqualTo(vertices.size(), 3, "A polygon must have at least 3 vertices.");

        for (Location2D vertex : vertices) {
            Validate.notNull(vertex, "Vertices list cannot contain null elements.");
        }
        this.vertices = new ArrayList<>(vertices);
    }


    /**
     * Constructs a new {@code Polygon} with the specified vertices.
     *
     * <p>This constructor creates a polygon defined by a variable number of {@code Location2D} vertices.
     * The order of the vertices should follow either a clockwise or counter-clockwise direction to
     * correctly define the polygon's shape. The polygon must have at least three vertices, and none
     * of the vertices can be {@code null}.
     *
     * @param vertices the vertices defining the polygon, provided as a variable number of {@code Location2D} objects
     * @throws IllegalArgumentException if {@code vertices} is {@code null},
     *                                  contains fewer than three vertices,
     *                                  or if any vertex in the array is {@code null}
     */
    public Polygon(Location2D... vertices) {
        Validate.notNull(vertices, "Vertices cannot be null.");
        Validate.isGreaterThanOrEqualTo(vertices.length, 3, "A polygon must have at least 3 vertices.");

        List<Location2D> temp = new ArrayList<>(vertices.length);
        for (Location2D vertex : vertices) {
            Validate.notNull(vertex, "Vertices list cannot contain null elements.");
            temp.add(vertex);
        }
        this.vertices = temp;
    }

    /**
     * Retrieves the list of vertices defining the polygon.
     *
     * @return An unmodifiable list of the polygon's vertices.
     */
    public List<Location2D> getVertices() {
        return new ArrayList<>(vertices);
    }

    /**
     * Calculates the area of the polygon using the Shoelace formula.
     *
     * @return The area of the polygon.
     */
    public double getArea() {
        double area = 0.0;
        int n = vertices.size();
        for (int i = 0; i < n; i++) {
            Location2D current = vertices.get(i);
            Location2D next = vertices.get((i + 1) % n);
            area += (current.getX() * next.getY()) - (next.getX() * current.getY());
        }
        return Math.abs(area) / 2.0;
    }

    /**
     * Calculates the perimeter of the polygon by summing the distances between consecutive vertices.
     *
     * @return The perimeter of the polygon.
     */
    public double getPerimeter() {
        double perimeter = 0.0;
        int n = vertices.size();
        for (int i = 0; i < n; i++) {
            Location2D current = vertices.get(i);
            Location2D next = vertices.get((i + 1) % n);
            perimeter += distance(current, next);
        }
        return perimeter;
    }

    /**
     * Computes the Euclidean distance between two Location2D points.
     *
     * @param p1 The first point.
     * @param p2 The second point.
     * @return The Euclidean distance between p1 and p2.
     */
    private double distance(Location2D p1, Location2D p2) {
        double dx = p1.getX() - p2.getX();
        double dy = p1.getY() - p2.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Returns an iterator over the integer points within or on the boundary of the polygon.
     *
     * @return An Iterator for Location2D objects inside or on the polygon.
     */
    @Override
    public Iterator<Location2D> iterator() {
        return new PolygonIterator();
    }

    /**
     * Compares this Polygon to another object for equality.
     *
     * @param o The object to compare with.
     * @return true if the other object is a Polygon with the same vertices in the same order, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Polygon polygon = (Polygon) o;
        return vertices.equals(polygon.vertices);
    }

    /**
     * Computes the hash code for the Polygon object.
     *
     * @return The hash code of the Polygon.
     */
    @Override
    public int hashCode() {
        return Objects.hash(vertices);
    }

    /**
     * Provides a string representation of the Polygon object.
     *
     * @return A string representation of the Polygon.
     */
    @Override
    public String toString() {
        return "Polygon{" +
                "vertices=" + vertices +
                '}';
    }

    /**
     * An iterator over Location2D objects that represent all integer points within or on the boundary of the polygon.
     * It iterates through the points within the polygon's bounding box, checking each point for inclusion.
     */
    private class PolygonIterator implements Iterator<Location2D> {
        private int currentX;
        private int currentY;
        private final int minX;
        private final int minY;
        private final int maxX;
        private final int maxY;
        private Location2D nextLocation;
        private boolean hasNext;

        /**
         * Constructs a new PolygonIterator. Initializes the iteration bounds based on the polygon's bounding box.
         */
        public PolygonIterator() {
            minX = (int) Math.floor(getMinX());
            minY = (int) Math.floor(getMinY());
            maxX = (int) Math.ceil(getMaxX());
            maxY = (int) Math.ceil(getMaxY());

            currentX = minX;
            currentY = minY;
            advanceToNextValid();
        }

        /**
         * Retrieves the minimum X coordinate among the polygon's vertices.
         *
         * @return The minimum X coordinate.
         */
        private double getMinX() {
            return vertices.stream().mapToDouble(Location2D::getX).min().orElse(0.0);
        }

        /**
         * Retrieves the minimum Y coordinate among the polygon's vertices.
         *
         * @return The minimum Y coordinate.
         */
        private double getMinY() {
            return vertices.stream().mapToDouble(Location2D::getY).min().orElse(0.0);
        }

        /**
         * Retrieves the maximum X coordinate among the polygon's vertices.
         *
         * @return The maximum X coordinate.
         */
        private double getMaxX() {
            return vertices.stream().mapToDouble(Location2D::getX).max().orElse(0.0);
        }

        /**
         * Retrieves the maximum Y coordinate among the polygon's vertices.
         *
         * @return The maximum Y coordinate.
         */
        private double getMaxY() {
            return vertices.stream().mapToDouble(Location2D::getY).max().orElse(0.0);
        }

        /**
         * Checks if there is another integer point within or on the polygon.
         *
         * @return true if there is another point, false otherwise.
         */
        @Override
        public boolean hasNext() {
            return hasNext;
        }

        /**
         * Returns the next integer point within or on the polygon.
         *
         * @return The next Location2D object inside or on the polygon.
         * @throws NoSuchElementException if there are no more points to iterate over.
         */
        @Override
        public Location2D next() {
            if (!hasNext) {
                throw new NoSuchElementException();
            }

            Location2D result = nextLocation;
            advance();

            return result;
        }

        /**
         * Advances the iterator to the next valid point within or on the polygon.
         */
        private void advance() {
            currentY++;
            advanceToNextValid();
        }

        /**
         * Advances the iterator to the next valid point, setting hasNext and nextLocation accordingly.
         */
        private void advanceToNextValid() {
            nextLocation = null;
            hasNext = false;

            while (currentX <= maxX) {
                while (currentY <= maxY) {
                    Location2D point = new Location2D(currentX, currentY);
                    if (isPointInside(point)) {
                        nextLocation = point;
                        hasNext = true;
                        return;
                    }
                    currentY++;
                }
                currentY = minY;
                currentX++;
            }
        }

        /**
         * Determines whether a given point is inside or on the boundary of the polygon using the Ray Casting algorithm.
         *
         * @param point The point to test.
         * @return true if the point is inside or on the boundary of the polygon, false otherwise.
         */
        private boolean isPointInside(Location2D point) {
            int intersections = 0;
            int n = vertices.size();
            double x = point.getX();
            double y = point.getY();

            for (int i = 0; i < n; i++) {
                Location2D v1 = vertices.get(i);
                Location2D v2 = vertices.get((i + 1) % n);

                double x1 = v1.getX();
                double y1 = v1.getY();
                double x2 = v2.getX();
                double y2 = v2.getY();

                if ((x == x1 && y == y1) || (x == x2 && y == y2)) {
                    return true;
                }

                if (isPointOnLineSegment(point, v1, v2)) {
                    return true;
                }

                if (((y1 > y) != (y2 > y))) {
                    double slope = (x2 - x1) / (y2 - y1);
                    double intersectX = slope * (y - y1) + x1;
                    if (intersectX == x) {
                        return true;
                    }
                    if (intersectX > x) {
                        intersections++;
                    }
                }
            }

            return (intersections % 2) != 0;
        }

        /**
         * Determines whether a given point lies exactly on the line segment defined by two vertices.
         *
         * @param p  The point to test.
         * @param v1 The first vertex of the line segment.
         * @param v2 The second vertex of the line segment.
         * @return true if the point lies on the line segment, false otherwise.
         */
        private boolean isPointOnLineSegment(Location2D p, Location2D v1, Location2D v2) {
            double minX = Math.min(v1.getX(), v2.getX());
            double maxX = Math.max(v1.getX(), v2.getX());
            double minY = Math.min(v1.getY(), v2.getY());
            double maxY = Math.max(v1.getY(), v2.getY());

            if (p.getX() < minX || p.getX() > maxX || p.getY() < minY || p.getY() > maxY) {
                return false;
            }

            double dx = v2.getX() - v1.getX();
            double dy = v2.getY() - v1.getY();
            double dxp = p.getX() - v1.getX();
            double dyp = p.getY() - v1.getY();

            if (dx == 0) {
                return dxp == 0;
            }
            if (dy == 0) {
                return dyp == 0;
            }

            double slope1 = dy / dx;
            double slope2 = dyp / dxp;
            return Double.compare(slope1, slope2) == 0;
        }
    }
}
