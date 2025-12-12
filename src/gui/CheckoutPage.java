package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import models.Room;
import models.Checkout;

public class CheckoutPage extends JFrame {

    private static final long serialVersionUID = 1L;
    
    private Room selectedRoom;
    private Checkout checkout; 
    private String checkInDate;
    private String checkOutDate;
    private int numberOfRooms;
    private int numberOfAdults;
    private int numberOfChildren;
    private boolean isMember;
    private JLabel chargesTotalLabel;
    private double addonsTotal = 0;
    private JLabel extraValue;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JComboBox<String> countryCombo;
    private JTextField iddCodeField;
    private JTextField mobileNumberField;
    private JCheckBox smsCheck;


    private static final Color COLOR_PANEL_BACKGROUND = new Color(245, 245, 245);
    private static final Color COLOR_PRIMARY_ACTION = new Color(240, 180, 50);
    private static final Color PRIMARY_ACTION_COLOR = new Color(240, 180, 50);
    private static final Color COLOR_TEXT_DEFAULT = new Color(50, 50, 50);
    private static final Color BOX_GRAY_COLOR = new Color(245, 245, 245);
    private static final Color COLOR_SECONDARY_BUTTON = Color.WHITE;
    private static final Color SECONDARY_BUTTON_COLOR = Color.WHITE;
    private static final Color COLOR_GOLD = new Color(255, 215, 0);
    private static final Color TEXT_COLOR = new Color(50, 50, 50);
    private static final Color ERROR_COLOR = new Color(255, 0, 0);
    private static final Color COLOR_ERROR = new Color(255, 0, 0);
    private static final Color LINK_COLOR = new Color(0, 0, 128);
    private static final Color COLOR_LINK = new Color(0, 0, 128);
    private static final Color COLOR_BACKGROUND = Color.WHITE;
    private static final Color BACKGROUND_COLOR = Color.WHITE;

    private static final Font FONT_SUBHEADER = new Font("Arial", Font.BOLD, 14);
    private static final Font SUBHEADER_FONT = new Font("Arial", Font.BOLD, 14);
    private static final Font FONT_HEADER = new Font("Arial", Font.BOLD, 22);
    private static final Font HEADER_FONT = new Font("Arial", Font.BOLD, 22);
    private static final Font FONT_LABEL = new Font("Arial", Font.PLAIN, 12);
    private static final Font LABEL_FONT = new Font("Arial", Font.PLAIN, 12);
    private static final Font FONT_NOTE = new Font("Arial", Font.PLAIN, 11);
    private static final Font NOTE_FONT = new Font("Arial", Font.PLAIN, 11);

    String linkHex = String.format("#%06x", COLOR_LINK.getRGB() & 0xFFFFFF);
    String goldHex = String.format("#%06x", COLOR_PRIMARY_ACTION.getRGB() & 0xFFFFFF);


    public static void main(String[] args) 
    {
        java.awt.EventQueue.invokeLater(() -> {
            try 
            {
                Room selectedRoom = Room.roomList.get(0);
                String checkInDate = "Dec 2, 2025";
                String checkOutDate = "Dec 3, 2025";
                int numberOfRooms = 1;
                int numberOfAdults = 2;
                int numberOfChildren = 0;

            	CheckoutPage checkoutFrame = new CheckoutPage(
        		    selectedRoom,
        		    checkInDate,
        		    checkOutDate,
        		    numberOfRooms,
        		    numberOfAdults,
        		    numberOfChildren
        		);
            	checkoutFrame.setTitle("Reservation Review - Shangri-La");
            	checkoutFrame.setVisible(true);
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
        });
    }

    public CheckoutPage(Room room, String checkIn, String checkOut, int rooms, int adults, int children) {
        this.selectedRoom = room;
        this.checkInDate = checkIn;
        this.checkOutDate = checkOut;
        this.numberOfRooms = rooms;
        this.numberOfAdults = adults;
        this.numberOfChildren = children;
        this.checkout = new Checkout(selectedRoom, checkInDate, checkOutDate, numberOfRooms, numberOfAdults, numberOfChildren, isMember);
        
        initializeUI();
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1220, 1303);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(BACKGROUND_COLOR);
        setContentPane(contentPane);
        
        NavBar navBar = new NavBar();
        add(navBar, BorderLayout.NORTH);

        JPanel mainContent = new JPanel(new BorderLayout());
        mainContent.setBackground(BACKGROUND_COLOR);
        mainContent.setBorder(new EmptyBorder(10, 120, 20, 120));

        JPanel contentColumns = new JPanel(new GridBagLayout());
        contentColumns.setBackground(BACKGROUND_COLOR);
        contentColumns.setBorder(new EmptyBorder(20, 0, 0, 0));

        GridBagConstraints grid = new GridBagConstraints();
        grid.fill = GridBagConstraints.BOTH;
        grid.gridy = 0;

        JScrollPane leftColumn = new JScrollPane(createLeftColumnPanel());
        leftColumn.setBorder(BorderFactory.createEmptyBorder());
        leftColumn.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        grid.gridx = 0;
        grid.weightx = 0.7;
        grid.insets = new Insets(0, 0, 0, 10);
        contentColumns.add(leftColumn, grid);

        JPanel rightColumn = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        rightColumn.setBackground(BACKGROUND_COLOR);
        rightColumn.setPreferredSize(new Dimension(300, 750));
        rightColumn.add(createRightColumnPanel()); // uses checkout internally
        grid.gridx = 1;
        grid.weightx = 0.3;
        grid.insets = new Insets(0, 10, 0, 0);
        contentColumns.add(rightColumn, grid);

        JScrollPane mainScroll = new JScrollPane(mainContent);
        mainScroll.setBorder(BorderFactory.createEmptyBorder());
        mainScroll.getVerticalScrollBar().setUnitIncrement(16);

        mainContent.add(createHeaderPanel(), BorderLayout.NORTH);
        mainContent.add(contentColumns, BorderLayout.CENTER);
        contentPane.add(mainScroll, BorderLayout.CENTER);
    }
   
    
    
