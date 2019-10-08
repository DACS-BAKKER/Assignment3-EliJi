/* *****************************************************************************
 *  Name: Eli Ji
 *  Date: 10/1/19
 **************************************************************************** */

public class QuickUnion {

    private int[] arr;

    public QuickUnion(int n) {

        this.arr = new int [n];

        //initializes array, setting each value equal to the index
        for(int i = 0; i < arr.length; i++) {
            arr[i] = i;
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

    //connects i to j, (root of i is child, root of j is parent)
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
        arr[rootOfI] = arr[rootOfJ];
    }


    //prints array, for testing
    public String printArray() {
        String out = "";
        for (int i = 0; i <arr.length; i++) {
            out = out + arr[i] + " ";
        }
        return out;
    }
}
