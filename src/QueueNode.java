public class QueueNode {
    public Object object;
    public int key;

    public QueueNode(int key) {
        this.object = new Object();
        this.key = key;
    }

    public QueueNode(int key, Object object) {
        this.object = object;
        this.key = key;
    }
}
