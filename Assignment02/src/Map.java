import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

public class Map {
    public Paths P;
    public TreeMap<String, Integer> st;
    private String[] ifk;

    /**
     * Initializes a graph from a file using the specified delimiter.
     * Each line in the file contains the name of a vertex, followed by a list of the names
     * of the vertices adjacent to that vertex, separated by the delimiter.
     * @param filename the name of the file
     * @param delimiter the delimiter between fields
     */
    public Map(String filename, String delimiter) throws FileNotFoundException, IOException {

        st = new TreeMap<>();

        // First pass builds the index by reading strings to associate
        // distinct strings with an index
        BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(delimiter);
            for (String s: data) {
                if(!st.containsKey(s))
                    st.put(s, st.size());
            }
        }

        System.out.println("Done reading " + filename);

        ifk = new String[st.size()];
        for(String name : st.keySet())
            ifk[st.get(name)] = name;

        // second pass builds the graph by connecting first vertex on each
        // line to all others
        P = new Paths(st.size());
        reader = new BufferedReader(new FileReader(
                new File(filename)));


        while ((line = reader.readLine()) != null) {
            String[] data = line.split(delimiter);

            // Gets the location of the movie
            int v = st.get(data[0]);

            // Links the movies and actors together
            for(int i = 1; i < data.length; i++){
                int w = st.get(data[i]);
                P.addEdge(v, w);
            }
        }

    }

    /**
     * Does the graph contain the vertex named s
     * @param s the name of a vertex
     */
    public boolean contains(String s) {
        return st.containsKey(s);
    }

    /**
     * return the adjacent vertices of a vertex named s
     */
    public ItemBag<String> neighbors(String s){
        @SuppressWarnings({ "rawtypes", "unchecked" })
        ItemBag<String> ls = new ItemBag();

        return ls;
    }
    /**
     * return a list of movie title, actors, or actresses
     * if their names have s as a substring
     */
    public ItemBag<String> list(String s){
        ItemBag<String> ls=new ItemBag();
        return ls;
    }
    /**
     * Returns the integer associated with the vertex named s.
     * @param s the name of a vertex
     */
    public int index(String s) {
        return st.get(s);
    }

    /**
     * Returns the name of the vertex associated with the integer v.
     * @param v the integer corresponding to a vertex (between 0 and V - 1)
     */
    public String name(int v) {
        return ifk[v];
    }

    /**
     * Returns the graph associated with the symbol graph.
     */
    public Paths G(){
        return P;
    }

}