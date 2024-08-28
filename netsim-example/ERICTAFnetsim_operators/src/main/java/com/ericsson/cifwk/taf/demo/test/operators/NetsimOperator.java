package com.ericsson.cifwk.taf.demo.test.operators;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.cifwk.taf.annotations.Operator;
import com.ericsson.cifwk.taf.data.DataHandler;
import com.ericsson.cifwk.taf.data.Host;
import com.ericsson.cifwk.taf.handlers.netsim.CommandOutput;
import com.ericsson.cifwk.taf.handlers.netsim.NetSimCommand;
import com.ericsson.cifwk.taf.handlers.netsim.NetSimCommandHandler;
import com.ericsson.cifwk.taf.handlers.netsim.NetSimResult;
import com.ericsson.cifwk.taf.handlers.netsim.domain.NeGroup;
import com.ericsson.cifwk.taf.handlers.netsim.domain.NetworkElement;
import com.ericsson.cifwk.taf.handlers.netsim.domain.Simulation;
import com.ericsson.cifwk.taf.handlers.netsim.domain.SimulationGroup;

@Operator
public class NetsimOperator implements GenericOperator {

    private static final Logger log = LoggerFactory.getLogger(NetsimOperator.class);

    private NetSimCommandHandler service;

    public NetsimOperator() {
        Host host = DataHandler.getHostByName("netsim");
        initializeNetsimHandler(host);

    }

    public void initializeNetsimHandler(Host host) {
        service = NetSimCommandHandler.getInstance(host);
    }

    @Override
    public List<Simulation> getAllSimulations() {
        SimulationGroup simulationGroup = service.getAllSimulations();
        List<Simulation> simulations = simulationGroup.getSimulations();

        for (Simulation simulation : simulations) {
            log.info(simulation.getName());
        }
        return simulations;
    }

    @Override
    public List<NetworkElement> getNEsFromSimulation(String simulation) {
        NeGroup neGroup = service.getSimulationNEs(simulation);
        List<NetworkElement> networkElements = neGroup.getNetworkElements();
        for (NetworkElement networkElement : networkElements) {
            log.info(networkElement.toString());
        }
        return networkElements;
    }

    @Override
    public boolean startNe(String simulation, String neName) {
        NeGroup neGroup = service.getSimulationNEs(simulation);
        NetworkElement ne = neGroup.get(neName);
        boolean isStarted = ne.start();
        return isStarted;
    }

    @Override
    public boolean stopNe(String simulation, String neName) {
        NeGroup neGroup = service.getSimulationNEs(simulation);
        NetworkElement ne = neGroup.get(neName);
        boolean isStopped = ne.stop();
        return isStopped;
    }

    @Override
    public boolean executeCommandsOnNE(String simulation, String neName, NetSimCommand ... netSimCommands) {
        boolean isSuccess = false;
        NeGroup neGroup = service.getSimulationNEs(simulation);
        NetworkElement ne = neGroup.get(neName);
        NetSimResult netSimResult = ne.exec(netSimCommands);
        CommandOutput[] commandOutputs = netSimResult.getOutput();
        for (CommandOutput commandOutput : commandOutputs) {
            log.info(netSimResult.getRawOutput());
            isSuccess = commandOutput.getRawOutput().contains("OK") || commandOutput.getRawOutput().contains("EXECUTED") || commandOutput.getRawOutput().contains("1") || commandOutput.getRawOutput().contains("2");
        }
        return isSuccess;
    }

    @Override
    public boolean executeCommandsOnSimulation(String simulation, NetSimCommand ... netSimCommands) {
        boolean isSuccess = false;
        NeGroup neGroup = service.getSimulationNEs(simulation);
        Map<NetworkElement, NetSimResult> results = neGroup.exec(netSimCommands);

        for (Iterator<NetworkElement> iterator = neGroup.iterator(); iterator.hasNext();) {
            NetworkElement networkElement = (NetworkElement) iterator.next();
            log.info(results.get(networkElement).toString());
            if(results.get(networkElement).getOutput().length > 1)
                isSuccess = results.get(networkElement).getOutput()[0].toString().contains("OK") && results.get(networkElement).getOutput()[1].toString().contains("OK");
            else if(results.get(networkElement).getOutput().length > 0)
                isSuccess = results.get(networkElement).getOutput()[0].toString().contains("END_OF_COMMAND_BATCH");
        }
        return isSuccess;
    }

}
