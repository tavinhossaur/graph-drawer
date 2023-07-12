package Models;

import java.util.*;

public class Graph {
    private final Map<Node, List<Node>> connectionsList;

    // A representação do grafo no código é um HashMap em que "key" é um vértice x
    // e o "value" é um ArrayList de todas as conexões (vértices adjacentes) do vértice x
    public Graph() {
        connectionsList = new HashMap<>();
    }

    // Adiciona um vértice e suas conexões ao Mapa de conexões do grafo
    public void addNode(Node node) {
        connectionsList.put(node, new ArrayList<>());
    }

    // Retorna a lista de conexões de um vértice, adiciona o outro vértice
    // que faz conexão com ele nessa lista
    public void addConnection(Node node1, Node node2) {
        connectionsList.get(node1).add(node2);
    }

    public List<Node> getConnectedNodes(Node node) {
        return connectionsList.get(node);
    }

    public Set<Node> getNodes() {
        return connectionsList.keySet();
    }
}
