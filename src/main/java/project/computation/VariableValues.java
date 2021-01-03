
package project.computation;

import java.util.stream.IntStream;

/**
 * @author Massimo Palmisano
 * ADVANCED PROGRAMMING PROJECT - A class that models a variable and his values
 */
public class VariableValues {

    private final String variableName;
    private final Double[] values;

    public VariableValues(String variableWithValue) {

        this.variableName = variableWithValue.split(":")[0];

        double minValue = Double.parseDouble(variableWithValue.split(":")[1]);
        double stepSize = Double.parseDouble(variableWithValue.split(":")[2]);
        double maxValue = Double.parseDouble(variableWithValue.split(":")[3]);

        this.values = IntStream.range(0, (int) ((maxValue - minValue) / stepSize) + 1)
                .mapToObj(indexValue -> minValue + indexValue * stepSize)
                .toArray(Double[]::new);

    }

    public String getVariableName() {
        return variableName;
    }

    public Double[] getValues() {
        return values;
    }


}
