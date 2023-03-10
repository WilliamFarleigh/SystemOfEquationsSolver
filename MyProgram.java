import java.util.*;
public class MyProgram {
    public static void main(String[] args) {
        SystemOfEquationsMatrix matrix = new SystemOfEquationsMatrix(getAmountOfRowsAndColumns(), getSizeOfEachLocation());
        System.out.println(matrix + "\n" + matrix.solve());
    }
    public static int getAmountOfRowsAndColumns() {
        Scanner scammer = new Scanner(System.in);
        try {
            System.out.print("How many rows/columns would you like? ");
            return scammer.nextInt() + 1; 
        } catch (Exception e) {
            System.out.println("That is not a valid amount of rows!");
            return getAmountOfRowsAndColumns();
        }
        
    }
        public static int getSizeOfEachLocation() {
        Scanner scammer = new Scanner(System.in);
        try {
            System.out.print("What is the max number you want in each location? ");
            return scammer.nextInt() + 1;
        } catch (Exception e) {
            System.out.println("That is not a valid number");
            return getSizeOfEachLocation();
        }
        
    }
}