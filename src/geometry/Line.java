package geometry;

import java.util.List;

/**
 * geometry.Line class is form of two point.
 *
 * @author Omri Sak
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * geometry.Line constructor with points.
     *
     * @param start start point of the line
     * @param end   end point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;

    }

    /**
     * geometry.Line constructor with coordinate for start and end points.
     *
     * @param x1 x coordinate of start point
     * @param y1 y coordinate of start point
     * @param x2 x coordinate of end point
     * @param y2 x coordinate of end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        Point startPoint = new Point(x1, y1);
        Point endPoint = new Point(x2, y2);
        this.start = startPoint;
        this.end = endPoint;
    }

    /**
     * length of the line from start to end points.
     *
     * @return the length of the line
     */
    public double length() {
        return this.start.distanceTo(end);
    }

    /**
     * Returns the middle point of the line.
     *
     * @return middle point of the line
     */
    public Point middle() {
        double midX = (this.start.getX() + this.end.getX()) / 2;
        double midY = (this.start.getY() + this.end.getY()) / 2;
        return new Point(midX, midY);
    }

    /**
     * @return Returns the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return Returns the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * calculate the slop of the line.
     *
     * @param x1 x coordinate of start point
     * @param y1 y coordinate of start point
     * @param x2 x coordinate of end point
     * @param y2 x coordinate of end point
     * @return the slop or null if the is no slope
     */
    private Double calculateSlope(double x1, double y1, double x2, double y2) {
        double dy = y2 - y1;
        double dx = x2 - x1;

        if (dx == 0) {
            return null;
        }
        return dx / dy;

    }

    /**
     * intersection of two segment.
     *
     * @param other line
     * @return Returns the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        Point p1 = start();
        Point p2 = end();
        Point p3 = other.start();
        Point p4 = other.end();

        return intersectionWith(p1, p2, p3, p4);
    }

    /**
     * help method for intersection of two segment.
     *
     * @param p1 first point start
     * @param p2 first point end
     * @param p3 second point start
     * @param p4 second point end
     * @return Returns the intersection point if the lines intersect, and null otherwise.
     */
    private Point intersectionWith(Point p1, Point p2, Point p3, Point p4) {
        double denominator = (p4.getY() - p3.getY()) * (p2.getX() - p1.getX()) - (p4.getX() - p3.getX()) * (p2.getY()
                - p1.getY());

        if (denominator != 0.0D) {
            double da = ((p4.getX() - p3.getX()) * (p1.getY() - p3.getY()) - (p4.getY() - p3.getY()) * (p1.getX()
                    - p3.getX())) / denominator;


            if ((da <= 1.0D) && (da >= 0.0D)) {
                double db = ((p2.getX() - p1.getX()) * (p1.getY() - p3.getY()) - (p2.getY() - p1.getY()) * (p1.getX()
                        - p3.getX())) / denominator;

                if ((db <= 1.0D) && (db >= 0.0D)) {
                    int y = (int) (p1.getY() + da * (p2.getY() - p1.getY()));
                    int x = (int) (p1.getX() + da * (p2.getX() - p1.getX()));
                    return new Point(x, y);
                }
            }
        }

        return null;
    }

    /**
     * @param other line
     * @return Returns true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        Point p = intersectionWith(other);
        return p != null;
    }

    /**
     * @param other line to compare
     * @return true if line equals to other, if not return false
     */
    public boolean equals(Line other) {

        if (((this.start.equals(other.start)) && (this.end.equals(other.end)))) {
            return true;
        }
        if ((this.start.equals(other.end)) && (this.end.equals(other.start))) {
            return true;
        }
        return false;
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     *
     * @param rect rectangle from which we check the intersect with the line
     * @return the closet intersect point, null if there is no intersect points.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List list;
        list = rect.intersectionPoints(this);
        Point closestPoint;
        double closestDistance;
        if (list.isEmpty()) {
            return null;
        } else {
            //check for the closest point
            closestDistance = ((Point) list.get(0)).distanceTo(start);
            closestPoint = ((Point) list.get(0));
            for (int i = 1; i < list.size(); i++) {
                double d = ((Point) list.get(i)).distanceTo(start);
                if (d < closestDistance) {
                    closestDistance = d;
                    closestPoint = (Point) list.get(i);
                }
            }
            return closestPoint;
        }
    }
}
