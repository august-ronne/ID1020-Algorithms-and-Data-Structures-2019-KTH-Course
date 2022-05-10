import edu.princeton.cs.algs4.*;
import java.io.File;
import java.io.FileInputStream;

public class TestUnit {

    public static void main(String[] args) {
        String filePath = "C:\\Users\\August Ronne\\IdeaProjects\\Lab4\\contiguous-usa.dat.txt";
        String delimeter = " ";
        String startOfTrip = "AL";
        String tripDestination = "ME";
        SymbolGraph sg = new SymbolGraph(filePath, delimeter);
        UndirectedGraph graph = sg.graph();

        testDFS(graph, sg, startOfTrip);

        testBFS(graph, sg, startOfTrip, tripDestination);

    }

    public static void testDFS(UndirectedGraph graph, SymbolGraph sg, String source) {
        int s = sg.indexOf(source);
        DepthFirstPaths dfp = new DepthFirstPaths(graph, s);
        for (int v = 0; v < graph.V(); v++) {
            if (dfp.hasPathTo(v)) {
                StdOut.println(sg.nameOf(s) + " to " + sg.nameOf(v));
                for (int x : dfp.pathTo(v)) {
                    if (x == s) {
                        StdOut.print(sg.nameOf(x));
                    } else {
                        StdOut.print("-" + sg.nameOf(x));
                    }
                }
                StdOut.println();
            } else {
                StdOut.print(sg.nameOf(s) + " not connected to " + sg.nameOf(v));
            }
        }
    }

    public static void testBFS(UndirectedGraph graph, SymbolGraph sg, String source, String dest) {
        int s = sg.indexOf(source);
        int d = sg.indexOf(dest);
        BreadthFirstPaths bfs = new BreadthFirstPaths(graph, s);

        if (bfs.hasPathTo(d)) {
            StdOut.println("\nChecking path between " + sg.nameOf(s) +
                    " and " + sg.nameOf(d) + " ...\nShortest path is " +
                    bfs.distTo(d) + " flights long.\nThe flightpath you should" +
                    " take looks as follows:\n");
            for (int x : bfs.shortestPathTo(d)) {
                if (x == s) {
                    StdOut.print(sg.nameOf(x));
                } else {
                    StdOut.print(" -> " + sg.nameOf(x));
                }
            }
        } else {
            StdOut.println(sg.nameOf(s) + " and " + sg.nameOf(d) + " are not connected by plane");
        }
    }
}
