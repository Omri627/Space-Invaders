package levels;

import spaceinvaders.Block;
import spaceinvaders.GameLevel;
import backgrounds.GreenBackground;
import core.Sprite;
import core.Velocity;
import geometry.Point;
import spaceinvaders.Invader;
import spaceinvaders.InvadersGroup;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * level information about "Green".
 */
public class Green implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        double speed = 400;

        velocities.add(Velocity.fromAngleAndSpeed(250, speed));
        velocities.add(Velocity.fromAngleAndSpeed(290, speed));

        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 400;
    }

    @Override
    public int paddleWidth() {
        return 200;
    }

    @Override
    public String levelName() {
        return "Green";
    }

    @Override
    public Sprite getBackground() {
        return new GreenBackground();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        //create blocks
        blocks.addAll(GameLevel.createBlockInRowRL(10, Color.GRAY, new Point(740, 170), 40, 20, 2));
        blocks.addAll(GameLevel.createBlockInRowRL(9, Color.RED, new Point(740, 190), 40, 20, 1));
        blocks.addAll(GameLevel.createBlockInRowRL(8, Color.YELLOW, new Point(740, 210), 40, 20, 1));
        blocks.addAll(GameLevel.createBlockInRowRL(7, Color.BLUE, new Point(740, 230), 40, 20, 1));
        blocks.addAll(GameLevel.createBlockInRowRL(6, Color.WHITE, new Point(740, 250), 40, 20, 1));
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
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
