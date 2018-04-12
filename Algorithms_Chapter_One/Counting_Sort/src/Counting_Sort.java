import java.io.*;
import java.util.Arrays;

public class Counting_Sort {
    public static void main(String args[]) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("/home/danil/Documents/JavaProjects/Stepik/Counting_Sort/src/input.txt.txt"));
        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] a = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] A = new int[n];
        int[] result = new int[10];
        for (int i = 0; i < n; i++) {
            result[a[i] - 1] += 1;
        }
        System.out.println(Arrays.toString(result));
        for (int i = 1; i < 10; i++) {
            result[i] = result[i] + result[i - 1];
        }
        System.out.println(Arrays.toString(result));
        System.out.println();
        System.out.println(Arrays.toString(a));
        for (int i = n - 1; i >= 0; i--) {
            A[result[a[i] - 1] - 1] = a[i];
            result[a[i] - 1] = result[a[i] - 1] - 1;
        }
        System.out.println(Arrays.toString(A));
    }
}
