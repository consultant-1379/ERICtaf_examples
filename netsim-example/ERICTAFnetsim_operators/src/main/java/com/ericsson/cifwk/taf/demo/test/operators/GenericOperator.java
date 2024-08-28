package com.ericsson.cifwk.taf.demo.test.operators;

import java.util.List;
import com.ericsson.cifwk.taf.handlers.netsim.NetSimCommand;
import com.ericsson.cifwk.taf.handlers.netsim.domain.NetworkElement;
import com.ericsson.cifwk.taf.handlers.netsim.domain.Simulation;

public interface GenericOperator {
    /**
     * Get all the simulation present ot Netsim
     *
     * @return
     */
    List<Simulation> getAllSimulations();

    /**
     * Lists all the NEs present in a simulation
     *
     * @param simulation
     * @return
     */
    List<NetworkElement> getNEsFromSimulation(String simulation);

    /**
     * Starts a NE from the simulation
     *
     * @param simulation
     * @param neName
     * @return
     */
    boolean startNe(String simulation, String neName);

    /**
     * Stops a NE from the simulation
     *
     * @param simulation
     * @param neName
     * @return
     */
    boolean stopNe(String simulation, String neName);

    /**
     * Execute multiple netsim commands on a NE of the simulation
     *
     * @param simulation
     * @param neName
     * @param netSimCommands
     * @return
     */
    boolean executeCommandsOnNE(String simulation, String neName,NetSimCommand ... netSimCommands);

    /**
     * Execute multiple netsim commands on all NEs in a simulation
     *
     * @param simulation
     * @param netSimCommands
     * @return
     */
    boolean executeCommandsOnSimulation(String simulation,NetSimCommand ... netSimCommands);

}
