package main.junctionframework;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class JRoad extends JPanel {

    private final LinkedList<Coordinate> vehiclesCoordinates;

    private BufferedImage image;

    public JRoad(String path) {
        super(true);

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
