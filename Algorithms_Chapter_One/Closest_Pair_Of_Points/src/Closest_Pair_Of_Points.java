import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Closest_Pair_Of_Points {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader("/home/danil/Documents/JavaProjects/Stepik/Algorithms_Chapter_One/Closest_Pair_Of_Points/input.txt"));
        //BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(input.readLine());
        Point[] P = new Point[n];
        for (int i = 0; i < n; i++) {
            String[] temp = input.readLine().split(" ");
            P[i] = new Point(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
        }
        printPoints(P, n);

        //System.out.printf("The smallest distance is %.9f\n", closest(P, n));
        System.out.println(closest(P, n));
    }

    private static double bruteForce(Point[] P, int n) {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                min = Math.min(distance(P[i], P[j]), min);
            }
        }
        return min;
    }

    private static double stripClosest(Point[] strip, int size, double d) {
        double min = d;
        printPoints(strip, strip.length);
        Arrays.sort(strip, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.y - o2.y;
            }
        });

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size && (strip[j].y - strip[i].y) < min; ++j) {
                min = Math.min(distance(strip[i], strip[j]), min);
            }
        }

        return min;
    }

    private static double closestUtil(Point[] P, int n) {
        if (n <= 3) return bruteForce(P, n);

        int mid = n / 2;
        Point midPoint = P[mid];

        double dl = closestUtil(P, mid);
        double dr = closestUtil(P, mid);

        double d = Math.min(dl, dr);

        //Point[] strip = new Point[n];
        ArrayList<Point> stripTemp = new ArrayList<>();

        //stripTemp.add(new Point(0, 0));
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (Math.abs(P[i].x - midPoint.x) < d) {
                stripTemp.add(j, P[i]);
                j++;
            }
        }

        Point[] strip = new Point[stripTemp.size()];
        strip = stripTemp.toArray(strip);
        return Math.min(d, stripClosest(strip, j ,d));
    }

    private static double closest(Point[] P, int n) {
        Arrays.sort(P, Comparator.comparingInt(o -> o.x));
        return closestUtil(P, n);
    }

    private static double distance(Point p1, Point p2) {
        return Math.sqrt( (p1.x - p2.x) * (p1.x - p2.x) +
                          (p1.y - p2.y) * (p1.y - p2.y)
                        );
    }

    private static void printPoints(Point[] P, int n) {
        for (int i = 0; i < n; i++) {
            System.out.println(P[i].x + " " + P[i].y);
        }
        System.out.println();
    }
}

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}