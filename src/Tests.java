public class Tests {
    public static void main(String[] arg) {
        System.out.println("The Original Heap is ");
        PriorityQueue queue = new BinaryHeapPriorityQueue(15, BinaryHeapPriorityQueue.HEAP_TYPE.MIN);
        queue.insert(5, "5");
        queue.insert(3, "3");
        queue.insert(17, "17");
        queue.insert(10, "10");
        queue.insert(84, "84");
        queue.insert(34, "34");
        queue.insert(6, "6");
        queue.insert(22, "22");
        queue.insert(9, "9");
        queue.printHeap();
        System.out.println("Extract top: ");
        queue.remove();
        queue.printHeap();
        System.out.println("Toggle: ");
        queue.toggle();
        queue.printHeap();
        System.out.println("Insert a 1 and a 2");
        queue.insert(1, "1");
        queue.insert(2, "2");
        queue.printHeap();
        System.out.println("Another toggle");
        queue.toggle();
        queue.printHeap();
    }
}
