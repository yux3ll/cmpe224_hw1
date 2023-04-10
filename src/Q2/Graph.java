package Q2;
public class Graph {
//-----------------------------------------------------
// Title: Graph
// Author: Yüksel Çağlar Baypınar
// ID: 43951623744
// Section: 1
// Assignment: 1
// Description: Custom Graph implementation, has an additional method called shortestCycleWithSourceAndDestination(sorry about the names) that calculates an ideal path for a round trip while not going over the same vertices twice.
//-----------------------------------------------------

    private final int V; // number of vertices
    // number of edges
    private final Bag<Integer>[] adj; // adjacency lists

    public Graph(int V) {
        this.V = V;
        adj = (Bag<Integer>[]) new Bag[V]; // Create array of lists.
        for (int v = 0; v < V; v++) // Initialize all lists
            adj[v] = new Bag<>(); // to empty.
    }

    public void addEdge(int v, int w) {
        adj[v].add(w); // Add w to v’s list.
        adj[w].add(v); // Add v to w’s list.
    }

    public int[] shortestCycleWithSourceAndDestination(int source, int destination){ // This method finds the shortest simple cycle that contains the source and destination vertices, not my best work, but it is formulaic and deterministic .
        int[] allCycles = findAllCycles(); // This method finds all simple cycles in the graph.
        int[] tempResult = new int[V*V]; // This array is used to store the current cycle being checked.
        int cycleAmount = 0; // This variable is used to keep track of how many cycles are left to check.
        int indexTracker = 0; // This variable is used to keep track of the index of the current cycle being checked.
        int minIndexTracker = 0; // This variable is used to keep track of the index of the shortest cycle found.
        int minCycleLength = V*V; // This variable is used to keep track of the length of the shortest cycle found.
        for (int allCycle : allCycles) { // This loop counts the amount of cycles in the graph.
            if (allCycle == -1) { // The -1 value is used to separate cycles.
                cycleAmount++;
            }
        }
        while(cycleAmount>0){ // This loop checks each cycle for the shortest cycle that contains the source and destination vertices.
            int cycleLength = 0; // This variable is used to keep track of the length of the current cycle being checked.
            while(allCycles[indexTracker] != -1){ // This loop adds the current cycle to the tempResult array.
                tempResult[cycleLength] = allCycles[indexTracker];
                indexTracker++;
                cycleLength++;
            } indexTracker++; // This is used to skip the -1 value.
            if(tempResult[0] == source && tempResult[cycleLength-1] == source){ // This checks if the current cycle contains the source and destination vertices.
               boolean containsDestination = false; // This variable is used to keep track of whether the current cycle contains the destination vertex.
                for (int i = 0; i < cycleLength-1; i++) { // This loop checks if the current cycle contains the destination vertex.
                    if (tempResult[i] == destination) {
                        containsDestination = true;
                        break;
                    }
                }
                if(containsDestination){
                    if(cycleLength < minCycleLength&&cycleLength>3){ // This checks if the current cycle is shorter than the shortest cycle found.
                        minCycleLength = cycleLength; // This updates the shortest cycle length.
                        minIndexTracker = indexTracker-cycleLength-1; // This updates the index of the shortest cycle.
                    }
                    else if(cycleLength == minCycleLength){ // This checks if the current cycle is the same length as the shortest cycle found.
                        int sum1 = 0;
                        int sum2 = 0;
                        for (int i = 0; i < cycleLength-1; i++) { // This loop calculates the sum of the current cycle and the shortest cycle found.
                            sum1 += tempResult[i];
                           sum2 += allCycles[indexTracker-cycleLength-1+i];
                        }
                        if(sum2<sum1){  // This checks if the sum of the current cycle is smaller than the sum of the shortest cycle found.
                            minIndexTracker = indexTracker-cycleLength-1;
                        }
                    }
                }
            }
            cycleAmount--; // This decrements the amount of cycles left to check.
        }
        int[] result = new int[minCycleLength-1]; // This array is used to store the shortest cycle that contains the source and destination vertices.
        // This loop adds the shortest cycle to the result array.
        System.arraycopy(allCycles, minIndexTracker, result, 0, minCycleLength - 1);
    return result;
    }

    public int[] findAllCycles() { // This method finds all simple cycles in the graph, returns the list as an int array divided with -1's.
        boolean[] visited = new boolean[V]; // keep track of visited vertices
        int[] path = new int[V*V]; // keep track of vertices in current path
        int[] cycles = new int[V * V*V*V]; // keep track of cycles
        int index = 0; // current index in cycles array

        // perform DFS from each vertex
        for (int v = 0; v < V; v++) { // for each vertex
            visited[v] = true; // mark it as visited
            path[0] = v; // add it to the path
            index = findAllCyclesHelper(v, v, visited, path, 1, cycles, index); // find all cycles starting from v
            visited[v] = false; // unmark it
        }

        // add -1 between cycles and trim the array to remove unused elements
        int[] result = new int[index]; // create a new array with the correct size
        System.arraycopy(cycles, 0, result, 0, index); // copy the cycles array to the result array
        for (int i = 0; i < result.length - 1; i++) { // add -1 between cycles
            if (result[i] == -1 && result[i + 1] == -1) { // if two -1's are next to each other
                result[i] = 0; // replace the first one with 0
            }
        }
        return result;
    }

    private int findAllCyclesHelper(int start, int current, boolean[] visited, int[] path, int index, int[] cycles, int cycleIndex) {
        // check if we have reached the start vertex and the path length is greater than 2
        if (start == current && index > 2) {
            // add the cycle to the result array
            for (int i = 0; i < index; i++) {
                cycles[cycleIndex++] = path[i];
            }
            cycles[cycleIndex++] = -1;
            return cycleIndex;
        }

        // explore all adjacent vertices
        for (int i = 0; i < adj[current].size(); i++) {
            int next = adj[current].get(i);
            if (!visited[next] || next == start) {
                visited[next] = true;
                path[index] = next;
                cycleIndex = findAllCyclesHelper(start, next, visited, path, index + 1, cycles, cycleIndex);
                visited[next] = false;
            }
        }
        return cycleIndex;
    }
    }









