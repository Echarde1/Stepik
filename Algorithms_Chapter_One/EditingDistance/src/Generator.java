import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class Generator {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter out = new PrintWriter("/home/danil/Documents/JavaProjects/Stepik/Algorithms_Chapter_One/EditingDistance/input.txt.txt");
        int n = 10000;
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            out.print((char) ('a' + random.nextInt(26)));
        }
        out.println();
        for (int i = 0; i < n; i++) {
            out.print((char) ('a' + random.nextInt(26)));
        }
        out.println();
        out.close();
    }
}
