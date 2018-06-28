/*
________________________________________________
|Comp 1555 - Computer Algorithms & modelling    |
|      London underground route finder          |
|                                               |
|Source code by: Michael Level                  |
|Implementation by: Leonardo Mendes Oliveira,   |
|                   Ankur Chaudhary,            |
|                   Kasparas Kulpavicius        |
|_______________________________________________|
*/
package algorithms;

import java.util.*;
import javafx.util.Pair;

public class RouteFinder {

    private Graph graph;
    private String initialVertex;
    private HashMap<String, Pair<String, Edge>> predecessors;
    private HashMap<String, Integer> distances;
    private PriorityQueue<Vertex> availableVertices;
    private HashSet<Vertex> visitedVertices;

    public RouteFinder(Graph graph, String initialVertexLabel) { // the class uses graph and the initial
        // vertex passed from the gui.java class.
        this.graph = graph;
        Set<String> vertexKeys = this.graph.vertexKeys();

        if (!vertexKeys.contains(initialVertexLabel)) { // throws an error message if the initial vertex was not passed into the class.
            throw new IllegalArgumentException("The graph must contain the initial station.");
        }

        this.initialVertex = initialVertexLabel;
        this.predecessors = new HashMap<String, Pair<String, Edge>>();
        this.distances = new HashMap<String, Integer>();
        this.availableVertices = new PriorityQueue<Vertex>(vertexKeys.size(), new Comparator<Vertex>() {

            public int compare(Vertex one, Vertex two) {
                int weightOne = RouteFinder.this.distances.get(one.getLabel());
                int weightTwo = RouteFinder.this.distances.get(two.getLabel());
                return weightOne - weightTwo;
            }
        });

        this.visitedVertices = new HashSet<Vertex>();

        //for each Vertex in the graph
        //assume it has distance infinity denoted by Integer.MAX_VALUE
        for (String key : vertexKeys) {
            this.predecessors.put(key, null);
            this.distances.put(key, Integer.MAX_VALUE);
        }

        //the distance from the initial vertex to itself is 0
        this.distances.put(initialVertexLabel, 0);

        //and seed initialVertex's neighbors
        Vertex initialVertex = this.graph.getVertex(initialVertexLabel);
        ArrayList<Edge> initialVertexNeighbors = initialVertex.getNeighbors();
        for (Edge e : initialVertexNeighbors) {
            Vertex other = e.getNeighbor(initialVertex);
            this.predecessors.put(other.getLabel(), new Pair<>(initialVertexLabel, e));
            this.distances.put(other.getLabel(), e.getTime());
            this.availableVertices.add(other);
        }

        this.visitedVertices.add(initialVertex);

        //now apply Dijkstra's algorithm to the Graph
        processGraph();

    }

    
// the following method applies Dijkstra's algorithm to the grap using the initial vertex as starting point
// @post The shortest a-b paths as specified by Dijkstra's algorithm and their distances are available
    private void processGraph() {

        //as long as there are Edges to process
        while (this.availableVertices.size() > 0) {

            //pick the cheapest vertex
            Vertex next = this.availableVertices.poll();
            int distanceToNext = this.distances.get(next.getLabel());

            //and for each available neighbor of the chosen vertex
            List<Edge> nextNeighbors = next.getNeighbors();
            for (Edge e : nextNeighbors) {
                Vertex other = e.getNeighbor(next);
                if (this.visitedVertices.contains(other)) {
                    continue;
                }

                //we check if a shorter path exists
                //and update to indicate a new shortest found path
                //in the graph
                int currentWeight = this.distances.get(other.getLabel());
                //+1 minute for waiting at station
                int newWeight = distanceToNext + e.getTime() + 1;

                if (newWeight < currentWeight) {
                    this.predecessors.put(other.getLabel(), new Pair<>(next.getLabel(), e));
                    this.distances.put(other.getLabel(), newWeight);
                    this.availableVertices.remove(other);
                    this.availableVertices.add(other);
                }

            }

            // finally, mark the selected vertex as visited 
            // so we don't revisit it
            this.visitedVertices.add(next);
        }
    }

 
    
    // destinationLabel refers to the Vertex whose shortest path from the initial
    // uses the path linked list function to generate the path starting at departure point
    //all the way to final destination, considering the result of the algorithm which is runned before
    public Path getPathTo(String destinationLabel) {
        Path path = new Path(graph.getVertex(destinationLabel)); // adds a node to the path from the graph.

        while (!destinationLabel.equals(this.initialVertex)) {
            Pair<String, Edge> predecessor = this.predecessors.get(destinationLabel);
            Vertex vertex = graph.getVertex(predecessor.getKey());
            destinationLabel = vertex.getLabel();
            path.addVertex(vertex, predecessor.getValue());
        }
        return path;
    }

}
