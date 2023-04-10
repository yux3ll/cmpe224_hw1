public class Graph
{
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

    public String toString()
    {
        StringBuilder s = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++)
        {
            s.append(v + ": ");
            for (int w : adj[v])
            {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
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

    public int[] shortestPathWithStates(int source, int destination, int stateSwitch, int travel)
    {
        int[] path = shortestPath(source, destination);
        int[] pathWithStates = new int[path.length+2];
        boolean state = true;
        int time = 0;
        for (int i = 0; i< path.length-1; i++){
            int timeCheck=0;
            if (!state){
                int timeToWait = stateSwitch - time%stateSwitch;
                time += timeToWait;

            }
            time += travel;
            timeCheck = time;
            if(timeCheck >=stateSwitch&&timeCheck< stateSwitch*2){
                state = !state;
            }
        }
        pathWithStates[0] = path.length;
        for(int i = 0; i<path.length; i++){
            pathWithStates[i+1] = path[i];
        }
        pathWithStates[path.length+1] = time;
        return pathWithStates;
    }

    public int[] shortestPath(int source, int destination){ //return an int array that containt the shortesr path to be followed with no duplicates
        int[] path = new int[V];
        int[] distance = new int[V];
        boolean[] visited = new boolean[V];
        for (int i = 0; i<V; i++){
            distance[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }
        distance[source] = 0;
        for (int i = 0; i<V; i++){
            int min = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int j = 0; j<V; j++){
                if (!visited[j] && distance[j] < min){
                    min = distance[j];
                    minIndex = j;
                }
            }
            visited[minIndex] = true;
            for (int j : adj[minIndex]){
                if (!visited[j] && distance[minIndex] + 1 < distance[j]){
                    distance[j] = distance[minIndex] + 1;
                    path[j] = minIndex;
                }
            }
        }
        int[] shortestPath = new int[distance[destination]+1];
        int index = destination;
        for (int i = distance[destination]; i>=0; i--){
            shortestPath[i] = index;
            index = path[index];
        }
        return shortestPath;


    }

}