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
        Scanner scanner = new Scanner(new File("RSAKeys"));
        scanner.nextLine();
        String publicKey=scanner.nextLine();

        System.out.println(publicKey);
        AgentController serverAgent = agentContainer.createNewAgent("server", "dev.elma.agents.Server", new Object[]{publicKey});
        serverAgent.start();

    }
}
