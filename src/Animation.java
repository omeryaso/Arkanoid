import biuoop.DrawSurface;

/**
 * Animation interface.
 */
public interface Animation {

    /**
     *
     * @param d Draw surface.
     * @param dt specifies the amount of seconds passed since the last call.
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     *
     * @return return if we should stop or not.
     */
    boolean shouldStop();
}
