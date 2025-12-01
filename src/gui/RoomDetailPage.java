package gui;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import models.Room;
import models.ImagePanel;

public class RoomDetailPage extends JFrame {

    private static final long serialVersionUID = 1L;

    // --- State Management ---
    private String tempCheckInDate = null;  // Stores 1st click
    private String checkInDate = null;      // Final start date
    private String checkOutDate = null;     // Final end date
    
    // UI References
    private JLabel lblDateDisplay;
    private JLabel lblGuestDisplay;

    // Guest Configuration State
    private class RoomConfig {
        int adults = 2;
        int children = 0;
    }
    private List<RoomConfig> activeRooms = new ArrayList<>();

    // Business Logic Constraints
    private final int MAX_ROOMS = 3;
    private final int MAX_PER_ROOM = 4;
    private final int MAX_TOTAL_GUESTS = 6;

    public RoomDetailPage(Room room) {
        activeRooms.add(new RoomConfig());

        setTitle(room.getName() + " - Shangri-La");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(50, 50, 1280, 800);
        setLocationRelativeTo(null);

        // --- Main Layout ---
        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));
        mainContainer.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(mainContainer);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20); // Faster scrolling
        setContentPane(scrollPane);
        
        // 1. Navigation
        NavBar navBar = new NavBar(); 
        navBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        mainContainer.add(navBar);

        // 2. Hero Image
        ImagePanel headerImg = new ImagePanel(room.getImagePath()); 
        headerImg.setPreferredSize(new Dimension(1280, 400));
        headerImg.setMaximumSize(new Dimension(Integer.MAX_VALUE, 400));
        headerImg.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContainer.add(headerImg);

        // 3. Content Wrapper (Max-width 1100px)
        JPanel bodyWrapper = new JPanel();
        bodyWrapper.setLayout(new BoxLayout(bodyWrapper, BoxLayout.Y_AXIS));
        bodyWrapper.setBackground(Color.WHITE);
        bodyWrapper.setMaximumSize(new Dimension(1100, Integer.MAX_VALUE));
        bodyWrapper.setAlignmentX(Component.CENTER_ALIGNMENT);
        bodyWrapper.setBorder(new EmptyBorder(0, 30, 50, 30));

        // --- Header Section (Title & Phone) ---
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setBorder(new EmptyBorder(30, 0, 10, 0));
        titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        titlePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel leftTitle = new JPanel();
        leftTitle.setLayout(new BoxLayout(leftTitle, BoxLayout.Y_AXIS));
        leftTitle.setBackground(Color.WHITE);

        JLabel lblHotelName = new JLabel(" |  Makati Shangri-La, Manila");
        lblHotelName.setFont(new Font("Serif", Font.BOLD, 14));
        lblHotelName.setForeground(new Color(197, 160, 89)); // Brand Gold
        lblHotelName.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftTitle.add(lblHotelName);
        
        JLabel lblRoomName = new JLabel(room.getName());
        lblRoomName.setFont(new Font("Serif", Font.PLAIN, 36));
        lblRoomName.setForeground(new Color(50,50,50));
        lblRoomName.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftTitle.add(lblRoomName);

        titlePanel.add(leftTitle, BorderLayout.WEST);
        titlePanel.add(new JLabel("<html><div style='text-align:right;'>Reservation number<br><b>63 2 8231 3712</b></div></html>"), BorderLayout.EAST);

        bodyWrapper.add(titlePanel);

        // --- Split Layout (Details vs Booking Card) ---
        JPanel contentArea = new JPanel(new GridBagLayout());
        contentArea.setBackground(Color.WHITE);
        contentArea.setBorder(new EmptyBorder(20, 0, 0, 0));
        contentArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.NORTH;

        // Left Col: Room Details (55%)
        gbc.gridx = 0; 
        gbc.gridy = 0;
        gbc.weightx = 0.55; 
        gbc.weighty = 1.0;
        gbc.insets = new Insets(0, 0, 0, 40); 
        contentArea.add(buildDetailsPanel(room), gbc);

        // Right Col: Booking Card (45%)
        gbc.gridx = 1; 
        gbc.weightx = 0.45;
        gbc.insets = new Insets(0, 0, 0, 0);
        contentArea.add(buildBookingCard(), gbc);

        bodyWrapper.add(contentArea);
        mainContainer.add(bodyWrapper);
    }

    // =================================================================
    // UI BUILDERS
    // =================================================================

    /**
     * Builds the left column: Description, Features, Amenities.
     */
    private JPanel buildDetailsPanel(Room room) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(Color.WHITE);

        // --- Description ---
        String fullDesc = room.getDescription();
        String headlineText = "";
        String bodyText = fullDesc;

        if (fullDesc.contains("\n")) {
            String[] parts = fullDesc.split("\n", 2);
            headlineText = parts[0];
            bodyText = parts[1];
        }

        if (!headlineText.isEmpty()) {
            JTextArea headline = new JTextArea(headlineText);
            headline.setFont(new Font("Serif", Font.PLAIN, 20));
            headline.setForeground(new Color(40, 40, 40));
            headline.setLineWrap(true);
            headline.setWrapStyleWord(true);
            headline.setEditable(false);
            headline.setAlignmentX(Component.LEFT_ALIGNMENT);
            p.add(headline);
            p.add(Box.createVerticalStrut(10));
        }

        JTextArea desc = new JTextArea(bodyText);
        desc.setFont(new Font("SansSerif", Font.PLAIN, 14));
        desc.setForeground(new Color(80, 80, 80));
        desc.setLineWrap(true);
        desc.setWrapStyleWord(true);
        desc.setEditable(false);
        desc.setAlignmentX(Component.LEFT_ALIGNMENT); 
        p.add(desc);
        p.add(Box.createVerticalStrut(40));

        // --- Features ---
        addHeader(p, "Features");
        addBullet(p, room.getSize());
        for(String f : room.getFeatures()) {
            addBullet(p, f);
        }
        p.add(Box.createVerticalStrut(25));

        // --- Amenities ---
        addHeader(p, "Amenities");
        for(String amenity : room.getAmenities()) {
             addBullet(p, amenity);
        }

        p.add(Box.createVerticalStrut(40));
        
        // Static Policy Text
        addHeader(p, "Children's meal plan");
        JTextArea childTxt = new JTextArea("The following children’s meal plan is applicable for all Shangri-La Circle members. When accompanied by a dine-in adult, up to 2 children of registered in-house hotel guests at the age of 6 and below can enjoy buffet meals at the all-day dining venues at no additional charge. Additional children at the age of 6 and below and all children who are above 6 years of age but under 12 years of age will receive a 50% discount on the adult buffet price. Registered in-house hotel guests can also join Shangri-La Circle at any time during their stay to enjoy the meal plan.");
        childTxt.setFont(new Font("SansSerif", Font.PLAIN, 11));
        childTxt.setForeground(Color.GRAY);
        childTxt.setLineWrap(true);
        childTxt.setWrapStyleWord(true);
        childTxt.setEditable(false);
        childTxt.setAlignmentX(Component.LEFT_ALIGNMENT);
        p.add(childTxt);

        return p;
    }

    /**
     * Builds the right column: Sticky booking card.
     */
    private JPanel buildBookingCard() {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS)); 
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));

        // Card Header (Gray background)
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(new Color(244, 244, 244)); 
        headerPanel.setBorder(new EmptyBorder(25, 0, 25, 0)); 
        
        Dimension headerSize = new Dimension(Integer.MAX_VALUE, 90);
        headerPanel.setPreferredSize(headerSize);
        headerPanel.setMaximumSize(headerSize);

        JLabel lblReady = new JLabel("Ready to book?");
        lblReady.setFont(new Font("Serif", Font.PLAIN, 22));
        lblReady.setForeground(new Color(60, 60, 60));
        lblReady.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.add(lblReady);
        card.add(headerPanel);

        // Card Body (Inputs)
        JPanel bodyPanel = new JPanel();
        bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));
        bodyPanel.setBackground(Color.WHITE);
        bodyPanel.setBorder(new EmptyBorder(20, 25, 30, 25));

        // Input 1: Calendar Trigger
        JPanel dateRow = createDropdownRow("Dates", "Select Dates...");
        lblDateDisplay = (JLabel) dateRow.getClientProperty("valueLabel");
        dateRow.setCursor(new Cursor(Cursor.HAND_CURSOR));
        dateRow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showCalendarPopup(dateRow); 
            }
        });
        bodyPanel.add(dateRow);
        bodyPanel.add(Box.createVerticalStrut(15)); 

        // Input 2: Guest/Room Trigger
        JPanel guestRow = createDropdownRow("Rooms", "1 Room, 1 Adult, 0 Children");
        lblGuestDisplay = (JLabel) guestRow.getClientProperty("valueLabel");
        guestRow.setCursor(new Cursor(Cursor.HAND_CURSOR));
        guestRow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showGuestPopup(guestRow); 
            }
        });
        bodyPanel.add(guestRow);
        bodyPanel.add(Box.createVerticalStrut(15));

        // Input 3: Promo Code
        bodyPanel.add(createDropdownRow("Special Code", "Special Code"));
        bodyPanel.add(Box.createVerticalStrut(30));

        // Action: Book Button
        JButton btnBook = new JButton("Book Now");
        btnBook.setBackground(new Color(143, 113, 55)); 
        btnBook.setForeground(Color.WHITE);
        btnBook.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnBook.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBook.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45)); 
        btnBook.setFocusPainted(false);
        btnBook.setBorderPainted(false);
        
        // Debug output on click
        btnBook.addActionListener(e -> {
            System.out.println("--- BOOKING REQUEST ---");
            System.out.println("Check-in: " + checkInDate);
            System.out.println("Check-out: " + checkOutDate);
            System.out.println("Total Rooms: " + activeRooms.size());
            for(int i=0; i<activeRooms.size(); i++) {
                RoomConfig r = activeRooms.get(i);
                System.out.println("  Room " + (i+1) + ": Adults=" + r.adults + ", Children=" + r.children);
            }
        });
        
        bodyPanel.add(btnBook);
        card.add(bodyPanel);

        // Fixed Wrapper (prevents vertical stretching)
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(Color.WHITE);
        wrapper.add(card, BorderLayout.NORTH);
        wrapper.setPreferredSize(new Dimension(380, 500));
        wrapper.setMinimumSize(new Dimension(380, 500));
        
        return wrapper;
    }

    // =================================================================
    // LOGIC: GUEST SELECTOR POPUP
    // =================================================================
    
    private void showGuestPopup(Component invoker) {
        JPopupMenu popup = new JPopupMenu();
        popup.setBackground(Color.WHITE);
        popup.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setPreferredSize(new Dimension(380, 300)); 

        // Header
        JLabel lblHeader = new JLabel("Max. " + MAX_PER_ROOM + " guests per room");
        lblHeader.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblHeader.setForeground(new Color(50, 50, 50));
        lblHeader.setBorder(new EmptyBorder(10, 15, 10, 15));
        lblHeader.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(lblHeader);

        // Column Headers (GridBag for alignment)
        JPanel colHeader = new JPanel(new GridBagLayout());
        colHeader.setBackground(new Color(245, 245, 245)); 
        colHeader.setBorder(new EmptyBorder(8, 10, 8, 10));
        colHeader.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.25;
        
        gbc.gridx = 0; colHeader.add(new JLabel(""), gbc);
        
        JLabel h1 = new JLabel("Adult(s)");
        h1.setFont(new Font("SansSerif", Font.BOLD, 11));
        h1.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 1; colHeader.add(h1, gbc);
        
        JLabel h2 = new JLabel("Children (under 12)");
        h2.setFont(new Font("SansSerif", Font.BOLD, 11));
        h2.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 2; colHeader.add(h2, gbc);
        
        gbc.gridx = 3; gbc.weightx = 0.1; colHeader.add(new JLabel(""), gbc);
        
        mainPanel.add(colHeader);

        // Render Rows dynamically based on 'activeRooms' list
        JPanel rowsPanel = new JPanel();
        rowsPanel.setLayout(new BoxLayout(rowsPanel, BoxLayout.Y_AXIS));
        rowsPanel.setBackground(Color.WHITE);
        
        for (int i = 0; i < activeRooms.size(); i++) {
            rowsPanel.add(createRoomRow(i, popup, invoker));
            rowsPanel.add(Box.createVerticalStrut(5));
        }
        mainPanel.add(rowsPanel);

        // Footer: Add Room Button
        JPanel footer = new JPanel();
        footer.setBackground(Color.WHITE);
        footer.setBorder(new EmptyBorder(10, 15, 10, 15));
        
        JButton btnAdd = new JButton("+ Add Room");
        btnAdd.setFont(new Font("SansSerif", Font.PLAIN, 14));
        btnAdd.setForeground(new Color(153, 128, 60)); // Brand Gold
        btnAdd.setBackground(Color.WHITE); 
        btnAdd.setBorder(BorderFactory.createLineBorder(new Color(153, 128, 60))); 
        btnAdd.setPreferredSize(new Dimension(300, 35));
        btnAdd.setFocusPainted(false);
        btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add Room Logic
        btnAdd.addActionListener(e -> {
            if (activeRooms.size() < MAX_ROOMS && getTotalPeople() < MAX_TOTAL_GUESTS) {
                activeRooms.add(new RoomConfig());
                updateGuestLabel();
                popup.setVisible(false);
                showGuestPopup(invoker); // Re-open to refresh UI
            } else {
                JOptionPane.showMessageDialog(null, "Maximum capacity reached.");
            }
        });
        
        footer.add(btnAdd);
        mainPanel.add(footer);

        popup.add(mainPanel);
        popup.show(invoker, 0, invoker.getHeight());
    }

    private JPanel createRoomRow(int index, JPopupMenu popup, Component invoker) {
        JPanel row = new JPanel(new GridBagLayout());
        row.setBackground(Color.WHITE);
        row.setBorder(new EmptyBorder(5, 10, 5, 10));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 0.25;

        // Label
        JLabel lblRoomName = new JLabel("Room " + (index + 1));
        lblRoomName.setFont(new Font("SansSerif", Font.BOLD, 13));
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        row.add(lblRoomName, gbc);

        RoomConfig data = activeRooms.get(index);

        // Counters (Adults & Children)
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        row.add(createCounter(data, true), gbc);

        gbc.gridx = 2;
        row.add(createCounter(data, false), gbc);
        
        // Delete Button (Only if > 1 room)
        gbc.gridx = 3;
        gbc.weightx = 0.1;
        
        if (index > 0) {
            JButton btnDelete = new JButton("x");
            btnDelete.setBorder(null);
            btnDelete.setBackground(Color.WHITE);
            btnDelete.setForeground(Color.GRAY);
            btnDelete.setFont(new Font("SansSerif", Font.BOLD, 14));
            btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnDelete.setPreferredSize(new Dimension(30, 30));
            
            btnDelete.addActionListener(e -> {
                activeRooms.remove(index);
                updateGuestLabel();
                popup.setVisible(false);
                showGuestPopup(invoker); // Refresh UI
            });
            row.add(btnDelete, gbc);
        } else {
            row.add(Box.createHorizontalStrut(30), gbc);
        }
        
        return row;
    }

    // Helper: Creates the +/- Control Box
    private JPanel createCounter(RoomConfig data, boolean isAdult) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200))); 
        panel.setPreferredSize(new Dimension(90, 30)); 

        JButton btnMinus = new JButton("–");
        btnMinus.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnMinus.setBorderPainted(false);
        btnMinus.setContentAreaFilled(false);
        btnMinus.setForeground(Color.GRAY);
        btnMinus.setPreferredSize(new Dimension(30, 30));

        JLabel lblVal = new JLabel(String.valueOf(isAdult ? data.adults : data.children));
        lblVal.setHorizontalAlignment(SwingConstants.CENTER);
        lblVal.setFont(new Font("SansSerif", Font.PLAIN, 13));

        JButton btnPlus = new JButton("+");
        btnPlus.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnPlus.setBorderPainted(false);
        btnPlus.setContentAreaFilled(false);
        btnPlus.setForeground(new Color(153, 128, 60)); 
        btnPlus.setPreferredSize(new Dimension(30, 30));

        // Logic: Decrement
        btnMinus.addActionListener(e -> {
            if (isAdult) {
                if (data.adults > 0) data.adults--;
            } else {
                if (data.children > 0) data.children--;
            }
            lblVal.setText(String.valueOf(isAdult ? data.adults : data.children));
            updateGuestLabel();
        });

        // Logic: Increment (Check constraints)
        btnPlus.addActionListener(e -> {
            int currentRoomTotal = data.adults + data.children;
            if (getTotalPeople() < MAX_TOTAL_GUESTS && currentRoomTotal < MAX_PER_ROOM) {
                if (isAdult) data.adults++;
                else data.children++;
                lblVal.setText(String.valueOf(isAdult ? data.adults : data.children));
                updateGuestLabel();
            }
        });

        panel.add(btnMinus, BorderLayout.WEST);
        panel.add(lblVal, BorderLayout.CENTER);
        panel.add(btnPlus, BorderLayout.EAST);
        return panel;
    }

    private void updateGuestLabel() {
        int totalAdults = 0;
        int totalChildren = 0;
        for (RoomConfig r : activeRooms) {
            totalAdults += r.adults;
            totalChildren += r.children;
        }
        lblGuestDisplay.setText(activeRooms.size() + " Room(s), " + totalAdults + " Adult, " + totalChildren + " Children");
    }

    private int getTotalPeople() {
        int total = 0;
        for (RoomConfig r : activeRooms) total += (r.adults + r.children);
        return total;
    }

    // =================================================================
    // LOGIC: CALENDAR POPUP
    // =================================================================
    
    private void showCalendarPopup(Component invoker) {
        tempCheckInDate = null; // Reset selection flow

        JPopupMenu popup = new JPopupMenu();
        popup.setBackground(Color.WHITE);
        popup.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setPreferredSize(new Dimension(800, 420)); 

        // Nav Arrows (Visual only for demo)
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(new EmptyBorder(0, 0, 20, 0));
        
        JLabel btnPrev = new JLabel("<");
        btnPrev.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnPrev.setForeground(Color.GRAY);
        
        JLabel btnNext = new JLabel(">");
        btnNext.setFont(new Font("SansSerif", Font.PLAIN, 18));
        btnNext.setForeground(Color.BLACK);

        headerPanel.add(btnPrev, BorderLayout.WEST);
        headerPanel.add(btnNext, BorderLayout.EAST);
        mainPanel.add(headerPanel);

        // Month Grid Container (Shows Dec & Jan)
        JPanel monthsWrapper = new JPanel(new GridLayout(1, 2, 40, 0));
        monthsWrapper.setBackground(Color.WHITE);
        monthsWrapper.add(createMonthPanel(popup, "Dec 2025", 1, 31, -1, -1)); 
        monthsWrapper.add(createMonthPanel(popup, "Jan 2026", 4, 31, -1, -1)); 
        mainPanel.add(monthsWrapper);
        
        // Footer Note
        JPanel footer = new JPanel(new BorderLayout());
        footer.setBackground(Color.WHITE);
        footer.setBorder(new EmptyBorder(20, 0, 0, 0));
        
        JLabel lblPriceNote = new JLabel("Price is for reference only.");
        lblPriceNote.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblPriceNote.setForeground(Color.GRAY);
        
        footer.add(lblPriceNote, BorderLayout.WEST);
        mainPanel.add(footer);

        popup.add(mainPanel);
        int xOffset = (invoker.getWidth() - 800) / 2; // Center over invoker
        popup.show(invoker, xOffset, invoker.getHeight() + 5);
    }

    private JPanel createMonthPanel(JPopupMenu popup, String title, int startOffset, int daysInMonth, int selStart, int selEnd) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(Color.WHITE);

        // Month Title
        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.add(lblTitle);
        p.add(Box.createVerticalStrut(15));

        // Weekday Headers
        JPanel weekHeader = new JPanel(new GridLayout(1, 7));
        weekHeader.setBackground(Color.WHITE);
        String[] days = {"Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"};
        for (String d : days) {
            JLabel l = new JLabel(d, SwingConstants.CENTER);
            l.setFont(new Font("SansSerif", Font.PLAIN, 12));
            l.setForeground(Color.GRAY);
            weekHeader.add(l);
        }
        p.add(weekHeader);
        p.add(Box.createVerticalStrut(5));

        // Date Grid
        JPanel grid = new JPanel(new GridLayout(0, 7, 3, 3));
        grid.setBackground(Color.WHITE);

        // Pad start of month
        for (int i = 0; i < startOffset; i++) {
            JLabel spacer = new JLabel("");
            spacer.setPreferredSize(new Dimension(45, 45));
            grid.add(spacer); 
        }

        String[] parts = title.split(" ");
        String monthName = parts[0]; 
        String yearName = parts[1];

        // Fill Days
        for (int i = 1; i <= daysInMonth; i++) {
            boolean isSelected = (i >= selStart && i <= selEnd);
            
            // Dummy logic for prices
            String price = (i % 2 == 0) ? "8.2k" : "9.3k"; 
            if(i == 2) price = "16.3k"; 

            grid.add(createDayCell(popup, monthName, yearName, i, price, isSelected));
        }
        p.add(grid);
        return p;
    }

    private JPanel createDayCell(JPopupMenu popup, String month, String year, int day, String price, boolean isSelected) {
        JPanel cell = new JPanel();
        cell.setLayout(new BoxLayout(cell, BoxLayout.Y_AXIS));
        Dimension dim = new Dimension(45, 45);
        cell.setPreferredSize(dim);
        cell.setMaximumSize(dim);
        
        cell.setBackground(isSelected ? new Color(153, 126, 61) : Color.WHITE);

        JLabel lblDay = new JLabel(String.valueOf(day));
        lblDay.setFont(new Font("SansSerif", Font.PLAIN, 14));
        lblDay.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblDay.setForeground(isSelected ? Color.WHITE : Color.BLACK);
        
        JLabel lblPrice = new JLabel(price);
        lblPrice.setFont(new Font("SansSerif", Font.PLAIN, 10));
        lblPrice.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblPrice.setForeground(isSelected ? Color.WHITE : new Color(180, 140, 60)); 

        cell.add(Box.createVerticalGlue());
        cell.add(lblDay);
        cell.add(Box.createVerticalStrut(2));
        cell.add(lblPrice);
        cell.add(Box.createVerticalGlue());
        
        if (!isSelected) {
            cell.setCursor(new Cursor(Cursor.HAND_CURSOR));
            cell.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    String clickedDate = month + " " + day + ", " + year;
                    
                    // Logic: 1st click = Start Date, 2nd Click = End Date
                    if (tempCheckInDate == null) {
                        tempCheckInDate = clickedDate;
                        checkInDate = clickedDate; 
                        lblDateDisplay.setText(tempCheckInDate + " / Select Check-out...");
                        cell.setBackground(new Color(240, 240, 240)); // Feedback
                    } else {
                        checkOutDate = clickedDate;
                        lblDateDisplay.setText(tempCheckInDate + " / " + clickedDate);
                        popup.setVisible(false);
                        tempCheckInDate = null;
                    }
                }
            });
        }
        return cell;
    }

    // =================================================================
    // GENERIC HELPERS (Typography & Dropdowns)
    // =================================================================
    
    private void addHeader(JPanel p, String text) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("Serif", Font.BOLD, 15));
        l.setForeground(new Color(30, 30, 30));
        l.setAlignmentX(Component.LEFT_ALIGNMENT); 
        p.add(l);
        p.add(Box.createVerticalStrut(8));
    }
    
    private void addSubHeader(JPanel p, String text) {
        JLabel l = new JLabel(text);
        l.setFont(new Font("SansSerif", Font.BOLD, 12));
        l.setForeground(new Color(60, 60, 60));
        l.setAlignmentX(Component.LEFT_ALIGNMENT);
        p.add(l);
        p.add(Box.createVerticalStrut(4));
    }

    private void addBullet(JPanel p, String text) {
        JTextArea bullet = new JTextArea("•  " + text);
        bullet.setFont(new Font("SansSerif", Font.PLAIN, 13));
        bullet.setForeground(new Color(80, 80, 80));
        bullet.setBackground(Color.WHITE);
        bullet.setLineWrap(true);
        bullet.setWrapStyleWord(true);
        bullet.setEditable(false);
        bullet.setBorder(new EmptyBorder(2, 0, 2, 0));
        bullet.setAlignmentX(Component.LEFT_ALIGNMENT);
        p.add(bullet);
    }

    private JPanel createDropdownRow(String label, String value) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(Color.WHITE);
        p.setMaximumSize(new Dimension(400, 45));
        p.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230,230,230)));
        
        JLabel l1 = new JLabel(label);
        l1.setFont(new Font("SansSerif", Font.BOLD, 11));
        l1.setPreferredSize(new Dimension(80, 40));
        
        JLabel l2 = new JLabel(value);
        l2.setFont(new Font("SansSerif", Font.PLAIN, 12));
        l2.setForeground(Color.DARK_GRAY);
        
        JLabel l3 = new JLabel("v");
        l3.setForeground(Color.LIGHT_GRAY);
        
        p.add(l1, BorderLayout.WEST);
        p.add(l2, BorderLayout.CENTER);
        p.add(l3, BorderLayout.EAST);
        
        // Store reference to the value label so we can update it later
        p.putClientProperty("valueLabel", l2);
        return p;
    }
}