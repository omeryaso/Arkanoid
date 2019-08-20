
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * WideEasy Green3.
 */
public class Green3 implements LevelInformation {

    /**
     * @return number Of Balls
     */
    @Override
    public int numberOfBalls() {
        return 2;
    }

    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     *
     * @return initial Ball Velocities
     */
    @Override
    public List<Velocity> initialBallVelocities() {

        List<Velocity> vList = new ArrayList<Velocity>();

        for (int i = 0; i < 2; i++) {
            vList.add(Velocity.fromAngleAndSpeed(-20 + 40 * i, 20));
        }
        return vList;
    }

    /**
     * @return paddle Speed
     */
    @Override
    public int paddleSpeed() {
        return 22;
    }

    /**
     * @return paddle Width
     */
    @Override
    public int paddleWidth() {
        return 80;
    }

    /**
     * @return level Name
     */
    @Override
    public String levelName() {
        return "Green 3";
    }

    /**
     * @return get Background
     */
    @Override
    public Sprite getBackground() {
        return new Green3Background();
    }

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return The Blocks that make up this level
     */
    @Override
    public List<Block> blocks() {

        Color[] colors = {Color.darkGray, Color.RED, Color.YELLOW, Color.BLUE, Color.WHITE };

        List<Block> blocks = new ArrayList<Block>();
        int hits = 2;
        for (int i = 0; i < 5; i++) {
            if (i != 0) {
                hits = 1;
            }
            for (int j = 0; j < 10 - i; j++) {
                blocks.add(new Block(new Point(730 - j * 50, 150 + i * 20), 50, 20,
                        colors[i], hits));
            }
        }
        return blocks;
    }

    /**
     * Number of levels that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     *
     * @return Number of levels that should be removed
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
