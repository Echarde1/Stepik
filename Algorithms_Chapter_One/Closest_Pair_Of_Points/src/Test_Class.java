import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test_Class {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scn = new Scanner(new File("/home/danil/Documents/JavaProjects/Stepik/Algorithms_Chapter_One/Closest_Pair_Of_Points/input.txt"));
        double[] answers = new double[]{1.414213562, 1.000000000, 1.000000000, 1.414213562, 5.000000000};
        int k = 0;
        while(scn.hasNext()) {
            scn.nextLine();
            int n = Integer.parseInt(scn.nextLine());
            Point[] P = new Point[n];
            for (int i = 0; i < n; i++) {
                String[] temp = scn.nextLine().split(" ");
                P[i] = new Point(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
            }
            String s1 = String.format("%.9f", Closest_Pair_Of_Points.closest(P, n));
            String s2 = String.format("%.9f", answers[k]);
            System.out.println(s1.equals(s2));
            k++;
        }
    }
}
