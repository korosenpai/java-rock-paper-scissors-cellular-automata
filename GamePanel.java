import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.Timer;

import javax.swing.JPanel;

public class GamePanel extends JPanel  implements ActionListener{

    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = 600;

    static final int TILESIZE = 5;

    static final int COLS = SCREEN_WIDTH / TILESIZE;
    static final int ROWS = SCREEN_HEIGHT/ TILESIZE;

    final int FPS = 30;
    final int DELAY = 1000 / FPS;
    Timer timer;

    static Grid grid = new Grid(ROWS, COLS);
    static int NUMBER_OF_COLOURS;


    public static void main(String[] args) {
        System.out.println("res: " + SCREEN_HEIGHT + "(height) x " + SCREEN_WIDTH + "(width)");
    }

    GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT)); // set window size
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // all drawing from this component will be done in an offscreen painting buffer -> improves performance
    }

    public void start() {
        System.out.println("game loop running, fps: " + FPS);
        timer = new Timer(DELAY, this);
        timer.setRepeats(true);
        timer.start();

        new Colors();
        NUMBER_OF_COLOURS = Colors.size();
        grid.randomize(NUMBER_OF_COLOURS);
    }

    // called when the timer ends
    public void actionPerformed(ActionEvent event) {
        // update the screen
        System.out.println("starting new cycle");
        updateGrid();
        repaint(); // to call paintComponent

    }

    // will be calculated new grid values
    public void updateGrid() {
        Grid newGrid = new Grid(ROWS, COLS);

        for (int row = 0; row < grid.rows; row++) {
            for (int col = 0; col < grid.cols; col++) {
                int currentCell = grid.grid[row][col];
                int vulnerableTo = currentCell < NUMBER_OF_COLOURS - 1 ? currentCell + 1  : 0; // vulnerable to next int or if bigger: zero
                //System.out.println(currentCell + " is vulnerable to " + vulnerableTo);

                // must be incremented anytime the current cell is vulnerable to a neighbor
                int nVulnerableToNeighbors = 0;

                // top left
                if (row > 0 && col > 0)
                    if (grid.grid[row - 1][col - 1] == vulnerableTo)
                        nVulnerableToNeighbors++;

                // top 
                if (row > 0)
                    if (grid.grid[row - 1][col] == vulnerableTo)
                        nVulnerableToNeighbors++;

                // top right
                if (row > 0 && col < grid.cols - 1)
                    if (grid.grid[row - 1][col + 1] == vulnerableTo)
                        nVulnerableToNeighbors++;

                // left
                if (col > 0)
                    if (grid.grid[row][col - 1] == vulnerableTo)
                        nVulnerableToNeighbors++;

                // right
                if (col < grid.cols - 1)
                    if (grid.grid[row][col + 1] == vulnerableTo)
                        nVulnerableToNeighbors++;

                // bottom left
                if (row < grid.rows - 1 && col > 0)
                    if (grid.grid[row + 1][col - 1] == vulnerableTo)
                        nVulnerableToNeighbors++;

                // bottom
                if (row < grid.rows - 1)
                    if (grid.grid[row + 1][col] == vulnerableTo)
                        nVulnerableToNeighbors++;

                // bottom right
                if (row < grid.rows - 1 && col < grid.cols - 1)
                    if (grid.grid[row + 1][col + 1] == vulnerableTo)
                        nVulnerableToNeighbors++;


                newGrid.grid[row][col] = nVulnerableToNeighbors > 2 ? vulnerableTo : currentCell;
            }
        }

        grid.grid = newGrid.grid;

    }

    // called by repaint()
    public void paintComponent(Graphics g) {
        // builtin method
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g; // 2d gives more access on geometry, coords, ...

        drawGridLines(g2);
        drawGrid(g2);

        g2.dispose();

    }

    public void drawGridLines(Graphics g) {
        for (int row = 1; row < grid.rows; row++) {
            g.drawLine(0, row * TILESIZE, SCREEN_WIDTH, row *TILESIZE); // x1, y1, x2, y2
        }
        for (int col = 1; col < grid.cols; col++) {
            g.drawLine(col * TILESIZE, 0, col * TILESIZE, SCREEN_HEIGHT); // x1, y1, x2, y2
        }
    }

    public void drawGrid(Graphics g) {
        //g.setColor(Color.white);
        //g.fillRect(0, 0, 100, 100); // x, y, width, height


        //g.setColor(new Color(255, 100, 0));
        //g.fillRect(100, 100, 100, 100); // x, y, width, height


        for (int row = 0; row < grid.rows; row++) {
            for (int col = 0; col < grid.cols; col++) {
                int[] cellColor = Colors.get(grid.grid[row][col]);
                int colorR = cellColor[0];
                int colorG = cellColor[1];
                int colorB = cellColor[2];

                g.setColor(new Color(colorR, colorB, colorG));
                g.fillRect(col * TILESIZE, row * TILESIZE, TILESIZE, TILESIZE); // x, y, width, height

            }

        }

    }

}
