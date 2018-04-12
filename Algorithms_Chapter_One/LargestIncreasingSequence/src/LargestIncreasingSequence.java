import java.io.*;
import java.util.Arrays;

public class LargestIncreasingSequence {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader("/home/danil/Documents/JavaProjects/Stepik/LargestIncreasingSequence/input.txt.txt"));
        //BufferedReader input.txt = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(input.readLine());
        int[] a = Arrays.stream(input.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] D = new int[n];
        int[] prev = new int[n]; //Массив для хранения индексов предыдущего жлемента, соответствующего наибольшей подпоследовательности
        //System.out.println("Массив а = " + Arrays.toString(a));
        for (int i = 0; i < n; i++) {
            D[i] = 1; prev[i] = - 1;
            for (int j = 0; j < i; j++) {
                if (/*a[j] >= a[i] &&*/ //Для посчета наибольшей невозрастающая подпоследовательность
                    a[j] < a[i] && //Для подсчета возрастающей подпоследовательности максимальной длины
                        /*a[i] % a[j] == 0 && */ //Для подсчета последовательнократной подпоследовательности максимальной длины
                        D[j] + 1 > D[i]
                        )  {
                    D[i] = D[j] + 1;
                    prev[i] = j + 1;
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, D[i]);
        }

        System.out.println("ans = " + ans);
        System.out.println("Массив D = " + Arrays.toString(D));
        System.out.println("Массив prev = " + Arrays.toString(prev));

        int[] L = new int[ans]; //Массив для индексов элементов возрастающей подпоследовательности
        int k = 0;

        for (int i = 0; i < n; i++) {
            if (D[i] > D[k]) k = i;
        }

        int j = ans - 1;
        while (j >= 0) {
            L[j] = k + 1;
            j = j - 1;
            k = --prev[k];
        }

        System.out.println(Arrays.toString(L));
        System.out.println("Массив L = " + Arrays.toString(L));
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = ans - 1; i >= 0; i--) {
            stringBuilder.append(a[L[i]]).append(" ");
        }

        System.out.println("Есть вот такая последовательность: " + stringBuilder); //Последовательностей может быть несколько
    }
}