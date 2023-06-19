package Utils;

public class MatrixGenerator {
    public static int[][] generateRandomGraph(int verticesAmount, int maxWeight, int zeroProb){

        if (verticesAmount < 0) {
            System.err.println("ERRO: Quantidade negativa de vértices:\nverticesAmount = " + verticesAmount);
            System.exit(1);
        }

        int[][] matrix = new int[verticesAmount][verticesAmount];

        for (int i = 0; i < verticesAmount; i++) {
            for (int j = 0; j < verticesAmount; j++) {
                if (i == j) matrix[i][j] = 0;
                else {
                    int rnd = (int) (Math.random() * 100);
                    int rndWeight = (int) (Math.random() * maxWeight);

                    matrix[i][j] = rnd < zeroProb ? 0 : rndWeight;

                    matrix[j][i] = matrix[i][j];
                }
            }
        }

        // Printando a matriz no console
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }

        return matrix;
    }
}
