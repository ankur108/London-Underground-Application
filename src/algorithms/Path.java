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
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import javafx.util.Pair;

/**
 * path class which creates the journey path + calculate time taken to travel
 * uses linked list by to output result
 * it works by firstly adding the vertex name 
 * then id of the edge which is later used to retrieve information such as
 * time to next stop and also the line name.
 */
public class Path { 

    List<Pair<Vertex, Edge>> list = new LinkedList<>();
    List<Double> total = new ArrayList();
    List<String> walking = new ArrayList();
    List<Integer> change = new ArrayList();
    int totstops = 0;
    int hour = 0;
    int min = 0;
    
    // Method that adds a new vertex pair
    public Path(Vertex vertex) {
        list.add(new Pair(vertex, null));
    }

    public void addVertex(Vertex vertex, Edge edge) {
        list.add(0, new Pair(vertex, edge));
    }

    //The taken() method calculates total journey time
    public String taken() {
        if (total.isEmpty()) {
            return "0";
        } else {
            int i;
            double sum = 0.0;
            for (i = 0; i < total.size(); i++) {
                sum = sum + total.get(i);
            }
            //it adds the total time between stations
            //then uses totstops (int value) to determine how many stops were made
            //theorically adding 1 minute per stop to total journey time
            sum = sum + totstops;
            if (sum == 1.0) {
                return sum + " minute";
            //this part determines if the time is bigger than 60, if so it triggers a calculation
            //that changes the output to "X hour and Y minutes" instead of only minutes
            } else if (sum >= 60) {
                hour = (int) (sum / 60);
                min = (int) (sum - (hour * 60));
                return hour + " hour and " + min + " minutes";
            //however if less than 60, only shows minutes
            } else {
                hour = 0;
                min = (int) sum;
                return sum + " minutes";
            }            
        }
    }

    //if any walking time has been added
    //this calculates how much it was.
    // Depending on the amount of changes made during the journey
    public double added() {
        double sum = 0;
        if (change.isEmpty()) {
            return 0;
        } else {
            //determines how many minutes are gonna be added
            switch (change.get(0)) {
                case 1:
                    sum = change.get(0) * 2;
                    break;
                case 2: 
                    sum = change.get(0) * 2;
                    break;
                case 3:
                    sum = change.get(0) * 2;
                    break;
                case 4:
                    sum = change.get(0) * 1.5;
                    break;
                case 5:
                    sum = change.get(0) * 1.5;
                    break;
                case 6:
                    sum = change.get(0) * 1.5;
                    break;
                case 7:
                    sum = change.get(0) * 1.0;
                    break;
                case 8:
                    sum = change.get(0) * 1.0;
                    break;
                case 9:
                    sum = change.get(0) * 1.0;
                    break;
                case 10:
                    sum = change.get(0) * 1.0;
                    break;    
                default:
                    break;
                      
            }
        }
        return sum;
    }

    //compare lines entered in walking list,
    //if the last and next are the same, no extra time added
    //if they are different it means a change of line
    //meaning user will need to walk to go to the other line
    public void walk() {
        int t = 0;
        double sum = 0.0;
        //does the comparisson betwen current and next entry 
        for (int i = 0; i < walking.size() - 1; i++) {
            if (walking.get(i) != walking.get(i + 1)) {
                //if different increases t
                t++;
            }
        }
        switch (t) {
            //determines how many minutes are gonna be added
            case 1:
                sum = 2.0 * t;
                total.add(sum);
                break;
            case 2:
                sum = 2.0 * t;
                total.add(sum);
                break;
            case 3:
                sum = 2.0 * t;
                total.add(sum);
                break;
            case 4:
                sum = 1.5 * t;
                total.add(sum);
                break;
            case 5:
                sum = 1.5 * t;
                total.add(sum);
                break;
            case 6:
                sum = 1.5 * t;
                total.add(sum);
                break;
            case 7:
                sum = 1.0 * t;
                total.add(sum);
                break;
            case 8:
                sum = 1.0 * t;
                total.add(sum);
                break;
            case 9:
                sum = 1.0 * t;
                total.add(sum);
                break;
            case 10:
                sum = 1.0 * t;
                total.add(sum);
                break;
            default:
                break;

        }
        //adds the t value (number of line changes) 
        //to change list
        change.add(0, t);
    }

