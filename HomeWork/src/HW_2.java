import java.io.*;
import java.util.Scanner;

/**
 * Created by Eason on 10/11/15.
 */
public class HW_2 {
    public static void main(String[] args) throws FileNotFoundException {

        //Scanner scanner = new Scanner(new File("HomeWork/input_2.txt"));
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();    //Get input array.
            int k = Integer.parseInt(scanner.nextLine().trim());    //Get k

            if (line.equals("exit")) {
                break;
            }
            String[] s_array = line.split(" ");
            int[] array = new int[s_array.length];
            for (int i = 0; i < array.length; i++) {
                array[i] = Integer.parseInt(s_array[i]);
            }

            int[] result = kNumCloseToMedian(array, k);

            for (int i = 0; i < result.length; i++) {
                System.out.print(result[i] + " ");
            }
            System.out.println();

        }

    }

    public static int[] kNumCloseToMedian(int[] array, int k) {
        int[] gap = gap(array);                                     //Calculate each gap between elements to the median.
        int[] input = gap.clone();
        int kthNum = kth(input, 0, input.length - 1, k);            //Obtain the kth gap.
        int[] result = new int[k];
        int index = 0;
        for (int i = 0; i < gap.length; i++) {
            if (index == k) {
                break;
            }
            if (gap[i] <= kthNum) {
                result[index] = array[i];
                index++;
            }
        }
        return result;
    }

    public static int[] gap(int[] array) {          //This method calculate each gap between elements to the median.
        int[] result = new int[array.length];
        int[] input = array.clone();
        int median = getMedian(input);
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i] - median;
            if (result[i] < 0) {
                result[i] = 0 - result[i];
            }
        }
        return result;
    }

    public static int getMedian(int[] array) {      //This method invoke select-function to return the median of an array.
        if (array.length % 2 == 1) {
            return kth(array, 0, array.length - 1, array.length / 2 + 1);
        } else {
            int a = kth(array, 0, array.length - 1, array.length / 2);
            int b = kth(array, 0, array.length - 1, array.length / 2 + 1);
            return (a + b) / 2;
        }
    }

    public static int kth(int[] array, int start, int end, int i) {
        //int[] array = input.clone();
        if (start == end) {
            return array[start];
        } else {
            int q = partition(array, start, end);
            int k = q - start + 1;
            if (k == i) {
                return array[q];
            } else if (k > i) {
                return kth(array, start, q - 1, i);
            } else {
                return kth(array, q + 1, end, i - k);
            }
        }
    }

    public static int partition(int[] array, int start, int end) {
        int i = start - 1;
        for (int j = start; j < end; j++) {
            if (array[j] <= array[end]) {
                i = i + 1;
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }
        }
        i = i + 1;
        int tmp = array[i];
        array[i] = array[end];
        array[end] = tmp;

        return i;
    }
}
