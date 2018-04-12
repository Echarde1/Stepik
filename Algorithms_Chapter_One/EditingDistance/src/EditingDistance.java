import javafx.scene.transform.Scale;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class EditingDistance {

    private String first;
    private String second;

    private int[][] d;
    boolean[][] calculated;

    private int editingDistance (int n, int m) {
        //first[0..n-1], seconds[0..m-1]
        if (n == 0 && m == 0) return 0;
        if (n == 0) return m;
        if (m == 0) return n;
        if (calculated[n][m]) {
            return d[n][m];
        }
        int res1 = editingDistance(n, m - 1) + 1;
        int res2 = editingDistance(n - 1, m) + 1;
        int res3 = editingDistance(n - 1, m - 1) + (first.charAt(n - 1) == second.charAt(m - 1) ? 0 : 1);
        int result = Math.min(Math.min(res1, res2), res3);
        calculated[n][m] = true;
        d[n][m] = result;
        return result;
    }

    private void run() throws FileNotFoundException {
        Scanner input = new Scanner(new File("/home/danil/Documents/JavaProjects/Stepik/Algorithms_Chapter_One/EditingDistance/input.txt.txt"));
        first = input.next();
        second = input.next();
        d = new int[first.length() + 1][second.length() + 1];
        calculated = new boolean[first.length() + 1][second.length() + 1];
        System.out.println(editingDistance(first.length(), second.length()));
    }

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        new EditingDistance().run();
        long finishTime = System.currentTimeMillis();
        System.out.println(finishTime - startTime + " ms");
    }
}
