//Merge Find SET ADT

package org.iitd.ell781;

import java.util.*;

//Used in Kruskal's algorithm to find if there is a cycle and merge sets which forms a tree.
public class MergeFindSet {

    private final Map<Integer, ArrayList<Object>> set;

    public MergeFindSet(ArrayList<Object> objects) {
        set = new HashMap<>();
        for (int i = 0; i < objects.size(); i++) {
            set.put(i, new ArrayList<>());      //ArrayList is used to store the components in each set with key i
            ArrayList<Object> temp = set.get(i);
            temp.add(objects.get(i));
        }
    }

    public int size() {
        return set.size();
    }

    //Returns the key of the set which contains the object v
    public int find(Object v) {
        for (int key : set.keySet()) {
            if(set.get(key).contains(v)){
                return key;
            }
        }
        return -1;
    }

    //Merges two sets i.e., adds components in Object(Set) containing u to Object(Set) containing v and delete the Object(Set) containing u.
    public void merge(Object source, Object destination) {
        int componentOfSource = find(source);
        int componentOfDestination = find(destination);
        for (Object object :
                set.get(componentOfSource)) {
            ArrayList<Object> arrayList = set.get(componentOfDestination);
            arrayList.add(object);
        }
        set.remove(componentOfSource);
    }
}
