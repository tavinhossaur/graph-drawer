package Utils;

import javax.swing.text.DefaultStyledDocument;
import java.util.Arrays;

import static UI.GraphCreator.ADJACENCY_MATRIX;

public class DijkstraAlgorithm {

    private static final int MAX_VERTICES = ADJACENCY_MATRIX.length;

    public static void runDijkstra(){

        int[] dist = new int[MAX_VERTICES];

        Boolean[] tree = new Boolean[MAX_VERTICES];

        // Preenche ambos os arrays com valores padrão
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(tree, false);

        // A distância do vértice de origem em relação à ele mesmo é sempre 0
        dist[0] = 0;

        // Encontra o menor caminho para todos os vértices
        for (int count = 0; count < MAX_VERTICES - 1; count++) {
            int currentNode = minDistance(dist, tree);

            // Marca o vértice atual como atualizado
            tree[currentNode] = true;

            // Atualiza o valor de distância dos vértices adjacentes ao vértice escolhido.
            for (int adjacentVertice = 0; adjacentVertice < MAX_VERTICES; adjacentVertice++)
                if (
                        !tree[adjacentVertice]
                        && ADJACENCY_MATRIX[currentNode][adjacentVertice] != 0
                        && dist[currentNode] != Integer.MAX_VALUE
                        && dist[currentNode] + ADJACENCY_MATRIX[currentNode][adjacentVertice] < dist[adjacentVertice]
                ) {
                    dist[adjacentVertice] = dist[currentNode] + ADJACENCY_MATRIX[currentNode][adjacentVertice];
                }
        }

        showSolution(dist);
    }

    private static int minDistance(int[] dist, Boolean[] tree)
    {
        // Inicializa o valor mínimo
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < MAX_VERTICES; v++)
            if (!tree[v] && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }

    private static void showSolution(int[] dist)
    {
        System.out.println("\nVértice\t\tMenor distância percorrida\n");
        for (int i = 0; i < MAX_VERTICES; i++)
        {
            if (dist[i] == Integer.MAX_VALUE) System.out.println("1 -> " + (i+1) + "\t\tNão há caminhos possíveis");
            else System.out.println("1 -> " + (i+1) + "\t\t" + dist[i]);
        }


        System.out.println("\n");
    }

}
