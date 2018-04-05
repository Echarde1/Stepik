import java.io.*;
import java.util.Arrays;

public class Inversions {

    private int[] temp;
    private int[] a;

    private void merge(/*int[] a, int[] b*/int l, int m, int r) {
        //a[l..m - 1] a[m.. r - 1] -> temp[l..r - 1] -> a[l..r - 1]

        int i = l; //Индекс первого неизпользованного элемента в левой части
        int j = m; //Индекс первого неизпользованного элемента в правой части
        for (int k = l; k < r; k++) {
            if (j == r || (i < m && a[i] <= a[j])) {
                temp[k] = a[i];
                i++;

            } else {
                //a[i, i + 1 .. m - 1] > b[j]
                count += m - i;
                temp[k] = a[j];
                j++;
            }
        }
        System.arraycopy(temp, l, a, l, r - l);
    }

    private void run() throws IOException {
        BufferedReader input = new BufferedReader(new FileReader("input.txt"));
        int n = Integer.parseInt(input.readLine());
        a = new int[n];
        String[] tokens = input.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(tokens[i]);
        }
        temp = new int[n]; //Буфер
        //int[] sortedA = mergerSort(a);
        mergerSort(0, n);
        System.out.println(Arrays.toString(a));
        System.out.println(count);
    }

    long count = 0;

    private void mergerSort(/*int[] a*/int l, int r) {
        if (/*a.length == 1*/r <= l + 1) return;
        //a[l .. r - 1] -> a[l .. m - 1] a[m .. r - 1]
        int m = (l + r) >> 1;
        //left = mergerSort(left);
        mergerSort(l, m);
        //rigth = mergerSort(rigth);
        mergerSort(m, r);
        merge(l, m, r);
    }

    public static void main(String args[]) throws IOException {
        long startTime = System.currentTimeMillis();
        new Inversions().run();
        long finishTime = System.currentTimeMillis();
        System.out.println(finishTime - startTime + " ms");
    }
}
