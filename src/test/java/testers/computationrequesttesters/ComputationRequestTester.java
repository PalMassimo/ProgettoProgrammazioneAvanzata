package testers.computationrequesttesters;

import org.junit.jupiter.api.Test;
import project.computation.ComputationRequest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComputationRequestTester {

    @Test
    public void test1(){
        ComputationRequest computationRequest = new ComputationRequest("MAX_GRID;x0:-1:0.1:1,x1:-10:1:20;((x0+(2.0^x1))/(1-x0));(x1*x0)");
        Set<String> expressions = new HashSet<>();
        expressions.add("((x0+(2.0^x1))/(1-x0))");
        expressions.add("(x1*x0)");
        assertEquals(computationRequest.getComputationKind(), "MAX");
        assertEquals(computationRequest.getValuesKind(), "GRID");
        assertEquals(computationRequest.getVariableValuesFunction(), "x0:-1:0.1:1,x1:-10:1:20");
        assertEquals(computationRequest.getExpressions(), expressions);
    }

    @Test
    public void test2(){
        ComputationRequest computationRequest = new ComputationRequest("AVG_GRID;y:-11:0.1:7,a:-10:5:10;(y/(11.1-a);(a/y)");
        Set<String> expressions = new HashSet<>();
        expressions.add("(y/(11.1-a)");
        expressions.add("(a/y)");
        assertEquals(computationRequest.getComputationKind(), "AVG");
        assertEquals(computationRequest.getValuesKind(), "GRID");
        assertEquals(computationRequest.getVariableValuesFunction(), "y:-11:0.1:7,a:-10:5:10");
        assertEquals(computationRequest.getExpressions(), expressions);
    }

}
