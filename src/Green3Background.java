import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Created by omer on 29/05/2017.
 */
public class Green3Background implements Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d the draw surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(41, 183, 46));
        d.fillRectangle(0, 0, 800, 800);

        d.setColor(Color.GRAY);
        d.fillRectangle(100, 200, 10, 300);
        d.setColor(Color.DARK_GRAY);
        d.fillRectangle(90, 400, 30, 50);
        d.setColor(Color.black);
        d.fillRectangle(60, 450, 90, 150);
        d.setColor(Color.white);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(68 + j * 16, 460 + i * 30, 10, 25);
            }
        }

        d.setColor(new Color(214, 219, 78));
        d.fillCircle(105, 200, 11);

        d.setColor(new Color(243, 65, 11));
        d.fillCircle(105, 200, 8);

        d.setColor(Color.white);
        d.fillCircle(105, 200, 3);

    }

    /**
     * notify the sprite that time has passed.
     * @param dt specifies the amount of seconds passed since the last call
     */
    @Override
    public void timePassed(double dt) {
    }
}
