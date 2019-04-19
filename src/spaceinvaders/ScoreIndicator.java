package spaceinvaders;

import biuoop.DrawSurface;
import core.Counter;
import core.Sprite;
import geometry.Rectangle;

import java.awt.Color;

/**
 * spaceinvaders.ScoreIndicator which will be in charge of displaying the current score.
 */
public class ScoreIndicator implements Sprite {
    private Counter scoreCounter;
    private Rectangle rectangle;
    private Color color;

    /**
     * Constructor.
     * @param scoreCounter the score to display
     * @param rectangle the size of the information bar
     * @param color color of the text
     */
    public ScoreIndicator(Counter scoreCounter, Rectangle rectangle, Color color) {
        this.scoreCounter = scoreCounter;
        this.rectangle = rectangle;
        this.color = color;
    }

    @Override
    public void drawOn(DrawSurface d) {
        //draw the text
        d.setColor(color);
        d.drawText((int) rectangle.getUpperLeft().getX() + (int) rectangle.getWidth() / 2 - 80,
                (int) rectangle.getUpperLeft().getY() + (int) rectangle.getHeight() / 2 + 5,
                "Score: " + scoreCounter.getValue(), 14);
    }

    @Override
    public void timePassed(double dt) {

    }

    /**
     * add the ScoreIndicator to the sprites collection.
     * @param g gameLevel
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
