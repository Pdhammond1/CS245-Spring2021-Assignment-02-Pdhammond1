import java.util.Iterator;
import java.util.Stack;

public class Paths <Item> implements Iterable <Item>{
    private int V;              // number of vertices in path
    private int E;            // number of edges
    private Paths<Integer>[] adj;
    private int N;               // number of elements in bag
    private Node <Item> first;    // beginning of bag

    // helper linked list class
    private class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    /**
     * Initializes an empty path
     * param V the number of vertices
     */

    public Paths(int V) {

        if (V < 0) {
            throw new IllegalArgumentException("Number of vertices must be nonnegative");
        }

        this.V = V; this.E = 0;

        for (int v = 0; v < V; v++) {
            adj[v] = new Paths<Integer>(V);
        }
    }

    /**
     * Initializes a new path that is a deep copy of <tt>G</tt>.
     * @param P the path to copy
     *
     */
    public Paths(Paths P) {
        this(P.V()); this.E = P.E();

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
     * @return the number of edges in the graph
     */
    public int E() {
        return E;
    }

    /**
     * Adds the undirected edge v-w to the graph.
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
     * @param v the vertex
     */

    public Iterable<Integer> adj(int v) {
        if (v < 0 || v >= V) {
            throw new IndexOutOfBoundsException();
        }
        return adj[v];
    }

    /**
     ** Returns a string representation of the path.
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


    public void Bag() {
        first = null;
        N = 0;
    }

    /**
     * Is this bag empty?
     * @return true if this bag is empty; false otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of items in this bag.
     * @return the number of items in this bag
     */
    public int size() {
        return N;
    }

    /**
     * Adds the item to this bag.
     * @param item the item to add to this bag
     */
    public void add(Item item) {
        Node <Item> oldfirst;
        oldfirst = first;
        first = new Node <Item>();
        first.item = item;
        first.next = oldfirst;
        N++;
    }

    /**
     * Returns an iterator that iterates over the items in the bag in arbitrary order.
     */
    public Iterator<Item> iterator()  {
        return new ListIterator<Item>(first);
    }

    // an iterator, doesn't implement remove() since it's optional

    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext(){
            return current != null;
        }
        public void remove(){
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()){
                throw new UnsupportedOperationException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}

