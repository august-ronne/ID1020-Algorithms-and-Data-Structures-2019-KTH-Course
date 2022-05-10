import edu.princeton.cs.algs4.*;

public class TestUnit {

    public static void main(String[] args) {
        String filePath = "C:\\Users\\August Ronne\\IdeaProjects\\Lab4\\contiguous-usa-cycles.dat.txt";
        String delimeter = " ";
        String startOfTrip = "AL";
        String tripDestination = "NH";
        SymbolGraph sg = new SymbolGraph(filePath, delimeter);
        Digraph graph = sg.graph();

        testBFS(graph, sg, startOfTrip, tripDestination);

        testDirCycle(graph, sg);
    }

    private static void testBFS(Digraph graph, SymbolGraph sg, String source, String end) {
        DigraphBFSPaths dbfs = new DigraphBFSPaths(graph, sg.indexOf(source));

        if (dbfs.hasPathTo(sg.indexOf(end))) {
            StdOut.println("\nThere is a path between " + source + " and " + end + "\n");
            for (int v : dbfs.pathTo(sg.indexOf(end))) {
                if (v == sg.indexOf(source)) {
                    StdOut.print(sg.nameOf(v));
                } else {
                    StdOut.print(" -> " + sg.nameOf(v));
                }
            }
            StdOut.println();
        } else {
            StdOut.println("\nThere is no path between " + source + " and " + end);
        }
    }

    private static void testDirCycle(Digraph graph, SymbolGraph sg) {
        DirectedCycle cycleFinder = new DirectedCycle(graph);
        if (cycleFinder.hasCycle()) {
            StdOut.println("\nFound directed cycle: \n");
            for (int v : cycleFinder.cycle()) {
                StdOut.print(sg.nameOf(v) + " -> ");
            }
            StdOut.println();
        } else {
            StdOut.println("\nThere is no directed cycle in the graph.\n");
        }
    }
}