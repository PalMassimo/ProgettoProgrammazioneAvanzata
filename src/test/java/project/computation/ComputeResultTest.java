package project.computation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import project.utils.RequestType;
import project.utils.RequestParser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * @author Massimo Palmisano
 * ADVANCED PROGRAMMING PROJECT - A test class
 */
public class ComputeResultTest {

    @ParameterizedTest
    @CsvSource({"'MAX_GRID;x0:0:5:51,x1:0:4:80;(x0+x1)', '130.0'", "'AVG_LIST;x0:-1:1:1,x1:-1:1:1;(x0+x1)', '0.0'"})
    public void computeResultTest(String requestLine, double result)  {
        assertSame(RequestType.COMPUTATION_REQUEST, RequestParser.parseRequest(requestLine));
        ComputationRequest computationRequest = new ComputationRequest(requestLine);
        VariablesMap variablesMap = new VariablesMap(computationRequest.getVariableValuesFunction());
        Tuples tuples = new Tuples(variablesMap, computationRequest.getValuesKind());
        ComputeResult computeResult = new ComputeResult(computationRequest.getExpressions(), tuples, computationRequest.getComputationKind());
        assertEquals(result, computeResult.getFinalResult());
    }

}
