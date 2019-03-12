import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PriorityQueueTest {
    PriorityQueue priorityQueue = new BinaryHeapPriorityQueue();

    @Test
    public void test() {
        priorityQueue.insert(1, null);
    }

    @Test
    public void switchToMin() {
        priorityQueue.switchToMin();
    }

    @Test
    public void switchToMax() {
        priorityQueue.switchToMax();
    }

    @Test
    public void isEmpty_emptyQueeue_true() {
        assertTrue(priorityQueue.isEmpty());
    }

    @Test
    public void isEmpty_notEmptyQueeue_false() {
        priorityQueue.insert(1, null);
        assertFalse(priorityQueue.isEmpty());
    }
}
