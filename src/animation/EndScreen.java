package animation;

import biuoop.DrawSurface;
import core.Counter;

import java.awt.Color;

/**
 * end screen display the final score.
 */
public class EndScreen implements Animation {
    private Counter scoreCounter;
    private Counter livesCounter;
    private boolean stop;

    /**
     * constructor.
     *
     * @param scoreCounter   total score
     * @param livesCounter   current lives point at the end of the game
     */
    public EndScreen(Counter scoreCounter, Counter livesCounter) {
        this.scoreCounter = scoreCounter;
        this.livesCounter = livesCounter;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.GRAY);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        String message = "You win!";
        if (livesCounter.getValue() == 0) {
            message = "Game Over ";
        }
        //draw message
        d.setColor(Color.BLACK);
        d.drawText(100, 100, message, 55);
        d.setColor(Color.RED);
        d.drawText(102, 101, message, 55);
        d.setColor(Color.BLACK);
        d.drawText(105, 102, message, 55);
        //draw score
        String message2 = "Your score is: " + scoreCounter.getValue();
        d.drawText(100, 170, message2, 55);
        d.setColor(Color.RED);
        d.drawText(102, 171, message2, 55);
        d.setColor(Color.BLACK);
        d.drawText(105, 172, message2, 55);

        d.setColor(Color.BLACK);
        d.drawText(50, 550, "Press space key to exit", 30);
    }

    @Override
    public boolean shouldStop() {
        return stop;
    }
}
