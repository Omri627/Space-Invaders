package backgrounds;

import biuoop.DrawSurface;
import core.Sprite;

import java.awt.*;

/**
 * the GreenBackground sprite.
 */
public class GreenBackground implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {

        //draw background
        d.setColor(new Color(0, 128, 0));
        d.fillRectangle(0, 20, d.getWidth(), d.getHeight());

        int startX = 60;
        int startY = 300;
        d.setColor(new Color(173, 216, 230));
        d.fillRectangle(startX, startY, 100, 300);

        //draw columns
        d.setColor(Color.BLACK);
        for (int i = 0; i < 6; i++) {
            d.fillRectangle(startX, startY, 5, 300);
            startX += 20;
        }

        //draw rows
        startX = 60;
        for (int i = 0; i < 8; i++) {
            d.fillRectangle(startX, startY, 100, 5);
            startY += 40;
        }
        startY = 250;
        //draw the pole
        d.fillRectangle(startX + 25, startY, 55, 50);
        d.fillRectangle(startX + 50, startY - 80, 10, 80);


        d.setColor(new Color(78, 34, 34));
        d.fillCircle(115, startY - 80, 14);
        d.setColor(new Color(255, 165, 0));
        d.fillCircle(115, startY - 80, 10);
        d.setColor(Color.WHITE);
        d.fillCircle(115, startY - 80, 4);


    }

    @Override
    public void timePassed(double dt) {

    }

}
