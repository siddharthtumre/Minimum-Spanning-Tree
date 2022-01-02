package org.iitd.ell781;

import java.util.Objects;

//Record is used in priority queue
//it has 2 members - object and key
//priority queue will use key for its ordering
//in Prims algorithm, object initially will be a vertex with no parent
//in Kruskal algorithm , object will be an edge
public class Record {

    private final Object object;
    private double key;

    public Record(Object object, double key) {
        this.object = object;
        this.key = key;
    }

    public double getKey() {
        return key;
    }

    public Object getObject() {
        return object;
    }

    public void setKey(double newKey) {
        this.key = newKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return object.equals(record.object);
    }

    @Override
    public int hashCode() {
        return Objects.hash(object);
    }
}
