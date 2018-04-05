import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Tree_Hight {

    public static void main (String args[]) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        int[] A = new int[N];
        String s = reader.readLine();
        String[] str = s.split(" ");
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(str[i]);
        }
        TreeWithArray treeArray = new TreeWithArray();
        System.out.println(treeArray.TreeHeight(A, N));
    }
}
class TreeWithArray {
    public int TreeHeight(int[] A, int N) {
        int result = 0;
        for (int i = 0; i < N; i++) {
            int p = i, current = 1;
            while (A[p] != -1) {
                current++;
                p = A[p];
            }
            result = Math.max(result, current);
        }
        return result;
    }
}
