package levels;

import spaceinvaders.Block;
import backgrounds.DirectHitBackground;
import core.Sprite;
import core.Velocity;
import geometry.Point;
import geometry.Rectangle;
import spaceinvaders.Invader;
import spaceinvaders.InvadersGroup;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * level information about "DirectHit".
 */
public class DirectHit implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities =  new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(270, 300));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 500;
    }

    @Override
    public int paddleWidth() {
        return 150;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
       return new DirectHitBackground();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Block block = new Block(new Rectangle(new Point(385, 148), 30, 30), Color.RED);
        block.setHitPoint(1);
        blocks.add(block);
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

    @Override
    public List<Invader> invaders() {
        return null;
    }

    @Override
    public InvadersGroup getGroup() {
        return null;
    }

    @Override
    public void setInvadersGroup(InvadersGroup newInvadersGroup) {

    }
}
