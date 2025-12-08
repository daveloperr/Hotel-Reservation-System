	package gui;
	
	import javax.swing.*;
	import java.awt.*;
	
	class ImagePanel extends JPanel {
	    private Image background;
	
	    public ImagePanel(String imagePath) {
	        try {
	            background = new ImageIcon(imagePath).getImage();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        setLayout(new GridBagLayout());
	    }
	
	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        if (background != null) {
	            int panelWidth = getWidth();
	            int panelHeight = getHeight();
	
	            int imgWidth = background.getWidth(this);
	            int imgHeight = background.getHeight(this);
	
	            double scale = Math.max((double) panelWidth / imgWidth, (double) panelHeight / imgHeight);
	
	            int drawWidth = (int) (imgWidth * scale);
	            int drawHeight = (int) (imgHeight * scale);
	
	            int x = (panelWidth - drawWidth) / 2;
	            int y = 0; 
	            g.drawImage(background, x, y, drawWidth, drawHeight, this);
	        }
	    }
	}
	
	public class HomePage extends JFrame {
	
	    private static final long serialVersionUID = 1L;
	
	    public HomePage() {
	        setTitle("Hotel Reservation - Home");
	        setSize(900, 700);
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	        // --- MAIN SCROLLABLE PANEL ---
	        JPanel mainPanel = new JPanel();
	        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); 
	
	        // --- SCREEN 1: HEADER + NAV ---
	        JPanel screen1 = new JPanel(new BorderLayout());
	        screen1.setPreferredSize(new Dimension(900, 700)); // full window height
	
	        NavBar navBar = new NavBar();
	        screen1.add(navBar, BorderLayout.NORTH);
	
	
	
	        // Header / Hero text with background
	        ImagePanel headerPanel = new ImagePanel("images/background.jpg"); 
	        headerPanel.setPreferredSize(new Dimension(900, 650)); 
	
	        JLabel headerText = new JLabel(
	            "<html><div style='text-align:center;'>Makati Shangri-La, Manila<br><span style='font-size:18px; font-weight:bold;'>Where the City Gathers</span></div></html>"
	        );
	        headerText.setFont(new Font("Times New Roman", Font.BOLD, 36));
	        headerText.setForeground(Color.WHITE); 
	        headerPanel.add(headerText);
	
	        screen1.add(headerPanel, BorderLayout.CENTER);
	
	        mainPanel.add(screen1); // add first screen
	
	        // --- SCREEN 2: CARDS ---
	        JPanel screen2 = new JPanel(new BorderLayout());
	        screen2.setPreferredSize(new Dimension(900, 700));
	        screen2.setBackground(Color.WHITE);

	        // --- TITLE ---
	        JLabel offersTitle = new JLabel("Offers");
	        offersTitle.setFont(new Font("Times New Roman", Font.BOLD, 36));
	        offersTitle.setHorizontalAlignment(SwingConstants.CENTER);
	        offersTitle.setBorder(BorderFactory.createEmptyBorder(50, 0, 20, 0)); // top and bottom margin
	        screen2.add(offersTitle, BorderLayout.NORTH);

	        // --- CARD PANEL ---
	        JPanel cardPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20)); // horizontal gap=30, vertical gap=20
	        cardPanel.setBackground(Color.WHITE);

	        String[] images = {
	                "images/room1.jpg",
	                "images/room2.jpg",
	                "images/room3.jpg"
	        };
	        String[] chips = {"Queen Bed", "50 sqm", "City View", "Breakfast"};

	        for (int i = 0; i < images.length; i++) {
	            JPanel card = new JPanel();
	            card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
	            card.setBackground(Color.WHITE);
	            card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true)); // only border, no extra internal padding
	            card.setPreferredSize(new Dimension(300, 450)); // bigger and taller card

	            // ----- IMAGE (full width of card) -----
	            ImageIcon icon = new ImageIcon(images[i]);
	            Image img = icon.getImage().getScaledInstance(card.getPreferredSize().width, 200, Image.SCALE_SMOOTH); // full width
	            JLabel imgLabel = new JLabel(new ImageIcon(img));
	            imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	            card.add(imgLabel);

	            // ----- CHIPS -----
	            JPanel chipsRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 8)); // horizontal gap=8, vertical gap=8 (top margin)
	            chipsRow.setBackground(Color.WHITE);
	            for (int j = 0; j < chips.length; j++) {
	                JLabel chip = new JLabel(chips[j]);
	                chip.setFont(new Font("Arial", Font.PLAIN, 12));
	                chip.setBorder(BorderFactory.createCompoundBorder(
	                        BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true),
	                        BorderFactory.createEmptyBorder(5, 12, 5, 12)
	                ));
	                chipsRow.add(chip);

	                // Extra spacing above last chip
	                if (j == chips.length - 1) {
	                    chip.setBorder(BorderFactory.createCompoundBorder(
	                            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true),
	                            BorderFactory.createEmptyBorder(10, 12, 5, 12) // more top padding for last chip
	                    ));
	                }
	            }
	            card.add(Box.createRigidArea(new Dimension(0, 10))); // space between image and chips
	            card.add(chipsRow);
	            card.add(Box.createVerticalGlue()); // push phrase and button to bottom

	       
	         // ----- BUTTON -----
	            JButton viewBtn = new JButton("View Details");
	            viewBtn.setFocusPainted(false);
	            viewBtn.setContentAreaFilled(false); // transparent
	            viewBtn.setBorder(BorderFactory.createLineBorder(new Color(212, 175, 55), 2)); // gold border
	            viewBtn.setForeground(new Color(212, 175, 55));
	            viewBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
	            viewBtn.setMaximumSize(new Dimension(150, 45)); // bigger button
	            viewBtn.setPreferredSize(new Dimension(150, 45));
	            card.add(Box.createRigidArea(new Dimension(0, 15))); 
	            card.add(viewBtn);
	            card.add(Box.createRigidArea(new Dimension(0, 20))); 

	            cardPanel.add(card);
	        }



	        // Wrap cards in horizontal scroll
	        JScrollPane cardScroll = new JScrollPane(cardPanel,
	                JScrollPane.VERTICAL_SCROLLBAR_NEVER,
	                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	        cardScroll.setBorder(null);
	        cardScroll.getHorizontalScrollBar().setUnitIncrement(20);
	        cardScroll.setPreferredSize(new Dimension(900, 500)); // card area height

	        screen2.add(cardScroll, BorderLayout.CENTER); // add card scroll in center
	        mainPanel.add(screen2);



	     // --- SCREEN 3: ABOUT ---
	        JPanel screen3 = new JPanel(new GridLayout(1, 2));
	        screen3.setPreferredSize(new Dimension(900, 700));
	
	        JPanel leftPanel = new JPanel();
	        leftPanel.setBackground(Color.WHITE);
	        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
	
	        leftPanel.add(Box.createVerticalGlue());
	
	        int leftMargin = 100;
	        int rightMargin = 100;
	
	        JPanel contentPanel = new JPanel();
	        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
	        contentPanel.setOpaque(false); 
	        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, leftMargin, 0, rightMargin));
	
	        JLabel aboutTitle = new JLabel("About");
	        aboutTitle.setFont(new Font("Times New Roman", Font.BOLD, 36));
	        aboutTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
	        contentPanel.add(aboutTitle);
	        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
	
	        // --- Paragraph ---
	        JLabel aboutParagraph = new JLabel(
	                "<html>Located at the heart of the city's premier financial and commercial district, "
	                + "Makati Shangri-La, Manila is an iconic landmark. As an award-winning hotel, the hotel "
	                + "is known for its timeless elegance and legendary brand of service, supported by inventive "
	                + "and on-trend destination dining experiences, and the most flexible banqueting spaces in Metro Manila.</html>");
	        aboutParagraph.setFont(new Font("Arial", Font.PLAIN, 18));
	        aboutParagraph.setAlignmentX(Component.LEFT_ALIGNMENT);
	        contentPanel.add(aboutParagraph);
	        contentPanel.add(Box.createRigidArea(new Dimension(0, 20))); // spacing
	
	        // --- Bullets ---
	        String[] bullets = {
	            "Offering the city's most versatile and flexible banqueting and meeting spaces",
	            "Effortless access to luxury shopping in Makati",
	            "All-in-one wellness floor with a gym, outdoor pool, tennis courts, and spa for fitness, relaxation and rejuvenation"
	        };
	
	        for (String bullet : bullets) {
	            JLabel bulletLabel = new JLabel("<html>&#8226; " + bullet + "</html>");
	            bulletLabel.setFont(new Font("Arial", Font.PLAIN, 18));
	            bulletLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
	            contentPanel.add(bulletLabel);
	            contentPanel.add(Box.createRigidArea(new Dimension(0, 10))); 
	        }
	
	        JButton learnMoreBtn = new JButton("Learn More");
	        learnMoreBtn.setFocusPainted(false);
	        learnMoreBtn.setContentAreaFilled(false);
	        learnMoreBtn.setForeground(new Color(212, 175, 55)); // gold
	        learnMoreBtn.setFont(new Font("Times", Font.BOLD, 16));
	        learnMoreBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
	        learnMoreBtn.setBorder(BorderFactory.createCompoundBorder(
	                BorderFactory.createLineBorder(new Color(212, 175, 55), 2),
	                BorderFactory.createEmptyBorder(5, 20, 5, 20) // internal padding
	        ));
	        contentPanel.add(Box.createRigidArea(new Dimension(0, 20))); // spacing before button
	        contentPanel.add(learnMoreBtn);
	
	        // Add contentPanel to leftPanel
	        leftPanel.add(contentPanel);
	
	        // Vertical glue bottom to center vertically
	        leftPanel.add(Box.createVerticalGlue());
	
	        // --- Right side: Image ---
	        JLabel aboutImage = new JLabel(new ImageIcon("images/aboutimage.jpg"));
	        aboutImage.setHorizontalAlignment(SwingConstants.CENTER);
	
	        // Add left and right panels to screen3
	        screen3.add(leftPanel);
	        screen3.add(aboutImage);
	
	        mainPanel.add(screen3); // add third screen
	
	
	        // --- SCROLL PANE ---
	        JScrollPane scrollPane = new JScrollPane(mainPanel);
	        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
	
	        add(scrollPane);
	
	        SwingUtilities.invokeLater(() -> scrollPane.getViewport().setViewPosition(new Point(0, 0)));
	
	        setVisible(true);
	    }
	
	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> new HomePage());
	    }
	}
