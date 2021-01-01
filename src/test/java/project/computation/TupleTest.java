package project.computation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static project.utils.ValuesKind.LIST;

public class TupleTest {

    @Test
    public void tupleTester() {
        VariablesMap variablesMap = new VariablesMap("x0:-1:1:1,x1:3:1:5");
        Tuples tuples = new Tuples(variablesMap, LIST);
        Set<Map<String, Double>> set = buildSet();
        Assertions.assertEquals(set, tuples.getTuples());
    }

    public Set<Map<String, Double>> buildSet() {

        Set<Map<String, Double>> set = new HashSet<>();

        Map<String, Double> map1 = new HashMap<>();
        map1.put("x0", -1d);
        map1.put("x1", 3d);

        Map<String, Double> map2 = new HashMap<>();
        map2.put("x0", 0d);
        map2.put("x1", 4d);

        Map<String, Double> map3 = new HashMap<>();
        map3.put("x0", 1d);
        map3.put("x1", 5d);

        set.add(map1);
        set.add(map2);
        set.add(map3);

        return set;
    }


}
