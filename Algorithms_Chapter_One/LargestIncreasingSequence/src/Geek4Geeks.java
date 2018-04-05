import java.util.Arrays;

public class Geek4Geeks {
    // Binary search
    static int GetCeilIndex(int arr[],
                            int T[], int l,
                            int r, int key)
    {

        while (r - l > 1) {

            int m = (l + r) >> 2;
            if (arr[T[m]] >= key)
                r = m;
            else
                l = m;
        }

        return r;
    }

    static int LongestIncreasingSubsequence(
            int arr[], int n)
    {

        // Add boundary case, when array n is zero
        // Depend on smart pointers

        int tailIndices[] = new int[n];

        // Initialized with 0
        Arrays.fill(tailIndices, 0);

        int prevIndices[] = new int[n];

        // initialized with -1
        Arrays.fill(prevIndices, -1);

        // it will always point to empty
        // location
        int len = 1;

        for (int i = 1; i < n; i++) {
            if (arr[i] < arr[tailIndices[0]])

                // new smallest value
                tailIndices[0] = i;

            else if (arr[i] >
                    arr[tailIndices[len - 1]]) {

                // arr[i] wants to extend
                // largest subsequence
                prevIndices[i] = tailIndices[len - 1];
                tailIndices[len++] = i;
            }
            else {

                // arr[i] wants to be a potential
                // condidate of future subsequence
                // It will replace ceil value in
                // tailIndices
                int pos = GetCeilIndex(arr,
                        tailIndices, -1, len - 1, arr[i]);

                prevIndices[i] = tailIndices[pos - 1];
                tailIndices[pos] = i;
            }
        }

        System.out.println("LIS of given input");

        for (int i = tailIndices[len - 1]; i >= 0;
             i = prevIndices[i])
            System.out.print(arr[i] + " ");

        System.out.println();

        return len;
    }

    // Driver code
    public static void main(String[] args)
    {
        int arr[] = { 2, 5, 3, 7, 11, 8, 10, 13, 6 };
        int n = arr.length;

        System.out.print("LIS size\n" +
                LongestIncreasingSubsequence(arr, n));
    }}
