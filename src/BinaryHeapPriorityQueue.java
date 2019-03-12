public class BinaryHeapPriorityQueue implements PriorityQueue {
    public enum HEAP_TYPE {MIN, MAX};

    private static HEAP_TYPE DEFAULT_HEAP_TYPE = HEAP_TYPE.MAX;
    private static final int DEFAULT_SIZE = 16;

    private QueueNode[] Heap;
    private int currentSize;
    private int maxSize;
    private HEAP_TYPE heapType;

    public BinaryHeapPriorityQueue(int maxSize, HEAP_TYPE heapType) {
        this.maxSize = maxSize;
        this.currentSize = 0;
        Heap = new QueueNode[this.maxSize + 1];
        if (heapType == HEAP_TYPE.MAX) {
            Heap[0] = new QueueNode(Integer.MAX_VALUE); //heaps top node starts at 1
        } else {
            Heap[0] = new QueueNode(Integer.MIN_VALUE); //heaps top node starts at 1
        }
        this.heapType = heapType;
    }

    public BinaryHeapPriorityQueue(HEAP_TYPE heapType) {
        this(DEFAULT_SIZE, heapType);
    }

    public BinaryHeapPriorityQueue() {
        this(DEFAULT_SIZE, DEFAULT_HEAP_TYPE);
    }

    private int parent(int positionIndex) {
        return positionIndex / 2;
    }

    private int leftChild(int positionIndex) {
        return (2 * positionIndex);
    }

    private int rightChild(int positionIndex) {
        return (2 * positionIndex) + 1;
    }

    private boolean isLeaf(int positionIndex) {
        return positionIndex >= (currentSize / 2) && positionIndex <= currentSize;
    }

    private void swapPosition(int firstPosition, int secondPosition) {
        QueueNode temp = Heap[firstPosition];
        Heap[firstPosition] = Heap[secondPosition];
        Heap[secondPosition] = temp;
    }

    private void typeHeapify(int startingPositionIndex) {
        if (!isLeaf(startingPositionIndex)) {
            if (!compareKeysReturnBoolean(Heap[startingPositionIndex].key, Heap[leftChild(startingPositionIndex)].key)
                    || !compareKeysReturnBoolean(Heap[startingPositionIndex].key, Heap[rightChild(startingPositionIndex)].key)) {
                if (compareKeysReturnBoolean(Heap[leftChild(startingPositionIndex)].key, Heap[rightChild(startingPositionIndex)].key)) {
                    swapPosition(startingPositionIndex, leftChild(startingPositionIndex));
                    typeHeapify(leftChild(startingPositionIndex));
                } else {
                    swapPosition(startingPositionIndex, rightChild(startingPositionIndex));
                    typeHeapify(rightChild(startingPositionIndex));
                }
            }
        }
    }

    private int compareKeysReturnKey(int key1, int key2) {
        if (heapType == HEAP_TYPE.MAX) {
            return Math.max(key1, key2);
        } else {
            return Math.min(key1, key2);
        }
    }

    private boolean compareKeysReturnBoolean(int key1, int key2) {
        if (heapType == HEAP_TYPE.MAX) {
            return key1 > key2;
        } else {
            return key1 < key2;
        }
    }

    private void doubleHeapSize() {
        QueueNode[] doubleSizeHeap = new QueueNode[this.maxSize * 2];
        System.arraycopy(this.Heap, 0, doubleSizeHeap, 0, this.Heap.length);
        this.Heap = doubleSizeHeap.clone();
    }

    private QueueNode top() {
        return Heap[1];
    }

    private int compareKeys(int key1, int key2) {
        if (heapType == HEAP_TYPE.MAX) {
            return Math.max(key1, key2);
        } else {
            return Math.min(key1, key2);
        }
    }


    @Override
    public void insert(int key, Object value) {
        if (this.currentSize == this.maxSize) {
            doubleHeapSize();
        }
        Heap[++currentSize] = new QueueNode(key, value);
        int currentSizeIndex = this.currentSize;
        while (compareKeysReturnBoolean(Heap[currentSizeIndex].key, Heap[parent(currentSizeIndex)].key)) {
            swapPosition(currentSizeIndex, parent(currentSizeIndex));
            currentSizeIndex = parent(currentSizeIndex);
        }
    }

    @Override
    public void printHeap() {
        int lineSize = 1;
        int printIndex = 1;
        for (int j = 0; j < (Math.ceil(Math.log(this.currentSize) / Math.log(2))); j++) {
            for (int i = 1; i <= lineSize; i++) {
                if (printIndex <= currentSize) {
                    System.out.print(this.Heap[printIndex++].key + " ");
                }
            }
            lineSize = lineSize * 2;
            System.out.println();
        }
    }

    @Override
    public int remove() {
        int top = Heap[1].key;
        Heap[1] = Heap[currentSize--];
        typeHeapify(1);
        return top;
    }

    @Override
    public void toggle() {
        if (heapType == HEAP_TYPE.MAX) {
            this.heapType = HEAP_TYPE.MIN;
            int i = (currentSize - 2) / 2;
            while (i >= 1) {
                typeHeapify(i--);
            }
        } else {
            this.heapType = HEAP_TYPE.MAX;
            int i = (currentSize - 2) / 2;
            while (i >= 1) {
                typeHeapify(i--);
            }
        }
    }

    @Override
    public String state() {
        return heapType.name() + " - Heap";
    }

    @Override
    public int size() {
        return this.currentSize;
    }

    @Override
    public boolean isEmpty() {
        return this.currentSize == 0;
    }

    @Override
    public void switchToMin() {
        throw new RuntimeException("not implemented");
    }

    @Override
    public void switchToMax() {
        throw new RuntimeException("not implemented");
    }

}
