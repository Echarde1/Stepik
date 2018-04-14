import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Calculator {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader("/home/danil/Documents/JavaProjects/Stepik/Algorithms_Chapter_One/Calculator_Dynamic/src/input.txt"));
        //BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(input.readLine());
        int[] D = new int[number + 1];

        for (int i = 2; i < number + 1; i++) {
            int min = D[i - 1] + 1;
            if (i % 3 == 0) min = Math.min(min, D[i / 3] + 1);
            if (i % 2 == 0) min = Math.min(min, D[i / 2] + 1);

            D[i] = min;
        }
        //System.out.println(Arrays.toString(D));
        StringBuilder stringBuilder = new StringBuilder();

        int i = number;
        while (i > 1) {
            if (D[i] == D[i - 1] + 1) {
                stringBuilder/*.insert(0, " ")*/
                        .insert(0, 1);
                i--;
                continue;
            }
            if (i % 2 == 0 && D[i] == D[i / 2] + 1) {
                stringBuilder/*.insert(0, " ")*/
                        .insert(0, 2);
                i /= 2;
                continue;
            }
            stringBuilder
                    .insert(0 , 3);
            i /= 3;
        }
        System.out.println(D[number]);
        //System.out.println(stringBuilder);
        int n = 1;
        StringBuilder sb = new StringBuilder();
        sb.append(n).append(" ");
        for (int j = 0; j < stringBuilder.length(); j++) {
            int a = Integer.parseInt(String.valueOf(stringBuilder.charAt(j)));
            switch (a) {
                case 1 :
                    n++;
                    break;
                case 2 :
                    n *= 2;
                    break;
                case 3 :
                    n *= 3;
                    break;
            }
            sb.append(n).append(" ");
        }
        System.out.println(sb);
    }
}