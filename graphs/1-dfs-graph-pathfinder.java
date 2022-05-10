import edu.princeton.cs.algs4.StdOut;

public class DepthFirstPaths {
    private boolean[] visited;
    private int[] edgeTo;
    private final int source;

    public DepthFirstPaths(UndirectedGraph graph, int source) {
        this.source = source;
        edgeTo = new int[graph.V()];
        visited = new boolean[graph.V()];
        validateVertex(source);
        dfs(graph, source);
    }

    private void dfs(UndirectedGraph graph, int v) {
        visited[v] = true;
        for (int w : graph.adj(v)) {
            if (!visited[w]) {
                edgeTo[w] = v;
                dfs(graph, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        validateVertex(v);
        return visited[v];
    }

    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != source; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(source);
        return path;
    }

    private void validateVertex(int v) {
        int V = visited.length;
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("arg vertex " + v + " out of graph bounds.");
        }
    }

}
