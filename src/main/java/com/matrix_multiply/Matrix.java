package com.matrix_multiply;

public class Matrix extends Thread {

    private int[][] matrix;
    private int[] row;
    private int[] result;
    private boolean finish;

    public Matrix(int[][] matrix, int[] row) {
        this.matrix = matrix;
        this.row = row;
    }

    @Override
    public void run() {
        result = multiplyLine(matrix, row);
        finish = true;
    }

    public static int[] multiplyLine(int[][] matr, int[] row) {
        int result[] = new int[matr.length];

        for (int i = 0; i < matr.length; i++) {
            for (int j = 0; j < matr.length; j++) {
                result[i] += row[j] * matr[j][i];
            }
        }
        return result;
    }

    public static int[][] randomMatrix(int matrixSize, int rand) {
        long startTimeRand;
        long endTimeRand;
        long timeRand;

        startTimeRand = System.nanoTime();
        int[][] matrix = new int[matrixSize][matrixSize];
        Thread[] threads = new Thread[matrixSize];

        for (int i = 0; i < matrixSize; i++) {
            int finalI = i;
            threads[i] = new Thread(){
                public void run() {
                    for (int j = 0; j < matrixSize; j++) {
                        matrix[finalI][j] = (int) (Math.random() * rand);
                    }

                }
            };
            threads[i].start();
        }
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
        }
        endTimeRand = System.nanoTime();
        System.out.println("время заполнения матрицы рандомными числами = " + (endTimeRand - startTimeRand)/1000000 + " ms");
        return matrix;
    }

    public static void showMatrix(int[][] matrix, int matrixSize) {
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean getFinish() {
        return finish;
    }

    public int[] getResult() {
        return result;
    }


}