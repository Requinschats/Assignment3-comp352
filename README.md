#Programming Question

###Pseudo Code:

Note: to facilitate child/parent and level requests, the heap array implementation starts at index 1 for root node.
       node of index 0 is either smallest or largest integer

-  toggle: (array) 
       heapSize := size(array); 
       for i := floor(heapSize/2) down to 1 
           do typeHeapify(A, i); 
       end for 
   END
   
- typeHeapify: typeHeapify(A, i) {
                  left <- left(i)
                  right <- right(i)
                  if (le compareKeys heapSize) and (A[left] compareKeys A[i])
                     meetsTestNode <- le
                  else
                     meetsTestNode <- i 
                  if (ri compareKeys heapSize) and (A[right] compareKeys A[largest])
                     meetsTestNode <- ri
                  if (compareKeys != i) {
                     exchange A[i] <-> A[largest]
                     typeHeapify(A, meetsTestNode)
                  }
               }

###Time complexities:

- Toggle: This method is the equivalent of building a heap of the desired type. For each inserted value (n times), an O(log n) complexity method is called, typeHeapify.
          Hence, it would be tempting to evaluate to overall time complexity as O(n log n). Though, it is to be noted that the loop goes from the 
          index of the last internal node with height 1 to the index of the root with height log(n). The overall complexity becomes O(n). 
          
- As suggested above, typeHeapify is of a log(n) time complexity. In the worst case scenario, each constant time recursive call to the downward subtree can be done log(n) times, the equivalent of the number of levels.  
          
- SwitchToMax & SwitchToMin: In the best case scenario, these methods are applied on a heap that is already of the desired type. 
                             In that case, they would be of constant time O(1). 
                             On the other hand, in the worst case scenario, they have to be converted to an heap of the opposite type.
                             In that case, these two methods are the equivalent of a toggle(). Therefore, they are of O(n) type. 

- Insert: The while loop swapping parent child positions until the heap condition is met, represents in the worst scenario the number of levels in the heap (leaf to root node).
          Therefore, the time complexity of this algorithm is O(log n).


c) look at Tests.main and tests