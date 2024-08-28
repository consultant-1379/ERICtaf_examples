package com.ericsson.cifwk.taf.demo.test.operators;

import com.ericsson.cifwk.taf.demo.test.data.CalcResponse;

public interface CalcOperator {

    /**
     * Call the Calc Rest Interface and return the result
     * @param jsonRequest
     * @param operator
     * @return {@link CalcResponse}
     * @throws Exception
     */
    CalcResponse verifyCalc(String jsonRequest,String operator) throws Exception;
}


