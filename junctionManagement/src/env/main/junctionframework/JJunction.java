package main.junctionframework;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class JJunction extends JPanel{

    private BufferedImage image;
    private final String path;


    /**
     * Konstruktor.
     */
    public JJunction() {
        super(true);
        this.setPreferredSize(new Dimension(260, 260));

this.path = "src/env/main/junctionframework/images/intersection.png";

        try {
            this.image = ImageIO.read(new File(path));
        } catch (IOException e) {
            this.image = null;
            System.out.println("Error while loading image: " + path);
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);

        // Draw the cars
    }
}