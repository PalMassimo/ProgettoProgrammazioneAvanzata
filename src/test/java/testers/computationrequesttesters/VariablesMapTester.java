package testers.computationrequesttesters;

import org.junit.jupiter.api.Test;
import project.computation.VariablesMap;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VariablesMapTester {

    @Test
    public void variablesMapTest() {
        String variableValuesFunction = "x0:-1:2:11,x1:10:2:20,y:6:10:46";
        Map<String, double[]> variablesMap = new VariablesMap(variableValuesFunction);
        assertEquals(new HashSet<>(Arrays.asList("x0", "x1", "y")), variablesMap.keySet());
        assertArrayEquals(new double[]{-1, 1, 3, 5, 7, 9, 11}, variablesMap.get("x0"));
        assertArrayEquals(new double[]{10, 12, 14, 16, 18, 20}, variablesMap.get("x1"));
        assertArrayEquals(new double[]{6, 16, 26, 36, 46}, variablesMap.get("y"));
    }

}
