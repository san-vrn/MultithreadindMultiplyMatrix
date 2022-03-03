package com.matrix_multiply;

public class MultiplyMatrix{
    int[][] matrixA;
    int[][] matrixB;
    int[][] matrixMultiply;
    final Matrix[] list;

    long startTime;
    long endTime;
    long multiTime;
    long singleTime;

    public MultiplyMatrix(int[][] matrixA, int[][] matrixB) {
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.list = new Matrix[matrixA.length];
        this.matrixMultiply = new int[matrixA.length][matrixB[0].length];
    }

    // многопоточное перемножение
    public int[][] MultiplyMultiThread(){

        startTime = System.nanoTime();
        for (int i = 0; i < list.length; i++) {
            list[i] = new Matrix(matrixB, matrixA[i]);
            list[i].start();
        }
        try {
            for(int l = 0; l< list.length; l++){
                list[l].join();
                matrixMultiply[l] = list[l].getResult();
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        endTime = System.nanoTime();
        multiTime = endTime - startTime;
        System.out.println("\nMatrixMulti = A * B:");
        Matrix.showMatrix(matrixMultiply, matrixMultiply.length);
        return matrixMultiply;
    }

    // однопоточное перемножение
    public int[][] MultiplySingleThread(){

        startTime = System.nanoTime();
        for (int j = 0; j < matrixA.length; j++) {
            matrixMultiply[j] = Matrix.multiplyLine(matrixB, matrixA[j]);
        }
        endTime = System.nanoTime();
        singleTime = endTime - startTime;

        System.out.println("\nMatrixSingle = A * B:");
        Matrix.showMatrix(matrixMultiply, matrixMultiply.length);
        return matrixMultiply;
    }

    public void DisplayTime(){
        System.out.println("\nВремя перемножения мартиц в многопоточном режиме = " + multiTime / 1000000 + " ms");
        System.out.println("\nВремя перемножения мартиц в однопоточном режиме = " + singleTime / 1000000 + " ms");
    }
}
