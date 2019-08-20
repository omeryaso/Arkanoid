import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;

/**
 * HighScoresTable class.
 */
 public class HighScoresTable implements Serializable {

    private int size;
    private ArrayList<ScoreInfo> scoresTable;

    /**
     * Create an empty high-scores table with the specified size.
     * @param size the table holds up to size top scores.
     */
    public HighScoresTable(int size) {
        this.size = size;
        this.scoresTable = new ArrayList<ScoreInfo>(size);
    }

    /**
     *
     * @param score Add a high-score.
     */
    public void add(ScoreInfo score) {
        if (this.getRank(score.getScore()) <= this.size) {
            this.scoresTable.add((this.getRank(score.getScore()) - 1), score);
            if (this.scoresTable.size() > this.size) {
                this.scoresTable.remove(this.size);
            }
        }
    }

    /**
     * @return table size.
     */
    public int size() {
        return this.size;
    }

    /**
     *
     * @return the current high scores.
     */
    public List<ScoreInfo> getHighScores() {
     return this.scoresTable;
    }

    /**
     *
     * @param score a score to check its rank in the score table
     * @return the rank of the current score:
     *                       Rank 1 means the score will be highest on the list.
     *                       Rank `size` means the score will be lowest.
     *                       Rank > `size` means the score is too low and will not be added to the list.
     */
    public int getRank(int score) {
        int rank = this.scoresTable.size() + 1;
        if (rank != 1) {
            for (ScoreInfo scoreInfo : this.scoresTable) {
                if (score > scoreInfo.getScore()) {
                    return (this.scoresTable.indexOf(scoreInfo) + 1);
                }
            }
        }
        return rank;
    }

    /**
     * Clears the table.
     */
    public void clear() {
        this.scoresTable.clear();
    }

    /**
     * Load table data from file. Current table data is cleared.
     * @param filename A file to load the table data from.
     * @throws IOException Input/Output exception
     */
    public void load(File filename) throws IOException {

        ObjectInputStream input = null;
        try {
            input = new ObjectInputStream(new FileInputStream(filename));
            Object object = input.readObject();
            if (object instanceof HighScoresTable) {
                List<ScoreInfo> list = ((HighScoresTable) object).getHighScores();
           // if (object instanceof ArrayList) {
                for (Object obj: list) {
                    if (obj instanceof ScoreInfo) {
                        this.scoresTable.add((ScoreInfo) obj);
                    }
                }
            }
            /*if(object instanceof HighScoresTable) {
                List<ScoreInfo> list = ((HighScoresTable) object).getHighScores();

            }*/
        } catch (ClassNotFoundException e) {
            System.out.println("class was not found");
        } finally {
            if (input != null) {
                input.close();
            }
        }
    }

    /**
     * Save table data to the specified file.
     * @param filename Save table data to this file.
     * @throws IOException Input/Output exception
     */
    public void save(File filename) throws IOException {
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(filename));
            outputStream.writeObject(this);
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    /**
     * Read a table from file and return it.
     * If the file does not exist, or there is a problem with
     * reading it, an empty table is returned.
     * @param filename file to read
     * @return Read a table from file and return it. if there is any problem an empty table
     */
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable table = new HighScoresTable(5);
        try {
            table.load(filename);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return table;
    }
}