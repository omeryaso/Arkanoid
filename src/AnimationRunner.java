import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * AnimationRunner.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;


    /**
     *
     * @param gui GUI
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.framesPerSecond = 60;
    }

    /**
     *
     * @param animation animation
     */
    public void run(Animation animation) {

        Sleeper sleeper = new Sleeper();
        int millisecondsPerFrame = this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis();
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d, (double) 1 / this.framesPerSecond);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep =  millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}