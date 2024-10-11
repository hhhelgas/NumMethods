package org.example.Jacobi;

public class Main {
    public static void main(String[] args) {
//        int n = 5;  // Размер матрицы
//        double[][] A = generateMatrix(n);
//        double[] b = generateVector(n);
//
//        System.out.println("Сгенерированная матрица A:");
//        printMatrix(A);
//
//        System.out.println("Сгенерированный вектор b:");
//        printVector(b);
//
//        try {
//            double[] solution = solve(A, b);
//            System.out.println("Решение системы:");
//            printVector(solution);
//        } catch (RuntimeException e) {
//            System.out.println(e.getMessage());
//        }


        // Простая матрица с диагональным преобладанием
        double[][] A = {
                {10, 1, 1},
                {2, 10, 1},
                {2, 1, 10}
        };

        // Вектор правой части
        double[] b = {12, 13, 14};

        System.out.println("Матрица A:");
        JacobiMethod.printMatrix(A);

        System.out.println("Вектор b:");
        JacobiMethod.printVector(b);

        try {
            double[] solution = JacobiMethod.solve(A, b);
            System.out.println("Решение системы:");
            JacobiMethod.printVector(solution);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

    }
}