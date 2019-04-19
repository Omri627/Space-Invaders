package spaceinvaders;

import animation.Animation;
import animation.AnimationRunner;
import animation.SpriteCollection;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import core.Collidable;
import core.HitListener;
import core.InvaderRemover;
import core.Counter;
import core.Sprite;
import core.Velocity;
import geometry.Point;
import geometry.Rectangle;
import levels.LevelInformation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * class that hold the sprites and the collidables,
 * and will be in charge of the animation.
 *
 * @author Omri Sak
 */
public class GameLevel implements Animation {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private static final int THICKNESS = 20;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private biuoop.KeyboardSensor keyboard;
    private Counter blocksCounter;
    private Counter invadersCounter;
    private Counter ballsCounter;
    private Counter currentScore;
    private Counter currentLives;
    private AnimationRunner runner;
    private boolean running;
    private Paddle currentPaddle;
    private LevelInformation levelInformation;
    private InvadersGroup invadersGroup;
    private InvadersGroup formationGroup;
    private double coolDown;
    private double enemyCoolDown;
    private List<Invader> invaders;
    private List<Invader> invadersCopy;
    private List<Ball> enemyBalls;
    private List<Ball> playerBall;
    private Velocity startVelocity;
    //private int lifePoint;
    private boolean lostlife;


