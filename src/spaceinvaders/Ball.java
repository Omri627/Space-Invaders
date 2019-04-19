package spaceinvaders;

import biuoop.DrawSurface;
import core.CollisionInfo;
import core.Sprite;
import core.Velocity;
import geometry.Line;
import geometry.Point;

import java.awt.Color;

/**
 * spaceinvaders.Ball class.
 *
 * @author Omri Sak
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private Color color;
    private Velocity velocity;
    private GameEnvironment environment;

    /**
     * spaceinvaders.Ball constructor.
     *
     * @param center center point
     * @param r      radius
     * @param color  color of the ball
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }

    /**
     * spaceinvaders.Ball constructor with coordinate.
     *
     * @param x     x coordinate of the center
     * @param y     y coordinate of the center
     * @param r     radius
     * @param color color of the ball
     */
    public Ball(double x, double y, int r, Color color) {
        //Point p = new Point(x, y);
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
    }

    /**
     * @return x coordinate of the radius
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * @return y coordinate of the radius
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * @return color of ball
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * draw a ball on DrawSurface.
     *
     * @param surface DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
        surface.setColor(Color.RED);
        surface.fillCircle(this.getX(), this.getY(), 1);
    }

    @Override
    public void timePassed(double dt) {
        moveOneStep(dt);
    }

    /**
     * set the velocity of the ball.
     *
     * @param v velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * set the velocity of the ball.
     *
     * @param dx difference in x axis
     * @param dy difference in y axis
     */
    public void setVelocity(double dx, double dy) {
        Velocity v = new Velocity(dx, dy);
        this.setVelocity(v);
    }

    /**
     * @return velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * change the position of the center point.
     * @param dt specifies the amount of seconds passed since the last call.
     */
    public void moveOneStep(double dt) {
        //set the velocity with dt
        Velocity dtVelocity = velocity.dtVelocity(dt);

        Line trajectory = new Line(this.center, dtVelocity.applyToPoint(this.center));
        CollisionInfo info = environment.getClosestCollision(trajectory);
        if (info == null) {
            this.center = dtVelocity.applyToPoint(this.center);
            return;
        } else {
            this.velocity = info.collisionObject().hit(this, info.collisionPoint(), this.velocity);
        }
    }

    /**
     * set environment so the ball will be aware to obstacles.
     *
     * @param gameEnvironment environment object
     */
    public void setEnvironment(GameEnvironment gameEnvironment) {
        this.environment = gameEnvironment;
    }

    /**
     * add the ball to sprite of the game.
     *
     * @param g spaceinvaders.GameLevel
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * remove a ball from the sprite collection.
     * @param gameLevel GameLevel
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}
