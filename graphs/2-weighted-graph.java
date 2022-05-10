public class EdgeWeightedGraph {
    private final int numberOfVertices;
    private int numberOfEdges;
    private Bag<Edge>[] adj;

    public EdgeWeightedGraph(int numberOfVertices) {
        this.numberOfVertices = numberOfVertices;
        this.numberOfEdges = 0;
        adj = (Bag<Edge>[]) new Bag[numberOfVertices];
        for (int v = 0; v < numberOfVertices; v++) {
            adj[v] = new Bag<Edge>();
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
            throw new IllegalArgumentException("arg vertex " + v + " is out of graph bounds");
        }
    }

    public Iterable<Edge> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    public Iterable<Edge> edges() {
        Bag<Edge> list = new Bag<Edge>();
        for (int v = 0; v < numberOfVertices; v++) {
            int selfLoops = 0;
            for (Edge e : adj(v)) {
                if (e.other(v) > v) {
                    list.add(e);
                } else if (e.other(v) == v) {
                    if (selfLoops % 2 == 0) {
                        list.add(e);
                    }
                    selfLoops++;
                }
            }
        }
        return list;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(numberOfVertices + " " + numberOfEdges + "\n");
        for (int v = 0; v < numberOfVertices; v++) {
            sb.append(v + ": ");
            for (Edge e : adj[v]) {
                sb.append(e + "  ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        validateVertex(v);
        validateVertex(w);
        adj[v].add(e);
        adj[w].add(e);
        numberOfEdges++;
    }
}
