package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class RoomDetailPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RoomDetailPage frame = new RoomDetailPage();
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
	public RoomDetailPage() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
		contentPane.setLayout(null);
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		
		setTitle("Room Details - Deluxe Room");
		
		// Header
		JLabel lblTitle = new JLabel("Deluxe Room");
		lblTitle.setBounds(20, 10, 200, 25);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
		lblTitle.setForeground(new Color(139, 69, 19));
		contentPane.add(lblTitle);
		
		JLabel lblSize = new JLabel("53-64 sqm / 570-688 sqf");
		lblSize.setBounds(20, 35, 200, 20);
		lblSize.setFont(new Font("Arial", Font.PLAIN, 12));
		lblSize.setForeground(new Color(100, 100, 100));
		contentPane.add(lblSize);
		
		// Amenities Section (Collapsed)
		JPanel amenitiesPanel = new JPanel();
		amenitiesPanel.setBounds(20, 170, 450, 45);
		amenitiesPanel.setBackground(Color.WHITE);
		amenitiesPanel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
		amenitiesPanel.setLayout(null);
		contentPane.add(amenitiesPanel);
		
		JLabel lblAmenitiesHeader = new JLabel("Amenities");
		lblAmenitiesHeader.setBounds(10, 10, 430, 20);
		lblAmenitiesHeader.setFont(new Font("Arial", Font.BOLD, 13));
		lblAmenitiesHeader.setForeground(new Color(0, 0, 0));
		lblAmenitiesHeader.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		amenitiesPanel.add(lblAmenitiesHeader);
		
		JTextArea txtAmenities = new JTextArea();
		txtAmenities.setBounds(10, 35, 430, 75);
		txtAmenities.setFont(new Font("Arial", Font.PLAIN, 11));
		txtAmenities.setForeground(new Color(80, 80, 80));
		txtAmenities.setBackground(Color.WHITE);
		txtAmenities.setEditable(false);
		txtAmenities.setLineWrap(true);
		txtAmenities.setWrapStyleWord(true);
		txtAmenities.setText("• High-speed WiFi\n• 55-inch LED TV with cable channels\n• Mini bar and coffee/tea maker\n• In-room safe\n• Iron and ironing board\n• 24-hour room service");
		txtAmenities.setVisible(false);
		amenitiesPanel.add(txtAmenities);
		
		// Children's Meal Plan Section (Collapsed)
		JPanel mealPlanPanel = new JPanel();
		mealPlanPanel.setBounds(20, 225, 450, 45);
		mealPlanPanel.setBackground(Color.WHITE);
		mealPlanPanel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
		mealPlanPanel.setLayout(null);
		contentPane.add(mealPlanPanel);
		
		JLabel lblMealPlanHeader = new JLabel("Children's meal plan for guests staying at the hotel");
		lblMealPlanHeader.setBounds(10, 10, 430, 20);
		lblMealPlanHeader.setFont(new Font("Arial", Font.BOLD, 13));
		lblMealPlanHeader.setForeground(new Color(0, 0, 0));
		lblMealPlanHeader.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		mealPlanPanel.add(lblMealPlanHeader);
		
		JTextArea txtMealPlan = new JTextArea();
		txtMealPlan.setBounds(10, 35, 430, 50);
		txtMealPlan.setFont(new Font("Arial", Font.PLAIN, 11));
		txtMealPlan.setForeground(new Color(80, 80, 80));
		txtMealPlan.setBackground(Color.WHITE);
		txtMealPlan.setEditable(false);
		txtMealPlan.setLineWrap(true);
		txtMealPlan.setWrapStyleWord(true);
		txtMealPlan.setText("• Children under 6 years: Complimentary breakfast\n• Children 6-12 years: 50% discount on breakfast buffet\n• Special kids menu available at all hotel restaurants");
		txtMealPlan.setVisible(false);
		mealPlanPanel.add(txtMealPlan);
		
		// Features Section (Expanded)
		JPanel featuresPanel = new JPanel();
		featuresPanel.setBounds(20, 70, 450, 90);
		featuresPanel.setBackground(Color.WHITE);
		featuresPanel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
		featuresPanel.setLayout(null);
		contentPane.add(featuresPanel);
		
		JLabel lblFeaturesHeader = new JLabel("Features");
		lblFeaturesHeader.setBounds(10, 5, 430, 20);
		lblFeaturesHeader.setFont(new Font("Arial", Font.BOLD, 13));
		lblFeaturesHeader.setForeground(new Color(0, 0, 0));
		lblFeaturesHeader.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		featuresPanel.add(lblFeaturesHeader);
		
		JTextArea txtFeatures = new JTextArea();
		txtFeatures.setBounds(10, 30, 430, 50);
		txtFeatures.setFont(new Font("Arial", Font.PLAIN, 11));
		txtFeatures.setForeground(new Color(80, 80, 80));
		txtFeatures.setBackground(Color.WHITE);
		txtFeatures.setEditable(false);
		txtFeatures.setLineWrap(true);
		txtFeatures.setWrapStyleWord(true);
		txtFeatures.setText("• 53-64 sqm / 570-688 sqf\n• Panoramic views of Financial District\n• A spacious bathroom with separate shower and bath tub areas");
		featuresPanel.add(txtFeatures);
		
		// Features toggle functionality
		final boolean[] featuresExpanded = {true};
		final boolean[] amenitiesExpanded = {false};
		final boolean[] mealPlanExpanded = {false};
		
		lblFeaturesHeader.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (featuresExpanded[0]) {
					txtFeatures.setVisible(false);
					featuresPanel.setBounds(20, 70, 450, 35);
					amenitiesPanel.setBounds(20, 115, 450, amenitiesExpanded[0] ? 120 : 45);
					int mealY = amenitiesExpanded[0] ? 245 : 170;
					mealPlanPanel.setBounds(20, mealY, 450, mealPlanExpanded[0] ? 95 : 45);
				} else {
					txtFeatures.setVisible(true);
					featuresPanel.setBounds(20, 70, 450, 90);
					amenitiesPanel.setBounds(20, 170, 450, amenitiesExpanded[0] ? 120 : 45);
					int mealY = amenitiesExpanded[0] ? 300 : 225;
					mealPlanPanel.setBounds(20, mealY, 450, mealPlanExpanded[0] ? 95 : 45);
				}
				featuresExpanded[0] = !featuresExpanded[0];
			}
		});
		
		// Amenities toggle functionality
		lblAmenitiesHeader.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (amenitiesExpanded[0]) {
					txtAmenities.setVisible(false);
					int amenitiesY = featuresExpanded[0] ? 170 : 115;
					amenitiesPanel.setBounds(20, amenitiesY, 450, 45);
					int mealY = amenitiesY + 55;
					mealPlanPanel.setBounds(20, mealY, 450, mealPlanExpanded[0] ? 95 : 45);
				} else {
					txtAmenities.setVisible(true);
					int amenitiesY = featuresExpanded[0] ? 170 : 115;
					amenitiesPanel.setBounds(20, amenitiesY, 450, 120);
					int mealY = amenitiesY + 130;
					mealPlanPanel.setBounds(20, mealY, 450, mealPlanExpanded[0] ? 95 : 45);
				}
				amenitiesExpanded[0] = !amenitiesExpanded[0];
			}
		});
		
		// Meal Plan toggle functionality
		lblMealPlanHeader.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (mealPlanExpanded[0]) {
					txtMealPlan.setVisible(false);
					mealPlanPanel.setBounds(mealPlanPanel.getX(), mealPlanPanel.getY(), 450, 45);

				} else {
					txtMealPlan.setVisible(true);
					mealPlanPanel.setBounds(mealPlanPanel.getX(), mealPlanPanel.getY(), 450, 95);

				}
				mealPlanExpanded[0] = !mealPlanExpanded[0];
			}
		});

	}

}
