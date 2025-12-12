package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NavBar extends JPanel {

    private static final long serialVersionUID = 1L;

    public NavBar() {
        setLayout(new BorderLayout());

        // --- Top white nav bar ---
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setBackground(Color.WHITE);
        topBar.setPreferredSize(new Dimension(900, 50));

        ImageIcon logo = new ImageIcon("images/hotel_logo.png");
        Image img = logo.getImage().getScaledInstance(180, 40, Image.SCALE_SMOOTH);
        topBar.add(new JLabel(new ImageIcon(img)), BorderLayout.WEST);

        JLabel langLabel = new JLabel("English | PHP");
        langLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        topBar.add(langLabel, BorderLayout.EAST);

        // --- Black navigation bar ---
        JPanel userNavBar = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 15));
        userNavBar.setBackground(new Color(0x33, 0x33, 0x33));
        String[] navItems = {"HOME", "ROOM & SUITES", "GALLERY"};

        for (String navItem : navItems) {
            final String item = navItem;
            JLabel label = new JLabel(item);
            label.setForeground(Color.WHITE);
            label.setFont(new Font("Times New Roman", Font.BOLD, 14));
            label.setCursor(new Cursor(Cursor.HAND_CURSOR));

            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    switch (item) {
                        case "HOME":
                            new HomePage().setVisible(true);
                            SwingUtilities.getWindowAncestor(NavBar.this).dispose();
                            break;
//                        case "ABOUT":
//                            new AboutPage().setVisible(true);
//                            SwingUtilities.getWindowAncestor(NavBar.this).dispose();
//                            break;
                        case "ROOM & SUITES":
                            new RoomsSuitesPage().setVisible(true);
                            SwingUtilities.getWindowAncestor(NavBar.this).dispose();
                            break;
                        case "GALLERY":
                            new GalleryPage().setVisible(true);
                            SwingUtilities.getWindowAncestor(NavBar.this).dispose();
                            break;
                    }
                }
            });

            userNavBar.add(label);
        }

        // Add top white and black nav bars to this panel
        add(topBar, BorderLayout.NORTH);
        add(userNavBar, BorderLayout.SOUTH);
    }
}
