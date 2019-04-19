package spaceinvaders;

import animation.Animation;
import animation.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * CountdownAnimation is responsible to play a countdown animation from 3 to 1.
 */
public class CountdownAnimation implements Animation {
    private double numbOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private Sleeper sleeper;
    private boolean stop;

    /**
     * constructor.
     *
     * @param numbOfSeconds number of seconds to count from
     * @param countFrom     the number to count from
     * @param gameScreen    all the animation behind the countdown
     */
    public CountdownAnimation(double numbOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numbOfSeconds = numbOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        sleeper = new Sleeper();
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        String text;
        if (numbOfSeconds < 0) {
            text = "Go!";
            stop = true;
        } else {
            text = countFrom + "";
        }
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.MAGENTA);
        d.drawText(d.getHeight() / 2 + 50, d.getWidth() / 2, text, 42);
        d.setColor(Color.GREEN);
        d.drawText(d.getHeight() / 2 + 50, d.getWidth() / 2, text, 44);
        sleeper.sleepFor(500);
        this.countFrom = countFrom - 1;
        this.numbOfSeconds = numbOfSeconds - 1;
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
