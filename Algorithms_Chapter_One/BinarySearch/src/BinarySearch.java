public class BinarySearch {
    public static void main(String[] args) {
        System.out.println(new BinarySearch().binarySearch(new int[]{5, 6, 7, 8}, 7));
    }
    private int binarySearch(int[] a, int x) {
        int l = -1;
        int r = a.length;
        while (r > l + 1) {
            int m = (l + r) >> 1;
            if (a[m] <= x) {
                l = m;
            } else {
                r = m;
            }
        }
        if (l >= 0 && a[l] == x) {
            return l + 1;
        } else return -1;
    }
}
