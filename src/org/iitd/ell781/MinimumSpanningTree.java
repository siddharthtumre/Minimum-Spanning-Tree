package org.iitd.ell781;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;

public class MinimumSpanningTree {

    //-------------------------------------MAIN FUNCTION-------------------------------------
    public static void main(String[] args) throws IOException {
        Graph G = new Graph();

        File file = new File("./input/test2.txt");                   //Input graph is stored in graph1.txt as an adjacency matrix
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        ArrayList<ArrayList<String>> input = new ArrayList<>();
        while ((line = bufferedReader.readLine()) != null) {
            ArrayList<String> temp = new ArrayList<>(Arrays.asList(line.split(" ")));
            input.add(temp);
        }

        for (int i = 0; i < input.size(); i++) {
            G.addVertex(new Vertex(String.valueOf(i + 1)));             //add vertices to graph G
        }

        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (Integer.parseInt(input.get(i).get(j)) != -1 && Integer.parseInt(input.get(i).get(j)) != 0) {
                    Edge edge = new Edge(G.getVertices().get(i), G.getVertices().get(j), Integer.parseInt(input.get(i).get(j)));
                    G.addUndirectedEdge(edge);                          // add edge to graph G
                }
            }
        }
        //find runtime of Prim's algorithm
        Instant startTime = java.time.Instant.now();
        Graph primsMST = prims(G);
        Instant endTime = java.time.Instant.now();

        long runtime = (java.time.Duration.between(startTime, endTime).toMillis());
        double totalCost = 0;
        ArrayList<Edge> edges = primsMST.getEdges();

        for (Edge edge : edges) {
            totalCost+=edge.getWeight();        //Find total cost of Prim's algorithm
        }

        //Print output of Prim's algorithm
        System.out.println("Prim’s algorithm MST (total cost : " + totalCost + "; runtime : " + runtime +"ms)");
        for (Edge edge :
                edges) {
            System.out.println("(" + edge.getSource().getLabel() + "," + edge.getDestination().getLabel() + ")");
        }

        //find runtime of Kruskal's algorithm
        startTime = java.time.Instant.now();
        Graph kruskalMST = kruskal(G);
        endTime = java.time.Instant.now();

        runtime = (java.time.Duration.between(startTime, endTime).toMillis());
        totalCost = 0;
        edges = kruskalMST.getEdges();

        for (Edge edge :
                edges) {
            totalCost+=edge.getWeight();            //Find total cost of Kruskal's algorithm
        }

        //Print output of Kruskal's algorithm
        System.out.println("Kruskal’s algorithm MST (total cost : " + totalCost + "; runtime : " + runtime +"ms)");
        for (Edge edge :
                edges) {
            System.out.println("(" + edge.getSource().getLabel() + "," + edge.getDestination().getLabel() + ")");
        }
    }

    //-------------------------------------KRUSKAL'S ALGORITHM-------------------------------------
    private static Graph kruskal(Graph G) {
        Graph MST = new Graph();
        // Cast ArrayList<Vertex> to ArrayList<Object> as MergeFindSet takes any generic object type
        ArrayList<Object> vertices = new ArrayList<>(G.getVertices());
        MergeFindSet components = new MergeFindSet(vertices);

        PriorityQueue priorityQueue = new PriorityQueue();
        for (Edge edge :
                G.getEdges()) {
            priorityQueue.insert(edge, edge.getWeight());
        }

        while (components.size() > 1) {                         //We've to run this loop until we get a single component(tree) instead of a forest.
            Edge edge = (Edge) priorityQueue.extractMin();      //get the edge with minimum weight.
            Vertex u = edge.getSource();
            Vertex v = edge.getDestination();
            MST.addVertex(u);
            MST.addVertex(v);
            if (components.find(u) != components.find(v)) {     //If the vertices are not in same component, then that edge does not form a cycle.
                MST.addUndirectedEdge(edge);                    //Add the edge to MST if the vertices are from different component.
                components.merge(u, v);                         //Merge the two components into one.
            }
        }
        return MST;
    }

    //-------------------------------------PRIM'S ALGORITHM-------------------------------------
    private static Graph prims(Graph G) {
        ArrayList<Vertex> vertices = G.getVertices();
        PriorityQueue priorityQueue = new PriorityQueue();
        Vertex root = vertices.get(0);
        priorityQueue.insert(root, 0);                  //Only root vertex is added to priority queue with key 0 and parent null
        for (int i = 1; i < vertices.size(); i++) {
            //All other vertices are added to priority queue with key infinity and parent null
            priorityQueue.insert(vertices.get(i), Double.POSITIVE_INFINITY);
        }

        Graph MST = new Graph();
        while (priorityQueue.size() != 0) {         //Run the loop until there are no more elements left in priority queue.
            double minimumKey = priorityQueue.minimumKey();
            Vertex u = (Vertex) priorityQueue.extractMin();
            MST.addVertex(u);                       //Extract vertex with minimum key and add it to MST
            if (u.getParent() != null) {
                MST.addUndirectedEdge(new Edge(u.getParent(), u, minimumKey));       //Add edge joining vertex u and its parent to MST
            }
            //If the vertices adjacent to newly added vertex(u) has a key with lesser value than its current value in priority queue
            //Then decrease the key to new key and set the parent of the vertex as u
            for (Vertex v : G.adjacent(u)) {
                if (priorityQueue.contains(v) && G.weight(u, v) < priorityQueue.getKey(v)) {
                    priorityQueue.decreaseKey(priorityQueue.indexOf(v), G.weight(u, v));
                    v.setParent(u);
                }
            }
        }

        return MST;
    }
}
