package dev.elma.containers;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class SimpleContainer {
    public static void main(String[] args) throws Exception {
        Runtime instance = Runtime.instance();
        ProfileImpl profile=new ProfileImpl();
        profile.setParameter(Profile.MAIN_HOST,"localhost");
        AgentContainer agentContainer = instance.createAgentContainer(profile);
        AgentController newAgent = agentContainer.createNewAgent("client","dev.elma.agents.Client",new Object[]{});
        newAgent.start();

    }
}
