package spaceinvaders;

import biuoop.DrawSurface;
import core.Collidable;
import core.HitListener;
import core.HitNotifier;
import core.Sprite;
import core.Velocity;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * spaceinvaders.Block Class.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private Color color;
    private int hitPoint;
    private List<HitListener> hitListeners;
    private double dt;

    /**
     * constructor.
     *
     * @param rectangle that define the block.
     * @param color     color of the block
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
        this.hitPoint = 1;
        this.hitListeners = new ArrayList<HitListener>();
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //decrease hit point when hit
        hitPoint--;
        if (hitPoint < 0) {
            hitPoint = 0;
        }
        //notify the hit
        this.notifyHit(hitter);
        return currentVelocity;
    }

    /**
     * @return hit point of block.
     */
    public int getHitPoints() {
        return hitPoint;
    }

    /**
     * set the hit point of block.
     *
     * @param hit int
     */
    public void setHitPoint(int hit) {
        this.hitPoint = hit;
    }

    /**
     * @return the color of the block.
     */
    public Color getColor() {
        return color;
    }

    /**
     * @return the rectangle shape of the block.
     */
    public Rectangle getRectangle() {
        return rectangle;
    }

    /**
     * set rectangle of the block.
     *
     * @param newRectangle new rectangle
     */
    public void setRectangle(Rectangle newRectangle) {
        this.rectangle = newRectangle;
    }

    @Override
    public void drawOn(DrawSurface d) {

        d.setColor(this.color);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        //d.setColor(Color.BLACK);
       // d.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
               // (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }


    @Override
    public void timePassed(double dtt) {
        this.dt = dt;
    }

    /**
     * add the block to sprite and collidable collection of the game.
     *
     * @param g spaceinvaders.GameLevel
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * remove the block from sprite and collidable collection of the gameLevel.
     *
     * @param gameLevel spaceinvaders.GameLevel
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    /**
     * whenever a hit() occurs notifyHit notifiers all of the registered core.HitListener objects
     * by calling their hitEvent method.
     *
     * @param hitter the hitter ball
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
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

}
