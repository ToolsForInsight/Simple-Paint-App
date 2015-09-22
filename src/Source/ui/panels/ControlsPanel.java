/*
The ControlsPanel is comprised of a ButtonPanel and a ColorPanel.
The ButtonPanel tells the model to clear the program, and the ColorPanel
offers the user selections for line color and fill color.
*/
package ui.panels;

import java.awt.Panel;
import java.awt.Choice;
import java.awt.Color;

import interfaces.Resettable;
import model.Model;
import ui.panels.ButtonPanel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ControlsPanel extends Panel implements Resettable {

    private ButtonPanel btnPanel;
    private ColorPanel colorPanel;
    
    public ControlsPanel(Model model) {
      
        btnPanel = new ButtonPanel(model);
        colorPanel = new ColorPanel(model);
        add(btnPanel);
        add(colorPanel);
    }
    
    /*
    use an inner class to create the color choices.
    */
    public class ColorPanel extends Panel implements Resettable {
      
        Choice fillColorChooser, lineColorChooser;
        
        public ColorPanel(final Model model) {
            
            lineColorChooser = new Choice();
            lineColorChooser.add("*Select Line Color*");
            lineColorChooser.add("Black");
            lineColorChooser.add("Blue");
            lineColorChooser.add("Cyan");
            lineColorChooser.add("Gray");
            lineColorChooser.add("Green");
            lineColorChooser.add("Magenta");
            lineColorChooser.add("Orange");
            lineColorChooser.add("Pink");
            lineColorChooser.add("Red");
            lineColorChooser.add("White");
            lineColorChooser.add("Yellow");
            
            lineColorChooser.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    if (e.getItem() == "Black") model.setLineColor(Model.BLACK);
                    if (e.getItem() == "Blue") model.setLineColor(Model.BLUE);
                    if (e.getItem() == "Cyan") model.setLineColor(Model.CYAN);
                    if (e.getItem() == "Gray") model.setLineColor(Model.GRAY);
                    if (e.getItem() == "Green") model.setLineColor(Model.GREEN);
                    if (e.getItem() == "Magenta") model.setLineColor(Model.MAGENTA);
                    if (e.getItem() == "Orange") model.setLineColor(Model.ORANGE);
                    if (e.getItem() == "Pink") model.setLineColor(Model.PINK);
                    if (e.getItem() == "Red") model.setLineColor(Model.RED);
                    if (e.getItem() == "White") model.setLineColor(Model.WHITE);
                    if (e.getItem() == "Yellow") model.setLineColor(Model.YELLOW);
                }
            });
            add(lineColorChooser);
          
            fillColorChooser = new Choice();
            fillColorChooser.add("*Select Fill Color*");
            fillColorChooser.add("Black");
            fillColorChooser.add("Blue");
            fillColorChooser.add("Cyan");
            fillColorChooser.add("Gray");
            fillColorChooser.add("Green");
            fillColorChooser.add("Magenta");
            fillColorChooser.add("Orange");
            fillColorChooser.add("Pink");
            fillColorChooser.add("Red");
            fillColorChooser.add("White");
            fillColorChooser.add("Yellow");
            
            fillColorChooser.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    if (e.getItem() == "Black") model.setFillColor(Model.BLACK);
                    if (e.getItem() == "Blue") model.setFillColor(Model.BLUE);
                    if (e.getItem() == "Cyan") model.setFillColor(Model.CYAN);
                    if (e.getItem() == "Gray") model.setFillColor(Model.GRAY);
                    if (e.getItem() == "Green") model.setFillColor(Model.GREEN);
                    if (e.getItem() == "Magenta") model.setFillColor(Model.MAGENTA);
                    if (e.getItem() == "Orange") model.setFillColor(Model.ORANGE);
                    if (e.getItem() == "Pink") model.setFillColor(Model.PINK);
                    if (e.getItem() == "Red") model.setFillColor(Model.RED);
                    if (e.getItem() == "White") model.setFillColor(Model.WHITE);
                    if (e.getItem() == "Yellow") model.setFillColor(Model.YELLOW);
                }
            });
            add(fillColorChooser);
            
        }
        
        /*
        Reset color choosers to default value
        */
        public void resetComponents() {
          
            fillColorChooser.select("*Select Fill Color*");
            lineColorChooser.select("*Select Line Color*");
        }
    }
    
    /*
    Reset the colorPanel, which resets the colorChoosers.
    */
    public void resetComponents() {
      
        colorPanel.resetComponents();
    }
}