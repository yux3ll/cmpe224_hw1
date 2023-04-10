import java.util.Scanner;
public class DriverQ1 {
//-----------------------------------------------------
// Title: DriverQ1
// Author: Yüksel Çağlar Baypınar
// ID: 43951623744
// Section: 1
// Assignment: 1
// Description: Driver class for the first question, reads the input,
// calculates the shortest path and prints the necessary output.
//-----------------------------------------------------

    public static void main(String[] args) {
        //read input with following parameters: first line's first int is the number of vertices, second int is the number of total bidirectional connections, third int is an independent variable for time, and fourth is travel time
        //following lines are the connections between vertices, each line has two ints, first is the source vertex, second is the destination vertex
        //last line is the source and destination vertices for the path to be calculated
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();
        int stateSwitch = sc.nextInt();
        int travel = sc.nextInt();
        sc.nextLine();
        Graph g = new Graph(V);
        for (int i = 0; i < E; i++) {
            g.addEdge(sc.nextInt()-1, sc.nextInt()-1);
            sc.nextLine();
        }
        int source = sc.nextInt()-1;
        int destination = sc.nextInt()-1;
        sc.close();
        //calculate the shortest path
        int[] path = g.shortestPathWithStates(source, destination, stateSwitch, travel);
        System.out.println(path[0]);
        for (int i = 1; i < path.length-1; i++) {
            System.out.print(path[i]+1 + " ");
        }
        System.out.println("\n" + path[path.length-1]);
    }

}


