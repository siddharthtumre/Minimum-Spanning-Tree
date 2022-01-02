package org.iitd.ell781;

import java.util.Objects;

//This Class is used to represent an edge
//It Has 3 members - source node, destination node and edge weight
public class Edge {
    private final Vertex source;
    private final Vertex destination;
    private final double weight;

    public Edge(Vertex source, Vertex destination, double weight){
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return source.equals(edge.source) && destination.equals(edge.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, destination);
    }

    public Vertex getSource() {
        return this.source;
    }

    public Vertex getDestination() {
        return this.destination;
    }

    public double getWeight() {
        return this.weight;
    }
}
