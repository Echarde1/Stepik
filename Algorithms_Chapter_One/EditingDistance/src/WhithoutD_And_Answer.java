import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WhithoutD_And_Answer {

    private void run() throws FileNotFoundException {
        Scanner input = new Scanner(new File("/home/danil/Documents/JavaProjects/Stepik/Algorithms_Chapter_One/EditingDistance/input.txt.txt"));
        //Scanner input.txt = new Scanner(System.in);
        String first = input.next();
        String second = input.next();
        //d = new int[first.length() + 1][second.length() + 1];
        int[] cur = new int[second.length() + 1];
        int[] prev = new int[second.length() + 1];


        for (int n = 0; n <= first.length(); n++) {
            for (int m = 0; m <= second.length(); m++) {
                if (n == 0 && m == 0) {
                    cur[m] = 0;
                } else {
                    int result = Integer.MAX_VALUE;

                    if (m > 0 )
                        result = Math.min(result, cur[m - 1] + 1);
                    if (n > 0)
                        result = Math.min(result, prev[m] + 1);
                    if (n > 0 && m > 0)
                        result = Math.min(result, prev[m - 1] +
                            (first.charAt(n - 1) == second.charAt(m - 1) ? 0 : 1));

                    cur[m] = result;
                }
            }
            int[] temp = cur;
            cur = prev;
            prev = temp;
        }
        System.out.println(prev[second.length()]);
    }

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        new WhithoutD_And_Answer().run();
        long finishTime = System.currentTimeMillis();
        System.out.println(finishTime - startTime + " ms");
    }
}

