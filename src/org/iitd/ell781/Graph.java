//GRAPH ADT

package org.iitd.ell781;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

//This class represents the graph.
public class Graph {
    private final ArrayList<Vertex> vertices = new ArrayList<>();
    private final ArrayList<Edge> edges = new ArrayList<>();
    private final Map<Vertex, LinkedList<Vertex>> adjacencyList = new HashMap<>();      //To store all nodes adjacent to a vertex.

    public void addVertex(Vertex vertex) {
        if (!checkIfVertexExistsInGraph(vertex)) {      //Vertex added if it is not already added to list of vertices in the graph.
            vertices.add(vertex);
            adjacencyList.put(vertex, new LinkedList<>());
        }
    }

    public void addUndirectedEdge(Edge edge) {
        Vertex source = edge.getSource();
        Vertex destination = edge.getDestination();
        if (checkIfVertexExistsInGraph(source)) {
            if (checkIfVertexExistsInGraph(destination)) {
                if (!checkIfEdgeExistsInGraph(edge)) {
                    //Edge added if the edge doesn't already exist in edges list(graph) and the source and destination vertex exists in the vertices list(graph).
                    LinkedList<Vertex> adjacentVerticesToSource = adjacencyList.get(source);
                    adjacentVerticesToSource.add(destination);
                    LinkedList<Vertex> adjacentVerticesToDestination = adjacencyList.get(destination);
                    adjacentVerticesToDestination.add(source);
                    edges.add(edge);
                }
            }
        }
    }

    private boolean checkIfEdgeExistsInGraph(Edge edge) {                   // Returns true if edge is present in graph
        Edge reverseEdge = new Edge(edge.getDestination(), edge.getSource(), edge.getWeight());
        return edges.contains(edge) || edges.contains(reverseEdge);
    }

    private boolean checkIfVertexExistsInGraph(Vertex vertex) {             // Returns true if vertex is present in graph

        for (Vertex value : vertices) {
            if (value.equals(vertex)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public LinkedList<Vertex> adjacent(Vertex vertex) {             //Returns adjacent vertices of a vertex
        return adjacencyList.get(vertex);
    }

    public double weight(Vertex source, Vertex destination) {       //Returns the weight of the edge joining the given 2 vertices.
        for (Edge edge : edges) {
            if (edge.getSource().equals(source) && edge.getDestination().equals(destination) || edge.getSource().equals(destination) && edge.getDestination().equals(source)) {
                return edge.getWeight();
            }
        }
        return -1;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

}
