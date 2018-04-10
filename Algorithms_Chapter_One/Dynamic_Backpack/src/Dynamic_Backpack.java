import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Dynamic_Backpack {
    private static int[] prev;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("/home/danil/Documents/JavaProjects/Stepik/Algorithms_Chapter_One/Dynamic_Backpack/input.txt"));
        String[] temp = reader.readLine().split(" ");
        int W = Integer.parseInt(temp[0]);
        int n = Integer.parseInt(temp[1]);
        int[] weight = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        //int[] cost = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] cost = new int[n];
        prev = new int[n];
        Arrays.fill(cost, 1);
        System.out.println(new Dynamic_Backpack().whithoutReps(W, weight, cost));
    }

    private int whithoutReps(int W, int[] weight, int[] cost) {
        int[][] D = new int[weight.length + 1][W + 1];
        for (int i = 0; i <= W; i++) {
            D[0][i] = 0;
        }
        for (int i = 0; i <= weight.length; i++) {
            D[i][0] = 0;
        }
        for (int i = 1; i <= weight.length; i++) {
            for (int w = 1; w <= W; w++) {
                D[i][w] = D[i - 1][w];
                if (weight[i - 1] <= w) {
                    D[i][w] = Math.max(D[i][w], D[i - 1][w - weight[i - 1]] + cost[i - 1]);
                }
            }
        }
        for (int i = 0; i <= weight.length; i++) {
            for (int j = 0; j <= W; j++) {
                System.out.print(D[i][j] + " ");
            }
            System.out.println();
        }
        return D[weight.length][W];
    }
}
