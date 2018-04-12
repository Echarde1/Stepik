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
    private static ArrayList<int[]> segments;
    public static void main(String[] args) throws IOException {
        //BufferedReader input.txt = new BufferedReader(new FileReader("/home/danil/Documents/JavaProjects/Stepik/Algorithms_Chapter_One/Segments_QuickSort_BinarySearch/input.txt.txt"));
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = input.readLine().split(" ");
        int n = Integer.parseInt(temp[0]); //Columns' number
        int k = Integer.parseInt(temp[1]); //Points number

        segments = new Main().fillSegments(n, input);
        int[] points = new Main().fillPoints(k, input);

        //new Main().quickSort(segments, 0, n - 1); //Sort segments by initial value

        //new Main().printSegments();

        int[][] array = new int[n][2];
        for (int i = 0; i < segments.size(); i++) {
            array[i] = segments.get(i)  ;
        }
        //System.out.println(Arrays.deepToString(array));
        Arrays.sort(array, Comparator.comparingInt(o -> o[0]));

        for (int i = 0; i < segments.size(); i++) {
            segments.set(i, array[i]);
        }

        StringBuilder builder = new StringBuilder();

            for (int i = 0; i < k; i++) {
            ArrayList<int[]> segmTemp = new ArrayList<>(segments); //List without removed elements
            builder.append(new Main().binarySearch(segments, points[i], -1, segments.size()));
            builder.append(" ");
            count = 0;
            segments = new ArrayList<>(segmTemp);
        }
        System.out.println(builder);
    }

    private int[] fillPoints(int k, BufferedReader input) throws IOException {
        String[] tempPoints = input.readLine().split(" ");
        int[] points = new int[k];
        for (int i = 0; i < k; i++) {
            points[i] = Integer.parseInt(tempPoints[i]);
        }
        return points;
    }

    private ArrayList<int[]> fillSegments(int n, BufferedReader input) throws IOException {
        ArrayList<int[]> segments = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] tempSegm = input.readLine().split(" ");
            int[] values = {
                    Integer.parseInt(tempSegm[0]), Integer.parseInt(tempSegm[1])
            };
            segments.add(values);
        }
        return segments;
    }

    private void quickSort(ArrayList<int[]> segments, int low, int high) {
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

    private int partition(ArrayList<int[]> segments, int low, int high) {
        int random=high + ((int) (Math.random() * (segments.size())))/(high-low+1);
        exch(segments, random,low);

        int[] pivot = segments.get(high);
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

    private int binarySearch(ArrayList<int[]> array, int x, int l, int r) {

        while (r > l + 1) {
            int m = (l + r) >> 1;
            int[] temp = array.get(m);
            if (temp[0] <= x) {
                if (temp[1] >= x) {
                    count++;
                    segments.remove(temp);
                    //r = array.size();
                    r--;
                } else {
                    l = m;
                }
            } else {
                r = m;
            }
        }
        return count;
    }

    private void printSegments() {
        System.out.println("List sorted by initial value");
        for (int[] segm : segments) {
            System.out.println(segm[0] + " " + segm[1]);
        }

    }

    private static void exch(ArrayList<int[]> array, int i, int j) {
        int[] swap = array.get(i);
        array.set(i, array.get(j));
        array.set(j, swap);
    }

    private static int compareTo(int[] val1, int[] val2) {
        if (val1[0] > val2[0]) return 1;
        else if (val1[0] == val2[0]) return 0;
        return -1;
    }

    private static boolean less(int[] val1, int[] val2) {
        return compareTo(val1, val2) < 0;
    }
}