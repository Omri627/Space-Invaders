package animation;

import biuoop.DrawSurface;
import core.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * hold a collection of sprites, and can preform task on all the sprites.
 */
public class SpriteCollection {
    private List<Sprite> list = new ArrayList<>();

    /**
     * add sprite to the list.
     *
     * @param s core.Sprite
     */
    public void addSprite(Sprite s) {
        list.add(s);
    }

    /**
     * remove a sprite from the list.
     *
     * @param s core.Sprite
     */
    public void removeSprites(Sprite s) {
        list.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     * @param dt  amount of seconds passed since the last call
     */
    public void notifyAllTimePassed(double dt) {
        List<Sprite> sprites = new ArrayList<>(this.list);
        for (Sprite sprite : sprites) {
            sprite.timePassed(dt);
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d drawSurface
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> sprites = new ArrayList<>(this.list);
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }
}
