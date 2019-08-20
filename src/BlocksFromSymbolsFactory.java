import java.util.Map;

/**
 * BlocksFromSymbolsFactory class.
 */
public class BlocksFromSymbolsFactory {

    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;

    /**
     *
     * @param spacerWidths spacer Widths
     * @param blockCreators block Creators
     */
    public BlocksFromSymbolsFactory(Map<String, Integer> spacerWidths, Map<String, BlockCreator> blockCreators) {
        this.spacerWidths = spacerWidths;
        this.blockCreators = blockCreators;
    }

    /**
     * @param s space symbol to validate
     * @return true if 's' is a valid space symbol
     */
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.containsKey(s);
    }

    /**
     *
     * @param s block symbol (string) to validate
     * @return true if 's' is a valid block symbol.
     */
    public boolean isBlockSymbol(String s) {
        return  this.blockCreators.containsKey(s);
    }

    /**
     *
     * @param s string
     * @param xpos block x position
     * @param ypos block y position
     * @return  a block according to the definitions associated with symbol s.
     */
    public Block getBlock(String s, int xpos, int ypos) {
        return this.blockCreators.get(s).create(xpos, ypos);
    }

    /**
     *
     * @param s spacer symbol (string).
     * @return the width in pixels associated with the given spacer-symbol.
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }
}