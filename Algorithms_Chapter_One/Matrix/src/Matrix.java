import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Matrix {
    private static int[][] D;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader("/home/danil/Documents/JavaProjects/Stepik/Algorithms_Chapter_One/Matrix/input.txt"));
        n = Integer.parseInt(input.readLine());
        D = new int[n][n];
        int[] m = Arrays.stream(input.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        new Matrix().fillTable();

        int j;
        for (int L = 2; L < n; L++) {
            for (int i = 1; i < n - L + 1; i++) {
                j = i + L - 1;
                if (j == n) continue;
                D[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++) {
                    D[i][j] = Math.min(D[i][j], D[i][k] + D[k + 1][j] + m[i - 1]*m[k]*m[j]);
                }
            }
        }
        System.out.println(D[1][n - 1]);

        for (int[] row : D) {
            System.out.println(Arrays.toString(row));
        }

    }
    private void fillTable() {
        for (int i = 1; i < n; i++) {
            D[i][i] = 0;
        }
    }
}
