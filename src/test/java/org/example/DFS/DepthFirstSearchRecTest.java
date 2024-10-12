package org.example.DFS;

import java.util.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class DepthFirstSearchRecTest {

    @Test
    public void testSimpleGraphRec() {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(1, 2));
        graph.put(1, Arrays.asList(2, 3, 4));
        graph.put(2, Arrays.asList(0, 5));
        graph.put(3, Arrays.asList(1));
        graph.put(4, Arrays.asList(1, 5));
        graph.put(5, Arrays.asList(2, 4));

        System.out.println("Граф: ");
        GraphService.printGraph(graph);

        Set<Integer> expected = new HashSet<>(graph.keySet());
        Set<Integer> visited = new HashSet<>(); // Множество для хранения посещенных вершин

        DepthFirstSearchRecursion.dfs(graph, 0, visited);  // Запускаем DFS, начиная с вершины 0

        Assertions.assertIterableEquals(expected, visited);

    }

    @Test
    public void testGeneratedGraphRec() {
        Map<Integer, List<Integer>> graph = GraphService.generateGraphDFS(6, 8);

        GraphService.printGraph(graph);

        Set<Integer> expected = new HashSet<>(graph.keySet());
        Set<Integer> visited = new HashSet<>(); // Множество для хранения посещенных вершин

        DepthFirstSearchRecursion.dfs(graph, 0, visited);  // Запускаем DFS, начиная с вершины 0

        Assertions.assertIterableEquals(expected, visited);
    }

    @Test
    public void testLargerGeneratedGraphRec() {
        Map<Integer, List<Integer>> graph = GraphService.generateGraphDFS(1000, 20000);

        GraphService.printGraph(graph);

        Set<Integer> expected = new HashSet<>(graph.keySet());
        Set<Integer> visited = new HashSet<>(); // Множество для хранения посещенных вершин

        DepthFirstSearchRecursion.dfs(graph, 0, visited);  // Запускаем DFS, начиная с вершины 0

        Assertions.assertIterableEquals(expected, visited);
    }


}
