import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingDeque;

public class Net_Packages_Processing {

    public static int SIZE;
    public static LinkedBlockingDeque<Integer> queue = new LinkedBlockingDeque<>();
    public static int proc = 0;
    public static int start_proc = 0;

    public static void main(String args[]) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //Init buffer size and packages number. Queue creating
        String[] str = reader.readLine().split(" ");
        SIZE = Integer.parseInt(str[0]);
        int n = Integer.parseInt(str[1]);

        //Creating and filling arrays, containing info about loading and processing time
        for (int i = 0; i < n; i++) {
            String[] AD = reader.readLine().split(" ");
            addPackage(Integer.parseInt(AD[0]), Integer.parseInt(AD[1]));
        }
    }

    public static void addPackage(int time, int duration) {
        if (check(time)){
            push(time, duration);
        }
        else System.out.println(-1);
    }

    public static boolean check(int time) {
        if (queue.size() < SIZE) {
            return true;
        } else if (time >= queue.getFirst()){
            queue.removeFirst();
            return true;
        }
        return false;
    }

    public static void push(int time, int duration) {
        if (queue.size() != 0) {
            start_proc = Math.max(time, queue.getLast());
        } else {
            start_proc = Math.max(time, proc);
            proc = duration;
        }
        queue.addLast(start_proc + duration);
        System.out.println(start_proc);
    }
}