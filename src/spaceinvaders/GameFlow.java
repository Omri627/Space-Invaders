package spaceinvaders;


import animation.Animation;
import animation.HighScoresAnimation;
import animation.AnimationRunner;
import animation.EndScreen;
import animation.KeypressStoppableAnimation;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import core.Counter;
import core.Velocity;
import levels.InvaderLevel;


import java.io.File;
import java.io.IOException;


/**
 * This class will be in charge of creating the different levels, and moving from one level to the next.
 */
public class GameFlow {
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private int lives;
    private GUI gui;
    private HighScoresTable highScoreTable;
    private InvaderLevel currentLevel;

    /**
     * Constructor.
     *
     * @param gui             GUI
     * @param keyboardSensor  keyboard sensor for the game
     * @param animationRunner AnimationRunner for the game
     * @param lives           number of lives through the game
     * @param scoresTable     the high score table of the game
     * @param currentLevel    the original level to run
     */
    public GameFlow(GUI gui, KeyboardSensor keyboardSensor, AnimationRunner animationRunner, int lives,
                    HighScoresTable scoresTable, InvaderLevel currentLevel) {
        this.keyboardSensor = keyboardSensor;
        this.animationRunner = animationRunner;
        this.lives = lives;
        this.gui = gui;
        this.highScoreTable = scoresTable;
        this.currentLevel = currentLevel;
    }

    /**
     * @param levelInfo y
     */
    public void runLevel(InvaderLevel levelInfo) {
        int i = 2;
        int speed = 70;
        Velocity velocity = new Velocity(speed, 0);
        Counter scoreCounter = new Counter();
        Counter livesCounter = new Counter(lives);
        InvaderLevel originalLevel = new InvaderLevel(currentLevel, 1, velocity);
        GameLevel level = new GameLevel(originalLevel, gui, animationRunner, keyboardSensor, scoreCounter,
                livesCounter, currentLevel.getGroup());
        level.initialize();

        while (livesCounter.getValue() > 0) {
            level.playOneTurn();
            //move to next level
            if (level.getRemainingInvaders() == 0) {
                speed += 10;
                velocity = new Velocity(speed, 0);
                originalLevel = new InvaderLevel(currentLevel, i, velocity);
                i++;
                level = new GameLevel(originalLevel, gui, animationRunner, keyboardSensor, scoreCounter,
                        livesCounter, currentLevel.getGroup());
                level.initialize();
            }

        }

        //check if player enters the high score
        int score = scoreCounter.getValue();
        int rank = highScoreTable.getRank(score);
        if (rank <= HighScoresTable.MAX_SIZE && rank >= 1) {
            //enter the user name and score to the table
            DialogManager dialog = gui.getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "Player");
            highScoreTable.add(new ScoreInfo(name, score));

            //save to the table
            try {
                highScoreTable.save(new File(HighScoresTable.SCORE_TABLE_NAME));
            } catch (IOException e) {
                System.err.println("Failed to save the high scores table");
                e.printStackTrace();
            }

        }
        //end screen
        Animation endScreen = new KeypressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY,
                new EndScreen(scoreCounter, livesCounter));
        Animation highScore = new KeypressStoppableAnimation(keyboardSensor, KeyboardSensor.SPACE_KEY,
                new HighScoresAnimation(highScoreTable));
        animationRunner.run(endScreen);
        animationRunner.run(highScore);

    }

}


