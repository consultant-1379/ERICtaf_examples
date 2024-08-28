package com.ericsson.cifwk.taf.demo.test.operators;

import com.ericsson.cifwk.taf.demo.test.data.CalcResponse;

public interface CalcOperator {

    /**
     * Executes the Calc command and returns the result
     * @param xvalue
     * @param yvalue
     * @param operator
     * @return {@link CalcResponse}
     */
    CalcResponse verifyCalcCommand(String xvalue,String yvalue,String operator);


}
