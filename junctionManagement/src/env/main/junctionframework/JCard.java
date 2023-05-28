package main.junctionframework;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class JCard extends JPanel{

    private BufferedImage image;


    /**
     * Konstruktor.
     */
    public JCard(String path) {
        try {
            InputStream imagestream = getClass().getResourceAsStream(path);
            if(imagestream != null)
                this.image = ImageIO.read(imagestream);
            else
                this.image = null;
        } catch (IOException e) {
            this.image = null;
            System.out.println("Error while loading image: " + path);
        }
    }

    /**
     * Konstruktor.
     * @param scale      Méret.
     * @param image
     */
    public JCard(double scale, BufferedImage image) {
        super(true);
        this.image = image;
        this.setPreferredSize(new Dimension((int) (260 * scale), (int) (260 * scale)));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gCopy = (Graphics2D) g.create();
        g.drawImage(this.image, 0, 0, this.getWidth(), this.getHeight(), null);
        gCopy.dispose();
    }
}