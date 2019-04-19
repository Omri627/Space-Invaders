package spaceinvaders;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * HighScoresTable will manage a table of size high-scores.
 */
public class HighScoresTable implements Serializable {
    private int size;
    private List<ScoreInfo> highScoresList;
    public static final String SCORE_TABLE_NAME = "highscores";
    public static final int MAX_SIZE = 5;

    /**
     * Create an empty high-scores table with the specified size.
     * The size means that the table holds up to size top scores.
     *
     * @param size of the the table
     */
    public HighScoresTable(int size) {
        this.size = size;
        highScoresList = new ArrayList<>(MAX_SIZE);
    }

    /**
     * Add a high-score.
     *
     * @param score the score to add
     */
    public void add(ScoreInfo score) {
        //if the current table is full
        if (highScoresList.size() >= MAX_SIZE) {
            //add the new score to tempArray
            List<ScoreInfo> tempArray = new ArrayList<>(highScoresList);
            tempArray.add(score);
            //sort the temp array
            Collections.sort(tempArray);
            //copy only the biggest score to the current array
            highScoresList.clear();
            for (int i = 0; i < MAX_SIZE; i++) {
                highScoresList.add(tempArray.get(i));
            }
        } else {
            highScoresList.add(score);
        }
        //sort the current array
        Collections.sort(highScoresList);
    }

    /**
     * Return table size.
     *
     * @return size of the table
     */
    public int size() {
        return highScoresList.size();
    }

    /**
     * Return the current high scores.
     * The list is sorted such that the highest scores come first.
     *
     * @return list of scoreInfo
     */
    public List<ScoreInfo> getHighScores() {
        return highScoresList;
    }

    /**
     * return the rank of the current score.
     * Rank 1 means the score will be highest on the list.
     * Rank `size` means the score will be lowest.
     * Rank > `size` means the score is too low and will not be added to the list.
     *
     * @param score score to check the rank
     * @return the rank of the score
     */
    public int getRank(int score) {
        if (!highScoresList.isEmpty()) {
            for (int i = 0; i < highScoresList.size(); i++) {
                if (score > highScoresList.get(i).getScore()) {
                    return i + 1;
                }
            }
            //there is room for the score no matter what the score
            if (highScoresList.size() < MAX_SIZE) {
                return highScoresList.size() + 1;
            }
            return MAX_SIZE + 1;
        } else {
            //table is empty so score is number one
            return 1;
        }
    }

    /**
     * Clears the table.
     */
    public void clear() {
        highScoresList.clear();
    }

    /**
     * Load table data from file.
     * Current table data is cleared.
     *
     * @param filename the file name
     * @throws IOException IOException
     */
    public void load(File filename) throws IOException {
        ObjectInputStream is = null;
        try {
            is = new ObjectInputStream(new FileInputStream(filename));
            HighScoresTable loadedTable = (HighScoresTable) is.readObject();
            highScoresList = loadedTable.getHighScores();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    /**
     * Save table data to the specified file.
     *
     * @param filename the file name
     * @throws IOException IOException
     */
    public void save(File filename) throws IOException {
        ObjectOutputStream os = null;
        try {
            os = new ObjectOutputStream(new FileOutputStream(filename));
            os.writeObject(this);
        } finally {
            if (os != null) {
                os.close();
            }
        }
    }

    /**
     * Read a table from file and return it.
     * If the file does not exist, or there is a problem with
     * reading it, an empty table is returned.
     *
     * @param filename the file name
     * @return HighScoresTable
     * @throws IOException exception
     */
    public static HighScoresTable loadFromFile(File filename) throws IOException {
        ObjectInputStream is = null;
        try {
            is = new ObjectInputStream(new FileInputStream(filename));
            HighScoresTable loadedTable = (HighScoresTable) is.readObject();
            return loadedTable;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Can't find class.", e);
        } catch (FileNotFoundException e) {
            return new HighScoresTable(MAX_SIZE);
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }


}
