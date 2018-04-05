import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Main {

    private static int count = 0;
    private static ArrayList<long[]> segments;
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader("/home/danil/Documents/JavaProjects/Stepik/Segments_QuickSort_BinarySearch/input.txt"));
        //BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = input.readLine().split(" ");
        int n = Integer.parseInt(temp[0]); //Columns' number
        int k = Integer.parseInt(temp[1]); //Points number

        segments = new Main().fillSegments(n, input);
        long[] points = new Main().fillPoints(k, input);

        segments.sort(Comparator.comparingLong(o -> o[0])); //Javas quickSort

        //new Main().quickSort(segments, 0, n - 1); //Sort segments by initial value

        //new Main().printSegments();

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < k; i++) {
            ArrayList<long[]> segmTemp = new ArrayList<>(segments); //List without removed elements
            builder.append(new Main().binarySearch(segments, points[i], 0, segments.size()));
            builder.append(" ");
            count = 0;
            segments = new ArrayList<>(segmTemp);
        }
        System.out.println(builder);
    }

    private long[] fillPoints(int k, BufferedReader input) throws IOException {
        String[] tempPoints = input.readLine().split(" ");
        long[] points = new long[k];
        for (int i = 0; i < k; i++) {
            points[i] = Integer.parseInt(tempPoints[i]);
        }
        return points;
    }

    private ArrayList<long[]> fillSegments(int n, BufferedReader input) throws IOException {
        ArrayList<long[]> segments = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] tempSegm = input.readLine().split(" ");
            long[] values = {
                    Long.parseLong(tempSegm[0]), Long.parseLong(tempSegm[1])
            };
            segments.add(values);
        }
        return segments;
    }

    private void quickSort(ArrayList<long[]> segments, int low, int high) {
        while (low < high) {
            int pi = partition(segments, low, high);
            if (pi - low < high - pi) {
                quickSort(segments, low, pi - 1);
                low = pi + 1;
            }
            else {
                quickSort(segments, pi + 1, high);
                high = pi - 1;
            }
        }
    }

    private int partition(ArrayList<long[]> segments, int low, int high) {
        int random=high + ((int) (Math.random() * (segments.size())))/(high-low+1);
        exch(segments, random,low);

        long[] pivot = segments.get(high);
        int pIndex = low;

        for (int i = low; i < high; i++) {
            if (less(segments.get(i), pivot)) {
                exch(segments, i, pIndex);
                pIndex++;
            }
        }
        exch(segments, high, pIndex);
        return pIndex;
    }

    private int binarySearch(ArrayList<long[]> array, long x, int l, int r) {
        while (r > l + 1) {
            int m = (l + r) >> 1;
            long[] temp = array.get(m);
            if (temp[0] <= x) {
                if (temp[1] >= x) {
                    count++;
                    segments.remove(temp);
                    l = 0;
                    r = array.size();
                } else {
                    l = m;
                }
            } else {
                r = m;
            }
        }

        if (r < array.size()) {
            long[] temp1 = array.get(r);
            if (x >= temp1[0] && x <= temp1[1]) {
                count++;
                segments.remove(temp1);
                binarySearch(segments, x, 0, segments.size());
            }
        }

        if (array.size() > 0) {
            long[] temp2 = array.get(0);
            if (x >= temp2[0] && x <= temp2[1]) {
                count++;
                segments.remove(temp2);
                binarySearch(segments, x, 0, segments.size());
            }
        }
        return count;
    }

    private void printSegments() {
        System.out.println("List sorted by initial value");
        for (long[] segm : segments) {
            System.out.println(segm[0] + " " + segm[1]);
        }

    }

    private static void exch(ArrayList<long[]> array, long i, long j) {
        long[] swap = array.get((int) i);
        array.set((int) i, array.get((int) j));
        array.set((int) j, swap);
    }

    private static int compareTo(long[] val1, long[] val2) {
        if (val1[0] > val2[0]) return 1;
        else if (val1[0] == val2[0]) return 0;
        return -1;
    }

    private static boolean less(long[] val1, long[] val2) {
        return compareTo(val1, val2) < 0;
    }
}