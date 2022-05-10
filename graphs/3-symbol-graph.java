import edu.princeton.cs.algs4.*;

public class SymbolGraph {
    private ST<String, Integer> st;
    private String[] keys;
    private Digraph graph;

    public SymbolGraph(String filename, String delimeter) {
        st = new ST<String, Integer>();
        In in = new In(filename);
        while (!in.isEmpty()) {
            String[] a = in.readLine().split(delimeter);
            for (int i = 0; i < a.length; i++) {
                if (!st.contains(a[i])) {
                    st.put(a[i], st.size());
                }
            }
        }

        keys = new String[st.size()];
        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }

        graph = new Digraph(st.size());
        in = new In(filename);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delimeter);
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++) {
                int w = st.get(a[i]);
                graph.addEdge(v, w);
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

    public Digraph graph() {
        return graph;
    }

    private void validateVertex(int v) {
        int V = graph.numberOfVertices();
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("arg vertex " + v + " is out of graph bounds.");
        }
    }
}
