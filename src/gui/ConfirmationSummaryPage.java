package gui;

import java.awt.*;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import models.Checkout;

public class ConfirmationSummaryPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private Checkout checkout;
    
    // UI Colors matching your theme
    private static final Color BG_COLOR = Color.WHITE;
    private static final Color SECTION_BG = new Color(250, 250, 250);
    private static final Color GOLD_COLOR = new Color(218, 165, 32);
    private static final Font HEADER_FONT = new Font("Serif", Font.BOLD, 24);
    private static final Font SUBHEADER_FONT = new Font("Arial", Font.BOLD, 14);
    private static final Font TEXT_FONT = new Font("Arial", Font.PLAIN, 12);

    public ConfirmationSummaryPage(Checkout checkout) {
        this.checkout = checkout;

        setTitle("Confirm Reservation - Shangri-La");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 800);
        setLocationRelativeTo(null);

        // Main Scroll Pane
        JPanel mainContent = new JPanel();
        mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.Y_AXIS));
        mainContent.setBackground(BG_COLOR);
        
        JScrollPane scrollPane = new JScrollPane(mainContent);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        setContentPane(scrollPane);

        // --- 1. Navigation Bar ---
        NavBar navBar = new NavBar();
        navBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        mainContent.add(navBar);

        // --- 2. Content Wrapper ---
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setBackground(BG_COLOR);
        wrapper.setBorder(new EmptyBorder(30, 100, 50, 100));
        mainContent.add(wrapper);

        // Header Title
        JLabel lblTitle = new JLabel("Review & Confirm Reservation");
        lblTitle.setFont(HEADER_FONT);
        lblTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        wrapper.add(lblTitle);
        wrapper.add(Box.createVerticalStrut(20));

        // --- 3. Guest Details Section ---
        JPanel guestPanel = createSectionPanel("Guest Information");
        addDetailRow(guestPanel, "Name:", checkout.getFirstName() + " " + checkout.getLastName());
        addDetailRow(guestPanel, "Email:", checkout.getEmail());
        addDetailRow(guestPanel, "Mobile:", checkout.getIddCode() + " " + checkout.getMobileNumber());
        addDetailRow(guestPanel, "Country:", checkout.getCountry());
        wrapper.add(guestPanel);
        wrapper.add(Box.createVerticalStrut(20));

        // --- 4. Stay Details Section ---
        JPanel stayPanel = createSectionPanel("Stay Details");
        
        // Add Room Image thumbnail
        try {
            ImageIcon icon = new ImageIcon(checkout.getSelectedRoom().getImagePath());
            Image img = icon.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH);
            JLabel imgLabel = new JLabel(new ImageIcon(img));
            imgLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            imgLabel.setBorder(new EmptyBorder(0, 0, 10, 0));
            stayPanel.add(imgLabel);
        } catch (Exception e) {  }

        addDetailRow(stayPanel, "Room Type:", checkout.getSelectedRoom().getName());
        addDetailRow(stayPanel, "Check-in:", checkout.getCheckInDate());
        addDetailRow(stayPanel, "Check-out:", checkout.getCheckOutDate());
        addDetailRow(stayPanel, "Duration:", checkout.getNights() + " Night(s)");
        addDetailRow(stayPanel, "Occupancy:", checkout.getNumberOfAdults() + " Adult(s), " + checkout.getNumberOfChildren() + " Children");
        wrapper.add(stayPanel);
        wrapper.add(Box.createVerticalStrut(20));

        // --- 5. Payment Breakdown Section ---
        JPanel costPanel = createSectionPanel("Payment Summary");
        
        // Header Row for Cost
        JPanel headerRow = new JPanel(new BorderLayout());
        headerRow.setBackground(SECTION_BG);
        JLabel itemLbl = new JLabel("Item"); itemLbl.setFont(SUBHEADER_FONT);
        JLabel priceLbl = new JLabel("Price"); priceLbl.setFont(SUBHEADER_FONT);
        headerRow.add(itemLbl, BorderLayout.WEST);
        headerRow.add(priceLbl, BorderLayout.EAST);
        headerRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        costPanel.add(headerRow);
        costPanel.add(Box.createVerticalStrut(10));
        costPanel.add(new JSeparator());

        // Dynamic Rows
        addCostRow(costPanel, "Room Charges (" + checkout.getNumberOfRooms() + " room, " + checkout.getNights() + " nights)", checkout.getRoomCharges());
        addCostRow(costPanel, "Service Charge & Tax (12%)", checkout.getServiceTax());
        
        if (checkout.getMemberDiscount() > 0) {
            addCostRow(costPanel, "Member Discount (10%)", -checkout.getMemberDiscount());
        }

        // Add-ons Loop
        Map<String, Integer> services = checkout.getSelectedServices();
        if (!services.isEmpty()) {
            costPanel.add(Box.createVerticalStrut(5));
            JLabel lblAddons = new JLabel("Add-on Services:");
            lblAddons.setFont(new Font("Arial", Font.BOLD, 11));
            costPanel.add(lblAddons);
            
            addCostRow(costPanel, "Total Add-ons Selected", checkout.getServicesTotal());
        }

        costPanel.add(Box.createVerticalStrut(10));
        costPanel.add(new JSeparator());
        
        // Grand Total
        JPanel totalRow = new JPanel(new BorderLayout());
        totalRow.setBackground(SECTION_BG);
        totalRow.setBorder(new EmptyBorder(10, 0, 0, 0));
        
        JLabel lblTotalTxt = new JLabel("TOTAL AMOUNT");
        lblTotalTxt.setFont(new Font("Arial", Font.BOLD, 16));
        
        JLabel lblTotalVal = new JLabel(String.format("PHP %,.2f", checkout.getTotalCharges()));
        lblTotalVal.setFont(new Font("Arial", Font.BOLD, 18));
        lblTotalVal.setForeground(GOLD_COLOR);
        
        totalRow.add(lblTotalTxt, BorderLayout.WEST);
        totalRow.add(lblTotalVal, BorderLayout.EAST);
        totalRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        costPanel.add(totalRow);
        
        wrapper.add(costPanel);
        wrapper.add(Box.createVerticalStrut(30));

        // --- 6. Action Buttons ---
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.setBackground(BG_COLOR);
        btnPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

        JButton btnBack = new JButton("Edit Details");
        styleSecondaryButton(btnBack);
        btnBack.addActionListener(e -> {
            this.dispose(); 
        });

        JButton btnConfirm = new JButton("CONFIRM RESERVATION");
        stylePrimaryButton(btnConfirm);
        btnConfirm.addActionListener(e -> {
            // Finalize booking
            boolean success = checkout.completeBooking();
            if(success) {
                int refNum = (int)(Math.random() * 1000000);
                JOptionPane.showMessageDialog(this, 
                    "Reservation Successful!\nYour Reference Number is: #" + refNum + "\n\nAn email has been sent to " + checkout.getEmail(), 
                    "Confirmed", 
                    JOptionPane.INFORMATION_MESSAGE);
                
                // Return to Home
                this.dispose();
                new HomePage().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Error completing booking.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnPanel.add(btnBack);
        btnPanel.add(Box.createHorizontalStrut(10));
        btnPanel.add(btnConfirm);
        
        wrapper.add(btnPanel);
    }

    // --- Helper Methods for UI ---

    private JPanel createSectionPanel(String title) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(SECTION_BG);
        panel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(230, 230, 230), 1),
            new EmptyBorder(15, 20, 15, 20)
        ));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 300)); // Restrict height growth

        JLabel header = new JLabel(title);
        header.setFont(SUBHEADER_FONT);
        header.setForeground(new Color(80, 80, 80));
        header.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        panel.add(header);
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JSeparator());
        panel.add(Box.createVerticalStrut(10));
        
        return panel;
    }

    private void addDetailRow(JPanel parent, String label, String value) {
        JPanel row = new JPanel(new BorderLayout());
        row.setBackground(SECTION_BG);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        
        JLabel l = new JLabel(label);
        l.setFont(TEXT_FONT);
        l.setForeground(Color.GRAY);
        
        JLabel v = new JLabel(value);
        v.setFont(TEXT_FONT);
        v.setForeground(Color.BLACK);
        
        row.add(l, BorderLayout.WEST);
        row.add(v, BorderLayout.EAST);
        parent.add(row);
        parent.add(Box.createVerticalStrut(5));
    }

    private void addCostRow(JPanel parent, String label, double amount) {
        JPanel row = new JPanel(new BorderLayout());
        row.setBackground(SECTION_BG);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
        
        JLabel l = new JLabel(label);
        l.setFont(TEXT_FONT);
        
        String prefix = amount < 0 ? "- PHP " : "PHP ";
        JLabel v = new JLabel(prefix + String.format("%,.2f", Math.abs(amount)));
        v.setFont(TEXT_FONT);
        if(amount < 0) v.setForeground(new Color(34, 139, 34)); // Green for discount
        
        row.add(l, BorderLayout.WEST);
        row.add(v, BorderLayout.EAST);
        parent.add(row);
        parent.add(Box.createVerticalStrut(5));
    }

    private void stylePrimaryButton(JButton btn) {
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(GOLD_COLOR);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    private void styleSecondaryButton(JButton btn) {
        btn.setFont(new Font("Arial", Font.PLAIN, 14));
        btn.setForeground(Color.GRAY);
        btn.setBackground(BG_COLOR);
        btn.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}