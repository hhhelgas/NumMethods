package org.example.Jacobi;

import java.util.Random;
import java.util.Arrays;

public class JacobiMethod {
    private static final long MAX_ITERATIONS = 1_000_000; // максимальное количество итераций
    private static final double TOLERANCE = 1e-6;  // точность

    // Метод для решения СЛАУ методом Якоби
    public static double[] solve(double[][] A, double[] b) {
        int n = A.length;
        double[] x = new double[n];  // начальное приближение (все нули)
        double[] xNew = new double[n];
        Arrays.fill(x, 0);  // Заполняем начальный вектор нулями

        for (int iter = 0; iter < MAX_ITERATIONS; iter++) {
            for (int i = 0; i < n; i++) {
                double sum = b[i];
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        sum -= A[i][j] * x[j];
                    }
                }
                xNew[i] = sum / A[i][i];
            }

            // Проверка на сходимость
            if (converged(x, xNew)) {
                System.out.println("Система сошлась за " + iter + " итераций.");
                return xNew;
            }

            // Обновляем вектор решения
            System.arraycopy(xNew, 0, x, 0, n);
        }

        throw new RuntimeException("Метод Якоби не сошелся за " + MAX_ITERATIONS + " итераций.");
    }

    // Метод для проверки сходимости (евклидова норма разности векторов)
    private static boolean converged(double[] xOld, double[] xNew) {
        double norm = 0;
        for (int i = 0; i < xOld.length; i++) {
            norm += Math.pow(xNew[i] - xOld[i], 2);
        }
        norm = Math.sqrt(norm);
        return norm < TOLERANCE;
    }

    // Генерация диагонально доминирующей матрицы для тестирования
    public static double[][] generateMatrix(int n) {
        Random rand = new Random();
        double[][] A = new double[n][n];
        for (int i = 0; i < n; i++) {
            double rowSum = 0;
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    A[i][j] = rand.nextDouble() * 10;
                    rowSum += Math.abs(A[i][j]);
                }
            }
            A[i][i] = rowSum + rand.nextDouble() * 10;  // Обеспечиваем диагональное преобладание
        }
        return A;
    }

    // Генерация вектора b для тестирования
    public static double[] generateVector(int n) {
        Random rand = new Random();
        double[] b = new double[n];
        for (int i = 0; i < n; i++) {
            b[i] = rand.nextDouble() * 10;
        }
        return b;
    }

    // Печать матрицы
    public static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double val : row) {
                System.out.printf("%10.4f ", val);
            }
            System.out.println();
        }
    }

    // Печать вектора
    public static void printVector(double[] vector) {
        for (double val : vector) {
            System.out.printf("%10.4f ", val);
        }
        System.out.println();
    }




    }