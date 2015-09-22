/*
The MainPanel.  Creates the ActionPanel (actions), ChoicePanel (shapes),
and ControlsPanel (clear button and color choosers).
*/
package ui.panels;

import interfaces.Resettable;
import java.awt.GridLayout;
import java.awt.Panel;
import model.Model;

public class MainPanel extends Panel implements Resettable {

    ActionPanel actionPanel;
    ChoicePanel choicePanel;
    ControlsPanel controlsPanel;
    
    /*
    Give the model to the MainPanel so the MainPanel can tell the model
    what to do.
    */
    public MainPanel(Model model) {
      
        actionPanel = new ActionPanel(model);
        controlsPanel = new ControlsPanel(model);
        choicePanel = new ChoicePanel(model);
        setLayout(new GridLayout(2, 1));
        add(controlsPanel);
        add(actionPanel);
        add(choicePanel);
    }
    
    /*
    Reset all panels.
    */
    public void resetComponents() {
      
        controlsPanel.resetComponents();
        actionPanel.resetComponents();
        choicePanel.resetComponents();
    }
}