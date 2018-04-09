import java.io.*;
import java.util.*;

public class Test {

    private static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader("/home/danil/Documents/JavaProjects/Stepik/Algorithms_Chapter_One/Segments_QuickSort_BinarySearch/input.txt"));
        //BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = input.readLine().split(" ");
        int n = Integer.parseInt(temp[0]); //Columns' number
        int k = Integer.parseInt(temp[1]); //Points number

        StringBuilder builder = new StringBuilder();
        StringBuilder builder2 = new StringBuilder();
        int[][] array = new int[n][2];

        array = new Test().fillArray(array, n, input);
        int[] points = new Test().fillPoints(k, input);



        Arrays.sort(array, Comparator.comparingInt(o -> o[0]));

        System.out.println(Arrays.deepToString(array));

        for (int i = 0; i < k; i++) {
            int[][] tempArray = array;
            builder.append(new Test().binarySearch(array, points[i], -1, array.length));
            builder.append(" ");
            array = tempArray;
            count = 0;
        }
        System.out.println(builder);
    }

    private int[][] fillArray(int[][] array, int n, BufferedReader input) throws IOException {
        for (int i = 0; i < n; i++) {
            String[] tempPoints = input.readLine().split(" ");
            array[i][0] = Integer.parseInt(tempPoints[0]);
            array[i][1] = Integer.parseInt(tempPoints[1]);
        }
        return array;
    }

    private int[] fillPoints(int k, BufferedReader input) throws IOException {
        String[] tempPoints = input.readLine().split(" ");
        int[] points = new int[k];
        for (int i = 0; i < k; i++) {
            points[i] = Integer.parseInt(tempPoints[i]);
        }
        return points;
    }

    private int binarySearch(int[][] array, int x, int l, int r) {

        while (r > l + 1) {
            int m = (l + r) >> 1;
            int[] temp = array[m];
            if (temp[0] <= x) {
                if (temp[1] >= x) {
                    count++;
                    //array = ArrayU
                    //r = array.size();
                    r = m;
                } else {
                    l = m;
                }
            } else {
                r = m;
            }
        }
        return count;
    }
}