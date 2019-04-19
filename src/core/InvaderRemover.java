package core;



import spaceinvaders.Ball;
import spaceinvaders.Block;
import spaceinvaders.GameLevel;
import spaceinvaders.Invader;
import spaceinvaders.InvadersGroup;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * InvaderRemover is in charge of removing invaders from the gameLevel, as well as keeping count
 * of the number of Invaders that remain.
 */
public class InvaderRemover implements HitListener<Block> {

    private GameLevel gameLevel;
    private Counter remainingInvaders;
    private List<Invader> invaderList;
    private List<Ball> enemysBall;
    private InvadersGroup group;

    /**
     * constructor.
     *
     * @param gameLevel         gameLevel
     * @param remainingInvaders counter of the remaining Invaders
     * @param invaderList       List of all the invaders
     * @param enemyBalls        enemy's own balls
     * @param group             the group of invaders
     */
    public InvaderRemover(GameLevel gameLevel, Counter remainingInvaders, List<Invader> invaderList,
                          List<Ball> enemyBalls, InvadersGroup group) {
        this.gameLevel = gameLevel;
        this.remainingInvaders = remainingInvaders;
        this.invaderList = invaderList;
        this.enemysBall = enemyBalls;
        this.group = group;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        //check if Invader should be removed.
        if (beingHit.getHitPoints() == 0 && !enemysBall.contains(hitter)) {
            //remove from list
            ArrayList<LinkedList<Invader>> copy = new ArrayList<>(group.getArrOfLinkedList());
            group.deleteFromLinkedList(((Invader) beingHit).getId(), copy);
            group.setArrOfLinkedList(copy);
            invaderList.remove(beingHit);
            //removing
            beingHit.removeFromGame(gameLevel);
            beingHit.removeHitListener(this);
            //decrease the number of invaders remaining to removed.
            remainingInvaders.decrease(1);
        }
    }

}
