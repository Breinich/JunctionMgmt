// Environment code for project junctionManagement
package main;

import jason.asSyntax.*;
import jason.environment.*;
import main.junctionframework.JunctionFramework;
import main.world.Direction;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.logging.*;



public class Env extends Environment {

    EnumMap<Direction, String> agentNames = new EnumMap<>(Direction.class);

    private Logger logger = Logger.getLogger("junctionManagement."+Env.class.getName());
    private Object syncObject;

    /** Called before the MAS execution with the args informed in .mas2j */
    @Override
    public void init(String[] args) {
        super.init(args);

        agentNames.put(Direction.RED, "trafficLight1");
        agentNames.put(Direction.ORANGE, "trafficLight2");
        agentNames.put(Direction.BLUE, "trafficLight3");
        agentNames.put(Direction.PURPLE, "trafficLight4");

        JunctionFramework.setEnvironment(this);

        syncObject = new Object();

        JunctionFramework.start(syncObject);
    }

    @Override
    public boolean executeAction(String agName, Structure action) {
        logger.info("executing: " + agName + " - " + action);
        if (true) {
             informAgsEnvironmentChanged();
        }
        return true; // the action was executed with success
    }

    /** Called before the end of MAS execution */
    @Override
    public void stop() {
        super.stop();

        JunctionFramework.getSimulator().stop();
    }

    public void notifyAgentNewVehicle(Direction direction){
        addPercept(agentNames.get(direction),Literal.parseLiteral("newVehicle(1)"));
    }

    public void notifyAgentVehicleLeft(Direction direction, int weight) {
        addPercept(agentNames.get(direction),Literal.parseLiteral("vehicleLeft("+weight+")"));
    }

    private void setFutureLampColors(){
        synchronized (syncObject){

        }
    }
}
