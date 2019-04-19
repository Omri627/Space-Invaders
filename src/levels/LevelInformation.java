package levels;

import spaceinvaders.Block;
import core.Sprite;
import core.Velocity;
import spaceinvaders.Invader;
import spaceinvaders.InvadersGroup;

import java.util.List;

/**
 * The levels.LevelInformation interface specifies the information required to fully describe a level.
 */
public interface LevelInformation {
    /**
     * number of balls.
     * @return number of balss
     */
    int numberOfBalls();

    /**
     * list of The initial velocity of each ball.
     * @return The initial velocity of each ball
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return speed of the paddle.
     */
    int paddleSpeed();

    /**
     * @return width of the paddle
     */
    int paddleWidth();

    /**
     * @return the name of the level
     */
    String levelName();

    /**
     * @return a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     * @return all blocks that make up this level
     */
    List<Block> blocks();

    /**
     * Number of Blocks that should be removed
     * before the level is considered to be "cleared".
     * @return num of blocks that should be removed
     */
    int numberOfBlocksToRemove();

    /**
     * the invaders in the level.
     * @return invaders int the levels
     */
    List<Invader> invaders();

    /**
     * return the group of invaders.
     * @return the group of invaders
     */
    InvadersGroup getGroup();

    /**
     * set the group of aliens.
     *
     * @param newInvadersGroup the group to set
     */
    void setInvadersGroup(InvadersGroup newInvadersGroup);
}
