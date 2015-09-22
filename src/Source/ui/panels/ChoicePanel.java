/*
The ChoicePanel displays different shape choices for the user to select.
*/
package ui.panels;

import interfaces.Resettable;

import java.awt.Choice;
import java.awt.Panel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import model.Model;

public class ChoicePanel extends Panel implements Resettable {

    private Choice shapeChooser;
  
    //Give the model to the choice panel so the choice panel can tell the 
    // model what to do.
    public ChoicePanel (final Model model) {
  
        shapeChooser = new Choice();
        shapeChooser.add("Rectangle");
        shapeChooser.add("Oval");
        shapeChooser.add("Triangle");
        shapeChooser.add("Line");
  
        shapeChooser.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getItem() == "Rectangle") model.setShapeChoice(Model.ShapeChoice.RECTANGLE);
                if (e.getItem() == "Oval") model.setShapeChoice(Model.ShapeChoice.OVAL);
                if (e.getItem() == "Triangle") model.setShapeChoice(Model.ShapeChoice.TRIANGLE);
                if (e.getItem() == "Line") model.setShapeChoice(Model.ShapeChoice.LINE);
            }
        });
        
        add(shapeChooser);
    }
  
    /*
    Reset to the default value - Rectangle.
    */
    public void resetComponents() {

        shapeChooser.select("Rectangle");
        // known bug: select() will not change the state of the component, so if user clicks on the choice that was 
        // chosen prior to select(), itemStateChanged(ItemEvent e) will not be called and therefore
        // a Rectangle will still be held in the model.
    }
}