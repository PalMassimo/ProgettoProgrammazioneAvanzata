package project.computation;

import project.computation.expressioncomponents.Constant;
import project.computation.expressioncomponents.Node;
import project.computation.expressioncomponents.Operator;
import project.computation.expressioncomponents.Parser;
import project.computation.expressioncomponents.Variable;
import project.utils.ComputationKind;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static project.utils.ComputationKind.AVG;
import static project.utils.ComputationKind.MAX;
import static project.utils.ComputationKind.MIN;

/**
 * @author Massimo Palmisano
 * ADVANCED PROGRAMMING PROJECT - A class that compute the result of a computation request
 */
public class ComputeResult {

    private final Set<Double> allResults = new HashSet<>();
    private final double finalResult;

    public ComputeResult(Set<String> expressions, Tuples tuples, ComputationKind computationKind) {

        for (String expression : expressions) {
            for (Map<String, Double> tuple : tuples.getTuples()) {
                Parser parser = new Parser(expression);
                Node root = parser.parse();
                allResults.add(computeExpression(root, tuple));
            }
        }

        this.finalResult = computeFinalResult(computationKind);

    }

    public double computeExpression(Node node, Map<String, Double> tuple) {

        if (node instanceof Constant) {
            return ((Constant) node).getValue();
        } else if (node instanceof Variable) {
            for (String variable : tuple.keySet()) {
                if (variable.equals(((Variable) node).getName())) {
                    return tuple.get(variable);
                }
            }
            throw new IllegalArgumentException("Unknown Variable " + ((Variable) node).getName());
        } else {
            //node is an Operator here
            double rightValue = computeExpression(node.getChildren().get(1), tuple);
            double leftValue = computeExpression(node.getChildren().get(0), tuple);
            return ((Operator) node).getType().getFunction().apply(new double[]{leftValue, rightValue});
        }

    }

    public double getFinalResult() {
        return finalResult;
    }

    private double computeFinalResult(ComputationKind computationKind) {

        if (computationKind == MAX) {
            return Collections.max(allResults);
        } else if (computationKind == MIN) {
            return Collections.min(allResults);
        } else if (computationKind == AVG) {
            return calculateAvg();
        } else {
            return allResults.size();
        }

    }

    private double calculateAvg() {
        double sum = allResults.stream().mapToDouble(Double::doubleValue).sum();
        return sum / allResults.size();
    }

}
