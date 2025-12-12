package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Cursor;
import java.awt.Toolkit; 
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
    
    private Checkout checkout;

    
    // Define the ideal width for the central content block (e.g., 80% of a standard 1280px wide screen)
    private static final int IDEAL_CONTENT_WIDTH = 950; 
    
    // Spacing constants (remain fixed for visual consistency)
    private static final int SECTION_GAP_Y = 30;
    private static final int V_SPACING = 20; 
    private static final int H_SPACING = 20; 
    private static final Color PRIMARY_ACCENT = new Color(218, 165, 32); 

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
                    // Use setExtendedState to maximize the frame
                    frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Constructor
     */
    public PaymentPage(Checkout checkout) {
        this.checkout = checkout;
        

        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        

        final int MAIN_CONTENT_WIDTH = Math.min(IDEAL_CONTENT_WIDTH, screenWidth - 100); 
        final int START_X = (screenWidth - MAIN_CONTENT_WIDTH) / 2; 

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, screenWidth, screenHeight); 
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        contentPane.setLayout(null);
        contentPane.setBackground(Color.WHITE);
        setContentPane(contentPane);
        
        int currentY = 0; // Tracks the Y position for layout

        // --- Navigation ---
        JPanel navPanel = new JPanel();
        navPanel.setBounds(0, currentY, screenWidth, 35); // Stretches across the full screen width
        navPanel.setBackground(new Color(245, 245, 245));
        navPanel.setLayout(null);
        contentPane.add(navPanel);
        currentY += 35;
        
        // Navigation labels (Centralized based on screenWidth)
        int navStartX = (screenWidth / 2) - 250; 
        int navX = navStartX;
        
        JLabel lblSelectHotel = new JLabel("Select a Hotel");
        lblSelectHotel.setBounds(navX, 8, 100, 20);
        lblSelectHotel.setFont(new Font("Arial", Font.PLAIN, 12));
        lblSelectHotel.setForeground(new Color(150, 150, 150));
        navPanel.add(lblSelectHotel);
        navX += 130;
        
        JLabel lblFindRoom = new JLabel("Find a Room");
        lblFindRoom.setBounds(navX, 8, 100, 20);
        lblFindRoom.setFont(new Font("Arial", Font.PLAIN, 12));
        lblFindRoom.setForeground(new Color(150, 150, 150));
        navPanel.add(lblFindRoom);
        navX += 120;
        
        JLabel lblReview = new JLabel("Review");
        lblReview.setBounds(navX, 8, 100, 20);
        lblReview.setFont(new Font("Arial", Font.PLAIN, 12));
        lblReview.setForeground(new Color(150, 150, 150));
        navPanel.add(lblReview);
        navX += 120;
        
        JLabel lblGuarantee = new JLabel("Guarantee or Pay");
        lblGuarantee.setBounds(navX, 8, 120, 20);
        lblGuarantee.setFont(new Font("Arial", Font.BOLD, 12));
        lblGuarantee.setForeground(new Color(0, 0, 0));
        navPanel.add(lblGuarantee);
        
        // --- Notice Banner ---
        JPanel noticePanel = new JPanel();
        noticePanel.setBounds(0, currentY, screenWidth, 30); // Stretches across the full screen width
        noticePanel.setBackground(new Color(240, 240, 240));
        noticePanel.setLayout(null);
        contentPane.add(noticePanel);
        currentY += 30;
        
        JLabel lblNotice = new JLabel("The reservation will be closed in next 15:00");
        lblNotice.setBounds((screenWidth / 2) - 150, 5, 300, 20); // Centered horizontally
        lblNotice.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNotice.setForeground(new Color(100, 100, 100));
        noticePanel.add(lblNotice);
        
        currentY += 25; // Space after banner

        // --- Back Button ---
        // Aligned relative to the START_X of the main content
        JLabel lblBack = new JLabel("< Back");
        lblBack.setBounds(START_X - 60, currentY, 60, 20); 
        lblBack.setFont(new Font("Arial", Font.PLAIN, 12));
        lblBack.setForeground(new Color(100, 100, 100));
        lblBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dispose(); 
            }
        });
        contentPane.add(lblBack);
        
        // --- Summary Section Title ---
        JLabel lblStaySummary = new JLabel("Stay Summary: Makati Shangri-La, Manila");
        lblStaySummary.setBounds(START_X, currentY, 400, 20);
        lblStaySummary.setFont(new Font("Arial", Font.BOLD, 14));
        contentPane.add(lblStaySummary);
        
        currentY += SECTION_GAP_Y;
        
        int summaryPanelY = currentY;

        // --- Summary Content (Image + Text + Amount Panel) ---
        final int IMAGE_SIZE_W = 143;
        final int IMAGE_SIZE_H = 108;
        final int AMOUNT_PANEL_WIDTH = 250;
        
        // 1. Room image
        JLabel lblRoomImage = new JLabel();
        lblRoomImage.setBounds(START_X, summaryPanelY, IMAGE_SIZE_W, IMAGE_SIZE_H);
        lblRoomImage.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        
        try {
            ImageIcon roomIcon = new ImageIcon(checkout.getSelectedRoom().getImagePath());
            Image scaledImage = roomIcon.getImage().getScaledInstance(IMAGE_SIZE_W, IMAGE_SIZE_H, Image.SCALE_SMOOTH);
            lblRoomImage.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            lblRoomImage.setBackground(new Color(200, 180, 140));
            lblRoomImage.setOpaque(true);
            lblRoomImage.setText("Image Missing");
            lblRoomImage.setHorizontalAlignment(JLabel.CENTER);
            lblRoomImage.setFont(new Font("Arial", Font.ITALIC, 10));
        }
        contentPane.add(lblRoomImage);
        
        // 2. Text Block (aligned next to image)
        int textX = START_X + IMAGE_SIZE_W + H_SPACING;
        int currentTextY = summaryPanelY + 5;
        
        String dateStr = checkout.getCheckInDate() + " / " + checkout.getCheckOutDate() + " (" + checkout.getNights() + " nights)";
        JLabel lblDate = new JLabel(dateStr);
        lblDate.setBounds(textX, currentTextY, 350, 18);
        lblDate.setFont(new Font("Arial", Font.PLAIN, 12));
        lblDate.setForeground(new Color(60, 60, 60));
        contentPane.add(lblDate);
        currentTextY += V_SPACING;
        
        String guestStr = "Rooms: " + checkout.getNumberOfRooms() + ", Adults: " + checkout.getNumberOfAdults() + ", Children: " + checkout.getNumberOfChildren();
        String typeStr = " | Room Type: " + checkout.getSelectedRoom().getName();
        JLabel lblRoomsGuests = new JLabel(guestStr + typeStr);
        lblRoomsGuests.setBounds(textX, currentTextY, 600, 18); 
        lblRoomsGuests.setFont(new Font("Arial", Font.PLAIN, 11));
        lblRoomsGuests.setForeground(new Color(60, 60, 60));
        contentPane.add(lblRoomsGuests);
        currentTextY += V_SPACING;
        
        JLabel lblRateName = new JLabel("Rate Name: " + checkout.getSelectedRoom().getType() + " | Policy: Non-refundable");
        lblRateName.setBounds(textX, currentTextY, 500, 18);
        lblRateName.setFont(new Font("Arial", Font.PLAIN, 11));
        lblRateName.setForeground(new Color(60, 60, 60));
        contentPane.add(lblRateName);
        
        // 3. Amount Panel (aligned to the right edge of the content block)
        int amountPanelX = START_X + MAIN_CONTENT_WIDTH - AMOUNT_PANEL_WIDTH; 
        JPanel amountPanel = new JPanel();
        amountPanel.setBounds(amountPanelX, summaryPanelY, AMOUNT_PANEL_WIDTH, 80);
        amountPanel.setBackground(Color.WHITE);
        amountPanel.setLayout(null);
        contentPane.add(amountPanel);
        
        JLabel lblAmountLabel = new JLabel("Amount to Pay Now:");
        lblAmountLabel.setBounds(0, 0, AMOUNT_PANEL_WIDTH, 18);
        lblAmountLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        lblAmountLabel.setForeground(new Color(100, 100, 100));
        lblAmountLabel.setHorizontalAlignment(JLabel.RIGHT);
        amountPanel.add(lblAmountLabel);
        
        // Dynamic Total Price
        JLabel lblAmount = new JLabel("PHP " + String.format("%,.2f", checkout.getTotalCharges()));
        lblAmount.setBounds(0, 18, AMOUNT_PANEL_WIDTH, 25);
        lblAmount.setFont(new Font("Arial", Font.BOLD, 18));
        lblAmount.setForeground(new Color(0, 0, 0));
        lblAmount.setHorizontalAlignment(JLabel.RIGHT);
        amountPanel.add(lblAmount);
        
        JLabel lblTaxNote = new JLabel("+ Inclusive of Service Charge and Tax");
        lblTaxNote.setBounds(0, 43, AMOUNT_PANEL_WIDTH, 15);
        lblTaxNote.setFont(new Font("Arial", Font.PLAIN, 9));
        lblTaxNote.setForeground(new Color(150, 150, 150));
        lblTaxNote.setHorizontalAlignment(JLabel.RIGHT);
        amountPanel.add(lblTaxNote);
        
        currentY = summaryPanelY + IMAGE_SIZE_H + V_SPACING; 
        
        // Price notice
        JLabel lblPriceNotice = new JLabel("The lowest prices. Instant benefits. Only via Shangri-La.com.");
        lblPriceNotice.setBounds(START_X, currentY, 400, 18);
        lblPriceNotice.setFont(new Font("Arial", Font.PLAIN, 11));
        lblPriceNotice.setForeground(PRIMARY_ACCENT);
        contentPane.add(lblPriceNotice);
        
        currentY += SECTION_GAP_Y + 10;
        
        // --- Payment Form Section Title ---
        JLabel lblAdvanceDeposit = new JLabel("Advance Deposit Payment");
        lblAdvanceDeposit.setBounds(START_X, currentY, 250, 20);
        lblAdvanceDeposit.setFont(new Font("Arial", Font.BOLD, 14));
        contentPane.add(lblAdvanceDeposit);
        currentY += V_SPACING + 5;
        
        JLabel lblDepositNote = new JLabel("Note: Some banks may charge additional service fees or online payment service.");
        lblDepositNote.setBounds(START_X, currentY, 500, 18);
        lblDepositNote.setFont(new Font("Arial", Font.PLAIN, 11));
        lblDepositNote.setForeground(new Color(120, 120, 120));
        contentPane.add(lblDepositNote);
        currentY += V_SPACING + 10;
        
        // --- Payment Methods Icons ---
        JLabel lblPaymentMethods = new JLabel("Payment methods:");
        lblPaymentMethods.setBounds(START_X, currentY, 120, 18);
        lblPaymentMethods.setFont(new Font("Arial", Font.PLAIN, 12));
        contentPane.add(lblPaymentMethods);
        
        String[] cardTypes = {"VISA", "MC", "AMEX", "JCB", "Discover", "UnionPay"};
        int iconX = START_X + 130;
        for (String card : cardTypes) {
            JLabel lblCard = new JLabel(card);
            lblCard.setBounds(iconX, currentY, 50, 18);
            lblCard.setFont(new Font("Arial", Font.BOLD, 8));
            lblCard.setForeground(Color.WHITE);
            lblCard.setBackground(new Color(50, 100, 180));
            lblCard.setOpaque(true);
            lblCard.setHorizontalAlignment(JLabel.CENTER);
            lblCard.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
            contentPane.add(lblCard);
            iconX += 60;
        }
        
        currentY += V_SPACING + 10;
        
        // --- Grey Form Panel ---
        final int FORM_PANEL_HEIGHT = 290;
        final int FORM_FIELD_WIDTH = 550; 
        
        // Anchor the form panel at START_X with MAIN_CONTENT_WIDTH
        JPanel formPanel = new JPanel();
        formPanel.setBounds(START_X, currentY, MAIN_CONTENT_WIDTH, FORM_PANEL_HEIGHT);
        formPanel.setBackground(new Color(245, 245, 245));
        formPanel.setLayout(null);
        contentPane.add(formPanel);
        
        // Internal form layout coordinates
        int formPaddingX = 40; 
        int formY = 20;
        
        JLabel lblChoosePayment = new JLabel("Choose payment method *");
        lblChoosePayment.setBounds(formPaddingX, formY, 200, 18);
        lblChoosePayment.setFont(new Font("Arial", Font.PLAIN, 12));
        lblChoosePayment.setForeground(new Color(60, 60, 60));
        formPanel.add(lblChoosePayment);
        formY += V_SPACING;
        
        JComboBox<String> cmbPaymentMethod = new JComboBox<>(new String[]{"Credit Card", "Debit Card"});
        cmbPaymentMethod.setBounds(formPaddingX, formY, FORM_FIELD_WIDTH, 35);
        cmbPaymentMethod.setFont(new Font("Arial", Font.PLAIN, 12));
        cmbPaymentMethod.setBackground(Color.WHITE);
        formPanel.add(cmbPaymentMethod);
        formY += V_SPACING + 20;
        
        JLabel lblCardNumber = new JLabel("Card Number *");
        lblCardNumber.setBounds(formPaddingX, formY, 200, 18);
        lblCardNumber.setFont(new Font("Arial", Font.PLAIN, 12));
        lblCardNumber.setForeground(new Color(60, 60, 60));
        formPanel.add(lblCardNumber);
        formY += V_SPACING;
        
        txtCardNumber = new JTextField();
        txtCardNumber.setBounds(formPaddingX, formY, FORM_FIELD_WIDTH, 35);
        formPanel.add(txtCardNumber);
        formY += V_SPACING + 20;
        
        JLabel lblCardHolderName = new JLabel("Card Holder's Name *");
        lblCardHolderName.setBounds(formPaddingX, formY, 200, 18);
        lblCardHolderName.setFont(new Font("Arial", Font.PLAIN, 12));
        lblCardHolderName.setForeground(new Color(60, 60, 60));
        formPanel.add(lblCardHolderName);
        formY += V_SPACING;
        
        txtCardHolderName = new JTextField();
        txtCardHolderName.setBounds(formPaddingX, formY, FORM_FIELD_WIDTH, 35);
        formPanel.add(txtCardHolderName);
        formY += V_SPACING + 20;
        
        JLabel lblExpiryDate = new JLabel("Expiry Date (Month / Year) *");
        lblExpiryDate.setBounds(formPaddingX, formY, 200, 18);
        lblExpiryDate.setFont(new Font("Arial", Font.PLAIN, 12));
        lblExpiryDate.setForeground(new Color(60, 60, 60));
        formPanel.add(lblExpiryDate);
        formY += V_SPACING;
        
        txtMonth = new JTextField("MM");
        txtMonth.setBounds(formPaddingX, formY, 80, 35);
        txtMonth.setHorizontalAlignment(JTextField.CENTER);
        formPanel.add(txtMonth);
        
        txtYear = new JTextField("YYYY");
        txtYear.setBounds(formPaddingX + 95, formY, 120, 35);
        txtYear.setHorizontalAlignment(JTextField.CENTER);
        formPanel.add(txtYear);
        
        currentY += FORM_PANEL_HEIGHT + SECTION_GAP_Y;
        
        // --- Submit Button ---
        final int BUTTON_WIDTH = 180;
        final int BUTTON_HEIGHT = 45;
        JButton btnSubmit = new JButton("Submit");
        // Center button relative to the frame's dynamic screen width
        btnSubmit.setBounds((screenWidth - BUTTON_WIDTH) / 2, currentY, BUTTON_WIDTH, BUTTON_HEIGHT); 
        btnSubmit.setFont(new Font("Arial", Font.BOLD, 14));
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setBackground(PRIMARY_ACCENT);
        btnSubmit.setBorder(BorderFactory.createLineBorder(PRIMARY_ACCENT));
        btnSubmit.setFocusPainted(false);
        btnSubmit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // OPEN CONFIRMATION PAGE LISTENER
        btnSubmit.addActionListener(e -> {
            // Basic Validation
            if (txtCardNumber.getText().isEmpty() || txtCardHolderName.getText().isEmpty() || 
                txtMonth.getText().isEmpty() || txtYear.getText().isEmpty() ||
                txtMonth.getText().equals("MM") || txtYear.getText().equals("YYYY")) {
                JOptionPane.showMessageDialog(this, "Please fill in all payment details.", "Missing Details", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Open Confirmation Page
            ConfirmationSummaryPage confirmPage = new ConfirmationSummaryPage(this.checkout);
            // It is recommended to set new pages to be maximized as well for consistency
            confirmPage.setExtendedState(JFrame.MAXIMIZED_BOTH);
            confirmPage.setVisible(true);
            
            // Close Payment Page
            this.dispose();
        });
        
        contentPane.add(btnSubmit);
    }
}