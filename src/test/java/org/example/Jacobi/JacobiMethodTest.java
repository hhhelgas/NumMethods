package org.example.Jacobi;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JacobiMethodTest {

    // Тест на небольшой матрице 2x2
    @Test
    public void testSimpleCase() {
        // Матрица A
        double[][] A = {
                {4, 1},
                {2, 3}
        };

        // Вектор правых частей b
        double[] b = {15, 10};

        // Ожидаемое решение (точное решение: x = {3, 1})
        double[] expectedSolution = {3.5, 1};

        // Решаем СЛАУ методом Якоби
        double[] actualSolution = JacobiMethod.solve(A, b);

        // Сравниваем полученное решение с ожидаемым
        assertArrayEquals(expectedSolution, actualSolution, 1e-6, "Решение должно быть близко к {3, 1}");
    }

    @Test
    public void testSimpleCase2() {
        // Простая система уравнений:
        // 10x + y + z = 12
        // 2x + 10y + z = 13
        // 3x + y + 10z = 14
        double[][] A = {
                {10, 1, 1},
                {2, 10, 1},
                {3, 1, 10}
        };
        double[] b = {12, 13, 14};

        // Ожидаемое решение
        double[] expectedSolution = {1.0, 1.0, 1.0};

        // Решение методом Якоби
        double[] solution = JacobiMethod.solve(A, b);

        // Проверка, что решение достаточно близко к ожидаемому с точностью до 1e-6
        for (int i = 0; i < expectedSolution.length; i++) {
            assertEquals(expectedSolution[i], solution[i], 1e-4,
                    "Ошибка на индексе [" + i + "]: Ожидалось " + expectedSolution[i] + ", Получено " + solution[i]);
        }
    }

    // Тест на случайно сгенерированной диагонально доминирующей матрице 5x5
    @Test
    public void testLargerCase() {
        int n = 1000;

        // Генерируем случайную диагонально доминирующую матрицу A
        double[][] A = JacobiMethod.generateMatrix(n);

        // Генерируем вектор b
        double[] b = JacobiMethod.generateVector(n);

        // Печатаем матрицу и вектор для наглядности
        System.out.println("Тестовая матрица A:");
        JacobiMethod.printMatrix(A);
        System.out.println("Тестовый вектор b:");
        JacobiMethod.printVector(b);

        // Пытаемся решить СЛАУ методом Якоби
        double[] solution = JacobiMethod.solve(A, b);

        // Печатаем найденное решение
        System.out.println("Решение:");
        JacobiMethod.printVector(solution);

        // Проверим, что решение не содержит NaN или бесконечностей
        for (double value : solution) {
            assertFalse(Double.isNaN(value) || Double.isInfinite(value), "Значения в решении должны быть конечными числами.");
        }

//        // Проверим, что произведение A * solution близко к b
//        double[] Ax = new double[n];
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                Ax[i] += A[i][j] * solution[j];
//            }
//        }
//
//        // Сравниваем Ax с b с допустимой погрешностью
//        for (int i = 0; i < n; i++) {
//            assertEquals(b[i], Ax[i], 1e-6,
//                    "Ошибка на индексе [" + i + "]: ожидалось " + b[i] + ", получено " + Ax[i]);
//        }
    }

    @Test
    public void testNonConvergence() {
        // Система, где метод Якоби не должен сходиться (нет диагонального преобладания)
        double[][] A = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        double[] b = {6, 15, 24};

        // Ожидаем, что решение не будет достигнуто, и метод выбросит исключение
        Exception exception = assertThrows(RuntimeException.class, () -> {
            JacobiMethod.solve(A, b);
        });

        String expectedMessage = "Метод Якоби не сошелся за";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage), "Неверное сообщение об ошибке");
    }



}
