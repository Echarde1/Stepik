import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Optimized {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader("/home/danil/Documents/JavaProjects/Stepik/Algorithms_Chapter_One/LargestIncreasingSequence/input.txt.txt"));
        //BufferedReader input.txt = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(input.readLine());
        int[] a = Arrays.stream(input.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] D = new Optimized().lis(a);
        System.out.println("Массив а: " + Arrays.toString(a));
        System.out.println("Массив D: " + Arrays.toString(D));
        for (int aD : D) {
            System.out.print(a[aD - 1] + " ");
        }
    }
    private int[] lis(int[] a) {
        int n = a.length;

        int[] tailIndexes = new int[n];
        //индекс i tailIndexes показывает
        //длину подпоследовательности(len = i + 1), а
        //tailIndexes[i] показывает последний
        //индекс элемента a[i] в подпоследовательности
        //длинной i.

        int[] prevIndexes = new int[n];
        //prevIndexes показывает индексы элементов a[i - 1]
        //длинной i.

        int len = 0;
        for (int i = 0; i < n; i++) {
            //Багодаря тому, что tailIndexes[i] хранит
            //длину подмассива (len = i + 1) мы можем
            //использовать бинарный поиск
            int pos = lower_bound(a, tailIndexes, len, a[i]);

            if (pos == len) {
                ++len;
            }
            //Необходимое условие, чтобы обновить tailIndexes[],

            prevIndexes[i] = pos > 0 ? tailIndexes[pos - 1] : -1;
            tailIndexes[pos] = i;
        }

        int[] res = new int[len];
        for (int i = tailIndexes[len - 1]; i >= 0; i = prevIndexes[i]) {
            res[--len] = i + 1;
        }
        System.out.println("Массив tailIndices: " + Arrays.toString(tailIndexes));
        System.out.println("Массив prevIndexes: " + Arrays.toString(prevIndexes));
        return res;
    }

    private int lower_bound(int[] a, int[] tail, int len, int key) {
        int lo = -1;
        int hi = len;
        while (hi - lo > 1) {
            int mid = (lo + hi) >>> 1;
            if (a[tail[mid]] >= key) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        return hi;
    }

}
