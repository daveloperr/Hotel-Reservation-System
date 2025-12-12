package gui;

import models.Gallery; 
import models.ImagePanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GalleryPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final int IMAGE_WIDTH = 350;
    private static final int IMAGE_HEIGHT = 250;

    private static final String[] CHECKBOX_OPTIONS = {
        "Room & Suites", "Hotel", "Dining", "Health & Leisure", 
        "Meetings & Events", "Weddings & Celebrations"
    };

    public GalleryPage() {

        setTitle("Gallery - Hotel Reservation");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 50, 1200, 1500);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        setContentPane(scrollPane);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        scrollPane.setViewportView(mainPanel);

        // --- NAVIGATION ---
        NavBar navBar = new NavBar();
        navBar.setAlignmentX(Component.CENTER_ALIGNMENT);
        navBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        mainPanel.add(navBar);

        // --- HEADER IMAGE ---
        ImagePanel headerPanel = new ImagePanel("images/galleryimage.jpg");
        headerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.setPreferredSize(new Dimension(1, 600));
        headerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 600));

        JLabel headerText = new JLabel("Gallery Photos");
        headerText.setFont(new Font("Times New Roman", Font.BOLD, 48));
        headerText.setForeground(Color.WHITE);
        headerPanel.add(headerText);

        mainPanel.add(headerPanel);

        // --- CHECKBOX PANEL ---
        JPanel checkboxPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        checkboxPanel.setBackground(Color.WHITE);
        checkboxPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        checkboxPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        for (String option : CHECKBOX_OPTIONS) {
            JCheckBox checkBox = new JCheckBox(option);
            checkBox.setBackground(Color.WHITE);
            checkBox.setFont(new Font("Arial", Font.BOLD, 14));
            checkboxPanel.add(checkBox);
        }

        mainPanel.add(checkboxPanel);

        // --- GALLERY GRID (3 per row ALWAYS) ---
        JPanel galleryPanel = new JPanel(new GridLayout(0, 3, 20, 20));
        galleryPanel.setBackground(Color.WHITE);
        galleryPanel.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 40));
        galleryPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        List<Gallery.GalleryItem> items = Gallery.items;

        for (Gallery.GalleryItem item : items) {

            JPanel card = new JPanel();
            card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
            card.setBackground(Color.WHITE);

            // IMAGE AREA — using ImagePanel from models
            ImagePanel imgPanel = new ImagePanel(item.getImagePath());
            imgPanel.setPreferredSize(new Dimension(IMAGE_WIDTH, IMAGE_HEIGHT));
            card.add(imgPanel);

            // DESCRIPTION
            JLabel descLabel = new JLabel(item.getDescription());
            descLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            descLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
            card.add(descLabel);

            galleryPanel.add(card);
        }

        mainPanel.add(galleryPanel);

//        // --- READ MORE ---
//        JLabel readMore = new JLabel("<HTML><U>Read More</U></HTML>");
//        readMore.setForeground(Color.BLUE);
//        readMore.setFont(new Font("Arial", Font.BOLD, 16));
//        readMore.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//        readMore.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//        readMore.addMouseListener(new java.awt.event.MouseAdapter() {
//            @Override
//            public void mouseClicked(java.awt.event.MouseEvent e) {
//                JOptionPane.showMessageDialog(null, "Next batch of images would load here");
//            }
//        });
//
//        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
//        mainPanel.add(readMore);
//
//        revalidate();
//        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GalleryPage().setVisible(true));
    }
}
