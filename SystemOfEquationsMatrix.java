import java.util.*;
import java.math.*;
public class SystemOfEquationsMatrix extends Matrix {
    private Matrix solutionToMatrix;
    public SystemOfEquationsMatrix(double[][] matrix, double[] solutionToMatrix) {
        super(matrix);
        double[][] temp = new double[solutionToMatrix.length][1];
        for (int i = 0; i < temp.length; i++) {
            temp[i][0] = solutionToMatrix[i];
        }
        this.solutionToMatrix = new Matrix(temp);
    } 

    public SystemOfEquationsMatrix(int size, double[] solutionToMatrix) {
        super(size, 10);
        double[][] temp = new double[solutionToMatrix.length][1];
        for (int i = 0; i < temp.length; i++) {
            temp[i][0] = solutionToMatrix[i];
        }
        this.solutionToMatrix = new Matrix(temp);
    } 
    
    public SystemOfEquationsMatrix(int size, int maxESize) {
        super(size, maxESize);
        double[][] temp = new double[size][1];
        for (int i = 0; i < temp.length; i++) {
            temp[i][0] = new Random().nextInt(maxESize);
        }
        this.solutionToMatrix = new Matrix(temp);
    } 

    public SystemOfEquationsMatrix(int size, int maxESize, double[] solutionToMatrix) {
        super(size, maxESize);
        double[][] temp = new double[solutionToMatrix.length][1];
        for (int i = 0; i < temp.length; i++) {
            temp[i][0] = solutionToMatrix[i];
        }
        this.solutionToMatrix = new Matrix(temp);
    } 
    public Matrix solve() {
        BigDecimal bigDecimal = getDeterminant(doubleMatrix);
        double[][] anwerMatrix = new double[doubleMatrix.length][1]; 
        for (int i = 0; i < this.doubleMatrix[0].length; i++) {
            double[][] modifiedMatrix = new double[doubleMatrix.length][doubleMatrix[0].length];
            for (int x = 0; x < this.doubleMatrix.length; x++) {
                for (int y = 0; y < this.doubleMatrix[0].length; y++) {
                    modifiedMatrix[x][y] = doubleMatrix[x][y];
                }
            }
            for (int j = 0; j < this.doubleMatrix.length; j++) {
                modifiedMatrix[j][i] = solutionToMatrix.doubleMatrix[j][0];
            }
            anwerMatrix[i][0] = getDeterminant(modifiedMatrix).divide(bigDecimal, 10, RoundingMode.HALF_UP).doubleValue();
            
        }
        return new Matrix(anwerMatrix);
        // Have to modify multiply to detect if inverse
        // Inverse inverse = this.findInverse();
        // return solutionToMatrix.multiplyMatrix(inverse);
    }
    @Override
    public String toPythonList() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        int i = 0;
        for (double[] row : doubleMatrix) {
            builder.append("[");
            for (double pos : row) {
                builder.append(pos).append(", ");
            }
            builder.delete(builder.length() - 2, builder.length());
            builder.append("],");
            i++;
        }
        builder.delete(builder.length() - 1, builder.length());
        builder.append("]");
        builder.append("\n\n");
        builder.append("[");
        for (double[] row : solutionToMatrix.doubleMatrix) {
            for (double pos : row) {
                builder.append(pos).append(", ");
            }
        }
        builder.delete(builder.length() - 2, builder.length());
        builder.append("]\n\n");
        return builder.toString();
    }
    public String toEquations() {
        String str = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder builder = new StringBuilder();
        int i = 0;
        char[] charArray = str.toCharArray();
        for (double[] row : doubleMatrix) {
            int j = 0;
            for (double pos : row) {
                if (pos > 0) {
                    builder.append("+").append(pos).append(charArray[j]).append(" ");
                } else {
                    builder.append(pos).append(charArray[j]).append(" ");
                }
                j++;
            }
            builder.append(" = ");
            builder.append(solutionToMatrix.getMatrixAsArray()[i][0]);
            
            builder.append("\n");
            i++;
        }
        builder.delete(builder.length() - 1, builder.length());

        return builder.toString();
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (double[] row : doubleMatrix) {
            
            builder.append("[");
            for (double pos : row) {
                builder.append(pos).append(" ");
            }
            builder.append("| ");
            builder.append(solutionToMatrix.getMatrixAsArray()[i][0]);
            
            builder.append("]\n");
            i++;
        }
        builder.delete(builder.length() - 1, builder.length());

        return builder.toString();
    }
}