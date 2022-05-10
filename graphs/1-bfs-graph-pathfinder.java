public class BreadthFirstPaths {
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean visited[];
    private int[] edgeTo;
    private int[] distTo;

    public BreadthFirstPaths(UndirectedGraph graph, int source) {
        visited = new boolean[graph.V()];
        distTo = new int[graph.V()];
        edgeTo = new int[graph.V()];
        validateVertex(source);
        bfs(graph, source);
    }

    private void validateVertex(int v) {
        int V = visited.length;
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("arg vertex " + v + " out of graph bounds");
        }
    }

    private void bfs(UndirectedGraph graph, int source) {
        Queue<Integer> q = new Queue<Integer>();
        for (int v = 0; v < graph.V(); v++) {
            distTo[v] = INFINITY;
        }
        distTo[source] = 0;
        visited[source] = true;
        q.enqueue(source);
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

    public int  distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }

    public Iterable<Integer> shortestPathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> shortestPath = new Stack<Integer>();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x]) {
            shortestPath.push(x);
        }
        shortestPath.push(x);
        return shortestPath;
    }
}
