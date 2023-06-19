package UI;

import Models.Graph;
import Models.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import static UI.GraphCreator.ADJACENCY_MATRIX;

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
        for (Node node : graph.getNodes()) {
            int nodeX = node.getX();
            int nodeY = node.getY();
            if (x >= nodeX - 10 && x <= nodeX + 55 && y >= nodeY - 10 && y <= nodeY + 55) {
                return node;
            }
        }
        return null;
    }

    @Override
    // Função que desenha os vértices e conexões no quadro
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Para cada vértice, retorna a sua posição, e a posição de cada vértice que tem uma conexão com ele
        for (Node node : graph.getNodes()) {
            int startX = node.getX() + 15;
            int startY = node.getY() + 15;

            int i = Integer.parseInt(node.getId()) - 1;

            for (Node connectedNode : graph.getConnectedNodes(node)) {
                int endX = connectedNode.getX() + 15;
                int endY = connectedNode.getY() + 15;

                int j = Integer.parseInt(connectedNode.getId()) - 1;

                // Fazendo a linha ficar mais expessa
                Graphics2D g2d = (Graphics2D) g;
                g2d.setStroke(new BasicStroke(3));

                // Desenha uma linha que vai da posição do primeiro vértice até o vértice que ele possui conexão
                g2d.drawLine(startX, startY, endX, endY);

                File file1 = new File("res/fonts/Monaco.ttf");

                try {
                    Font font1 = Font.createFont(Font.TRUETYPE_FONT, file1).deriveFont(25F);
                    g.setFont(font1);
                } catch (FontFormatException | IOException e) {
                    throw new RuntimeException(e);
                }

                int x = ((startX + endX) / 2) - 15;
                int y = ((startY + endY) / 2) - 15;

                g.drawString(String.valueOf(ADJACENCY_MATRIX[i][j]), x, y);
            }
        }

        // Para cada vértice, retorna sua posição e desenha um círculo com sua ID no meio
        for (Node node : graph.getNodes()) {
            int x = node.getX();
            int y = node.getY();

            Color purple = new Color(145, 103, 245);

            g.setColor(purple);
            g.fillOval(x - 13, y - 13, 56, 56);
            g.setColor(Color.white);
            g.fillOval(x - 10, y - 10, 50, 50);

            File file = new File("res/fonts/Pacifico-Regular.ttf");

            try {
                Font font = Font.createFont(Font.TRUETYPE_FONT, file).deriveFont(30F);
                g.setFont(font);
            } catch (FontFormatException | IOException e) {
                throw new RuntimeException(e);
            }

            g.setColor(Color.black);

            // Ajusta a posição da id dentro do vértice, caso o tamanho da id seja maior que 10 (2 caracteres)
            x += Integer.parseInt(node.getId()) >= 10 ? 2 : 5;

            g.drawString(node.getId(), x, y + 24);
        }
    }
}
