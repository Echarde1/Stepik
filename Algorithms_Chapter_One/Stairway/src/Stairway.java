import javafx.print.Printer;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Stairway {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("/home/danil/Documents/JavaProjects/Stepik/Algorithms_Chapter_One/Stairway/input.txt"));
        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine()); //Number of stairs
        String[] temp = reader.readLine().split(" ");
        int[] D = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            D[i] = Integer.parseInt(temp[i - 1]);
        }

        System.out.println(Arrays.toString(D));

        for (int i = 0; i < n; i++) {
            if (i == n - 1) {
                D[i + 1] += D[i];
                break;
            }

            int oneStairTwice = D[i] + D[i + 1] + D[i + 2];
            int twoStairs = D[i] + D[i + 2];
            if (oneStairTwice >= twoStairs) {
                D[i + 2] = oneStairTwice;
                i++;
            } else {
                D[i + 2] = twoStairs;
                i++;
            }
        }
        System.out.println(D[n]);
    }
}
