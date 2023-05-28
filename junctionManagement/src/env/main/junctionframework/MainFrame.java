package main.junctionframework;

import main.world.Direction;
import main.world.Vehicle;

import javax.swing.JFrame;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {


	public MainFrame() {
		super("Junction Management with Intelligent Traffic Lights");
		
		initComponents();
	}

	public void refresh() {
        List<Vehicle> movingVehicles = JunctionFramework.getSimulator().getActualMovingVehicles();
        if(movingVehicles != null){}
        //TODO move cars

        //TODO update lamps
        redLampPurplePanel.setBackground(JunctionFramework.getSimulator().getTrafficLight(Direction.RED).getColor(Direction.PURPLE));
        redLampOrangePanel.setBackground(JunctionFramework.getSimulator().getTrafficLight(Direction.RED).getColor(Direction.ORANGE));
        redLampBluePanel.setBackground(JunctionFramework.getSimulator().getTrafficLight(Direction.RED).getColor(Direction.BLUE));
        blueLampPurplePanel.setBackground(JunctionFramework.getSimulator().getTrafficLight(Direction.BLUE).getColor(Direction.PURPLE));
        blueLampOrangePanel.setBackground(JunctionFramework.getSimulator().getTrafficLight(Direction.BLUE).getColor(Direction.ORANGE));
        blueLampRedPanel.setBackground(JunctionFramework.getSimulator().getTrafficLight(Direction.BLUE).getColor(Direction.RED));
        orangeLampPurplePanel.setBackground(JunctionFramework.getSimulator().getTrafficLight(Direction.ORANGE).getColor(Direction.PURPLE));
        orangeLampRedPanel.setBackground(JunctionFramework.getSimulator().getTrafficLight(Direction.ORANGE).getColor(Direction.RED));
        orangeLampBluePanel.setBackground(JunctionFramework.getSimulator().getTrafficLight(Direction.ORANGE).getColor(Direction.BLUE));
        purpleLampRedPanel.setBackground(JunctionFramework.getSimulator().getTrafficLight(Direction.PURPLE).getColor(Direction.RED));
        purpleLampOrangePanel.setBackground(JunctionFramework.getSimulator().getTrafficLight(Direction.PURPLE).getColor(Direction.ORANGE));
        purpleLampBluePanel.setBackground(JunctionFramework.getSimulator().getTrafficLight(Direction.PURPLE).getColor(Direction.BLUE));


		//calculate the values
        redCarSumValueLabel.setText(JunctionFramework.getSimulator().getCarSumByDirection(Direction.RED).toString());
        redDelayValueLabel.setText(JunctionFramework.getSimulator().getAvgWaitingByDirection(Direction.RED).toString());
        blueCarSumValueLabel.setText(JunctionFramework.getSimulator().getCarSumByDirection(Direction.BLUE).toString());
        blueDelayValueLabel.setText(JunctionFramework.getSimulator().getAvgWaitingByDirection(Direction.BLUE).toString());
        orangeCarSumValueLabel.setText(JunctionFramework.getSimulator().getCarSumByDirection(Direction.ORANGE).toString());
        orangeDelayValueLabel.setText(JunctionFramework.getSimulator().getAvgWaitingByDirection(Direction.ORANGE).toString());
        purpleCarSumValueLabel.setText(JunctionFramework.getSimulator().getCarSumByDirection(Direction.PURPLE).toString());
        purpleDelayValueLabel.setText(JunctionFramework.getSimulator().getAvgWaitingByDirection(Direction.PURPLE).toString());

        elapsedTimeValueLabel.setText(JunctionFramework.getSimulator().getTime().toString());

        this.repaint();
	}

	private void redNewVehicleButtonActionPerformed(java.awt.event.ActionEvent evt) {
		JunctionFramework.getSimulator().addVehicle(Direction.RED);
    }                                                   

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        JunctionFramework.getSimulator().stop();
    }                                          

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        JunctionFramework.getSimulator().start();
    }                                           

    private void purpleNewVehicleButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                       
        JunctionFramework.getSimulator().addVehicle(Direction.PURPLE);
    }                                                      

    private void orangeNewVehicleButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                       
        JunctionFramework.getSimulator().addVehicle(Direction.ORANGE);
    }                                                      

    private void blueNewVehicleButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                     
        JunctionFramework.getSimulator().addVehicle(Direction.BLUE);
    }                                                    

    private void speedSliderStateChanged() {
        JunctionFramework.getSimulator().setSpeed(2000-speedSlider.getValue());
    }

    private void greenDurationSliderStateChanged() {
        JunctionFramework.getSimulator().setGreenDuration(greenDurationSlider.getValue());
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        junctionPanel = new JCard("src/env/main/junctionframework/images/intersection.png");
        redNewVehicleButton = new javax.swing.JButton();
        purpleNewVehicleButton = new javax.swing.JButton();
        blueNewVehicleButton = new javax.swing.JButton();
        orangeNewVehicleButton = new javax.swing.JButton();
        speedSlider = new javax.swing.JSlider();
        speedLabel = new javax.swing.JLabel();
        redPanel = new javax.swing.JPanel();
        bluePanel = new javax.swing.JPanel();
        orangePanel = new javax.swing.JPanel();
        purplePanel = new javax.swing.JPanel();
        stopButton = new javax.swing.JButton();
        startButton = new javax.swing.JButton();
        redLampOrangePanel = new javax.swing.JPanel();
        orangeLampRedPanel = new javax.swing.JPanel();
        purpleLampBluePanel = new javax.swing.JPanel();
        blueLampPurplePanel = new javax.swing.JPanel();
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
        blueLampOrangePanel = new javax.swing.JPanel();
        orangeLampBluePanel = new javax.swing.JPanel();
        blueLampRedPanel = new javax.swing.JPanel();
        purpleLampRedPanel = new javax.swing.JPanel();
        orangeLampPurplePanel = new javax.swing.JPanel();
        purpleLampOrangePanel = new javax.swing.JPanel();
        redLampPurplePanel = new javax.swing.JPanel();
        redLampBluePanel = new javax.swing.JPanel();
        greenDurationLabel = new javax.swing.JLabel();
        greenDurationSlider = new javax.swing.JSlider();

        String vehicleText = "New Vehicle";
        String delayText = "delay avg:";
        String carSumText = "car sum:";

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        junctionPanel.setBackground(Color.green);
        junctionPanel.setPreferredSize(new java.awt.Dimension(260, 260));

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

        speedSlider.setMajorTickSpacing(250);
        speedSlider.setMaximum(1500);
        speedSlider.setMinorTickSpacing(250);
        speedSlider.setPaintTicks(true);
        speedSlider.setSnapToTicks(true);
        speedSlider.setValue(0);
        speedSlider.addChangeListener(evt -> speedSliderStateChanged());

        speedLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 12)); // NOI18N
        speedLabel.setText("Speed");

        redPanel.setBackground(Color.RED);

        javax.swing.GroupLayout redPanelLayout = new javax.swing.GroupLayout(redPanel);
        redPanel.setLayout(redPanelLayout);
        redPanelLayout.setHorizontalGroup(
                redPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 191, Short.MAX_VALUE)
        );
        redPanelLayout.setVerticalGroup(
                redPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );

        bluePanel.setBackground(Color.BLUE);

        javax.swing.GroupLayout bluePanelLayout = new javax.swing.GroupLayout(bluePanel);
        bluePanel.setLayout(bluePanelLayout);
        bluePanelLayout.setHorizontalGroup(
                bluePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );
        bluePanelLayout.setVerticalGroup(
                bluePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );

        orangePanel.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout orangePanelLayout = new javax.swing.GroupLayout(orangePanel);
        orangePanel.setLayout(orangePanelLayout);
        orangePanelLayout.setHorizontalGroup(
                orangePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        orangePanelLayout.setVerticalGroup(
                orangePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );

        purplePanel.setBackground(new java.awt.Color(153, 0, 153));

        javax.swing.GroupLayout purplePanelLayout = new javax.swing.GroupLayout(purplePanel);
        purplePanel.setLayout(purplePanelLayout);
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

        redLampOrangePanel.setBackground(Color.BLACK);

        javax.swing.GroupLayout redLampPanelLayout = new javax.swing.GroupLayout(redLampOrangePanel);
        redLampOrangePanel.setLayout(redLampPanelLayout);
        redLampPanelLayout.setHorizontalGroup(
                redLampPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );
        redLampPanelLayout.setVerticalGroup(
                redLampPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );

        orangeLampRedPanel.setBackground(Color.BLACK);

        javax.swing.GroupLayout orangeLampPanelLayout = new javax.swing.GroupLayout(orangeLampRedPanel);
        orangeLampRedPanel.setLayout(orangeLampPanelLayout);
        orangeLampPanelLayout.setHorizontalGroup(
                orangeLampPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );
        orangeLampPanelLayout.setVerticalGroup(
                orangeLampPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );

        purpleLampBluePanel.setBackground(Color.BLACK);

        javax.swing.GroupLayout purpleLampPanelLayout = new javax.swing.GroupLayout(purpleLampBluePanel);
        purpleLampBluePanel.setLayout(purpleLampPanelLayout);
        purpleLampPanelLayout.setHorizontalGroup(
                purpleLampPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );
        purpleLampPanelLayout.setVerticalGroup(
                purpleLampPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );

        blueLampPurplePanel.setBackground(Color.BLACK);

        javax.swing.GroupLayout blueLampPanelLayout = new javax.swing.GroupLayout(blueLampPurplePanel);
        blueLampPurplePanel.setLayout(blueLampPanelLayout);
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

        blueLampOrangePanel.setBackground(Color.BLACK);

        javax.swing.GroupLayout blueLampLeftPanelLayout = new javax.swing.GroupLayout(blueLampOrangePanel);
        blueLampOrangePanel.setLayout(blueLampLeftPanelLayout);
        blueLampLeftPanelLayout.setHorizontalGroup(
                blueLampLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );
        blueLampLeftPanelLayout.setVerticalGroup(
                blueLampLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );

        orangeLampBluePanel.setBackground(Color.BLACK);

        javax.swing.GroupLayout orangeLampRightPanelLayout = new javax.swing.GroupLayout(orangeLampBluePanel);
        orangeLampBluePanel.setLayout(orangeLampRightPanelLayout);
        orangeLampRightPanelLayout.setHorizontalGroup(
                orangeLampRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );
        orangeLampRightPanelLayout.setVerticalGroup(
                orangeLampRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );

        blueLampRedPanel.setBackground(Color.BLACK);

        javax.swing.GroupLayout blueLampRightPanelLayout = new javax.swing.GroupLayout(blueLampRedPanel);
        blueLampRedPanel.setLayout(blueLampRightPanelLayout);
        blueLampRightPanelLayout.setHorizontalGroup(
                blueLampRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );
        blueLampRightPanelLayout.setVerticalGroup(
                blueLampRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );

        purpleLampRedPanel.setBackground(Color.BLACK);

        javax.swing.GroupLayout purpleLampLeftPanelLayout = new javax.swing.GroupLayout(purpleLampRedPanel);
        purpleLampRedPanel.setLayout(purpleLampLeftPanelLayout);
        purpleLampLeftPanelLayout.setHorizontalGroup(
                purpleLampLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );
        purpleLampLeftPanelLayout.setVerticalGroup(
                purpleLampLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );

        orangeLampPurplePanel.setBackground(Color.BLACK);

        javax.swing.GroupLayout orangeLampLeftPanelLayout = new javax.swing.GroupLayout(orangeLampPurplePanel);
        orangeLampPurplePanel.setLayout(orangeLampLeftPanelLayout);
        orangeLampLeftPanelLayout.setHorizontalGroup(
                orangeLampLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );
        orangeLampLeftPanelLayout.setVerticalGroup(
                orangeLampLeftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );

        purpleLampOrangePanel.setBackground(Color.BLACK);

        javax.swing.GroupLayout purpleLampRightPanelLayout = new javax.swing.GroupLayout(purpleLampOrangePanel);
        purpleLampOrangePanel.setLayout(purpleLampRightPanelLayout);
        purpleLampRightPanelLayout.setHorizontalGroup(
                purpleLampRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );
        purpleLampRightPanelLayout.setVerticalGroup(
                purpleLampRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );

        redLampPurplePanel.setBackground(Color.BLACK);

        javax.swing.GroupLayout redLampRightPanelLayout = new javax.swing.GroupLayout(redLampPurplePanel);
        redLampPurplePanel.setLayout(redLampRightPanelLayout);
        redLampRightPanelLayout.setHorizontalGroup(
                redLampRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );
        redLampRightPanelLayout.setVerticalGroup(
                redLampRightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 40, Short.MAX_VALUE)
        );

        redLampBluePanel.setBackground(Color.BLACK);

        javax.swing.GroupLayout redLampLeftPanelLayout = new javax.swing.GroupLayout(redLampBluePanel);
        redLampBluePanel.setLayout(redLampLeftPanelLayout);
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
                                        .addComponent(orangePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                                                        .addComponent(orangeLampBluePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(orangeLampPurplePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(orangeLampRedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(purpleNewVehicleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(purplePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(purpleInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(purpleLampOrangePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(purpleLampBluePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(purpleLampRedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                .addComponent(blueInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGroup(layout.createSequentialGroup()
                                                                        .addComponent(blueLampOrangePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(blueLampPurplePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(blueLampRedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGap(18, 18, 18)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(bluePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(blueNewVehicleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addComponent(junctionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(redLampOrangePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(redLampPurplePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(redLampBluePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(45, 45, 45)
                                                .addComponent(redInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(redNewVehicleButton, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                                        .addComponent(redPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                                                                .addComponent(orangeLampPurplePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(orangeLampRedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(orangeLampBluePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(orangeInfoPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(orangePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                                                        .addComponent(purpleLampOrangePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(purpleLampBluePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(purpleLampRedPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(purpleNewVehicleButton)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(purplePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(junctionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(redNewVehicleButton)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(redPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(4, 4, 4)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(redInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(redLampPurplePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(redLampOrangePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(redLampBluePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(blueLampRedPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(blueLampPurplePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(blueLampOrangePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                                                .addComponent(blueInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(bluePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(blueNewVehicleButton)))
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>


    // Variables declaration
    private javax.swing.JLabel blueCarSumLabel;
    private javax.swing.JLabel blueCarSumValueLabel;
    private javax.swing.JLabel blueDelayLabel;
    private javax.swing.JLabel blueDelayValueLabel;
    private javax.swing.JPanel blueInfoPanel;
    private javax.swing.JPanel blueLampOrangePanel;
    private javax.swing.JPanel blueLampPurplePanel;
    private javax.swing.JPanel blueLampRedPanel;
    private javax.swing.JButton blueNewVehicleButton;
    private javax.swing.JPanel bluePanel;
    private javax.swing.JLabel elapsedTimeLabel;
    private javax.swing.JLabel elapsedTimeValueLabel;
    private javax.swing.JLabel greenDurationLabel;
    private javax.swing.JSlider greenDurationSlider;
    private JCard junctionPanel;
    private javax.swing.JLabel orangeCarSumLabel;
    private javax.swing.JLabel orangeCarSumValueLabel;
    private javax.swing.JLabel orangeDelayLabel;
    private javax.swing.JLabel orangeDelayValueLabel;
    private javax.swing.JPanel orangeInfoPanel;
    private javax.swing.JPanel orangeLampPurplePanel;
    private javax.swing.JPanel orangeLampRedPanel;
    private javax.swing.JPanel orangeLampBluePanel;
    private javax.swing.JButton orangeNewVehicleButton;
    private javax.swing.JPanel orangePanel;
    private javax.swing.JLabel purpleCarSumLabel;
    private javax.swing.JLabel purpleCarSumValueLabel;
    private javax.swing.JLabel purpleDelayLabel;
    private javax.swing.JLabel purpleDelayValueLabel;
    private javax.swing.JPanel purpleInfoPanel;
    private javax.swing.JPanel purpleLampRedPanel;
    private javax.swing.JPanel purpleLampBluePanel;
    private javax.swing.JPanel purpleLampOrangePanel;
    private javax.swing.JButton purpleNewVehicleButton;
    private javax.swing.JPanel purplePanel;
    private javax.swing.JLabel redCarSumLabel;
    private javax.swing.JLabel redCarSumValueLabel;
    private javax.swing.JLabel redDelayLabel;
    private javax.swing.JLabel redDelayValueLabel;
    private javax.swing.JPanel redInfoPanel;
    private javax.swing.JPanel redLampBluePanel;
    private javax.swing.JPanel redLampOrangePanel;
    private javax.swing.JPanel redLampPurplePanel;
    private javax.swing.JButton redNewVehicleButton;
    private javax.swing.JPanel redPanel;
    private javax.swing.JLabel speedLabel;
    private javax.swing.JSlider speedSlider;
    private javax.swing.JButton startButton;
    private javax.swing.JButton stopButton;
    // End of variables declaration                   
}
