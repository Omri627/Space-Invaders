package spaceinvaders;

import biuoop.DrawSurface;
import core.Sprite;
import geometry.Rectangle;

import java.awt.Color;

/**
 * spaceinvaders.LevelIndicator which will be in charge of displaying the current level name.
 */
public class LevelIndicator implements Sprite {
    private String name;
    private Rectangle rectangle;
    private Color color;

    /**
     * Constructor.
     *
     * @param name      the level name to display
     * @param rectangle the size of the information bar
     * @param color     color of the text
     */
    public LevelIndicator(String name, Rectangle rectangle, Color color) {
        this.name = name;
        this.rectangle = rectangle;
        this.color = color;
    }

    @Override
    public void drawOn(DrawSurface d) {
        //draw the text
        d.setColor(color);
        d.drawText((int) rectangle.getUpperRight().getX() - (int) rectangle.getWidth() / 4,
                (int) rectangle.getUpperLeft().getY() + (int) rectangle.getHeight() / 2 + 5,
                "Level Name: " + name, 14);
    }

    @Override
    public void timePassed(double dt) {

    }

    /**
     * add the LevelIndicator to the sprites collection.
     *
     * @param g GameLevel
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
