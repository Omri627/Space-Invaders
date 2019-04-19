package spaceinvaders;

import core.Counter;
import core.HitListener;

/**
 * a spaceinvaders.BallRemover is in charge of removing balls from the gameLevel, as well as keeping count
 * of the number of balls that remain.
 */
public class BallRemover implements HitListener<Block> {
    private GameLevel gameLevel;
    private Counter remainingBalls;
    /**
     * constructor.
     * @param gameLevel gameLevel
     * @param removedBalls counter of balls that can be removed
     */
    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        //removing
        hitter.removeFromGame(gameLevel);
        remainingBalls.decrease(1);
        //System.out.println(hitter + " Removed");
    }
}