    //examine the list and build the string using the values from the vertex and individual edges
    //it also performs the check on the small checkbox (time) which is used to half bakerloo time as requrested
    public String lineInfo() {
        ListIterator<Pair<Vertex, Edge>> listIterator = list.listIterator();
        StringBuilder stringBuilder = new StringBuilder();
        double time = 0.0;
        double tim;
        while (listIterator.hasNext()) {
            Pair<Vertex, Edge> pair = listIterator.next();
            Vertex vertex = pair.getKey();
            Edge edge = pair.getValue();
            String v = (String) Gui.hours.getSelectedItem();
            int read = Integer.parseInt(v);

            // code block to execute if the user selected specific timeframe for their journey
            //in this case, off peak time
            if (read >= 9 && read <= 15 || read >= 19 && read <= 23) {
                if (listIterator.hasNext()) {
                    stringBuilder.append("<br/>");
                    walking.add(edge.getLine());
                    totstops++;

                    // checks if the user is traveling using bakerloo line or any other specific line
                    // if the user is taking bakerloo during off peak time, it halves the time taken from A to B (via bakerloo only)
                    if (edge.getLine().equals("Bakerloo")) { 
                        //halves bakerloo time due to better signalling during off peak time
                        tim = (double) edge.getTime() / 2;
                        time = time + tim;
                        total.add(time);
                        stringBuilder.append(vertex.getLabel() + " ----- " + "<font color='#8B4513'><b>" + edge.getLine() + "</b></font>" + " ----- " + (double) edge.getTime() + " " + minute(edge));
                        time = 0.0;
                        tim = 0.0;
                    } else {
                        total.add((double) edge.getTime());
                        if (edge.getLine().equals("Victoria")) {
                            stringBuilder.append(vertex.getLabel() + " ----- " + "<font color='#00FFFF'><b>" + edge.getLine() + "</b></font>" + " ----- " + (double) edge.getTime() + " " + minute(edge));
                        }
                        if (edge.getLine().equals("Central")) {
                            stringBuilder.append(vertex.getLabel() + " ----- " + "<font color='Red'><b>" + edge.getLine() + "</b></font>" + " ----- " + (double) edge.getTime() + " " + minute(edge));
                        }
                        if (edge.getLine().equals("Circle")) {
                            stringBuilder.append(vertex.getLabel() + " ----- " + "<font color='#cccc00'><b>" + edge.getLine() + "</b></font>" + " ----- " + (double) edge.getTime() + " " + minute(edge));
                        }
                        if (edge.getLine().equals("District")) {
                            stringBuilder.append(vertex.getLabel() + " ----- " + "<font color='Green'><b>" + edge.getLine() + "</b></font>" + " ----- " + (double) edge.getTime() + " " + minute(edge));
                        }
                        if (edge.getLine().equals("Hammersmith and City")) {
                            stringBuilder.append(vertex.getLabel() + " ----- " + "<font color='#ff00ff'><b>" + edge.getLine() + "</b></font>" + " ----- " + (double) edge.getTime() + " " + minute(edge));
                        }
                        if (edge.getLine().equals("Jubilee")) {
                            stringBuilder.append(vertex.getLabel() + " ----- " + "<font color='#C0C0C0'><b>" + edge.getLine() + "</b></font>" + " ----- " + (double) edge.getTime() + " " + minute(edge));
                        }
                        if (edge.getLine().equals("Metropolitan")) {
                            stringBuilder.append(vertex.getLabel() + " ----- " + "<font color='Purple'><b>" + edge.getLine() + "</b></font>" + " ----- " + (double) edge.getTime() + " " + minute(edge));
                        }
                        if (edge.getLine().equals("Piccadilly")) {
                            stringBuilder.append(vertex.getLabel() + " ----- " + "<font color='Blue'><b>" + edge.getLine() + "</b></font>" + " ----- " + (double) edge.getTime() + " " + minute(edge));
                        }
                        if (edge.getLine().equals("Waterloo & City")) {
                            stringBuilder.append(vertex.getLabel() + " ----- " + "<font color='#ADD8E6'><b>" + edge.getLine() + "</b></font>" + " ----- " + (double) edge.getTime() + " " + minute(edge));
                        }
                        if (edge.getLine().equals("Northern")) {
                            stringBuilder.append(vertex.getLabel() + " ----- " + "<b>" + edge.getLine() + "</b>" + " ----- " + (double) edge.getTime() + " " + minute(edge));
                        }
                    }

                } else {
                    stringBuilder.append("<br />" + vertex.getLabel() + " - end of journey." + "<br />");
                }
            //peak time
            } else {
                if (listIterator.hasNext()) {
                    stringBuilder.append("<br/>");
                    walking.add(edge.getLine());
                    totstops++;

                    //due to peak time being busier than usual, half a minute is added
                    //to compensate for possible longer waiting times
                    total.add((double) edge.getTime() + 0.5);
                    
                    if (edge.getLine().equals("Bakerloo")) {
                        stringBuilder.append(vertex.getLabel() + " ----- " + "<font color='#8B4513'><b>" + edge.getLine() + "</b></font>" + " ----- " + (double) edge.getTime() + " " + minute(edge));
                    }
                    if (edge.getLine().equals("Victoria")) {
                        stringBuilder.append(vertex.getLabel() + " ----- " + "<font color='#00FFFF'><b>" + edge.getLine() + "</b></font>" + " ----- " + (double) edge.getTime() + " " + minute(edge));
                    }
                    if (edge.getLine().equals("Central")) {
                        stringBuilder.append(vertex.getLabel() + " ----- " + "<font color='Red'><b>" + edge.getLine() + "</b></font>" + " ----- " + (double) edge.getTime() + " " + minute(edge));
                    }
                    if (edge.getLine().equals("Circle")) {
                        stringBuilder.append(vertex.getLabel() + " ----- " + "<font color='#cccc00'><b>" + edge.getLine() + "</b></font>" + " ----- " + (double) edge.getTime() + " " + minute(edge));
                    }
                    if (edge.getLine().equals("District")) {
                        stringBuilder.append(vertex.getLabel() + " ----- " + "<font color='Green'><b>" + edge.getLine() + "</b></font>" + " ----- " + (double) edge.getTime() + " " + minute(edge));
                    }
                    if (edge.getLine().equals("Hammersmith and City")) {
                        stringBuilder.append(vertex.getLabel() + " ----- " + "<font color='#ff00ff'><b>" + edge.getLine() + "</b></font>" + " ----- " + (double) edge.getTime() + " " + minute(edge));
                    }
                    if (edge.getLine().equals("Jubilee")) {
                        stringBuilder.append(vertex.getLabel() + " ----- " + "<font color='#C0C0C0'><b>" + edge.getLine() + "</b></font>" + " ----- " + (double) edge.getTime() + " " + minute(edge));
                    }
                    if (edge.getLine().equals("Metropolitan")) {
                        stringBuilder.append(vertex.getLabel() + " ----- " + "<font color='Purple'><b>" + edge.getLine() + "</b></font>" + " ----- " + (double) edge.getTime() + " " + minute(edge));
                    }
                    if (edge.getLine().equals("Piccadilly")) {
                        stringBuilder.append(vertex.getLabel() + " ----- " + "<font color='Blue'><b>" + edge.getLine() + "</b></font>" + " ----- " + (double) edge.getTime() + " " + minute(edge));
                    }
                    if (edge.getLine().equals("Waterloo & City")) {
                        stringBuilder.append(vertex.getLabel() + " ----- " + "<font color='#ADD8E6'><b>" + edge.getLine() + "</b></font>" + " ----- " + (double) edge.getTime() + " " + minute(edge));
                    }
                    if (edge.getLine().equals("Northern")) {
                        stringBuilder.append(vertex.getLabel() + " ----- " + "<b>" + edge.getLine() + "</b>" + " ----- " + (double) edge.getTime() + " " + minute(edge));
                    }

                } else {
                    stringBuilder.append("<br />" + vertex.getLabel() + " - end of journey." + "<br />");
                }
            }
        }
        //runs walk function
        walk();
        return stringBuilder.toString();
    }

