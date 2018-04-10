import java.util.Scanner;
import java.util.stream.IntStream;

public class Lambdas {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int W = sc.nextInt();
            int n = sc.nextInt();
            System.out.println(knapsackWithoutRepsBU(W, IntStream.generate(sc::nextInt).limit(n).toArray()));
        }
    }

    private static int knapsackWithoutRepsBU(int W, int[] ws) {
        int n = ws.length;
        int[][] D = new int[W + 1][n + 1];
        IntStream.rangeClosed(0, W).forEach(w -> D[w][0] = 0);
        IntStream.rangeClosed(0, n).forEach(i -> D[0][i] = 0);
        IntStream.rangeClosed(1, n).forEach(i -> IntStream.rangeClosed(1, W).forEach(w -> {
            D[w][i] = D[w][i - 1];
            if (ws[i - 1] <= w)
                D[w][i] = Integer.max(D[w][i], D[w - ws[i - 1]][i - 1] + ws[i - 1]);
        }));
        return D[W][n];
    }
}