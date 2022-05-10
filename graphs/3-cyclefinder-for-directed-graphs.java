import edu.princeton.cs.algs4.*;

public class DirectedCycle {
    private boolean[] visited;
    private int[] edgeTo;
    private boolean[] onStack;
    private Stack<Integer> cycle;

    public DirectedCycle(Digraph graph) {
        visited = new boolean[graph.numberOfVertices()];
        onStack = new boolean[graph.numberOfVertices()];
        edgeTo = new int[graph.numberOfVertices()];
        for (int v = 0; v < graph.numberOfVertices(); v++) {
            if (!visited[v] && cycle == null) {
                dfs(graph, v);
            }
        }
    }

    private void dfs(Digraph graph, int v) {
        onStack[v] = true;
        visited[v] = true;
        for (int w : graph.adj(v)) {
            if (cycle != null) {
                return;
            } else if (!visited[w]) {
                edgeTo[w] = v;
                dfs(graph, w);
            } else if (onStack[w]) {
                cycle = new Stack<Integer>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }
}
