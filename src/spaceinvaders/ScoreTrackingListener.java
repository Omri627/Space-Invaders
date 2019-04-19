package spaceinvaders;


import core.Counter;
import core.HitListener;

import java.awt.Color;

/**
 * spaceinvaders.ScoreTrackingListener update  a counter when blocks are being hit and removed.
 */
public class ScoreTrackingListener implements HitListener<Block> {
    private Counter currentScore;

    /**
     * constructor.
     *
     * @param scoreCounter the scoreCounter to update.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {

        if (beingHit.getHitPoints() == 0) {
            if (!hitter.getColor().equals(Color.RED)) {
                //if the block is destroyed, add 10 points
                currentScore.increase(100);
            }

        }
    }
}
