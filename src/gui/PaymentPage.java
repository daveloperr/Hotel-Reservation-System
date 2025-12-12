package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Cursor;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import models.Checkout;
import models.Room; 

public class PaymentPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtCardNumber;
    private JTextField txtCardHolderName;
    private JTextField txtMonth;
    private JTextField txtYear;
    
    // 1. THIS IS THE MISSING FIELD
    private Checkout checkout;

    /**
     * Launch the application (For testing purposes).
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Dummy data so you can run this file by itself to test UI
                    Room testRoom = new Room("Deluxe Room", "Room", "Twin", "Desc", "images/deluxeroom.png", "45sqm", new String[]{}, new String[]{}, 10500);
                    Checkout testCheckout = new Checkout(testRoom, "Dec 11, 2025", "Dec 12, 2025", 1, 2, 0, true);
                    
                    PaymentPage frame = new PaymentPage(testCheckout);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 2. THIS IS THE CONSTRUCTOR YOU ARE MISSING
     */
    public PaymentPage(Checkout checkout) {
        this.checkout = checkout;
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1100, 750);
        setLocationRelativeTo(null); // Center the frame
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        contentPane.setLayout(null);
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);
        
        // --- Navigation ---
        JPanel navPanel = new JPanel();
        navPanel.setBounds(0, 0, 1100, 35);
        navPanel.setBackground(new Color(245, 245, 245));
        navPanel.setLayout(null);
        contentPane.add(navPanel);
        
        JLabel lblSelectHotel = new JLabel("Select a Hotel");
        lblSelectHotel.setBounds(250, 8, 100, 20);
        lblSelectHotel.setFont(new Font("Arial", Font.PLAIN, 12));
        lblSelectHotel.setForeground(new Color(150, 150, 150));
        navPanel.add(lblSelectHotel);
        
        JLabel lblFindRoom = new JLabel("Find a Room");
        lblFindRoom.setBounds(400, 8, 100, 20);
        lblFindRoom.setFont(new Font("Arial", Font.PLAIN, 12));
        lblFindRoom.setForeground(new Color(150, 150, 150));
        navPanel.add(lblFindRoom);
        
        JLabel lblReview = new JLabel("Review");
        lblReview.setBounds(550, 8, 100, 20);
        lblReview.setFont(new Font("Arial", Font.PLAIN, 12));
        lblReview.setForeground(new Color(150, 150, 150));
        navPanel.add(lblReview);
        
        JLabel lblGuarantee = new JLabel("Guarantee or Pay");
        lblGuarantee.setBounds(680, 8, 120, 20);
        lblGuarantee.setFont(new Font("Arial", Font.BOLD, 12));
        lblGuarantee.setForeground(new Color(0, 0, 0));
        navPanel.add(lblGuarantee);
        
        // --- Notice Banner ---
        JPanel noticePanel = new JPanel();
        noticePanel.setBounds(0, 35, 1100, 30);
        noticePanel.setBackground(new Color(240, 240, 240));
        noticePanel.setLayout(null);
        contentPane.add(noticePanel);
        
        JLabel lblNotice = new JLabel("The reservation will be closed in next 15:00");
        lblNotice.setBounds(400, 5, 300, 20);
        lblNotice.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNotice.setForeground(new Color(100, 100, 100));
        noticePanel.add(lblNotice);
        
        // --- Back Button ---
        JLabel lblBack = new JLabel("< Back");
        lblBack.setBounds(30, 85, 60, 20);
        lblBack.setFont(new Font("Arial", Font.PLAIN, 12));
        lblBack.setForeground(new Color(100, 100, 100));
        lblBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Logic to go back if needed
                dispose(); 
            }
        });
        contentPane.add(lblBack);
        
        // --- Summary Section ---
        JLabel lblStaySummary = new JLabel("Stay Summary: Makati Shangri-La, Manila");
        lblStaySummary.setBounds(140, 84, 400, 20);
        lblStaySummary.setFont(new Font("Arial", Font.BOLD, 14));
        contentPane.add(lblStaySummary);
        
        // Room image
        JLabel lblRoomImage = new JLabel();
        lblRoomImage.setBounds(113, 116, 143, 108);
        lblRoomImage.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        
        try {
            ImageIcon roomIcon = new ImageIcon(checkout.getSelectedRoom().getImagePath());
            Image scaledImage = roomIcon.getImage().getScaledInstance(143, 108, Image.SCALE_SMOOTH);
            lblRoomImage.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            lblRoomImage.setBackground(new Color(200, 180, 140));
            lblRoomImage.setOpaque(true);
        }
        contentPane.add(lblRoomImage);
        
        // Dynamic Data Labels
        String dateStr = checkout.getCheckInDate() + " / " + checkout.getCheckOutDate() + " (" + checkout.getNights() + " nights)";
        JLabel lblDate = new JLabel(dateStr);
        lblDate.setBounds(270, 120, 350, 18);
        lblDate.setFont(new Font("Arial", Font.PLAIN, 12));
        lblDate.setForeground(new Color(60, 60, 60));
        contentPane.add(lblDate);
        
        String guestStr = "Rooms: " + checkout.getNumberOfRooms() + ", Adults: " + checkout.getNumberOfAdults() + ", Children: " + checkout.getNumberOfChildren();
        String typeStr = "   Room Type: " + checkout.getSelectedRoom().getName();
        JLabel lblRoomsGuests = new JLabel(guestStr + typeStr);
        lblRoomsGuests.setBounds(270, 140, 600, 18); 
        lblRoomsGuests.setFont(new Font("Arial", Font.PLAIN, 11));
        lblRoomsGuests.setForeground(new Color(60, 60, 60));
        contentPane.add(lblRoomsGuests);
        
        JLabel lblRateName = new JLabel("Rate Name: " + checkout.getSelectedRoom().getType() + "     Policy: Non-refundable");
        lblRateName.setBounds(270, 160, 500, 18);
        lblRateName.setFont(new Font("Arial", Font.PLAIN, 11));
        lblRateName.setForeground(new Color(60, 60, 60));
        contentPane.add(lblRateName);
        
        // --- Amount Panel ---
        JPanel amountPanel = new JPanel();
        amountPanel.setBounds(650, 120, 250, 80);
        amountPanel.setBackground(Color.WHITE);
        amountPanel.setLayout(null);
        contentPane.add(amountPanel);
        
        JLabel lblAmountLabel = new JLabel("Amount to Pay Now:");
        lblAmountLabel.setBounds(0, 0, 250, 18);
        lblAmountLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        lblAmountLabel.setForeground(new Color(100, 100, 100));
        lblAmountLabel.setHorizontalAlignment(JLabel.RIGHT);
        amountPanel.add(lblAmountLabel);
        
        // Dynamic Total Price
        JLabel lblAmount = new JLabel("PHP " + String.format("%,.2f", checkout.getTotalCharges()));
        lblAmount.setBounds(0, 18, 250, 25);
        lblAmount.setFont(new Font("Arial", Font.BOLD, 18));
        lblAmount.setForeground(new Color(0, 0, 0));
        lblAmount.setHorizontalAlignment(JLabel.RIGHT);
        amountPanel.add(lblAmount);
        
        JLabel lblTaxNote = new JLabel("+ Inclusive of Service Charge and Tax");
        lblTaxNote.setBounds(0, 43, 250, 15);
        lblTaxNote.setFont(new Font("Arial", Font.PLAIN, 9));
        lblTaxNote.setForeground(new Color(150, 150, 150));
        lblTaxNote.setHorizontalAlignment(JLabel.RIGHT);
        amountPanel.add(lblTaxNote);
        
        // Price notice
        JLabel lblPriceNotice = new JLabel("The lowest prices. Instant benefits. Only via Shangri-La.com.");
        lblPriceNotice.setBounds(165, 235, 350, 18);
        lblPriceNotice.setFont(new Font("Arial", Font.PLAIN, 11));
        lblPriceNotice.setForeground(new Color(200, 150, 50));
        contentPane.add(lblPriceNotice);
        
        // --- Payment Form Section ---
        JLabel lblAdvanceDeposit = new JLabel("Advance Deposit Payment");
        lblAdvanceDeposit.setBounds(140, 270, 250, 20);
        lblAdvanceDeposit.setFont(new Font("Arial", Font.BOLD, 14));
        contentPane.add(lblAdvanceDeposit);
        
        JLabel lblDepositNote = new JLabel("Note: Some banks may charge additional service fees or online payment service.");
        lblDepositNote.setBounds(140, 295, 500, 18);
        lblDepositNote.setFont(new Font("Arial", Font.PLAIN, 11));
        lblDepositNote.setForeground(new Color(120, 120, 120));
        contentPane.add(lblDepositNote);
        
        JLabel lblPaymentMethods = new JLabel("Payment methods:");
        lblPaymentMethods.setBounds(140, 325, 120, 18);
        lblPaymentMethods.setFont(new Font("Arial", Font.PLAIN, 12));
        contentPane.add(lblPaymentMethods);
        
        // Icons
        String[] cardTypes = {"VISA", "MC", "AMEX", "JCB", "Discover", "UnionPay"};
        int iconX = 270;
        for (String card : cardTypes) {
            JLabel lblCard = new JLabel(card);
            lblCard.setBounds(iconX, 325, 50, 18);
            lblCard.setFont(new Font("Arial", Font.BOLD, 8));
            lblCard.setForeground(Color.WHITE);
            lblCard.setBackground(new Color(50, 100, 180));
            lblCard.setOpaque(true);
            lblCard.setHorizontalAlignment(JLabel.CENTER);
            lblCard.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
            contentPane.add(lblCard);
            iconX += 60;
        }
        
        // Grey Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setBounds(90, 355, 730, 290);
        formPanel.setBackground(new Color(245, 245, 245));
        formPanel.setLayout(null);
        contentPane.add(formPanel);
        
        JLabel lblChoosePayment = new JLabel("Choose payment method *");
        lblChoosePayment.setBounds(60, 10, 200, 18);
        lblChoosePayment.setFont(new Font("Arial", Font.PLAIN, 12));
        lblChoosePayment.setForeground(new Color(60, 60, 60));
        formPanel.add(lblChoosePayment);
        
        JComboBox<String> cmbPaymentMethod = new JComboBox<>(new String[]{"Credit Card", "Debit Card"});
        cmbPaymentMethod.setBounds(60, 30, 435, 35);
        cmbPaymentMethod.setFont(new Font("Arial", Font.PLAIN, 12));
        cmbPaymentMethod.setBackground(Color.WHITE);
        formPanel.add(cmbPaymentMethod);
        
        JLabel lblCardNumber = new JLabel("Card Number *");
        lblCardNumber.setBounds(60, 80, 200, 18);
        lblCardNumber.setFont(new Font("Arial", Font.PLAIN, 12));
        lblCardNumber.setForeground(new Color(60, 60, 60));
        formPanel.add(lblCardNumber);
        
        txtCardNumber = new JTextField();
        txtCardNumber.setBounds(60, 100, 435, 35);
        formPanel.add(txtCardNumber);
        
        JLabel lblCardHolderName = new JLabel("Card Holder's Name *");
        lblCardHolderName.setBounds(60, 150, 200, 18);
        lblCardHolderName.setFont(new Font("Arial", Font.PLAIN, 12));
        lblCardHolderName.setForeground(new Color(60, 60, 60));
        formPanel.add(lblCardHolderName);
        
        txtCardHolderName = new JTextField();
        txtCardHolderName.setBounds(60, 170, 435, 35);
        formPanel.add(txtCardHolderName);
        
        JLabel lblExpiryDate = new JLabel("Expiry Date (Month / Year) *");
        lblExpiryDate.setBounds(60, 220, 200, 18);
        lblExpiryDate.setFont(new Font("Arial", Font.PLAIN, 12));
        lblExpiryDate.setForeground(new Color(60, 60, 60));
        formPanel.add(lblExpiryDate);
        
        txtMonth = new JTextField("MM");
        txtMonth.setBounds(60, 240, 80, 35);
        formPanel.add(txtMonth);
        
        txtYear = new JTextField("YYYY");
        txtYear.setBounds(155, 240, 120, 35);
        formPanel.add(txtYear);
        
        // --- Submit Button ---
        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(380, 655, 160, 40);
        btnSubmit.setFont(new Font("Arial", Font.BOLD, 14));
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setBackground(new Color(218, 165, 32));
        btnSubmit.setBorder(BorderFactory.createLineBorder(new Color(218, 165, 32)));
        btnSubmit.setFocusPainted(false);
        btnSubmit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // OPEN CONFIRMATION PAGE LISTENER
        btnSubmit.addActionListener(e -> {
            // Basic Validation
            if (txtCardNumber.getText().isEmpty() || txtCardHolderName.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all payment details.", "Missing Details", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Open Confirmation Page
            ConfirmationSummaryPage confirmPage = new ConfirmationSummaryPage(this.checkout);
            confirmPage.setVisible(true);
            
            // Close Payment Page
            this.dispose();
        });
        
        contentPane.add(btnSubmit);
    }
}