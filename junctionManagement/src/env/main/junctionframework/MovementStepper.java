package main.junctionframework;

import main.world.Direction;
import main.world.Vehicle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class MovementStepper implements Runnable {

    private MainFrame frame;
    private int n;
    private final int longRouteLength = 170;
    private boolean stopped = false;

    public MovementStepper(MainFrame mF, int n){
        frame = mF;
        this.n = n;
    }

    @Override
    public void run() {
        List<Vehicle> movingVehicles = JunctionFramework.getSimulator().getActualMovingVehicles();
        if(movingVehicles != null && !movingVehicles.isEmpty()) {
            try {
                BufferedImage redCar = null;
                BufferedImage blueCar = null;
                BufferedImage orangeCar = null;
                BufferedImage purpleCar = null;
                try {
                    redCar = ImageIO.read(new File("src/env/main/junctionframework/images/carRed.png"));
                    blueCar = ImageIO.read(new File("src/env/main/junctionframework/images/carBlue.png"));
                    orangeCar = ImageIO.read(new File("src/env/main/junctionframework/images/carOrange.png"));
                    purpleCar = ImageIO.read(new File("src/env/main/junctionframework/images/carPurple.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < n && !stopped; i++) {
                    JJunction junction = frame.getJunctionPanel();
                    junction.emptyVehicles();
                    //JRoard .empty

                    for(Vehicle v : movingVehicles){
                        switch(v.getFrom()){
                            case RED -> {
                                switch(v.getDestionation()){
                                    case BLUE -> {
                                        Coordinate coord;
                                        if(i <= n/2) {
                                            coord = new Coordinate(259 - 50- i/(n/2)* longRouteLength, 39);
                                            junction.addVehicle(coord, redCar);
                                        }
                                        else {
                                            coord = new Coordinate(259 - 50 - longRouteLength, 39+(i-n/2)/(n/2) * longRouteLength);
                                            junction.addVehicle(coord, purpleCar);
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

                    List<Direction> directions = List.of(Direction.RED, Direction.BLUE, Direction.ORANGE, Direction.PURPLE);
                    for (Direction direction : directions) {
                        int x2 = 0;
                        int y2 = 0;
                        int x3 = 0;
                        int y3 = 0;
                        switch (direction){
                            case RED -> {
                                x2= 50-i*(50/n);
                                y2= 25;
                                x3 =100-i*(50/n);
                                y3 = 25;
                            }
                            case BLUE -> {
                                x2= 25;
                                y2= 0-i*(50/n);
                                x3 =25;
                                y3 = 50-i*(50/n);
                            }
                            case ORANGE -> {
                                x2= (117-50)+i*(50/n);
                                y2= 25;
                                x3 =(117-2*50)+i*(50/n);
                                y3 = 25;

                            }
                            case PURPLE -> {
                                y2 = (138-50)+i*(50/n);
                                x2 = 25;
                                y3 = (88-50)+i*(50/n);
                                x3 = 25;
                            }
                        }
                        int numberOfVehicles = JunctionFramework.getSimulator().getTrafficLight(direction).getWaitingVehiclesCount();
                        if(numberOfVehicles ==2 ) {
                                frame.getJRoad(direction).removeAllCoordinate();
                                frame.getJRoad(direction).addNewCoordinate(new Coordinate(x2, y2));
                        } else if(numberOfVehicles>2){
                                frame.getJRoad(direction).removeAllCoordinate();
                                frame.getJRoad(direction).addNewCoordinate(new Coordinate(x2, y2));
                                frame.getJRoad(direction).addNewCoordinate(new Coordinate(x3, y3));

                        } else if (numberOfVehicles>3){
                            frame.calculateRoadCoordinates(frame.getJRoad(direction), direction);;
                        }
                    }



                    frame.repaint();
                    frame.revalidate();
                    Thread.sleep(frame.getSpeed()/Math.round(1.5*n));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        stopped = true;
    }
}
