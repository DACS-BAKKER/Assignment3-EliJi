/* *****************************************************************************
 *  Name: Eli Ji
 *  Date: 10/1/19
 *
 *  Description: This program draws the percolation grid and also runs the
 *  Monte Carlo Simulation
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Collections;

public class PercolationRunner {

    //length of one dimesion of the Percolation grid
    static int percSize = 8;
    static Percolation myPerc = new Percolation(percSize);

    public static void main(String[] args) {
        myPerc.setAlg_num(1);
        drawPercolation();
        runMonteCarlo(100);
    }

    public static void runMonteCarlo(int pauseTime){

        System.out.println("Monte Carlo Simulation for " + percSize + " x " + percSize + " Percolation" );
        System.out.println("--------------------------------------------------" );

        //List of sites in random order (sites are numbered the same as the percolation array)
        ArrayList<Integer> randomSite = new ArrayList<Integer>();

        for(int i = 0; i < percSize * percSize; i ++){
            randomSite.add(i);
        }

        Collections.shuffle(randomSite);

        int expected = (int)Math.round(0.593 * percSize * percSize);
        int actual = 0;

        for(int i =0; i < percSize * percSize; i ++) {
            StdDraw.pause(pauseTime);
            int randomRow = randomSite.get(i) % percSize;
            int randomCol = randomSite.get(i) / percSize;
            //System.out.println("row: " + randomRow + " col: " + randomCol);
            myPerc.open(randomRow, randomCol);
            updateOpening(randomRow, randomCol);
            updateWater(randomRow, randomCol);
            if(myPerc.percolates()){
                actual = i + 1;
                break;
            }
        }

        System.out.println("Expected: " + expected);
        System.out.println("Actual: " + actual);
    }

    //updates the graphics of the Percolation grid so that site (r, c) is open / white
    public static void updateOpening(int r, int c) {
        double sideLength = 0.6 / percSize;

        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledSquare(0.2 + sideLength/2 + c * sideLength, 0.8 - sideLength/2 - (r * sideLength), sideLength/2);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.square(0.2 + sideLength/2 + c * sideLength, 0.8 - sideLength/2 - (r * sideLength), sideLength/2);

    }

    //recursivley updates the graphics of the Percolation grid so that the water flows down, left, and right as a result of opening site (r,c)
    public static void updateWater( int r, int c){
        boolean[][] waterCheck = new boolean[percSize][percSize];
        updateWaterWrapper(r, c, "down", waterCheck);
    }


    //This is an algorithm, I had to implement waterCheck because the algorithm was too slow
    public static void updateWaterWrapper(int r, int c, String flowDirection, boolean[][] waterCheck) {
        //base case: blocked or off the grid or already full
        if(c < 0 || c == percSize || r == percSize || !myPerc.isOpen(r, c) || waterCheck[r][c]) {
            return;
        }
        double sideLength = 0.6 / percSize;

        //full -> cyan
        if(myPerc.isFull(r , c)) {
            StdDraw.setPenColor(StdDraw.CYAN);
            StdDraw.filledSquare(0.2 + sideLength/2 + c * sideLength, 0.8 - sideLength/2 - (r * sideLength), sideLength/2);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.square(0.2 + sideLength/2 + c * sideLength, 0.8 - sideLength/2 - (r * sideLength), sideLength/2);
            //makes sure not to draw water where there is already water (memoization)
            waterCheck[r][c] = true;
        }

        if(flowDirection == "down"){
            updateWaterWrapper(r, c - 1, "left", waterCheck);
            updateWaterWrapper(r, c + 1, "right", waterCheck);
            updateWaterWrapper(r + 1 , c,"down", waterCheck);
        } else if (flowDirection == "left"){
            updateWaterWrapper(r, c - 1, "left", waterCheck);
            updateWaterWrapper(r + 1 , c,"down", waterCheck);
        } else if (flowDirection == "right"){
            updateWaterWrapper(r, c + 1, "right", waterCheck);
            updateWaterWrapper(r + 1 , c,"down", waterCheck);
        }
    }

    //draws the Percolation grid, centers and adjusts sidelength according to percolation size
    public static void drawPercolation() {
        StdDraw.setCanvasSize(500, 500);
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(StdDraw.BLACK);

        double sideLength = 0.6 / percSize;

        for(int r =0; r < percSize; r++){
            for(int c =0; c < percSize; c++) {
                //blocked -> gray
                if(!myPerc.isOpen(r , c)) {
                    StdDraw.setPenColor(StdDraw.GRAY);
                    StdDraw.filledSquare(0.2 + sideLength/2 + c * sideLength, 0.8 - sideLength/2 - (r * sideLength), sideLength/2);
                    StdDraw.setPenColor(StdDraw.BLACK);
                    StdDraw.square(0.2 + sideLength/2 + c * sideLength, 0.8 - sideLength/2 - (r * sideLength), sideLength/2);
                //full -> cyan
                } else if(myPerc.isFull(r , c)) {
                    StdDraw.setPenColor(StdDraw.CYAN);
                    StdDraw.filledSquare(0.2 + sideLength/2 + c * sideLength, 0.8 - sideLength/2 - (r * sideLength), sideLength/2);
                    StdDraw.setPenColor(StdDraw.BLACK);
                    StdDraw.square(0.2 + sideLength/2 + c * sideLength, 0.8 - sideLength/2 - (r * sideLength), sideLength/2);
                //empty -> white / not filled
                } else if(myPerc.isOpen(r , c)) {
                    StdDraw.setPenColor(StdDraw.BLACK);
                    StdDraw.square(0.2 + sideLength/2 + c * sideLength, 0.8 - sideLength/2 - (r * sideLength), sideLength/2);
                }
            }
        }
    }
}
