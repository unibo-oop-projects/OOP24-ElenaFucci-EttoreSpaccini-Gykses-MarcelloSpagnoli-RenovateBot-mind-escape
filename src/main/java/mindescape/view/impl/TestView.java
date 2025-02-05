package mindescape.view.impl;

import javax.swing.JFrame;

import com.fasterxml.jackson.databind.util.ViewMatcher;

import mindescape.view.menu.MenuView;
import mindescape.view.api.View;

public class TestView {
    public static void main(String[] args) {
        var view = new MenuView();
        JFrame frame = new JFrame("Test View");
        frame.add(view);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
    
}
