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
//            replaceVariablesWithTheirValues(root, tuple);
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
            double rightValue = computeExpression(((Operator) (node)).getChildren().get(1), tuple);
            double leftValue = computeExpression(((Operator) (node)).getChildren().get(0), tuple);
            return ((Operator) node).getType().getFunction().apply(new double[]{leftValue, rightValue});
        }

    }

    public double getFinalResult() {
        return finalResult;
    }

    private double computeFinalResult(String computationKind) {

        switch (computationKind) {

            case "MAX":
                return Collections.max(allResults);
            case "MIN":
                return Collections.min(allResults);
            case "AVG":
                return calculateAvg();
            case "COUNT":
                return allResults.size();
//            default:
//                //SISTEMA
//                System.out.println("Unsupported Operation");
//                System.exit(0);
//                break;
        }
        return 0;//correggi
    }

    private double calculateAvg() {
        double sum = 0;
        for (Double real : allResults) {
            sum = sum + real;
        }

        return sum / allResults.size();
    }

}
