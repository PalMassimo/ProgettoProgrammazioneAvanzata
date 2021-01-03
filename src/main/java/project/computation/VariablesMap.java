package project.computation;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author massi
 */
public class VariablesMap extends HashMap<String, Double[]> {

    // variable values function e.g. x0:-1:0.1:1,x2:-2:1:1
    public VariablesMap(String variableValuesFunction){

        super();

        String[] variableValuesLine = variableValuesFunction.split(",");

        Arrays.stream(variableValuesLine).map(VariableValues::new)
                .forEach(variableValue -> put(variableValue.getVariableName(), variableValue.getValues()));

    }

}
