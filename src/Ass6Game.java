import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * Ass6Game class.
 *
 */
public class Ass6Game {
    /**
     * main method to run game levels.
     *
     * @param args levels to run.
     */
    public static void main(String[] args) {

        GUI gui = new GUI("Arkanoid", 800, 600);
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        AnimationRunner ar = new AnimationRunner(gui);
        File fileName = new File("highscores");
        HighScoresTable highScoresTable = new HighScoresTable(7);

        try {
            highScoresTable.load(fileName);
        } catch (IOException e) {
            try {
                highScoresTable.save(fileName);
            } catch (IOException e1) {
                System.out.println("Cannot create or load " + fileName + " file");
            }
        }

        String levelSetsFile = "level_sets.txt";
        if (args.length != 0) {
            levelSetsFile = args[0];
        }

        LevelSetReader levelSetReader = new LevelSetReader();

        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(levelSetsFile);
        if (is == null) {
            System.out.println("Error: could not read file");
            System.exit(1);
        }

        java.io.Reader reader = new InputStreamReader(is);

        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>("Arkanoid", keyboard, ar);
        Menu<Task<Void>> subMenu = levelSetReader.fromReader(reader, keyboard, ar, highScoresTable, gui, fileName);

        menu.addSelection("h", "High Scores", new Task<Void>() {
            @Override
            public Void run() {
                HighScoresAnimation highScores = new HighScoresAnimation(highScoresTable, KeyboardSensor.SPACE_KEY);
                KeyPressStoppableAnimation screen = new KeyPressStoppableAnimation(
                        keyboard, KeyboardSensor.SPACE_KEY, highScores);
                ar.run(screen);
                return null;
            }
        });

        menu.addSelection("s", "New Game", new Task<Void>() {
            @Override
            public Void run() {
                return null;
            }
        });

        menu.addSubMenu("s", "New Game", subMenu);

        menu.addSelection("q", "Quit", new Task<Void>() {
            @Override
            public Void run() {
                System.exit(0);
                gui.close();
                return null; }
        });

        while (true) {
            ar.run(menu);
            // A menu with the selections is displayed.
            // Runs until user presses "a", "b"  or "c"
            Task<Void> task = menu.getStatus();
            task.run();
            menu.resetStop();
        }
    }
}