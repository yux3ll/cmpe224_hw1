package Q1;

public class Graph {
//-----------------------------------------------------
// Title: Q1.Graph
// Author: Yüksel Çağlar Baypınar
// ID: 43951623744
// Section: 1
// Assignment: 1
// Description: Custom Q1.Graph implementation, has an additional method called shortestPathWithStates that calculates the time necessary when the states are taken into account.
//-----------------------------------------------------

    private final int V; // number of vertices
    private int E; // number of edges
    private Bag<Integer>[] adj; // adjacency lists
    public Graph(int V)
    {
        this.V = V; this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V]; // Create array of lists.
        for (int v = 0; v < V; v++) // Initialize all lists
            adj[v] = new Bag<Integer>(); // to empty.
    }
    public int V() { return V; }
    public int E() { return E; }
    public void addEdge(int v, int w)
    {
        adj[v].add(w); // Add w to v’s list.
        adj[w].add(v); // Add v to w’s list.
        E++;
    }
    public Iterable<Integer> adj(int v)
    { return adj[v]; }

    public int[] shortestPathWithStates(int source, int destination, int stateSwitch, int travel) //return an int array that contains the shortest path to be followed with no duplicates and the time necessary to travel
    {
        int[] path = shortestPath(source, destination); //get the shortest path
        int[] pathWithStates = new int[path.length+2]; //create a new array that will contain the path and the time
        boolean state = true; //true means the light is green, false means the light is red
        int time = 0; //time necessary to travel
        for (int i = 0; i< path.length-1; i++){ //calculate the time necessary to travel
            int timeCheck=0; //temp variable used to check if the state should be changed
            if (!state){ //if the light is red, wait until it turns green
                int timeToWait = stateSwitch - time%stateSwitch; //calculate the time necessary to wait
                time += timeToWait; //add the time necessary to wait to the total time
                timeCheck = timeToWait; //set the temp variable to the time necessary to wait
            }
            time += travel; //add the time necessary to travel to the total time
            timeCheck += travel; //add to the temp variable the time necessary to travel
            if(timeCheck >=stateSwitch&&timeCheck< stateSwitch*2){ //if the temp variable is greater than or equal to the time necessary to change the state and less than the time necessary to change the state twice, change the state
                // this assumes that there won't be a combination of travel time and state change time that causes the state to change 3 times in one operation
                state = !state;
            }
        }
        pathWithStates[0] = path.length; //"
        for(int i = 0; i<path.length; i++){ // add the path taken to the array(starting from 1 as opposed to 0)
            pathWithStates[i+1] = path[i];
        }
        pathWithStates[path.length+1] = time; //add the time necessary to travel to the array
        return pathWithStates;
    }

    public int[] shortestPath(int source, int destination){ //return an int array that contains the shortest path to be followed with no duplicates
        int[] path = new int[V]; //array that contains the path
        int[] distance = new int[V]; //array that contains the distance from the source to each vertex
        boolean[] visited = new boolean[V]; //array that contains the visited vertices
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