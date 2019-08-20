import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) secods, before
 * it is replaced with the next one.
 */

public class CountdownAnimation implements Animation {

    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private Counter numLeft;

    /**
     *
     * @param numOfSeconds number Of Seconds until start
     * @param countFrom number to count From
     * @param gameScreen the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {

        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.numLeft = new Counter(countFrom);
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {

        this.gameScreen.drawAllOn(d);
        long startTime = System.currentTimeMillis(); // timing
        if (this.numLeft.getValue() >= 0) {
            d.setColor(Color.white);
            d.drawText(d.getWidth() / 2 - 15, d.getHeight() / 2, Integer.toString(this.numLeft.getValue()), 60);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = (long) this.numOfSeconds / this.countFrom * 1000;
            while (milliSecondLeftToSleep >= (usedTime)) {
                usedTime = (System.currentTimeMillis() - startTime);
            }
            this.numLeft.decrease(1);
        } else {
            this.stop = true;
        }

    }

    /**
     *
     * @return true if we should stop, false otherwise
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
