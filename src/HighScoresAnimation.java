import biuoop.DrawSurface;
import java.awt.Color;

/**
 * HighScoresAnimation class.
 */
public class HighScoresAnimation implements Animation {

    private HighScoresTable scores;
    private String endKey;

    /**
     *
     * @param scores HighScores Table
     * @param endKey key to end this animation
     */
    public HighScoresAnimation(HighScoresTable scores, String endKey) {
        this.scores = scores;
        this.endKey = endKey;
    }

    /**
     * @param d  Draw surface.
     * @param dt specifies the amount of seconds passed since the last call.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {

        d.setColor(new Color(0, 0, 51));
        d.fillRectangle(0, 0, 800, 600);

        d.setColor(new Color(0, 0, 100));
        d.fillRectangle(0, 60, 800, 400);

        d.setColor(Color.orange);
        d.drawText(250, 50, "High Score", 50);

        d.setColor(Color.orange);
        d.drawText(90, 100, "Player Name", 25);
        d.drawText(390, 100, "Score", 25);;

        d.setColor(Color.white);
        int i = 0;
        for (ScoreInfo score: this.scores.getHighScores()) {
            d.drawText(100, 150 + i, score.getName(), 20);
            d.drawText(400, 150 + i, "" + score.getScore(), 20);
            i += 35;
        }

        d.setColor(Color.GREEN);
        d.drawText(220, 550, "press " + this.endKey + " to Main Menu", 30);

    }

    /**
     * @return return if we should stop or not.
     */
    @Override
    public boolean shouldStop() {
        return false;
    }
}
