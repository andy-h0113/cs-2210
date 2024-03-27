public class Edge {
    private Node firstNode;
    private Node secondNode;
    private String type;

    Edge (Node u, Node v, String type) {
        firstNode = u;
        secondNode = v;
        this.type = type;
    }

    Node firstNode() {
        return firstNode;
    }

    Node secondNode() {
        return secondNode;
    }

    String getType() {
        return type;
    }

}
