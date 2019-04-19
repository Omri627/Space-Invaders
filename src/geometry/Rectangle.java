package geometry;

import java.util.ArrayList;
import java.util.List;


/**
 * geometry.Rectangle Class represent form width and height coming out from the upper left corner.
 * @author Omri Sak 205892615
 */
public class Rectangle {
    private Point upperLeft;
    private Point upperRight;
    private Point downLeft;
    private Point downRight;
    private double width;
    private double height;

    /**

     * geometry.Rectangle constructor.
     * @param upperLeft a point from which the width and height coming from.
     * @param width width of the rectangle
     * @param height height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.downLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        this.upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        this.downRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
    }

    /**
     * @return width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return  Returns the upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    /**
     * @return  Returns the upper-Right point of the rectangle
     */
    public Point getUpperRight() {
        return upperRight;
    }
    /**
     * @return  Returns the down-left point of the rectangle
     */
    public Point getDownLeft() {
        return downLeft;
    }
    /**
     * @return  Returns the down-right point of the rectangle
     */
    public Point getDownRight() {
        return downRight;
    }

    /**
     * set the upper left point.
     * @param point new upperLeft
     */
    public void setUpperLeft(Point point) {
        this.upperLeft = point;
    }

    /**
     * Return a (possibly empty) List of intersection points with the rectangle and other line.
     * @param line the line that intersect with the rectangle
     * @return list of intersect points
     */
    public List intersectionPoints(Line line) {
        List<Point> list = new ArrayList<>();
        // array of 4 sides of the rectangle
        Line[] sides = new Line[4];
        sides[0] = new Line(upperLeft, upperRight);
        sides[1] = new Line(upperRight, downRight);
        sides[2] = new Line(downRight, downLeft);
        sides[3] = new Line(downLeft, upperLeft);
        //check if each side intersect with the line
        for (Line side : sides) {
            Point intersectPoint = line.intersectionWith(side);
            if (intersectPoint != null) {
                list.add(intersectPoint);
            }
        }
        return list;
    }
}
