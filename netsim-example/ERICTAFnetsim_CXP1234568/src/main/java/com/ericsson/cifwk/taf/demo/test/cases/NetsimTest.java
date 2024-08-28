package com.ericsson.cifwk.taf.demo.test.cases;

import static com.ericsson.cifwk.taf.scenario.TestScenarios.dataSource;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.runner;
import static com.ericsson.cifwk.taf.scenario.TestScenarios.scenario;
import javax.inject.Inject;
import org.testng.annotations.Test;
import com.ericsson.cifwk.taf.TafTestBase;
import com.ericsson.cifwk.taf.annotations.TestId;
import com.ericsson.cifwk.taf.demo.test.flows.NetsimFlows;
import com.ericsson.cifwk.taf.scenario.TestScenario;
import com.ericsson.cifwk.taf.scenario.TestScenarioRunner;
import com.ericsson.cifwk.taf.scenario.impl.LoggingScenarioListener;

/**
 * Created by eendjor on 09/06/2016.
 */
public class NetsimTest extends TafTestBase {

    public static final String NETSIM_EXAMPLES = "netsimExamples";
    public static final String NETSIM_SCENARIO = "netsimScenario";
    public static final String NODE_DATA = "NodeData";
    public static final String SIMULATIONS = "Simulations";

    @Inject
    private NetsimFlows netsimFlows;

    @TestId(id = NETSIM_EXAMPLES)
    @Test
    public void netsimExamples() {

        TestScenario scenario = scenario(NETSIM_SCENARIO)
                .addFlow(netsimFlows.netsimFlow(dataSource(NODE_DATA), dataSource(SIMULATIONS)))
                .build();

        TestScenarioRunner runner = runner().withListener(new LoggingScenarioListener()).build();

        runner.start(scenario);
    }
}
