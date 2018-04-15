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

        System.out.printf("%.9f\n", closest(P, n));
    }

    private static double findMinDistance(Point[] P, int low,int high) {
        printPoints(P, high);
        double min = Double.MAX_VALUE;
        for (int i = low; i < high/*- 1*/; ++i) {
            for (int j = i + 1; j < high; ++j) {
                min = Math.min(distance(P[i], P[j]), min);
            }
        }
        return min;
    }

    private static double stripClosest(Point[] strip, int size, double d) {
        double min = d;

        printPoints(strip, size);

        Arrays.sort(strip, Comparator.comparingInt(o -> o.y));

        for (int i = 0; i < size; ++i) {
            for (int j = i + 1; j < size && (strip[j].y - strip[i].y) < min; ++j) {
                min = Math.min(distance(strip[i], strip[j]), min);
            }
        }

        return min;
    }

    private static double closestUtil(Point[] P, int low,int high) {
        if (high - low <= 3) return findMinDistance(P, low, high);

        int mid = (high + low) / 2;
        Point midPoint = P[mid];

        double dl = closestUtil(P, low, mid);
        double dr = closestUtil(P, mid + 1, high);

        double d = Math.min(dl, dr);

        ArrayList<Point> stripTemp = new ArrayList<>();

        int j = 0;
        for (int i = 0; i < high; i++) {
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

        printPoints(P, n);

        return closestUtil(P, 0, n);
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

class PointG {
    int x;
    int y;

    PointG(int x, int y) {
        this.x = x;
        this.y = y;
    }
}