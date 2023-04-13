package dev.elma.agents;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class Server extends Agent {
    @Override
    protected void setup() {
        String secretKey=(String) getArguments()[0];
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage receiveMessage = receive();
                if(receiveMessage!=null){
                    String content = receiveMessage.getContent();
                }
                else
                    block();
            }
        });
    }
}
