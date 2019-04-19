package animation;

import spaceinvaders.HighScoresTable;
import spaceinvaders.ScoreInfo;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * This class show the high score.
 */
public class HighScoresAnimation implements Animation {
    private boolean stop;
    private HighScoresTable table;

    /**
     * constructor.
     * @param scoresTable scoreTable to show
     */
    public HighScoresAnimation(HighScoresTable scoresTable) {
        this.table = scoresTable;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        //draw the table
        d.setColor(Color.lightGray);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.BLACK);
        d.drawText(d.getWidth() / 2 - 150, (int) (d.getHeight() * 0.15), "High Scores Table", 32);
        int diff = 400;
        int i = 1;
        for (ScoreInfo info: table.getHighScores()) {
            d.drawText(10, d.getHeight() - diff, i + ") " + info.getName() , 26);
            d.drawText(400, d.getHeight() - diff, info.getScore() + "" , 26);
            diff -= 35;
            i++;
        }
        d.drawText(10, (int) (d.getHeight() * 0.85) , "press space to continue", 32);

    }

    @Override
    public boolean shouldStop() {
        return stop;
    }


}
