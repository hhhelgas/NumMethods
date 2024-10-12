package org.example.DFS;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.example.DFS.GraphService.generateGraphDFS;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GraphServiceTest {
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
