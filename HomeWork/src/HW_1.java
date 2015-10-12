import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * Created by Eason on 10/11/15.
 */
public class HW_1 {
    public static void main(String[] args) {

        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                PrintWriter bw = new PrintWriter(new OutputStreamWriter(System.out))
        ) {
            while(true) {
                String line = br.readLine();
                if (line.equals("exit")) {
                    break;
                }
                String[] s_array = line.split(" ");
                int[] array = new int[s_array.length];
                for (int i = 0; i < array.length ; i++) {
                    array[i] = Integer.parseInt(s_array[i]);
                }
                bw.println(mergesort_and_count(array, 0, array.length - 1));
                bw.flush();
            }
        } catch (Exception e) {
            //Just ignore the exception.
            System.out.println("IOException.");
        }

    }

    //mergesort the array and count the inversions at the same time
    public static int mergesort_and_count(int[] A, int start, int end) {

        if (start == end) {
            return 0;
        }

        if (start + 1 == end) {
            if (A[start] <= A[end]) {
                return 0;
            } else {
                int tmp = A[start];
                A[start] = A[end];
                A[end] = tmp;
                return 1;
            }   //  if A[start] > A[end] then swap start and end
        }

        int mid = start + (end - start) / 2;
        int count = 0;
        count = count + mergesort_and_count(A, start, mid);
        count = count + mergesort_and_count(A, mid + 1, end);
        count = count + merge_and_count(A, start, mid, end);
        return count;
    }

    public static int merge_and_count(int[] A, int start, int mid, int end) {
        //The return value of this method is the number of inversions the merge method have been reversed.
        int n1 = mid - start + 1;
        int n2 = end - mid;
        int[] L = new int[n1 + 1];
        int[] R = new int[n2 + 1];
        for (int i = 0; i < n1; i++) {
            L[i] = A[start + i];
        }
        L[n1] = Integer.MAX_VALUE;

        for (int j = 0; j < n2; j++) {
            R[j] = A[mid + j + 1];
        }
        R[n2] = Integer.MAX_VALUE;

        int left = 0;
        int right = 0;
        int count = 0;
        for (int i = start; i <= end; i++) {
            if (L[left] <= R[right]) {  //If left < right, then we find an inversion.
                A[i] = L[left];
                if (L[left] != Integer.MAX_VALUE) {
                    count = count + right;
                }
                left++;
            } else {
                A[i] = R[right];
                right++;
            }
        }
        return count;
    }
}
