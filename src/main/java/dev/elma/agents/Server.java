package dev.elma.agents;

import jade.core.Agent;

public class Server extends Agent {
    @Override
    protected void setup() {
        String secretKey=(String) getArguments()[0];
    }
}
