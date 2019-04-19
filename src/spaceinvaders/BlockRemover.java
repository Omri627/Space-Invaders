package spaceinvaders;

import core.Counter;
import core.HitListener;


/**
 * a spaceinvaders.BlockRemover is in charge of removing blocks from the gameLevel, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener<Block> {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * constructor.
     * @param gameLevel gameLevel
     * @param removedBlocks counter of blocks that can be removed
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        //check if block should be removed.
       if (beingHit.getHitPoints() == 0) {
           //removing
           beingHit.removeFromGame(gameLevel);
           beingHit.removeHitListener(this);
           //decrease the number of blocks remaining to removed.
           remainingBlocks.decrease(1);
       }
    }


}
