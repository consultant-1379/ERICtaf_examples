package com.ericsson.cifwk.taf.demo.test.steps;

import static com.google.common.truth.Truth.assertThat;
import javax.inject.Inject;
import javax.inject.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ericsson.cifwk.taf.annotations.Input;
import com.ericsson.cifwk.taf.annotations.Output;
import com.ericsson.cifwk.taf.annotations.TestStep;
import com.ericsson.cifwk.taf.data.DataHandler;
import com.ericsson.cifwk.taf.demo.test.data.CalcResponse;
import com.ericsson.cifwk.taf.demo.test.operators.CalcCLIOperator;
import com.ericsson.cifwk.taf.demo.test.operators.CalcOperator;
import com.google.common.base.Strings;

/**
 * Created by eendjor on 23/05/2016.
 */
public class CalcCLITestSteps {

    private static Logger LOGGER = LoggerFactory.getLogger(CalcCLITestSteps.class);

    @Inject
    private Provider<CalcCLIOperator> provider;

    /**
     * @DESCRIPTION Verify the functionality of the Calc Command
     * @PRE Get the calc version to be tested
     */
    @TestStep(id = StepIds.VERIFY_CALC_COMMAND)
    public void verifyCalcCommand(
            @Input(Parameters.TEST_CASE_ID) String testCaseId,
            @Input(Parameters.X) String xValue,
            @Input(Parameters.Y) String yValue,
            @Input(Parameters.OP) String operator,
            @Output(Parameters.OPDESC) String opDesc,
            @Output(Parameters.EXIT_CODE) int exitCode,
            @Output(Parameters.EXPECTED) String expected
    ) {

        LOGGER.info(testCaseId + "- Verify calculator by passing the different X,Y,OP values");

        final String calcVersion = DataHandler.getAttribute("CALCVER").toString();

        CalcOperator calcOperator = provider.get();
        CalcResponse calcResponse = calcOperator.verifyCalcCommand(xValue, yValue, operator);

        LOGGER.info("Verifying Version");
        assertThat(calcResponse.getVersionDescription()).contains(calcVersion);

        LOGGER.info("Verifying the exit code of the Command");
        assertThat(calcResponse.getExitCode()).isEqualTo(exitCode);

        if (Strings.isNullOrEmpty(expected)) {
            LOGGER.debug("Expected Result is empty - does not verify anything");
        } else {
            LOGGER.info("Verifying Expected Result");
            assertThat(calcResponse.getResult()).contains(expected);
        }

        if (0 == calcResponse.getExitCode()) {
            LOGGER.info("Verifying Operator Description");
            assertThat(calcResponse.getOperatorDescription()).contains(opDesc);
        } else {
            LOGGER.debug("Skip validation of \'Operator Description\' due to error exit code ({}) returned by script", calcResponse.getExitCode());
        }
    }

    public static class StepIds{
        public static final String VERIFY_CALC_COMMAND = "Verify Calc Command";

        private StepIds(){}
    }

    public static class Parameters{
        public static final String TEST_CASE_ID = "testCaseId";
        public static final String X = "X";
        public static final String Y = "Y";
        public static final String OP = "OP";
        public static final String OPDESC = "OPDesc";
        public static final String EXIT_CODE = "ExitCode";
        public static final String EXPECTED = "Expected";

        private Parameters(){}

    }
}
