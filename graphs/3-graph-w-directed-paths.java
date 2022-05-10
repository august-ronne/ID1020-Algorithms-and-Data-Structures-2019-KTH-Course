import edu.princeton.cs.algs4.*;

public class Digraph {
    private final int numberOfVertices;
    private int numberOfEdges;
    private Bag<Integer>[] adj;
    private int[] indegree;

    public Digraph(int numberOfVertices) {
        this.numberOfVertices = numberOfVertices;
        this.numberOfEdges = 0;
        indegree = new int[numberOfVertices];
        adj = (Bag<Integer>[]) new Bag[numberOfVertices];
        for (int v = 0; v < numberOfVertices; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    public int numberOfVertices() {
        return numberOfVertices;
    }

    public int numberOfEdges() {
        return numberOfEdges;
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= numberOfVertices) {
            throw new IllegalArgumentException("arg vertex " + v + " out of graph bounds.");
        }
    }

    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        adj[v].add(w);
        indegree[w]++;
        numberOfEdges++;
    }

    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public int outdegree(int v) {
        return adj[v].size();
    }

    public int indegree(int v) {
        validateVertex(v);
        return indegree[v];
    }
}
