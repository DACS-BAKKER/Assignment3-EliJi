/* *****************************************************************************
 *  Name: Eli Ji
 *  Date: 10/1/19
 *
 *  Description: This program tests the three algorithms and compares their
 *               runtimes.
 **************************************************************************** */

public class PercolationTester {


    public static void main(String[] args) {
        testTime(100);
    }

    //prints into console runtime for each algorithm for a percolation of size n^2
    public static void testTime(int percSize){

        Percolation myPerc = new Percolation(percSize);
        //opens all sites
        for(int r = 0; r < percSize; r++){
            for(int c = 0; c < percSize; c++){
                myPerc.open(r, c);
            }
        }

        System.out.println("Runtimes for " + percSize + " x " + percSize + "Percolation" );
        System.out.println("----------------------------------" );

        //Union and Finds to site on opposite end of array (was not sure which site would be least optimal since it depends on the algorithm)
        //QF
        long start1 = System.nanoTime();
        for(int i = 0; i < percSize; i++){
            myPerc.getMyQF().Union(i,percSize - 1 -i);
            myPerc.getMyQF().Find(i,percSize -1 -i);
        }
        long end1 = System.nanoTime();
        long total1 = end1 - start1;
        System.out.println("QuickFind: " + total1);

        //QU
        long start2 = System.nanoTime();
        for(int i = 0; i < percSize; i++){
            myPerc.getMyQU().Union(i,percSize - 1 -i);
            myPerc.getMyQU().Find(i,percSize -1 -i);
        }
        long end2 = System.nanoTime();
        long total2 = end2 - start2;
        System.out.println("QuickUnion: " + total2);

        //QUF
        long start3 = System.nanoTime();
        for(int i = 0; i < percSize; i++){
            myPerc.getMyQUF().Union(i,percSize - 1 -i);
            myPerc.getMyQUF().Find(i,percSize -1 -i);
        }
        long end3 = System.nanoTime();
        long total3 = end3 - start3;
        System.out.println("QuickUnionFind: " + total3);

    }
}
