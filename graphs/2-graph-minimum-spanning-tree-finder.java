import edu.princeton.cs.algs4.*;

public class PrimMST {
    private double weight;
    private Queue<Edge> mst;
    private boolean[] marked;
    private MinPQ<Edge> pq;

    public PrimMST(EdgeWeightedGraph graph) {
        mst = new Queue<Edge>();
        pq = new MinPQ<Edge>();
        marked = new boolean[graph.numberOfVertices()];
        for (int v = 0; v < graph.numberOfVertices(); v++)
            if (!marked[v]) prim(graph, v);
    }

    private void prim(EdgeWeightedGraph graph, int source) {
        scan(graph, source);
        while (!pq.isEmpty()) {
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (marked[v] && marked[w]) continue;
            mst.enqueue(e);
            weight += e.weight();
            if (!marked[v]) scan(graph, v);
            if (!marked[w]) scan(graph, w);
        }
    }

    private void scan(EdgeWeightedGraph graph, int v) {
        marked[v] = true;
        for (Edge e : graph.adj(v))
            if (!marked[e.other(v)]) pq.insert(e);
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        return weight;
    }
}