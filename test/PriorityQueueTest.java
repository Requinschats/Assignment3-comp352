import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class PriorityQueueTest {
    PriorityQueue priorityQueue = new BinaryHeapPriorityQueue(BinaryHeapPriorityQueue.HEAP_TYPE.MAX);

    @Test
    public void test() {
        priorityQueue.insert(1, null);
    }

    @Test
    public void insert_1a3a2a1_topOf3() {
        new ArrayList<Integer>(Arrays.asList(1,3,2,1)).forEach(key -> priorityQueue.insert(key, null));
        assertEquals(3, priorityQueue.top().key);
    }

    @Test
    public void switchToMin_maxQueue_switchesToMin() {
        priorityQueue.insert(1, null);
        priorityQueue.insert(2, null);
        priorityQueue.switchToMin();
        assertEquals(1, priorityQueue.top().key);
    }

    @Test
    public void switchToMax_maxQueue_staysMax() {
        priorityQueue.insert(1, null);
        priorityQueue.insert(2, null);
        priorityQueue.switchToMax();
        assertEquals(2, priorityQueue.top().key);
    }

    @Test
    public void isEmpty_emptyQueue_true() {
        assertTrue(priorityQueue.isEmpty());
    }

    @Test
    public void top_empty_null() {
        assertNull(priorityQueue.top());
    }

    @Test
    public void isEmpty_notEmptyQueue_false() {
        priorityQueue.insert(1, null);
        assertFalse(priorityQueue.isEmpty());
    }

    @Test
    public void remove_oneEntry_returnsEntry() {
        priorityQueue.insert(1, null);
        assertEquals(1, priorityQueue.remove().key);
    }

    @Test
    public void remove_twoEntries_removesTopEntry() {
        priorityQueue.insert(1, null);
        priorityQueue.insert(2, null);
        priorityQueue.remove();
        assertEquals(1, priorityQueue.top().key);
    }

    @Test
    public void insert_1024entries_returnsTop() {
       for (int i = 1; i <= 1024; ++i) {
           priorityQueue.insert(i, null);
       }
       assertEquals(1024, priorityQueue.top().key);
    }
}
