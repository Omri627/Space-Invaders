package backgrounds;

import biuoop.DrawSurface;
import core.Sprite;

import java.awt.*;

/**
 * the background of the direct hit level.
 */
public class DirectHitBackground implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        //background color
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 20, d.getWidth(), d.getHeight());

        //the target drawing
        d.setColor(Color.GREEN);
        d.drawCircle(400, 162, 60);
        d.drawCircle(400, 162, 90);
        d.drawCircle(400, 162, 120);

        d.drawLine(400, 182, 400, 302);
        d.drawLine(420, 162, 540, 162);
        d.drawLine(380, 162, 260, 162);
        d.drawLine(400, 142, 400, 22);
    }

    @Override
    public void timePassed(double dt) {

    }

}
