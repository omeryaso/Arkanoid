import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * EndScreen class.
 */
public class EndScreen implements Animation {

    private KeyboardSensor keyboard;
    private boolean stop;
    private int livesLeft;
    private int score;

    /**
     *
     * @param k KeyboardSensor
     * @param livesLeft lives Left
     * @param score game score
     */
    public EndScreen(KeyboardSensor k, int livesLeft, int score) {
        this.keyboard = k;
        this.livesLeft = livesLeft;
        this.score = score;
        this.stop = false;
    }

    /**
     * @param d  Draw surface.
     * @param dt specifies the amount of seconds passed since the last call.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {

       if (livesLeft == 0) {

           d.setColor(Color.black);
           d.fillRectangle(0, 0, 800, 600);

           d.setColor(Color.white);
           d.drawText(150, 500, "You lose. Your score is " + score, 45);
           d.fillRectangle(250, 100, 300, 250); // body
           d.fillRectangle(200, 150, 50, 100); // hands
           d.fillRectangle(550, 150, 50, 100);
           d.fillRectangle(150, 200, 50, 150);
           d.fillRectangle(600, 200, 50, 150);
           d.fillRectangle(250, 0, 50, 50);   //mahoshim
           d.fillRectangle(300, 50, 50, 50);
           d.fillRectangle(450, 50, 50, 50);
           d.fillRectangle(500, 0, 50, 50);
           d.fillRectangle(300, 350, 80, 50); // legs
           d.fillRectangle(420, 350, 80, 50);

           d.setColor(Color.black);
           d.fillRectangle(300, 300, 200, 50); // mouth
           d.fillRectangle(300, 150, 50, 50);  //eyes
           d.fillRectangle(450, 150, 50, 50);

       } else {

           d.setColor(Color.white);
           d.fillRectangle(0, 0, 800, 600);

           d.setColor(Color.black);
           d.drawText(150, 500, "You Win! Your score is " + score, 45);
           d.fillRectangle(250, 100, 300, 250); // body
           d.fillRectangle(200, 150, 50, 140); // hands
           d.fillRectangle(550, 150, 50, 140);
           d.fillRectangle(150, 50, 50, 150);
           d.fillRectangle(600, 50, 50, 150);
           d.fillRectangle(250, 0, 50, 50);   //mahoshim
           d.fillRectangle(300, 50, 50, 50);
           d.fillRectangle(450, 50, 50, 50);
           d.fillRectangle(500, 0, 50, 50);
           d.fillRectangle(200, 350, 50, 50); // legs
           d.fillRectangle(550, 350, 50, 50);

           d.setColor(Color.white);
           d.fillRectangle(300, 300, 200, 50); // mouth
           d.fillRectangle(300, 150, 50, 50);  //eyes
           d.fillRectangle(450, 150, 50, 50);

       }
    }

    /**
     *
     * @return true if we should Stop false otherwise
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
