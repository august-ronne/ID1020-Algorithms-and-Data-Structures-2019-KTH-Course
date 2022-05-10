import edu.princeton.cs.algs4.*;

public class TestUnit {

    public static void main(String[] args) {
        String filePath = "C:\\Users\\August Ronne\\IdeaProjects\\Lab4\\contiguous-usa-weighted.dat.txt";
        String delimeter = " ";
        String startOfTrip = "AL";
        String tripDestination = "ME";

        SymbolGraph sg = new SymbolGraph(filePath, delimeter);
        EdgeWeightedGraph ewg = sg.edgeGraph();
        UndirectedGraph g = sg.regGraph();

        testDSP(ewg, sg, startOfTrip, tripDestination);

        testCC(g, sg);

        testPrimMST(ewg, sg);
    }

    private static void testDSP(EdgeWeightedGraph ewg, SymbolGraph sg, String source, String end) {
        DijkstraShortestPath dsp = new DijkstraShortestPath(ewg, sg.indexOf(source));
        if (dsp.hasPathTo(sg.indexOf(end))) {
            StdOut.println("\n" + source + " to " + end + " (total path length: " +
                    dsp.distTo(sg.indexOf(end)) + " km)\n");
            for (Edge e : dsp.pathTo(sg.indexOf(end))) {
                StdOut.print(sg.nameOf(e.v()) + " <-> " + sg.nameOf(e.w()) + "    (" + e.weight() + " km)\n");
            }
        } else {
            StdOut.println(source + " and " + end + " are not connected");
        }
    }

    private static void testCC(UndirectedGraph g, SymbolGraph sg) {
        ConnectedComps cc = new ConnectedComps(g);
        int comps = cc.count();
        int compSize = cc.size(0);
        StdOut.println("\nThere is a total of " + comps + " connected components" +
                ". This component consists of " + compSize + " vertices.\n");
    }

    private static void testPrimMST(EdgeWeightedGraph ewg, SymbolGraph sg) {
        PrimMST mst = new PrimMST(ewg);
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.printf("\nTotal weight of mst: " + mst.weight() + "\n");
    }
}
