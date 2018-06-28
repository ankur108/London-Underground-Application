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

public class Edge implements Comparable<Edge> {

    private int time;
    
    private String line;
    private Vertex one, two;
    
    
    // one refers to the first vertex in the edge
    // two refers to the second vertex in the edge
    public Edge(Vertex one, Vertex two){
        this(one, two, "none", 0);
    }
    
    
    // one refers to the first vertex in the edge
    // two refers to the second vertex in the edge
    // Line refers to the line id
    // time refers to time taken from one to two
    public Edge(Vertex one, Vertex two, String line, int time){
        this.one = (one.getLabel().compareTo(two.getLabel()) <= 0) ? one : two;
        this.two = (this.one == one) ? two : one;
        this.line = line;
        this.time = time;
    }
    
    
    
    
    // returns the neighbour of current along the edge
    public Vertex getNeighbor(Vertex current){
        if(!(current.equals(one) || current.equals(two))){
            return null;
        }
        
        return (current.equals(one)) ? two : one;
    }
    
    // gets the first vertex of the edge
    public Vertex getOne(){
        return this.one;
    }
    
    
    // gets the second vertex of the edge
    public Vertex getTwo(){
        return this.two;
    }
    
    
    
    // gets the time of the edge
    public int getTime(){
        return this.time;
    }
    
    //gets the Line for edge
    public String getLine(){
        return this.line;
    }
    
    
    
    // sets the weight of an edge
    public void setTime(int time){
        this.time = time;
    }
    
    
    //compare edges time
    public int compareTo(Edge other){
        return this.time - other.time;
    }
    
    
    // returns a string representation of the edge
    public String toString(){
        return "({" + one + ", " + two + "}, " + line + ":" + time + ")";
    }
    

    // returns the hash code for the edge
    public int hashCode(){
        return (one.getLabel() + two.getLabel()).hashCode(); 
    }
    
    
    // 'Other' is used for comparison
    // returns true if other is an Edge with the same Vertices
    public boolean equals(Object other){
        if(!(other instanceof Edge)){
            return false;
        }
        
        Edge e = (Edge)other;
        
        return e.one.equals(this.one) && e.two.equals(this.two);
    }   

    
}
