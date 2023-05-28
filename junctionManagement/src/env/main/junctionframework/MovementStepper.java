package main.junctionframework;

import main.world.Direction;
import main.world.Vehicle;

import java.util.List;

public class MovementStepper implements Runnable {

    private MainFrame frame;
    private int n;
    private final int horizontalLongRouteLength = 170;

    public MovementStepper(MainFrame mF, int n){
        frame = mF;
        this.n = n;
    }

    @Override
    public void run() {
        List<Vehicle> movingVehicles = JunctionFramework.getSimulator().getActualMovingVehicles();
        if(movingVehicles != null && !movingVehicles.isEmpty()) {
            try {
                for (int i = 0; i < n; i++) {
                    frame.getJunctionPanel().emptyVehicles();
                    //JRoard .empty

                    for(Vehicle v : movingVehicles){
                        switch(v.getFrom()){
                            case RED -> {
                                switch(v.getDestionation()){
                                    case BLUE -> {
                                        Coordinate coord;
                                        if(i <= n/2) {
                                            coord = new Coordinate(259 - 50- i/(n/2)*170, 25);
                                        }
                                        else {

                                        }
                                    }
                                    case ORANGE -> {

                                    }
                                    case PURPLE -> {

                                    }
                                }
                            }
                            case BLUE -> {
                                switch(v.getDestionation()){
                                    case RED -> {

                                    }
                                    case ORANGE -> {

                                    }
                                    case PURPLE -> {

                                    }
                                }
                            }
                            case ORANGE -> {
                                switch(v.getDestionation()){
                                    case BLUE -> {

                                    }
                                    case RED -> {

                                    }
                                    case PURPLE -> {

                                    }
                                }
                            }
                            case PURPLE -> {
                                switch(v.getDestionation()){
                                    case BLUE -> {

                                    }
                                    case ORANGE -> {

                                    }
                                    case RED -> {

                                    }
                                }
                            }
                        }
                    }


                    //

                    /*
                    List<Direction> directions = List.of(Direction.RED, Direction.BLUE, Direction.ORANGE, Direction.PURPLE);
                    for (Direction direction : directions) {
                        int add = 0;
                        switch (direction){
                            case RED -> add = -i*(50/n);
                            case BLUE -> add = 1;
                            case ORANGE -> add = 2;
                            case PURPLE -> add = 3;
                        }
                        int numberOfVehicles = JunctionFramework.getSimulator().getTrafficLight(direction).getWaitingVehiclesCount();
                        if(numberOfVehicles ==2 ) {
                                frame.getJRoad(direction).removeAllCoordinate();
                                frame.getJRoad(direction).addNewCoordinate(new Coordinate(50-i*(50/n), 25));
                        } else if(numberOfVehicles>2){
                                frame.getJRoad(direction).removeAllCoordinate();
                                frame.getJRoad(direction).addNewCoordinate(new Coordinate(100-i*(50/n), 25));
                                frame.getJRoad(direction).addNewCoordinate(new Coordinate(50-i*(50/n), 25));

                        } else if (numberOfVehicles>3){
                            frame.getJRoad(direction).addNewCoordinate(new Coordinate(100, 25));
                        }
                    }*/



                    frame.repaint();
                    frame.revalidate();
                    Thread.sleep(frame.getSpeed()/(n));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
