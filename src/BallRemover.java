/**
 * BallRemover class.
 * in charge of removing balls, and updating an availabe-balls counter.
 */

public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     *
     * @param gameLevel the gameLevel of the ball
     * @param removedBalls count the number of blocks that remain.
     */
    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBalls;
    }

    /**
     * Ball that are hit and reach 0 hit-points should be removed
     * from the gameLevel. Remember to remove this listener from the block
     * that is being removed from the gameLevel.
     *
     * @param beingHit the object (block) hows being hit.
     * @param hitter the Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getCollisionRectangle().getUpperLeft().getY() == 599) {
            hitter.removeFromGame(this.gameLevel);
            this.remainingBalls.decrease(1);
        }
    }
}