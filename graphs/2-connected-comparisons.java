public class ConnectedComps {
    private boolean visited[];
    private int[] id;
    private int[] size;
    private int count;

    public ConnectedComps(UndirectedGraph graph) {
        visited = new boolean[graph.V()];
        id = new int[graph.V()];
        size = new int[graph.V()];
        for (int v = 0; v < graph.V(); v++) {
            if (!visited[v]) {
                dfs(graph, v);
                count++;
            }
        }
    }
    public ConnectedComps(EdgeWeightedGraph graph) {
        visited = new boolean[graph.numberOfVertices()];
        id = new int[graph.numberOfVertices()];
        size = new int[graph.numberOfVertices()];
        for (int v = 0; v < graph.numberOfVertices(); v++) {
            if (!visited[v]) {
                dfs(graph, v);
                count++;
            }
        }
    }

    private void dfs(EdgeWeightedGraph graph, int v) {
        visited[v] = true;
        id[v] = count;
        size[count]++;
        for (Edge e : graph.adj(v)) {
            int w = e.other(v);
            if (!visited[w]) {
                dfs(graph, w);
            }
        }
    }

    private void dfs(UndirectedGraph graph, int v) {
        visited[v] = true;
        id[v] = count;
        size[count]++;
        for (int w : graph.adj(v)) {
            if (!visited[w]) {
                dfs(graph, w);
            }
        }
    }

    private void validateVertex(int v) {
        int V = visited.length;
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("arg vertex " + v + " out of graph bounds.");
        }
    }

    public int id(int v) {
        validateVertex(v);
        return id[v];
    }

    public int size(int v) {
        validateVertex(v);
        return size[id[v]];
    }

    public int count() {
        return count;
    }

    public boolean connected(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return id(v) == id(w);
    }
}
