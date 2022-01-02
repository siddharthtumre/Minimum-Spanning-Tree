//PRIORITY QUEUE ADT

package org.iitd.ell781;

import java.util.ArrayList;

//PriorityQueue implementation based on Min-Heap
public class PriorityQueue {

    private final ArrayList<Record> A = new ArrayList<>();      //Stores the heap elements

    //-------------------------------------INSERT-------------------------------------
    //new item is first added to the end of the list
    //then the element is swapped with its parent till its value is less than its parent
    public void insert(Object object, double key) {
        Record record = new Record(object, key);
        A.add(record);
        int index = A.indexOf(record);
        while (index > 0 && A.get(parent(index)).getKey() > A.get(index).getKey()) {
            Record temp = A.get(index);
            A.set(index, A.get(parent(index)));
            A.set(parent(index), temp);
            index = parent(index);
        }
    }

    //-------------------------------------DELETE_MIN-------------------------------------
    //Returns Min element available at the first index
    //Last element is made first element and then using heapify the first element is moved to its right position.
    public Object extractMin() {
        Record minimum = A.get(0);
        A.set(0, A.get(A.size() - 1));
        A.remove(A.size() - 1);
        heapify(0);
        return minimum.getObject();
    }

    //-------------------------------------DECREASE_KEY-------------------------------------
    //Useful in implementing Prim's algorithm.
    //Decreases the key value of the node if a new edge with lesser key value to reach it is found.
    public void decreaseKey(int index, double weight) {
        if (weight > A.get(index).getKey()) {
            System.out.println("New key is greater then current key.");
        }
        A.get(index).setKey(weight);
        while (index > 0 && A.get(parent(index)).getKey() > A.get(index).getKey()) {
            Record temp = A.get(index);
            A.set(index, A.get(parent(index)));
            A.set(parent(index), temp);
            index = parent(index);
        }
    }

    //This method will be used to shift down an element at root to its correct position in the tree
    private void heapify(int index) {
        int l = left(index);
        int r = right(index);
        int smallest;

        if (l < A.size() && A.get(l).getKey() < A.get(index).getKey()) {
            smallest = l;
        } else {
            smallest = index;
        }

        if (r < A.size() && A.get(r).getKey() < A.get(smallest).getKey()) {
            smallest = r;
        }
        if (smallest != index) {
            Record temp = A.get(smallest);
            A.set(smallest, A.get(index));
            A.set(index, temp);
            heapify(smallest);
        }

    }

    private int parent(int index) {
        return (int) Math.floor((index - 1) / 2.0);
    }

    private int right(int index) {
        return ((2 * index) + 2);
    }

    private int left(int index) {
        return ((2 * index) + 1);
    }

    public int size() {
        return A.size();
    }

    public boolean contains(Object v) {         //Used in Prim's algorithm to find if a node already exists in the tree.
        for (Record record : A) {
            if (record.getObject().equals(v)) {
                return true;
            }

        }
        return false;
    }

    public double getKey(Object v) {        //returns the key stored in a record.
        for (Record record :
                A) {
            if (record.getObject().equals(v)) {
                return record.getKey();
            }

        }
        return -1;
    }

    public int indexOf(Object v) {
        for (int i = 0; i < A.size(); i++) {
            if (A.get(i).getObject().equals(v)) {
                return i;
            }
        }
        return -1;
    }

    public double minimumKey() {
        return A.get(0).getKey();
    }

}
