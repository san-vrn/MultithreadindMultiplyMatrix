import com.matrix_multiply.Matrix;
import com.matrix_multiply.MultiplyMatrix;

public class Main {

        public static void main(String[] args) {
            int[][] matrixA = Matrix.randomMatrix(1000, 10);
            int[][] matrixB = Matrix.randomMatrix(1000, 10);
            MultiplyMatrix multiply = new MultiplyMatrix(matrixA, matrixB);

            multiply.MultiplyMultiThread();
            multiply.MultiplySingleThread();

            System.out.println("Выполнено");
            System.out.println("\nMatrix A:");
            Matrix.showMatrix(matrixA, matrixA.length);
            System.out.println("\nMatrix B:");
            Matrix.showMatrix(matrixB, matrixB.length);
            multiply.DisplayTime();




        }
}
