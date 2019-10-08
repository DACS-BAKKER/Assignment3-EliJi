/* *****************************************************************************
 *  Name: Eli Ji
 *  Date: 10/1/19
 **************************************************************************** */

public class QuickFind {

    private int[] arr;

    public QuickFind(int n) {

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
        return arr[i] == arr[j];
    }

    //connects i to j, changing all indexes with the value of i into the value of j
    public void Union(int i, int j){
        //checks if out of bounds
        if(i < 0 || j < 0 || i > arr.length - 1 || j > arr.length - 1){
            throw new IllegalArgumentException("Specified index is outside the dimensions of the grid." + i + " " + j);
        }
        //checks if already connected
        if(Find(i , j)) return;
        int temp = arr[i];
        for(int x = 0; x < arr.length; x++){
            if(arr[x] == temp){
                arr[x] = arr[j];
            }
        }
    }

    //prints array, for testing
    public String printArray() {
        String out = "";
        for (int i = 0; i < arr.length; i++) {
            out = out + arr[i] + " ";
        }
        return out;
    }
}
