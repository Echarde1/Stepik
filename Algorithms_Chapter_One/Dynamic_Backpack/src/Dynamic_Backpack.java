import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Dynamic_Backpack {
    private static int[] prev;

    public static void main(String[] args) throws IOException {
        //BufferedReader reader = new BufferedReader(new FileReader("/home/danil/Documents/JavaProjects/Stepik/Algorithms_Chapter_One/Dynamic_Backpack/input.txt.txt"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = reader.readLine().split(" ");
        int W = Integer.parseInt(temp[0]);
        int n = Integer.parseInt(temp[1]);
        long[] weight = Arrays.stream(reader.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        //int[] cost = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        long[] cost = new long[n];
        System.arraycopy(weight, 0, cost, 0, n);
        //prev = new int[n];
        System.out.println(new Dynamic_Backpack().whithoutReps(W, weight, cost));
    }

    private long whithoutReps(int W, long[] weight, long[] cost) {
        long[][] D = new long[weight.length + 1][W + 1];
        for (int i = 0; i <= W; i++) {
            D[0][i] = 0;
        }
        for (int i = 0; i <= weight.length; i++) {
            D[i][0] = 0;
        }
        /*System.out.println("Веса предметов: " + Arrays.toString(weight));
        System.out.println("Стоимости предметов: " + Arrays.toString(cost));*/
        for (int i = 1; i <= weight.length; i++) {
            for (long w = 1; w <= W; w++) {
                D[i][(int) w] = D[i - 1][(int) w];
                if (weight[i - 1] <= w) {
                    D[i][(int) w] = Math.max(D[i][(int) w], D[i - 1][(int) (w - weight[i - 1])] + cost[i - 1]);
                }
            }
        }
        /*for (int i = 0; i <= weight.length; i++) {
            for (int j = 0; j <= W; j++) {
                System.out.print(D[i][j] + " ");
            }
            System.out.println();
        }*/
        /*int j = W;
        for (int i = weight.length; i > 0 || D[i][j] != 0; ) {
            if (j - weight[i - 1] >= 0 && D[i][j] - cost[i - 1] == D[i - 1][j - weight[i - 1]]) {
                prev[i - 1]++;
                j = j - weight[i - 1];
                i--;
            } else if (D[i][j] == D[i - 1][j]) {
                j = W - weight[i - 1];
                i--;
            }
        }*/
        //System.out.println(Arrays.toString(prev));
        return D[weight.length][W];
    }
}
