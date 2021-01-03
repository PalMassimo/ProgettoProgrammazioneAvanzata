package project.computation;

import project.utils.ComputationKind;
import project.utils.ValuesKind;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static project.utils.ComputationKind.*;

/**
 * @author Massimo Palmisano
 * ADVANCED PROGRAMMING PROJECT - Class that models a computation request
 */
public class ComputationRequest {

    private final ComputationKind computationKind;              // MIN, MAX, LIST or COUNT
    private final ValuesKind valuesKind;                        // GRID, LIST
    private final String variableValuesFunction;                // e.g. x0:-1:0.1:1,x1:10:2:20, ...
    private final Set<String> expressions = new HashSet<>();    // e.g. (x0+5)-x1

    public ComputationRequest(String computationRequest) {
        String[] tokens = computationRequest.split(";");
        this.computationKind = parseComputationKind(tokens[0].split("_")[0]);
        this.valuesKind = parseValuesKind(tokens[0].split("_")[1]);
        this.variableValuesFunction = tokens[1];
        buildExpressionSet(computationRequest.split(";"));
    }

    private ValuesKind parseValuesKind(String valuesKind) {
        return valuesKind.equals("GRID")? ValuesKind.GRID: ValuesKind.LIST;
    }

    private ComputationKind parseComputationKind(String computationKind) {
        switch(computationKind){
            case "MAX"-> {return MAX;}
            case "MIN" -> {return MIN;}
            case "AVG" -> {return AVG;}
            default -> {return COUNT;}
        }
    }

    public Set<String> getExpressions() {
        return expressions;
    }

    public ComputationKind getComputationKind() {
        return computationKind;
    }

    public String getVariableValuesFunction() {
        return variableValuesFunction;
    }

    public ValuesKind getValuesKind() {
        return valuesKind;
    }

    private void buildExpressionSet(String[] tokensComputationRequest) {
        this.expressions.addAll(Arrays.asList(tokensComputationRequest).subList(2, tokensComputationRequest.length));
    }
}
