package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class ReviewCustomizePage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private static final Color PRIMARY_ACTION_COLOR = new Color(240, 180, 50);
    private static final Color SECONDARY_BUTTON_COLOR = Color.WHITE;
    private static final Color TEXT_COLOR = new Color(50, 50, 50);
    private static final Color BACKGROUND_COLOR = Color.WHITE;
    private static final Color BOX_GRAY_COLOR = new Color(245, 245, 245);
    private static final Color LINK_COLOR = new Color(0, 0, 128);
    private static final Color ERROR_COLOR = new Color(255, 0, 0);
    private static final Font HEADER_FONT = new Font("Arial", Font.BOLD, 22);
    private static final Font SUBHEADER_FONT = new Font("Arial", Font.BOLD, 14);
    private static final Font LABEL_FONT = new Font("Arial", Font.PLAIN, 12);
    private static final Font NOTE_FONT = new Font("Arial", Font.PLAIN, 11);
    
    String linkHex = String.format("#%06x", LINK_COLOR.getRGB() & 0xFFFFFF);
    String goldHex = String.format("#%06x", PRIMARY_ACTION_COLOR.getRGB() & 0xFFFFFF);

    // Flag to track current page
    private boolean showingCustomizePage = true;

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            try {
            	ReviewCustomizePage frame = new ReviewCustomizePage();
                frame.setTitle("Reservation Review - Shangri-La");
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ReviewCustomizePage() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1220, 1303);

        contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(BACKGROUND_COLOR);
        setContentPane(contentPane);

        JPanel mainContentWrapper = new JPanel(new BorderLayout());
        mainContentWrapper.setBackground(BACKGROUND_COLOR);
        mainContentWrapper.setBorder(new EmptyBorder(0, 40, 0, 40));

        mainContentWrapper.add(createUnifiedHeaderPanel(), BorderLayout.NORTH);

        JPanel contentColumns = new JPanel(new GridBagLayout());
        contentColumns.setBackground(BACKGROUND_COLOR);
        contentColumns.setBorder(new EmptyBorder(10, 0, 10, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 0;

        gbc.gridx = 0;
        gbc.weightx = 0.7;
        gbc.insets = new Insets(0, 0, 0, 30);
        JScrollPane scrollableLeft = new JScrollPane(createCustomiseStayPanel());
        scrollableLeft.setBorder(BorderFactory.createEmptyBorder());
        scrollableLeft.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        contentColumns.add(scrollableLeft, (GridBagConstraints) gbc.clone());

        gbc.gridx = 1;
        gbc.weightx = 0.3;
        gbc.insets = new Insets(0, 0, 0, 0);
        JPanel rightWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        rightWrapper.setBackground(BACKGROUND_COLOR);
        rightWrapper.setPreferredSize(new Dimension(300, 750));
        rightWrapper.add(createSummaryPanel());
        contentColumns.add(rightWrapper, (GridBagConstraints) gbc.clone());

        mainContentWrapper.add(contentColumns, BorderLayout.CENTER);

        JScrollPane mainScrollPane = new JScrollPane(mainContentWrapper);
        mainScrollPane.setBorder(BorderFactory.createEmptyBorder());
        mainScrollPane.getVerticalScrollBar().setUnitIncrement(16);

        contentPane.add(mainScrollPane, BorderLayout.CENTER);
    }

    //=================================================================
    //                          TOP NAVBAR
    //=================================================================

    private JPanel createUnifiedHeaderPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(BACKGROUND_COLOR);
        panel.setBorder(new EmptyBorder(15, 5, 10, 5));

        JPanel navBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        navBar.setBackground(BACKGROUND_COLOR);
        navBar.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel backLabel = new JLabel(" < Back ");
        backLabel.setFont(new Font("Arial", Font.BOLD, 14));
        backLabel.setForeground(TEXT_COLOR);
        navBar.add(backLabel);

        panel.add(navBar);
        panel.add(Box.createVerticalStrut(5));

        JPanel summaryPanel = new JPanel();
        summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.Y_AXIS));
        summaryPanel.setBackground(new Color(245, 245, 245));
        summaryPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        summaryPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 180));
        summaryPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)),
            new EmptyBorder(10, 15, 10, 15)
        ));

        JLabel stayTitle = new JLabel("Stay Summary: Shangri-La Baotou");
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
        detailsRow.add(imagePlaceholder, BorderLayout.WEST);

        JPanel detailContainer = new JPanel();
        detailContainer.setLayout(new BoxLayout(detailContainer, BoxLayout.Y_AXIS));
        detailContainer.setBackground(new Color(245, 245, 245));
        detailContainer.setBorder(new EmptyBorder(10, 10, 0, 0));

        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        row1.setBackground(new Color(245, 245, 245));
        JLabel l1 = new JLabel("<html><b>Tue, 02 Dec 2025 / Wed, 03 Dec 2025 (1 night)</b></html>");
        l1.setFont(LABEL_FONT);
        row1.add(l1);
        detailContainer.add(row1);

        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        row2.setBackground(new Color(245, 245, 245));
        JLabel roomsLabel = new JLabel("Rooms & Guests:");
        roomsLabel.setFont(LABEL_FONT.deriveFont(Font.BOLD));
        JLabel roomsValue = new JLabel("1 Room, 1 Adult, 0 Children");
        roomsValue.setFont(LABEL_FONT);
        row2.add(roomsLabel);
        row2.add(roomsValue);

        JLabel typeLabel = new JLabel("Room Type:");
        typeLabel.setFont(LABEL_FONT.deriveFont(Font.BOLD));
        JLabel typeValue = new JLabel("<html><b style='color: " + goldHex + ";'>Deluxe Room</b></html>");
        typeValue.setFont(LABEL_FONT);
        row2.add(typeLabel);
        row2.add(typeValue);
        detailContainer.add(row2);

        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        row3.setBackground(new Color(245, 245, 245));
        JLabel rateLabel = new JLabel("Rate Name:");
        rateLabel.setFont(LABEL_FONT.deriveFont(Font.BOLD));
        JLabel rateValue = new JLabel("<html><b style='color: " + goldHex + ";'>Members Online Saver Rate</b></html>");
        rateValue.setFont(LABEL_FONT);
        row3.add(rateLabel);
        row3.add(rateValue);

        JLabel policyLabel = new JLabel("Reservation Policy:");
        policyLabel.setFont(LABEL_FONT.deriveFont(Font.BOLD));
        JLabel policyValue = new JLabel("<html><b style='color: " + goldHex + ";'>Non-refundable, fees apply</b></html>");
        policyValue.setFont(LABEL_FONT);
        row3.add(policyLabel);
        row3.add(policyValue);
        detailContainer.add(row3);

        detailsRow.add(detailContainer, BorderLayout.CENTER);
        summaryPanel.add(detailsRow);

        panel.add(summaryPanel);

        return panel;
    }

    //=================================================================
    //                    CUSTOMISE YOUR STAY
    //=================================================================

    private JPanel createCustomiseStayPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setAlignmentX(LEFT_ALIGNMENT);
        mainPanel.setBorder(new EmptyBorder(0, 0, 15, 0));

        JLabel title = new JLabel("Customise Your Stay");
        title.setFont(HEADER_FONT.deriveFont(Font.BOLD, 18f));
        title.setAlignmentX(LEFT_ALIGNMENT);
        mainPanel.add(title);
        mainPanel.add(Box.createVerticalStrut(15));

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(SUBHEADER_FONT.deriveFont(Font.PLAIN));
        tabbedPane.setBackground(BACKGROUND_COLOR);
        tabbedPane.setOpaque(true);
        tabbedPane.setAlignmentX(LEFT_ALIGNMENT);
        tabbedPane.setPreferredSize(new Dimension(500, 600));

        tabbedPane.addTab("Dine", createDinePanel());
        tabbedPane.addTab("Transportation", createTransportationPanel());
        tabbedPane.addTab("Other Services", createOtherServicesPanel());

        tabbedPane.setBorder(BorderFactory.createEmptyBorder(-5, 0, 0, 0));

        mainPanel.add(tabbedPane);
        mainPanel.add(Box.createVerticalStrut(20));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        buttonPanel.setBackground(BACKGROUND_COLOR);
        buttonPanel.setAlignmentX(LEFT_ALIGNMENT);
        buttonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));


        mainPanel.add(buttonPanel);

        return mainPanel;
    }

    private JPanel createDinePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(BACKGROUND_COLOR);
        panel.setAlignmentX(LEFT_ALIGNMENT);
        panel.setBorder(new EmptyBorder(5, 0, 5, 0));

        panel.add(createDineItem1());
        panel.add(createDineItem2());
        panel.add(createDineItem3());
        panel.add(createDineItem4());

        panel.add(Box.createVerticalGlue());
        return panel;
    }

    private JPanel createTransportationPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(BACKGROUND_COLOR);
        panel.setAlignmentX(LEFT_ALIGNMENT);
        panel.setBorder(new EmptyBorder(5, 0, 5, 0));

        panel.add(createTransportItem1());

        panel.add(Box.createVerticalGlue());
        return panel;
    }

    private JPanel createOtherServicesPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(BACKGROUND_COLOR);
        panel.setAlignmentX(LEFT_ALIGNMENT);
        panel.setBorder(new EmptyBorder(5, 0, 5, 0));

        panel.add(createOtherItem1());
        panel.add(createOtherItem2());

        panel.add(Box.createVerticalGlue());
        return panel;
    }

    private JPanel createDineItem1() {
        JPanel itemPanel = new JPanel(new BorderLayout(15, 0));
        itemPanel.setBackground(BACKGROUND_COLOR);
        itemPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        itemPanel.setAlignmentX(LEFT_ALIGNMENT);
        itemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        itemPanel.setPreferredSize(new Dimension(500, 100));

        buildServiceItem(itemPanel, "", "Daily Buffet Breakfast", "USD 11.88", "USD <strike>20.92</strike>");
        return itemPanel;
    }

    private JPanel createDineItem2() {
        JPanel itemPanel = new JPanel(new BorderLayout(15, 0));
        itemPanel.setBackground(BACKGROUND_COLOR);
        itemPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        itemPanel.setAlignmentX(LEFT_ALIGNMENT);
        itemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        itemPanel.setPreferredSize(new Dimension(500, 100));

        buildServiceItem(itemPanel, "", "Dining Credit of CNY 150 for your entire stay", "USD 15.45", "USD <strike>21.2</strike>");
        return itemPanel;
    }

    private JPanel createDineItem3() {
        JPanel itemPanel = new JPanel(new BorderLayout(15, 0));
        itemPanel.setBackground(BACKGROUND_COLOR);
        itemPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        itemPanel.setAlignmentX(LEFT_ALIGNMENT);
        itemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        itemPanel.setPreferredSize(new Dimension(500, 100));

        buildServiceItem(itemPanel, "", "Dining Credit of CNY 300 for your entire stay", "USD 30.91", "USD <strike>42.4</strike>");
        return itemPanel;
    }

    private JPanel createDineItem4() {
        JPanel itemPanel = new JPanel(new BorderLayout(15, 0));
        itemPanel.setBackground(BACKGROUND_COLOR);
        itemPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        itemPanel.setAlignmentX(LEFT_ALIGNMENT);
        itemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        itemPanel.setPreferredSize(new Dimension(500, 100));

        buildServiceItem(itemPanel, "", "Dining Credit of CNY 600 for your entire stay", "USD 61.81", "USD <strike>84.79</strike>");
        return itemPanel;
    }

    private JPanel createTransportItem1() {
        JPanel itemPanel = new JPanel(new BorderLayout(15, 0));
        itemPanel.setBackground(BACKGROUND_COLOR);
        itemPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        itemPanel.setAlignmentX(LEFT_ALIGNMENT);
        itemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        itemPanel.setPreferredSize(new Dimension(500, 100));

        buildServiceItem(itemPanel, "", "Airport Transfer (1-way)", "USD 31.46", "USD <strike>41.69</strike>");
        return itemPanel;
    }

    private JPanel createOtherItem1() {
        JPanel itemPanel = new JPanel(new BorderLayout(15, 0));
        itemPanel.setBackground(BACKGROUND_COLOR);
        itemPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        itemPanel.setAlignmentX(LEFT_ALIGNMENT);
        itemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        itemPanel.setPreferredSize(new Dimension(500, 100));

        buildServiceItem(itemPanel, "", "Laundry - Wash & Fold (one bag, up to 10 pieces)", "USD 20", "USD <strike>21.2</strike>");
        return itemPanel;
    }

    private JPanel createOtherItem2() {
        JPanel itemPanel = new JPanel(new BorderLayout(15, 0));
        itemPanel.setBackground(BACKGROUND_COLOR);
        itemPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        itemPanel.setAlignmentX(LEFT_ALIGNMENT);
        itemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        itemPanel.setPreferredSize(new Dimension(500, 100));

        buildServiceItem(itemPanel, "", "Laundry - Wash & Fold & Press (one bag, up to 10 pieces)", "USD 26.66", "USD <strike>28.26</strike>");
        return itemPanel;
    }

    private void buildServiceItem(JPanel itemPanel, String imageTag, String description, String currentPrice, String originalPrice) {
        JLabel imagePlaceholder = new JLabel(imageTag, SwingConstants.CENTER);
        imagePlaceholder.setPreferredSize(new Dimension(80, 80));
        imagePlaceholder.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        imagePlaceholder.setOpaque(true);
        imagePlaceholder.setBackground(Color.LIGHT_GRAY.brighter());
        itemPanel.add(imagePlaceholder, BorderLayout.WEST);

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(BACKGROUND_COLOR);

        JLabel descLabel = new JLabel(description);
        descLabel.setFont(LABEL_FONT.deriveFont(Font.BOLD, 14f));
        descLabel.setAlignmentX(LEFT_ALIGNMENT);
        textPanel.add(descLabel);

        JPanel priceRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        priceRow.setBackground(BACKGROUND_COLOR);
        priceRow.setAlignmentX(LEFT_ALIGNMENT);

        JLabel currentPriceLabel = new JLabel(currentPrice);
        currentPriceLabel.setFont(LABEL_FONT.deriveFont(Font.BOLD, 13f));
        currentPriceLabel.setForeground(TEXT_COLOR.darker());
        priceRow.add(currentPriceLabel);

        JLabel originalPriceLabel = new JLabel("<html>" + originalPrice + "</html>");
        originalPriceLabel.setFont(NOTE_FONT);
        originalPriceLabel.setForeground(Color.GRAY);
        priceRow.add(originalPriceLabel);

        textPanel.add(priceRow);

        JLabel viewDetails = new JLabel("View Details");
        viewDetails.setForeground(LINK_COLOR);
        viewDetails.setFont(LABEL_FONT);
        viewDetails.setAlignmentX(LEFT_ALIGNMENT);
        textPanel.add(Box.createVerticalStrut(5));
        textPanel.add(viewDetails);

        itemPanel.add(textPanel, BorderLayout.CENTER);

        JPanel counterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        counterPanel.setBackground(BACKGROUND_COLOR);

        JButton minus = createCounterButton("-");
        JTextField quantity = new JTextField("0");
        quantity.setPreferredSize(new Dimension(30, 30));
        quantity.setHorizontalAlignment(SwingConstants.CENTER);
        quantity.setFont(LABEL_FONT.deriveFont(Font.BOLD));
        quantity.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        JButton plus = createCounterButton("+");

        counterPanel.add(minus);
        counterPanel.add(quantity);
        counterPanel.add(plus);

        JPanel counterWrapper = new JPanel(new GridBagLayout());
        counterWrapper.setBackground(BACKGROUND_COLOR);
        counterWrapper.add(counterPanel);

        itemPanel.add(counterWrapper, BorderLayout.EAST);

        itemPanel.setBorder(BorderFactory.createCompoundBorder(
            itemPanel.getBorder(),
            BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(240, 240, 240))
        ));
    }

    private JButton createCounterButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(30, 30));
        button.setFont(LABEL_FONT.deriveFont(Font.BOLD, 16f));
        button.setOpaque(true);
        button.setBackground(Color.WHITE);
        button.setForeground(TEXT_COLOR);
        button.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        button.setFocusPainted(false);
        
        return button;
    }

    //=================================================================
    //                          CHARGES PANEL
    //=================================================================

    private JPanel createSummaryPanel() 
    {
        JPanel summaryPanel = new JPanel();
        summaryPanel.setPreferredSize(new Dimension(300, 750)); 
        summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.Y_AXIS));
        summaryPanel.setBackground(BACKGROUND_COLOR);
        summaryPanel.setAlignmentX(LEFT_ALIGNMENT); 
        
        
        JPanel summaryBox = new JPanel(new BorderLayout());
        summaryBox.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
        summaryBox.setBackground(BACKGROUND_COLOR);
        summaryBox.setAlignmentX(LEFT_ALIGNMENT);
        
        
        JLabel chargesTitle = new JLabel("Charges", SwingConstants.CENTER);
        chargesTitle.setFont(SUBHEADER_FONT.deriveFont(Font.BOLD, 16f));
        chargesTitle.setOpaque(true);
        chargesTitle.setBackground(new Color(245, 245, 245)); 
        chargesTitle.setBorder(new EmptyBorder(10, 0, 10, 0));
        
        
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        contentPanel.setBackground(BACKGROUND_COLOR);
        contentPanel.setAlignmentX(LEFT_ALIGNMENT);
       

        JPanel roomChargesPanel = new JPanel(new BorderLayout());
        roomChargesPanel.setBackground(BACKGROUND_COLOR);
        roomChargesPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        
        JLabel roomLabel = new JLabel("Room Charges");
        roomLabel.setFont(LABEL_FONT.deriveFont(Font.BOLD));

        JLabel roomValue = new JLabel("USD 66.42", SwingConstants.RIGHT);
        roomValue.setFont(LABEL_FONT);
        roomValue.setForeground(TEXT_COLOR);
        roomChargesPanel.add(roomLabel, BorderLayout.WEST);
        roomChargesPanel.add(roomValue, BorderLayout.EAST);
        contentPanel.add(roomChargesPanel);

        
        JPanel servicePanel = new JPanel(new BorderLayout());
        servicePanel.setBackground(BACKGROUND_COLOR);
        servicePanel.setBorder(new EmptyBorder(1, 0, 1, 0));
        
        JLabel serviceLabel = new JLabel("Service Charge and Tax");
        serviceLabel.setFont(LABEL_FONT.deriveFont(Font.BOLD));
        
        JLabel serviceValue = new JLabel("USD 9.81", SwingConstants.RIGHT);
        serviceValue.setFont(LABEL_FONT);
        serviceValue.setForeground(TEXT_COLOR);
        servicePanel.add(serviceLabel, BorderLayout.WEST);
        servicePanel.add(serviceValue, BorderLayout.EAST);
        contentPanel.add(servicePanel);


        JPanel memberPanel = new JPanel(new BorderLayout());
        memberPanel.setBackground(BACKGROUND_COLOR);
        memberPanel.setBorder(new EmptyBorder(1, 0, 1, 0));
        
        JLabel memberLabel = new JLabel("Member Discount");
        memberLabel.setFont(LABEL_FONT.deriveFont(Font.BOLD));
        
        JLabel memberValue = new JLabel("- USD 7.31", SwingConstants.RIGHT);
        memberValue.setFont(LABEL_FONT);
        memberValue.setForeground(new Color(40, 150, 40));
        memberPanel.add(memberLabel, BorderLayout.WEST);
        memberPanel.add(memberValue, BorderLayout.EAST);
        contentPanel.add(memberPanel);
        contentPanel.add(Box.createVerticalStrut(10));


        JPanel totalPanel = new JPanel(new BorderLayout());
        totalPanel.setBackground(BACKGROUND_COLOR);
        totalPanel.setBorder(new EmptyBorder(1, 0, 1, 0));
        
        JLabel totalLabel = new JLabel("Total Charges to Pay");
        totalLabel.setFont(LABEL_FONT.deriveFont(Font.BOLD));
        
        JLabel totalValue = new JLabel("approx. USD 68.92", SwingConstants.RIGHT);
        totalValue.setFont(LABEL_FONT.deriveFont(Font.BOLD));
        totalValue.setForeground(TEXT_COLOR);
        totalPanel.add(totalLabel, BorderLayout.WEST);
        totalPanel.add(totalValue, BorderLayout.EAST);
        contentPanel.add(totalPanel);


        JPanel cnyTotalPanel = new JPanel(new BorderLayout());
        cnyTotalPanel.setBackground(BACKGROUND_COLOR);
        cnyTotalPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        
        JLabel cnyTotalLabel = new JLabel("");
        cnyTotalLabel.setFont(NOTE_FONT);
        
        JLabel cnyTotalValue = new JLabel("CNY 487.74", SwingConstants.RIGHT);
        cnyTotalValue.setFont(NOTE_FONT);
        cnyTotalValue.setForeground(TEXT_COLOR);
        cnyTotalPanel.add(cnyTotalLabel, BorderLayout.WEST);
        cnyTotalPanel.add(cnyTotalValue, BorderLayout.EAST);
        contentPanel.add(cnyTotalPanel);
        contentPanel.add(Box.createVerticalStrut(10));


        JPanel amountPanel = new JPanel(new BorderLayout());
        amountPanel.setBackground(BACKGROUND_COLOR);
        amountPanel.setBorder(new EmptyBorder(1, 0, 1, 0));
        
        JLabel amountLabel = new JLabel("Amount to Pay Now");
        amountLabel.setFont(LABEL_FONT.deriveFont(Font.BOLD));
        
        JLabel amountValue = new JLabel("approx. USD 68.92", SwingConstants.RIGHT);
        amountValue.setFont(LABEL_FONT.deriveFont(Font.BOLD));
        amountValue.setForeground(TEXT_COLOR);
        amountPanel.add(amountLabel, BorderLayout.WEST);
        amountPanel.add(amountValue, BorderLayout.EAST);
        contentPanel.add(amountPanel);

        
        JPanel cnyAmountPanel = new JPanel(new BorderLayout());
        cnyAmountPanel.setBackground(BACKGROUND_COLOR);
        cnyAmountPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        
        JLabel cnyAmountLabel = new JLabel("");
        cnyAmountLabel.setFont(NOTE_FONT);
        
        JLabel cnyAmountValue = new JLabel("CNY 487.74", SwingConstants.RIGHT);
        cnyAmountValue.setFont(NOTE_FONT);
        cnyAmountValue.setForeground(TEXT_COLOR);
        cnyAmountPanel.add(cnyAmountLabel, BorderLayout.WEST);
        cnyAmountPanel.add(cnyAmountValue, BorderLayout.EAST);
        contentPanel.add(cnyAmountPanel);


        JPanel breakdownPanel = new JPanel(new BorderLayout());
        breakdownPanel.setBackground(BACKGROUND_COLOR);
        breakdownPanel.setBorder(new EmptyBorder(10, 0, 10, 0));

        breakdownPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        breakdownPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));

        JLabel breakdown = new JLabel("View Breakdown");
        breakdown.setForeground(new Color(0, 102, 204));
        breakdown.setFont(LABEL_FONT);

        breakdownPanel.add(breakdown, BorderLayout.WEST);

        contentPanel.add(breakdownPanel);

        
        JPanel guarantee = new JPanel(new BorderLayout());
        guarantee.setBackground(new Color(255, 250, 220));
        guarantee.setBorder(new LineBorder(new Color(255, 230, 140), 1, true));
        guarantee.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        guarantee.setPreferredSize(new Dimension(300, 40));
        
        JLabel guaranteeLabel = new JLabel("Best Rate Guarantee");
        guaranteeLabel.setForeground(TEXT_COLOR);
        guaranteeLabel.setFont(LABEL_FONT.deriveFont(Font.BOLD));
        guaranteeLabel.setBorder(new EmptyBorder(0, 5, 0, 0));

        JLabel arrowIcon = new JLabel(">");
        arrowIcon.setFont(LABEL_FONT.deriveFont(Font.BOLD, 18f));
        arrowIcon.setForeground(new Color(150, 150, 150));
        arrowIcon.setBorder(new EmptyBorder(0, 0, 0, 10));
        
        JLabel guaranteeIcon = new JLabel("\uD83C\uDFC5");
        guaranteeIcon.setFont(LABEL_FONT.deriveFont(20f));
        guaranteeIcon.setBorder(new EmptyBorder(0, 10, 0, 0));
        guarantee.add(guaranteeIcon, BorderLayout.WEST); 
        guarantee.add(guaranteeLabel, BorderLayout.CENTER);
        guarantee.add(arrowIcon, BorderLayout.EAST);
        guarantee.setAlignmentY(CENTER_ALIGNMENT);
        guaranteeLabel.setAlignmentY(CENTER_ALIGNMENT);
        guaranteeIcon.setAlignmentY(CENTER_ALIGNMENT);
        contentPanel.add(guarantee);


        JLabel inclusiveTax = new JLabel("*Inclusive of Service Charge and Tax");
        inclusiveTax.setFont(NOTE_FONT);
        inclusiveTax.setBorder(new EmptyBorder(5, 0, 15, 0));
        inclusiveTax.setAlignmentX(RIGHT_ALIGNMENT);
        contentPanel.add(inclusiveTax);
        
        
        JButton bookNowButton = new JButton("Book Now");
        bookNowButton.setFont(new Font("Arial", Font.BOLD, 15));
        bookNowButton.setBackground(PRIMARY_ACTION_COLOR);
        bookNowButton.setForeground(TEXT_COLOR);
        bookNowButton.setOpaque(true);
        bookNowButton.setFocusPainted(false);
        bookNowButton.setBorder(new LineBorder(new Color(180, 180, 180), 1));
        bookNowButton.setBorderPainted(true);
        bookNowButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        bookNowButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        bookNowButton.setPreferredSize(new Dimension(0, 45));
        contentPanel.add(bookNowButton);
        contentPanel.add(Box.createVerticalStrut(20));

        
        JPanel policyPanel = new JPanel();
        policyPanel.setLayout(new BoxLayout(policyPanel, BoxLayout.Y_AXIS));
        policyPanel.setBackground(BACKGROUND_COLOR);
        policyPanel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        policyPanel.setBorder(new EmptyBorder(15, 0, 15, 0));

        JLabel policyTitle = new JLabel("Reservation Policy");
        policyTitle.setFont(SUBHEADER_FONT.deriveFont(Font.BOLD, 16f));
        policyTitle.setForeground(TEXT_COLOR.darker());
        policyTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        policyTitle.setBorder(new EmptyBorder(0, 0, 8, 0));
        policyPanel.add(policyTitle);

        JLabel policyText = new JLabel(
            "<html>"
                + "<b>Room</b><br>"
                + "Non-refundable<br>"
                + "Room cancelled will be subject to 100% of stay room charge, plus applicable service charges, taxes, and fees."
                + "</html>"
        );
        policyText.setFont(LABEL_FONT);
        policyText.setForeground(TEXT_COLOR);
        policyText.setAlignmentX(Component.LEFT_ALIGNMENT);
        policyText.setBorder(new EmptyBorder(0, 0, 0, 0));
        policyText.setMaximumSize(new Dimension(Integer.MAX_VALUE, policyText.getPreferredSize().width));
        policyPanel.add(policyText);

        contentPanel.add(policyPanel);

        summaryBox.add(chargesTitle, BorderLayout.NORTH);
        summaryBox.add(contentPanel, BorderLayout.CENTER);
        summaryPanel.add(summaryBox);

        return summaryPanel;
    }
}