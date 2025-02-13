package mindescape.view.enigmacalendar.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import mindescape.controller.enigmacalendar.impl.CalendarControllerImpl;
import mindescape.view.enigmacalendar.api.CalendarView;

/**
 * Implementation of the calendar view that displays a daily schedule with time slots and activities.
 * The layout dynamically adjusts to the resizing of the window.
 */
public class CalendarViewImpl implements CalendarView {

    private static final String FONT_NAME = "Arial";
    private static final Color BACKGROUND_COLOR = Color.DARK_GRAY;
    private static final Color ENTRY_BACKGROUND_COLOR = new Color(50, 50, 50);
    private static final Color BORDER_COLOR = Color.WHITE;
    private static final int BORDER_THICKNESS = 1;
    private static final int GRID_HGAP = 10;
    private static final int GRID_VGAP = 10;
    private static final int MIN_FONT_SIZE = 14;
    private static final int TITLE_FONT_INCREMENT = 6;
    private static final String TITLE_TEXT = "Daily Schedule";
    private static final String QUIT_BUTTON_TEXT = "Quit";

    private static final String[] TIME_SLOTS = {
        "09:00 - 12:00", "12:00 - 13:00", "14:00 - 16:00", "16:00 - 21:00"
    };
    
    private static final String[] ACTIVITIES = {
        "Group psychological session",
        "Lunch in the canteen",
        "Afternoon rest",
        "Outdoor activities"
    };

    private final JPanel panel;
    private final JLabel titleLabel;
    private final JPanel schedulePanel;
    private final JLabel[] timeLabels;
    private final JLabel[] activityLabels;

    /**
     * Constructor that initializes the calendar view.
     * Creates the panel structure with time slot and activity labels, and adds logic
     * to resize the text based on the window's width.
     * 
     * @param controller The controller that manages the calendar view.
     */
    public CalendarViewImpl(final CalendarControllerImpl controller) {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(BACKGROUND_COLOR);

        final JButton button = new JButton(QUIT_BUTTON_TEXT);
        button.addActionListener(e -> controller.quit());
        panel.add(button, BorderLayout.SOUTH);

        titleLabel = new JLabel(TITLE_TEXT, SwingConstants.CENTER);
        titleLabel.setFont(new Font(FONT_NAME, Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        panel.add(titleLabel, BorderLayout.NORTH);

        schedulePanel = new JPanel();
        schedulePanel.setLayout(new GridLayout(TIME_SLOTS.length, 1, GRID_HGAP, GRID_VGAP));
        schedulePanel.setBackground(BACKGROUND_COLOR);

        timeLabels = new JLabel[TIME_SLOTS.length];
        activityLabels = new JLabel[ACTIVITIES.length];

        // Creates the labels for time slots and activities, then adds them to the panel
        for (int i = 0; i < TIME_SLOTS.length; i++) {
            final JPanel entryPanel = new JPanel(new BorderLayout());
            entryPanel.setBackground(ENTRY_BACKGROUND_COLOR);
            entryPanel.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, BORDER_THICKNESS));

            timeLabels[i] = new JLabel(TIME_SLOTS[i], SwingConstants.CENTER);
            timeLabels[i].setFont(new Font(FONT_NAME, Font.BOLD, 18));
            timeLabels[i].setForeground(Color.WHITE);
            entryPanel.add(timeLabels[i], BorderLayout.WEST);

            activityLabels[i] = new JLabel(ACTIVITIES[i], SwingConstants.CENTER);
            activityLabels[i].setFont(new Font(FONT_NAME, Font.PLAIN, 18));
            activityLabels[i].setForeground(Color.WHITE);
            entryPanel.add(activityLabels[i], BorderLayout.CENTER);

            schedulePanel.add(entryPanel);
        }

        panel.add(schedulePanel, BorderLayout.CENTER);

        // Adds a listener to resize the text when the window is resized
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(final ComponentEvent e) {
                final int width = panel.getWidth();
                final int fontSize = Math.max(MIN_FONT_SIZE, width / 30);
                titleLabel.setFont(new Font(FONT_NAME, Font.BOLD, fontSize + TITLE_FONT_INCREMENT));
                for (final JLabel label : timeLabels) {
                    label.setFont(new Font(FONT_NAME, Font.BOLD, fontSize));
                }
                for (final JLabel label : activityLabels) {
                    label.setFont(new Font(FONT_NAME, Font.PLAIN, fontSize));
                }
                button.setFont(new Font(FONT_NAME, Font.BOLD, fontSize));
            }
        });
    }

    /**
     * Returns the panel that contains the calendar view.
     * 
     * @return The panel containing the calendar view.
     */
    @Override
    public JPanel getPanel() {
        return panel;
    }
}
