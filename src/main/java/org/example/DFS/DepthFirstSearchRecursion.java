package org.example.DFS;

import java.util.*;


public class DepthFirstSearchRecursion {
    // Метод для выполнения обхода в глубину

    public static void dfs(Map<Integer, List<Integer>> graph, int start, Set<Integer> visited) {
        visited.add(start);  // Помечаем текущую вершину как посещённую
//        System.out.println(start);  // Делаем что-то с текущей вершиной (например, выводим её)

        // Проходим по всем соседям текущей вершины
        for (int neighbor : graph.get(start)) {
            if (!visited.contains(neighbor)) {
                dfs(graph, neighbor, visited);  // Рекурсивный вызов для непосещенных соседей
            }
        }
    }



    public static void main(String[] args) {

    }
}
