public class Node {
    private int id;
    private boolean mark;

    Node (int id) {
        this.id = id;
    }
    void markNode (boolean mark) {
        this.mark = mark;
    }

    boolean getMark() {
        return mark;
    }

    int getId() {
        return id;
    }

}
