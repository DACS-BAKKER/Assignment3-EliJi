/* *****************************************************************************
 *  Name: Eli Ji
 *  Date: 10/1/19
 **************************************************************************** */

public class QuickUnionFind {

    private int[] arr;
    private int[] size;

    public QuickUnionFind(int n) {

        this.arr = new int [n];
        this.size = new int[n];

        //initializes array, setting each value equal to the index
        for(int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

        //initializes size array, setting each size to 1
        for(int i = 0; i < size.length; i++) {
            size[i] = 1;
        }
    }

    //is i connected to j?
    public boolean Find(int i, int j){
        //checks if out of bounds
        if(i < 0 || j < 0 || i > arr.length - 1 || j > arr.length - 1){
            throw new IllegalArgumentException("Specified index is outside the dimensions of the grid.");
        }
        int rootOfI = arr[i];
        int rootOfJ = arr[j];
        while (rootOfI != arr[rootOfI]){
            rootOfI = arr[rootOfI];
        }
        while (rootOfJ != arr[rootOfJ]){
            rootOfJ = arr[rootOfJ];
        }
        return rootOfI == rootOfJ;
    }

    //connects i to j, making the value of the root of i -> j (root of i is child, j is parent)
    public void Union(int i, int j){
        //checks if out of bounds
        if(i < 0 || j < 0 || i > arr.length - 1 || j > arr.length - 1){
            throw new IllegalArgumentException("Specified index is outside the dimensions of the grid.");
        }
        //checks if already connected
        if(Find(i , j)) return;

        int rootOfI = arr[i];
        int rootOfJ = arr[j];
        while (rootOfI != arr[rootOfI]){
            rootOfI = arr[rootOfI];
        }
        while (rootOfJ != arr[rootOfJ]){
            rootOfJ = arr[rootOfJ];
        }

        //smaller sized tree is child
        if(size[rootOfI] <= size[rootOfJ]){
            arr[rootOfI] = arr[rootOfJ];
            size[rootOfJ] += size[rootOfI];
        } else {
            arr[rootOfJ] = arr[rootOfI];
            size[rootOfI] += size[rootOfJ];
        }

    }

    //prints array, for testing
    public String printArray() {
        String out = "";
        for (int i = 0; i <arr.length; i++) {
            out = out + arr[i] + " ";
        }
        return out;
    }

    //prints size array, for testing
    public String printSizeArray() {
        String out = "";
        for (int i = 0; i <size.length; i++) {
            out = out + size[i] + " ";
        }
        return out;
    }
}
