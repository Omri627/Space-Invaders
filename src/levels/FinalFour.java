package levels;

import spaceinvaders.Block;
import spaceinvaders.GameLevel;
import backgrounds.FinalFourBackground;
import core.Sprite;
import core.Velocity;
import geometry.Point;
import spaceinvaders.Invader;
import spaceinvaders.InvadersGroup;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * level information about Final Four.
 */
public class FinalFour implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        double speed = 400;

        velocities.add(Velocity.fromAngleAndSpeed(250, speed));
        velocities.add(Velocity.fromAngleAndSpeed(270, speed));
        velocities.add(Velocity.fromAngleAndSpeed(290, speed));

        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 400;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return new FinalFourBackground();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        //create blocks
        blocks.addAll(GameLevel.createBlockInRowRL(19, Color.GRAY, new Point(740, 130), 40, 20, 2));
        blocks.addAll(GameLevel.createBlockInRowRL(19, Color.RED, new Point(740, 150), 40, 20, 1));
        blocks.addAll(GameLevel.createBlockInRowRL(19, Color.BLUE, new Point(740, 170), 40, 20, 1));
        blocks.addAll(GameLevel.createBlockInRowRL(19, Color.YELLOW, new Point(740, 190), 40, 20, 1));
        blocks.addAll(GameLevel.createBlockInRowRL(19, Color.WHITE, new Point(740, 210), 40, 20, 1));
        blocks.addAll(GameLevel.createBlockInRowRL(19, Color.GREEN, new Point(740, 230), 40, 20, 1));
        blocks.addAll(GameLevel.createBlockInRowRL(19, Color.pink, new Point(740, 250), 40, 20, 1));
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 114;
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
