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


public class Graph  {
    
    private HashMap<String, Vertex> vertices;
    private HashMap<Integer, Edge> edges;
    
    public Graph(){
        this.vertices = new HashMap<String, Vertex>();
        this.edges = new HashMap<Integer, Edge>();
    }
    
    
    //This constructor accepts an ArrayList<Vertex> and populates the vertices
    // If multiple Vertex objects have the same label,
    // then the last Vertex with the given label is used.
    // vertices refer to the initial Vertices to populate the Graph
    public Graph(ArrayList<Vertex> vertices){
        this.vertices = new HashMap<String, Vertex>();
        this.edges = new HashMap<Integer, Edge>();
        
        for(Vertex v: vertices){
            this.vertices.put(v.getLabel(), v);
        }
        
    }
    
    
    //This method adds am edge between Vertex 1 and vertex 2
    // of weight 1, if no Edge between these Vertices alreadye xists in the Graph.
    // one refers to the first vertex
    // two refers to the second vertex
    // returns true if no Edge relating one and two exists in the Graph
    public boolean addEdge(Vertex one, Vertex two){
        return addEdge(one, two, "none", 0);
    }
    
    
    
    
    // the method accepts two vertices and a weight, adding to the edge
    // one refers to the first vertex in the edge
    // two refers to the second vertex in the edge
    // Line refers to the line id
    // weight refers to the weight of the edge (time)
    // returns true if no Edge already exists in the Graph
    public boolean addEdge(Vertex one, Vertex two, String line, int weight){
        if(one.equals(two)){
            return false;   
        }
       
        //ensures the Edge is not in the Graph
        Edge e = new Edge(one, two, line, weight);
        if(edges.containsKey(e.hashCode())){
            return false;
        }
       
        //and that the Edge isn't already incident to one of the vertices
        else if(one.containsAdjacent(e) || two.containsAdjacent(e)){
            return false;
        }
            
        edges.put(e.hashCode(), e);
        one.addAdjacent(e);
        two.addAdjacent(e);
        return true;
    }
    
    
    // e refers to the edge that the method looks up
    // returns true if the graph contains the edge e
    public boolean containsEdge(Edge e){
        if(e.getOne() == null || e.getTwo() == null){
            return false;
        }
        
        return this.edges.containsKey(e.hashCode());
    }
    
    
   
    
    // the method removes a specific edge from the graph.
    public Edge removeEdge(Edge e){
       e.getOne().removeAdjacent(e);
       e.getTwo().removeAdjacent(e);
       return this.edges.remove(e.hashCode());
    }
    
    
    // the method looks up a vertex and returns true if graph contains the vertex.
    public boolean containsVertex(Vertex vertex){
        return this.vertices.get(vertex.getLabel()) != null;
    }
    
    
    //returns the vertex with a specified label.
    public Vertex getVertex(String label){
        return vertices.get(label);
    }
    
    //the method adds the vertex to the graph
    public boolean addVertex(Vertex vertex, boolean overwriteExisting){
        Vertex current = this.vertices.get(vertex.getLabel());
        if(current != null){
            if(!overwriteExisting){
                return false;
            }
            
            while(current.getAdjacentCount() > 0){
                this.removeEdge(current.getAdjacent(0));
            }
        }
        
        
        vertices.put(vertex.getLabel(), vertex);
        return true;
    }
    
    
    // The method removes a vertex from the graph.
    public Vertex removeVertex(String label){
        Vertex v = vertices.remove(label);
        
        while(v.getAdjacentCount() > 0){
            this.removeEdge(v.getAdjacent((0)));
        }
        
        return v;
    }
    
    
    //returns the unique labels of the graph's vertices
    public Set<String> vertexKeys(){
        return this.vertices.keySet();
    }
    
    
    // returns the edges of the graph.
    public Set<Edge> getEdges(){
        return new HashSet<Edge>(this.edges.values());
    }
    
}
