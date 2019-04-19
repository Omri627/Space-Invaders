package spaceinvaders;


import core.HitListener;

/**
 * simple core.HitListener that prints a message to the screen whenever a block is hit.
 */
public class PrintingHitListener implements HitListener<Block> {

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        //add one point because the print listener listen after the point reduce
        int hitPoint = beingHit.getHitPoints() + 1;
        System.out.println("A Block with " + hitPoint + " points was hit.");
    }
}
