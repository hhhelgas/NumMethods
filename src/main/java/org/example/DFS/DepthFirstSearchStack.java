package org.example.DFS;
import java.util.*;

public class DepthFirstSearchStack {
    // Нерекурсивный метод для выполнения обхода в глубину

    public static void dfs(Map<Integer, List<Integer>> graph, int start, Set<Integer> visited) {
        Stack<Integer> stack = new Stack<>();     // Стек для хранения вершин

        stack.push(start);  // Добавляем стартовую вершину в стек

        while (!stack.isEmpty()) {
            int vertex = stack.pop();  // Извлекаем вершину из стека

            if (!visited.contains(vertex)) {
                visited.add(vertex);  // Помечаем её как посещенную
                System.out.println(vertex);  // Делаем что-то с текущей вершиной

                // Добавляем всех соседей текущей вершины в стек
                for (int neighbor : graph.get(vertex)) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
    }



    public static void main(String[] args) {

    }
}
