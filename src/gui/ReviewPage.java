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
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class ReviewPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private static final Color PRIMARY_ACTION_COLOR = new Color(240, 180, 50); // Gold/Yellow
    private static final Color SECONDARY_BUTTON_COLOR = Color.WHITE;
    private static final Color TEXT_COLOR = new Color(50, 50, 50);
    private static final Color BACKGROUND_COLOR = Color.WHITE;
    private static final Color BOX_GRAY_COLOR = new Color(245, 245, 245); // Light gray for charges and member box
    private static final Color LINK_COLOR = new Color(0, 0, 128); // Blue for links/rate names
    private static final Color ERROR_COLOR = new Color(255, 0, 0); // Red for non-refundable policy
    private static final Font HEADER_FONT = new Font("Arial", Font.BOLD, 22);
    private static final Font SUBHEADER_FONT = new Font("Arial", Font.BOLD, 14);
    private static final Font LABEL_FONT = new Font("Arial", Font.PLAIN, 12);
    private static final Font NOTE_FONT = new Font("Arial", Font.PLAIN, 11);
    
    
    private static final Color COLOR_PRIMARY_ACTION = new Color(240, 180, 50);
    private static final Color COLOR_SECONDARY_BUTTON = Color.WHITE;
    private static final Color COLOR_TEXT_DEFAULT = new Color(50, 50, 50);
    private static final Color COLOR_BACKGROUND = Color.WHITE;
    private static final Color COLOR_PANEL_BACKGROUND = new Color(245, 245, 245);
    private static final Color COLOR_LINK = new Color(0, 0, 128);
    private static final Color COLOR_GOLD = new Color(255, 215, 0);
    private static final Color COLOR_ERROR = new Color(255, 0, 0);

    private static final Font FONT_HEADER = new Font("Arial", Font.BOLD, 22);
    private static final Font FONT_SUBHEADER = new Font("Arial", Font.BOLD, 14);
    private static final Font FONT_LABEL = new Font("Arial", Font.PLAIN, 12);
    private static final Font FONT_NOTE = new Font("Arial", Font.PLAIN, 11);
    
    String linkHex = String.format("#%06x", COLOR_LINK.getRGB() & 0xFFFFFF);
    String goldHex = String.format("#%06x", COLOR_PRIMARY_ACTION.getRGB() & 0xFFFFFF);


    public static void main(String[] args) 
    {
        java.awt.EventQueue.invokeLater(() -> {
            try 
            {
                ReviewPage frame = new ReviewPage();
                frame.setTitle("Reservation Review - Shangri-La");
                frame.setVisible(true);
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
        });
    }

    public ReviewPage() 
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1220, 1303);

        contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(BACKGROUND_COLOR);
        setContentPane(contentPane);

        JPanel mainContentWrapper = new JPanel(new BorderLayout());
        mainContentWrapper.setBackground(BACKGROUND_COLOR);
        mainContentWrapper.setBorder(new EmptyBorder(0, 40, 0, 40));

        // ⬅️ Header now scrolls with content
        mainContentWrapper.add(createUnifiedHeaderPanel(), BorderLayout.NORTH);

        JPanel contentColumns = new JPanel(new GridBagLayout());
        contentColumns.setBackground(BACKGROUND_COLOR);
        contentColumns.setBorder(new EmptyBorder(10, 0, 10, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy = 0; // Add gridy for clarity

        gbc.gridx = 0;
        gbc.weightx = 0.7;
        gbc.insets = new Insets(0, 0, 0, 30);
        JScrollPane scrollableLeft = new JScrollPane(createDetailsPanel());
        scrollableLeft.setBorder(BorderFactory.createEmptyBorder());
        scrollableLeft.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        contentColumns.add(scrollableLeft, (GridBagConstraints) gbc.clone()); // Clone gbc

        gbc.gridx = 1;
        gbc.weightx = 0.3;
        gbc.insets = new Insets(0, 0, 0, 0);
        JPanel rightWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        rightWrapper.setBackground(BACKGROUND_COLOR);
        rightWrapper.setPreferredSize(new Dimension(300, 750));
        rightWrapper.add(createSummaryPanel());
        contentColumns.add(rightWrapper, (GridBagConstraints) gbc.clone()); // Clone gbc

        mainContentWrapper.add(contentColumns, BorderLayout.CENTER);

        JScrollPane mainScrollPane = new JScrollPane(mainContentWrapper);
        mainScrollPane.setBorder(BorderFactory.createEmptyBorder());
        mainScrollPane.getVerticalScrollBar().setUnitIncrement(16);

        contentPane.add(mainScrollPane, BorderLayout.CENTER);
    }

    
    //	=================================================================
    //								TOP NAVBAR
    //	=================================================================

    
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
        setFont(LABEL_FONT.deriveFont(Font.BOLD));
        
        JLabel typeValue = new JLabel("<html><b style='color: " + goldHex + ";'>Deluxe Room</b></html>");
        typeValue.setFont(LABEL_FONT);
        row2.add(typeLabel);
        row2.add(typeValue);
        detailContainer.add(row2);


        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        row3.setBackground(new Color(245, 245, 245));
        
        JLabel rateLabel = new JLabel("Rate Name:");
        setFont(LABEL_FONT.deriveFont(Font.BOLD));
        
        JLabel rateValue = new JLabel("<html><b style='color: " + goldHex + ";'>Members Online Saver Rate</b></html>");
        rateValue.setFont(LABEL_FONT);
        row3.add(rateLabel);
        row3.add(rateValue);

        
        JLabel policyLabel = new JLabel("Reservation Policy:");
        setFont(LABEL_FONT.deriveFont(Font.BOLD));
        
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


    //	=================================================================
    //								GUEST INFORMATION
    //	=================================================================

    
    private JPanel createDetailsPanel() 
    {
    	JPanel detailsPanel = new JPanel();
    	detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
    	detailsPanel.setBackground(BACKGROUND_COLOR);
    	detailsPanel.setAlignmentX(LEFT_ALIGNMENT);
    	detailsPanel.setPreferredSize(new Dimension(500, 1200));

    	
    	JPanel guestInfoTitlePanel = new JPanel(new BorderLayout());
    	guestInfoTitlePanel.setBackground(BACKGROUND_COLOR);
    	guestInfoTitlePanel.setAlignmentX(LEFT_ALIGNMENT);
    	guestInfoTitlePanel.setBorder(new EmptyBorder(15, 0, 5, 0));
    	detailsPanel.add(guestInfoTitlePanel);
    	
    	
    	JLabel guestInfoTitleLabel = new JLabel("Guest Information");
    	guestInfoTitleLabel.setFont(SUBHEADER_FONT.deriveFont(Font.BOLD, 18f));
    	guestInfoTitlePanel.add(guestInfoTitleLabel, BorderLayout.WEST);

    	
    	JLabel hintLabel = new JLabel("Fields marked with * are required.");
    	hintLabel.setFont(NOTE_FONT);
    	hintLabel.setBorder(new EmptyBorder(0, 0, 15, 0));
    	detailsPanel.add(hintLabel);

    	
    	detailsPanel.add(createGuestInformationForm());


        JPanel optionalRequestTitlePanel = new JPanel(new BorderLayout());
        optionalRequestTitlePanel.setBackground(BACKGROUND_COLOR);
        optionalRequestTitlePanel.setAlignmentX(LEFT_ALIGNMENT);
        optionalRequestTitlePanel.setBorder(new EmptyBorder(15, 0, 5, 0));

        JLabel optionalRequestTitleLabel = new JLabel("Optional Request");
        optionalRequestTitleLabel.setFont(SUBHEADER_FONT.deriveFont(Font.BOLD, 18f));
        optionalRequestTitlePanel.add(optionalRequestTitleLabel, BorderLayout.WEST);

        detailsPanel.add(optionalRequestTitlePanel);
        detailsPanel.add(createOptionalRequestPanel());

        
        JPanel joinCircleTitlePanel = new JPanel(new BorderLayout());
        joinCircleTitlePanel.setBackground(BACKGROUND_COLOR);
        joinCircleTitlePanel.setAlignmentX(LEFT_ALIGNMENT);
        joinCircleTitlePanel.setBorder(new EmptyBorder(15, 0, 5, 0));

        detailsPanel.add(joinCircleTitlePanel);
        detailsPanel.add(createJoinCirclePanel());


        JPanel termsTitlePanel = new JPanel(new BorderLayout());
        termsTitlePanel.setBackground(BACKGROUND_COLOR);
        termsTitlePanel.setAlignmentX(LEFT_ALIGNMENT);
        termsTitlePanel.setBorder(new EmptyBorder(15, 0, 5, 0));

        
        JLabel termsTitleLabel = new JLabel("Terms and Conditions");
        termsTitleLabel.setFont(SUBHEADER_FONT.deriveFont(Font.BOLD, 18f));
        termsTitlePanel.add(termsTitleLabel, BorderLayout.WEST);

        detailsPanel.add(termsTitlePanel);
        detailsPanel.add(createTermsAndConditionsPanel());
        detailsPanel.add(Box.createVerticalStrut(40));

        return detailsPanel;
    }

    
    private JPanel createGuestInformationForm() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(BACKGROUND_COLOR);
        formPanel.setAlignmentX(LEFT_ALIGNMENT);


        JPanel nameRow = new JPanel(new BorderLayout(10, 0));
        nameRow.setBackground(BACKGROUND_COLOR);
        JPanel firstPanel = new JPanel(new BorderLayout(5, 0));
        firstPanel.setBackground(BACKGROUND_COLOR);
        JLabel firstLabel = new JLabel("Given/First Name*"); firstLabel.setFont(LABEL_FONT);
        firstPanel.add(firstLabel, BorderLayout.NORTH);
        firstPanel.add(new JTextField(), BorderLayout.CENTER);

        
        JPanel lastPanel = new JPanel(new BorderLayout(5, 0));
        lastPanel.setBackground(BACKGROUND_COLOR);
        JLabel lastLabel = new JLabel("Family/Last Name*"); lastLabel.setFont(LABEL_FONT);
        lastPanel.add(lastLabel, BorderLayout.NORTH);
        lastPanel.add(new JTextField(), BorderLayout.CENTER);

        nameRow.add(firstPanel, BorderLayout.WEST);
        nameRow.add(lastPanel, BorderLayout.CENTER);
        formPanel.add(nameRow);
        formPanel.add(Box.createVerticalStrut(15));

        
        JPanel emailRow = new JPanel(new BorderLayout(5, 0));
        emailRow.setBackground(BACKGROUND_COLOR);
        JLabel emailLabel = new JLabel("Email*"); emailLabel.setFont(LABEL_FONT);
        emailRow.add(emailLabel, BorderLayout.NORTH);
        emailRow.add(new JTextField(), BorderLayout.CENTER);
        formPanel.add(emailRow);
        formPanel.add(Box.createVerticalStrut(15));


        JPanel countryRow = new JPanel(new BorderLayout(5, 0));
        countryRow.setBackground(BACKGROUND_COLOR);
        JLabel countryLabel = new JLabel("Country / Region*"); countryLabel.setFont(LABEL_FONT);
        countryRow.add(countryLabel, BorderLayout.NORTH);
        String[] countries = {"Please select", "United States", "China", "Other..."};
        countryRow.add(new JComboBox<>(countries), BorderLayout.CENTER);
        formPanel.add(countryRow);
        formPanel.add(Box.createVerticalStrut(15));


        JPanel mobileRow = new JPanel(new BorderLayout(5, 0));
        mobileRow.setBackground(BACKGROUND_COLOR);
        JLabel mobileLabel = new JLabel("Mobile*"); mobileLabel.setFont(LABEL_FONT);
        mobileRow.add(mobileLabel, BorderLayout.NORTH);
        JPanel mobilePanel = new JPanel(new BorderLayout(5, 0));
        mobilePanel.setBackground(BACKGROUND_COLOR);
        JTextField iddCode = new JTextField("+IDD Code"); iddCode.setFont(LABEL_FONT); iddCode.setForeground(new Color(150,150,150));
        iddCode.setPreferredSize(new Dimension(100,35));
        mobilePanel.add(iddCode, BorderLayout.WEST);
        JTextField mobileNumber = new JTextField();
        mobilePanel.add(mobileNumber, BorderLayout.CENTER);
        mobileRow.add(mobilePanel, BorderLayout.CENTER);
        formPanel.add(mobileRow);
        formPanel.add(Box.createVerticalStrut(15));

        
        JCheckBox smsCheck = new JCheckBox("Send my reservation confirmation by SMS");
        smsCheck.setBackground(BACKGROUND_COLOR);
        smsCheck.setFont(LABEL_FONT);

        
        JPanel smsRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        smsRow.setBackground(BACKGROUND_COLOR);
        smsRow.add(smsCheck);

        formPanel.add(smsRow);


        return formPanel;
    }



    //	=================================================================
    //								OPTIONAL REQUEST
    //	=================================================================
    
    
    private JPanel createOptionalRequestPanel() 
    {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setAlignmentX(LEFT_ALIGNMENT);

        
        JLabel note = new JLabel(
            "Please let us know if you have any additional requests. You may add them any time by managing your booking online or by contacting us."
        );
        note.setFont(LABEL_FONT);
        note.setForeground(TEXT_COLOR);
        note.setAlignmentX(LEFT_ALIGNMENT);
        mainPanel.add(note);
        mainPanel.add(Box.createVerticalStrut(15));

        // -------------------------
        // 1. Room Preferences expandable box
        // -------------------------
       

        JPanel prefsPanel = new JPanel(new BorderLayout());
        prefsPanel.setBorder(new LineBorder(new Color(220, 220, 220), 1));
        prefsPanel.setBackground(BACKGROUND_COLOR);
        prefsPanel.setPreferredSize(new Dimension(1, 40));
        prefsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        prefsPanel.setAlignmentX(LEFT_ALIGNMENT);


        JPanel labelWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        labelWrapper.setBackground(BACKGROUND_COLOR);
        JLabel prefLabel = new JLabel("Room Preferences");
        prefLabel.setFont(LABEL_FONT.deriveFont(Font.BOLD));
        labelWrapper.add(prefLabel);


        JPanel buttonWrapper = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        buttonWrapper.setBackground(BACKGROUND_COLOR);
        JLabel showDetails = new JLabel("Show Details");
        showDetails.setForeground(LINK_COLOR);
        showDetails.setFont(LABEL_FONT);
        buttonWrapper.add(showDetails);

        prefsPanel.add(labelWrapper, BorderLayout.WEST);
        prefsPanel.add(buttonWrapper, BorderLayout.EAST);

        labelWrapper.add(Box.createVerticalGlue());
        buttonWrapper.add(Box.createVerticalGlue());
        

        JPanel preferencesDetailsPanel = new JPanel();
        preferencesDetailsPanel.setLayout(new BoxLayout(preferencesDetailsPanel, BoxLayout.Y_AXIS));
        preferencesDetailsPanel.setBorder(new EmptyBorder(10, 15, 10, 15));
        preferencesDetailsPanel.setBackground(BACKGROUND_COLOR);
        preferencesDetailsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // --- Smoking Preference ---
        JLabel smokingTitle = new JLabel("Smoking or a non-smoking room?");
        smokingTitle.setFont(LABEL_FONT.deriveFont(Font.BOLD));
        smokingTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel smokingNote = new JLabel("This is a non-smoking hotel");
        smokingNote.setFont(LABEL_FONT);
        smokingNote.setForeground(TEXT_COLOR);
        smokingNote.setAlignmentX(Component.LEFT_ALIGNMENT);

        preferencesDetailsPanel.add(smokingTitle);
        preferencesDetailsPanel.add(Box.createVerticalStrut(5));
        preferencesDetailsPanel.add(smokingNote);
        preferencesDetailsPanel.add(Box.createVerticalStrut(20));

        // --- Accessible Room ---
        JLabel accessibleTitle = new JLabel("Please let us know if you would prefer an accessible guest room.");
        accessibleTitle.setFont(LABEL_FONT.deriveFont(Font.BOLD));
        accessibleTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        preferencesDetailsPanel.add(accessibleTitle);
        preferencesDetailsPanel.add(Box.createVerticalStrut(10));

        // Radio buttons in left-aligned panel
        JPanel accessibleOptions = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        accessibleOptions.setBackground(BACKGROUND_COLOR);
        accessibleOptions.setAlignmentX(Component.LEFT_ALIGNMENT);

        JRadioButton accessibleNo = new JRadioButton("No");
        JRadioButton accessibleYes = new JRadioButton("Yes");
        accessibleNo.setBackground(BACKGROUND_COLOR);
        accessibleYes.setBackground(BACKGROUND_COLOR);

        ButtonGroup accessibleGroup = new ButtonGroup();
        accessibleGroup.add(accessibleNo);
        accessibleGroup.add(accessibleYes);
        accessibleNo.setSelected(true);

        accessibleOptions.add(accessibleNo);
        accessibleOptions.add(accessibleYes);

        preferencesDetailsPanel.add(accessibleOptions);
        preferencesDetailsPanel.add(Box.createVerticalStrut(20));

        // --- Purpose of Trip ---
        JLabel purposeTitle = new JLabel("Purpose of Your Trip");
        purposeTitle.setFont(LABEL_FONT.deriveFont(Font.BOLD));
        purposeTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel purposeNote = new JLabel("Please let us know the purpose of your trip so we can offer you a more personalised experience.");
        purposeNote.setFont(LABEL_FONT);
        purposeNote.setForeground(TEXT_COLOR);
        purposeNote.setAlignmentX(Component.LEFT_ALIGNMENT);

        preferencesDetailsPanel.add(purposeTitle);
        preferencesDetailsPanel.add(Box.createVerticalStrut(5));
        preferencesDetailsPanel.add(purposeNote);
        preferencesDetailsPanel.add(Box.createVerticalStrut(10));

        // Purpose options row 1
        JPanel purposeOptions1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        purposeOptions1.setBackground(BACKGROUND_COLOR);
        purposeOptions1.setAlignmentX(Component.LEFT_ALIGNMENT);

        JRadioButton business = new JRadioButton("Business Travel");
        JRadioButton weddings = new JRadioButton("Weddings & Celebrations");
        JRadioButton meetings = new JRadioButton("Meetings & Events");

        business.setBackground(BACKGROUND_COLOR);
        weddings.setBackground(BACKGROUND_COLOR);
        meetings.setBackground(BACKGROUND_COLOR);

        ButtonGroup purposeGroup = new ButtonGroup();
        purposeGroup.add(business);
        purposeGroup.add(weddings);
        purposeGroup.add(meetings);

        purposeOptions1.add(business);
        purposeOptions1.add(weddings);
        purposeOptions1.add(meetings);

        // Purpose options row 2
        JPanel purposeOptions2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        purposeOptions2.setBackground(BACKGROUND_COLOR);
        purposeOptions2.setAlignmentX(Component.LEFT_ALIGNMENT);

        JRadioButton leisure = new JRadioButton("Leisure");
        JRadioButton family = new JRadioButton("Family");

        leisure.setBackground(BACKGROUND_COLOR);
        family.setBackground(BACKGROUND_COLOR);

        purposeGroup.add(leisure);
        purposeGroup.add(family);

        purposeOptions2.add(leisure);
        purposeOptions2.add(family);

        preferencesDetailsPanel.add(purposeOptions1);
        preferencesDetailsPanel.add(Box.createVerticalStrut(5));
        preferencesDetailsPanel.add(purposeOptions2);
        preferencesDetailsPanel.add(Box.createVerticalStrut(20));
        preferencesDetailsPanel.setVisible(false);

        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));
        containerPanel.setAlignmentX(LEFT_ALIGNMENT);
        containerPanel.add(prefsPanel);
        containerPanel.add(preferencesDetailsPanel);
        containerPanel.setBorder(new LineBorder(new Color(220, 220, 220), 1));

        prefsPanel.setBorder(new EmptyBorder(0, 0, 0, 0));

        buttonWrapper.setBackground(BACKGROUND_COLOR);
        labelWrapper.setBackground(BACKGROUND_COLOR);

        showDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                boolean isVisible = preferencesDetailsPanel.isVisible();
                preferencesDetailsPanel.setVisible(!isVisible);

                if (isVisible) 
                {
                    showDetails.setText("Show Details");
                    containerPanel.setBorder(new LineBorder(new Color(220, 220, 220), 1));
                    prefsPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 40));
                } else 
                {
                    showDetails.setText("Hide Details");
                    containerPanel.setBorder(new LineBorder(new Color(220, 220, 220), 1));
                    prefsPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 40));
                }

                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        mainPanel.add(containerPanel);
        mainPanel.add(Box.createVerticalStrut(20));

        // -------------------------
        // 2. Estimated Time of Arrival
        // -------------------------
        
        JPanel arrivalPanel = new JPanel();
        arrivalPanel.setLayout(new BoxLayout(arrivalPanel, BoxLayout.Y_AXIS));
        arrivalPanel.setBackground(BACKGROUND_COLOR);
        arrivalPanel.setAlignmentX(LEFT_ALIGNMENT);

        
        JLabel arrivalTitle = new JLabel("Estimated Time of Arrival");
        arrivalTitle.setFont(SUBHEADER_FONT.deriveFont(Font.BOLD, 15f));
        arrivalTitle.setAlignmentX(LEFT_ALIGNMENT);
        arrivalPanel.add(arrivalTitle);

        JLabel arrivalNote = new JLabel(
            "If your arrival time is before the standard check-in time, we will do our best to accommodate you."
        );
        arrivalNote.setFont(LABEL_FONT);
        arrivalNote.setForeground(TEXT_COLOR);
        arrivalNote.setAlignmentX(LEFT_ALIGNMENT);
        arrivalPanel.add(arrivalNote);
        arrivalPanel.add(Box.createVerticalStrut(10));

        
        JLabel timeLabel = new JLabel("Time of Arrival");
        timeLabel.setFont(LABEL_FONT.deriveFont(Font.BOLD)); // Make text bold
        timeLabel.setAlignmentX(LEFT_ALIGNMENT);
        arrivalPanel.add(timeLabel);
        arrivalPanel.add(Box.createVerticalStrut(5));

        JComboBox<String> timeBox = new JComboBox<>(new String[] {
            "Please select Time of Arrival", "09:00 - 10:00", "10:00 - 11:00", "..."
        });
        timeBox.setFont(LABEL_FONT);
        timeBox.setPreferredSize(new Dimension(300, 35));
        timeBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        timeBox.setAlignmentX(LEFT_ALIGNMENT);
        arrivalPanel.add(timeBox);

        mainPanel.add(arrivalPanel);
        mainPanel.add(Box.createVerticalStrut(20));

        // -------------------------
        // 3. Special Request
        // -------------------------
        
        JPanel specialPanel = new JPanel();
        specialPanel.setLayout(new BoxLayout(specialPanel, BoxLayout.Y_AXIS));
        specialPanel.setBackground(BACKGROUND_COLOR);
        specialPanel.setAlignmentX(LEFT_ALIGNMENT);

        
        JLabel specialTitle = new JLabel("Special Request");
        specialTitle.setFont(SUBHEADER_FONT.deriveFont(Font.BOLD, 15f));
        specialTitle.setAlignmentX(LEFT_ALIGNMENT);
        specialPanel.add(specialTitle);

        
        JLabel specialNote = new JLabel(
            "Please let us know of any additional requests to help us ensure you have a comfortable stay."
        );
        specialNote.setFont(LABEL_FONT);
        specialNote.setForeground(TEXT_COLOR);
        specialNote.setAlignmentX(LEFT_ALIGNMENT);
        specialPanel.add(specialNote);
        specialPanel.add(Box.createVerticalStrut(10));

        
        JTextArea requestArea = new JTextArea();
        requestArea.setFont(LABEL_FONT);
        requestArea.setLineWrap(true);
        requestArea.setWrapStyleWord(true);

        
        JScrollPane scrollArea = new JScrollPane(requestArea);
        scrollArea.setBorder(new LineBorder(new Color(200, 200, 200), 1));
        scrollArea.setPreferredSize(new Dimension(300, 80));
        scrollArea.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        scrollArea.setAlignmentX(LEFT_ALIGNMENT);
        specialPanel.add(scrollArea);
        specialPanel.add(Box.createVerticalStrut(5));

        
        JLabel counter = new JLabel("0/300");
        counter.setFont(NOTE_FONT);
        counter.setForeground(new Color(150, 150, 150));
        counter.setAlignmentX(RIGHT_ALIGNMENT);

        
        JPanel counterWrapper = new JPanel(new BorderLayout());
        counterWrapper.setBackground(BACKGROUND_COLOR);
        counterWrapper.setAlignmentX(LEFT_ALIGNMENT);
        counterWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, 15));
        counterWrapper.add(counter, BorderLayout.EAST);
        specialPanel.add(counterWrapper);

        mainPanel.add(specialPanel);

        return mainPanel;
    }

    
    //	=================================================================
    //							JOIN SHANGRI-LA
    //	=================================================================

    
    private JPanel createJoinCirclePanel() 
    {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(BOX_GRAY_COLOR);
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(Color.LIGHT_GRAY, 1),
            new EmptyBorder(20, 20, 20, 20)
        ));
        mainPanel.setAlignmentX(LEFT_ALIGNMENT);
        
        
        JLabel title = new JLabel("Join Shangri-La Circle");
        title.setFont(HEADER_FONT.deriveFont(Font.BOLD, 18f));
        title.setAlignmentX(LEFT_ALIGNMENT);
        mainPanel.add(title);
        
        
        JLabel subtitle = new JLabel("Enroll as a Shangri-La Circle member to enjoy exclusive privileges and rewards");
        subtitle.setBorder(new EmptyBorder(5, 0, 15, 0));
        subtitle.setFont(LABEL_FONT);
        subtitle.setAlignmentX(LEFT_ALIGNMENT);
        mainPanel.add(subtitle);
        
        // ICON PANEL using FlowLayout
        JPanel iconPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0)); // center aligned, 10px horizontal gap
        iconPanel.setBackground(BOX_GRAY_COLOR);
        iconPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(iconPanel);
        mainPanel.add(Box.createVerticalStrut(15));

        // MEMBER OFFER
        JPanel memberOffer = new JPanel();
        memberOffer.setBackground(BOX_GRAY_COLOR);
        memberOffer.setLayout(new BoxLayout(memberOffer, BoxLayout.Y_AXIS));
        ImageIcon memberIcon = new ImageIcon("images/member.png");
        Image imgMember = memberIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        JLabel icon1 = new JLabel(new ImageIcon(imgMember));
        icon1.setAlignmentX(Component.CENTER_ALIGNMENT);
        memberOffer.add(icon1);
        memberOffer.add(Box.createVerticalStrut(5));
        JLabel label1 = new JLabel("<html><div style='text-align: center;'>Member Exclusive Offers</div></html>");
        label1.setFont(NOTE_FONT);
        label1.setAlignmentX(Component.CENTER_ALIGNMENT);
        memberOffer.add(label1);
        iconPanel.add(memberOffer);

        // MOBILE CHECK-IN
        JPanel mobileCheckIn = new JPanel();
        mobileCheckIn.setBackground(BOX_GRAY_COLOR);
        mobileCheckIn.setLayout(new BoxLayout(mobileCheckIn, BoxLayout.Y_AXIS));
        ImageIcon mobileIcon = new ImageIcon("images/mobile.png");
        Image imgMobile = mobileIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        JLabel icon2 = new JLabel(new ImageIcon(imgMobile));
        icon2.setAlignmentX(Component.CENTER_ALIGNMENT);
        mobileCheckIn.add(icon2);
        mobileCheckIn.add(Box.createVerticalStrut(5));
        JLabel label2 = new JLabel("<html><div style='text-align: center;'>Mobile Check-In & Out</div></html>");
        label2.setFont(NOTE_FONT);
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        mobileCheckIn.add(label2);
        iconPanel.add(mobileCheckIn);

        // CHILDREN EAT FREE
        JPanel childrenEatFree = new JPanel();
        childrenEatFree.setBackground(BOX_GRAY_COLOR);
        childrenEatFree.setLayout(new BoxLayout(childrenEatFree, BoxLayout.Y_AXIS));
        ImageIcon childIcon = new ImageIcon("images/kids.png");
        Image imgChild = childIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        JLabel icon3 = new JLabel(new ImageIcon(imgChild));
        icon3.setAlignmentX(Component.CENTER_ALIGNMENT);
        childrenEatFree.add(icon3);
        childrenEatFree.add(Box.createVerticalStrut(5));
        JLabel label3 = new JLabel("<html><div style='text-align: center;'>Children Eat Free</div></html>");
        label3.setFont(NOTE_FONT);
        label3.setAlignmentX(Component.CENTER_ALIGNMENT);
        childrenEatFree.add(label3);
        iconPanel.add(childrenEatFree);

        // EARN FREE NIGHTS
        JPanel earnFreeNights = new JPanel();
        earnFreeNights.setBackground(BOX_GRAY_COLOR);
        earnFreeNights.setLayout(new BoxLayout(earnFreeNights, BoxLayout.Y_AXIS));
        ImageIcon nightsIcon = new ImageIcon("images/nights.png");
        Image imgNights = nightsIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        JLabel icon4 = new JLabel(new ImageIcon(imgNights));
        icon4.setAlignmentX(Component.CENTER_ALIGNMENT);
        earnFreeNights.add(icon4);
        earnFreeNights.add(Box.createVerticalStrut(5));
        JLabel label4 = new JLabel("<html><div style='text-align: center;'>Earn Free Nights</div></html>");
        label4.setFont(NOTE_FONT);
        label4.setAlignmentX(Component.CENTER_ALIGNMENT);
        earnFreeNights.add(label4);
        iconPanel.add(earnFreeNights);

        
        JCheckBox joinCheck = new JCheckBox("I would like to join Shangri-La Circle.");
        joinCheck.setBackground(BOX_GRAY_COLOR);
        joinCheck.setFont(LABEL_FONT);
        joinCheck.setAlignmentX(LEFT_ALIGNMENT);
        joinCheck.setMaximumSize(new Dimension(Integer.MAX_VALUE, joinCheck.getPreferredSize().height));
        mainPanel.add(joinCheck);
        mainPanel.add(Box.createVerticalStrut(15));
        

        JLabel joinLabel = new JLabel("Join now using a password");
        joinLabel.setFont(joinLabel.getFont().deriveFont(Font.BOLD));
        joinLabel.setAlignmentX(LEFT_ALIGNMENT);
        
        
        JPanel switchRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        switchRow.setBackground(BOX_GRAY_COLOR);
        switchRow.setAlignmentX(LEFT_ALIGNMENT);
        switchRow.add(joinLabel);
        switchRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, switchRow.getPreferredSize().height));
        mainPanel.add(switchRow);
        
        
        JTextField passwordField = new JTextField();
        passwordField.setAlignmentX(LEFT_ALIGNMENT);
        passwordField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        mainPanel.add(passwordField);

        
        JTextArea passwordHint = new JTextArea("Your password must contain at least 8 characters, with a combination of English letters (a - z, A - Z) and numeric digits (0 - 9). At least one of the characters must be a numeric digit.");
        passwordHint.setWrapStyleWord(true);
        passwordHint.setLineWrap(true);
        passwordHint.setEditable(false);
        passwordHint.setOpaque(false); 
        passwordHint.setFont(NOTE_FONT.deriveFont(Font.ITALIC));
        passwordHint.setBorder(new EmptyBorder(5, 0, 10, 0));
        passwordHint.setAlignmentX(LEFT_ALIGNMENT);
        passwordHint.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        mainPanel.add(passwordHint);
        
        
        JLabel signInLink = new JLabel("<html>Already a member? <b style='color: " + String.format("#%06x", PRIMARY_ACTION_COLOR.getRGB() & 0xFFFFFF) + ";'>Sign in now ></b></html>");
        signInLink.setFont(LABEL_FONT);
        signInLink.setAlignmentX(LEFT_ALIGNMENT);
        signInLink.setMaximumSize(new Dimension(Integer.MAX_VALUE, signInLink.getPreferredSize().height));
        mainPanel.add(signInLink);
        
        return mainPanel;
    }
    
    
    //	=================================================================
    //							TERMS AND CONDITIONS
    //	=================================================================
    
    
    private JPanel createTermsAndConditionsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(BACKGROUND_COLOR);
        panel.setAlignmentX(LEFT_ALIGNMENT);
        panel.setBorder(new EmptyBorder(0, 0, 15, 0));
        
        
        JCheckBox agreeAllCheck = new JCheckBox("<html><b>I agree to all the following terms and conditions.</b></html>");
        agreeAllCheck.setBackground(BACKGROUND_COLOR);
        agreeAllCheck.setFont(LABEL_FONT);
        agreeAllCheck.setAlignmentX(LEFT_ALIGNMENT);
        panel.add(agreeAllCheck);
        panel.add(Box.createVerticalStrut(10));

       
        JCheckBox termsCheck = new JCheckBox("<html>"
        		+ "I have read and agree to the <b style='color: " + String.format("#%06x", LINK_COLOR.getRGB() & 0xFFFFFF) + ";'>"
        		+ "Terms and Conditions (including cancellation policy)</b>, "
        		+ "the Shangri-La Circle <b style='color: " + String.format("#%06x", LINK_COLOR.getRGB() & 0xFFFFFF) 
        		+ ";'>Terms and Conditions</b>, the <b style='color: " + String.format("#%06x", LINK_COLOR.getRGB() & 0xFFFFFF)  + ";'>Privacy Policy</b> "
        		+ "and the <b style='color: " + String.format("#%06x", LINK_COLOR.getRGB() & 0xFFFFFF)  + ";'>Cookies Policy</b>.</html>");
        termsCheck.setBackground(BACKGROUND_COLOR);
        termsCheck.setFont(LABEL_FONT);
        termsCheck.setAlignmentX(LEFT_ALIGNMENT);
        panel.add(termsCheck);
        panel.add(Box.createVerticalStrut(10));

        
        JCheckBox privacyCheck = new JCheckBox("<html>"
        		+ "I agree to have my personal information "
        		+ "transferred outside of China according "
        		+ "to the Privacy Policy, including for "
        		+ "the loyalty management purpose when I join Shangri-La Circle."
        		+ "</html>");
        privacyCheck.setBackground(BACKGROUND_COLOR);
        privacyCheck.setFont(LABEL_FONT);
        privacyCheck.setAlignmentX(LEFT_ALIGNMENT);
        panel.add(privacyCheck);
        panel.add(Box.createVerticalStrut(10));

        
        JCheckBox marketingCheck = new JCheckBox("I would like to receive account updates, programme news and exclusive offers from Shangri-La and selected partners.");
        marketingCheck.setBackground(BACKGROUND_COLOR);
        marketingCheck.setFont(LABEL_FONT);
        marketingCheck.setAlignmentX(LEFT_ALIGNMENT);
        panel.add(marketingCheck);
        
        return panel;
    }

    
    //	=================================================================
    //								CHARGES
    //	=================================================================

    
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
        
        
        JButton nextStepButton = new JButton("Next Step");
        nextStepButton.setFont(new Font("Arial", Font.BOLD, 15));
        nextStepButton.setBackground(new Color(255, 170, 0));
        nextStepButton.setForeground(Color.WHITE);
        nextStepButton.setOpaque(true);
        nextStepButton.setFocusPainted(false);
        nextStepButton.setBorderPainted(false);
        nextStepButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        nextStepButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        nextStepButton.setPreferredSize(new Dimension(0, 45));
        // nextStepButton.addActionListener(e -> showCustomiseStayPage());
        contentPanel.add(nextStepButton);
        contentPanel.add(Box.createVerticalStrut(10));

        
        JButton bookNowButton = new JButton("Book Now");
        bookNowButton.setFont(new Font("Arial", Font.BOLD, 15));
        bookNowButton.setBackground(Color.WHITE);
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