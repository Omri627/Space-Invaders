package spaceinvaders;

import biuoop.DrawSurface;
import core.Counter;
import core.Sprite;
import geometry.Rectangle;

import java.awt.Color;

/**
 * spaceinvaders.LivesIndicator which will be in charge of displaying the current lives.
 */
public class LivesIndicator implements Sprite {
    private Counter livesCounter;
    private Rectangle rectangle;
    private Color color;
    /**
     * Constructor.
     * @param livesCounter the lives to display
     * @param rectangle the size of the information bar
     * @param color color of the text
     */
    public LivesIndicator(Counter livesCounter, Rectangle rectangle, Color color) {
        this.livesCounter = livesCounter;
        this.rectangle = rectangle;
        this.color = color;
    }

    @Override
    public void drawOn(DrawSurface d) {
        //draw the text
        d.setColor(color);
        d.drawText((int) rectangle.getUpperLeft().getX() + (int) rectangle.getWidth() / 6,
                (int) rectangle.getUpperLeft().getY() + (int) rectangle.getHeight() / 2 + 5,
                "Lives: " + livesCounter.getValue(), 14);
    }

    @Override
    public void timePassed(double dt) {

    }

    /**
     * add the spaceinvaders.LivesIndicator to the sprites collection.
     * @param g GameLevel
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
