public interface PriorityQueue {
    void insert(int key, Object object);

    void printHeap();

    int remove();

    void toggle();

    String state();

    int size();

    boolean isEmpty();

    void switchToMin();

    void switchToMax();

}
