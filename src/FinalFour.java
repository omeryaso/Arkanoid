
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * WideEasy FinalFour.
 */
public class FinalFour implements LevelInformation {

    /**
     * @return number Of Balls.
     */
    @Override
    public int numberOfBalls() {
        return 3;
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

        for (int i = 0; i < 3; i++) {
            vList.add(Velocity.fromAngleAndSpeed(-30 + 30 * i, 20));
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
        return "Final Four";
    }

    /**
     * @return get Background
     */
    @Override
    public Sprite getBackground() {
        return new FinalFourBackground();
    }

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return The Blocks that make up this level
     */
    @Override
    public List<Block> blocks() {

        List<Block> blocks = new ArrayList<Block>();
        int hits = 2;

        Color[] colors = {Color.gray, Color.red, Color.yellow, Color.green, Color.white, Color.pink,
                new Color(19, 250, 250)};

        for (int i = 0; i < 7; i++) {
            if (i != 0) {
                hits = 1;
            }
            for (int j = 0; j < 15; j++) {
                blocks.add(new Block(new Point(729 - j * 760 / 15, 150 + i * 20), 760 / 15, 20,
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
        return 105;
    }
}
