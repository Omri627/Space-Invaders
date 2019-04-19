package backgrounds;

import biuoop.DrawSurface;
import core.Sprite;

import java.awt.*;

/**
 * the background for WildEasy.
 */
public class WildEasyBackground implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        //background color
        d.setColor(new Color(74, 196, 223));
        d.fillRectangle(0, 20, d.getWidth(), d.getHeight());

        //sunColor
        Color yellow = new Color(236, 211, 71);
        d.setColor(yellow);
        d.fillCircle(150, 150, 50);

        int rays = 100;
        int startX = 20;
        int endX = 800;

        d.setColor(yellow);
        for (int i = 1; i <= rays; i++) {
            d.drawLine(150, 150, (endX - startX) / rays * i, 250);
        }

        Color sunsetYellow = new Color(236, 176, 40);
        d.setColor(sunsetYellow);
        d.fillCircle(150, 150, 40);
    }

    @Override
    public void timePassed(double dt) {

    }
}
