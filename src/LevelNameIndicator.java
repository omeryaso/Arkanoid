import biuoop.DrawSurface;
import java.awt.Color;

/**
 * LevelNameIndicator class.
 */
public class LevelNameIndicator implements Sprite {

    private String string;

    /**
     * levelNameIndicator constructor.
     * @param s String
     */
    public LevelNameIndicator(String s) {
        this.string = s;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d the draw surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.drawText(570, 15, "Level Name: " + this.string, 14);
    }

    /**
     * notify the sprite that time has passed.
     * @param dt specifies the amount of seconds passed since the last call
     */
    @Override
    public void timePassed(double dt) {
    }
}
