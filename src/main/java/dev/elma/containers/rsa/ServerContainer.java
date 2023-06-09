package dev.elma.containers.rsa;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

import java.io.File;
import java.util.Scanner;

public class ServerContainer {
    public static void main(String[] args) throws Exception {
        Runtime instance = Runtime.instance();
        ProfileImpl profile=new ProfileImpl();
        profile.setParameter(Profile.MAIN_HOST,"localhost");
        AgentContainer agentContainer = instance.createAgentContainer(profile);
        String privateKey=new Scanner(new File("RSAKeys")).nextLine();

        //System.out.println(privateKey);
        AgentController serverAgent = agentContainer.createNewAgent("server", "dev.elma.agentsRSA.Server", new Object[]{privateKey});
        serverAgent.start();

    }
}
