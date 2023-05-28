package main.junctionframework;

import main.world.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class JRoad extends JPanel {

    private LinkedList<Coordinate> vehiclesCoordinates;

    BufferedImage image;

    public JRoad() {
        super(true);
        this.vehiclesCoordinates = new LinkedList<>();
    }

    public void addNewCoordinate(Coordinate c) {
    	vehiclesCoordinates.add(c);
    }



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Coordinate vehicle : vehiclesCoordinates) {
            g.drawImage(image, vehicle.x(), 0, null);
        }
    }

}
