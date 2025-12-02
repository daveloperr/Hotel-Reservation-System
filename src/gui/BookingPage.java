package gui;

import components.FindARoomNavBar;
import components.FindARoomTabPanel;
import models.Room;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BookingPage extends JFrame {

    public BookingPage() {
        setTitle("Booking Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1200, 900);
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);

        // North NavBar
        NavBar navBar = new NavBar();
        navBar.setPreferredSize(new Dimension(1200, 100));
        contentPane.add(navBar, BorderLayout.NORTH);

        // Center panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.WHITE);
        contentPane.add(centerPanel, BorderLayout.CENTER);

        centerPanel.add(new FindARoomNavBar());
        centerPanel.add(new FindARoomTabPanel());

        // Table
        JPanel tablePanel = new JPanel(new GridBagLayout());
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setBorder(new EmptyBorder(10, 50, 10, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 8, 12, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;

        // Header
        JLabel hdrRoom = new JLabel("Room Type");
        hdrRoom.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0.45;
        tablePanel.add(hdrRoom, gbc);

        JLabel hdrRate = new JLabel("Rate Plan");
        hdrRate.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 1; gbc.weightx = 0.40;
        tablePanel.add(hdrRate, gbc);

        JLabel hdrPrice = new JLabel("Average Per Night");
        hdrPrice.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 2; gbc.weightx = 0.15;
        tablePanel.add(hdrPrice, gbc);

        // Header separator (full width)
        row++;
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 3;
        tablePanel.add(new JSeparator(), gbc);
        gbc.gridwidth = 1;
        row++;

        // ROOM LOOP
        for (Room room : Room.roomList) {

            int bedIndex = 0;

          for (String bedType : new String[]{"Twin", "King"}) {

    // COLUMN 1 — IMAGE ONLY ON THE FIRST
    JPanel col1 = new JPanel();
    col1.setLayout(new BoxLayout(col1, BoxLayout.Y_AXIS));
    col1.setBackground(Color.WHITE);

    if (bedIndex == 0) {
        JLabel img = new JLabel();
        img.setPreferredSize(new Dimension(320, 190));
        try {
            ImageIcon icon = new ImageIcon(room.getImagePath());
            Image scaled = icon.getImage().getScaledInstance(320, 190, Image.SCALE_SMOOTH);
            img.setIcon(new ImageIcon(scaled));
        } catch (Exception e) {
            img.setOpaque(true);
            img.setBackground(Color.LIGHT_GRAY);
        }
        col1.add(img);
        col1.add(Box.createVerticalStrut(8));

        JLabel nm = new JLabel(room.getName() + " (" + bedType + ")");
        nm.setFont(new Font("Arial", Font.BOLD, 16));
        col1.add(nm);

        JLabel size = new JLabel(room.getSize());
        size.setFont(new Font("Arial", Font.PLAIN, 12));
        col1.add(size);

    } else {
        JPanel blank = new JPanel();
        blank.setPreferredSize(new Dimension(320, 190));
        blank.setBackground(Color.WHITE);
        col1.add(blank);
    }

    gbc.gridx = 0;
    gbc.gridy = row;
    gbc.anchor = GridBagConstraints.NORTHWEST;
    tablePanel.add(col1, gbc);

    // COLUMN 2 — RATE PLAN (TOP ALIGNED)
    JPanel col2 = new JPanel();
    col2.setLayout(new BoxLayout(col2, BoxLayout.Y_AXIS));
    col2.setBackground(Color.WHITE);

    JLabel rateTitle = new JLabel("Flexible Rate (" + bedType + ")");
    rateTitle.setFont(new Font("Arial", Font.BOLD, 13));
    rateTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
    col2.add(rateTitle);
    col2.add(Box.createVerticalStrut(3));

    JLabel d1 = new JLabel("<html>&#8226; Members Online Exclusive</html>");
    d1.setFont(new Font("Arial", Font.PLAIN, 11));
    d1.setAlignmentX(Component.LEFT_ALIGNMENT);
    col2.add(d1);
    col2.add(Box.createVerticalStrut(3));

    JLabel d2 = new JLabel("<html>&#8226; Non-refundable, fees apply</html>");
    d2.setFont(new Font("Arial", Font.PLAIN, 11));
    d2.setAlignmentX(Component.LEFT_ALIGNMENT);
    col2.add(d2);
    col2.add(Box.createVerticalStrut(3));

    JLabel pay = new JLabel("Pay online");
    pay.setFont(new Font("Arial", Font.BOLD, 11));
    pay.setForeground(new Color(218,165,32));
    pay.setAlignmentX(Component.LEFT_ALIGNMENT);
    col2.add(pay);

    // LESS SPACE — push Earn down 
    col2.add(Box.createVerticalStrut(30));

    JLabel earn = new JLabel("  Earn 325  ");
    earn.setOpaque(true);
    earn.setBackground(new Color(255,215,0));
    earn.setFont(new Font("Arial", Font.BOLD, 11));
    earn.setBorder(BorderFactory.createEmptyBorder(3,6,3,6));
    earn.setAlignmentX(Component.LEFT_ALIGNMENT);
    col2.add(earn);

    // Add space between earn and details
    col2.add(Box.createVerticalStrut(10)); 

    JLabel details = new JLabel("<html><u>Rate Details</u></html>");
    details.setFont(new Font("Arial", Font.BOLD, 11));
    details.setAlignmentX(Component.LEFT_ALIGNMENT);
    col2.add(details);


    gbc.gridx = 1;
    gbc.anchor = GridBagConstraints.NORTHWEST;
    tablePanel.add(col2, gbc);

    // COLUMN 3 — PRICE + BUTTON (TOP ALIGNED)
    JPanel col3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
    col3.setBackground(Color.WHITE);

    double price = room.getPricePerNight(); 
    JLabel priceLabel = new JLabel("PHP " + String.format("%,.0f", price));
    priceLabel.setFont(new Font("Arial", Font.BOLD, 15));
    col3.add(priceLabel);

    JButton btn = new JButton("Book Now");
    btn.setBackground(new Color(218,165,32));
    btn.setForeground(Color.BLACK);
    btn.setFont(new Font("Arial", Font.BOLD, 12));
    btn.setFocusPainted(false);
    col3.add(btn);

    gbc.gridx = 2;
    gbc.anchor = GridBagConstraints.NORTHWEST; // TOP alignment
    tablePanel.add(col3, gbc);

    row++;
    bedIndex++;

    // SEPARATOR BETWEEN TWIN & KING
    if (bedIndex == 1) {
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        JSeparator mid = new JSeparator();
        mid.setForeground(new Color(220,220,220));
        tablePanel.add(mid, gbc);
        gbc.gridwidth = 1;
        row++;
    }
}

// AFTER KING — FULL WIDTH SEPARATOR
gbc.gridx = 0;
gbc.gridy = row;
gbc.gridwidth = 3;
JSeparator full = new JSeparator();
full.setForeground(new Color(220,220,220));
tablePanel.add(full, gbc);
gbc.gridwidth = 1;
row++;



if (bedIndex == 1) { 
    gbc.gridx = 1;        
    gbc.gridy = row;
    gbc.gridwidth = 2;      
    JSeparator midSep = new JSeparator();
    midSep.setForeground(new Color(220, 220, 220));
    tablePanel.add(midSep, gbc);
    gbc.gridwidth = 1;

} else {
    
    gbc.gridx = 0;         
    gbc.gridy = row;
    gbc.gridwidth = 3;      
    JSeparator fullSep = new JSeparator();
    fullSep.setForeground(new Color(220, 220, 220));
    tablePanel.add(fullSep, gbc);
    gbc.gridwidth = 1;
}

row++;
        }

        JScrollPane scrollPane = new JScrollPane(tablePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        centerPanel.add(scrollPane);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new BookingPage().setVisible(true);
        });
    }
}
