package testers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import project.RequestType;
import project.utils.RequestParser;

public class RequestParserTester {

    @Test
    public void quitRequestTest() {
        String quitRequestLine = "BYE";
        Assertions.assertSame(RequestType.QUIT_REQUEST, RequestParser.parseRequest(quitRequestLine));
    }

    @ParameterizedTest
    @ValueSource(strings = {"STAT_REQS", "STAT_AVG_TIME", "STAT_MAX_TIME"})
    public void statRequestTest(String statRequestLine) {
        Assertions.assertSame(RequestType.STAT_REQUEST, RequestParser.parseRequest(statRequestLine));
    }

    @ParameterizedTest
    @ValueSource(strings = {"MAX_GRID;x0:-1:0.1:1,x1:-10:1:20;((x0+(2.0^x1))/(1-x0));(x1*x0)",
            "MIN_LIST;x4:-22:2:10,y0:-10:1:1,h33:0:5:15;((x4*5)+h33)/y0;(y0+x4)+h33;((y0/h33)+1)/x4"})
    public void computationRequestTest(String computationRequestLine) {
        Assertions.assertSame(RequestType.COMPUTATION_REQUEST, RequestParser.parseRequest(computationRequestLine));
    }

    @ParameterizedTest
    @ValueSource(strings = {"BY", "STAT_REQ", "STATMAX", "MINLIST;x4:-22:2:10,y0:-10:1:1,h33:0:5:15;"})
    public void unknownRequestTest(String computationRequestLine) {
        Assertions.assertSame(RequestType.UNKNOWN_REQUEST, RequestParser.parseRequest(computationRequestLine));
    }


}
