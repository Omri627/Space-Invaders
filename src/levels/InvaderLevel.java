package levels;

import biuoop.DrawSurface;
import core.Sprite;
import core.Velocity;
import spaceinvaders.Block;
import spaceinvaders.GameLevel;
import spaceinvaders.Invader;
import spaceinvaders.InvadersGroup;

import java.awt.Image;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * level information about the level.
 */
public class InvaderLevel implements LevelInformation {

    private Image image;
    private int leftBorder, rightBoreder;
    private InvadersGroup invadersGroup;
    private int levlNum;
    private Velocity velocity;

    /**
     * constructor.
     * @param image the image of the invader
     * @param rightBoreder right border of the screen
     * @param leftBorder left border of the screen
     * @param levelNum the level number
     */
    public InvaderLevel(Image image, int rightBoreder, int leftBorder, int levelNum) {
        this.velocity = new Velocity(70, 0);
        this.image = image;
        this.leftBorder = leftBorder;
        this.rightBoreder = rightBoreder;
        invadersGroup = new InvadersGroup(50, 50, image, rightBoreder, leftBorder, velocity);
        this.levlNum = levelNum;

    }

    /**
     * constructor.
     * @param level the level to copy
     * @param levelNum number of the battle
     * @param v velocity to start from
     */
    public InvaderLevel(InvaderLevel level , int levelNum, Velocity v) {
        this.velocity = v;
        this.image = level.getImage();
        this.leftBorder = level.getLeftBorder();
        this.rightBoreder = level.getRightBoreder();
        this.invadersGroup = new InvadersGroup(50, 50, image, rightBoreder, leftBorder, velocity);
        this.levlNum = levelNum;
    }

    @Override
    public int numberOfBalls() {
        return 0;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return null;
    }

    @Override
    public int paddleSpeed() {
        return 500;
    }

    @Override
    public int paddleWidth() {
        return 60;
    }

    @Override
    public String levelName() {
        return " Battle n. "  + levlNum;
    }

    @Override
    public Sprite getBackground() {
        //the background
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.BLACK);
                d.fillRectangle(0, 20, d.getWidth(), d.getHeight());
            }

            @Override
            public void timePassed(double dt) {

            }
        };
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        //create shields
        blocks.addAll(GameLevel.createBlockInRowRL(28, Color.MAGENTA, new geometry.Point(200, 500), 5, 5, 1));
        blocks.addAll(GameLevel.createBlockInRowRL(28, Color.MAGENTA, new geometry.Point(200, 505), 5, 5, 1));
        blocks.addAll(GameLevel.createBlockInRowRL(28, Color.MAGENTA, new geometry.Point(200, 510), 5, 5, 1));

        blocks.addAll(GameLevel.createBlockInRowRL(28, Color.MAGENTA, new geometry.Point(475, 500), 5, 5, 1));
        blocks.addAll(GameLevel.createBlockInRowRL(28, Color.MAGENTA, new geometry.Point(475, 505), 5, 5, 1));
        blocks.addAll(GameLevel.createBlockInRowRL(28, Color.MAGENTA, new geometry.Point(475, 510), 5, 5, 1));

        blocks.addAll(GameLevel.createBlockInRowRL(28, Color.MAGENTA, new geometry.Point(750, 500), 5, 5, 1));
        blocks.addAll(GameLevel.createBlockInRowRL(28, Color.MAGENTA, new geometry.Point(750, 505), 5, 5, 1));
        blocks.addAll(GameLevel.createBlockInRowRL(28, Color.MAGENTA, new geometry.Point(750, 510), 5, 5, 1));

        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        //the number of aliens to removes
        return invadersGroup.getInvaderList().size();
    }

    @Override
    public List<Invader> invaders() {
        //return the invaders to kill
        return invadersGroup.getInvaderList();
    }

    @Override
    public InvadersGroup getGroup() {
        return invadersGroup;
    }

    @Override
    public void setInvadersGroup(InvadersGroup newInvadersGroup) {
        this.invadersGroup = invadersGroup;
    }

    /**
     * get the image of the invaders.
     * @return image
     */
    public Image getImage() {
        return image;
    }

    /**
     * get the left border.
     * @return left border.
     */
    public int getLeftBorder() {
        return leftBorder;
    }

    /**
     * get the right border.
     * @return right border
     */
    public int getRightBoreder() {
        return rightBoreder;
    }
}
