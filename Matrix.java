import java.util.*;
import java.math.*;
public class Matrix {
    
    protected double[][] doubleMatrix;
    
    /** 
     * Only for new Matrixes, will generate 
     * a sizeGeneratedxsizeGenerated size matrix
     */
    private static int sizeGenerated = 10;
    private static int roundLoc = 50;
    private static int maxElementSize = 2;
    /**
     *  Generates a new, random matrix
     */
    public Matrix() {
        this(sizeGenerated, maxElementSize);
    }
    public Matrix(int size, int maxESize) {
        this.doubleMatrix = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                doubleMatrix[i][j] = new Random().nextInt(maxESize) * (((new Random().nextInt(6) % 2) * 2) - 1);
            }
        }
    }
    public Matrix(Matrix matrix) {
        this(matrix.doubleMatrix);
    }
    public Matrix(double[][] matrix) {
        this.doubleMatrix = matrix;
    }
    public Matrix findInverse() {
        return new Inverse(getAdjugate(doubleMatrix), getDeterminant(doubleMatrix));

    }
    
    protected static BigDecimal getDeterminant(double[][] matrix) {
        if (matrix.length != matrix[0].length) {
            throw new RuntimeException("There is no determinant in this matrix!");
        } else {
            int rowsSwapped = 0;
            double[][] answerMatrix = new double[matrix.length][matrix.length];
          //  double[] divisionArr = new double[matrix.length];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    answerMatrix[i][j] = matrix[i][j];
                }
            }
            double[][] tempInverse = new double[matrix.length - 1][1];
            for(int i = 0; i < matrix.length; i++) {
                double[] zeros = new double[answerMatrix.length];
                double[] locs = new double[answerMatrix.length];
                for (int z = 0; z < answerMatrix.length; z++) {
                    int amountOfZeros = 0;
                    for (int y = 0; y < answerMatrix.length; y++) {
                        if (answerMatrix[z][y] == 0.0) {
                            amountOfZeros++;
                        } else {
                            break;
                        }
                    }
                    zeros[z] = amountOfZeros;
                }

                for (int x = 0; x < zeros.length; x++) {
                    for (int y = 0; y < zeros.length; y++) {
                        if (zeros[y] > zeros[x]) {
                            double temp = zeros[x];
                            zeros[x] = zeros[y];
                            zeros[y] = temp;
                            double[] temp2 = answerMatrix[y];
                            answerMatrix[y] = answerMatrix[x];
                            answerMatrix[x] = temp2;
                            rowsSwapped++;
                        }
                    }
                }
                for (int j = 0; j < matrix.length - 1 - i; j++) {
                    double thing = answerMatrix[matrix.length - 1 - j][i];
                    double multiplyBy = answerMatrix[i][i];
                    if (thing != 0 && multiplyBy != 0) {
                        for (int y = 0; y < matrix[0].length; y++) {
                            answerMatrix[matrix.length - 1 - j][y] = (answerMatrix[matrix.length - 1 - j][y] * multiplyBy - answerMatrix[i][y] * thing) / multiplyBy;
                        }
                    }
                }
             }
            BigDecimal answer = new BigDecimal(1);
            for (int i = 0; i < answerMatrix.length; i++) {
                answer = answer.multiply(BigDecimal.valueOf(answerMatrix[i][i]));
            }
            return answer.multiply(BigDecimal.valueOf(Math.pow(-1, rowsSwapped)));
        }
    }

    
    /**
     * otherMatrix is the one multiplying
     */
    public Matrix multiplyMatrix(Matrix otherMatrix) {
        if (otherMatrix.doubleMatrix[0].length != this.doubleMatrix.length)
            throw new RuntimeException("That is not a valid matrix to multiply by!");
        double[][] newMatrixAsArr = new double[doubleMatrix.length][doubleMatrix[0].length];
        
        for (int i = 0; i < otherMatrix.doubleMatrix.length; i++) {
            for (int x = 0; x < newMatrixAsArr[i].length; x++) {
                int answerForLoc = 0;
                for (int y = 0; y < doubleMatrix.length; y++) {
                    answerForLoc += doubleMatrix[y][x] * otherMatrix.doubleMatrix[i][y];
                }
                newMatrixAsArr[i][x] = answerForLoc;
            }
        }
        return new Matrix(newMatrixAsArr);
    }
    
    public Inverse multiplyMatrix(Inverse otherMatrix) {
        if (otherMatrix.doubleMatrix[0].length != this.doubleMatrix.length)
            throw new RuntimeException("That is not a valid matrix to multiply by!");
        double[][] newMatrixAsArr = new double[doubleMatrix.length][doubleMatrix[0].length];
        
        for (int i = 0; i < otherMatrix.doubleMatrix.length; i++) {
            for (int x = 0; x < newMatrixAsArr[i].length; x++) {
                int answerForLoc = 0;
                for (int y = 0; y < doubleMatrix.length; y++) {
                    answerForLoc += doubleMatrix[y][x] * otherMatrix.doubleMatrix[i][y];
                }
                newMatrixAsArr[i][x] = answerForLoc;
            }
        }
        return new Inverse(newMatrixAsArr, otherMatrix.getDeterminant());
    }
    private static double[][] getAdjugate(double[][] matrix) {
        double[][] adjugate = new double[matrix.length][matrix.length];

        double zeroZero = matrix[0][0];
        double[][] newMatrix;
        double coFactor = 0;
        int locationOfY = 0;
        int locationOfX = 0;
        int posOrNeg = 1;
        for (int j = 0; j < matrix[0].length; j++) {

            for (int i = 0; i < matrix.length; i++) {
                newMatrix = new double[matrix.length - 1][matrix.length - 1];

                for (int x = 0; x < matrix.length; x++) {
                    for (int y = 0; y < matrix[x].length; y++) {

                        if (i != x && j != y) {
                            newMatrix[locationOfY][locationOfX] = matrix[y][x];  
                            locationOfY++;

                        }
                    }
                    if (i != x) {
                        locationOfY = 0;
                        locationOfX++;
                    }

                }
                locationOfX = 0;
                BigDecimal determinant = getDeterminant(newMatrix);
                adjugate[i][j] = determinant.multiply(BigDecimal.valueOf(((posOrNeg % 2) * 2) - 1)).doubleValue();
                posOrNeg++;
            }
            if (adjugate.length % 2 == 0) {
                posOrNeg--;
            }
        }
        return adjugate;
    
        
    }
    public int getYLength() {
        return doubleMatrix.length;
    }
    public int getXLength() {
        return doubleMatrix[0].length;
    }
    public int getTotalLength() {
        return doubleMatrix[0].length * doubleMatrix.length;
    }
    public double[][] getMatrixAsArray() {
        return doubleMatrix;
    }
    public String toPythonList() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (double[] row : doubleMatrix) {
            builder.append("[");
            for (double pos : row) {
                builder.append(pos).append(", ");
                
            }
            builder.delete(builder.length() - 2, builder.length());
            builder.append("],");
        }
        builder.delete(builder.length() - 1, builder.length());
        builder.append("]");
        return builder.toString();
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (double[] row : doubleMatrix) {
            builder.append("[");
            for (double pos : row) {
                builder.append(pos).append(" ");
                
            }
            builder.delete(builder.length() - 1, builder.length());
            builder.append("]\n");
        }
        builder.delete(builder.length() - 1, builder.length());

        return builder.toString();
    }

}