    /**
     * Constructor.
     *
     * @param information the information of the current level
     */
    public GameLevel(LevelInformation information) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = new GUI("Arkanoid", WIDTH, HEIGHT);
        this.keyboard = gui.getKeyboardSensor();
        this.blocksCounter = new Counter();
        this.ballsCounter = new Counter();
        this.currentScore = new Counter();
        this.currentLives = new Counter();
        this.invadersCounter = new Counter();
        this.currentLives.increase(4);
        this.runner = new AnimationRunner(gui, 60);
        this.levelInformation = information;
        this.enemyBalls = new ArrayList<>();
        this.playerBall = new ArrayList<>();
        this.lostlife = false;

    }

    /**
     * constructor.
     *
     * @param info              the current level information
     * @param gui               GUI
     * @param runner            AnimationRunner
     * @param sensor            keyboard sensor
     * @param scoreCounter      the current score counter
     * @param livesCounter      the current lives counter
     * @param originalFormation original formation
     */
    public GameLevel(LevelInformation info, GUI gui, AnimationRunner runner, KeyboardSensor sensor,
                     Counter scoreCounter, Counter livesCounter, InvadersGroup originalFormation) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = gui;
        this.keyboard = sensor;
        this.blocksCounter = new Counter();
        this.ballsCounter = new Counter();
        this.invadersCounter = new Counter();
        this.currentScore = scoreCounter;
        this.currentLives = livesCounter;
        this.runner = runner;
        this.levelInformation = info;
        this.enemyBalls = new ArrayList<>();
        this.playerBall = new ArrayList<>();
        this.formationGroup = originalFormation;
    }

    /**
     * get the remaining removable blocks on the current level.
     *
     * @return int number of remaining blocks
     */
    public int getRemainingBlocks() {
        return blocksCounter.getValue();
    }

    /**
     * get the remaining enemies on the current level.
     *
     * @return int number of remaining enemies
     */
    public int getRemainingInvaders() {
        return invadersCounter.getValue();
    }

    /**
     * add collidable object to the game.
     *
     * @param c collidable object.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * remove a collidable object from the collidable list.
     *
     * @param c collidable object.
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * add sprite to the game.
     *
     * @param s sprite to add to gmae.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * remove sprite from the game.
     *
     * @param s sprite to add to gmae.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprites(s);
    }

    /**
     * Initialize a new game: create the Blocks and spaceinvaders.Ball (and spaceinvaders.Paddle)
     * and add them to the game.
     */
    public void initialize() {
        ////add the background to the sprite collection
        this.addSprite(levelInformation.getBackground());
        //add blocks
        List<Block> blocks = levelInformation.blocks();
        for (Block block : blocks) {
            blocksCounter.increase(1);
            //add listener
            block.addToGame(this);
        }
        //add the aliens
        //get the invader group
        invadersGroup = levelInformation.getGroup();
        invadersGroup.addToGame(this);
        invadersCounter.increase(50);
        invaders = levelInformation.invaders();
        invadersCopy = levelInformation.invaders();
        this.startVelocity = invaders.get(0).getVelocity();

        //add listeners, hitListneres, printListeners and hitListeners
        PrintingHitListener printer = new PrintingHitListener();
        addPrintingHitListener(printer, blocks);
        BlockRemover blockRemover = new BlockRemover(this, blocksCounter);
        addBlockRemoverListener(blockRemover, blocks);
        InvaderRemover invaderRemover = new InvaderRemover(
                this, invadersCounter, invadersCopy, enemyBalls, invadersGroup);
        addInvaderRemoverListener(invaderRemover, invaders);
        ScoreTrackingListener scoreTracking = new ScoreTrackingListener(currentScore);
        addScoreTrackingListener(scoreTracking, invaders);
        //draw the score indicator
        creteInformationBar(currentScore, levelInformation.levelName());


        //create death region
        Rectangle deathRectangle = new Rectangle(new Point(-50, HEIGHT + 2 * THICKNESS), WIDTH + 100, THICKNESS);
        Rectangle deathRectangle2 = new Rectangle(new Point(-50, 0 - 2 * THICKNESS), WIDTH + 100, THICKNESS);
        Block deathBlock = new Block(deathRectangle, Color.BLACK);
        Block deathBlock2 = new Block(deathRectangle2, Color.GREEN);
        BallRemover ballRemover = new BallRemover(this, ballsCounter);
        deathBlock.addHitListener(ballRemover);
        deathBlock2.addHitListener(ballRemover);
        deathBlock.addToGame(this);
        deathBlock2.addToGame(this);
        //add ball remover listener to the bullets
        addBallRemoverToBlocks(blocks, ballRemover);
        addBallRemoverToInvaders(invaders, ballRemover);


    }

    /**
     * add ball remover to the bullets.
     *
     * @param blocks  the blocks to remove
     * @param remover the listener
     */
    private void addBallRemoverToBlocks(List<Block> blocks, BallRemover remover) {
        for (Block b : blocks) {
            b.addHitListener(remover);
        }
    }

    /**
     * add ball remover to the bullets.
     *
     * @param invadersList the invaders to remove
     * @param remover      the listener
     */
    private void addBallRemoverToInvaders(List<Invader> invadersList, BallRemover remover) {
        for (Invader b : invadersList) {
            b.addHitListener(remover);
        }
    }

    /**
     * Run the game - start the animation loop.
     * need to initialize the game before.
     */
    public void playOneTurn() {
        this.coolDown = 0.0;
        this.enemyCoolDown = 0.0;
        this.currentPaddle = createPaddle();
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    /**
     * run playOneTurn while there is still life.
     */
    public void run() {
        playOneTurn();
        while (currentLives.getValue() > 0) {
            playOneTurn();
        }
        gui.close();
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        this.coolDown -= dt;
        this.enemyCoolDown -= dt;
        //draw the all the drawables.
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);

        boolean reachedShields = invadersGroup.checkAndSwapPosition();

        //the invader reached the shield position
        if (reachedShields) {
            currentLives.decrease(1);
            this.running = false;
            resetFormation(invadersGroup);
            removePaddle(currentPaddle);
            removeBalls(enemyBalls);
            removeBalls(playerBall);
            //resetLevel();
        }
        //all invaders has been cleared
        if (invadersCounter.getValue() == 0) {
            removePaddle(currentPaddle);
            removeBalls(enemyBalls);
            removeBalls(playerBall);
            this.running = false;
        }

        //the player lost life from getting shot but still have life
        if (lostlife) {
            lostlife = false;
            this.running = false;
            removePaddle(currentPaddle);
            removeBalls(enemyBalls);
            removeBalls(playerBall);
            resetFormation(invadersGroup);

        }

        //the player lost all his life
        if (currentLives.getValue() <= 0) {
            //resetLevel();
            this.running = false;
        }


        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }

        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY) && this.coolDown < 0.0) {
            paddleShot();
        }

        if (this.enemyCoolDown < 0) {
            //.updateLinkedList(invadersCopy);
            this.enemyShoot(this.invadersGroup.getShoot());
        }

    }

    /**
     * reset position of the invaders and paddle.
     *
     * @param currentInvaders current invaders
     */
    private void resetLevel(List<Invader> currentInvaders) {
        invadersGroup.resetGroup(currentInvaders);
    }

    /**
     * make the paddle shot.
     */
    private void paddleShot() {
        this.coolDown = 0.35;
        double ballX = this.currentPaddle.getUpperLeftX() + 30.0;
        double ballY = this.currentPaddle.getUpperLeftY() - 10.0;
        Ball bullet = new Ball(new Point(ballX, ballY), 3, Color.WHITE);
        bullet.setVelocity(Velocity.fromAngleAndSpeed(270, 600));
        bullet.setEnvironment(environment);
        bullet.addToGame(this);
        playerBall.add(bullet);
    }

    /**
     * make the invader shoot.
     *
     * @param start start point of the bulletq
     */
    private void enemyShoot(Point start) {

        if (start != null) {
            this.enemyCoolDown = 0.5;
            Ball bullet = new Ball(start, 4, Color.RED);
            bullet.setVelocity(Velocity.fromAngleAndSpeed(-270, 600));
            bullet.setEnvironment(environment);
            bullet.addToGame(this);
            enemyBalls.add(bullet);
        }

    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * this function return a list of block in a row from left to right.
     * this method is static so I can get access it in all the levels that I create.
     *
     * @param numOfBlock num of block to add
     * @param color      color of the block
     * @param upperLeft  first position to add a block
     * @param width      width of single block
     * @param height     height of single block
     * @param hitPoint   hit geometry.Point of the blocks
     * @return list of blocks
     */
    public static List<Block> createBlockInRowLR(int numOfBlock, Color color, Point upperLeft,
                                                 double width, double height, int hitPoint) {
        double x = upperLeft.getX();
        double y = upperLeft.getY();
        List<Block> blockList = new ArrayList<>();
        for (int i = 0; i < numOfBlock; i++) {
            Block b = new Block(new Rectangle(new Point(x, y), width, height), color);
            b.setHitPoint(hitPoint);
            blockList.add(b);
            //move the next block corner
            x += width;
        }
        return blockList;
    }

    /**
     * this function return a list of block in a row from Right to Left.
     *
     * @param numOfBlock num of block to add
     * @param color      color of the block
     * @param upperLeft  first position to add a block
     * @param width      width of single block
     * @param height     height of single block
     * @param hitPoint   hit geometry.Point of the blocks
     * @return list of blocks
     */
    public static List<Block> createBlockInRowRL(int numOfBlock, Color color, Point upperLeft,
                                                 double width, double height, int hitPoint) {
        double x = upperLeft.getX();
        double y = upperLeft.getY();
        List<Block> blockList = new ArrayList<>();
        for (int i = 0; i < numOfBlock; i++) {
            Block b = new Block(new Rectangle(new Point(x, y), width, height), color);
            b.setHitPoint(hitPoint);
            blockList.add(b);
            //move the next block corner
            x -= width;
        }
        return blockList;
    }

    /**
     * add block to game environment.
     *
     * @param list list of block which will added to the spaceinvaders.GameEnvironment.
     */
    private void addBlocksToEnvironmentAndSprites(List<Block> list) {
        for (Block block : list) {
            block.addToGame(this);
        }
    }

    /**
     * add removable block to game environment.
     *
     * @param blockCounter the blocksCounter of removable blocks.
     * @param list         list of block which will added to the spaceinvaders.GameEnvironment.
     */
    private void addRemovableBlocks(Counter blockCounter, List<Block> list) {
        for (Block block : list) {
            block.addToGame(this);
            blockCounter.increase(1);
        }
    }

    /**
     * create the borders blocks.
     *
     * @param width     width of screen
     * @param height    height of screen
     * @param thickness of the border
     * @param color     color of the border
     * @return list of block
     */
    private List<Block> createBorder(int width, int height, int thickness, Color color) {
        List<Block> borderList = new ArrayList<>();
        Rectangle[] recs = new Rectangle[3];
        //upper border
        recs[0] = new Rectangle(new Point(0, thickness), width, thickness);
        //left border
        recs[1] = new Rectangle(new Point(0, thickness), thickness, height);
        //right border
        recs[2] = new Rectangle(new Point(width - thickness, thickness), thickness * 2, height);
        for (Rectangle rec : recs) {
            Block b = new Block(rec, color);
            b.setHitPoint(0);
            borderList.add(b);
        }
        return borderList;
    }

    /**
     * create the information bar, witch contains all the score, life and level name.
     *
     * @param scoreCounter core.Counter of the score
     * @param levelName    the level name
     */
    private void creteInformationBar(Counter scoreCounter, String levelName) {
        //create the score bar
        Rectangle rec = new Rectangle(new Point(0, 0), WIDTH, THICKNESS);
        ScoreIndicator scoreIndicatorBar = new ScoreIndicator(scoreCounter, rec, Color.BLACK);
        LivesIndicator livesIndicator = new LivesIndicator(currentLives, rec, Color.BLACK);
        LevelIndicator levelIndicator = new LevelIndicator(levelName, rec, Color.BLACK);

        //add to game
        scoreIndicatorBar.addToGame(this);
        livesIndicator.addToGame(this);
        levelIndicator.addToGame(this);
    }

    /**
     * add spaceinvaders.PrintingHitListener to the blocks.
     *
     * @param listener spaceinvaders.PrintingHitListener
     * @param blocks   blocks to listen.
     */
    private void addPrintingHitListener(PrintingHitListener listener, List<Block> blocks) {
        for (Block block : blocks) {
            block.addHitListener(listener);
        }
    }

    /**
     * add spaceinvaders.BlockRemover to the blocks.
     *
     * @param listener spaceinvaders.BlockRemover
     * @param blocks   blocks to listen.
     */
    private void addBlockRemoverListener(BlockRemover listener, List<Block> blocks) {
        for (Block block : blocks) {
            block.addHitListener(listener);
        }
    }

    /**
     * add BlockRemover to the blocks.
     *
     * @param listener     BlockRemover
     * @param invadersList invaders to listen.
     */
    private void addInvaderRemoverListener(InvaderRemover listener, List<Invader> invadersList) {
        for (Invader invader : invadersList) {
            invader.addHitListener(listener);
        }
    }

    /**
     * add spaceinvaders.ScoreTrackingListener to the blocks.
     *
     * @param listener spaceinvaders.ScoreTrackingListener
     * @param blocks   blocks to listen.
     */


    /**
     * add ScoreTrackingListener to the invaders.
     *
     * @param listener     ScoreTrackingListener
     * @param invadersList invaders to listen
     */
    private void addScoreTrackingListener(ScoreTrackingListener listener, List<Invader> invadersList) {
        for (Invader invader : invadersList) {
            invader.addHitListener(listener);
        }
    }


    /**
     * create paddle in the middle.
     *
     * @return the created paddle.
     */
    private Paddle createPaddle() {
        Point middle = new Point((WIDTH / 2) - (levelInformation.paddleWidth() / 2), HEIGHT - (2 * THICKNESS));
        Rectangle size = new Rectangle(middle, levelInformation.paddleWidth(), 20);
        Paddle paddle = new Paddle(size, Color.ORANGE, new Velocity(levelInformation.paddleSpeed(), 0),
                WIDTH, 0, keyboard);
        paddle.addToGame(this);
        paddle.addHitListener(new HitListener<Paddle>() {
            @Override
            public void hitEvent(Paddle beingHit, Ball hitter) {
                currentLives.decrease(1);
                hitter.removeFromGame(GameLevel.this);
                lostlife = true;
            }
        });
        return paddle;
    }

    /**
     * create ball at the middle.
     *
     * @param velocities velocities of the balls
     */
    private void createMiddleBalls(List<Velocity> velocities) {
        for (Velocity velocity : velocities) {
            Ball ball = new Ball(new Point(WIDTH / 2, HEIGHT - (3 * THICKNESS)), 5, Color.WHITE);
            ball.setVelocity(velocity);
            ball.setEnvironment(environment);
            ball.addToGame(this);
            this.ballsCounter.increase(1);
        }
    }

    /**
     * remove the paddle from sprites and collidable lists.
     *
     * @param paddle the paddle to be removed.
     */
    private void removePaddle(Paddle paddle) {
        this.sprites.removeSprites(paddle);
        this.environment.removeCollidable(paddle);
    }

    /**
     * remove balls from the game.
     *
     * @param balls balls to remove
     */
    private void removeBalls(List<Ball> balls) {
        for (Ball b : balls) {
            this.sprites.removeSprites(b);

        }
    }

    /**
     * reset the formation of invaders.
     *
     * @param group of invaders
     */
    private void resetFormation(InvadersGroup group) {
        List<Invader> currents = group.getInvaderList();
        List<Invader> originals = formationGroup.getInvaderList();

        for (Invader curr : currents) {
            for (Invader origin : originals) {
                if (curr.getId() == origin.getId()) {
                    curr.setStartX(origin.getStartX());
                    curr.setStartY(origin.getStartY());
                    curr.setVelocity(startVelocity);
                    curr.setToMoveRight(true);
                    curr.setNeedPositionChange(false);
                }
            }
        }

    }


}
