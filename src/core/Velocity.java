package core;

import geometry.Point;

/**
 * core.Velocity is class that represent speed and direction.
 * @author Omri
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * constructor.
     * @param dx difference in x axis
     * @param dy difference in y axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * change the direction of x.
     * @param newDx difference in x axis
     *
     */
    public void setDx(double newDx) {
        this.dx = newDx;
    }
    /**
     * change the direction of y.
     * @param newDy difference in y axis
     *
     */
    public void setDy(double newDy) {
        this.dy = newDy;
    }

    /**
     * @return dx
     */
    public double getDx() {
        return dx;
    }

    /**
     * @return dy
     */
    public double getDy() {
        return dy;
    }

    /**
     * @return speed of the ball, there is speed only if you create velocity from angle
     */
    public double getSpeed() {
       //return Math.abs(dx) + Math.abs(dy);
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     * @param p current location of the point
     * @return new position of the point
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * static method that create a velocity through angle and speed.
     * @param angle angle of movement
     * @param speed speed of position change
     * @return new core.Velocity object
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.ceil(speed * Math.cos(Math.toRadians(angle)));
        double dy = Math.ceil(speed * Math.sin(Math.toRadians(angle)));
        return new Velocity(dx, dy);
    }

    /**
     * create velocity with dt.
     * @param dt dt
     * @return velocity
     */
    public Velocity dtVelocity(double dt) {
        return new Velocity(dx * dt, dy * dt);
    }
}
