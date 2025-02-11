package mindescape.view.enigmacalendar.impl;
 
import javax.swing.*;

import mindescape.view.enigmacalendar.api.CalendarView;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
 
public class CalendarViewImpl implements CalendarView {
 
    private final JPanel panel;
    private final JLabel titleLabel;
    private final JPanel schedulePanel;
    private final JLabel[] timeLabels;
    private final JLabel[] activityLabels;
 
    public CalendarViewImpl() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.DARK_GRAY);
 
        titleLabel = new JLabel("Daily Schedule", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        panel.add(titleLabel, BorderLayout.NORTH);
 
        String[] timeSlots = {"09:00 - 12:00", "12:00 - 13:00", "14:00 - 16:00", "16:00 - 21:00"};
        String[] activities = {
                "Seduta psicologica di gruppo",
                "Pranzo in mensa",
                "Riposo pomeridiano",
                "Attività all'aria aperta"
        };
 
        schedulePanel = new JPanel();
        schedulePanel.setLayout(new GridLayout(timeSlots.length, 1, 10, 10));
        schedulePanel.setBackground(Color.DARK_GRAY);
 
        timeLabels = new JLabel[timeSlots.length];
        activityLabels = new JLabel[activities.length];
 
        for (int i = 0; i < timeSlots.length; i++) {
            JPanel entryPanel = new JPanel(new BorderLayout());
            entryPanel.setBackground(new Color(50, 50, 50));
            entryPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
 
            timeLabels[i] = new JLabel(timeSlots[i], SwingConstants.CENTER);
            timeLabels[i].setFont(new Font("Arial", Font.BOLD, 18));
            timeLabels[i].setForeground(Color.WHITE);
            entryPanel.add(timeLabels[i], BorderLayout.WEST);
 
            activityLabels[i] = new JLabel(activities[i], SwingConstants.CENTER);
            activityLabels[i].setFont(new Font("Arial", Font.PLAIN, 18));
            activityLabels[i].setForeground(Color.WHITE);
            entryPanel.add(activityLabels[i], BorderLayout.CENTER);
 
            schedulePanel.add(entryPanel);
        }
 
        panel.add(schedulePanel, BorderLayout.CENTER);
 
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = panel.getWidth();
                int fontSize = Math.max(14, width / 30);
                titleLabel.setFont(new Font("Arial", Font.BOLD, fontSize + 6));
                for (JLabel label : timeLabels) {
                    label.setFont(new Font("Arial", Font.BOLD, fontSize));
                }
                for (JLabel label : activityLabels) {
                    label.setFont(new Font("Arial", Font.PLAIN, fontSize));
                }
            }
        });
    }
    @Override
    public JPanel getPanel() {
        return panel;
    }
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Calendar View Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 400);
            frame.setLocationRelativeTo(null);
 
            CalendarView calendarView = new CalendarViewImpl();
            frame.add(calendarView.getPanel());
 
            frame.setVisible(true);
        });
    }
    @Override
    public void draw() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }
}

