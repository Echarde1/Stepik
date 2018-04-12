import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class OptimizedDistance_Answer {

        private String first;
        private String second;

        private int[][] d;

        private void run() throws FileNotFoundException {
            Scanner input = new Scanner(new File("/home/danil/Documents/JavaProjects/Stepik/Algorithms_Chapter_One/EditingDistance/input.txt.txt"));
            //Scanner input.txt = new Scanner(System.in);
            first = input.next();
            second = input.next();
            d = new int[first.length() + 1][second.length() + 1];

            boolean[][] moveFirst = new boolean[first.length() + 1][second.length() + 1];
            boolean[][] moveSecond = new boolean[first.length() + 1][second.length() + 1];

            for (int n = 0; n <= first.length(); n++) {
                for (int m = 0; m <= second.length(); m++) {
                    if (n == 0 && m == 0) d[n][m] = 0;
                    else if (n == 0) {
                        moveSecond[n][m] = true;
                        d[n][m] = m;
                    }
                    else if (m == 0) {
                        moveFirst[n][m] = true;
                        d[n][m] = n;
                    }
                    else {
                        int res1 = d[n][m - 1] + 1;
                        int res2 = d[n - 1][m] + 1;
                        int res3 = d[n - 1][m - 1] + (first.charAt(n - 1) == second.charAt(m - 1) ? 0 : 1);
                        int result;
                        if (res1 <= res2 && res1 <= res3) {
                            moveSecond[n][m] = true;
                            result = res1;
                        } else {
                            if (res2 <= res3) {
                                moveFirst[n][m] = true;
                                result = res2;
                            } else {
                                moveFirst[n][m] = true;
                                moveSecond[n][m] = true;
                                result = res3;
                            }
                        }
                        d[n][m] = result;
                    }
                }
            }

            int n = first.length();
            int m = second.length();

            StringBuilder firstLine = new StringBuilder();
            StringBuilder secondLine = new StringBuilder();

            while(n > 0 || m > 0) {
                boolean mf = moveFirst[n][m];
                boolean ms = moveSecond[n][m];
                if (mf) {
                    firstLine.insert(0, first.charAt(n - 1));
                    n--;
                } else  {
                    firstLine.insert(0, "-");
                }
                if (ms) {
                    secondLine.insert(0, second.charAt(m - 1));
                    m--;
                } else {
                    secondLine.insert(0, "-");
                }
            }
            System.out.println(d[first.length()][second.length()]);
            System.out.println(firstLine);
            System.out.println(secondLine);
        }

        public static void main(String[] args) throws FileNotFoundException {
            long startTime = System.currentTimeMillis();
            new OptimizedDistance_Answer().run();
            long finishTime = System.currentTimeMillis();
            System.out.println(finishTime - startTime + " ms");
        }
    }

