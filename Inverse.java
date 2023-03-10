import java.text.DecimalFormat;
import java.math.*;
public class Inverse extends Matrix {
    private BigDecimal determinant;
    private static DecimalFormat df =  new DecimalFormat("0.000000");
    public Inverse(double[][] matrix, BigDecimal determinant) {
        super(matrix);
        this.determinant = determinant;
    } 
    @Override
    public Matrix multiplyMatrix(Matrix otherMatrix) {
        Matrix matrix  = super.multiplyMatrix(otherMatrix);
        for (int i = 0; i < matrix.doubleMatrix.length; i++) {
            for (int j = 0; j < matrix.doubleMatrix[i].length; j++) {
                matrix.doubleMatrix[i][j] = BigDecimal.valueOf(matrix.doubleMatrix[i][j]).divide(determinant).doubleValue();
            }
        }
        return matrix;
    }
    public BigDecimal getDeterminant() {
        return this.determinant;
    }
    @Override
    public String toPythonList() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (double[] row : doubleMatrix) {
            builder.append("[");
            for (double pos : row) {
                builder.append(BigDecimal.valueOf(pos).divide(determinant, 10, RoundingMode.HALF_EVEN)).append(", ");
                
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
                builder.append((BigDecimal.valueOf(pos).divide(determinant, 10, RoundingMode.HALF_EVEN))).append(" ");
            }
            builder.delete(builder.length() - 1, builder.length());
            builder.append("]\n");
        }
        builder.delete(builder.length() - 1, builder.length());

        return builder.toString();
    }

}