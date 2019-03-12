public interface PriorityQueue {
    void insert(int key, Object object);

    void printHeap();

    boolean isEmpty();

    QueueNode remove();

    void toggle();

    String state();

    int size();

    void switchToMin();

    void switchToMax();

    QueueNode top();
}
