import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Eason on 10/16/15.
 */
public class HW_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            int k = Integer.parseInt(scanner.nextLine().trim());

            if (line.equals("exit")) {
                break;
            }
            String[] s_array = line.split(" ");
            int[] array = new int[s_array.length];
            for (int i = 0; i < array.length; i++) {
                array[i] = Integer.parseInt(s_array[i]);
            }

            int[] state = minCombo(array, k);
            int[] result = reconstruct(array, state);
            for (int i = 0; i < result.length; i++) {
                System.out.print(result[i] + " ");
            }
            System.out.println();
        }

//        int[] test = new int[]{1, 4, 5, 8, 11};
//        int[] test2 = new int[]{16, 17, 23, 24, 39, 40};
//        int[] state = minCombo(test2, 100);
//        int[] result = reconstruct(test2, state);
//        System.out.println(Arrays.toString(result));
        //System.out.println(minCombo(test2, 100)[100]);

    }

    public static int[] minCombo(int[] D, int n) {
        if (n == 1) {
            return new int[]{1};
        }
        int state[] = new int[n + 1];
        state[0] = 0;
        state[1] = 1;
        for (int i = 1; i < n + 1; i++) {
            int min = i;
            for (int j = 0; j < D.length; j++) {
                int d = i / D[j];
                for (int k = 1; k <= d; k++) {
                    min = Math.min(min, k + state[i - k * D[j]]);
                }
            }
            state[i] = min;
        }
        return state;
    }

    public static int[] reconstruct(int[] D, int[] state) {
        if (D.length == 0) {
            return new int[]{};
        }
        int[] result = new int[D.length];
        int i = state.length - 1;
        while (i > 0) {
            boolean found = false;
            for (int j = 0; j < D.length; j++) {
                if (!found) {
                    int d = i / D[j];
                    for (int k = 1; k <= d; k++) {
                        if (state[i] == state[i - k * D[j]] + k) {
                            result[j] = result[j] + k;
                            i = i - k * D[j];
                            found = true;
                            break;
                        }
                    }
                } else {
                    found = false;
                    break;
                }
            }
        }
        return result;
    }
}
