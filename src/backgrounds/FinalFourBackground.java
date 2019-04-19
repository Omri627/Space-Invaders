package backgrounds;

import biuoop.DrawSurface;
import core.Sprite;

import java.awt.*;

/**
 * the FinalFourBackground background sprite .
 */
public class FinalFourBackground implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        //draw background
        d.setColor(new Color(0, 191, 255));
        d.fillRectangle(0, 20, d.getWidth(), d.getHeight());

        d.setColor(Color.WHITE);
        for (int i = 0; i < 12; i++) {
            d.drawLine(90 + i * 10, 400, 80 + i * 10, 600);
        }

        d.setColor(new Color(211, 211, 211));
        d.fillCircle(100, 400, 23);
        d.fillCircle(120, 420, 27);
        d.setColor(new Color(192, 192, 192));
        d.fillCircle(140, 390, 29);
        d.setColor(new Color(169, 169, 169));
        d.fillCircle(160, 420, 22);
        d.fillCircle(180, 400, 32);


        d.setColor(Color.WHITE);
        for (int i = 0; i < 12; i++) {
            d.drawLine(590 + i * 10, 490, 580 + i * 10, 600);
        }

        d.setColor(new Color(211, 211, 211));
        d.fillCircle(610, 480, 25);
        d.fillCircle(630, 520, 29);
        d.setColor(new Color(192, 192, 192));
        d.fillCircle(650, 470, 33);
        d.setColor(new Color(169, 169, 169));
        d.fillCircle(670, 520, 25);
        d.fillCircle(680, 510, 33);

    }

    @Override
    public void timePassed(double dt) {

    }
}
