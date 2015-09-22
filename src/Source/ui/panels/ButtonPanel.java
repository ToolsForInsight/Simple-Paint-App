/*
The clear button.  Tells the model to reset itself when pressed.
Uses callbacks from the model to reset state of the rest of the program's
components.
*/
package ui.panels;

import java.awt.Button;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Model;

public class ButtonPanel extends Panel {

    private Button btnClear;
    
    public ButtonPanel(final Model model) {
      
        btnClear = new Button("Clear");
        
        /*
        use an inner class to create the button
        */
        class ButtonPressed implements ActionListener {
            
            public void actionPerformed(ActionEvent arg0) {
                model.resetComponents();
                model.repaint();
            }
        }
        
        btnClear.addActionListener(new ButtonPressed());
        add(btnClear);
    }
}