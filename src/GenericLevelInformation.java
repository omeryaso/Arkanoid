import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * GenericLevelInformation class.
 */
public class GenericLevelInformation implements LevelInformation {

    private Map<String, String> levelInfo;
    private Map<String, String> blocksInfo;
    private String[] ballVelocities;

    /**
     *
     * @param levelInfo level information
     * @param blocksInfo blocks information
     */
    public GenericLevelInformation(Map<String, String> levelInfo, Map<String, String> blocksInfo) {
        this.levelInfo = levelInfo;
        this.blocksInfo = blocksInfo;
        this.ballVelocities = levelInfo.get("ball_velocities").split(" ");
    }

    /**
     * @return number Of Balls.
     */
    @Override
    public int numberOfBalls() {
        return ballVelocities.length;
    }

    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     *
     * @return initial Ball Velocities
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<Velocity>();
        for (String s : this.ballVelocities) {
            velocities.add(Velocity.fromAngleAndSpeed(
                    Double.parseDouble(s.split(",")[0]),
                    Double.parseDouble(s.split(",")[1])));
        }
        return velocities;
    }

    /**
     * @return paddle Speed
     */
    @Override
    public int paddleSpeed() {
        return Integer.parseInt(levelInfo.get("paddle_speed"));
    }

    /**
     * @return paddle Width
     */
    @Override
    public int paddleWidth() {
        return Integer.parseInt(levelInfo.get("paddle_width"));
    }

    /**
     * @return level Name
     */
    @Override
    public String levelName() {
        return levelInfo.get("level_name");
    }

    /**
     * @return get Background
     */
    @Override
    public Sprite getBackground() {
        BufferedImage backgroundImage = null;
        if (levelInfo.get("background").contains("image")) {
            try {
                backgroundImage = ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(
                        levelInfo.get("background").substring(6, (levelInfo.get("background").length() - 1))));
            } catch (IOException e) {
                System.out.println("Error in level specification image");
            }
        }
        ColorsParser colorsParser = new ColorsParser();
        if (colorsParser.colorFromString(levelInfo.get("background")) != null) {
            return new GenericBackground(colorsParser.colorFromString(levelInfo.get("background")));
        }
        return new GenericBackground(backgroundImage);
    }

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return The Blocks that make up this level
     */
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        String[] blocksLine = new String[blocksInfo.keySet().size()];
        BlocksFromSymbolsFactory blocksFromSymbolsFactory = BlocksDefinitionReader.fromReader(
                new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream(
                        levelInfo.get("block_definitions"))));

        if (ClassLoader.getSystemClassLoader().getResourceAsStream(levelInfo.get("block_definitions")) != null) {
            int y = Integer.parseInt(levelInfo.get("blocks_start_y"));
            for (int i = 0; i < blocksInfo.keySet().size(); i++) {
                int x = Integer.parseInt(levelInfo.get("blocks_start_x"));
                blocksLine[i] = blocksInfo.get(((i) + "").concat("line"));

                for (int j = 0; j < blocksLine[i].length(); j++) {
                    if (blocksFromSymbolsFactory.isBlockSymbol(String.valueOf(blocksLine[i].charAt(j)))) {
                        blocks.add(blocksFromSymbolsFactory.getBlock(String.valueOf(blocksLine[i].charAt(j)), x, y));
                        x = x + (int) blocksFromSymbolsFactory.getBlock(String.valueOf(blocksLine[i].charAt(j)),
                                x, y).getCollisionRectangle().getWidth();
                    }
                    if (blocksFromSymbolsFactory.isSpaceSymbol(String.valueOf(blocksLine[i].charAt(j)))) {
                        x = x + blocksFromSymbolsFactory.getSpaceWidth(String.valueOf(blocksLine[i].charAt(j)));
                    }
                }
                y = y +  (Integer.parseInt(levelInfo.get("row_height")));
            }
        }
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
        return Integer.parseInt(levelInfo.get("num_blocks"));
    }
}