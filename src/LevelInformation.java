import java.util.List;

/**
 * LevelInformation.
 */
public interface LevelInformation {

    /**
     * @return number Of Balls.
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     * @return initial Ball Velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     *
     * @return paddle Speed
     */
    int paddleSpeed();

    /**
     *
     * @return paddle Width
     */
    int paddleWidth();


    // the level name will be displayed at the top of the screen.

    /**
     *
     * @return level Name
     */
    String levelName();

    // Returns a sprite with the background of the level

    /**
     *
     * @return get Background
     */
    Sprite getBackground();


    /**
     * The Blocks that make up this level, each block contains
     *  its size, color and location.
     * @return The Blocks that make up this level
     */
    List<Block> blocks();


    /**
     * Number of levels that should be removed
     *  before the level is considered to be "cleared".
     *  Theis number should be <= blocks.size();
     * @return Number of levels that should be removed
     */
    int numberOfBlocksToRemove();
}
