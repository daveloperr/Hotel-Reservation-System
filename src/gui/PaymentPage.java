package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class PaymentPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCardNumber;
	private JTextField txtCardHolderName;
	private JTextField txtMonth;
	private JTextField txtYear;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaymentPage frame = new PaymentPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PaymentPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(null);
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		
		// Top navigation
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
		
		// Notice banner
		JPanel noticePanel = new JPanel();
		noticePanel.setBounds(0, 35, 1100, 30);
		noticePanel.setBackground(new Color(240, 240, 240));
		noticePanel.setLayout(null);
		contentPane.add(noticePanel);
		
		JLabel lblNotice = new JLabel("The reservation will be closed in next");
		lblNotice.setBounds(400, 5, 300, 20);
		lblNotice.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNotice.setForeground(new Color(100, 100, 100));
		noticePanel.add(lblNotice);
		
		// Back button
		JLabel lblBack = new JLabel("< Back");
		lblBack.setBounds(30, 85, 60, 20);
		lblBack.setFont(new Font("Arial", Font.PLAIN, 12));
		lblBack.setForeground(new Color(100, 100, 100));
		lblBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(lblBack);
		
		// Stay Summary title
		JLabel lblStaySummary = new JLabel("Stay Summary: Makati Shangri-La, Manila");
		lblStaySummary.setBounds(140, 84, 400, 20);
		lblStaySummary.setFont(new Font("Arial", Font.BOLD, 14));
		lblStaySummary.setForeground(new Color(0, 0, 0));
		contentPane.add(lblStaySummary);
		
		// Room image
		JLabel lblRoomImage = new JLabel();
		lblRoomImage.setBounds(113, 116, 143, 108);
		lblRoomImage.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
		
		// Load and scale the deluxe room image
		try {
			ImageIcon roomIcon = new ImageIcon("images/deluxeroom.png");
			Image scaledImage = roomIcon.getImage().getScaledInstance(143, 108, Image.SCALE_SMOOTH);
			lblRoomImage.setIcon(new ImageIcon(scaledImage));
		} catch (Exception e) {
			lblRoomImage.setBackground(new Color(200, 180, 140));
			lblRoomImage.setOpaque(true);
		}
		
		contentPane.add(lblRoomImage);
		
		// Booking details
		JLabel lblDate = new JLabel("Thu, 11 Dec 2025 / Fri, 12 Dec 2025 (1 night)");
		lblDate.setBounds(270, 120, 350, 18);
		lblDate.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDate.setForeground(new Color(60, 60, 60));
		contentPane.add(lblDate);
		
		JLabel lblRoomsGuests = new JLabel("Rooms & Guests:  1 Room, 1 Adult, 0 Children     Room Type:  Deluxe Room");
		lblRoomsGuests.setBounds(270, 140, 450, 18);
		lblRoomsGuests.setFont(new Font("Arial", Font.PLAIN, 11));
		lblRoomsGuests.setForeground(new Color(60, 60, 60));
		contentPane.add(lblRoomsGuests);
		
		JLabel lblRateName = new JLabel("Rate Name:  Members Online Saver Rate     Reservation Policy:  Non-refundable");
		lblRateName.setBounds(270, 160, 500, 18);
		lblRateName.setFont(new Font("Arial", Font.PLAIN, 11));
		lblRateName.setForeground(new Color(60, 60, 60));
		contentPane.add(lblRateName);
		
		// Amount panel
		JPanel amountPanel = new JPanel();
		amountPanel.setBounds(650, 120, 200, 60);
		amountPanel.setBackground(Color.WHITE);
		amountPanel.setLayout(null);
		contentPane.add(amountPanel);
		
		JLabel lblAmountLabel = new JLabel("Amount to Pay Now:");
		lblAmountLabel.setBounds(0, 0, 200, 18);
		lblAmountLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		lblAmountLabel.setForeground(new Color(100, 100, 100));
		lblAmountLabel.setHorizontalAlignment(JLabel.RIGHT);
		amountPanel.add(lblAmountLabel);
		
		JLabel lblAmount = new JLabel("PHP 11,647");
		lblAmount.setBounds(0, 18, 200, 25);
		lblAmount.setFont(new Font("Arial", Font.BOLD, 18));
		lblAmount.setForeground(new Color(0, 0, 0));
		lblAmount.setHorizontalAlignment(JLabel.RIGHT);
		amountPanel.add(lblAmount);
		
		JLabel lblTaxNote = new JLabel("+ Inclusive of Service Charge and Tax");
		lblTaxNote.setBounds(0, 43, 200, 15);
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
		
		// Advance Deposit Payment section
		JLabel lblAdvanceDeposit = new JLabel("Advance Deposit Payment");
		lblAdvanceDeposit.setBounds(140, 270, 250, 20);
		lblAdvanceDeposit.setFont(new Font("Arial", Font.BOLD, 14));
		lblAdvanceDeposit.setForeground(new Color(0, 0, 0));
		contentPane.add(lblAdvanceDeposit);
		
		JLabel lblDepositNote = new JLabel("Note: Some banks may charge additional service fees or online payment service.");
		lblDepositNote.setBounds(140, 295, 500, 18);
		lblDepositNote.setFont(new Font("Arial", Font.PLAIN, 11));
		lblDepositNote.setForeground(new Color(120, 120, 120));
		contentPane.add(lblDepositNote);
		
		// Payment methods label
		JLabel lblPaymentMethods = new JLabel("Payment methods:");
		lblPaymentMethods.setBounds(140, 325, 120, 18);
		lblPaymentMethods.setFont(new Font("Arial", Font.PLAIN, 12));
		lblPaymentMethods.setForeground(new Color(0, 0, 0));
		contentPane.add(lblPaymentMethods);
		
		// Payment method icons (using colored labels as placeholders)
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
		
		// Grey background panel for form
		JPanel formPanel = new JPanel();
		formPanel.setBounds(90, 355, 730, 290);
		formPanel.setBackground(new Color(245, 245, 245));
		formPanel.setLayout(null);
		contentPane.add(formPanel);
		
		// Choose payment method
		JLabel lblChoosePayment = new JLabel("Choose payment method *");
		lblChoosePayment.setBounds(60, 10, 200, 18);
		lblChoosePayment.setFont(new Font("Arial", Font.PLAIN, 12));
		lblChoosePayment.setForeground(new Color(60, 60, 60));
		formPanel.add(lblChoosePayment);
		
		JComboBox<String> cmbPaymentMethod = new JComboBox<>(new String[]{"Select"});
		cmbPaymentMethod.setBounds(60, 30, 435, 35);
		cmbPaymentMethod.setFont(new Font("Arial", Font.PLAIN, 12));
		cmbPaymentMethod.setBackground(Color.WHITE);
		formPanel.add(cmbPaymentMethod);
		
		// Card Number
		JLabel lblCardNumber = new JLabel("Card Number *");
		lblCardNumber.setBounds(60, 80, 200, 18);
		lblCardNumber.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCardNumber.setForeground(new Color(60, 60, 60));
		formPanel.add(lblCardNumber);
		
		txtCardNumber = new JTextField();
		txtCardNumber.setBounds(60, 100, 435, 35);
		txtCardNumber.setFont(new Font("Arial", Font.PLAIN, 12));
		txtCardNumber.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(new Color(200, 200, 200)),
			BorderFactory.createEmptyBorder(5, 10, 5, 10)
		));
		formPanel.add(txtCardNumber);
		
		// Card Holder's Name
		JLabel lblCardHolderName = new JLabel("Card Holder's Name *");
		lblCardHolderName.setBounds(60, 150, 200, 18);
		lblCardHolderName.setFont(new Font("Arial", Font.PLAIN, 12));
		lblCardHolderName.setForeground(new Color(60, 60, 60));
		formPanel.add(lblCardHolderName);
		
		txtCardHolderName = new JTextField();
		txtCardHolderName.setBounds(60, 170, 435, 35);
		txtCardHolderName.setFont(new Font("Arial", Font.PLAIN, 12));
		txtCardHolderName.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(new Color(200, 200, 200)),
			BorderFactory.createEmptyBorder(5, 10, 5, 10)
		));
		formPanel.add(txtCardHolderName);
		
		// Expiry Date
		JLabel lblExpiryDate = new JLabel("Expiry Date (Month / Year) *");
		lblExpiryDate.setBounds(60, 220, 200, 18);
		lblExpiryDate.setFont(new Font("Arial", Font.PLAIN, 12));
		lblExpiryDate.setForeground(new Color(60, 60, 60));
		formPanel.add(lblExpiryDate);
		
		txtMonth = new JTextField("MM");
		txtMonth.setBounds(60, 240, 80, 35);
		txtMonth.setFont(new Font("Arial", Font.PLAIN, 12));
		txtMonth.setForeground(new Color(150, 150, 150));
		txtMonth.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(new Color(200, 200, 200)),
			BorderFactory.createEmptyBorder(5, 10, 5, 10)
		));
		formPanel.add(txtMonth);
		
		txtYear = new JTextField("YYYY");
		txtYear.setBounds(155, 240, 120, 35);
		txtYear.setFont(new Font("Arial", Font.PLAIN, 12));
		txtYear.setForeground(new Color(150, 150, 150));
		txtYear.setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder(new Color(200, 200, 200)),
			BorderFactory.createEmptyBorder(5, 10, 5, 10)
		));
		formPanel.add(txtYear);
		
		// Submit button
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(380, 655, 160, 40);
		btnSubmit.setFont(new Font("Arial", Font.BOLD, 14));
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setBackground(new Color(218, 165, 32));
		btnSubmit.setBorder(BorderFactory.createLineBorder(new Color(218, 165, 32)));
		btnSubmit.setFocusPainted(false);
		btnSubmit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnSubmit);

	}

}
