/* *****************************************************************************
 *  Name: Eli Ji
 *  Date: 10/1/19
 **************************************************************************** */

public class Percolation {

    private boolean open_arr[];

    private QuickFind myQF;
    private QuickUnion myQU;
    private QuickUnionFind myQUF;

    //algorithm number: 1-QF, 2-QU, 3-QUF, default is 3 (QUF)
    private int alg_num;

    //create N-by-N grid, with all sites blocked
    public Percolation(int n){
        if(n < 0) {
            throw new IllegalArgumentException("Dimensions of the grid must be greater than zero.");
        }

        myQF = new QuickFind(n * n);
        myQU = new QuickUnion(n * n);
        myQUF = new QuickUnionFind(n * n);

        //default algorithm: QUF
        alg_num = 1;

        //initializes all sites as closed
        this.open_arr = new boolean [n * n];
        for(int i = 0; i < open_arr.length; i++) {
            open_arr[i] = false;
        }
    }

    //open site (row i, column j) if it is not open already
    public void open(int i, int j){
        //checks if out of bounds
        if(i < 0 || j < 0 || i > Math.sqrt(open_arr.length) - 1 || j > Math.sqrt(open_arr.length) - 1){
            throw new IllegalArgumentException("Specified index is outside the dimensions of the grid.");
        }
        //checks if it is already open
        if(!isOpen(i,j)) {
            open_arr[(int)(i * Math.sqrt(open_arr.length) + j)] = true;
        }
        connectSites();
    }

    //is site (row i, column j) open?
    public boolean isOpen(int i, int j){
        //checks if out of bounds
        if(i < 0 || j < 0 || i > Math.sqrt(open_arr.length) - 1 || j > Math.sqrt(open_arr.length) - 1){
            throw new IllegalArgumentException("Specified index is outside the dimensions of the grid.");
        }
        return open_arr[(int)(i * Math.sqrt(open_arr.length) + j)];
    }

    //is site (row i, column j) full?
    public boolean isFull(int i, int j){
        //checks if out of bounds
        if(i < 0 || j < 0 || i > Math.sqrt(open_arr.length) - 1 || j > Math.sqrt(open_arr.length) - 1){
            throw new IllegalArgumentException("Specified index is outside the dimensions of the grid.");
        }
        //checks if site is blocked
        if(!isOpen(i,j)){
            return false;
        }

        //checks if site is connected to any open site in the top row
        for (int x = 0; x < Math.sqrt(open_arr.length); x++) {
            if(alg_num == 1){
                if(isOpen(0, x) && myQF.Find((int)(i * Math.sqrt(open_arr.length) + j), x)){
                    return true;
                }
            } else if(alg_num == 2){
                if(isOpen(0, x) && myQU.Find((int)(i * Math.sqrt(open_arr.length) + j), x)){
                    return true;
                }
            } else if(alg_num == 3){
                if(isOpen(0, x) && myQUF.Find((int)(i * Math.sqrt(open_arr.length) + j), x)){
                    return true;
                }
            }
        }
        return false;
    }

    //does the system percolate? ************
    public boolean percolates(){
        //checks if any sites in the bottom row is full
        for(int i = 0; i < Math.sqrt(open_arr.length); i++){
            if(isFull((int)Math.sqrt(open_arr.length-1), i)){
                return true;
            }
        }
        return false;
    }

    //connects sites
    public void connectSites(){
        //connects every site to the one above and to the right of it
        for(int r =0; r < Math.sqrt(open_arr.length); r++) {
            for (int c = 0; c < Math.sqrt(open_arr.length); c++) {
                //only connects if site is open
                if(isOpen(r , c)) {
                    if (r != 0) {
                        //only connects up if site above is open and site is not in the first row
                        if(isOpen(r - 1 , c)) {
                            //up
                            if (alg_num == 1) {
                                myQF.Union((int) (r * Math.sqrt(open_arr.length)) + c,
                                           (int) ((r - 1) * Math.sqrt(open_arr.length)) + c);
                            }
                            else if (alg_num == 2) {
                                myQU.Union((int) (r * Math.sqrt(open_arr.length)) + c,
                                           (int) ((r - 1) * Math.sqrt(open_arr.length)) + c);
                            }
                            else if (alg_num == 3) {
                                myQUF.Union((int) (r * Math.sqrt(open_arr.length)) + c,
                                            (int) ((r - 1) * Math.sqrt(open_arr.length)) + c);
                            }
                        }
                    }
                    if (c != Math.sqrt(open_arr.length) - 1) {
                        //only connects right if site to the right is open and site is not in the last col
                        if(isOpen(r, c + 1)) {
                            //right
                            if (alg_num == 1) {
                                myQF.Union((int) (r * Math.sqrt(open_arr.length)) + c,
                                           (int) (r * Math.sqrt(open_arr.length)) + c + 1);
                            }
                            else if (alg_num == 2) {
                                myQU.Union((int) (r * Math.sqrt(open_arr.length)) + c,
                                           (int) (r * Math.sqrt(open_arr.length)) + c + 1);
                            }
                            else if (alg_num == 3) {
                                myQUF.Union((int) (r * Math.sqrt(open_arr.length)) + c,
                                            (int) (r * Math.sqrt(open_arr.length)) + c + 1);
                            }
                        }
                    }
                }
            }
        }
    }

    public int getAlg_num() {
        return alg_num;
    }

    public void setAlg_num(int alg_num) {
        this.alg_num = alg_num;
    }

    //getters are for testing each algorithm
    public QuickFind getMyQF() {
        return myQF;
    }

    public QuickUnion getMyQU() {
        return myQU;
    }

    public QuickUnionFind getMyQUF() {
        return myQUF;
    }

    //prints open arry, for testing
    public String printOpen_Arr() {
        String out = "";
        for (int i = 0; i < open_arr.length; i++) {
            out = out + open_arr[i] + " ";
        }
        return out;
    }
}
