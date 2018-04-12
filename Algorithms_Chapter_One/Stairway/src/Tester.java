import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tester {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scn = new Scanner(new File("/home/danil/Documents/JavaProjects/Stepik/Algorithms_Chapter_One/Stairway/input.txt.txt"));
        int k = 0;
        int[] answers = new int[]{-205, 32, 5, -2, -1000, 1, 21, -73, 3, 3};
        while (scn.hasNext()) {
            int n = scn.nextInt();
            int[] D = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                D[i] = scn.nextInt();
            }
            int[] nominals = D;
            System.out.println(new Stairway().stairway(D, nominals, n) + " " + answers[k]);
            k++;
        }
    }
}
