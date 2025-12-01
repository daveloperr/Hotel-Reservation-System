package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class BookingPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookingPage frame = new BookingPage();
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
	public BookingPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		
		// Search Panel
		JPanel searchPanel = new JPanel();
		searchPanel.setBounds(0, 0, 1100, 50);
		searchPanel.setBackground(new Color(250, 250, 250));
		searchPanel.setLayout(null);
		searchPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
		contentPane.add(searchPanel);
		
		JTextField txtLocation = new JTextField("Makati Shangri-La, Manila");
		txtLocation.setBounds(15, 12, 200, 25);
		txtLocation.setFont(new Font("Arial", Font.PLAIN, 12));
		txtLocation.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
		searchPanel.add(txtLocation);
		
		JLabel lblCheckIn = new JLabel("Dec 9, 2025");
		lblCheckIn.setBounds(230, 12, 100, 25);
		lblCheckIn.setFont(new Font("Arial", Font.PLAIN, 12));
		searchPanel.add(lblCheckIn);
		
		JLabel lblNight = new JLabel("1 night");
		lblNight.setBounds(345, 12, 60, 25);
		lblNight.setFont(new Font("Arial", Font.PLAIN, 12));
		searchPanel.add(lblNight);
		
		JLabel lblCheckOut = new JLabel("Dec 10, 2025");
		lblCheckOut.setBounds(420, 12, 100, 25);
		lblCheckOut.setFont(new Font("Arial", Font.PLAIN, 12));
		searchPanel.add(lblCheckOut);
		
		JLabel lblGuests = new JLabel("1 Room, 2 Adults, 0 Children");
		lblGuests.setBounds(535, 12, 190, 25);
		lblGuests.setFont(new Font("Arial", Font.PLAIN, 12));
		searchPanel.add(lblGuests);
		
		JTextField txtSpecialCode = new JTextField();
		txtSpecialCode.setBounds(740, 12, 110, 25);
		txtSpecialCode.setFont(new Font("Arial", Font.PLAIN, 12));
		txtSpecialCode.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
		txtSpecialCode.setForeground(new Color(150, 150, 150));
		txtSpecialCode.setText("Special Code");
		searchPanel.add(txtSpecialCode);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(920, 8, 150, 34);
		btnSearch.setBackground(new Color(218, 165, 32));
		btnSearch.setForeground(Color.BLACK);
		btnSearch.setFont(new Font("Arial", Font.BOLD, 13));
		btnSearch.setFocusPainted(false);
		searchPanel.add(btnSearch);
		
		// Package Info Panel
		JPanel packagePanel = new JPanel();
		packagePanel.setBounds(0, 50, 1100, 80);
		packagePanel.setBackground(new Color(248, 245, 240));
		packagePanel.setLayout(null);
		packagePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
		contentPane.add(packagePanel);
		
		JLabel lblStandard = new JLabel("Standard - 21 Offer(s)");
		lblStandard.setBounds(250, 15, 200, 20);
		lblStandard.setFont(new Font("Arial", Font.BOLD, 14));
		packagePanel.add(lblStandard);
		
		JLabel lblLowest = new JLabel("Lowest Available");
		lblLowest.setBounds(250, 35, 150, 18);
		lblLowest.setFont(new Font("Arial", Font.PLAIN, 11));
		lblLowest.setForeground(new Color(100, 100, 100));
		packagePanel.add(lblLowest);
		
		JLabel lblStandardPrice = new JLabel("From PHP 8,700 per night");
		lblStandardPrice.setBounds(250, 53, 200, 18);
		lblStandardPrice.setFont(new Font("Arial", Font.PLAIN, 11));
		lblStandardPrice.setForeground(new Color(80, 80, 80));
		packagePanel.add(lblStandardPrice);
		
		JLabel lblPackages = new JLabel("Packages - 10 Offer(s)");
		lblPackages.setBounds(650, 15, 200, 20);
		lblPackages.setFont(new Font("Arial", Font.BOLD, 14));
		packagePanel.add(lblPackages);
		
		JLabel lblSpecialSavings = new JLabel("Special Savings");
		lblSpecialSavings.setBounds(650, 35, 150, 18);
		lblSpecialSavings.setFont(new Font("Arial", Font.PLAIN, 11));
		lblSpecialSavings.setForeground(new Color(100, 100, 100));
		packagePanel.add(lblSpecialSavings);
		
		JLabel lblPackagePrice = new JLabel("From PHP 10,100 per night");
		lblPackagePrice.setBounds(650, 53, 200, 18);
		lblPackagePrice.setFont(new Font("Arial", Font.PLAIN, 11));
		lblPackagePrice.setForeground(new Color(80, 80, 80));
		packagePanel.add(lblPackagePrice);
		
		// Tabs Panel
		JPanel tabsPanel = new JPanel();
		tabsPanel.setBounds(0, 130, 1100, 45);
		tabsPanel.setBackground(Color.WHITE);
		tabsPanel.setLayout(null);
		tabsPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));
		contentPane.add(tabsPanel);
		
		JButton btnRoomType = new JButton("Room Type:");
		btnRoomType.setBounds(20, 10, 95, 28);
		btnRoomType.setFont(new Font("Arial", Font.PLAIN, 11));
		btnRoomType.setBackground(Color.WHITE);
		btnRoomType.setFocusPainted(false);
		tabsPanel.add(btnRoomType);
		
		JButton btnRoom = new JButton("Room");
		btnRoom.setBounds(120, 10, 80, 28);
		btnRoom.setFont(new Font("Arial", Font.PLAIN, 11));
		btnRoom.setBackground(new Color(240, 240, 240));
		btnRoom.setFocusPainted(false);
		tabsPanel.add(btnRoom);
		
		JButton btnClubRoom = new JButton("Club Room");
		btnClubRoom.setBounds(205, 10, 100, 28);
		btnClubRoom.setFont(new Font("Arial", Font.PLAIN, 11));
		btnClubRoom.setBackground(Color.WHITE);
		btnClubRoom.setFocusPainted(false);
		tabsPanel.add(btnClubRoom);
		
		JButton btnSuite = new JButton("Suite");
		btnSuite.setBounds(310, 10, 70, 28);
		btnSuite.setFont(new Font("Arial", Font.PLAIN, 11));
		btnSuite.setBackground(Color.WHITE);
		btnSuite.setFocusPainted(false);
		tabsPanel.add(btnSuite);
		
		JButton btnConnecting = new JButton("Connecting Room");
		btnConnecting.setBounds(385, 10, 135, 28);
		btnConnecting.setFont(new Font("Arial", Font.PLAIN, 11));
		btnConnecting.setBackground(Color.WHITE);
		btnConnecting.setFocusPainted(false);
		tabsPanel.add(btnConnecting);
		
		JButton btnBookingPolicy = new JButton("Booking Policy:");
		btnBookingPolicy.setBounds(530, 10, 115, 28);
		btnBookingPolicy.setFont(new Font("Arial", Font.PLAIN, 11));
		btnBookingPolicy.setBackground(Color.WHITE);
		btnBookingPolicy.setFocusPainted(false);
		tabsPanel.add(btnBookingPolicy);
		
		JButton btnPayAtProperty = new JButton("Pay at property");
		btnPayAtProperty.setBounds(650, 10, 115, 28);
		btnPayAtProperty.setFont(new Font("Arial", Font.PLAIN, 11));
		btnPayAtProperty.setBackground(Color.WHITE);
		btnPayAtProperty.setFocusPainted(false);
		tabsPanel.add(btnPayAtProperty);
		
		JButton btnPayOnline = new JButton("Pay online");
		btnPayOnline.setBounds(770, 10, 90, 28);
		btnPayOnline.setFont(new Font("Arial", Font.PLAIN, 11));
		btnPayOnline.setBackground(Color.WHITE);
		btnPayOnline.setFocusPainted(false);
		tabsPanel.add(btnPayOnline);
		
		JButton btnSeeMore = new JButton("See More Filters ∨");
		btnSeeMore.setBounds(870, 10, 140, 28);
		btnSeeMore.setFont(new Font("Arial", Font.PLAIN, 11));
		btnSeeMore.setBackground(Color.WHITE);
		btnSeeMore.setFocusPainted(false);
		tabsPanel.add(btnSeeMore);
		
		// Header Labels
		JLabel lblRoomTypeHeader = new JLabel("Room Type");
		lblRoomTypeHeader.setBounds(20, 185, 150, 20);
		lblRoomTypeHeader.setFont(new Font("Arial", Font.BOLD, 12));
		lblRoomTypeHeader.setForeground(new Color(100, 100, 100));
		contentPane.add(lblRoomTypeHeader);
		
		JLabel lblRatePlanHeader = new JLabel("Rate Plan");
		lblRatePlanHeader.setBounds(400, 185, 150, 20);
		lblRatePlanHeader.setFont(new Font("Arial", Font.BOLD, 12));
		lblRatePlanHeader.setForeground(new Color(100, 100, 100));
		contentPane.add(lblRatePlanHeader);
		
		JLabel lblAveragePriceHeader = new JLabel("Average per night");
		lblAveragePriceHeader.setBounds(840, 185, 150, 20);
		lblAveragePriceHeader.setFont(new Font("Arial", Font.BOLD, 12));
		lblAveragePriceHeader.setForeground(new Color(100, 100, 100));
		contentPane.add(lblAveragePriceHeader);
		
		// Room Listing Panel
		JPanel roomPanel = new JPanel();
		roomPanel.setBounds(10, 215, 1070, 520);
		roomPanel.setBackground(Color.WHITE);
		roomPanel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
		roomPanel.setLayout(null);
		contentPane.add(roomPanel);
		
		// Room Image
		JLabel lblRoomImage = new JLabel();
		lblRoomImage.setBounds(10, 10, 180, 120);
		lblRoomImage.setBackground(new Color(200, 200, 200));
		lblRoomImage.setOpaque(true);
		lblRoomImage.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
		
		// Load and scale the deluxe room image
		try {
			ImageIcon originalIcon = new ImageIcon("images/deluxeroom.png");
			Image scaledImage = originalIcon.getImage().getScaledInstance(180, 120, Image.SCALE_SMOOTH);
			lblRoomImage.setIcon(new ImageIcon(scaledImage));
		} catch (Exception e) {
			System.out.println("Could not load room image: " + e.getMessage());
		}
		
		roomPanel.add(lblRoomImage);
		
		JLabel lblRecommended = new JLabel("✓ Recommended");
		lblRecommended.setBounds(15, 15, 110, 22);
		lblRecommended.setForeground(Color.WHITE);
		lblRecommended.setBackground(new Color(218, 165, 32));
		lblRecommended.setOpaque(true);
		lblRecommended.setFont(new Font("Arial", Font.BOLD, 10));
		lblRecommended.setHorizontalAlignment(JLabel.CENTER);
		roomPanel.add(lblRecommended);
		
		// Room Details
		JLabel lblDeluxeRoom = new JLabel("Deluxe Room");
		lblDeluxeRoom.setBounds(10, 140, 180, 22);
		lblDeluxeRoom.setFont(new Font("Arial", Font.BOLD, 14));
		lblDeluxeRoom.setForeground(new Color(139, 69, 19));
		roomPanel.add(lblDeluxeRoom);
		
		JLabel lblRoomSize = new JLabel("33-42 sqm / 355-452 sq²");
		lblRoomSize.setBounds(10, 162, 180, 18);
		lblRoomSize.setFont(new Font("Arial", Font.PLAIN, 11));
		lblRoomSize.setForeground(new Color(100, 100, 100));
		roomPanel.add(lblRoomSize);
		
		JButton btnRoomDetails = new JButton("Room Details");
		btnRoomDetails.setBounds(10, 185, 120, 25);
		btnRoomDetails.setForeground(new Color(139, 69, 19));
		btnRoomDetails.setFont(new Font("Arial", Font.BOLD, 12));
		btnRoomDetails.setContentAreaFilled(false);
		btnRoomDetails.setBorderPainted(false);
		btnRoomDetails.setFocusPainted(false);
		btnRoomDetails.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		roomPanel.add(btnRoomDetails);
		
		// Divider line
		JPanel divider1 = new JPanel();
		divider1.setBounds(200, 10, 1, 500);
		divider1.setBackground(new Color(230, 230, 230));
		roomPanel.add(divider1);
		
		// Rate Plan 1 - Members Online Saver Rate (King)
		JPanel ratePlan1 = new JPanel();
		ratePlan1.setBounds(210, 10, 850, 145);
		ratePlan1.setBackground(Color.WHITE);
		ratePlan1.setLayout(null);
		ratePlan1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
		roomPanel.add(ratePlan1);
		
		JLabel lblBadge1 = new JLabel("");
		lblBadge1.setBounds(5, 5, 25, 25);
		lblBadge1.setFont(new Font("Arial", Font.PLAIN, 18));
		ratePlan1.add(lblBadge1);
		
		JLabel lblRate1 = new JLabel("Members Online Saver Rate (King)");
		lblRate1.setBounds(35, 5, 280, 22);
		lblRate1.setFont(new Font("Arial", Font.BOLD, 13));
		ratePlan1.add(lblRate1);
		
		JPanel exclusivePanel1 = new JPanel();
		exclusivePanel1.setBounds(35, 30, 170, 20);
		exclusivePanel1.setBackground(new Color(255, 248, 220));
		exclusivePanel1.setBorder(BorderFactory.createLineBorder(new Color(218, 165, 32)));
		JLabel lblExclusive1 = new JLabel("Members Online Exclusive");
		lblExclusive1.setFont(new Font("Arial", Font.PLAIN, 10));
		lblExclusive1.setForeground(new Color(139, 69, 19));
		exclusivePanel1.add(lblExclusive1);
		ratePlan1.add(exclusivePanel1);
		
		JLabel lblPayOnline1 = new JLabel("⊕ Pay online");
		lblPayOnline1.setBounds(35, 55, 100, 18);
		lblPayOnline1.setFont(new Font("Arial", Font.PLAIN, 11));
		lblPayOnline1.setForeground(new Color(218, 165, 32));
		ratePlan1.add(lblPayOnline1);
		
		JLabel lblNonRefundable1 = new JLabel("○ Non-refundable ⓘ");
		lblNonRefundable1.setBounds(35, 75, 150, 18);
		lblNonRefundable1.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNonRefundable1.setForeground(new Color(100, 100, 100));
		ratePlan1.add(lblNonRefundable1);
		
		JLabel lblBedType1 = new JLabel("1 King");
		lblBedType1.setBounds(35, 100, 60, 20);
		lblBedType1.setFont(new Font("Arial", Font.PLAIN, 11));
		lblBedType1.setForeground(Color.WHITE);
		lblBedType1.setBackground(new Color(218, 165, 32));
		lblBedType1.setOpaque(true);
		lblBedType1.setHorizontalAlignment(JLabel.CENTER);
		ratePlan1.add(lblBedType1);
		
		JLabel lblRate1Details = new JLabel("⊞ 1/4 / Night ∨");
		lblRate1Details.setBounds(100, 100, 120, 20);
		lblRate1Details.setFont(new Font("Arial", Font.PLAIN, 11));
		lblRate1Details.setForeground(new Color(139, 69, 19));
		ratePlan1.add(lblRate1Details);
		
		JButton btnRateDetails1 = new JButton("Rate Details");
		btnRateDetails1.setBounds(230, 97, 127, 22);
		btnRateDetails1.setForeground(new Color(139, 69, 19));
		btnRateDetails1.setFont(new Font("Arial", Font.PLAIN, 11));
		btnRateDetails1.setContentAreaFilled(false);
		btnRateDetails1.setBorderPainted(false);
		btnRateDetails1.setFocusPainted(false);
		ratePlan1.add(btnRateDetails1);
		
		JLabel lblOriginalPrice1 = new JLabel("PHP 9,700");
		lblOriginalPrice1.setBounds(540, 8, 80, 20);
		lblOriginalPrice1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblOriginalPrice1.setForeground(new Color(150, 150, 150));
		ratePlan1.add(lblOriginalPrice1);
		
		JLabel lblDiscountPrice1 = new JLabel("PHP 8,700");
		lblDiscountPrice1.setBounds(630, 5, 110, 28);
		lblDiscountPrice1.setFont(new Font("Arial", Font.BOLD, 20));
		ratePlan1.add(lblDiscountPrice1);
		
		JButton btnBookNow1 = new JButton("Book Now");
		btnBookNow1.setBounds(730, 50, 110, 35);
		btnBookNow1.setBackground(new Color(218, 165, 32));
		btnBookNow1.setForeground(Color.BLACK);
		btnBookNow1.setFont(new Font("Arial", Font.BOLD, 12));
		btnBookNow1.setFocusPainted(false);
		ratePlan1.add(btnBookNow1);
		
		// Rate Plan 2 - Members Online Saver Rate (Twin)
		JPanel ratePlan2 = new JPanel();
		ratePlan2.setBounds(210, 165, 850, 145);
		ratePlan2.setBackground(Color.WHITE);
		ratePlan2.setLayout(null);
		ratePlan2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
		roomPanel.add(ratePlan2);
		
		JLabel lblBadge2 = new JLabel("");
		lblBadge2.setBounds(5, 5, 25, 25);
		lblBadge2.setFont(new Font("Arial", Font.PLAIN, 18));
		ratePlan2.add(lblBadge2);
		
		JLabel lblRate2 = new JLabel("Members Online Saver Rate (Twin)");
		lblRate2.setBounds(35, 5, 280, 22);
		lblRate2.setFont(new Font("Arial", Font.BOLD, 13));
		ratePlan2.add(lblRate2);
		
		JPanel exclusivePanel2 = new JPanel();
		exclusivePanel2.setBounds(35, 30, 170, 20);
		exclusivePanel2.setBackground(new Color(255, 248, 220));
		exclusivePanel2.setBorder(BorderFactory.createLineBorder(new Color(218, 165, 32)));
		JLabel lblExclusive2 = new JLabel("Members Online Exclusive");
		lblExclusive2.setFont(new Font("Arial", Font.PLAIN, 10));
		lblExclusive2.setForeground(new Color(139, 69, 19));
		exclusivePanel2.add(lblExclusive2);
		ratePlan2.add(exclusivePanel2);
		
		JLabel lblPayOnline2 = new JLabel("⊕ Pay online");
		lblPayOnline2.setBounds(35, 55, 100, 18);
		lblPayOnline2.setFont(new Font("Arial", Font.PLAIN, 11));
		lblPayOnline2.setForeground(new Color(218, 165, 32));
		ratePlan2.add(lblPayOnline2);
		
		JLabel lblNonRefundable2 = new JLabel("○ Non-refundable ⓘ");
		lblNonRefundable2.setBounds(35, 75, 150, 18);
		lblNonRefundable2.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNonRefundable2.setForeground(new Color(100, 100, 100));
		ratePlan2.add(lblNonRefundable2);
		
		JLabel lblBedType2 = new JLabel("1 King");
		lblBedType2.setBounds(35, 100, 60, 20);
		lblBedType2.setFont(new Font("Arial", Font.PLAIN, 11));
		lblBedType2.setForeground(Color.WHITE);
		lblBedType2.setBackground(new Color(218, 165, 32));
		lblBedType2.setOpaque(true);
		lblBedType2.setHorizontalAlignment(JLabel.CENTER);
		ratePlan2.add(lblBedType2);
		
		JLabel lblRate2Details = new JLabel("⊞ 1/4 / Night ∨");
		lblRate2Details.setBounds(100, 100, 120, 20);
		lblRate2Details.setFont(new Font("Arial", Font.PLAIN, 11));
		lblRate2Details.setForeground(new Color(139, 69, 19));
		ratePlan2.add(lblRate2Details);
		
		JButton btnRateDetails2 = new JButton("Rate Details");
		btnRateDetails2.setBounds(230, 97, 120, 22);
		btnRateDetails2.setForeground(new Color(139, 69, 19));
		btnRateDetails2.setFont(new Font("Arial", Font.PLAIN, 11));
		btnRateDetails2.setContentAreaFilled(false);
		btnRateDetails2.setBorderPainted(false);
		btnRateDetails2.setFocusPainted(false);
		ratePlan2.add(btnRateDetails2);
		
		JLabel lblOriginalPrice2 = new JLabel("PHP 9,700");
		lblOriginalPrice2.setBounds(540, 8, 80, 20);
		lblOriginalPrice2.setFont(new Font("Arial", Font.PLAIN, 12));
		lblOriginalPrice2.setForeground(new Color(150, 150, 150));
		ratePlan2.add(lblOriginalPrice2);
		
		JLabel lblDiscountPrice2 = new JLabel("PHP 8,700");
		lblDiscountPrice2.setBounds(630, 5, 110, 28);
		lblDiscountPrice2.setFont(new Font("Arial", Font.BOLD, 20));
		ratePlan2.add(lblDiscountPrice2);
		
		JButton btnBookNow2 = new JButton("Book Now");
		btnBookNow2.setBounds(730, 50, 110, 35);
		btnBookNow2.setBackground(new Color(218, 165, 32));
		btnBookNow2.setForeground(Color.BLACK);
		btnBookNow2.setFont(new Font("Arial", Font.BOLD, 12));
		btnBookNow2.setFocusPainted(false);
		ratePlan2.add(btnBookNow2);
		
		// Rate Plan 3 - Members Online Exclusive Rate (King)
		JPanel ratePlan3 = new JPanel();
		ratePlan3.setBounds(210, 320, 850, 145);
		ratePlan3.setBackground(Color.WHITE);
		ratePlan3.setLayout(null);
		roomPanel.add(ratePlan3);
		
		JLabel lblBadge3 = new JLabel("");
		lblBadge3.setBounds(5, 5, 25, 25);
		lblBadge3.setFont(new Font("Arial", Font.PLAIN, 18));
		ratePlan3.add(lblBadge3);
		
		JLabel lblRate3 = new JLabel("Members Online Exclusive Rate (King)");
		lblRate3.setBounds(35, 5, 300, 22);
		lblRate3.setFont(new Font("Arial", Font.BOLD, 13));
		ratePlan3.add(lblRate3);
		
		JLabel lblPayOnline3 = new JLabel("⊕ Pay online");
		lblPayOnline3.setBounds(35, 30, 100, 18);
		lblPayOnline3.setFont(new Font("Arial", Font.PLAIN, 11));
		lblPayOnline3.setForeground(new Color(218, 165, 32));
		ratePlan3.add(lblPayOnline3);
		
		JLabel lblNonRefundable3 = new JLabel("○ Non-refundable ⓘ");
		lblNonRefundable3.setBounds(35, 50, 150, 18);
		lblNonRefundable3.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNonRefundable3.setForeground(new Color(100, 100, 100));
		ratePlan3.add(lblNonRefundable3);
		
		JLabel lblBedType3 = new JLabel("1 King");
		lblBedType3.setBounds(35, 75, 60, 20);
		lblBedType3.setFont(new Font("Arial", Font.PLAIN, 11));
		lblBedType3.setForeground(Color.WHITE);
		lblBedType3.setBackground(new Color(218, 165, 32));
		lblBedType3.setOpaque(true);
		lblBedType3.setHorizontalAlignment(JLabel.CENTER);
		ratePlan3.add(lblBedType3);
		
		JLabel lblRate3Details = new JLabel("⊞ 1/4 / Night ∨");
		lblRate3Details.setBounds(100, 75, 120, 20);
		lblRate3Details.setFont(new Font("Arial", Font.PLAIN, 11));
		lblRate3Details.setForeground(new Color(139, 69, 19));
		ratePlan3.add(lblRate3Details);
		
		JButton btnRateDetails3 = new JButton("Rate Details");
		btnRateDetails3.setBounds(230, 72, 130, 22);
		btnRateDetails3.setForeground(new Color(139, 69, 19));
		btnRateDetails3.setFont(new Font("Arial", Font.PLAIN, 11));
		btnRateDetails3.setContentAreaFilled(false);
		btnRateDetails3.setBorderPainted(false);
		btnRateDetails3.setFocusPainted(false);
		ratePlan3.add(btnRateDetails3);
		
		JLabel lblOriginalPrice3 = new JLabel("PHP 9,700");
		lblOriginalPrice3.setBounds(540, 8, 80, 20);
		lblOriginalPrice3.setFont(new Font("Arial", Font.PLAIN, 12));
		lblOriginalPrice3.setForeground(new Color(150, 150, 150));
		ratePlan3.add(lblOriginalPrice3);
		
		JLabel lblDiscountPrice3 = new JLabel("PHP 8,800");
		lblDiscountPrice3.setBounds(630, 5, 110, 28);
		lblDiscountPrice3.setFont(new Font("Arial", Font.BOLD, 20));
		ratePlan3.add(lblDiscountPrice3);
		
		JButton btnBookNow3 = new JButton("Book Now");
		btnBookNow3.setBounds(730, 50, 110, 35);
		btnBookNow3.setBackground(new Color(218, 165, 32));
		btnBookNow3.setForeground(Color.BLACK);
		btnBookNow3.setFont(new Font("Arial", Font.BOLD, 12));
		btnBookNow3.setFocusPainted(false);
		ratePlan3.add(btnBookNow3);
		
		// Chat button
		JButton btnChat = new JButton("Chat");
		btnChat.setBounds(1010, 720, 60, 60);
		btnChat.setBackground(new Color(218, 165, 32));
		btnChat.setFont(new Font("Arial", Font.PLAIN, 24));
		btnChat.setFocusPainted(false);
		btnChat.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
		contentPane.add(btnChat);
		
		JLabel lblChatLabel = new JLabel("Shangri-La Chat");
		lblChatLabel.setBounds(920, 765, 100, 15);
		lblChatLabel.setFont(new Font("Arial", Font.PLAIN, 10));
		lblChatLabel.setForeground(new Color(100, 100, 100));
		contentPane.add(lblChatLabel);

	}

}
