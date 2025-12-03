package components;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class FindARoomNavBar extends JPanel {

    public FindARoomNavBar() {
        this("Dec 9, 2025", "Dec 10, 2025", 1, 2, 0);
    }
    
    public FindARoomNavBar(String checkIn, String checkOut, int rooms, int adults, int children) {

        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 8)); 
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 50)); 
        setBackground(new Color(250, 250, 250));
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));

        // Location Input
        JTextField txtLocation = new JTextField("Makati Shangri-La, Manila");
        txtLocation.setPreferredSize(new Dimension(200, 25));
        txtLocation.setFont(new Font("Arial", Font.PLAIN, 12));
        txtLocation.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        add(txtLocation);

        // Labels
        JLabel lblCheckIn = new JLabel(checkIn);
        lblCheckIn.setFont(new Font("Arial", Font.PLAIN, 12));
        add(lblCheckIn);

        JLabel lblNight = new JLabel("1 night");
        lblNight.setFont(new Font("Arial", Font.PLAIN, 12));
        add(lblNight);

        JLabel lblCheckOut = new JLabel(checkOut);
        lblCheckOut.setFont(new Font("Arial", Font.PLAIN, 12));
        add(lblCheckOut);

        JLabel lblGuests = new JLabel(rooms + " Room" + (rooms != 1 ? "s" : "") + ", " + adults + " Adult" + (adults != 1 ? "s" : "") + ", " + children + " Child" + (children != 1 ? "ren" : ""));
        lblGuests.setFont(new Font("Arial", Font.PLAIN, 12));
        add(lblGuests);

        // Special Code TextField
        JTextField txtSpecialCode = new JTextField("Special Code");
        txtSpecialCode.setPreferredSize(new Dimension(110, 25));
        txtSpecialCode.setFont(new Font("Arial", Font.PLAIN, 12));
        txtSpecialCode.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        txtSpecialCode.setForeground(new Color(150, 150, 150));
        add(txtSpecialCode);

        // Search Button
        JButton btnSearch = new JButton("Search");
        btnSearch.setPreferredSize(new Dimension(150, 34));
        btnSearch.setBackground(new Color(218, 165, 32));
        btnSearch.setForeground(Color.BLACK);
        btnSearch.setFont(new Font("Arial", Font.BOLD, 13));
        btnSearch.setFocusPainted(false);
        add(btnSearch);
    }
}
