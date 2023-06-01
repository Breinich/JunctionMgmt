package main.junctionframework;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class JLight extends JPanel {

        private BufferedImage color;

        public JLight() {
            setBackground(Color.BLACK);
        }

        public void setColor(BufferedImage c) {
            color = c;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            if(color != null)
                g.drawImage(color, 0, 0, null);
        }
}
