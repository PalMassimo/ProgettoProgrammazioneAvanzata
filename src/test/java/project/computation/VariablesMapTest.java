package project.computation;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VariablesMapTest {

    @Test
    public void variablesMapTest() {
        String variableValuesFunction = "x0:-1:2:11,x1:10:2:20,y:6:10:46";
        Map<String, Double[]> variablesMap = new VariablesMap(variableValuesFunction);
        assertEquals(new HashSet<>(Arrays.asList("x0", "x1", "y")), variablesMap.keySet());
        assertArrayEquals(new Double[]{-1d, 1d, 3d, 5d, 7d, 9d, 11d}, variablesMap.get("x0"));
        assertArrayEquals(new Double[]{10d, 12d, 14d, 16d, 18d, 20d}, variablesMap.get("x1"));
        assertArrayEquals(new Double[]{6d, 16d, 26d, 36d, 46d}, variablesMap.get("y"));
    }

}
