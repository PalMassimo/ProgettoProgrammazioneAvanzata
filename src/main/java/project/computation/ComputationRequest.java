package project.computation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author massi
 */
public class ComputationRequest {

    private final String computationKind;
    private final String valuesKind;
    private final Set<String> expressions = new HashSet<>();
    private final String variableValuesFunction;

    public ComputationRequest(String computationRequest) {
        String[] tokens = computationRequest.split(";");
        this.computationKind = tokens[0].split("_")[0];
        this.valuesKind = tokens[0].split("_")[1];
        this.variableValuesFunction = tokens[1];
        buildExpressionSet(computationRequest.split(";"));
    }

    public Set<String> getExpression() {
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
