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
        for (models.Room room : models.Room.roomList) {

            // Initialize card container
            JPanel card = new JPanel(new BorderLayout());
            card.setBackground(Color.WHITE);
            card.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230), 1));

            // Header Image
            ImagePanel imgPanel = new ImagePanel(room.getImagePath());
            imgPanel.setPreferredSize(new Dimension(300, 200)); 
            card.add(imgPanel, BorderLayout.NORTH);

            // Details Panel
            JPanel textPanel1 = new JPanel();
            textPanel1.setLayout(new BoxLayout(textPanel1, BoxLayout.Y_AXIS));
            textPanel1.setBackground(Color.WHITE);
            textPanel1.setBorder(new EmptyBorder(15, 15, 20, 15)); // Padding

            // Room Title
            JLabel titleLabel = new JLabel(room.getName());
            titleLabel.setFont(new Font("Serif", Font.PLAIN, 22)); 
            titleLabel.setForeground(new Color(50, 50, 50));
            titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            textPanel1.add(titleLabel);
            
            textPanel1.add(Box.createVerticalStrut(15)); 

            // Room Size Indicator
            JLabel sizeLabel = new JLabel("• " + room.getSize());
            sizeLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
            sizeLabel.setForeground(Color.DARK_GRAY);
            sizeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            textPanel1.add(sizeLabel);
            
            textPanel1.add(Box.createVerticalStrut(8)); 

            // Feature Description
            String firstFeature = (room.getFeatures().length > 0) ? room.getFeatures()[0] : "";
            JLabel viewLabel = new JLabel("<html>• " + firstFeature + "</html>");
            viewLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
            viewLabel.setForeground(Color.DARK_GRAY);
            viewLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            
            // Enforce fixed height to maintain alignment across grid
            viewLabel.setPreferredSize(new Dimension(200, 40)); 
            textPanel1.add(viewLabel);

            // Layout Spacing
            textPanel1.add(Box.createVerticalGlue()); // Pushes content up
            textPanel1.add(Box.createVerticalStrut(15));

            // Navigation Link Button
            JButton viewBtn = new JButton("View Details >");
            viewBtn.setFont(new Font("SansSerif", Font.PLAIN, 14));
            viewBtn.setForeground(new Color(197, 160, 89)); // Brand Gold
            viewBtn.setBackground(Color.WHITE);
            
            // Style as hyperlink
            viewBtn.setBorderPainted(false);      
            viewBtn.setContentAreaFilled(false);  
            viewBtn.setFocusPainted(false);       
            viewBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            // Alignment
            viewBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
            viewBtn.setMargin(new Insets(0,0,0,0)); // Flush left alignment
            
            // Event Listener
            viewBtn.addActionListener(e -> {
                new RoomDetailPage(room).setVisible(true);
                RoomsSuitesPage.this.dispose();
            });
            
            textPanel1.add(viewBtn);

            // Assemble Card
            card.add(textPanel1, BorderLayout.CENTER);
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
