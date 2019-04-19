package spaceinvaders;

import biuoop.DrawSurface;
import core.Velocity;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;
import java.awt.Image;

/**
 * invader - the enemy in the game.
 */
public class Invader extends Block {
    private int startX;
    private int startY;
    private Image image;
    private Velocity velocity;
    private int rightBorder, leftBorder;
    private boolean toMoveRight;
    private boolean needPositionChange;
    private static final int WIDTH = 40;
    private static final int HEIGHT = 30;
    private int id;

    /**
     * Constructor for invader.
     *
     * @param startX      start top x of the invader
     * @param startY      start top y of the invader
     * @param velocity    velocity of the invader
     * @param image       the image of the invader
     * @param rightBorder right Border of the game area
     * @param leftBorder  left Border of the game area
     * @param id          id for the invader
     */
    public Invader(int id, int startX, int startY, Velocity velocity, Image image, int rightBorder, int leftBorder) {

        super(new Rectangle(new Point(startX, startY), WIDTH, HEIGHT), Color.GRAY);
        this.id = id;
        this.startX = startX;
        this.startY = startY;
        this.image = image;
        this.velocity = velocity;
        this.rightBorder = rightBorder;
        this.leftBorder = leftBorder;
        this.toMoveRight = true;
        this.needPositionChange = false;
    }

    @Override
    public void drawOn(DrawSurface d) {
        if (image != null) {
            d.drawImage((int) getRectangle().getUpperLeft().getX(), (int) getRectangle().getUpperLeft().getY(), image);
        } else {
            super.drawOn(d);
        }
    }

    /**
     * move the invader left.
     *
     * @param dt amount of seconds passed since the last call
     */
    public void moveLeft(double dt) {
        Point newPosition = new Point(getRectangle().getUpperLeft().getX() - velocity.getDx() * dt,
                getRectangle().getUpperLeft().getY());
        //check if invader hit the border
        if (newPosition.getX() < leftBorder) {
            newPosition.setX((double) leftBorder);
            //indicate that position change is required
            needPositionChange = true;
        }
        //set the location of the new rectangle
        setRectangle(new Rectangle(newPosition, getRectangle().getWidth(), getRectangle().getHeight()));

    }

    /**
     * move the invader right.
     *
     * @param dt amount of seconds passed since the last call
     */
    public void moveRight(double dt) {
        Point newPosition = new Point(getRectangle().getUpperLeft().getX() + velocity.getDx() * dt,
                getRectangle().getUpperLeft().getY());
        //check if paddle hit the border
        if (newPosition.getX() + 40 > rightBorder) {
            newPosition.setX((double) rightBorder - 40);
            //indicate that position change is required
            needPositionChange = true;
        }

        setRectangle(new Rectangle(newPosition, getRectangle().getWidth(), getRectangle().getHeight()));
    }

    @Override
    public void timePassed(double dtt) {
        if (toMoveRight) {
            moveRight(dtt);
        } else {
            moveLeft(dtt);
        }
    }

    /**
     * set the velocity of the alien.
     *
     * @param newVelocity new velocity
     */
    public void setVelocity(Velocity newVelocity) {
        this.velocity = newVelocity;
    }

    /**
     * shot one bullet.
     */
    public void shot() {
    }

    /**
     * get for start x.
     *
     * @return start x
     */
    public int getStartX() {
        return startX;
    }

    /**
     * setter for x starting.
     *
     * @param x start x
     */
    public void setStartX(int x) {
        Point newPoint = new Point(x, startY);
        setRectangle(new Rectangle(newPoint, WIDTH, HEIGHT));
        this.startX = x;
    }

    /**
     * getter for start y.
     *
     * @return start y
     */
    public int getStartY() {
        return startY;
    }

    /**
     * setter for start y, and updating the rectangle.
     *
     * @param y start y
     */
    public void setStartY(int y) {
        Point newPoint = new Point(getRectangle().getUpperLeft().getX(), y);
        setRectangle(new Rectangle(newPoint, WIDTH, HEIGHT));
        this.startY = y;
    }

    /**
     * get current velocity.
     *
     * @return current velocity
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * set true if the invader need to move right, false to move left.
     * @param toMoveRightSide boolean
     */
    public void setToMoveRight(boolean toMoveRightSide) {
        this.toMoveRight = toMoveRightSide;
    }

    /**
     * getter for if the invader needs to move right.
     * @return true if moving right, false if left
     */
    public boolean isToMoveRight() {
        return toMoveRight;
    }

    /**
     * check if the invader need position change.
     * @return true if needed position change, false if not.
     */
    public boolean isNeedPositionChange() {
        return needPositionChange;
    }

    /**
     * setter for needPositionChange .
     * @param positionChange true or false
     */
    public void setNeedPositionChange(boolean positionChange) {
        this.needPositionChange = positionChange;
    }

    /**
     * return the id.
     * @return id
     */
    public int getId() {
        return id;
    }
}
