import edu.princeton.cs.algs4.*;

public class SymbolGraph {
    private ST<String, Integer> st;
    private String[] keys;
    private EdgeWeightedGraph edgeGraph;
    private UndirectedGraph regGraph;

    public SymbolGraph(String filename, String delimeter) {
        st = new ST<String, Integer>();
        In in = new In(filename);
        while (!in.isEmpty()) {
            String[] a = in.readLine().split(delimeter);
            for (int i = 0; i < a.length - 1; i++) {
                if (!st.contains(a[i])) {
                    st.put(a[i], st.size());
                }
            }
        }
        keys = new String[st.size()];
        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }
        edgeGraph = new EdgeWeightedGraph(st.size());
        regGraph = new UndirectedGraph(st.size());
        in = new In(filename);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delimeter);
            int v = st.get(a[0]);
            int weight = Integer.parseInt(a[2]);
            for (int i = 1; i < a.length - 1; i++) {
                int w = st.get(a[i]);
                edgeGraph.addEdge(new Edge(v, w, weight));
                regGraph.addEdge(v, w);
            }
        }
    }

    public boolean contains(String s) {
        return st.contains(s);
    }

    public int indexOf(String s) {
        return st.get(s);
    }

    public String nameOf(int v) {
        validateVertex(v);
        return keys[v];
    }

    public EdgeWeightedGraph edgeGraph() {
        return edgeGraph;
    }

    public UndirectedGraph regGraph() { return regGraph; }

    private void validateVertex(int v) {
        int V = edgeGraph.numberOfVertices();
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("arg vertex " + v + " is out of graph bounds.");
        }
    }
}
