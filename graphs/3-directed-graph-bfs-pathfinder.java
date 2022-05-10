import edu.princeton.cs.algs4.*;

public class DigraphBFSPaths {
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] visited;
    private int[] edgeTo;
    private int[] distTo;

    public DigraphBFSPaths(Digraph graph, int sourceVertex) {
        visited = new boolean[graph.numberOfVertices()];
        distTo = new int[graph.numberOfVertices()];
        edgeTo = new int[graph.numberOfVertices()];
        for (int v = 0; v < graph.numberOfVertices(); v++) {
            distTo[v] = INFINITY;
        }
        validateVertex(sourceVertex);
        bfs(graph, sourceVertex);
    }

    private void validateVertex(int v) {
        int V = visited.length;
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("arg vertex " + v + " out of graph bounds.");
        }
    }

    private void bfs(Digraph graph, int sourceVertex) {
        Queue<Integer> q = new Queue<Integer>();
        visited[sourceVertex] = true;
        distTo[sourceVertex] = 0;
        q.enqueue(sourceVertex);
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : graph.adj(v)) {
                if (!visited[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    visited[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        validateVertex(v);
        return visited[v];
    }

    public int distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }

    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<Integer>();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(x);
        return path;
    }
}
