package utils;


import animation.AnimationRunner;
import animation.HighScoresAnimation;
import animation.KeypressStoppableAnimation;
import spaceinvaders.GameFlow;
import spaceinvaders.GameLevel;
import spaceinvaders.HighScoresTable;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import levels.LevelInformation;
import levels.InvaderLevel;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Create a game object, initializes and runs it.
 */
public class Ass7Game {
    /**
     * main.
     *
     * @param args from commandline.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", GameLevel.WIDTH, GameLevel.HEIGHT);
        List<LevelInformation> levels = new ArrayList<>();

        //read the image
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("resources/enemy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        InvaderLevel originalLvl = new InvaderLevel(image, gui.getDrawSurface().getWidth(), 0, 1);

        //check for arguments
        if (args.length == 0) {
            levels.add(new InvaderLevel(image, gui.getDrawSurface().getWidth(), 0, 1));

        } else {
            for (String level : args) {
                switch (level) {
                    case "1":
                        levels.add(new InvaderLevel(image, gui.getDrawSurface().getWidth(), 0, 1));
                        break;

                    default:

                }
            }
        }

        //load or create the high score table
        HighScoresTable table;
        try {
            table = HighScoresTable.loadFromFile(new File(HighScoresTable.SCORE_TABLE_NAME));
        } catch (IOException e) {
            System.err.println("Failed loading high score table");
            e.printStackTrace(System.err);
            return;
        }

        AnimationRunner runner = new AnimationRunner(gui, 60);
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        GameFlow gameFlow = new GameFlow(gui, gui.getKeyboardSensor(), runner, 3, table, originalLvl);

        //create main menu
        Menu<Task<Void>> mainMenu = new MenuAnimation<Task<Void>>(keyboard, "Arknoid", runner);

        KeypressStoppableAnimation highScore = new KeypressStoppableAnimation(keyboard, KeyboardSensor.SPACE_KEY,
                new HighScoresAnimation(table));

        mainMenu.addSelection("h", "High scores", new ShowHiScoresTask(runner, highScore));
        mainMenu.addSelection("s", "New game", new Task<Void>() {
            @Override
            public Void run() {
                gameFlow.runLevel(originalLvl);
                return null;
            }
        });
        mainMenu.addSelection("q", "Quit game", new Task<Void>() {
            @Override
            public Void run() {
                System.exit(1);
                return null;
            }
        });

        while (true) {
            runner.run(mainMenu);
            Task<Void> task = mainMenu.getStatus();
            task.run();
            mainMenu.reset();
            highScore.reset();
        }

    }
}
