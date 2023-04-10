package Q2;

import java.util.Scanner;
import java.util.Arrays;
public class DriverQ2 {
//-----------------------------------------------------
// Title: DriverQ2
// Author: Yüksel Çağlar Baypınar
// ID: 43951623744
// Section: 1
// Assignment: 1
// Description: Driver class for Q2. It reads the input and creates the graph. Then it calls the shortestCycleWithSourceAndDestination method. Finally, it prints the result after sorting it along the requirements.
//-----------------------------------------------------

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in); // Scanner for reading the input
        int V = sc.nextInt(); // Number of vertices
        int E = sc.nextInt(); // Number of edges
        sc.nextLine(); // To skip the line
        Graph g = new Graph(V); // Creating the graph
        for (int i = 0; i < E; i++) { // Adding the edges
            g.addEdge(sc.nextInt()-1, sc.nextInt()-1);
            sc.nextLine();
        }
        int source = sc.nextInt()-1; // Source vertex
        int include = sc.nextInt()-1; // Included vertex

        int[] path = g.shortestCycleWithSourceAndDestination(source, include); // Calling the shortestCycleWithSourceAndDestination method
        Arrays.sort(path); // Sorting the result

        for (int j : path) {
            System.out.print(j + 1 + " ");
        }
    }
}