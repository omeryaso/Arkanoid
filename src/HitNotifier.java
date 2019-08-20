/**
 * HitNotifier interface.
 */
public interface HitNotifier {

    /**
     * Add hl as a listener to hit events.
     * @param hl Hit Listener
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl A Hit Listener
     */
    void removeHitListener(HitListener hl);
}
