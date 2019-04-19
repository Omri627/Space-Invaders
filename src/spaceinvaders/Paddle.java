package spaceinvaders;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import core.Collidable;
import core.HitListener;
import core.Sprite;
import core.HitNotifier;
import core.Velocity;
import geometry.Point;
import geometry.Rectangle;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The spaceinvaders.Paddle is the player in the game. It is a rectangle that is controlled by the arrow keys,
 * and moves according to the player key presses.
 */
public class Paddle implements Sprite, Collidable, HitNotifier<Paddle> {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private Color color;
    private Velocity velocity;
    private int rightBorder, leftBorder;
    private double dtt;
    private List<HitListener> hitListeners;

    /**
     * spaceinvaders.Paddle constructor.
     *
     * @param rectangle   the rectangle shape of the paddle
     * @param color       color of the paddle
     * @param velocity    velocity of the paddle
     * @param rightBorder right Border of the game area
     * @param leftBorder  left Border of the game area
     * @param keyboard    keyboard sensor
     */
    public Paddle(Rectangle rectangle, Color color, Velocity velocity, int rightBorder, int leftBorder,
                  biuoop.KeyboardSensor keyboard) {
        this.rectangle = rectangle;
        this.color = color;
        this.velocity = velocity;
        this.rightBorder = rightBorder - (int) rectangle.getWidth();
        ;
        this.leftBorder = leftBorder;
        this.keyboard = keyboard;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * move the paddle left.
     *
     * @param dt amount of seconds passed since the last call
     */
    public void moveLeft(double dt) {
        Point newPosition = new Point(this.getUpperLeftX() - velocity.getDx() * dt,
                this.rectangle.getUpperLeft().getY());
        //check if paddle hit the border
        if (newPosition.getX() < leftBorder) {
            newPosition.setX((double) leftBorder);
        }
        this.rectangle = new Rectangle(newPosition, rectangle.getWidth(), rectangle.getHeight());

    }

    /**
     * move the paddle right.
     *
     * @param dt amount of seconds passed since the last call
     */
    public void moveRight(double dt) {
        Point newPosition = new Point(this.getUpperLeftX() + velocity.getDx() * dt,
                this.rectangle.getUpperLeft().getY());
        //check if paddle hit the border
        if (newPosition.getX() > rightBorder) {
            newPosition.setX((double) rightBorder);
        }

        this.rectangle = new Rectangle(newPosition, rectangle.getWidth(), rectangle.getHeight());
    }

    /**
     * add the paddle to the game sprites and collidable collections.
     *
     * @param g spaceinvaders.GameLevel.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);


    }

    /**
     * get the upper left x coordinate.
     *
     * @return upper left x coordinate
     */
    public double getUpperLeftX() {
        return this.rectangle.getUpperLeft().getX();
    }

    /**
     * get the upper left y coordinate.
     * @return upper left y coordinate.
     */
    public double getUpperLeftY() {
        return this.rectangle.getUpperLeft().getY();
    }

    /**
     * set the velocity.
     *
     * @param newVelocity set new velocity
     */
    public void setVelocity(Velocity newVelocity) {
        this.velocity = newVelocity;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
        return currentVelocity;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    /**
     * notify the paddle if its need to move.
     *
     * @param dt amount of seconds passed since the last call
     */
    @Override
    public void timePassed(double dt) {
        this.dtt = dt;
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight(dt);
        } else if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft(dt);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * notify hit.
     * @param hitter hitter ball
     */
    private void notifyHit(Ball hitter) {
        for (HitListener listeners : hitListeners) {
            listeners.hitEvent(this, hitter);
        }
    }
}
