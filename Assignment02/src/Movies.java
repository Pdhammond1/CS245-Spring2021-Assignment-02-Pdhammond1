import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.Reader;

/*
 * Downloaded JAR files from:
 *   http://commons.apache.org/proper/commons-csv/user-guide.html (Apache Commons CSV)
 *   http://www.java2s.com/Code/Jar/j/Downloadjsonsimple11jar.htm (JSON-Simple)
 *
 * Got them in my build path using:
 *   https://www.wikihow.com/Add-JARs-to-Project-Build-Paths-in-Eclipse-%28Java%29
 */

public class Movies {
    Map sg;

    public void list(String source){
        ItemBag<String> bg = sg.list(source);
        Paths P = sg.G();
        // Checks to see if the name in the database starts with the 'source'
        // then adds it to the bag
        for(int i = 0; i <= P.V(); i++){
            if(sg.name(i).startsWith(source))
                bg.add(sg.name(i));
        }

        // Prints the name(s) from the bag
        if(bg != null){
            for(String s:bg){
                System.out.println(s);
            }
        }
    }

    public void neighbors(String source){
        ItemBag<String> bg = sg.neighbors(source);
        Paths P = sg.G();

        // Creates a bag of integers and gets the adjacent vertices
        int k = sg.st.get(source);
        ItemBag<Integer> bi = (ItemBag<Integer>) P.adj(k);

        // Adds the name to the bag
        for(int i : bi){
            bg.add(sg.name(i));
        }

        // Prints the names from the bag
        if(bg != null){
            for(String s:bg){
                System.out.println(s);
            }
        }
    }
    public void path(String source, String sink){
        Paths P = sg.G();

        if (!sg.contains(source)) {
            System.out.println(source + " not in database.");
            return;
        }

        int s = sg.index(source);

        // Do a Breadth First Search
        BFS bfs = new BFS(P, s);

        if(sg.contains(sink)){
            int t = sg.index(sink);
            if(bfs.hasPathTo(t)){
                System.out.println(sg.name(s) + " --> " + sg.name(t));
                for(int x : bfs.pathTo(t)){
                    if(x == s)
                        System.out.println(sg.name(x));
                    else{
                        if(bfs.dist(x) % 2 != 0)
                            System.out.println("   " + sg.name(x));
                        else
                            System.out.println(sg.name(x));
                    }
                }
            }
            else
                System.out.println("Not connected");
        }
        else
            System.out.println("Not in database");
    }

    public static void main (String[] args) {

        try {
            Reader reader = new FileReader("tmdb_5000_credits.csv");
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
            JSONParser jsonParser = new JSONParser();

            int movies = 0;

            for (CSVRecord csvRecord : csvParser) {
                if (movies == 1) {
                    String title = csvRecord.get(1);
                    String castJSON = csvRecord.get(2);
                    // [] = array
                    // { } = "object" / "dictionary" / "hashtable" -- key "name": value

                    System.out.println("Title: " + title);
                    Object object = jsonParser.parse(castJSON);

                    JSONArray jsonArray = (JSONArray)object;
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONObject jsonObject = (JSONObject)jsonArray.get(i);
                        System.out.println(" * " + jsonObject.get("name"));
                    }
                }
                ++movies;
            }
            csvParser.close();
        }

        catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("File " + args + " is invalid or is in the wrong format.");
        }
    }
}