    // simple function that places either singular 'minute' or 'minutes' next to
    //the weight output depending on the weight.
    public String minute(Edge e) {
        if (e.getTime() == 1) {
            return "minute";
        } else {
            return "minutes";
        }
    }
    
    
    //this function is responsible for calculating the arrival time acording to journey total time
    //and output as hour:minutes
    public String arrival() {
        //get hours from gui dropdown time and convert to int
        String ho = (String) Gui.hours.getSelectedItem();
        int h = Integer.parseInt(ho);
        //get mins from gui dropdown time and convert to int
        String mi = (String) Gui.mins.getSelectedItem();
        int m = Integer.parseInt(mi);
        //adds the time taken to departure time
        int finh = hour + h;
        int finm = min + m;
        //declare two empty strings that will be used to output arrival time
        String pt1 = "";
        String pt2 = "";
        //the first part check if hours are between 1 and 9, so it adds "0" before it
        if (finh >= 1 && finh <= 9) {
            pt1 += "0" + finh;
            //here it does the same check but for minutes, if true it returns, if not goes next
            if (finm >= 0 && finm <= 9) {
                pt2 += "0" + finm;
                return pt1 + ":" + pt2;
            }
            pt2 += finm;
            return pt1 + ":" + pt2;
        //as we use 24h format, this part checks for overflow
        } else if (finh >= 24) {
            //if over 24, it removes 24 from result and simply puts 0 before it
            //lets say it was 27:50, takes 24 away 27 leaving 03:50 (correct format as expected)
            int over = finh - 24;
            if (finm >= 0 && finm <= 9) {
                pt2 += "0" + finm;
                return "0"+ over + ":" + pt2;
            }
            pt2 += finm;
            return "0"+ over + ":" + pt2;
        //if none of the above applis, simply declares pt1 as finh and pt2 as finm
        } else if (finh >= 10 && finh <= 23) {
            pt1 += finh;
            if (finm >= 0 && finm <= 9) {
                pt2 += "0" + finm;
                return pt1 + ":" + pt2;
            }
            pt2 += finm;
            return pt1 + ":" + pt2;
        } else {
            //returns arrival time
            return pt1 + ":" + pt2;
        }
    }
    
       
    public String toString() {
        //stores lineinfo to pathjourney so data in there can be manipulated here
        String pathJourney = lineInfo();
        //checks if a journey is made in a single line by watching added value 
        //if 0 it means no extra time was added for walking inside the station
        if (added() == 0) {
            return pathJourney + "----------------------------------------------------------<br />Total stops: " + totstops + "<br />Total journey time: " + taken() + "<br />----------------------------------------------------------<br />Arrival time: <b>" + arrival() + "</b>";
        //however if not zero it means more than one line was used, which executes the code 
        //that species how many line changes were made.
        } else {
            return pathJourney + "----------------------------------------------------------<br />Number of line/platform changes: " + change.get(0) + "<br />Total stops: " + totstops
                + "<br />----------------------------------------------------------<br />Estimated walking time of " + (double) added()
                + " minutes added to total journey time.<br />----------------------------------------------------------<br />Total journey time: " + taken() 
                + "<br />----------------------------------------------------------<br />Arrival time: <b>" + arrival() + "</b>";

        }
    }

}
