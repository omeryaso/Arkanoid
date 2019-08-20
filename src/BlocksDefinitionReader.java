import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import javax.imageio.ImageIO;

/**
 * BlocksDefinitionReader class.
 */
public class BlocksDefinitionReader {

    /**
     *
     * @param reader reader
     * @return BlocksFromSymbolsFactory.
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {

        Map<String, Integer> spacerWidths = new HashMap<>();
        Map<String, BlockCreator> blockCreators = new HashMap<>();
        Map<String, String> defaults = new HashMap<>();

        try {
            BufferedReader is = new BufferedReader(reader);
            String[] definitions;
            String line = is.readLine();

            while (line != null) {
                if (line.startsWith("#")) {
                    line = is.readLine();
                    continue;
                }
                if (line.startsWith("default")) {
                    definitions = line.split(" ");
                    for (int i = 1; i < definitions.length; i++) {
                        defaults.put(definitions[i].split(":")[0], definitions[i].split(":")[1]);
                    }
                }
                if (line.startsWith("sdef")) {
                    Map<String, String> sdefDefValues = new TreeMap<>();
                    String[] sdefDef = line.split(" ");
                    for (int i = 1; i < sdefDef.length; i++) {
                        sdefDefValues.put(sdefDef[i].split(":")[0], sdefDef[i].split(":")[1]);
                    }
                    sdefDefValues.putAll(defaults);
                    spacerWidths.put(sdefDefValues.get("symbol"), Integer.parseInt(sdefDefValues.get("width")));
                }

                if (line.startsWith("bdef")) {
                    Map<String, String> defValues = new TreeMap<>();
                    defValues.putAll(defaults);
                    String[] bdefDef = line.split(" ");
                    for (int i = 1; i < bdefDef.length; i++) {
                        defValues.put(bdefDef[i].split(":")[0], bdefDef[i].split(":")[1]);
                    }
                    if ((!defValues.containsKey("hit_points")) || (!defValues.containsKey("height"))
                            || (!defValues.containsKey("width"))) {
                        System.out.println("Arguments are missing");
                        System.exit(1);
                    } else {
                        blockCreators.put(defValues.get("symbol"), new BlockCreator() {

                            private int width = Integer.parseInt(defValues.get("width"));
                            private int height = Integer.parseInt(defValues.get("height"));
                            private ColorsParser colorsParser = new ColorsParser();
                            private int hits = Integer.parseInt(defValues.get("hit_points"));
                            private Color stroke = stroke();
                            private Object[] fillKList = fillKList();

                            @Override
                            public Block create(int xpos, int ypos) {
                                return new Block(new Point(xpos, ypos), width, height, stroke, hits, fillKList);
                            }

                            private Color stroke() {
                                Color stroke = null;
                                if (defValues.get("stroke") != null) {
                                    stroke = colorsParser.colorFromString(defValues.get("stroke"));
                                }
                                return stroke;
                            }

                            private Object getFill(String s) {

                                if (s == null) {
                                    return null;
                                }

                                BufferedImage image = null;
                                if (colorsParser.colorFromString(s) == null) {
                                    try {
                                        image = ImageIO.read(ClassLoader.getSystemClassLoader().
                                                getResourceAsStream(s.substring(6, (s.length() - 1))));
                                        return image;
                                    } catch (IOException e) {
                                        System.out.println("Error: failed reading block definitions");
                                        System.exit(1);
                                    }
                                }
                                return colorsParser.colorFromString(s);
                            }

                            private Object[] fillKList() {
                                Object[] fillK = new Object[hits];
                                for (int i = 0; i < fillK.length; i++) {
                                    if (defValues.containsKey("fill-".concat((i + 1) + ""))) {
                                        fillK[i] = getFill(defValues.get("fill-".concat((i + 1) + "")));
                                    } else {
                                        fillK[i] = getFill(defValues.get("fill"));
                                    }
                                }
                                return fillK;
                            }
                        });
                    }
                }
                line = is.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error: failed reading block definitions");
            System.exit(1);
        }
        return new BlocksFromSymbolsFactory(spacerWidths, blockCreators);
    }
}