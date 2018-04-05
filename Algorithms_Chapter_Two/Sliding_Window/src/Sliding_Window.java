import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;

public class Sliding_Window {

        static void printMax(int arr[],int n, int k)
        {
            Deque<Integer> Qi = new LinkedList<>();
            int i;

            for(i = 0; i < k; ++i)
            {
                while(!Qi.isEmpty() && arr[i] >= arr[Qi.peekLast()])
                    Qi.removeLast();
                Qi.addLast(i);
            }
            for( ;i < n; ++i)
            {
                System.out.print(arr[Qi.peek()] + " ");
                while((!Qi.isEmpty()) && Qi.peek() <= i-k)
                    Qi.removeFirst();
                while((!Qi.isEmpty()) && arr[i] >= arr[Qi.peekLast()])
                    Qi.removeLast();
                Qi.addLast(i);
            }
            System.out.print(arr[Qi.peek()]);
        }

        public static void main(String[] args) throws IOException
        {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.parseInt(reader.readLine());
            int[] A = new int[n];
            String s = reader.readLine();
            String[] str = s.split(" ");
            for (int i = 0; i < n; i++) {
                A[i] = Integer.parseInt(str[i]);
            }
            int m = Integer.parseInt(reader.readLine());
            printMax(A, A.length, m);
        }
    }