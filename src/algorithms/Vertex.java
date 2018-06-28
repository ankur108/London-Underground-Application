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

import java.util.ArrayList;


public class Vertex {

    private ArrayList<Edge> neighborhood;
    private String label;
    
    
    // label is associated with a specific vertex. 
    public Vertex(String label){
        this.label = label;
        this.neighborhood = new ArrayList<Edge>();
    }
    
    //The method is used to get a String object representing the value of the object
    public String toString(){
        return "" + label;
    }
    
    
    
    // returns the label as a string
    public String getLabel(){
        return this.label;
    }
    
    
    
    //returns the hash code of a vertex's label
    public int hashCode(){
        return this.label.hashCode();
    }
    
    
    
    
    
    // 'Other' is used for comparison
    // returns true only if the 'other' vertex is within the 'neighborhood'
    public boolean containsAdjacent(Edge other){
        return this.neighborhood.contains(other);
    }
    
    
    //index refers to the edge to retrieve
    // returns the specific edge within the neighborhood
    // the method gets an adjacent edge
    public Edge getAdjacent(int index){
        return this.neighborhood.get(index);
    }
    
    
    // e refers to a specific a specific edge
    // the method removes an adjacent edge from 'neighborhood'
    public void removeAdjacent(Edge e){
        this.neighborhood.remove(e);
    }
    
    // index refers to the edge that is to be removed from the 'neighborhood'
    // the method removes a specific, indexed, edge from 'neighborhood'
    Edge removeAdjacent(int index){
        return this.neighborhood.remove(index);
    }
    
    
    
    
    
    // method gets the number of a vertex's neighbours as an integer
    public int getAdjacentCount(){
        return this.neighborhood.size();
    }
    
     
    // The method adds an edge to the neighborhood if the edge is not already present.
    public void addAdjacent(Edge edge){
        if(this.neighborhood.contains(edge)){
            return;
        }
        
        this.neighborhood.add(edge);
    }

   
    // 'Other' is used for comparison
    // method returns true only if other instanceof Vertex and the two Vertex objects have the same label
    public boolean equals(Object other){
        if(!(other instanceof Vertex)){
            return false;
        }
        
        Vertex v = (Vertex)other;
        return this.label.equals(v.label);
    }
    
    
    //Method returns the <edge> arrayList of the neighboorhood.
    public ArrayList<Edge> getNeighbors(){
        return new ArrayList<Edge>(this.neighborhood);
    }
    
}
