public interface PriorityQueue {
    void insert(int key, Object object);

    void printHeap();

    int extractTop();

    void toggle();

    String state();

    int size();
}
