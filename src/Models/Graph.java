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
    // que faz conexão com ele nessa lista e vice-versa
    public void addEdge(Node node1, Node node2) {
        connectionsList.get(node1).add(node2);
        connectionsList.get(node2).add(node1);
    }

    // Função para remover a conexão de um vértice com outro
    public void removeEdge(Node node1, Node node2) {
        connectionsList.get(node1).remove(node2);
        connectionsList.get(node2).remove(node1);
    }

    public List<Node> getConnectedNodes(Node node) {
        return connectionsList.get(node);
    }

    public Set<Node> getVertices() {
        return connectionsList.keySet();
    }
    
    public boolean isEmpty() {
        return connectionsList.isEmpty();
    }

    public int size() {
        return connectionsList.size();
    }
}
