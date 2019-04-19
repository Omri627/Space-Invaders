package geometry;

/**
 * A geometry.Point class.
 *
 * @author Omri Sak
 */
public class Point {
    private double x;
    private double y;

    /**
     * Construct a point givne x and y coordinates.
     *
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x coordinate
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the y coordinate
     */
    public double getY() {
        return this.y;
    }

    /**
     * set new x coordinate.
     * @param nx new x
     */
    public void setX(double nx) {
        this.x = nx;
    }

    /**
     * set new y coordinate.
     * @param ny new y
     */
    public void setY(double ny) {
        this.y = ny;
    }

    /**
     * @param other point to compare
     * @return true if equals, false if not
     */
    public boolean equals(Point other) {
        return ((this.x == other.getX()) && (this.y == other.getY()));
    }

    /**
     * @param otherPoint a point to measure the distance to
     * @return the distance to the other point
     */
    public double distanceTo(Point otherPoint) {
        double dx = this.x - otherPoint.getX();
        double dy = this.y - otherPoint.getY();
        return Math.sqrt((dx * dx) + (dy * dy));
    }
}
