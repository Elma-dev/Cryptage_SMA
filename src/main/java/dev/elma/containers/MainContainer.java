package dev.elma.containers;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;

public class MainContainer {
    public static void main(String[] args) throws Exception {
        Runtime instance = Runtime.instance();

        ProfileImpl profile=new ProfileImpl();
        profile.setParameter(Profile.GUI,"true");

        AgentContainer mainContainer = instance.createMainContainer(profile);
        mainContainer.start();

    }
}
