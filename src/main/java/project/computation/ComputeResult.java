package project.computation;

import project.medvet.Constant;
import project.medvet.Node;
import project.medvet.Operator;
import project.medvet.Parser;
import project.medvet.Variable;
import project.utils.ComputationKind;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static project.utils.ComputationKind.*;

/**
 * @author massi
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

//        return switch (computationKind) {
//            case MAX -> ;
//            case "MIN" ->
//            case "AVG" -> calculateAvg();
//            case "COUNT" -> allResults.size();
//            default -> 0; //TODO: fix
//        };
    }

    private double calculateAvg() {
        double sum = allResults.stream().mapToDouble(Double::doubleValue).sum();
//        for (Double real : allResults) {
//            sum = sum + real;
//        }

        return sum / allResults.size();
    }

}
