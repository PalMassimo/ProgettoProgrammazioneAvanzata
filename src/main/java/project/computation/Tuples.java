package project.computation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author massi
 */
public class Tuples {

    private Set<Map<String, Double>> tuples;

    public Tuples(VariablesMap variablesMap, String valuesKind) {

        switch (valuesKind) {
            case "LIST" -> createTuplesByList(variablesMap);
            case "GRID" -> createTuplesByGrid(variablesMap);
            default -> {
                System.out.println("unknown value kind");
                System.exit(0);
            }
        }

    }

    public Set<Map<String, Double>> getTuples() {
        return tuples;
    }

    private void createTuplesByList(VariablesMap variablesMap) {
        this.tuples = new HashSet<>();
        int listLength = getListLength(variablesMap);
        for (int i = 0; i < listLength; i++) {
            Map<String, Double> tuple = new HashMap<>();
            for (String variable : variablesMap.getVariablesValuesMap().keySet()) {
                tuple.put(variable, variablesMap.getVariablesValuesMap().get(variable)[i]);
            }
            this.tuples.add(tuple);
        }
    }

    private int getListLength(VariablesMap variablesMap) {

        //set containing the
        Set<Integer> numberOfValuesTakenByEachVariables = new HashSet<>();
        for (String variable : variablesMap.getVariablesValuesMap().keySet()) {
            numberOfValuesTakenByEachVariables.add(variablesMap.getVariablesValuesMap().get(variable).length);
        }

        if (numberOfValuesTakenByEachVariables.size() == 1) {
            Iterator iterator = numberOfValuesTakenByEachVariables.iterator();
            return (int) iterator.next();
        } else {
            throw new IllegalArgumentException("wrong number of values taken by one or more variables");
        }

    }

    private void createTuplesByGrid(VariablesMap variablesMap) {

        CartesianProduct cartesianProduct = new CartesianProduct(variablesMap.getVariablesValuesMap());
        this.tuples = cartesianProduct.getTuples();

    }

}
