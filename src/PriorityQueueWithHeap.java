public class PriorityQueueWithHeap {
    private HeapNode[] Heap;
    private int currentSize;
    private int maxSize;
    private String type; //max or min

    public PriorityQueueWithHeap(int maxSize, String typeOfHeap) {
        this.maxSize = maxSize;
        this.currentSize = 0;
        Heap = new HeapNode[this.maxSize + 1];
        if(typeOfHeap.equals("max")) {
            Heap[0] = new HeapNode(Integer.MAX_VALUE); //heaps top node starts at 1
        }else {
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

    private int compareKeysReturnKey(int key1, int key2){
        if(this.type.equals("max")){
            return Math.max(key1,key2);
        }else {
            return Math.min(key1,key2);
        }
    }

    private boolean compareKeysReturnBoolean(int key1, int key2){
        if(this.type.equals("max")){
            return key1 > key2;
        }else {
            return key1 < key2;
        }
    }

    private void doubleHeapSize(){
        HeapNode [] doubleSizeHeap = new HeapNode[this.maxSize*2];
        System.arraycopy(this.Heap, 0, doubleSizeHeap, 0, this.Heap.length);
        this.Heap = doubleSizeHeap.clone();
    }

    public void insert(int key, Object object) {
        if(this.currentSize == this.maxSize) {doubleHeapSize();}
        Heap[++currentSize] = new HeapNode(key,object);
        int currentSizeIndex = this.currentSize;
        while (compareKeysReturnBoolean(Heap[currentSizeIndex].key, Heap[parent(currentSizeIndex)].key)) {
            swapPosition(currentSizeIndex, parent(currentSizeIndex));
            currentSizeIndex = parent(currentSizeIndex);
        }
    }

    public void printHeap() {
        for (int i = 1; i <= currentSize / 2; i++) {
            System.out.print(" PARENT : " + Heap[i].object + " LEFT CHILD : " +
                    Heap[2 * i].object + " RIGHT CHILD :" + Heap[2 * i + 1].object);
            System.out.println();
        }
    }

    public int extractTop() {
        int top = Heap[1].key;
        Heap[1] = Heap[currentSize--];
        typeHeapify(1);
        return top;
    }

    public HeapNode peekTop(){
        return Heap[1];
    }

    public int compareKeys(int key1, int key2){
        if(this.type.equals("max")){
            return Math.max(key1,key2);
        }else {
            return Math.min(key1,key2);
        }
    }

    public void toggle(){
        if (this.type.equals("max")){
            this.type = "min";
            int i = (currentSize - 2) / 2;
            while (i >= 1) {
                typeHeapify(i--);
            }
        }else {
            this.type = "max";
            int i = (currentSize - 2) / 2;
            while (i >= 1) {
                typeHeapify(i--);
            }
            typeHeapify(1);
        }
    }

    //teacher requirement
    public String state(){
        return type.toUpperCase()+ " - Heap";
    }

    //teacher requirement
    public int size(){
        return this.currentSize;
    }

    public class HeapNode {
            public Object object;
            public int key;

          public HeapNode(int key){
              this.object = new Object();
              this.key = key;
          }

          public HeapNode(int key, Object object){
              this.object = object;
              this.key = key;
          }
    }

    public static void main(String[] arg) {
        System.out.println("The Max Heap is ");
        PriorityQueueWithHeap maxHeap = new PriorityQueueWithHeap(15, "max");
        maxHeap.insert(5, "5");
        maxHeap.insert(3, "3");
        maxHeap.insert(17, "17");
        maxHeap.insert(10, "10");
        maxHeap.insert(84, "84");
        maxHeap.insert(34, "34");
        maxHeap.insert(6, "6");
        maxHeap.insert(22, "22");
        maxHeap.insert(9, "9");
        maxHeap.printHeap();
        System.out.println("Original heap: ");
        System.out.println("The top value is " + maxHeap.extractTop());
        System.out.println("The top value is " + maxHeap.extractTop());
        System.out.println("The top value is " + maxHeap.peekTop().key);
        System.out.println("The top value is " + maxHeap.peekTop().key);
        System.out.println("Toggle: ");
        maxHeap.toggle();
        System.out.println("The top value is " + maxHeap.extractTop());
        System.out.println("The top value is " + maxHeap.extractTop());
        System.out.println("The top value is " + maxHeap.peekTop().key);
        System.out.println("The top value is " + maxHeap.peekTop().key);
        System.out.println("Toggle: ");
        maxHeap.toggle();
        System.out.println("The top value is " + maxHeap.extractTop());
        System.out.println("The top value is " + maxHeap.extractTop());
        System.out.println("The top value is " + maxHeap.peekTop().key);
        System.out.println("The top value is " + maxHeap.peekTop().key);

    }
}
