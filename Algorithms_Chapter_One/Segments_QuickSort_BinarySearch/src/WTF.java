import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class WTF {
    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner sc = new Scanner(new File("/home/danil/Documents/JavaProjects/Stepik/Algorithms_Chapter_One/Segments_QuickSort_BinarySearch/input.txt.txt"))) {
            int n = sc.nextInt(); //Segments number
            int k = sc.nextInt(); //Points number
            int[] lefts = new int[n];
            int[] rights = new int[n];
            ArrayList<int[]> segments = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                lefts[i] = sc.nextInt();
                rights[i] = sc.nextInt();
                segments.add(new int[]{lefts[i], rights[i]});
            }

            segments.sort(Comparator.comparingInt(o -> o[0]));

            for (int[] temp : segments) {
                System.out.println(temp[0] + " " + temp[1]);
            }

            sort(lefts);
            sort(rights);
            System.out.println(Arrays.toString(lefts));
            System.out.println(Arrays.toString(rights));
            for (int i = 0; i < k; i++) {
                int point = sc.nextInt();
                if (point < lefts[0] || point > rights[n - 1]) {
                    System.out.print(0 + " ");
                } else {
                    int raz = binarySearch(lefts, point, true);
                    int dva = binarySearch(rights, point, false);
                    /*System.out.print((binarySearch(lefts, point, true) + 1)
                            - (binarySearch(rights, point, false) + 1) + " ");*/
                    System.out.print(raz - dva + " ");
                }
                /*System.out.print((point < lefts[0] || point > rights[n - 1]) ? 0 + " "
                        : ((binarySearch(lefts, point, true) + 1) - (binarySearch(rights, point, false) + 1)) + " ");*/
            }
        }
    }

    private static int binarySearch(int[] a, int x, boolean include) {
        int l = -1;
        int r = a.length;
        while (r > l + 1) {
            int m = (int)(Math.ceil((double)l + ((r - l) / 2)));
            if (include) {
                if (a[m] <= x) l = m;
                else r = m;
            } else {
                if (a[m] < x) l = m;
                else r = m;
            }
        }
        return r;
    }

    private static void sort(int a[]) {
        if (a.length > 1) {
            sort(a, 0, a.length - 1);
        }
    }

    private static void sort(int a[], int l, int r) {
        while (l < r) {
            int[] m = partition(a, l, r);
            if (m[0] - l <= r - m[1]) {
                sort(a, l, m[0] - 1);
                l = m[1] + 1;
            } else {
                sort(a, m[1] + 1, r);
                r = m[0] - 1;
            }
        }
    }

    private static int[] partition(int[] a, int l, int r) {
        //a possible median
        int mInd = (l + r) >> 1;
        int medianInd;
        if ((a[l] >= a[r] && a[l] <= a[mInd]) || (a[l] >= a[mInd] && a[l] <= a[r]))
            medianInd = l;
        else if ((a[r] >= a[l] && a[r] <= a[mInd]) || (a[r] >= a[mInd] && a[r] <= a[l]))
            medianInd = r;
        else
            medianInd = mInd;
        swap(a, l, medianInd);

        int x = a[l];
        int k = l;
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (a[i] <= x) {
                k ++;
                swap(a, k, i);
                if (a[k] < x) {
                    j ++;
                    swap(a, j, k);
                }
            }
        }
        swap(a, l, j);
        return new int[] {j, k};    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
