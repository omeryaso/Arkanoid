import biuoop.KeyboardSensor;
import java.util.List;

/**
 * GameFlow.
 */
public class GameFlow {

    private AnimationRunner ar;
    private KeyboardSensor ks;
    private ScoreIndicator scoreIndicator;
    private LivesIndicator liveIndicator;
    private ScoreTrackingListener scoreTrackingListener;
    private HighScoresTable highScoresTable;
    /**
     *
     * @param ar animation runner
     * @param ks keyboard
     * @param liveIndicator live indicator
     * @param scoreIndicator score indicator
     * @param scoreTrackingListener score Tracking Listener
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, LivesIndicator liveIndicator, ScoreIndicator scoreIndicator,
                    ScoreTrackingListener scoreTrackingListener) {

        this.ar = ar;
        this.ks = ks;
        this.liveIndicator = liveIndicator;
        this.scoreIndicator = scoreIndicator;
        this.scoreTrackingListener = scoreTrackingListener;
        this.highScoresTable = highScoresTable;
    }

    /**
     *
     * @param levels list of levels to run.
     */
    public void runLevels(List<LevelInformation> levels) {

        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.ks, this.ar, this.liveIndicator, this.scoreIndicator,
                    this.scoreTrackingListener);

            level.initialize();

            while (level.getBlocksLeft() != 0 && this.liveIndicator.getLives().getValue() != 0) {
                level.playOneTurn();
            }
        }

        EndScreen endScreen = new EndScreen(this.ks, this.liveIndicator.getLives().getValue(),
                this.scoreTrackingListener.getCurrentScore().getValue());
        KeyPressStoppableAnimation endScreen1 = new KeyPressStoppableAnimation(this.ks, KeyboardSensor.SPACE_KEY,
                endScreen);
        this.ar.run(endScreen1);

    }
}