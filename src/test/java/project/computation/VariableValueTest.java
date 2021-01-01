package project.computation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VariableValueTest {

    @Test
    public void variableValueTester1(){
        String variableValuesLine = "x0:-1:1:5";
        VariableValues variableValues = new VariableValues(variableValuesLine);
        assertEquals("x0", variableValues.getVariableName());
        assertArrayEquals(variableValues.getValues(), new Double[]{-1d, 0d, 1d, 2d, 3d, 4d, 5d});
    }

    @Test
    public void variableValueTester2(){
        String variableValuesLine = "g34534:-1:0.1:-0.5";
        VariableValues variableValues = new VariableValues(variableValuesLine);
        assertEquals("g34534", variableValues.getVariableName());
        assertArrayEquals(variableValues.getValues(), new Double[]{-1d, -0.9d, -0.8d, -0.7d, -0.6d, -0.5d});
    }

}
