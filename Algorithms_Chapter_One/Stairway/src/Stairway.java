import javafx.print.Printer;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Stairway {

    public static void main(String[] args) throws IOException {

        //BufferedReader reader = new BufferedReader(new FileReader("/home/danil/Documents/JavaProjects/Stepik/Algorithms_Chapter_One/Stairway/input.txt.txt"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine()); //Number of stairs
        String[] temp = reader.readLine().split(" ");
        int[] D = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            D[i] = Integer.parseInt(temp[i - 1]);
        }
        int[] nominals = D;

        //System.out.println(new Stairway().stairway(D, nominals, n));

    }

    public int stairway(int[] D, int[] nominals,int n) {
        System.out.println(Arrays.toString(D));

        for (int i = 2; i <= n; ++i) {
            D[i] = Math.max(D[i - 1] + nominals[i], D[i - 2] + nominals[i]);
        }
        return D[n];
    }
}
