import java.util.Stack;

public class Paths{
    private int V;              // number of vertices in path
    private int E;            // number of edges
    private ItemBag<Integer>[] adj;

    /**
     * Initializes an empty path
     * param V the number of vertices
     */

    public Paths(int V) {

        if (V < 0) {
            throw new IllegalArgumentException("Number of vertices must be nonnegative");
        }

        this.V = V;
        this.E = 0;

        for (int v = 0; v < V; v++) {
            if (adj != null) {
                adj[v] = new ItemBag<Integer>();
            }
        }
    }

    /**
     * Initializes a new path that is a deep copy of <tt>G</tt>.
     *
     * @param P the path to copy
     */
    public Paths(Paths P) {
        this(P.V());
        this.E = P.E();

        for (int v = 0; v < P.V(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Integer> reverse = new Stack<Integer>();

            for (Object w : P.adj[v]) {
                reverse.push((Integer) w);
            }
            for (int w : reverse) {
                adj[v].add(w);
            }
        }
    }

    /**
     * Returns the number of vertices in the graph.
     * @return the number of vertices in the graph
     */
    public int V() {
        return V;
    }

    /**
     * Returns the number of edges in the graph.
     *
     * @return the number of edges in the graph
     */
    public int E() {
        return E;
    }

    /**
     * Adds the undirected edge v-w to the graph.
     *
     * @param v one vertex in the edge
     * @param w the other vertex in the edge
     */

    public void addEdge(int v, int w) {
        if (v < 0 || v >= V) {
            throw new IndexOutOfBoundsException();
        }
        if (w < 0 || w >= V) {
            throw new IndexOutOfBoundsException();
        }
        E++;
        adj[v].add(w);
        adj[w].add(v);
    }

    /**
     * Returns the vertices adjacent to vertex.
     *
     * @param v the vertex
     */

    public Iterable<Integer> adj(int v) {
        if (v < 0 || v >= V) {
            throw new IndexOutOfBoundsException();
        }
        return adj[v];
    }

    /**
     * * Returns a string representation of the path.
     *
     * @return the number of vertices and the number of edges
     */
    public String toString() {

        StringBuilder s = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}