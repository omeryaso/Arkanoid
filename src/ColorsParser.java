import java.awt.Color;
import java.util.Map;
import java.util.TreeMap;

/**
 * ColorsParser class.
 */
public class ColorsParser {

    private Map<String, Color> colors = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    /**
     * Colors Parser.
     */
    public ColorsParser() {
        colors.put("black", Color.black);
        colors.put("blue", Color.blue);
        colors.put("cyan", Color.cyan);
        colors.put("darkGray", Color.darkGray);
        colors.put("gray", Color.gray);
        colors.put("green", Color.green);
        colors.put("lightGray", Color.lightGray);
        colors.put("magenta", Color.magenta);
        colors.put("orange", Color.orange);
        colors.put("pink", Color.pink);
        colors.put("red", Color.red);
        colors.put("white", Color.white);
        colors.put("yellow", Color.yellow);
    }

    /**
     * parse color definition and return the specified color.
     * @param s string color representation.
     * @return the specified color.
     */
    public java.awt.Color colorFromString(String s) {
        if (s.contains("RGB")) {
            return new Color(Integer.parseInt(s.split("\\(")[2].split(",")[0]),
                    Integer.parseInt(s.split("\\(")[2].split(",")[1]),
                    Integer.parseInt(s.split("\\(")[2].split(",")[2].split("\\)")[0]));
        } else if (s.contains("image")) {
            return null;
        } else {
            return colors.get(s.split("\\(")[1].split("\\)")[0]);
        }
    }

}