import java.util.Arrays;
import java.util.Random;

public class Grid {
    public int rows;
    public int cols;
    public int[][] grid = {{0}};

    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        grid = new int[rows][cols];
    }

    // populate grid with random values between 0 and numberOfColors(excluded as one is zero)
    public void randomize(int numberOfColors) {
        Random random = new Random();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                grid[row][col] = random.nextInt(numberOfColors);
            }

        }

    }

    public void print() {
        for (int[] row: grid)
            System.out.println(Arrays.toString(row));
    }

}
