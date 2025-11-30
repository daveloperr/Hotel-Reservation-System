package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import models.ImagePanel;

import java.awt.*;

public class RoomsSuitesPage extends JFrame {

    private static final long serialVersionUID = 1L;

    public RoomsSuitesPage() {

        setTitle("Rooms & Suites - Hotel Reservation");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 50, 1200, 900);
        setLocationRelativeTo(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        setContentPane(scrollPane);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        scrollPane.setViewportView(mainPanel);

        NavBar navBar = new NavBar(); 
        mainPanel.add(navBar);

        ImagePanel headerImg = new ImagePanel("images/roomsuite_header.jpg");
        headerImg.setPreferredSize(new Dimension(1200, 400));
        headerImg.setMaximumSize(new Dimension(Integer.MAX_VALUE, 400));
        headerImg.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(headerImg);

        JPanel textPanel = new JPanel();
        textPanel.setBackground(Color.WHITE);
        textPanel.setBorder(new EmptyBorder(30, 50, 30, 50));
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel("Makati Shangri-La, Manila");
        title.setFont(new Font("Serif", Font.BOLD, 32));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitle = new JLabel("Rooms and Suites");
        subtitle.setFont(new Font("Serif", Font.PLAIN, 24));
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        subtitle.setBorder(new EmptyBorder(10, 0, 20, 0));

        JLabel body = new JLabel("<html>" +
                "A tranquil sanctuary in the heart of the city. Makati Shangri-La, Manila, " +
                "features 696 beautifully appointed guest rooms and suites, which are among " +
                "the most spacious in the city.<br><br>" +
                "Comfortable elegance defines the accommodations, featuring colourful local touches " +
                "and terrific views of the metropolis." +
                "</html>");
        body.setFont(new Font("Arial", Font.PLAIN, 16));
        body.setAlignmentX(Component.LEFT_ALIGNMENT);

        textPanel.add(title);
        textPanel.add(subtitle);
        textPanel.add(body);

        mainPanel.add(textPanel);

        // CENTERED "Rooms & Suites" TITLE ABOVE GALLERY
//        JLabel galleryTitle = new JLabel("Rooms & Suites");
//        galleryTitle.setFont(new Font("Serif", Font.BOLD, 30));
//        galleryTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
//        galleryTitle.setBorder(new EmptyBorder(40, 0, 20, 0));
//        mainPanel.add(galleryTitle);

        JPanel galleryPanel = new JPanel(new GridLayout(0, 3, 20, 20));
        galleryPanel.setBackground(Color.WHITE);
        galleryPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 40, 40));
        galleryPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        int IMAGE_WIDTH = 300, IMAGE_HEIGHT = 200;
        for (int i = 1; i <= 6; i++) {
            JPanel card = new JPanel();
            card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
            card.setBackground(Color.WHITE);

            ImagePanel imgPanel = new ImagePanel("images/room" + i + ".jpg");
            imgPanel.setPreferredSize(new Dimension(IMAGE_WIDTH, IMAGE_HEIGHT));
            card.add(imgPanel);

            
            JLabel descLabel = new JLabel("Room " + i);
            descLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            descLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
            card.add(descLabel);

            galleryPanel.add(card);
        }

        mainPanel.add(galleryPanel);

        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RoomsSuitesPage().setVisible(true));
    }
}
