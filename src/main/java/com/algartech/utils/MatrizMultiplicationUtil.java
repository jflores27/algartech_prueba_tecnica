package com.algartech.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MatrizMultiplicationUtil {

    public int[][] multiplyMatricesConcurrently(int[][] matrix1, int[][] matrix2) {
        int matrix1Rows = matrix1.length;
        int matrix1Cols = matrix1[0].length;
        int matrix2Cols = matrix2[0].length;

        int[][] result = new int[matrix1Rows][matrix2Cols];
        ExecutorService executor = Executors.newFixedThreadPool(matrix1Rows * matrix2Cols);

        for (int row = 0; row < matrix1Rows; row++) {
            for (int col = 0; col < matrix2Cols; col++) {
                int finalRow = row;
                int finalCol = col;
                executor.execute(() -> result[finalRow][finalCol] = multiplyMatricesCell(matrix1, matrix2, finalRow, finalCol));
            }
        }

        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }

        return result;
    }

    private int multiplyMatricesCell(int[][] matrix1, int[][] matrix2, int row, int col) {
        int sum = 0;
        for (int i = 0; i < matrix2.length; i++) {
            sum += matrix1[row][i] * matrix2[i][col];
        }
        return sum;
    }
}

