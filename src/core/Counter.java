package core;

/**
 * simple class that is used for counting things.
 */
public class Counter {
    private int counts;

    /**
     * constructor that initialize counts to zero.
     */
    public Counter() {
        this.counts = 0;
    }

    /**
     * constructor that initialize counts to specific number.
     * @param num number of counts
     */
    public Counter(int num) {
        this.counts = num;
    }

    /**
     * add number to current count.
     *
     * @param number the number to add
     */
    public void increase(int number) {
        counts += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number the number to subtract
     */
    public void decrease(int number) {
        counts -= number;
        if (counts < 0) {
            counts = 0;
        }
    }

    /**
     * get current count.
     *
     * @return counts
     */
    public int getValue() {
        return counts;
    }
}
