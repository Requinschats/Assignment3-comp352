public class QueueNode {
    public Object value;
    public int key;

    public QueueNode(int key) {
        this.value = new Object();
        this.key = key;
    }

    public QueueNode(int key, Object value) {
        this.value = value;
        this.key = key;
    }

    public QueueNode (QueueNode queueNode){
        this.key = queueNode.key;
        this.value = queueNode.value;
    }
}
