package UI;

import Models.Graph;
import Models.Node;
import Utils.MatrixGenerator;

import javax.swing.*;

public class GraphCreator extends JFrame {

    // Matriz de adjacência forçada para gerar o grafo
    // caso queira rodar com uma matriz específica

    /* public static final int[][] ADJACENCY_MATRIX = {
            { 0, 0, 3, 0, 0, 2, 0 },
            { 0, 0, 0, 1, 2, 6, 2 },
            { 3, 0, 0, 4, 1, 2, 0 },
            { 0, 1, 4, 0, 0, 0, 0 },
            { 0, 2, 1, 0, 0, 3, 0 },
            { 2, 6, 2, 0, 3, 0, 5 },
            { 0, 2, 0, 0, 0, 5, 0 },
    }; */

    private static final int WINDOW_WIDTH = 1600;
    private static final int WINDOW_HEIGHT = 900;
    private static final int VERTICES = 6; // com 15 vértices já começa a lagar um pouco dependendo da quantidade de conexões
    private static final int MAX_WEIGHT = 10;
    private static final int ZERO_PROBABILITY = 30;
    public static final int[][] ADJACENCY_MATRIX = MatrixGenerator.generateRandomGraph(VERTICES, MAX_WEIGHT, ZERO_PROBABILITY);

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

        int verticesAmount = ADJACENCY_MATRIX.length;

        // Loop para criação de nós (vértices)
        for (int i = 0; i < verticesAmount; i++) {
            // Inicializa valores aleatórios para a posição x e y de cada vértice
            // e atribui o "nome" do vértice como sua posição "i" e atribui as posições
            int x = (int) (Math.random() * (WINDOW_WIDTH - 100));
            int y = (int) (Math.random() * (WINDOW_HEIGHT - 100));
            Node node = new Node(String.valueOf(i+1), x, y);
            graph.addNode(node);
        }

        // Loop aninhado que irá verificar se existe uma conexão entre dois vértices
        for (int i = 0; i < verticesAmount; i++) {
            for (int j = i + 1; j < verticesAmount; j++) {
                if (ADJACENCY_MATRIX[i][j] != 0) {
                    int id1 = i+1;

                    // Faz uma busca no grafo para verificar se o vértice com id "i" existe
                    // e retorna a primeira ocorrência (única possível)
                    // caso contrário, define node1 como null
                    Node node1 = graph.getNodes().stream()
                            .filter(n -> n.getId().equals(String.valueOf(id1)))
                            .findFirst()
                            .orElse(null);

                    int id2 = j + 1;

                    Node node2 = graph.getNodes().stream()
                            .filter(n -> n.getId().equals(String.valueOf(id2)))
                            .findFirst()
                            .orElse(null);

                    // Verifica se ambos os vértices não são nulos
                    if (node1 != null && node2 != null) {
                        // e estabelece uma conexão entre eles
                        graph.addConnection(node1, node2);
                    }
                }
            }
        }

        return graph;
    }
}
