package mindescape.view.api;

import javax.swing.JPanel;

public interface MainView {

    /**
     * Sets the current panel to the specified JPanel.
     *
     * @param panel the JPanel to be set as the current panel
     */
    void setPanel(JPanel panel);

    /**
     * Shows the current panel.
     */ 
    void show();

    /**
     * This method is called when the player wins the game.
     */
    void won();

}
