package org.example.DFS;

import java.util.*;

public class GraphService {

    // Метод для генерации неориентированного связного графа без кратных ребер
    public static Map<Integer, List<Integer>> generateGraphDFS(int vertices, int edges) {
        if (edges < vertices - 1) {
            throw new IllegalArgumentException("Количество рёбер должно быть не меньше V-1, чтобы граф был связным.");
        }

        // Максимальное количество рёбер для неориентированного графа
        int maxEdges = vertices * (vertices - 1) / 2;
        if (edges > maxEdges) {
            throw new IllegalArgumentException("Слишком много рёбер для неориентированного графа. Максимум для " + vertices + " вершин: " + maxEdges);
        }

        // Список смежности для хранения графа
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < vertices; i++) {
            graph.put(i, new ArrayList<>());
        }

        // Случайно соединяем вершины, чтобы создать остовное дерево (гарантирует связность)
        Random random = new Random();
        List<int[]> edgesList = new ArrayList<>();

        // Используем алгоритм случайного остовного дерева (Spanning Tree) для обеспечения связности
        List<Integer> connected = new ArrayList<>();
        List<Integer> unconnected = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            unconnected.add(i);
        }

        // Берём первую вершину, как стартовую
        connected.add(unconnected.remove(0));

        while (!unconnected.isEmpty()) {
            int u = connected.get(random.nextInt(connected.size()));
            int v = unconnected.remove(random.nextInt(unconnected.size()));

            // Добавляем рёбра в обе стороны, так как граф неориентированный
            graph.get(u).add(v);
            graph.get(v).add(u);

            edgesList.add(new int[]{u, v}); // Добавляем ребро в список
            connected.add(v); // Добавляем новую вершину в связанный список
        }

        // Добавляем дополнительные случайные рёбра для выполнения условия "edges"
        while (edgesList.size() < edges) {
            int u = random.nextInt(vertices);
            int v = random.nextInt(vertices);

            if (u != v && !graph.get(u).contains(v)) { // Проверяем на отсутствие кратных рёбер
                graph.get(u).add(v);
                graph.get(v).add(u);
                edgesList.add(new int[]{u, v});
            }
        }

        return graph;
    }
    public static void printGraph(Map<Integer, List<Integer>> graph) {

        for (int i: graph.keySet()) {
            System.out.println(i + " -> " + graph.get(i));
        }
    }
}
