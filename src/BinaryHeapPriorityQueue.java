public class BinaryHeapPriorityQueue implements PriorityQueue {
    private HeapNode[] Heap;
    private int currentSize;
    private int maxSize;
    private String type; //max or min

    public BinaryHeapPriorityQueue(int maxSize, String typeOfHeap) {
        this.maxSize = maxSize;
        this.currentSize = 0;
        Heap = new HeapNode[this.maxSize + 1];
        if (typeOfHeap.equals("max")) {
            Heap[0] = new HeapNode(Integer.MAX_VALUE); //heaps top node starts at 1
        } else {
            Heap[0] = new HeapNode(Integer.MIN_VALUE); //heaps top node starts at 1
        }
        this.type = typeOfHeap;
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
        HeapNode temp = Heap[firstPosition];
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
        if (this.type.equals("max")) {
            return Math.max(key1, key2);
        } else {
            return Math.min(key1, key2);
        }
    }

    private boolean compareKeysReturnBoolean(int key1, int key2) {
        if (this.type.equals("max")) {
            return key1 > key2;
        } else {
            return key1 < key2;
        }
    }

    private void doubleHeapSize() {
        HeapNode[] doubleSizeHeap = new HeapNode[this.maxSize * 2];
        System.arraycopy(this.Heap, 0, doubleSizeHeap, 0, this.Heap.length);
        this.Heap = doubleSizeHeap.clone();
    }

    private HeapNode peekTop() {
        return Heap[1];
    }

    private int compareKeys(int key1, int key2) {
        if (this.type.equals("max")) {
            return Math.max(key1, key2);
        } else {
            return Math.min(key1, key2);
        }
    }


    @Override
    public void insert(int key, Object object) {
        if (this.currentSize == this.maxSize) {
            doubleHeapSize();
        }
        Heap[++currentSize] = new HeapNode(key, object);
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
    public int extractTop() {
        int top = Heap[1].key;
        Heap[1] = Heap[currentSize--];
        typeHeapify(1);
        return top;
    }

    @Override
    public void toggle() {
        if (this.type.equals("max")) {
            this.type = "min";
            int i = (currentSize - 2) / 2;
            while (i >= 1) {
                typeHeapify(i--);
            }
        } else {
            this.type = "max";
            int i = (currentSize - 2) / 2;
            while (i >= 1) {
                typeHeapify(i--);
            }
        }
    }

    @Override
    public String state() {
        return type.toUpperCase() + " - Heap";
    }

    @Override
    public int size() {
        return this.currentSize;
    }

    private static class HeapNode {
        public Object object;
        public int key;

        public HeapNode(int key) {
            this.object = new Object();
            this.key = key;
        }

        public HeapNode(int key, Object object) {
            this.object = object;
            this.key = key;
        }
    }
}
