package com.ericsson.cifwk.taf.demo.test.steps;

import static com.google.common.truth.Truth.assertThat;
import javax.inject.Inject;
import javax.inject.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ericsson.cifwk.taf.annotations.Input;
import com.ericsson.cifwk.taf.annotations.Output;
import com.ericsson.cifwk.taf.annotations.TestStep;
import com.ericsson.cifwk.taf.demo.test.data.CalcResponse;
import com.ericsson.cifwk.taf.demo.test.operators.CalcRestOperator;

/**
 * Created by eendjor on 23/05/2016.
 */
public class CalcRestTestStep {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalcRestTestStep.class);

    @Inject
    private Provider<CalcRestOperator> provider;

    /**
     * @DESCRIPTION Verify the functionality of the Calc REST interface
     */
    @TestStep(id = StepIds.VERIFY_CALC)
    public void verifyCalc(@Input(Parameters.JSON_REQUEST) String jsonrequest, @Input(Parameters.OP) String operator, @Output(Parameters.RESPONSE_CODE) int responseCode,
            @Output("Expected") float expected) throws Exception {

        CalcResponse calcResponse = provider.get().verifyCalc(jsonrequest, operator);

        assertThat(calcResponse.getResponseCode()).isEqualTo(responseCode);

        assertThat(calcResponse.getResult()).isWithin(expected);

    }

    public static class StepIds {

        public static final String VERIFY_CALC = "verifyCalc";

        private StepIds(){}
    }

    public static class Parameters {

        public static final String JSON_REQUEST = "Json Request";
        public static final String OP = "OP";
        public static final String RESPONSE_CODE = "ResponseCode";

        private Parameters(){}
    }
}
