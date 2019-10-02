/* *****************************************************************************
 *  Name: Eli Ji
 *  Date: 10/1/19
 **************************************************************************** */

public class Percolation {

    private int connect_arr[];
    private int arr[][];
    //algorithm setting: 1-QF, 2-QU, 3-QUF, default: 1-QF
    private int algType;

    // create N-by-N grid, with all sites blocked
    public Percolation(int n){
        if(n < 0) {
            throw new IllegalArgumentException("Dimensions of the grid must be greater than zero.");
        }

        this.arr = new int[n][n];
        for(int r = 0; r < n; r++){
            for(int c = 0; c < n; c++){
                arr[r][c] = 0;
            }
        }

        this.connect_arr = new int [n];
        for(int i = 0; i < connect_arr.length; i++) {
            connect_arr[i] = i;
        }

        algType = 1;
    }

    // open site (row i, column j) if it is not open already
    public void open(int i, int j){
        if(i < 0 || j < 0 || i > arr.length-1 || j > arr.length-1){
            throw new IllegalArgumentException("Specified index is outside the dimensions of the grid.");
        }
        if(arr[i][j] == 0) {
            arr[i][j] = 1;
        }
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j){
        if(i < 0 || j < 0 || i > arr.length-1 || j > arr.length-1){
            throw new IllegalArgumentException("Specified index is outside the dimensions of the grid.");
        }
        return arr[i][j] == 1;
    }

    // is site (row i, column j) full? **************
    public boolean isFull(int i, int j){
        if(i < 0 || j < 0 || i > arr.length-1 || j > arr.length-1){
            throw new IllegalArgumentException("Specified index is outside the dimensions of the grid.");
        }
        return true;
    }

    // does the system percolate? ************
    public boolean percolates(){
        if(algType == 1){

        }
        return true;
    }

    //is i connected to j?
    public boolean QuickFindFind(int i, int j){
        return connect_arr[i] == connect_arr[j];
    }

    //connects i to j, changing all indexes with the value of i into the value of j
    public void QuickFindUnion(int i, int j){
        if(QuickFindFind(i,j)) return;
        int temp = connect_arr[i];
        for(int x : connect_arr){
            if(connect_arr[x] == temp){
                connect_arr[x] = connect_arr[j];
            }
        }
    }

    //is i connected to j?
    public boolean QuickUnionFind(int i, int j){
        int rootOfI = connect_arr[i];
        int rootOfJ = connect_arr[j];
        while (rootOfI != connect_arr[rootOfI]){
            rootOfI = connect_arr[rootOfI];
        }
        while (rootOfI != connect_arr[rootOfI]){
            rootOfI = connect_arr[rootOfI];
        }
        return rootOfI == rootOfJ;
    }

    //connects i to j, making the value of i -> j
    public void QuickUnionUnion(int i, int j){
        connect_arr[i] = j;
    }

    // prints array, used for debug and test
    public String printArray() {
        String out = "";
        for (int i = 0; i < arr.length; i++) {
            out = out + "{ ";
            for (int j = 0; j < arr[i].length; j++) {
                out = out + arr[i][j] + " ";
            }
            out = out + "},";
        }
        return out;
    }


    // test client
    public static void main(String[] args){

    }
}
