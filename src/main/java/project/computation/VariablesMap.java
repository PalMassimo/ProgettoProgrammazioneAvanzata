package project.computation;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author massi
 */
public class VariablesMap {

    private final Map<String, Double[]> variablesValuesMap = new HashMap<>();
    private final int numberOfVariables;
    private final Set<String> variableNames;

    public VariablesMap(String variableValuesFunction) {

        String[] variablesValues = variableValuesFunction.split(",");
        this.numberOfVariables = variablesValues.length;

        for (String iterator : variablesValues) {
            VariableValues variableValue = new VariableValues(iterator);
            this.variablesValuesMap.put(variableValue.getVariableName(), variableValue.getValues());
        }

        this.variableNames = variablesValuesMap.keySet();

    }

    public Set<String> getVariableNames() {
        return variableNames;
    }

    public Map<String, Double[]> getVariablesValuesMap() {
        return variablesValuesMap;
    }

    public int getNumberOfVariables() {
        return numberOfVariables;
    }

    public Set<String> getVariablesName() {
        return variablesValuesMap.keySet();
    }

}
