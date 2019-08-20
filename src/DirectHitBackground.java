import biuoop.DrawSurface;

import java.awt.Color;

/**
 * direct Hit Background.
 */
public class DirectHitBackground implements Sprite {

    /**
     * draw the sprite to the screen.
     *
     * @param d the draw surface
     */
    @Override
    public void drawOn(DrawSurface d) {

        d.setColor(Color.BLACK);
        d.fillRectangle(1, 1, 800, 600);

        d.setColor(Color.blue);
        d.drawCircle(400, 150, 50);
        d.drawCircle(400, 150, 80);
        d.drawCircle(400, 150, 110);
        d.drawLine(400, 1, 400, 130);
        d.drawLine(400, 170, 400, 290);
        d.drawLine(260, 150, 380, 150);
        d.drawLine(420, 150, 540, 150);
    }

    /**
     * notify the sprite that time has passed.
     * @param dt specifies the amount of seconds passed since the last call
     */
    @Override
    public void timePassed(double dt) {
    }
}