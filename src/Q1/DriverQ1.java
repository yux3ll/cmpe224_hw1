package Q1;

import java.util.Scanner;
public class DriverQ1 {
//-----------------------------------------------------
// Title: Q1.DriverQ1
// Author: Yüksel Çağlar Baypınar
// ID: 43951623744
// Section: 1
// Assignment: 1
// Description: Driver class for the first question, reads the input,
// calculates the shortest path and prints the necessary output.
//-----------------------------------------------------

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); //to read the input
        int V = sc.nextInt(); //number of vertices
        int E = sc.nextInt(); //number of edges
        int stateSwitch = sc.nextInt(); //number of state switches
        int travel = sc.nextInt();  //number of travel
        sc.nextLine();  //to skip the line
        Graph g = new Graph(V); //create a graph with V vertices
        for (int i = 0; i < E; i++) {   //add the edges to the graph
            g.addEdge(sc.nextInt()-1, sc.nextInt()-1);  //the input is 1 indexed, so we subtract 1 to make it 0 indexed
            sc.nextLine();  //to skip the line
        }
        int source = sc.nextInt()-1;
        int destination = sc.nextInt()-1;
        sc.close(); //close the scanner
        int[] path = g.shortestPathWithStates(source, destination, stateSwitch, travel);    //get the shortest path and time
        System.out.println(path[0]);   //print the number of vertices in the path
        for (int i = 1; i < path.length-1; i++) {   //print the path
            System.out.print(path[i]+1 + " ");
        }
        System.out.println("\n" + path[path.length-1]); //print the time necessary to travel
    }
}


