public class Edge implements Comparable<Edge> {
    private final int v;
    private final int w;
    private final int weight;

    public Edge(int v, int w, int weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }

    public int either() {
        return v;
    }

    public int v() {
        return v;
    }

    public int w() {
        return w;
    }

    public int other(int vertex) {
        if (vertex == v) {
            return w;
        } else if (vertex == w) {
            return v;
        } else {
            throw new IllegalArgumentException("Call to other(vertex) with illegal vertex.");
        }
    }

    @Override
    public int compareTo(Edge that) {
        return Double.compare(this.weight, that.weight);
    }

    public String toString() {
        return v + "-" + w + "." + weight;
    }
}
