package models;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {

    private Image background;

    public ImagePanel(String imagePath) {
        try {
            background = Toolkit.getDefaultToolkit().getImage(imagePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setLayout(new GridBagLayout()); // allows centered text inside
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (background != null) {
            Graphics2D g2d = (Graphics2D) g;

            int panelWidth = getWidth();
            int panelHeight = getHeight();
            int imgWidth = background.getWidth(this);
            int imgHeight = background.getHeight(this);

            if (imgWidth <= 0 || imgHeight <= 0) return;

            double scale = Math.max((double) panelWidth / imgWidth,
                                    (double) panelHeight / imgHeight);

            int drawWidth = (int) (imgWidth * scale);
            int drawHeight = (int) (imgHeight * scale);

            int x = (panelWidth - drawWidth) / 2;
            int y = (panelHeight - drawHeight) / 2;

            g2d.drawImage(background, x, y, drawWidth, drawHeight, this);
        }
    }
}
