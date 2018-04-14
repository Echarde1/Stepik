import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Maximize {
    private static String expression;
    private static int[][] D;
    private static int n;
    private static int[] number;
    private static Character[] operation;

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader("/home/danil/Documents/JavaProjects/Stepik/Algorithms_Chapter_One/Maximize_Expression_With_Brackets/input.txt"));
        //BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        expression = input.readLine();
        double len = ((double)expression.length()) / 2;
        int operations_number = (int) Math.floor(len);
        n = (int) Math.ceil(len);

        operation = new Character[operations_number];
        number = new int[n];
        D = new int[n][n];

        fillArrays(number, operation);

        System.out.println(Arrays.toString(operation));
        System.out.println(Arrays.toString(number));

        fillTable();

        System.out.println();
        for (int[] temp : D) {
            System.out.println(Arrays.toString(temp));
        }
        System.out.println();
        System.out.println(expression);
        run();

    }

    private static void run() {
        int j;
        for (int L = 2; L <= n; L++) {
            for (int i = 0; i <= n - L; i++) {
                j = i + L - 1;
                //D[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int temp_max = 0;
                    if (operation[k] == '+') {
                        temp_max = D[i][k] + D[k + 1][j];
                    }
                    else if (operation[k] == '*') {
                        temp_max = D[i][k] * D[k + 1][j];
                    }
                    else if (operation[k] == '-') {
                        temp_max = D[i][k] - D[k + 1][j];
                    }
                    D[i][j] = Math.max(D[i][j], temp_max);
                }
            }
        }
        for (int[] temp : D) {
            System.out.println(Arrays.toString(temp));
        }
        System.out.println(D[0][n - 1]);
    }

    private static void fillTable() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                D[i][j] = -1;
                if (i == j) D[i][j] = number[i];
            }
        }
    }

    private static void fillArrays(int[] number, Character[] operation) {
        for (int i = 0; i < expression.length(); i++) {
            if (i % 2 == 0) {
                number[i / 2] = Integer.valueOf(String.valueOf(expression.charAt(i)));
            }
            else {
                operation[i / 2] = expression.charAt(i);
            }
        }
    }
}
