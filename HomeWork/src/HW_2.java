/**
 * Created by Eason on 10/11/15.
 */
public class HW_2 {
    public static void main(String[] args) {
        int[] test = new int[]{18, 94, 29, 8, 61, 87, 27, 98, 6, 3};
        //System.out.println(kth(test, 0, 8, 9));
        //System.out.println(getMedian(test));
        int[] test_r = kNumCloseToMedian(test, 6);
        for (int i : test_r) {
            System.out.print(i + " ");
        }
    }

    public static int[] kNumCloseToMedian(int[] array, int k) {
        int[] gap = gap(array);
        int kthNum = kth(gap, 0, gap.length - 1, k);
        int[] result = new int[k];
        int index = 0;
        for (int i = 0; i < gap.length ;i++) {
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

    public static int[] gap(int[] array) {
        int[] result = new int[array.length];
        int median = getMedian(array);
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i] - median;
            if (result[i] < 0) {
                result[i] = 0 - result[i];
            }
        }
        return result;
    }

    public static int getMedian(int[] array) {
        if (array.length % 2 == 1) {
            return kth(array, 0, array.length - 1, array.length / 2);
        } else {
            int a = kth(array, 0, array.length - 1, array.length / 2);
            int b = kth(array, 0, array.length - 1, array.length / 2 + 1);
            return (a + b) / 2;
        }
    }

    public static int kth(int[] input, int start, int end, int k) {
        int[] array = input.clone();
        if (start == end) {
            return array[start];
        } else {
            int t = partition(array, start, end);
            int offset = t - start + 1;
            if (offset == k) {
                return array[t];
            } else if (offset > k) {
                return kth(array, start, t - 1, k);
            } else {
                return kth(array, t + 1, end, k - offset);
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
