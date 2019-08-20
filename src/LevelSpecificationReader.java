import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LevelSpecificationReader class.
 */
public class LevelSpecificationReader {

    /**
     * @param reader file to read.
     * @return list of levelInformation objects
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        List<LevelInformation> levelInformationList = new ArrayList<LevelInformation>();
        // read the level file
        BufferedReader is = null;

        try {
            is = new BufferedReader(reader);
            String line = is.readLine();
            while (line != null) {
                Map<String, String> levelInfo = new HashMap<String, String>();
                while (!line.matches("END_LEVEL")) {

                    if (line.startsWith("#") || line.equals("START_LEVEL") || line.equals("")) {
                        line = is.readLine();
                        continue;
                    }
                    if (line.contains(":")) {
                        levelInfo.put(line.split(":")[0], line.split(":")[1]);
                        line = is.readLine();
                    }
                    if (line.matches("START_BLOCKS")) {
                        int i = 0;
                        line = is.readLine();
                        Map<String, String> blockInfo = new HashMap<String, String>();
                        while (!line.matches("END_BLOCKS")) {
                            if (!line.equals("")) {
                                blockInfo.put(i + "line", line);
                                i++;
                            }
                            line = is.readLine();
                        }
                        levelInformationList.add(new GenericLevelInformation(levelInfo, blockInfo));
                        line = is.readLine();
                    }
                }
                line = is.readLine();
            }
        } catch (IOException e) {
            System.out.println("Something went wrong with user input!");
            System.exit(1);
        }
        return levelInformationList;
    }
}