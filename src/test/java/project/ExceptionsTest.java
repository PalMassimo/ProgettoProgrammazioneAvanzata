package project;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import project.exceptions.WrongNumberOfArguments;
import project.utils.RequestParser;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExceptionsTest {

    @ParameterizedTest
    @ValueSource(strings = {"MAX_GRID;x0:0.1:1,x1:-10:1:20;((x0+(2.0^x1))/(1-x0));(x1*x0)",
            "MIN_LIST;x0:-1:0.1:1,x1:1:-1:20;"})
    public void testWrongNumberOfArguments(String badComputationRequest) {
        assertThrows(WrongNumberOfArguments.class, () -> RequestParser.parseRequest(badComputationRequest));
    }

    @ParameterizedTest
    @ValueSource(strings = {"COUNT_GRID;è:0:0.1:1,x1:-10:1:20;(x1*x0)", "AVG_GRID;x0:-1:-0.1:1;x0", "COUNT_LIST;x0:-1:-0.1:-100;x0"})
    public void testIllegalArgumentException(String badComputationRequest) {
        assertThrows(IllegalArgumentException.class, () -> RequestParser.parseRequest(badComputationRequest));
    }

}
