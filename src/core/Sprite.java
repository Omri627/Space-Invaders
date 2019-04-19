package core;

import biuoop.DrawSurface;

/**
 * Sprites can be drawn on the screen, and can be notified that time has passed
 * (so that they know to change their position / shape / appearance / etc).
 * @author Omri
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     * @param d DrawSurface to draw on
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     * @param dt specifies the amount of seconds passed since the last call
     */
    void timePassed(double dt);

}
