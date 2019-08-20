/**
 * Counter class.
 */

public class Counter {

    private int counter;

    /**
     * Counter constructor.
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * Counter constructor.
     * @param num an integer to initialize to the counter
     */
    public Counter(int num) {
        this.counter = num;
    }

    /**
     * add number to current count.
     * @param number the number we add to
     */
    void increase(int number) {
        this.counter = this.counter + number;
    }

    /**
     * subtract number from current count.
     * @param number the number to subtract
     */
    void decrease(int number) {
        this.counter = this.counter - number;
    }

    /**
     *
     * @return counter
     */
    int getValue() {
        return this.counter;
    }
}
