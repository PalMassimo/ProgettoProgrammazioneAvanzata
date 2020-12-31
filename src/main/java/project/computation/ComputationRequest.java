package project.computation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author massi
 */
public class ComputationRequest {

    private final String computationKind;                       // MIN, MAX, LIST or COUNT
    private final String valuesKind;                            // GRID, LIST
    private final String variableValuesFunction;                // e.g. x0:-1:0.1:1,x1:10:2:20, ...
    private final Set<String> expressions = new HashSet<>();    // e.g. (x0+5)-x1

    public ComputationRequest(String computationRequest) {
        String[] tokens = computationRequest.split(";");
        this.computationKind = tokens[0].split("_")[0];
        this.valuesKind = tokens[0].split("_")[1];
        this.variableValuesFunction = tokens[1];
        buildExpressionSet(computationRequest.split(";"));
    }

    public Set<String> getExpressions() {
        return expressions;
    }

    public String getComputationKind() {
        return computationKind;
    }

    public String getVariableValuesFunction() {
        return variableValuesFunction;
    }

    public String getValuesKind() {
        return valuesKind;
    }

    private void buildExpressionSet(String[] tokensComputationRequest) {
        this.expressions.addAll(Arrays.asList(tokensComputationRequest).subList(2, tokensComputationRequest.length));
    }
}
