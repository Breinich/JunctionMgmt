package main.junctionframework;

import main.world.Direction;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.LinkedList;

public class JRoad extends JPanel {

    private LinkedList<Coordinate> vehiclesCoordinates;
    private String path;

    BufferedImage image;

    public JRoad(String path) {
        super(true);
        this.path = path;

        try {
            this.image = ImageIO.read(new File(path));
        } catch (IOException e) {
            this.image = null;
            System.out.println("Error while loading image: " + path);
        }

        this.vehiclesCoordinates = new LinkedList<>();
    }

    public void addNewCoordinate(Coordinate c) {
    	vehiclesCoordinates.add(c);
    }

    public void removeAllCoordinate() {
    	vehiclesCoordinates.clear();
    }



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Coordinate vehicle : vehiclesCoordinates) {
            g.drawImage(image, vehicle.x(), vehicle.y(), null);
        }
    }

}
