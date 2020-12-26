
package project.computation;

/**
 *
 * @author massi
 */
public class VariableValues {

    private final String variableName;
    private Double[] values;

    public VariableValues(String variableWithValue) {

        this.variableName = variableWithValue.split(":")[0];

        double minValue = Double.parseDouble(variableWithValue.split(":")[1]);
        double stepSize = Double.parseDouble(variableWithValue.split(":")[2]);
        double maxValue = Double.parseDouble(variableWithValue.split(":")[3]);

        setValues(minValue, stepSize, maxValue);
    }
    
    public String getVariableName() {
        return variableName;
    }

    public Double[] getValues() {
        return values;
    }

    private void setValues(double minValue, double stepSize, double maxValue) {

        int arrayLength = (int) ((maxValue - minValue) / stepSize) + 1;
        this.values = new Double[arrayLength];

        for (int i = 0; i < values.length; i++) {
            values[i] = minValue + i * stepSize;
        }

    }

}
