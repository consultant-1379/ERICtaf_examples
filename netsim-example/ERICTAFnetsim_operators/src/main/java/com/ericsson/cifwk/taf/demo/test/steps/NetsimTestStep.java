package com.ericsson.cifwk.taf.demo.test.steps;

import static com.google.common.truth.Truth.assertThat;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ericsson.cifwk.taf.annotations.Input;
import com.ericsson.cifwk.taf.annotations.TestStep;
import com.ericsson.cifwk.taf.demo.test.operators.GenericOperator;
import com.ericsson.cifwk.taf.demo.test.operators.NetsimOperator;
import com.ericsson.cifwk.taf.handlers.netsim.commands.NetSimCommands;
import com.ericsson.cifwk.taf.handlers.netsim.domain.NetworkElement;
import com.ericsson.cifwk.taf.handlers.netsim.domain.Simulation;

/**
 * Created by eendjor on 09/06/2016.
 */
public class NetsimTestStep {

    private static final Logger LOGGER = LoggerFactory.getLogger(NetsimTestStep.class);

    @Inject
    Provider<NetsimOperator> provider;

    /**
     * Verify the ability of NetsimCommandHandler to list all the simulation present at Netsim
     */
    @TestStep(id = StepIds.VERIFY_LISTING_ALL_SIMULATION)
    public void verifyListingAllSimulation() {

        GenericOperator apiOperator = provider.get();
        LOGGER.info("Listing all the simulation present at Netsim");
        List<Simulation> simulations = apiOperator.getAllSimulations();
        assertThat(simulations).isNotEmpty();
    }

    /**
     * Verify the ability of NetsimCommandHandler to list all the NEs of a simulation
     */
    @TestStep(id = StepIds.VERIFY_LISTING_NES_OF_SIMULATION)
    public void verifyListingNEsOfSimulation(@Input("Simulation") String simulation) {

        GenericOperator apiOperator = provider.get();
        LOGGER.info("List NEs from the simlation");
        List<NetworkElement> nEs = apiOperator.getNEsFromSimulation(simulation);
        assertThat(nEs).isNotEmpty();
    }

    /**
     * Verify stop of a NE in a simulation
     */
    @TestStep(id = StepIds.VERIFY_STOP_OF_NE)
    public void verifyStopOfNE(@Input("NodeSimulation") String simulation, @Input("NE") String nE) {

        GenericOperator apiOperator = provider.get();
        LOGGER.info("Stop a NE from the simulation");
        boolean isStopped = apiOperator.stopNe(simulation, nE);
        assertThat(isStopped).isTrue();
    }

    /**
     * Verify start of a NE in a simulation
     */
    @TestStep(id = StepIds.VERIFY_START_OF_NE)
    public void verifyStartOfNE(@Input("NodeSimulation") String simulation, @Input("NE") String nE) {

        GenericOperator apiOperator = provider.get();
        LOGGER.info("Start a NE from the simulation");
        boolean isStarted = apiOperator.startNe(simulation, nE);
        assertThat(isStarted).isTrue();
    }

    /**
     * Verify execution of a command on NE
     */
    @TestStep(id = StepIds.VERIFY_COMMAND_ON_NE)
    public void verifyCommandOnNE(@Input("NodeSimulation") String simulation, @Input("NE") String nE) {

        GenericOperator apiOperator = provider.get();
        LOGGER.info("Executing a command on NE ");
        boolean isSuccess = apiOperator.executeCommandsOnNE(simulation, nE, NetSimCommands.start(), NetSimCommands.sendalarm());
        assertThat(isSuccess).isTrue();
    }

    /**
     * Verify execution of multiple commands on NE
     */
    @TestStep(id = StepIds.VERIFY_MULTIPLE_COMMAND_ON_NE)
    public void verifyMultipleCommandOnNE(@Input("NodeSimulation") String simulation, @Input("NE") String nE) {

        GenericOperator apiOperator = provider.get();
        LOGGER.info("Executing more than one commands on NE ");
        boolean isSuccess = apiOperator.executeCommandsOnNE(simulation, nE, NetSimCommands.start(), NetSimCommands.sendalarm());
        assertThat(isSuccess).isTrue();
    }

    /**
     * Verify execution of a command for all NE in a simulation
     */
    @TestStep(id = StepIds.VERIFY_COMMAND_ON_SIMULATION)
    public void verifyCommandOnSimulation(@Input("Simulation") String simulation) {

        GenericOperator apiOperator = provider.get();
        LOGGER.info("Executing a command for all the NE in a simulation");
        boolean isSuccess = apiOperator.executeCommandsOnSimulation(simulation, NetSimCommands.start());
        assertThat(isSuccess).isTrue();
    }

    /**
     * Verify execution of a multiple command for all NE in a simulation
     */
    @TestStep(id = StepIds.VERIFY_MULTIPLE_COMMAND_ON_SIMULATION)
    public void verifyMultipleCommandOnSimulation(@Input("Simulation") String simulation) {

        GenericOperator apiOperator = provider.get();
        LOGGER.info("Executing a multiple command for all the NE in a simulation");
        boolean isSuccess = apiOperator.executeCommandsOnSimulation(simulation, NetSimCommands.start(), NetSimCommands.stop());
        assertThat(isSuccess).isTrue();
    }

    public static class StepIds{

        public static final String VERIFY_LISTING_ALL_SIMULATION = "verifyListingAllSimulation";
        public static final String VERIFY_LISTING_NES_OF_SIMULATION = "verifyListingNEsOfSimulation";
        public static final String VERIFY_STOP_OF_NE = "verifyStopOfNE";
        public static final String VERIFY_START_OF_NE = "verifyStartOfNE";
        public static final String VERIFY_COMMAND_ON_NE = "verifyCommandOnNE";
        public static final String VERIFY_MULTIPLE_COMMAND_ON_NE = "verifyMultipleCommandOnNE";
        public static final String VERIFY_COMMAND_ON_SIMULATION = "verifyCommandOnSimulation";
        public static final String VERIFY_MULTIPLE_COMMAND_ON_SIMULATION = "verifyMultipleCommandOnSimulation";
    }
}
