/**
 * BlockCreator interface.
 */
public interface BlockCreator {

    /**
     *
     * @param xpos x position.
     * @param ypos y position.
     * @return a block at the specified location
     */
    Block create(int xpos, int ypos);
}