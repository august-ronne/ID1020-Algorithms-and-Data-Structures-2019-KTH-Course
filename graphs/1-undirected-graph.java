import edu.princeton.cs.algs4.*;

public class UndirectedGraph {
    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public UndirectedGraph(int V) {
        if (V < 0) {
            throw new IllegalArgumentException("Can not have negative amount of vertices.");
        }
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    public UndirectedGraph(Graph G) {
        this(G.V());
        this.E = G.E();
        for (int v = 0; v < G.V(); v++) {
            Stack<Integer> reverse = new Stack<Integer>();
            for (int w : G.adj(v)) {
                reverse.push(w);
            }
            for (int w : reverse) {
                adj[v].add(w);
            }
        }
    }

    public int E() {
        return E;
    }

    public int V() {
        return V;
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex argument " + v + " is out of graph bounds.");
        }
    }

    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        E++;
        adj[v].add(w);
        adj[w].add(v);
    }

    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(V + " vertices, " + E + " edges \n");
        for (int v = 0; v < V; v++) {
            sb.append(v + ": ");
            for (int w : adj[v]) {
                sb.append(w + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}