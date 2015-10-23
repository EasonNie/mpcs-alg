
import java.util.Scanner;

/**
 * Created by Eason on 10/22/15.
 */
public class HW_4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            int n = Integer.parseInt(scanner.nextLine().trim());
            if (n == 0) {
                //System.out.println(0);
                continue;
            }

            int[][] matrix = new int[n][n];
            for (int i = 0; i < n; i++) {
                String line = scanner.nextLine().trim();
                String[] line_arr = line.split(" ");
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = Integer.parseInt(line_arr[j].trim());
                }
            }
            int[] result = count(matrix);
            System.out.println(result[0]);
            System.out.println(result[1] + " " + result[2]);
        }
    }

    public static int[] count(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[]{0, 1, 1};
        }

        int n = matrix.length;
        int m = matrix[0].length;
        int[][] state = new int[n][m];

        for (int i = 0; i < n; i++) {
            state[i][0] = matrix[i][0];
        }

        for (int i = 0; i < m; i++) {
            state[0][i] = matrix[0][i];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == 1) {
                    int min = Math.min(state[i - 1][j], state[i][j - 1]);
                    state[i][j] = Math.min(min, state[i - 1][j - 1]) + 1;
                } else {
                    state[i][j] = 0;
                }
            }
        }

        int maxArae = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                maxArae = Math.max(maxArae, state[i][j]);
            }
        }
        int index1 = 1;
        int index2 = 1;
        if (maxArae == 0) {
            return new int[]{maxArae, index1, index2};
        }
        for (int i = 0; i < n; i++) {
            boolean found = false;
            for (int j = 0; j < m; j++) {
                if (maxArae == state[i][j]) {
                    index1 = i - maxArae + 2;
                    index2 = j - maxArae + 2;
                    found = true;
                    break;
                }
            }
            if (found) {
                break;
            }
        }

        return new int[]{maxArae, index1, index2};
    }
}
