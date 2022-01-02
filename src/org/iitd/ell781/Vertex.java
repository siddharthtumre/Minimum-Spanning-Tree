package org.iitd.ell781;

import java.util.Objects;

//This Class represents a vertex of the graph
//It has 2 members - label and parent;
//parent is used for implementing prims algorithm
//label is used to identify the vertex;
public class Vertex {

    private final String label;
    private Vertex parent;

    public Vertex(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Vertex vertex = (Vertex) o;
        return label.equals(vertex.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }

    public String getLabel() {
        return this.label;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
    }

    public Vertex getParent() {
        return this.parent;
    }
}
