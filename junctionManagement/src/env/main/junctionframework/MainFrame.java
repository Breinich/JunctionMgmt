package main.junctionframework;

import main.world.Direction;
import main.world.LightColor;
import main.world.Vehicle;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class MainFrame extends JFrame {

	public MainFrame() {
		super("Junction Management with Intelligent Traffic Lights");
		
		initComponents();
	}

    private JRoad getJRoad (Direction dir){
        return switch(dir){
            case RED -> redRoad;
            case BLUE -> blueRoad;
            case ORANGE -> orangeRoad;
            case PURPLE -> purpleRoad;
        };
    }

    public Coordinate getCoordinateOfCar(Direction direction, int idxInWaitList)
    {
        int x = 0;
        int y = 0;

        switch (direction){
            case RED -> {
                x = 0 + idxInWaitList * 50;
                y = 25;
            }
            case BLUE -> {
                x = 25;
                y = 0 + idxInWaitList * 50;
            }
            case ORANGE -> {
                x = 117 - idxInWaitList * 50;
                y = 25;
            }
            case PURPLE -> {
                x = 25;
                y = 138 - idxInWaitList * 50-50;
            }
        }

        return new Coordinate(x, y);
    }

    private void calculateRoadCoordinates(JRoad road, Direction direction){
        int numberOfVehicles = JunctionFramework.getSimulator().getTrafficLight(direction).getWaitingVehiclesCount();
        road.removeAllCoordinate();

        for(int i = 0; i < numberOfVehicles; i++){
            road.addNewCoordinate(getCoordinateOfCar(direction, i));
        }
        revalidate();
        repaint();
    }

    private JLight getJLight(Direction from, Direction to){
        return switch(from){
            case RED -> switch(to){
                case BLUE -> redLampBlueLight;
                case ORANGE -> redLampOrangeLight;
                case PURPLE -> redLampPurpleLight;
                default -> null;
            };
            case BLUE -> switch(to){
                case RED -> blueLampRedLight;
                case ORANGE -> blueLampOrangeLight;
                case PURPLE -> blueLampPurpleLight;
                default -> null;
            };
            case ORANGE -> switch(to){
                case RED -> orangeLampRedLight;
                case BLUE -> orangeLampBlueLight;
                case PURPLE -> orangeLampPurpleLight;
                default -> null;
            };
            case PURPLE -> switch(to){
                case RED -> purpleLampRedLight;
                case BLUE -> purpleLampBlueLight;
                case ORANGE -> purpleLampOrangeLight;
                default -> null;
            };
        };
    }

    private BufferedImage loadLight(Direction from, Direction to, LightColor color){
        switch(color) {
            case RED -> {
                return lightImages.get(LightDirection.RED);
            }
            case YELLOW -> {
                return lightImages.get(LightDirection.YELLOW);
            }
            case REDYELLOW -> {
                return lightImages.get(LightDirection.REDYELLOW);
            }
            case GREEN -> {
                switch (from){
                    case RED -> {
                        return switch (to){
                            case BLUE -> lightImages.get(LightDirection.GREENDOWN);
                            case ORANGE -> lightImages.get(LightDirection.GREENLEFT);
                            case PURPLE -> lightImages.get(LightDirection.GREENUP);
                            case RED -> lightImages.get(LightDirection.BLACK);
                        };
                    }
                    case BLUE -> {
                        return switch (to){
                            case RED -> lightImages.get(LightDirection.GREENRIGHT);
                            case ORANGE -> lightImages.get(LightDirection.GREENLEFT);
                            case PURPLE -> lightImages.get(LightDirection.GREENUP);
                            case BLUE -> lightImages.get(LightDirection.BLACK);
                        };
                    }
                    case ORANGE -> {
                        return switch (to){
                            case RED -> lightImages.get(LightDirection.GREENRIGHT);
                            case BLUE -> lightImages.get(LightDirection.GREENDOWN);
                            case PURPLE -> lightImages.get(LightDirection.GREENUP);
                            case ORANGE -> lightImages.get(LightDirection.BLACK);
                        };
                    }
                    case PURPLE -> {
                        return switch (to){
                            case RED -> lightImages.get(LightDirection.GREENRIGHT);
                            case BLUE -> lightImages.get(LightDirection.GREENDOWN);
                            case ORANGE -> lightImages.get(LightDirection.GREENLEFT);
                            case PURPLE -> lightImages.get(LightDirection.BLACK);
                        };
                    }
                }
            }
        }
        return lightImages.get(LightDirection.BLACK);
    }

    public void refresh() {
        //update lamps
        for(Direction from : Direction.values()){
            for(Direction to : Direction.values()){
                if(from != to){
                    getJLight(from, to).setColor(loadLight(from, to, JunctionFramework.getSimulator().getTrafficLight(from).getColor(to)));
                }
            }
        }


		//calculate the values
        redCarSumValueLabel.setText(JunctionFramework.getSimulator().getCarSumByDirection(Direction.RED).toString());
        redDelayValueLabel.setText(String.format("%.2f",JunctionFramework.getSimulator().getAvgWaitingByDirection(Direction.RED)));
        blueCarSumValueLabel.setText(JunctionFramework.getSimulator().getCarSumByDirection(Direction.BLUE).toString());
        blueDelayValueLabel.setText(String.format("%.2f",JunctionFramework.getSimulator().getAvgWaitingByDirection(Direction.BLUE)));
        orangeCarSumValueLabel.setText(JunctionFramework.getSimulator().getCarSumByDirection(Direction.ORANGE).toString());
        orangeDelayValueLabel.setText(String.format("%.2f",JunctionFramework.getSimulator().getAvgWaitingByDirection(Direction.ORANGE)));
        purpleCarSumValueLabel.setText(JunctionFramework.getSimulator().getCarSumByDirection(Direction.PURPLE).toString());
        purpleDelayValueLabel.setText(String.format("%.2f",JunctionFramework.getSimulator().getAvgWaitingByDirection(Direction.PURPLE)));

        elapsedTimeValueLabel.setText(JunctionFramework.getSimulator().getTime().toString());

        //calculate the coordinates
        calculateRoadCoordinates(redRoad, Direction.RED);
        calculateRoadCoordinates(blueRoad, Direction.BLUE);
        calculateRoadCoordinates(orangeRoad, Direction.ORANGE);
        calculateRoadCoordinates(purpleRoad, Direction.PURPLE);

        this.revalidate();
        this.repaint();

        // animation resolution
        animate(50);
	}

    public int getSpeed() {
        return 4000-speedSlider.getValue();
    }

    private void redNewVehicleButtonActionPerformed(java.awt.event.ActionEvent evt) {
		JunctionFramework.getSimulator().addVehicle(Direction.RED);
        calculateRoadCoordinates(redRoad, Direction.RED);
    }                                                   

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        JunctionFramework.getSimulator().stop();
        stop();
    }                                          

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {
        stopped = false;
        JunctionFramework.getSimulator().start();
    }                                           

    private void purpleNewVehicleButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                       
        JunctionFramework.getSimulator().addVehicle(Direction.PURPLE);
        calculateRoadCoordinates(purpleRoad, Direction.PURPLE);
    }                                                      

    private void orangeNewVehicleButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                       
        JunctionFramework.getSimulator().addVehicle(Direction.ORANGE);
        calculateRoadCoordinates(orangeRoad, Direction.ORANGE);
    }                                                      

    private void blueNewVehicleButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                     
        JunctionFramework.getSimulator().addVehicle(Direction.BLUE);
        calculateRoadCoordinates(blueRoad, Direction.BLUE);
    }                                                    

    private void speedSliderStateChanged() {
        JunctionFramework.getSimulator().setSpeed(4000-speedSlider.getValue());
    }

    private void greenDurationSliderStateChanged() {
        JunctionFramework.getSimulator().setGreenDuration(greenDurationSlider.getValue());
    }


    private boolean stopped = false;


    public void animate(int n) {
        List<Vehicle> movingVehicles = JunctionFramework.getSimulator().getActualMovingVehicles();
        if(movingVehicles != null && !movingVehicles.isEmpty()) {

            try {
                int longRouteLength = 170;
                int shortRouteLength = 40;
                int maxLength = 259;
                int vehicleLength = 50;

                for (int i = 0; i < n && !stopped; i++) {

                    junctionPanel.emptyVehicles();

                    Coordinate coord;

                    List<Direction> activeDirections = new ArrayList<Direction>();

                    for(Vehicle v : movingVehicles){
                        switch(v.getFrom()){
                            case RED -> {
                                if(!activeDirections.contains(Direction.RED)) {
                                    activeDirections.add(Direction.RED);
                                }
                                switch(v.getDestionation()){
                                    case BLUE -> {

                                        if(i <= n/2) {
                                            coord = new Coordinate(Math.round(210 - i/((n-1)/2.0f)* 170), 40);
                                            junctionPanel.addVehicle(coord, redCar);
                                        }
                                        else {
                                            coord = new Coordinate(40, Math.round(40 + (i - (n - 1) / 2.0f) / ((n - 1) / 2.0f) * 170));
                                            junctionPanel.addVehicle(coord, purpleCar);
                                        }
                                    }
                                    case ORANGE -> {
                                        coord = new Coordinate(Math.round(210 - i / (n - 1.0f) * 210), 40);
                                        junctionPanel.addVehicle(coord, redCar);
                                    }
                                    case PURPLE -> {
                                        if(i <= n/2) {
                                            coord = new Coordinate(Math.round(210 - i / ((n - 1) / 2.0f) * 40), 40);
                                            junctionPanel.addVehicle(coord, redCar);
                                        }
                                        else {
                                            coord = new Coordinate(170, Math.round(40 - (i - (n - 1) / 2.0f) / ((n - 1) / 2.0f) * 40));
                                            junctionPanel.addVehicle(coord, blueCar);
                                        }
                                    }
                                }
                            }
                            case BLUE -> {
                                if(!activeDirections.contains(Direction.BLUE)) {
                                    activeDirections.add(Direction.BLUE);
                                }
                                switch(v.getDestionation()){
                                    case RED -> {
                                        if(i <= n/2) {
                                            coord = new Coordinate(170, Math.round(210 - (i / ((n - 1) / 2.0f) * 40)));
                                            junctionPanel.addVehicle(coord, blueCar);
                                        }
                                        else {
                                            coord = new Coordinate(Math.round(170 + (i - ((n - 1) / 2.0f)) / ((n - 1) / 2.0f) * 40), 170);
                                            junctionPanel.addVehicle(coord, orangeCar);
                                        }
                                    }
                                    case ORANGE -> {
                                        if(i <= n/2) {
                                            coord = new Coordinate(170, Math.round(210 - (i / ((n - 1) / 2.0f) * 170)));
                                            junctionPanel.addVehicle(coord, blueCar);
                                        }
                                        else {
                                            coord = new Coordinate(Math.round(170 - (i - ((n - 1) / 2.0f)) / ((n - 1) / 2.0f) * 170), 40);
                                            junctionPanel.addVehicle(coord, redCar);
                                        }
                                    }
                                    case PURPLE -> {
                                        coord = new Coordinate(170, Math.round(210 - (i / (n - 1.0f)) * 210));
                                        junctionPanel.addVehicle(coord, blueCar);
                                    }
                                }
                            }
                            case ORANGE -> {
                                if(!activeDirections.contains(Direction.ORANGE)) {
                                    activeDirections.add(Direction.ORANGE);
                                }
                                switch(v.getDestionation()){
                                    case BLUE -> {
                                        if(i <= n/2) {
                                            coord = new Coordinate(Math.round(i / ((n - 1) / 2.0f) * 40), 170);
                                            junctionPanel.addVehicle(coord, orangeCar);
                                        }
                                        else {
                                            coord = new Coordinate(40, Math.round(170 + (i - ((n - 1) / 2.0f)) / ((n - 1) / 2.0f) * 40));
                                            junctionPanel.addVehicle(coord, purpleCar);
                                        }
                                    }
                                    case RED -> {
                                        coord = new Coordinate(Math.round(i / (n - 1.0f) * 210), 170);
                                        junctionPanel.addVehicle(coord, orangeCar);
                                    }
                                    case PURPLE -> {
                                        if(i <= n/2) {
                                            coord = new Coordinate(Math.round(i / ((n - 1) / 2.0f) * 170), 170);
                                            junctionPanel.addVehicle(coord, orangeCar);
                                        }
                                        else {
                                            coord = new Coordinate( 170, Math.round(170 - (i - ((n - 1) / 2.0f)) / ((n - 1) / 2.0f) * 170));
                                            junctionPanel.addVehicle(coord, blueCar);
                                        }
                                    }
                                }
                            }
                            case PURPLE -> {
                                if(!activeDirections.contains(Direction.PURPLE)) {
                                    activeDirections.add(Direction.PURPLE);
                                }
                                switch(v.getDestionation()){
                                    case BLUE -> {
                                        coord = new Coordinate(40, Math.round( i / (n - 1.0f) * 210));
                                        junctionPanel.addVehicle(coord, purpleCar);
                                    }
                                    case ORANGE -> {
                                        if(i <= n/2) {
                                            coord = new Coordinate(40, Math.round(i / ((n - 1) / 2.0f) * 40));
                                            junctionPanel.addVehicle(coord, purpleCar);
                                        }
                                        else {
                                            coord = new Coordinate(Math.round(40 - (i - ((n - 1) / 2.0f)) / ((n - 1) / 2.0f) * 40), 40);
                                            junctionPanel.addVehicle(coord, redCar);
                                        }
                                    }
                                    case RED -> {
                                        if(i <= n/2) {
                                            coord = new Coordinate(40, Math.round(i / ((n - 1) / 2.0f) * 170));
                                            junctionPanel.addVehicle(coord, purpleCar);
                                        }
                                        else {
                                            coord = new Coordinate(Math.round(40 + (i - ((n - 1) / 2.0f)) / ((n - 1) / 2.0f) * 170), 170);
                                            junctionPanel.addVehicle(coord, orangeCar);
                                        }
                                    }
                                }
                            }
                        }
                    }


                    for (Direction direction : Direction.values()) {
                        if(activeDirections.contains(direction)) {

                            int numberOfVehicles = JunctionFramework.getSimulator().getTrafficLight(direction).getWaitingVehiclesCount();

                            getJRoad(direction).removeAllCoordinate();
                            for (int idx = 0; idx < numberOfVehicles; idx++)
                            {
                                Coordinate original = getCoordinateOfCar(direction, idx + 1);
                                Coordinate goal = getCoordinateOfCar(direction, idx);
                                int x = 0;
                                int y = 0;
                                switch (direction) {
                                    case RED, ORANGE -> {
                                        x = original.x() - ((original.x() - goal.x()) / n) * i;
                                        y = original.y();
                                    }
                                    case BLUE, PURPLE -> {
                                        x = original.x();
                                        y = original.y() - ((original.y() - goal.y()) / n) * i;
                                    }
                                }
                                getJRoad(direction).addNewCoordinate(new Coordinate(x, y));
                            }

                            getJRoad(direction).repaint();
                            getJRoad(direction).revalidate();
                        }
                    }



                    junctionPanel.repaint();
                    junctionPanel.revalidate();
                    Thread.sleep(Math.round(getSpeed()/(1.15*n)));
                }

                junctionPanel.emptyVehicles();
                junctionPanel.repaint();
                junctionPanel.revalidate();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        stopped = true;
    }

    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            System.out.println("Error while loading image: " + path);
        }
        return null;
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
        javax.swing.JLabel blueCarSumLabel;
        javax.swing.JLabel blueDelayLabel;
        javax.swing.JPanel blueInfoPanel;
        javax.swing.JButton blueNewVehicleButton;
        javax.swing.JLabel elapsedTimeLabel;
        javax.swing.JLabel greenDurationLabel;
        javax.swing.JLabel orangeCarSumLabel;
        javax.swing.JLabel orangeDelayLabel;
        javax.swing.JPanel orangeInfoPanel;
        javax.swing.JButton orangeNewVehicleButton;
        javax.swing.JLabel purpleCarSumLabel;
        javax.swing.JLabel purpleDelayLabel;
        javax.swing.JPanel purpleInfoPanel;
        javax.swing.JButton purpleNewVehicleButton;
        javax.swing.JLabel redCarSumLabel;
        javax.swing.JLabel redDelayLabel;
        javax.swing.JPanel redInfoPanel;
        javax.swing.JButton redNewVehicleButton;
        javax.swing.JButton stopButton;
        javax.swing.JButton startButton;
        javax.swing.JLabel speedLabel;

        junctionPanel = new JJunction();
        redNewVehicleButton = new javax.swing.JButton();
        purpleNewVehicleButton = new javax.swing.JButton();
        blueNewVehicleButton = new javax.swing.JButton();
        orangeNewVehicleButton = new javax.swing.JButton();
        speedSlider = new javax.swing.JSlider();
        speedLabel = new javax.swing.JLabel();
        redRoad = new JRoad("src/env/main/junctionframework/images/carRed.png");
        blueRoad = new JRoad("src/env/main/junctionframework/images/carBlue.png");
        orangeRoad = new JRoad("src/env/main/junctionframework/images/carOrange.png");
        purpleRoad = new JRoad("src/env/main/junctionframework/images/carPurple.png");
        stopButton = new javax.swing.JButton();
        startButton = new javax.swing.JButton();
        redLampOrangeLight = new JLight();
        orangeLampRedLight = new JLight();
        purpleLampBlueLight = new JLight();
        blueLampPurpleLight = new JLight();
        redInfoPanel = new javax.swing.JPanel();
        redDelayLabel = new javax.swing.JLabel();
        redDelayValueLabel = new javax.swing.JLabel();
        redCarSumLabel = new javax.swing.JLabel();
        redCarSumValueLabel = new javax.swing.JLabel();
        blueInfoPanel = new javax.swing.JPanel();
        blueDelayLabel = new javax.swing.JLabel();
        blueDelayValueLabel = new javax.swing.JLabel();
        blueCarSumLabel = new javax.swing.JLabel();
        blueCarSumValueLabel = new javax.swing.JLabel();
        orangeInfoPanel = new javax.swing.JPanel();
        orangeDelayLabel = new javax.swing.JLabel();
        orangeDelayValueLabel = new javax.swing.JLabel();
        orangeCarSumLabel = new javax.swing.JLabel();
        orangeCarSumValueLabel = new javax.swing.JLabel();
        purpleInfoPanel = new javax.swing.JPanel();
        purpleDelayLabel = new javax.swing.JLabel();
        purpleDelayValueLabel = new javax.swing.JLabel();
        purpleCarSumLabel = new javax.swing.JLabel();
        purpleCarSumValueLabel = new javax.swing.JLabel();
        elapsedTimeLabel = new javax.swing.JLabel();
        elapsedTimeValueLabel = new javax.swing.JLabel();
        blueLampOrangeLight = new JLight();
        orangeLampBlueLight = new JLight();
        blueLampRedLight = new JLight();
        purpleLampRedLight = new JLight();
        orangeLampPurpleLight = new JLight();
        purpleLampOrangeLight = new JLight();
        redLampPurpleLight = new JLight();
        redLampBlueLight = new JLight();
        greenDurationLabel = new javax.swing.JLabel();
        greenDurationSlider = new javax.swing.JSlider();

        lightImages = new EnumMap<>(LightDirection.class);
        lightImages.put(LightDirection.RED, loadImage("src/env/main/junctionframework/images/red.png"));
        lightImages.put(LightDirection.REDYELLOW, loadImage("src/env/main/junctionframework/images/redYellow.png"));
        lightImages.put(LightDirection.GREENUP, loadImage("src/env/main/junctionframework/images/greenUp.png"));
        lightImages.put(LightDirection.GREENDOWN, loadImage("src/env/main/junctionframework/images/greenDown.png"));
        lightImages.put(LightDirection.GREENLEFT, loadImage("src/env/main/junctionframework/images/greenLeft.png"));
        lightImages.put(LightDirection.GREENRIGHT, loadImage("src/env/main/junctionframework/images/greenRight.png"));
        lightImages.put(LightDirection.YELLOW, loadImage("src/env/main/junctionframework/images/yellow.png"));
        lightImages.put(LightDirection.BLACK, loadImage("src/env/main/junctionframework/images/black.png"));


        redCar = null;
        blueCar = null;
        orangeCar = null;
        purpleCar = null;
        try {
            redCar = ImageIO.read(new File("src/env/main/junctionframework/images/carRed.png"));
            blueCar = ImageIO.read(new File("src/env/main/junctionframework/images/carBlue.png"));
            orangeCar = ImageIO.read(new File("src/env/main/junctionframework/images/carOrange.png"));
            purpleCar = ImageIO.read(new File("src/env/main/junctionframework/images/carPurple.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String vehicleText = "New Vehicle";
        String delayText = "delay avg:";
        String carSumText = "car sum:";

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        javax.swing.GroupLayout junctionPanelLayout = new javax.swing.GroupLayout(junctionPanel);
        junctionPanel.setLayout(junctionPanelLayout);
        junctionPanelLayout.setHorizontalGroup(
                junctionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 260, Short.MAX_VALUE)
        );
        junctionPanelLayout.setVerticalGroup(
                junctionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );

        redNewVehicleButton.setBackground(Color.RED);
        redNewVehicleButton.setText(vehicleText);
        redNewVehicleButton.addActionListener(this::redNewVehicleButtonActionPerformed);

        purpleNewVehicleButton.setBackground(new java.awt.Color(153, 0, 153));
        purpleNewVehicleButton.setText(vehicleText);
        purpleNewVehicleButton.addActionListener(this::purpleNewVehicleButtonActionPerformed);

        blueNewVehicleButton.setBackground(Color.BLUE);
        blueNewVehicleButton.setText(vehicleText);
        blueNewVehicleButton.addActionListener(this::blueNewVehicleButtonActionPerformed);

        orangeNewVehicleButton.setBackground(new java.awt.Color(255, 153, 0));
        orangeNewVehicleButton.setText(vehicleText);
        orangeNewVehicleButton.addActionListener(this::orangeNewVehicleButtonActionPerformed);

        speedSlider.setMinimum(0);
        speedSlider.setMajorTickSpacing(250);
        speedSlider.setMaximum(3500);
        speedSlider.setMinorTickSpacing(250);
        speedSlider.setPaintTicks(true);
        speedSlider.setSnapToTicks(true);
        speedSlider.setValue(3000);
        speedSlider.addChangeListener(evt -> speedSliderStateChanged());

        speedLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 12)); // NOI18N
        speedLabel.setText("Speed");

        redRoad.setBackground(Color.RED);

        javax.swing.GroupLayout redPanelLayout = new javax.swing.GroupLayout(redRoad);
        redRoad.setLayout(redPanelLayout);
        redPanelLayout.setHorizontalGroup(
                redPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 191, Short.MAX_VALUE)
        );
        redPanelLayout.setVerticalGroup(
                redPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );

        blueRoad.setBackground(Color.BLUE);

        javax.swing.GroupLayout bluePanelLayout = new javax.swing.GroupLayout(blueRoad);
        blueRoad.setLayout(bluePanelLayout);
        bluePanelLayout.setHorizontalGroup(
                bluePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );
        bluePanelLayout.setVerticalGroup(
                bluePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );

        orangeRoad.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout orangePanelLayout = new javax.swing.GroupLayout(orangeRoad);
        orangeRoad.setLayout(orangePanelLayout);
        orangePanelLayout.setHorizontalGroup(
                orangePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        orangePanelLayout.setVerticalGroup(
                orangePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );

        purpleRoad.setBackground(new java.awt.Color(153, 0, 153));

        javax.swing.GroupLayout purplePanelLayout = new javax.swing.GroupLayout(purpleRoad);
        purpleRoad.setLayout(purplePanelLayout);
        purplePanelLayout.setHorizontalGroup(
                purplePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );
        purplePanelLayout.setVerticalGroup(
                purplePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 138, Short.MAX_VALUE)
        );

        stopButton.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 12)); // NOI18N
        stopButton.setText("stop");
        stopButton.setToolTipText("");
        stopButton.addActionListener(this::stopButtonActionPerformed);

        startButton.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 12)); // NOI18N
        startButton.setText("start");
        startButton.addActionListener(this::startButtonActionPerformed);

        javax.swing.GroupLayout redLampPanelLayout = new javax.swing.GroupLayout(redLampOrangeLight);
        redLampOrangeLight.setLayout(redLampPanelLayout);
        redLampPanelLayout.setHorizontalGroup(
                redLampPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );
        redLampPanelLayout.setVerticalGroup(
                redLampPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout orangeLampPanelLayout = new javax.swing.GroupLayout(orangeLampRedLight);
        orangeLampRedLight.setLayout(orangeLampPanelLayout);
        orangeLampPanelLayout.setHorizontalGroup(
                orangeLampPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );
        orangeLampPanelLayout.setVerticalGroup(
                orangeLampPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout purpleLampPanelLayout = new javax.swing.GroupLayout(purpleLampBlueLight);
        purpleLampBlueLight.setLayout(purpleLampPanelLayout);
        purpleLampPanelLayout.setHorizontalGroup(
                purpleLampPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );
        purpleLampPanelLayout.setVerticalGroup(
                purpleLampPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout blueLampPanelLayout = new javax.swing.GroupLayout(blueLampPurpleLight);
        blueLampPurpleLight.setLayout(blueLampPanelLayout);
        blueLampPanelLayout.setHorizontalGroup(
                blueLampPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );
        blueLampPanelLayout.setVerticalGroup(
                blueLampPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );

        redDelayLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        redDelayLabel.setText(delayText);

        redDelayValueLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        redDelayValueLabel.setText("0");

        redCarSumLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        redCarSumLabel.setText(carSumText);

        redCarSumValueLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        redCarSumValueLabel.setText("0");

        javax.swing.GroupLayout redInfoPanelLayout = new javax.swing.GroupLayout(redInfoPanel);
        redInfoPanel.setLayout(redInfoPanelLayout);
        redInfoPanelLayout.setHorizontalGroup(
                redInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(redInfoPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(redInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(redDelayLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                                        .addComponent(redDelayValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(redCarSumLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(redCarSumValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        redInfoPanelLayout.setVerticalGroup(
                redInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(redInfoPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(redDelayLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(redDelayValueLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(redCarSumLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(redCarSumValueLabel)
                                .addContainerGap(12, Short.MAX_VALUE))
        );

        blueDelayLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        blueDelayLabel.setText(delayText);

        blueDelayValueLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        blueDelayValueLabel.setText("0");

        blueCarSumLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        blueCarSumLabel.setText(carSumText);

        blueCarSumValueLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        blueCarSumValueLabel.setText("0");

        javax.swing.GroupLayout blueInfoPanelLayout = new javax.swing.GroupLayout(blueInfoPanel);
        blueInfoPanel.setLayout(blueInfoPanelLayout);
        blueInfoPanelLayout.setHorizontalGroup(
                blueInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(blueInfoPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(blueInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(blueDelayLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                                        .addComponent(blueDelayValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(blueCarSumLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(blueCarSumValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        blueInfoPanelLayout.setVerticalGroup(
                blueInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(blueInfoPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(blueDelayLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(blueDelayValueLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(blueCarSumLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(blueCarSumValueLabel)
                                .addContainerGap(12, Short.MAX_VALUE))
        );

        orangeDelayLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        orangeDelayLabel.setText(delayText);

        orangeDelayValueLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        orangeDelayValueLabel.setText("0");

        orangeCarSumLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        orangeCarSumLabel.setText(carSumText);

        orangeCarSumValueLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        orangeCarSumValueLabel.setText("0");

        javax.swing.GroupLayout orangeInfoPanelLayout = new javax.swing.GroupLayout(orangeInfoPanel);
        orangeInfoPanel.setLayout(orangeInfoPanelLayout);
        orangeInfoPanelLayout.setHorizontalGroup(
                orangeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(orangeInfoPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(orangeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(orangeDelayLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                                        .addComponent(orangeDelayValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(orangeCarSumLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(orangeCarSumValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        orangeInfoPanelLayout.setVerticalGroup(
                orangeInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(orangeInfoPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(orangeDelayLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(orangeDelayValueLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(orangeCarSumLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(orangeCarSumValueLabel)
                                .addContainerGap(12, Short.MAX_VALUE))
        );

        purpleDelayLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        purpleDelayLabel.setText(delayText);

        purpleDelayValueLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        purpleDelayValueLabel.setText("0");

        purpleCarSumLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        purpleCarSumLabel.setText(carSumText);

        purpleCarSumValueLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        purpleCarSumValueLabel.setText("0");

        javax.swing.GroupLayout purpleInfoPanelLayout = new javax.swing.GroupLayout(purpleInfoPanel);
        purpleInfoPanel.setLayout(purpleInfoPanelLayout);
        purpleInfoPanelLayout.setHorizontalGroup(
                purpleInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(purpleInfoPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(purpleInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(purpleDelayLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                                        .addComponent(purpleDelayValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(purpleCarSumLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(purpleCarSumValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        purpleInfoPanelLayout.setVerticalGroup(
                purpleInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(purpleInfoPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(purpleDelayLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(purpleDelayValueLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(purpleCarSumLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(purpleCarSumValueLabel)
                                .addContainerGap(12, Short.MAX_VALUE))
        );

        elapsedTimeLabel.setText("Elapsed time:");

        elapsedTimeValueLabel.setText("0");

        javax.swing.GroupLayout blueLampLeftPanelLayout = new javax.swing.GroupLayout(blueLampOrangeLight);
        blueLampOrangeLight.setLayout(blueLampLeftPanelLayout);
        blueLampLeftPanelLayout.setHorizontalGroup(
                blueLampLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );
        blueLampLeftPanelLayout.setVerticalGroup(
                blueLampLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout orangeLampRightPanelLayout = new javax.swing.GroupLayout(orangeLampBlueLight);
        orangeLampBlueLight.setLayout(orangeLampRightPanelLayout);
        orangeLampRightPanelLayout.setHorizontalGroup(
                orangeLampRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );
        orangeLampRightPanelLayout.setVerticalGroup(
                orangeLampRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout blueLampRightPanelLayout = new javax.swing.GroupLayout(blueLampRedLight);
        blueLampRedLight.setLayout(blueLampRightPanelLayout);
        blueLampRightPanelLayout.setHorizontalGroup(
                blueLampRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );
        blueLampRightPanelLayout.setVerticalGroup(
                blueLampRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout purpleLampLeftPanelLayout = new javax.swing.GroupLayout(purpleLampRedLight);
        purpleLampRedLight.setLayout(purpleLampLeftPanelLayout);
        purpleLampLeftPanelLayout.setHorizontalGroup(
                purpleLampLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );
        purpleLampLeftPanelLayout.setVerticalGroup(
                purpleLampLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout orangeLampLeftPanelLayout = new javax.swing.GroupLayout(orangeLampPurpleLight);
        orangeLampPurpleLight.setLayout(orangeLampLeftPanelLayout);
        orangeLampLeftPanelLayout.setHorizontalGroup(
                orangeLampLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );
        orangeLampLeftPanelLayout.setVerticalGroup(
                orangeLampLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout purpleLampRightPanelLayout = new javax.swing.GroupLayout(purpleLampOrangeLight);
        purpleLampOrangeLight.setLayout(purpleLampRightPanelLayout);
        purpleLampRightPanelLayout.setHorizontalGroup(
                purpleLampRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );
        purpleLampRightPanelLayout.setVerticalGroup(
                purpleLampRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout redLampRightPanelLayout = new javax.swing.GroupLayout(redLampPurpleLight);
        redLampPurpleLight.setLayout(redLampRightPanelLayout);
        redLampRightPanelLayout.setHorizontalGroup(
                redLampRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );
        redLampRightPanelLayout.setVerticalGroup(
                redLampRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout redLampLeftPanelLayout = new javax.swing.GroupLayout(redLampBlueLight);
        redLampBlueLight.setLayout(redLampLeftPanelLayout);
        redLampLeftPanelLayout.setHorizontalGroup(
                redLampLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );
        redLampLeftPanelLayout.setVerticalGroup(
                redLampLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );

        greenDurationLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 12)); // NOI18N
        greenDurationLabel.setText("GREEN sign's duration:");

        greenDurationSlider.setMajorTickSpacing(1);
        greenDurationSlider.setMaximum(15);
        greenDurationSlider.setMinimum(1);
        greenDurationSlider.setMinorTickSpacing(1);
        greenDurationSlider.setPaintTicks(true);
        greenDurationSlider.setSnapToTicks(true);
        greenDurationSlider.addChangeListener(evt -> greenDurationSliderStateChanged());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(orangeRoad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(orangeNewVehicleButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(elapsedTimeLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(elapsedTimeValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addComponent(orangeInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(orangeLampBlueLight, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(orangeLampPurpleLight, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(orangeLampRedLight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(purpleNewVehicleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(purpleRoad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(purpleInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(purpleLampOrangeLight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(purpleLampBlueLight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(purpleLampRedLight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(blueInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addComponent(blueLampOrangeLight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(blueLampPurpleLight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(blueLampRedLight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGap(18, 18, 18)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(blueRoad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(blueNewVehicleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addComponent(junctionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(redLampOrangeLight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(redLampPurpleLight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(redLampBlueLight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(45, 45, 45)
                                                .addComponent(redInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(redNewVehicleButton, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                                        .addComponent(redRoad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(greenDurationLabel)
                                        .addComponent(greenDurationSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(speedLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(speedSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(stopButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(startButton)
                                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(speedLabel)
                                        .addComponent(speedSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(stopButton)
                                        .addComponent(startButton))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(7, 7, 7)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(elapsedTimeLabel)
                                                        .addComponent(elapsedTimeValueLabel))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(orangeLampPurpleLight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(orangeLampRedLight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(orangeLampBlueLight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(orangeInfoPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(orangeRoad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(orangeNewVehicleButton))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGap(12, 12, 12)
                                                                                .addComponent(purpleInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(greenDurationLabel)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(greenDurationSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(purpleLampOrangeLight, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(purpleLampBlueLight, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(purpleLampRedLight, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(purpleNewVehicleButton)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(purpleRoad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(junctionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(redNewVehicleButton)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(redRoad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(4, 4, 4)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(redInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(redLampPurpleLight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(redLampOrangeLight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(redLampBlueLight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(blueLampRedLight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(blueLampPurpleLight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(blueLampOrangeLight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                                                .addComponent(blueInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(blueRoad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(blueNewVehicleButton)))
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>


    // Variables declaration
    BufferedImage redCar;
    BufferedImage blueCar;
    BufferedImage orangeCar;
    BufferedImage purpleCar;

    enum LightDirection{
        RED, BLUE, YELLOW, REDYELLOW, GREENRIGHT, GREENLEFT, GREENDOWN, GREENUP, BLACK
    }

    private EnumMap<LightDirection, BufferedImage> lightImages;

    private javax.swing.JLabel blueCarSumValueLabel;

    private javax.swing.JLabel blueDelayValueLabel;

    private JLight blueLampOrangeLight;
    private JLight blueLampPurpleLight;
    private JLight blueLampRedLight;

    private JRoad blueRoad;

    private javax.swing.JLabel elapsedTimeValueLabel;

    private javax.swing.JSlider greenDurationSlider;
    private JJunction junctionPanel;

    private javax.swing.JLabel orangeCarSumValueLabel;

    private javax.swing.JLabel orangeDelayValueLabel;

    private JLight orangeLampPurpleLight;
    private JLight orangeLampRedLight;
    private JLight orangeLampBlueLight;

    private JRoad orangeRoad;

    private javax.swing.JLabel purpleCarSumValueLabel;

    private javax.swing.JLabel purpleDelayValueLabel;

    private JLight purpleLampRedLight;
    private JLight purpleLampBlueLight;
    private JLight purpleLampOrangeLight;

    private JRoad purpleRoad;

    private javax.swing.JLabel redCarSumValueLabel;

    private javax.swing.JLabel redDelayValueLabel;

    private JLight redLampBlueLight;
    private JLight redLampOrangeLight;
    private JLight redLampPurpleLight;

    private JRoad redRoad;

    private javax.swing.JSlider speedSlider;


    // End of variables declaration                   
}
