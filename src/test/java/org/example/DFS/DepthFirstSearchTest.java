package org.example.DFS;

import java.util.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.example.DFS.Graph.*;
import static org.junit.jupiter.api.Assertions.*;


public class DepthFirstSearchTest {

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
        printGraph(graph);

        Set<Integer> expected = new HashSet<>(graph.keySet());
        Set<Integer> visited = new HashSet<>(); // Множество для хранения посещенных вершин

        DepthFirstSearchRecursion.dfs(graph, 0, visited);  // Запускаем DFS, начиная с вершины 0

        Assertions.assertIterableEquals(expected, visited);

    }

    @Test
    public void testGeneratedGraphRec() {
        Map<Integer, List<Integer>> graph = generateGraphDFS(6, 8);

        printGraph(graph);

        Set<Integer> expected = new HashSet<>(graph.keySet());
        Set<Integer> visited = new HashSet<>(); // Множество для хранения посещенных вершин

        DepthFirstSearchRecursion.dfs(graph, 0, visited);  // Запускаем DFS, начиная с вершины 0

        Assertions.assertIterableEquals(expected, visited);
    }

    @Test
    public void testLargerGeneratedGraphRec() {
        Map<Integer, List<Integer>> graph = generateGraphDFS(1000, 20000);

        printGraph(graph);

        Set<Integer> expected = new HashSet<>(graph.keySet());
        Set<Integer> visited = new HashSet<>(); // Множество для хранения посещенных вершин

        DepthFirstSearchRecursion.dfs(graph, 0, visited);  // Запускаем DFS, начиная с вершины 0

        Assertions.assertIterableEquals(expected, visited);
    }


    @Test
    public void testSimpleGraphStack() {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(1, 2));
        graph.put(1, Arrays.asList(2, 3, 4));
        graph.put(2, Arrays.asList(0, 5));
        graph.put(3, Arrays.asList(1));
        graph.put(4, Arrays.asList(1, 5));
        graph.put(5, Arrays.asList(2, 4));

        System.out.println("Граф: ");
        printGraph(graph);

        Set<Integer> expected = new HashSet<>(graph.keySet());
        Set<Integer> visited = new HashSet<>(); // Множество для хранения посещенных вершин

        DepthFirstSearchStack.dfs(graph, 0, visited);

        Assertions.assertIterableEquals(expected, visited);

    }

    @Test
    public void testGeneratedGraphStack() {
        Map<Integer, List<Integer>> graph = generateGraphDFS(6, 8);

        printGraph(graph);

        Set<Integer> expected = new HashSet<>(graph.keySet());
        Set<Integer> visited = new HashSet<>(); // Множество для хранения посещенных вершин

        DepthFirstSearchStack.dfs(graph, 0, visited);  // Запускаем DFS, начиная с вершины 0

        Assertions.assertIterableEquals(expected, visited);
    }

    @Test
    public void testLargerGeneratedGraphStack() {
        Map<Integer, List<Integer>> graph = generateGraphDFS(1000, 20000);

        printGraph(graph);

        Set<Integer> expected = new HashSet<>(graph.keySet());
        Set<Integer> visited = new HashSet<>(); // Множество для хранения посещенных вершин

        DepthFirstSearchStack.dfs(graph, 0, visited);

        Assertions.assertIterableEquals(expected, visited);
    }


    @Test
    public void testTooMuchEdges() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Map<Integer, List<Integer>> graph = generateGraphDFS(6, 60);
        });

        String expectedMessage = "Слишком много рёбер для неориентированного графа";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage), "Неверное сообщение об ошибке");
    }

    @Test
    public void testTooLittleEdges() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Map<Integer, List<Integer>> graph = generateGraphDFS(6, 1);
        });

        String expectedMessage = "Количество рёбер должно быть не меньше V-1, чтобы граф был связным";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage), "Неверное сообщение об ошибке");
    }

}
