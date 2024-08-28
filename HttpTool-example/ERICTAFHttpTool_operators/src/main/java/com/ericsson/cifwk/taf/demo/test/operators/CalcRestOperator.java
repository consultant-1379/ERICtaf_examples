package com.ericsson.cifwk.taf.demo.test.operators;

import com.ericsson.cifwk.taf.annotations.Operator;
import com.ericsson.cifwk.taf.data.DataHandler;
import com.ericsson.cifwk.taf.demo.test.data.CalcResponse;
import com.ericsson.cifwk.taf.tools.http.HttpResponse;
import com.ericsson.cifwk.taf.tools.http.HttpTool;
import com.ericsson.cifwk.taf.tools.http.HttpToolBuilder;
import com.ericsson.cifwk.taf.tools.http.constants.ContentType;
import com.google.gson.JsonParser;

@Operator
public class CalcRestOperator implements CalcOperator {

    /**
     * Call the Calc Rest Interface and return the result
     *
     * @param jsonRequest
     * @param operator
     *
     * @return {@link CalcResponse}
     * @throws Exception
     */
    public CalcResponse verifyCalc(String jsonRequest, String operator) throws Exception {
        HttpTool tool = HttpToolBuilder.newBuilder(DataHandler.getHostByName("testServer")).build();

        HttpResponse response = tool.request().contentType(ContentType.APPLICATION_JSON).header("Accept", "application/json").body(jsonRequest)
                .post(getUrl(operator));

        CalcResponse calcResponse = new CalcResponse();
        calcResponse.setResponseCode(response.getResponseCode().getCode());

        try {
            JsonParser parser = new JsonParser();
            calcResponse.setResult(Float.parseFloat(parser.parse(response.getBody()).getAsJsonObject().get("result").getAsString()));
        } catch (Exception e) {
        }
        return calcResponse;

    }

    public String getUrl(String operator) throws Exception {
        switch (operator.trim()) {
            case "+":
                return "/calculator/rest/add";
            case "-":
                return "/calculator/rest/subtract";
            case "/":
                return "/calculator/rest/divide";
            case "*":
                return "/calculator/rest/multiply";
            default:
                throw new Exception("Invalid Operation");
        }
    }
}
