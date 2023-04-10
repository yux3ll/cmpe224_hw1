package Q2;

import Q2.Bag;

public class Graph {
//-----------------------------------------------------
// Title: Graph
// Author: Yüksel Çağlar Baypınar
// ID: 43951623744
// Section: 1
// Assignment: 1
// Description: Custom Q2.Graph implementation, has an additional method called RoundTripWithStops that calculates an ideal path for a round trip while not going over the same vertices twice.
//-----------------------------------------------------

    private final int V; // number of vertices
    private int E; // number of edges
    private Bag<Integer>[] adj; // adjacency lists

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V]; // Create array of lists.
        for (int v = 0; v < V; v++) // Initialize all lists
            adj[v] = new Bag<Integer>(); // to empty.
    }

    public void addEdge(int v, int w) {
        adj[v].add(w); // Add w to v’s list.
        adj[w].add(v); // Add v to w’s list.
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }


    public int[] roundTrip(int source, int midway) {
        int[] path = shortestPath(source, midway); //get the shortest path from the source to the midway point


        int[] path2 = shortestPath(midway, source); //get the shortest path from the midway point to the source
        int[] path3 = new int[path.length + path2.length - 1]; //create a new array that will contain the path and the time
        path3[0] = path.length + path2.length - 1; //set the first element of the array to the length of the path
        for (int i = 1; i < path.length; i++) {  //for each element in the first path
            path3[i] = path[i]; //add the element to the new array
        }
        for (int i = 1; i < path2.length; i++) { //for each element in the second path
            path3[i + path.length - 1] = path2[i]; //add the element to the new array
        }
        return path3;   //return the path
    }


    public int[] shortestPath(int source, int destination) { //return an int array that contains the shortest path to be followed with no duplicates, and relaxation
        int[] path = new int[V]; //array that contains the path
        int[] distance = new int[V]; //array that contains the distance from the source to each vertex
        boolean[] visited = new boolean[V]; //array that contains the visited vertices
        //add here the previous shit i tried
        for (int i = 0; i<V; i++){ //initialize the arrays
            distance[i] = Integer.MAX_VALUE; //set the distance to infinity
            visited[i] = false; //set the visited vertices to false
        }
        distance[source] = 0; //set the distance from the source to itself to 0
        for (int i = 0; i<V; i++){ //for each vertex
            int min = Integer.MAX_VALUE; //set the minimum distance to infinity
            int minIndex = -1; //set the minimum index to -1
            for (int j = 0; j<V; j++){  //for each vertex
                if (!visited[j] && distance[j] < min){  //if the vertex is not visited and the distance is less than the minimum distance
                    min = distance[j];  //set the minimum distance to the distance
                    minIndex = j;   //set the minimum index to the index
                }
            }
            visited[minIndex] = true;   //set the minimum index to visited
            for (int j : adj[minIndex]){    //for each vertex adjacent to the minimum index
                if (!visited[j] && distance[minIndex] + 1 < distance[j]){   //if the vertex is not visited and the distance from the source to the minimum index plus 1 is less than the distance from the source to the vertex
                    distance[j] = distance[minIndex] + 1; //set the distance from the source to the vertex to the distance from the source to the minimum index plus 1
                    path[j] = minIndex; //set the path to the minimum index
                }
            }
        }
        int[] shortestPath = new int[distance[destination]+1]; //create a new array that will contain the shortest path
        int index = destination; //set the index to the destination
        for (int i = distance[destination]; i>=0; i--){ //for each vertex in the shortest path
            shortestPath[i] = index; //add the vertex to the array
            index = path[index]; //set the index to the previous vertex
        }
        return shortestPath;
    }

    }
