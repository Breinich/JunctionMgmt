package main.junctionframework;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;

public class JJunction extends JPanel{

    private BufferedImage image;
    private final String path;

    private HashMap<Coordinate, BufferedImage> vehicles;

    /**
     * Konstruktor.
     */
    public JJunction() {
        super(true);

        this.setBackground(Color.GREEN);
        this.setSize(260, 260);

        this.path = "src/env/main/junctionframework/images/intersection.png";

        try {
            this.image = ImageIO.read(new File(path));
        } catch (IOException e) {
            this.image = null;
            System.out.println("Error while loading image: " + path);
        }
    }

    public void addVehicle(Coordinate c, BufferedImage img){
        if(img != null){
            if(vehicles == null)
                vehicles = new HashMap<>();
            vehicles.put(c, img);
        }
        else{
            System.out.println("Error while loading image: simulating car");
        }
    }

    public void emptyVehicles(){
        if (vehicles != null)
            vehicles.clear();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);

        // Draw the cars
        if(vehicles != null && !vehicles.isEmpty()){
            for(Map.Entry<Coordinate,BufferedImage> e : vehicles.entrySet())
            {
                g.drawImage(e.getValue(), e.getKey().x(), e.getKey().y(), null);
            }
        }
    }
}