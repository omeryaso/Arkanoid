import biuoop.DialogManager;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

/**
 * LevelSetsReader class.
 */
public class LevelSetReader {
    /**
     *
     * @param reader reader.
     * @param keyboard Keyboard Sensor.
     * @param ar Animation Runner.
     * @param highScoresTable HighScoresTable.
     * @param gui GUI.
     * @param fileName file name.
     * @return sub menu.
     */
    public Menu<Task<Void>> fromReader(java.io.Reader reader, KeyboardSensor keyboard, AnimationRunner ar,
                                        HighScoresTable highScoresTable, GUI gui, File fileName) {
        Menu<Task<Void>> subMenu = new Menu<Task<Void>>() {
            private List<Selection<Task<Void>>> menu = new ArrayList<>();
            private Task<Void> pressedKey;
            private Boolean stop = false;

            @Override
            public void addSelection(String key, String message, Task<Void> returnVal) {
                Selection<Task<Void>> selection = new Selection<Task<Void>>(key, message, returnVal);
                menu.add(selection);
            }

            @Override
            public Task<Void> getStatus() {
                return pressedKey;
            }

            @Override
            public void resetStop() {
                stop = false;
            }

            @Override
            public void addSubMenu(String key, String message, Menu<Task<Void>> subMenu) {
                System.out.println("Unsupported");
            }

            @Override
            public void doOneFrame(DrawSurface d, double dt) {
                d.setColor(Color.lightGray);
                d.fillRectangle(0, 0, 800, 600);
                d.setColor(Color.orange);
                d.drawText(40, 80, "Levels Set", 40);
                d.setColor(Color.GREEN);
                int y = 80;
                for (Selection<Task<Void>> selection : menu) {
                    d.drawText(100, y + 50, "(" + selection.getKey() + ")", 20);
                    d.drawText(130, y + 50, selection.getMessage(), 20);
                    y = y + 50;
                }
                for (Selection<Task<Void>> selection : menu) {
                    if (keyboard.isPressed(selection.getKey())) {
                        pressedKey = selection.getReturnVal();
                        stop = true;
                    }
                }
            }

            @Override
            public boolean shouldStop() {
                return this.stop;
            }
        };

        try {
            LineNumberReader is = new LineNumberReader(reader);
            String line = is.readLine();
            while (line != null) {
                if (is.getLineNumber() % 2 != 0) {
                    String key = line.split(":")[0];
                    String message = line.split(":")[1];
                    subMenu.addSelection(key, message, new Task<Void>() {
                        private String file = is.readLine();
                        private List<LevelInformation> levels = null;
                        private LevelSpecificationReader levelSpecificationReader = new LevelSpecificationReader();

                        @Override
                        public Void run() {

                            levels = levelSpecificationReader.fromReader(new InputStreamReader(ClassLoader.
                                    getSystemClassLoader().getResourceAsStream(file)));
                            HighScoresAnimation highScoresAnimation = new HighScoresAnimation(
                                    highScoresTable, KeyboardSensor.SPACE_KEY);
                            Counter score = new Counter();
                            ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(score);
                            ScoreIndicator scoreIndicator = new ScoreIndicator(score);
                            LivesIndicator liveIndicator = new LivesIndicator(new Counter(7));
                            GameFlow game = new GameFlow(ar, keyboard, liveIndicator, scoreIndicator,
                                    scoreTrackingListener);
                            game.runLevels(levels);
                            if (highScoresTable.getRank(score.getValue()) <= 7) {
                                DialogManager dialog = gui.getDialogManager();
                                String name = dialog.showQuestionDialog("Name", "What is your name?",
                                        "Anonymous");
                                highScoresTable.add(new ScoreInfo(name, score.getValue()));
                                try {
                                    highScoresTable.save(fileName);
                                } catch (IOException e) {
                                    System.out.println("saving " + fileName + " file failed");
                                }
                            }
                            KeyPressStoppableAnimation endScreenK = new KeyPressStoppableAnimation(keyboard,
                                            KeyboardSensor.SPACE_KEY, highScoresAnimation);
                            ar.run(endScreenK);
                            return null;
                        }
                    });
                }
                line = is.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error: can't read levelset file");
            System.exit(1);
        }
        return subMenu;
    }
}