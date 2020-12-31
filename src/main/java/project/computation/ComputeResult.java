package project.computation;

import project.medvet.Constant;
import project.medvet.Node;
import project.medvet.Operator;
import project.medvet.Parser;
import project.medvet.Variable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author massi
 */
public class ComputeResult {

    private final Set<Double> allResults = new HashSet<>();
    private final double finalResult;

    public ComputeResult(Set<String> expressions, Tuples tuples, String computationKind) {

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

    private double computeFinalResult(String computationKind) {

        return switch (computationKind) {
            case "MAX" -> Collections.max(allResults);
            case "MIN" -> Collections.min(allResults);
            case "AVG" -> calculateAvg();
            case "COUNT" -> allResults.size();
            default -> 0; //TODO: fix
        };
    }

    private double calculateAvg() {
        double sum = 0;
        for (Double real : allResults) {
            sum = sum + real;
        }

        return sum / allResults.size();
    }

}