    private void initializeUI() 
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1220, 1303);
    }


    //	=================================================================
    //							Header Panel
    //	=================================================================

    
    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(BACKGROUND_COLOR);
        panel.setBorder(new EmptyBorder(15, 5, 10, 5));

        JPanel navBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 10));
        navBar.setBackground(BACKGROUND_COLOR);
        navBar.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel backLabel = new JLabel(" < Back ");
        backLabel.setFont(new Font("Arial", Font.BOLD, 14));
        backLabel.setForeground(TEXT_COLOR);
        navBar.add(backLabel);

        JPanel summaryPanel = new JPanel();
        summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.Y_AXIS));
        summaryPanel.setBackground(new Color(245, 245, 245));
        summaryPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        summaryPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 180));
        summaryPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)),new EmptyBorder(10, 15, 10, 15)));

        JLabel stayTitle = new JLabel("Stay Summary: " + selectedRoom.getName());
        stayTitle.setFont(HEADER_FONT.deriveFont(Font.BOLD, 12f));
        stayTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        summaryPanel.add(stayTitle);

        JPanel detailsRow = new JPanel(new BorderLayout(0, 0));
        detailsRow.setBackground(new Color(245, 245, 245));
        detailsRow.setAlignmentX(Component.LEFT_ALIGNMENT);
        detailsRow.setBorder(new EmptyBorder(5, 0, 5, 0));

        JLabel imagePlaceholder = new JLabel("", SwingConstants.CENTER);
        imagePlaceholder.setPreferredSize(new Dimension(180, 120));
        imagePlaceholder.setBorder(new LineBorder(Color.LIGHT_GRAY));
        imagePlaceholder.setOpaque(true);
        imagePlaceholder.setBackground(Color.LIGHT_GRAY.brighter());
        
        ImageIcon icon = new ImageIcon(selectedRoom.getImagePath());
        Image img = icon.getImage().getScaledInstance(180, 120, Image.SCALE_SMOOTH);
        imagePlaceholder.setIcon(new ImageIcon(img));
        detailsRow.add(imagePlaceholder, BorderLayout.WEST);

        JPanel detailContainer = new JPanel();
        detailContainer.setLayout(new BoxLayout(detailContainer, BoxLayout.Y_AXIS));
        detailContainer.setBackground(new Color(245, 245, 245));
        detailContainer.setBorder(new EmptyBorder(10, 10, 0, 0));

        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        row1.setBackground(new Color(245, 245, 245));
        
        JLabel l1 = new JLabel("<html><b>" + checkInDate + " / " + checkOutDate + "</b></html>");
        l1.setFont(LABEL_FONT);
        row1.add(l1);
        detailContainer.add(row1);

        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        row2.setBackground(new Color(245, 245, 245));

        JLabel roomsLabel = new JLabel("Rooms & Guests:");
        roomsLabel.setFont(LABEL_FONT.deriveFont(Font.BOLD));
        JLabel roomsValue = new JLabel(numberOfRooms + " Room(s), " +  numberOfAdults + " Adult(s), " + numberOfChildren + " Children");
        roomsValue.setFont(LABEL_FONT);
        row2.add(roomsLabel);
        row2.add(roomsValue);

        row2.add(Box.createHorizontalStrut(10)); 

        JLabel typeLabel = new JLabel("Room Type:");
        typeLabel.setFont(LABEL_FONT.deriveFont(Font.BOLD));
        JLabel typeValue = new JLabel("<html><b style='color: " + goldHex + ";'>" + selectedRoom.getName() + "</b></html>");
        typeValue.setFont(LABEL_FONT);
        row2.add(typeLabel);
        row2.add(typeValue);

        detailContainer.add(row2);


        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        row3.setBackground(new Color(245, 245, 245));
        
        JLabel rateLabel = new JLabel("Rate Name:");
        setFont(LABEL_FONT.deriveFont(Font.BOLD));
        JLabel rateValue = new JLabel("<html><b style='color: " + goldHex + ";'>" + selectedRoom.getType() + "</b></html>");
        rateValue.setFont(LABEL_FONT);
        row3.add(rateLabel);
        row3.add(rateValue);

        row3.add(Box.createHorizontalStrut(10)); 
        
        JLabel policyLabel = new JLabel("Reservation Policy:");
        policyLabel.setFont(LABEL_FONT.deriveFont(Font.BOLD));
        JLabel policyValue = new JLabel("<html><b style='color: " + goldHex + ";'>Non-refundable, fees apply</b></html>"); // [NOTE] Hardcoded for now...
        policyValue.setFont(LABEL_FONT);
        row3.add(policyLabel);
        row3.add(policyValue);
        detailContainer.add(row3);

        detailsRow.add(detailContainer, BorderLayout.CENTER);
        summaryPanel.add(detailsRow);

        panel.add(navBar);
        panel.add(summaryPanel);

        return panel;
    }

    //	=================================================================
    //							Left Column Panel
    //	=================================================================

    
    private JPanel createLeftColumnPanel() 
    {
    	JPanel detailsPanel = new JPanel();
    	detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
    	detailsPanel.setBackground(BACKGROUND_COLOR);
    	detailsPanel.setAlignmentX(LEFT_ALIGNMENT);

    	JPanel guestInfoTitlePanel = new JPanel(new BorderLayout());
    	guestInfoTitlePanel.setBackground(BACKGROUND_COLOR);
    	guestInfoTitlePanel.setAlignmentX(LEFT_ALIGNMENT);
    	guestInfoTitlePanel.setBorder(new EmptyBorder(0, 0, 5, 0));
    	detailsPanel.add(guestInfoTitlePanel);
    	    	
    	JLabel guestInfoTitleLabel = new JLabel("Guest Information");
    	guestInfoTitleLabel.setFont(SUBHEADER_FONT.deriveFont(Font.BOLD, 18f));
    	guestInfoTitlePanel.add(guestInfoTitleLabel, BorderLayout.WEST);

    	JLabel hintLabel = new JLabel("Fields marked with * are required.");
    	hintLabel.setFont(NOTE_FONT);
    	hintLabel.setBorder(new EmptyBorder(0, 0, 15, 0));
    	detailsPanel.add(hintLabel);
    	
    	detailsPanel.add(createGuestInformationForm());
        
        JPanel customiseTitle = new JPanel(new BorderLayout());
        customiseTitle.setBackground(BACKGROUND_COLOR);
        customiseTitle.setAlignmentX(LEFT_ALIGNMENT);
        customiseTitle.setBorder(new EmptyBorder(15, 0, 5, 0));

        JLabel customiseLabel = new JLabel("Customize Your Stay");
        customiseLabel.setFont(SUBHEADER_FONT.deriveFont(Font.BOLD, 18f));
        customiseTitle.add(customiseLabel, BorderLayout.WEST);

        detailsPanel.add(customiseTitle);
        detailsPanel.add(createCustomiseStayPanel());

        return detailsPanel;
    }

    
    private JPanel createGuestInformationForm() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(BACKGROUND_COLOR);
        formPanel.setAlignmentX(LEFT_ALIGNMENT);

        // --- Name Fields ---
        JPanel nameRow = new JPanel(new GridLayout(1, 2, 5, 0));
        nameRow.setBackground(BACKGROUND_COLOR);

        JPanel firstNamePanel = new JPanel(new BorderLayout(0, 0));
        firstNamePanel.setBackground(BACKGROUND_COLOR);
        JLabel firstNameLabel = new JLabel("Given/ First Name*");
        firstNameLabel.setFont(LABEL_FONT);
        firstNamePanel.add(firstNameLabel, BorderLayout.NORTH);
        
        // FIX 1: Remove "JTextField"
        firstNameField = new JTextField(); 
        firstNameField.setFont(LABEL_FONT);
        firstNameField.setMargin(new Insets(10, 10, 10, 10));
        firstNamePanel.add(firstNameField, BorderLayout.CENTER);

        JPanel lastNamePanel = new JPanel(new BorderLayout(0, 0));
        lastNamePanel.setBackground(BACKGROUND_COLOR);
        JLabel lastNameLabel = new JLabel("Family/ Last Name*");
        lastNameLabel.setFont(LABEL_FONT);
        lastNamePanel.add(lastNameLabel, BorderLayout.NORTH);
        
        // FIX 2: Remove "JTextField"
        lastNameField = new JTextField();
        lastNameField.setFont(LABEL_FONT);
        lastNameField.setMargin(new Insets(10, 10, 10, 10));
        lastNamePanel.add(lastNameField, BorderLayout.CENTER);

        nameRow.add(firstNamePanel, BorderLayout.WEST);
        nameRow.add(lastNamePanel, BorderLayout.CENTER);
        formPanel.add(nameRow);
        formPanel.add(Box.createVerticalStrut(15));

        // --- Email Field ---
        JPanel emailRow = new JPanel(new BorderLayout(5, 0));
        emailRow.setBackground(BACKGROUND_COLOR);
        JLabel emailLabel = new JLabel("Email*");
        emailLabel.setFont(LABEL_FONT);
        emailRow.add(emailLabel, BorderLayout.NORTH);
        
        // FIX 3: Remove "JTextField"
        emailField = new JTextField();
        emailField.setFont(LABEL_FONT);
        emailField.setMargin(new Insets(10, 10, 10, 10));
        emailRow.add(emailField, BorderLayout.CENTER);

        formPanel.add(emailRow);
        formPanel.add(Box.createVerticalStrut(15));

        // --- Country Field ---
        JPanel countryRow = new JPanel(new BorderLayout(5, 0));
        countryRow.setBackground(BACKGROUND_COLOR);
        JLabel countryLabel = new JLabel("Country / Region*");
        countryLabel.setFont(LABEL_FONT);
        countryRow.add(countryLabel, BorderLayout.NORTH);
        String[] countries = { "Please select", "United States", "China", "Philippines" };
        
        // FIX 4: Remove "JComboBox<String>"
        countryCombo = new JComboBox<>(countries);
        countryCombo.setFont(LABEL_FONT);
        countryCombo.setBackground(Color.WHITE); 
        countryCombo.setPreferredSize(new Dimension(countryCombo.getPreferredSize().width, 35));

        countryRow.add(countryCombo, BorderLayout.CENTER);
        formPanel.add(countryRow);
        formPanel.add(Box.createVerticalStrut(15));

        // --- Mobile Fields (THIS IS WHERE YOUR ERROR WAS) ---
        JPanel mobileRow = new JPanel(new BorderLayout(5, 0));
        mobileRow.setBackground(BACKGROUND_COLOR);
        JLabel mobileLabel = new JLabel("Mobile*");
        mobileLabel.setFont(LABEL_FONT);
        mobileRow.add(mobileLabel, BorderLayout.NORTH);
        JPanel mobilePanel = new JPanel(new BorderLayout(5, 0));
        mobilePanel.setBackground(BACKGROUND_COLOR);

        // FIX 5: Remove "JTextField"
        iddCodeField = new JTextField("+IDD Code");
        iddCodeField.setFont(LABEL_FONT);
        iddCodeField.setForeground(new Color(150, 150, 150));
        iddCodeField.setPreferredSize(new Dimension(100, 35));
        iddCodeField.setMargin(new Insets(5, 5, 5, 5));
        mobilePanel.add(iddCodeField, BorderLayout.WEST);

        // FIX 6: Remove "JTextField"
        mobileNumberField = new JTextField();
        mobileNumberField.setFont(LABEL_FONT);
        mobileNumberField.setMargin(new Insets(10, 10, 10, 10));
        mobilePanel.add(mobileNumberField, BorderLayout.CENTER);

        mobileRow.add(mobilePanel, BorderLayout.CENTER);
        formPanel.add(mobileRow);
        formPanel.add(Box.createVerticalStrut(15));

        // FIX 7: Remove "JCheckBox"
        smsCheck = new JCheckBox("Send my reservation confirmation by SMS");
        smsCheck.setBackground(BACKGROUND_COLOR);
        smsCheck.setFont(LABEL_FONT);

        JPanel smsRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        smsRow.setBackground(BACKGROUND_COLOR);
        smsRow.add(smsCheck);

        formPanel.add(smsRow);

        return formPanel;
    }
    
    
    private JPanel createCustomiseStayPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setAlignmentX(LEFT_ALIGNMENT);
        mainPanel.setBorder(new EmptyBorder(0, 0, 0, 0));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setAlignmentX(LEFT_ALIGNMENT);

        contentPanel.add(createServiceItem("Daily Buffet Breakfast", "PHP 500", "PHP 900", "images/breakfast.png"));
        contentPanel.add(createServiceItem("Dining Credit of CNY 150 for your entire stay", "PHP 750", "PHP 1,050", "images/dining150.png"));
        contentPanel.add(createServiceItem("Dining Credit of CNY 300 for your entire stay", "PHP 1,500", "PHP 2,100", "images/dining300.png"));
        contentPanel.add(createServiceItem("Dining Credit of CNY 600 for your entire stay", "PHP 3,000", "PHP 4,200", "images/dining600.png"));
        contentPanel.add(createServiceItem("Laundry – Wash & Fold (one bag, up to 10 pieces)", "PHP 800", "PHP 900", "images/laundry.png"));
        contentPanel.add(createServiceItem("Extra Bed (per night)", "PHP 1,000", "PHP 1,500", "images/extrabed.png"));
        contentPanel.add(createServiceItem("Breakfast Package", "PHP 600", "PHP 950", "images/breakfastpackage.png"));
        contentPanel.add(createServiceItem("Airport Pickup (Private Car)", "PHP 1,800", "PHP 2,500", "images/transport.png"));
        contentPanel.add(createServiceItem("Late Checkout (until 4:00 PM)", "PHP 900", "PHP 1,200", "images/latecheckout.png"));
        contentPanel.add(createServiceItem("Spa Package (60 min)", "PHP 2,250", "PHP 3,000", "images/spa.png"));
        contentPanel.add(createServiceItem("Poolside Cabana (full day)", "PHP 1,000", "PHP 1,500", "images/cabana.png"));
        contentPanel.add(createServiceItem("Club Lounge Access", "PHP 1,750", "PHP 2,500", "images/lounge.png"));
        contentPanel.add(createServiceItem("Welcome Package", "PHP 500", "PHP 750", "images/welcome.png"));


        mainPanel.add(contentPanel);

        return mainPanel;
    }

    
    private JPanel createServiceItem(String name, String price, String originalPrice, String imagePath) {
        JPanel itemPanel = new JPanel(new BorderLayout(15, 0));
        itemPanel.setBackground(Color.WHITE);
        itemPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)),
            new EmptyBorder(15, 15, 15, 15)
        ));
        
        JLabel imgLabel = new JLabel();
        imgLabel.setPreferredSize(new Dimension(150, 100));
        imgLabel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imgLabel.setVerticalAlignment(SwingConstants.CENTER);

        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage().getScaledInstance(
            imgLabel.getPreferredSize().width,
            imgLabel.getPreferredSize().height,
            Image.SCALE_SMOOTH
        );
        imgLabel.setIcon(new ImageIcon(img));

        itemPanel.add(imgLabel, BorderLayout.WEST);
        
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(Color.WHITE);
        
        JLabel nameLabel = new JLabel(name);
        nameLabel.setFont(LABEL_FONT.deriveFont(Font.BOLD, 14f));
        nameLabel.setAlignmentX(LEFT_ALIGNMENT);
        detailsPanel.add(nameLabel);
        detailsPanel.add(Box.createVerticalStrut(5));
        
        JPanel pricePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        pricePanel.setBackground(Color.WHITE);
        pricePanel.setAlignmentX(LEFT_ALIGNMENT);
        
        JLabel currentPriceLabel = new JLabel(price);
        currentPriceLabel.setFont(LABEL_FONT.deriveFont(Font.BOLD, 14f));
        currentPriceLabel.setForeground(Color.BLACK);
        
        JLabel origPriceLabel = new JLabel("<html><strike>" + originalPrice + "</strike></html>");
        origPriceLabel.setFont(NOTE_FONT);
        origPriceLabel.setForeground(new Color(150, 150, 150));
        
        pricePanel.add(currentPriceLabel);
        pricePanel.add(origPriceLabel);
        detailsPanel.add(pricePanel);
        detailsPanel.add(Box.createVerticalStrut(5));
        
        JLabel viewDetailsLabel = new JLabel("View Details");
        viewDetailsLabel.setFont(LABEL_FONT);
        viewDetailsLabel.setForeground(LINK_COLOR);
        viewDetailsLabel.setAlignmentX(LEFT_ALIGNMENT);
        detailsPanel.add(viewDetailsLabel);
        
        itemPanel.add(detailsPanel, BorderLayout.CENTER);
        
        JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        quantityPanel.setBackground(Color.WHITE);
        
        JButton minusBtn = new JButton("-");
        minusBtn.setFont(LABEL_FONT.deriveFont(Font.BOLD, 16f));
        minusBtn.setPreferredSize(new Dimension(35, 35));
        minusBtn.setBackground(Color.WHITE);
        minusBtn.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        minusBtn.setFocusPainted(false);
        
        JLabel quantityLabel = new JLabel("0", SwingConstants.CENTER);
        quantityLabel.setFont(LABEL_FONT);
        quantityLabel.setPreferredSize(new Dimension(30, 35));
        quantityLabel.setOpaque(true);
        quantityLabel.setBackground(Color.WHITE);
        quantityLabel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));

        JButton plusBtn = new JButton("+");
        plusBtn.setFont(LABEL_FONT.deriveFont(Font.BOLD, 16f));
        plusBtn.setPreferredSize(new Dimension(35, 35));
        plusBtn.setBackground(Color.WHITE);
        plusBtn.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        plusBtn.setFocusPainted(false);
        
        quantityPanel.add(minusBtn);
        quantityPanel.add(quantityLabel);
        quantityPanel.add(plusBtn);
        
        itemPanel.add(quantityPanel, BorderLayout.EAST);
        
        plusBtn.addActionListener(e -> {
            int qty = Integer.parseInt(quantityLabel.getText());
            qty++;
            quantityLabel.setText(String.valueOf(qty));
            checkout.addService(name, qty);
            updateChargesPanel();
        });

        minusBtn.addActionListener(e -> {
            int qty = Integer.parseInt(quantityLabel.getText());
            if (qty > 0) {
                qty--;
                quantityLabel.setText(String.valueOf(qty));
                checkout.addService(name, qty);
                updateChargesPanel();
            }
        });

        return itemPanel;
    }
    
    
    private void updateChargesPanel() {
        double total = checkout.getTotalCharges(); // room + service + discounts
        chargesTotalLabel.setText(String.format("approx. PHP %.2f", total));

        double extraCharges = checkout.getServicesTotal() + addonsTotal; // sum of all dynamic add-ons
        extraValue.setText(String.format("PHP %.2f", extraCharges));
    }


    
    
    //	=================================================================
    //							RIGHT COLUMN PANEL
    //	=================================================================

    
    private JPanel createRightColumnPanel() {

        JPanel summaryPanel = new JPanel();
        summaryPanel.setPreferredSize(new Dimension(300, 750));
        summaryPanel.setLayout(new BorderLayout());
        summaryPanel.setBackground(BACKGROUND_COLOR);

        JPanel summaryBox = new JPanel(new BorderLayout());
        summaryBox.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        summaryBox.setBackground(BACKGROUND_COLOR);

        JLabel chargesTitle = new JLabel("Charges", SwingConstants.CENTER);
        chargesTitle.setFont(SUBHEADER_FONT.deriveFont(Font.BOLD, 16f));
        chargesTitle.setOpaque(true);
        chargesTitle.setBackground(new Color(245, 245, 245));
        chargesTitle.setBorder(new EmptyBorder(15, 0, 15, 0));

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBorder(new EmptyBorder(15, 0, 15, 0));
        contentPanel.setBackground(BACKGROUND_COLOR);

        GridBagConstraints grid = new GridBagConstraints();
        grid.gridx = 0;
        grid.weightx = 1.0;
        grid.fill = GridBagConstraints.HORIZONTAL;
        grid.anchor = GridBagConstraints.NORTHWEST;

        // ========= Dynamic PHP → CNY conversion =========
        double cnyRate = 0.13; // example value; replace if needed
        double cnyConverted = checkout.getTotalCharges() * cnyRate;

        // ========= ROOM CHARGES =========
        grid.gridy = 0;
        grid.insets = new Insets(5, 0, 5, 0);

        JPanel roomChargesPanel = new JPanel(new BorderLayout(10, 0));
        roomChargesPanel.setBackground(BACKGROUND_COLOR);
        roomChargesPanel.setBorder(new EmptyBorder(0, 15, 0, 15));

        JLabel roomLabel = new JLabel("Room Charges");
        roomLabel.setFont(LABEL_FONT.deriveFont(Font.BOLD));

        JLabel roomValue = new JLabel(
            String.format("PHP %.2f", checkout.getRoomCharges()),
            SwingConstants.RIGHT
        );
        roomValue.setFont(LABEL_FONT);
        roomValue.setForeground(TEXT_COLOR);

        roomChargesPanel.add(roomLabel, BorderLayout.WEST);
        roomChargesPanel.add(roomValue, BorderLayout.EAST);
        contentPanel.add(roomChargesPanel, grid);
        
     // ========= EXTRA CHARGES =========
        grid.gridy++;
        JPanel extraPanel = new JPanel(new BorderLayout(10, 0));
        extraPanel.setBackground(BACKGROUND_COLOR);
        extraPanel.setBorder(new EmptyBorder(0, 15, 0, 15));

        JLabel extraLabel = new JLabel("Extra Charges");
        extraLabel.setFont(LABEL_FONT.deriveFont(Font.BOLD));

        double extraCharges = checkout.getServicesTotal(); // retrieves total add-on service charges
        extraValue = new JLabel( // assign to class-level field
            String.format("PHP %.2f", extraCharges),
            SwingConstants.RIGHT
        );
        extraValue.setFont(LABEL_FONT);
        extraValue.setForeground(TEXT_COLOR);

        extraPanel.add(extraLabel, BorderLayout.WEST);
        extraPanel.add(extraValue, BorderLayout.EAST);
        contentPanel.add(extraPanel, grid);




        // ========= SERVICE TAX =========
        grid.gridy++;
        JPanel servicePanel = new JPanel(new BorderLayout(10, 0));
        servicePanel.setBackground(BACKGROUND_COLOR);
        servicePanel.setBorder(new EmptyBorder(0, 15, 0, 15));

        JLabel serviceLabel = new JLabel("Service Charge and Tax");
        serviceLabel.setFont(LABEL_FONT.deriveFont(Font.BOLD));

        JLabel serviceValue = new JLabel(
            String.format("PHP %.2f", checkout.getServiceTax()),
            SwingConstants.RIGHT
        );
        serviceValue.setFont(LABEL_FONT);
        serviceValue.setForeground(TEXT_COLOR);

        servicePanel.add(serviceLabel, BorderLayout.WEST);
        servicePanel.add(serviceValue, BorderLayout.EAST);
        contentPanel.add(servicePanel, grid);

        // ========= MEMBER DISCOUNT =========
        grid.gridy++;
        JPanel memberPanel = new JPanel(new BorderLayout(10, 0));
        memberPanel.setBackground(new Color(220, 255, 220));
        memberPanel.setOpaque(true);
        memberPanel.setBorder(new EmptyBorder(5, 15, 5, 15));

        JLabel memberLabel = new JLabel("Member Discount");
        memberLabel.setFont(LABEL_FONT.deriveFont(Font.BOLD));

        JLabel memberValue = new JLabel(
            String.format("- PHP %.2f", checkout.getMemberDiscount()),
            SwingConstants.RIGHT
        );
        memberValue.setFont(LABEL_FONT);
        memberValue.setForeground(new Color(40, 150, 40));

        memberPanel.add(memberLabel, BorderLayout.WEST);
        memberPanel.add(memberValue, BorderLayout.EAST);
        contentPanel.add(memberPanel, grid);

        // ========= TOTAL CHARGES =========
        grid.gridy++;
        grid.insets = new Insets(10, 0, 1, 0);

        JPanel totalPanel = new JPanel(new BorderLayout(10, 0));
        totalPanel.setBackground(BACKGROUND_COLOR);
        totalPanel.setBorder(new EmptyBorder(0, 15, 0, 15));

        JLabel totalLabel = new JLabel("Total Charges to Pay");
        totalLabel.setFont(LABEL_FONT.deriveFont(Font.BOLD));

        JLabel totalValue = new JLabel(
            String.format("approx. PHP %.2f", checkout.getTotalCharges()),
            SwingConstants.RIGHT
        );
        totalValue.setFont(LABEL_FONT.deriveFont(Font.BOLD));
        totalValue.setForeground(TEXT_COLOR);
        
        chargesTotalLabel = totalValue;

        totalPanel.add(totalLabel, BorderLayout.WEST);
        totalPanel.add(totalValue, BorderLayout.EAST);
        contentPanel.add(totalPanel, grid);

        // ========= TOTAL CNY =========
        grid.gridy++;
        grid.insets = new Insets(0, 0, 1, 0);

        JPanel cnyTotalPanel = new JPanel(new BorderLayout(10, 0));
        cnyTotalPanel.setBackground(BACKGROUND_COLOR);
        cnyTotalPanel.setBorder(new EmptyBorder(0, 15, 0, 15));

        JLabel cnyTotalValue = new JLabel(
            String.format("CNY %.2f", cnyConverted),
            SwingConstants.RIGHT
        );
        cnyTotalValue.setFont(NOTE_FONT);
        cnyTotalValue.setForeground(TEXT_COLOR);

        cnyTotalPanel.add(new JLabel(""), BorderLayout.WEST);
        cnyTotalPanel.add(cnyTotalValue, BorderLayout.EAST);
        contentPanel.add(cnyTotalPanel, grid);

        // ========= AMOUNT TO PAY NOW =========
        grid.gridy++;
        grid.insets = new Insets(10, 0, 1, 0);

        JPanel amountPanel = new JPanel(new BorderLayout(10, 0));
        amountPanel.setBackground(BACKGROUND_COLOR);
        amountPanel.setBorder(new EmptyBorder(0, 15, 0, 15));

        JLabel amountLabel = new JLabel("Amount to Pay Now");
        amountLabel.setFont(LABEL_FONT.deriveFont(Font.BOLD));

        JLabel amountValue = new JLabel(
            String.format("approx. PHP %.2f", checkout.getTotalCharges()),
            SwingConstants.RIGHT
        );
        amountValue.setFont(LABEL_FONT.deriveFont(Font.BOLD));
        amountValue.setForeground(TEXT_COLOR);

        amountPanel.add(amountLabel, BorderLayout.WEST);
        amountPanel.add(amountValue, BorderLayout.EAST);
        contentPanel.add(amountPanel, grid);

        // ========= AMOUNT CNY =========
        grid.gridy++;
        grid.insets = new Insets(0, 0, 1, 0);

        JPanel cnyAmountPanel = new JPanel(new BorderLayout(10, 0));
        cnyAmountPanel.setBackground(BACKGROUND_COLOR);
        cnyAmountPanel.setBorder(new EmptyBorder(0, 15, 0, 15));

        JLabel cnyAmountValue = new JLabel(
            String.format("CNY %.2f", cnyConverted),
            SwingConstants.RIGHT
        );
        cnyAmountValue.setFont(NOTE_FONT);
        cnyAmountValue.setForeground(TEXT_COLOR);

        cnyAmountPanel.add(new JLabel(""), BorderLayout.WEST);
        cnyAmountPanel.add(cnyAmountValue, BorderLayout.EAST);
        contentPanel.add(cnyAmountPanel, grid);

     // ========= GUARANTEE =========
        grid.gridy++;
        grid.insets = new Insets(0, 0, 5, 0);

        JPanel guaranteePanel = new JPanel(new BorderLayout(5, 0));
        guaranteePanel.setBackground(new Color(255, 250, 220));
        guaranteePanel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(255, 230, 140), 1, true),
            new EmptyBorder(8, 10, 8, 10)
        ));

        JLabel guaranteeLabel = new JLabel("Best Rate Guarantee");
        guaranteeLabel.setForeground(TEXT_COLOR);
        guaranteeLabel.setFont(LABEL_FONT.deriveFont(Font.BOLD));

        JLabel arrowIcon = new JLabel(">");
        arrowIcon.setFont(LABEL_FONT.deriveFont(Font.BOLD, 18f));
        arrowIcon.setForeground(new Color(150, 150, 150));

        guaranteePanel.add(guaranteeLabel, BorderLayout.CENTER);
        guaranteePanel.add(arrowIcon, BorderLayout.EAST);
        contentPanel.add(guaranteePanel, grid);

        // --- Mouse Listener to trigger the modal ---
        guaranteePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                final Color DARK_TEXT = Color.BLACK;
                final Color LIGHT_TEXT = Color.GRAY;
                final Font TITLE_FONT = LABEL_FONT.deriveFont(Font.PLAIN, 20f);
                final Font BODY_FONT = new Font("SansSerif", Font.PLAIN, 12);
                final Font NOTE_FONT = new Font("SansSerif", Font.PLAIN, 12).deriveFont(Font.BOLD);

                JDialog guaranteeDialog = new JDialog((JFrame) SwingUtilities.getWindowAncestor(guaranteePanel), "Best Rate Guarantee", true);
                guaranteeDialog.setUndecorated(true);
                guaranteeDialog.setSize(500, 300);
                guaranteeDialog.setLocationRelativeTo(SwingUtilities.getWindowAncestor(guaranteePanel));

                // Main container
                JPanel dialogPanel = new JPanel();
                dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
                dialogPanel.setBackground(Color.WHITE);
                dialogPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

                // --- Top bar (flex-style with FlowLayout for horizontal alignment) ---
                JPanel topBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
                topBar.setBackground(Color.WHITE);
                topBar.setBorder(new EmptyBorder(10, 15, 0, 10));

                JButton closeBtn = new JButton("<html>&times;</html>");
                closeBtn.setFont(new Font("SansSerif", Font.PLAIN, 20));
                closeBtn.setForeground(LIGHT_TEXT);
                closeBtn.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
                closeBtn.setFocusPainted(false);
                closeBtn.setContentAreaFilled(false);
                closeBtn.addActionListener(ev -> guaranteeDialog.dispose());
                topBar.add(closeBtn);

                dialogPanel.add(topBar);

                // --- Content Panel (vertical stack) ---
                JPanel content = new JPanel();
                content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
                content.setBorder(new EmptyBorder(5, 25, 25, 25));
                content.setBackground(Color.WHITE);

                JLabel title = new JLabel("Enjoy our Best Rate Guarantee");
                title.setFont(TITLE_FONT);
                title.setForeground(DARK_TEXT);
                title.setAlignmentX(Component.LEFT_ALIGNMENT);
                content.add(title);
                content.add(Box.createVerticalStrut(12));

                String bodyText1 = "Shangri-La will honour the lowest room rate available when you book a stay at any Shangri-La hotel or resort on Shangri-La.com. As part of our guarantee, should you find a better rate elsewhere online, we will match it* and offer you a further 10% discount off the lower rate, as well as instant benefits to enjoy through Shangri-La Circle, our exclusive membership rewards programme.";
                String bodyText2 = "Our guarantee applies only to the room rate for the duration of your reservation and does not apply to taxes, gratuities, incidental charges, food and beverage charges, resort fees, and any other fees or charges that may be incurred during your stay.";

                JTextArea textArea1 = new JTextArea(bodyText1);
                textArea1.setFont(BODY_FONT);
                textArea1.setLineWrap(true);
                textArea1.setWrapStyleWord(true);
                textArea1.setForeground(DARK_TEXT);
                textArea1.setEditable(false);
                textArea1.setOpaque(false);
                textArea1.setAlignmentX(Component.LEFT_ALIGNMENT);
                textArea1.setBorder(BorderFactory.createEmptyBorder());
                content.add(textArea1);
                content.add(Box.createVerticalStrut(8));

                JTextArea textArea2 = new JTextArea(bodyText2);
                textArea2.setFont(BODY_FONT);
                textArea2.setLineWrap(true);
                textArea2.setWrapStyleWord(true);
                textArea2.setForeground(DARK_TEXT);
                textArea2.setEditable(false);
                textArea2.setOpaque(false);
                textArea2.setAlignmentX(Component.LEFT_ALIGNMENT);
                textArea2.setBorder(BorderFactory.createEmptyBorder());
                content.add(textArea2);
                content.add(Box.createVerticalStrut(12));

                JLabel note = new JLabel("*Terms and conditions apply.");
                note.setFont(NOTE_FONT);
                note.setForeground(DARK_TEXT);
                note.setAlignmentX(Component.LEFT_ALIGNMENT);
                content.add(note);

                // Wrap content in a scrollable panel
                JScrollPane scrollPane = new JScrollPane(content);
                scrollPane.setBorder(BorderFactory.createEmptyBorder());
                scrollPane.getVerticalScrollBar().setUnitIncrement(16);
                dialogPanel.add(scrollPane);

                guaranteeDialog.setContentPane(dialogPanel);
                guaranteeDialog.setVisible(true);
            }
        });


        // ========= TAX NOTE =========
        grid.gridy++;
        grid.insets = new Insets(0, 15, 20, 15);

        JPanel taxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 5));
        taxPanel.setBackground(BACKGROUND_COLOR);

        JLabel inclusiveTax = new JLabel("*Inclusive of Service Charge and Tax");
        inclusiveTax.setFont(NOTE_FONT);

        taxPanel.add(inclusiveTax);
        contentPanel.add(taxPanel, grid);

        // ========= BUTTON =========
        grid.gridy++;
        grid.insets = new Insets(0, 15, 20, 15);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBackground(BACKGROUND_COLOR);

        JButton bookNowButton = new JButton("Book Now");
        bookNowButton.setFont(new Font("Arial", Font.BOLD, 15));
        bookNowButton.setBackground(new Color(255, 170, 0));
        bookNowButton.setForeground(Color.BLACK);
        bookNowButton.setOpaque(true);
        bookNowButton.setFocusPainted(false);
        bookNowButton.setBorder(new LineBorder(new Color(180, 180, 180), 1));
        bookNowButton.setPreferredSize(new Dimension(0, 30));
        bookNowButton.setMinimumSize(new Dimension(0, 30));
        bookNowButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        bookNowButton.addActionListener(e -> {
        	checkout.setGuestInfo(
        	        firstNameField.getText(),
        	        lastNameField.getText(),
        	        emailField.getText(),
        	        (String) countryCombo.getSelectedItem(),
        	        iddCodeField.getText(),
        	        mobileNumberField.getText(),
        	        smsCheck.isSelected()
        	    );
            // Open the PaymentPage and pass the current checkout data
            PaymentPage paymentPage = new PaymentPage(this.checkout);
            paymentPage.setVisible(true);
            
            // Close the current Checkout window
            this.dispose();
        });
        buttonPanel.add(bookNowButton, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, grid);

        // ========= POLICY =========
        grid.gridy++;
        grid.insets = new Insets(0, 0, 0, 0);
        grid.weighty = 1.0;
        grid.fill = GridBagConstraints.BOTH;

        JPanel policyPanel = new JPanel();
        policyPanel.setLayout(new BoxLayout(policyPanel, BoxLayout.Y_AXIS));
        policyPanel.setBackground(BACKGROUND_COLOR);
        policyPanel.setBorder(new EmptyBorder(0, 15, 0, 15));

        JLabel policyTitle = new JLabel("Reservation Policy");
        policyTitle.setFont(SUBHEADER_FONT.deriveFont(Font.BOLD, 16f));
        policyTitle.setForeground(TEXT_COLOR.darker());
        policyTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        policyTitle.setBorder(new EmptyBorder(0, 0, 8, 0));

        JLabel policyText = new JLabel(
            "<html><b>Room</b><br>Non-refundable<br>" +
            "Room cancelled will be subject to 100% of stay room charge, " +
            "plus applicable service charges, taxes, and fees.</html>"
        );
        policyText.setFont(LABEL_FONT);
        policyText.setForeground(TEXT_COLOR);
        policyText.setAlignmentX(Component.LEFT_ALIGNMENT);

        policyPanel.add(policyTitle);
        policyPanel.add(policyText);
        policyPanel.add(Box.createVerticalGlue());
        contentPanel.add(policyPanel, grid);

        summaryBox.add(chargesTitle, BorderLayout.NORTH);
        summaryBox.add(contentPanel, BorderLayout.CENTER);
        summaryPanel.add(summaryBox, BorderLayout.NORTH);

        return summaryPanel;
    }

}