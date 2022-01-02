# Minimum Spanning Tree

This program Computes the Minimum spanning tree for a given graph using 2 algorithms.

- __Prim's Algorithm__
- __Kruskal's Algorithm__

The __./src/org/iitd/ell781__ folder contains all the required _java_ files to run the progrm.

The __./input__ folder contains the input text files for the program.

## How to Run?

__MinimumSpanningTree.java__ is the entry point to the program. In the main method we open the input text file
containing the weights between the vertices by specifying the path to the text file.

`File file = new File("./input/graph1.txt");`

By default, the program takes the text file __graph1.txt__ placed in the __input__ directory in the project directory.

Add a new `<filename>.txt` in the __input__ directory that contains the weights between vertices. And then change the
line `File file = new File("./input/<filename>.txt");` to compute the Minimum spanning tree on the new graph.

### Example of a graph file:

    0 6 1 5 -1 -1
    6 0 5 -1 3 -1
    1 5 0 5 6 4
    5 -1 5 0 -1 2
    -1 3 6 -1 0 6
    -1 -1 4 2 6 0

The value at a particular row *__i__* and column *__j__* represents the weight of edge between vertex *__i__* and
vertex *__j__*.

## References
1. Sumeet Agarwal Lecture notes on [Greedy algorithms and Minimum spanning trees.](https://web.iitd.ac.in/~sumeet/MIT6_046JS15_lec12.pdf)
2. Alfred V. Aho, John E. Hopcroft, and Jeffrey D. Ullman. [Data Structures and Algorithms](https://dl.acm.org/doi/book/10.5555/577958) (AHU). Addison-Wesley, 1983. 
3. Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein. Introduction to Algorithms (CLRS). MIT Press, 3rd Edition, 2009. 
