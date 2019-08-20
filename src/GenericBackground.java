import biuoop.DrawSurface;
import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * GenericBackground class.
 */
public class GenericBackground implements Sprite {

    private BufferedImage image;
    private Boolean isImage;
    private Color color;

    /**
     * @param image background image
     */
    public GenericBackground(BufferedImage image) {
        this.image = image;
        this.isImage = true;
    }

    /**
     *
     * @param color background color
     */
    public GenericBackground(Color color) {
        this.color = color;
        this.isImage = false;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d the draw surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        if (isImage) {
            d.drawImage(0, 0, image);
        } else {
            d.setColor(color);
            d.fillRectangle(0, 0, 800, 600);
        }
    }

    /**
     * notify the sprite that time has passed.
     *
     * @param dt specifies the amount of seconds passed since the last call
     */
    @Override
    public void timePassed(double dt) {
    }
}