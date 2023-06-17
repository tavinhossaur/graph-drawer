package UI;

import Models.Graph;
import Models.Node;

import javax.swing.*;

public class GraphCreator extends JFrame {

    // Altura e largura da janela
    private static final int WINDOW_WIDTH = 1920;
    private static final int WINDOW_HEIGHT = 1080;

    public GraphCreator() {
        // Criação de um grafo
        Graph graph = makeGraph();

        setTitle("Graph Drawer");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null); // Cria a janela no centro da tela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Adiciona o grafo criado para o quadro onde ele será desenhado
        GraphDrawer graphDrawer = new GraphDrawer(graph);
        add(graphDrawer);
    }

    private Graph makeGraph() {
        Graph graph = new Graph();

        // Matriz de adjacência utilizada para gerar o grafo
        int[][] adjacencyMatrix = {
                { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
                { 4, 0, 8, 0, 0, 0, 0, 5, 0 },
                { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
                { 0, 0, 7, 0, 9, 6, 0, 0, 0 },
                { 0, 0, 0, 9, 0, 1, 0, 0, 0 },
                { 0, 0, 4, 6, 1, 0, 2, 0, 0 },
                { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
                { 8, 5, 0, 0, 0, 0, 1, 0, 7 },
                { 0, 0, 2, 0, 0, 0, 6, 7, 0 }
        };

        int numVertices = adjacencyMatrix.length;

        // Loop para criação de nós (vértices)
        for (int i = 0; i < numVertices; i++) {
            // Inicializa valores aleatórios para a posição x e y de cada vértice
            // e atribui o "nome" do vértice como sua posição "i" e atribui as posições
            int x = (int) (Math.random() * (WINDOW_WIDTH - 100));
            int y = (int) (Math.random() * (WINDOW_HEIGHT - 100));
            Node node = new Node(String.valueOf(i+1), x, y);
            graph.addNode(node);
        }

        // Loop aninhado que irá verificar se existe uma conexão entre dois vértices
        for (int i = 0; i < numVertices; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                if (adjacencyMatrix[i][j] != 0) {
                    int id1 = i+1;

                    // Faz uma busca no grafo para verificar se o vértice com id "i" existe
                    // e retorna a primeira ocorrência (única possível)
                    // caso contrário, define node1 como null
                    Node node1 = graph.getVertices().stream()
                            .filter(n -> n.getId().equals(String.valueOf(id1)))
                            .findFirst()
                            .orElse(null);

                    int id2 = j + 1;

                    Node node2 = graph.getVertices().stream()
                            .filter(n -> n.getId().equals(String.valueOf(id2)))
                            .findFirst()
                            .orElse(null);

                    // Verifica se ambos os vértices não são nulos
                    if (node1 != null && node2 != null) {
                        // e estabelece uma conexão entre eles
                        graph.addEdge(node1, node2);
                    }
                }
            }
        }

        return graph;
    }
}
