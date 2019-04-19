package levels;

import spaceinvaders.Block;
import backgrounds.WildEasyBackground;
import core.Sprite;
import core.Velocity;
import geometry.Point;
import spaceinvaders.Invader;
import spaceinvaders.InvadersGroup;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * level information about WildEasy.
 */
public class WildEasy implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();

        double speed = 400;

        velocities.add(Velocity.fromAngleAndSpeed(230, speed));
        velocities.add(Velocity.fromAngleAndSpeed(240, speed));
        velocities.add(Velocity.fromAngleAndSpeed(250, speed));
        velocities.add(Velocity.fromAngleAndSpeed(260, speed));
        velocities.add(Velocity.fromAngleAndSpeed(270, speed));
        velocities.add(Velocity.fromAngleAndSpeed(280, speed));
        velocities.add(Velocity.fromAngleAndSpeed(290, speed));
        velocities.add(Velocity.fromAngleAndSpeed(300, speed));
        velocities.add(Velocity.fromAngleAndSpeed(310, speed));
        velocities.add(Velocity.fromAngleAndSpeed(320, speed));

        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 50;
    }

    @Override
    public int paddleWidth() {
        return 600;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new WildEasyBackground();
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Color[] colors = {Color.MAGENTA, Color.YELLOW, Color.GREEN, Color.RED, Color.CYAN};
        double x = 740;
        double y = 250;
        for (int i = 0; i < 19; i++) {
            Block b = new Block(new geometry.Rectangle(new Point(x, y), 40, 20),
                      colors[i % 5]);
            b.setHitPoint(1);
            blocks.add(b);
            //move the next block corner
            x -= 40;
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 19;
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
