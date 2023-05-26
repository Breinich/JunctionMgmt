package main;

import main.junctionframework.JunctionFramework;
import main.world.Direction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Optimizer {

    /**
     * The possible routes for each direction, to avoid collisions
     */
    private static final ArrayList<ArrayList<Route>> possibleRoutes = new ArrayList<>() {{

        // RED>PURPLE
        add(new ArrayList<>() {{
            add(new Route(Direction.RED, Direction.PURPLE));
            add(new Route(Direction.PURPLE, Direction.ORANGE));
            add(new Route(Direction.ORANGE, Direction.BLUE));
            add(new Route(Direction.BLUE, Direction.RED));
        }});
        add(new ArrayList<>() {{
            add(new Route(Direction.RED, Direction.PURPLE));
            add(new Route(Direction.PURPLE, Direction.ORANGE));
            add(new Route(Direction.ORANGE, Direction.RED));
        }});
        add(new ArrayList<>() {{
            add(new Route(Direction.RED, Direction.PURPLE));
            add(new Route(Direction.PURPLE, Direction.BLUE));
            add(new Route(Direction.BLUE, Direction.RED));
        }});
        add(new ArrayList<>() {{
            add(new Route(Direction.RED, Direction.PURPLE));
            add(new Route(Direction.PURPLE, Direction.RED));
        }});
        //RED>ORANGE
        add(new ArrayList<>() {{
            add(new Route(Direction.RED, Direction.ORANGE));
            add(new Route(Direction.ORANGE, Direction.BLUE));
            add(new Route(Direction.BLUE, Direction.RED));
        }});
        add(new ArrayList<>() {{
            add(new Route(Direction.RED, Direction.ORANGE));
            add(new Route(Direction.ORANGE, Direction.RED));
        }});
        //RED>BLUE
        add(new ArrayList<>() {{
            add(new Route(Direction.RED, Direction.BLUE));
            add(new Route(Direction.BLUE, Direction.RED));
        }});
        //PURPLE>ORANGE
        add(new ArrayList<>() {{
            add(new Route(Direction.PURPLE, Direction.ORANGE));
            add(new Route(Direction.ORANGE, Direction.BLUE));
            add(new Route(Direction.BLUE, Direction.PURPLE));
        }});
        add(new ArrayList<>() {{
            add(new Route(Direction.PURPLE, Direction.ORANGE));
            add(new Route(Direction.ORANGE, Direction.PURPLE));
        }});
        //PURPLE>BLUE
        add(new ArrayList<>() {{
            add(new Route(Direction.PURPLE, Direction.BLUE));
            add(new Route(Direction.BLUE, Direction.PURPLE));
        }});
        //ORANGE>BLUE
        add(new ArrayList<>() {{
            add(new Route(Direction.ORANGE, Direction.BLUE));
            add(new Route(Direction.BLUE, Direction.ORANGE));
        }});
    }};

    /**
     * Returns all the possible routes, to maximize the gain
     *
     * @param bids the bids of the traffic lights
     * @return the routes with the max gain
     */
    public static List<Route> maxGain(Map<Direction, Env.Bid> bids) {

        // transform bids into a map of routes
        HashMap<Route, Env.Bid> newBidMap = new HashMap<>();

        for (Direction from : bids.keySet()) {
            if (JunctionFramework.getSimulator().getDestination(from) == null)
                continue;

            newBidMap.put(new Route(from, JunctionFramework.getSimulator().getDestination(from)), bids.get(from));
        }

        // find the route with the max gain
        Env.Bid maxGain = new Env.Bid(0, 0);
        int maxGainIndex = 0;
        int maxGainLength = 0;

        for (int i = 0; i < possibleRoutes.size(); i++) {

            Env.Bid gain = new Env.Bid(0, 0);
            for (Route r : possibleRoutes.get(i)) {
                if (newBidMap.containsKey(r))
                    gain = gain.add(newBidMap.get(r));
            }

            if (gain.weight() > maxGain.weight() ||
                    (gain.weight() == maxGain.weight() && gain.count() > maxGain.count())  ||
                    (gain.weight() == maxGain.weight() && gain.count() == maxGain.count() && possibleRoutes.get(i).size() > maxGainLength)) {
                maxGain = gain;
                maxGainIndex = i;
                maxGainLength = possibleRoutes.get(i).size();
            }
        }

        return possibleRoutes.get(maxGainIndex);
    }

    public record Route(Direction from, Direction to) {
    }
}
