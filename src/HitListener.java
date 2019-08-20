/**
 * HitListener interface.
 */

public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the object (block) hows being hit.
     * @param hitter the Ball that's doing the hitting
     */
    void hitEvent(Block beingHit, Ball hitter);
}
