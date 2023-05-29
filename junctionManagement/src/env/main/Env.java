// Environment code for project junctionManagement
package main;

import jason.NoValueException;
import jason.asSyntax.*;
import jason.environment.*;
import main.junctionframework.JunctionFramework;
import main.world.LightColor;
import main.world.Direction;

import java.util.*;
import java.util.logging.*;


/**
 * Custom environment for the agents
 */
public class Env extends Environment {

    /**
     * An agent's bid
     * @param weight the weight of the bid
     * @param count the number of vehicles waiting at the Bid's direction
     */
    public record Bid(int weight, int count) {
        public Bid add(Bid other){
            return new Bid(weight + other.weight, count + other.count);
        }
    }

    EnumMap<Direction, String> agentNames = new EnumMap<>(Direction.class);
    HashMap<String, Direction> agentDirections = new HashMap<>();

    private Logger logger = Logger.getLogger("junctionManagement."+Env.class.getName());
    private final Object syncObject = new Object();

    /**
     * Called before the MAS execution with the args informed in .mas2j
     */
    @Override
    public void init(String[] args) {
        super.init(args);

        agentNames.put(Direction.RED, "trafficLight1");
        agentDirections.put("trafficLight1", Direction.RED);
        addPercept("trafficLight1",Literal.parseLiteral("color").addTerms(new StringTermImpl("red")));
        agentNames.put(Direction.ORANGE, "trafficLight2");
        agentDirections.put("trafficLight2", Direction.ORANGE);
        addPercept("trafficLight2",Literal.parseLiteral("color").addTerms(new StringTermImpl("orange")));
        agentNames.put(Direction.BLUE, "trafficLight3");
        agentDirections.put("trafficLight3", Direction.BLUE);
        addPercept("trafficLight3",Literal.parseLiteral("color").addTerms(new StringTermImpl("blue")));
        agentNames.put(Direction.PURPLE, "trafficLight4");
        agentDirections.put("trafficLight4", Direction.PURPLE);
        addPercept("trafficLight4",Literal.parseLiteral("color").addTerms(new StringTermImpl("purple")));
        informAgsEnvironmentChanged();

        JunctionFramework.setEnvironment(this);

        JunctionFramework.start(syncObject);
    }

    /**
     * Action parser for the agents
     * @param agName The name of the agent
     * @param action The action that the agent wants to execute
     * @return True if the action was executed successfully, false otherwise
     */
    @Override
    public boolean executeAction(String agName, Structure action) {
        logger.info("executing: " + agName + " - " + action);
        if (action.getFunctor().equals("optimizeLamps")) {
            EnumMap<Direction, Bid> bids = new EnumMap<>(Direction.class);
            try {
                for (int i = 0; i < 4; i += 3){

                    int weight = (int)((NumberTerm)action.getTerm(i)).solve();
                    int count = (int)((NumberTerm)action.getTerm(i+1)).solve();
                    String agentName = action.getTerm(i+2).toString();

                    bids.put(agentDirections.get(agentName), new Bid(weight, count));
                }

                optimizeLamps(bids);

            } catch (NoValueException e) {
                e.printStackTrace();
            }
        }
        else
            return false;
        return true; // the action was executed with success
    }

    private void optimizeLamps(Map<Direction, Bid> bids) {
        setFutureLampColors(Optimizer.maxGain(bids));
    }

    /**
     * Sets the future colors of the lamps
     * @param greens A map of the lamps that will be green in the future
     */
    private void setFutureLampColors(List<Optimizer.Route> greens){
        synchronized (syncObject){
            for(Direction dir : Direction.values()){
                for (Direction other : Direction.values()){
                    if (dir != other){
                        if(greens.contains(new Optimizer.Route(dir, other)))
                            JunctionFramework.getSimulator().getTrafficLight(dir).changeFutureColor(other, LightColor.GREEN);
                        else
                            JunctionFramework.getSimulator().getTrafficLight(dir).changeFutureColor(other, LightColor.RED);
                    }
                }
            }
        }
    }

    /**
     *  Called before the end of MAS execution
     */
    @Override
    public void stop() {
        super.stop();

        JunctionFramework.getSimulator().stop();
    }

    /**
     * Notifies the agent that a new vehicle has arrived
     * @param direction The direction of the traffic light
     */
    public void notifyAgentNewBid(Direction direction, int weight, int count){
        addPercept(agentNames.get(direction),Literal.parseLiteral("newBid").addTerms(new NumberTermImpl(weight), new NumberTermImpl(count)));
        informAgsEnvironmentChanged(agentNames.get(direction));
    }


}
