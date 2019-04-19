package spaceinvaders;

import java.io.Serializable;

/**
 * this class represent the score information.
 */
public class ScoreInfo implements Comparable<ScoreInfo>, Serializable {
    private String name;
    private int score;

    /**
     * Counstructor.
     * @param name name of the score holder
     * @param score the score
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * get the name of the score holder.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * get the score.
     * @return score
     */
    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(ScoreInfo info) {
        //compere the scores
        if (score == info.getScore()) {
            return 0;
        }
        if (score < info.getScore()) {
            return 1;
        } else {
            return -1;
        }
    }
}
