import biuoop.DrawSurface;

import java.awt.Color;

/**
 * FinalFourBackground class.
 */
public class FinalFourBackground implements Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d the draw surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(82, 228, 228));
        d.fillRectangle(0, 0, 800, 600);
        drawCloud(d, 0, 0);
        drawCloud(d, 500, -50);
    }

    /**
     * notify the sprite that time has passed.
     *
     * @param dt specifies the amount of seconds passed since the last call
     */
    @Override
    public void timePassed(double dt) {
    }

    /**
     *
     * @param d Draw Surface
     * @param w wight
     * @param h height
     */
    public void drawCloud(DrawSurface d, int w, int h) {

        d.setColor(Color.white);
        for (int i = 0; i < 10; i++) {
            d.drawLine(100 + i * 10 + w, 400 + h, 80 + i * 10 + w, 600);
        }

        d.setColor(Color.black);
        d.fillCircle(107 + w, 400 + h, 15);
        d.fillCircle(160 + w, 409 + h, 20);
        d.fillCircle(140 + w, 416 + h, 15);
        d.fillCircle(119 + w, 415 + h, 15);
        d.fillCircle(170 + w, 411 + h, 15);
        d.fillCircle(180 + w, 416 + h, 15);
        d.fillCircle(190 + w, 416 + h, 10);

        d.setColor(new Color(173, 176, 176));
        d.fillCircle(108 + w, 400 + h, 15);
        d.fillCircle(160 + w, 408 + h, 20);
        d.fillCircle(140 + w, 415 + h, 15);
        d.fillCircle(120 + w, 415 + h, 15);
        d.fillCircle(170 + w, 410 + h, 15);
        d.fillCircle(180 + w, 415 + h, 15);
        d.fillCircle(190 + w, 415 + h, 10);

        d.setColor(Color.black);
        d.fillCircle(124 + w, 385 + h, 20);
        d.fillCircle(148 + w, 391 + h, 20);
        d.fillCircle(125 + w, 386 + h, 20);
        d.fillCircle(145 + w, 379 + h, 22);
        d.fillCircle(177 + w, 389 + h, 25);
        d.fillCircle(197 + w, 392 + h, 15);
        d.fillCircle(196 + w, 401 + h, 15);


        d.setColor(new Color(196, 196, 196));
        d.fillCircle(125 + w, 385 + h, 20);
        d.fillCircle(145 + w, 380 + h, 22);
        d.fillCircle(145 + w, 385 + h, 22);
        d.fillCircle(148 + w, 390 + h, 20);
        d.fillCircle(180 + w, 386 + h, 20);
        d.fillCircle(177 + w, 390 + h, 25);
        d.fillCircle(197 + w, 393 + h, 15);
        d.fillCircle(196 + w, 400 + h, 15);

        }

}