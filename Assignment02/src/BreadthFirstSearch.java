import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BreadthFirstSearch extends Movies{

    private static final int MAX = Integer.MAX_VALUE;
    private boolean[] visited;
    private int[] et;
    private int[] dist;


    public BreadthFirstSearch(Paths G, int s){
        visited = new boolean[G.V()];
        dist = new int[G.V()];
        et = new int[G.V()];

        for(int v = 0; v < G.V(); v++)
            dist[v] = MAX;
        bfs(G, s);
    }

    // BFS which implements Dijkstra's algorithm
    private void bfs(Paths G, int s){

        Queue <Integer> q = new LinkedList<>();
        visited[s] = true;
        dist[s] = 0;
        q.add(s);

        while(!q.isEmpty()){
            int v = q.remove();
            for(Object w : G.adj(v)){
                if(!visited[(int) w]){
                    et[(int) w] = v;
                    dist[(int) w] = dist[v] + 1;
                    visited[(int) w] = true;
                    q.add((Integer) w);
                }
            }
        }
    }

    // Returns true if there is a path, false otherwise
    public boolean hasPathTo(int v){
        return visited[v];
    }

    // Returns the distance to the vertices
    public int dist(int v){
        return dist[v];
    }

    // Iterates through the paths to find a path to the location
    public Iterable <Integer> pathTo(int v){
        if(!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<>();

        int x;
        for(x = v; dist[x] != 0; x = et[x]) {
            path.push(x);
        }

        path.push(x);
        return path;
    }
}