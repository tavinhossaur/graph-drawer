package UI;

import Models.Graph;
import Models.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GraphDrawer extends JPanel {
    private final Graph graph;
    private Node selectedNode;

    // Construtor do quadro do grafo
    public GraphDrawer(Graph graph) {
        this.graph = graph;
        selectedNode = null;

        // Listener para selecionar o vértice que for clicado
        // e desselecionar quando soltar
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent m) {
                // Procura qual vértice se encontra na mesma posição do clique
                int x = m.getX();
                int y = m.getY();
                selectedNode = findNode(x, y);
            }

            @Override
            public void mouseReleased(MouseEvent m) {
                selectedNode = null;
            }
        });

        // Listener para alterar a posição do vértice selecionado
        // quando o mesmo for arrastado após ter sido clicado
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent m) {
                // Verifica se há algum vértice selecionado
                if (selectedNode != null) {
                    // Altera (redesenha) sua posição com base na posição em tempo real do mouse
                    int x = m.getX();
                    int y = m.getY();
                    selectedNode.setPosition(x, y);
                    repaint();
                }
            }
        });
    }

    // Procura qual vértice se encontra nas posições x e y do quadro
    public Node findNode(int x, int y) {
        // Para cada vértice, verifica se as posições estão dentro de um range superior e inferior de 10px
        for (Node node : graph.getVertices()) {
            int nodeX = node.getX();
            int nodeY = node.getY();
            if (x >= nodeX - 10 && x <= nodeX + 10 && y >= nodeY - 10 && y <= nodeY + 10) {
                return node;
            }
        }
        return null;
    }

    @Override
    // Função que desenha os vértices e conexões no quadro
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Para cada vértice, retorna sua posição e desenha um círculo com sua ID no meio
        for (Node node : graph.getVertices()) {
            int x = node.getX();
            int y = node.getY();
            g.drawOval(x - 10, y - 10, 20, 20);
            g.drawString(node.getId(), x - 5, y + 5);
        }

        // Para cada vértice, retorna a sua posição, e a posição de cada vértice que tem uma conexão com ele
        for (Node node : graph.getVertices()) {
            int startX = node.getX();
            int startY = node.getY();

            for (Node connectedNode : graph.getConnectedNodes(node)) {
                int endX = connectedNode.getX();
                int endY = connectedNode.getY();

                // Desenha uma linha que vai da posição do primeiro vértice até o vértice que ele possui conexão
                g.drawLine(startX, startY, endX, endY);
            }
        }
    }
}
