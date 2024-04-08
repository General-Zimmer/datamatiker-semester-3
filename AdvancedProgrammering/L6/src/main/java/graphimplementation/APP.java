package graphimplementation;

public class APP {
    public static void main(String[] args) {
        EdgeListGraph<Double> graph = new EdgeListGraph<>();
        graph.addVertex(15.0);
        graph.addVertex(6.0);
        graph.addVertex(38.0);
        graph.addVertex(66.0);

        graph.addEdge(15.0, 6.0, 23);
        graph.addEdge(15.0, 38.0, 10);
        graph.addEdge(15.0, 66.0, 90);
        graph.addEdge(6.0, 66.0, 8);
        graph.addEdge(38.0, 66.0, 2);

        graph.addVertex(123.0);

        graph.addEdge(123.0, 38.0, 55);
        graph.addEdge(123.0, 66.0, 76);
        graph.addEdge(123.0, 6.0, 7);

        System.out.println(graph);

    }
}
