package Models;

public class Node {
    private final String id;
    private int x;
    private int y;

    // Construtor do vértice (id e posição no quadro)
    public Node(String id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public String getId() { return id; }

    public int getX() { return x; }

    public int getY() { return y; }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return id;
    }
}

