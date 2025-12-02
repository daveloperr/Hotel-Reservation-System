package components;

import java.awt.*;
import javax.swing.*;

public class FindARoomTabPanel extends JPanel {

    public FindARoomTabPanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));

        // Room Type Group
        add(createTabButton("Room Type:"));

        JButton btnRoom = createTabButton("Room");
        btnRoom.setBackground(new Color(240, 240, 240)); // selected
        add(btnRoom);

        add(createTabButton("Club Room"));
        add(createTabButton("Suite"));
        add(createTabButton("Connecting Room"));

        // Spacer
        add(Box.createHorizontalStrut(5));

        // Booking Policy Group
        add(createTabButton("Booking Policy:"));
        add(createTabButton("Pay at property"));
        add(createTabButton("Pay online"));
        add(createTabButton("See More Filters ∨"));
    }

    private JButton createTabButton(String text) {
        JButton btn = new JButton(text);
        btn.setPreferredSize(new Dimension(140, 30));
        btn.setFocusPainted(false);
        btn.setBackground(Color.WHITE);
        btn.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        btn.setFont(new Font("Arial", Font.PLAIN, 12));
        return btn;
    }
}
