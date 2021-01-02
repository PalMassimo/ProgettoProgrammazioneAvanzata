package project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class regexRequestTypeTest {

    @ParameterizedTest
    @ValueSource(strings = {"MAX_GRID", "MAX_LIST", "MIN_GRID", "MIN_LIST", "AVG_GRID", "AVG_LIST", "COUNT_GRID", "COUNT_LIST"})
    public void testComputationalRequestRegex(String computationRequest) {
        Assertions.assertTrue(computationRequest.matches("(MAX|MIN|AVG|COUNT)_(LIST|GRID)"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"MAx_GRID", "MMAX_LIST", "MIN_", "SUP_LIST_", "AVG"})
    public void testComputationalRequestRegex2(String computationRequest) {
        Assertions.assertFalse(computationRequest.matches("(MAX|MIN|AVG|COUNT)_(LIST|GRID)"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"STAT_REQS", "STAT_AVG_TIME", "STAT_MAX_TIME"})
    public void testStatRequestRegex(String computationRequest) {
        Assertions.assertTrue(computationRequest.matches("STAT_(REQS|AVG|MAX)(_TIME)?"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"STAT", "STAT_", "STAT_MAX__TIME", "STAT_REQSAVG_TIME"})
    public void testStatRequestRegex2(String computationRequest) {
        Assertions.assertFalse(computationRequest.matches("STAT_(REQS|AVG|MAX)(_TIME)?"));
    }





}
