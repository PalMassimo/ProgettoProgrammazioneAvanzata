
package project.computation;

import java.util.stream.IntStream;

/**
 * @author massi
 */
public class VariableValues {

    private final String variableName;
    private final double[] values;

    public VariableValues(String variableWithValue) {

        this.variableName = variableWithValue.split(":")[0];

        double minValue = Double.parseDouble(variableWithValue.split(":")[1]);
        double stepSize = Double.parseDouble(variableWithValue.split(":")[2]);
        double maxValue = Double.parseDouble(variableWithValue.split(":")[3]);

        this.values = IntStream.range(0, (int) ((maxValue - minValue) / stepSize) + 1)
                .mapToDouble(indexValue -> minValue + indexValue * stepSize)
                .toArray();

    }

    public String getVariableName() {
        return variableName;
    }

    public double[] getValues() {
        return values;
    }


}
