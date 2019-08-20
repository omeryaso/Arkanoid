import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * DirectHit class.
 */
public class DirectHit implements LevelInformation {

    /**
     * @return number Of Balls
     */
    @Override
    public int numberOfBalls() {
        return 1;
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
        vList.add(Velocity.fromAngleAndSpeed(0, 20));
        return vList;
    }

    /**
     * @return paddle Speed
     */
    @Override
    public int paddleSpeed() {
        return 12;
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
        return "Direct Hit";
    }

    /**
     * @return get Background
     */
    @Override
    public Sprite getBackground() {
        return new DirectHitBackground();
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
        blocks.add(new Block(new Point(390, 140), 20, 20, Color.red, 1));
        return blocks;
    }

    /**
     * Number of levels that should be removed
     * before the level is considered to be "cleared".
     * Theis number should be <= blocks.size();
     *
     * @return Number of levels that should be removed
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
