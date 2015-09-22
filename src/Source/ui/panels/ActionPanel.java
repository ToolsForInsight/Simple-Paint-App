/*
The ActionPanel shows the actions available to the user and changes the model
based on what the user wants to do.
*/
package ui.panels;

import interfaces.Resettable;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import model.Model;

public class ActionPanel extends Panel implements Resettable {

    private CheckboxGroup actionGroup;
    private Checkbox chkDraw, chkMove, chkResize, chkRemove, chkChange, chkFill;
    
    /*
    Constructor.
    Give the action panel the model to manipulate and define the
    actions and related changes to the model resulting from user selection
    using anonymous inner classes.
    */
    public ActionPanel(final Model model) {
      
        actionGroup = new CheckboxGroup();
        chkDraw = new Checkbox("Draw", actionGroup, true);
        chkMove = new Checkbox("Move", actionGroup, false);
        chkResize = new Checkbox("Resize", actionGroup, false);
        chkRemove = new Checkbox("Remove", actionGroup, false);
        chkChange = new Checkbox("Change", actionGroup, false);
        chkFill = new Checkbox("Fill", false);
        setLayout(new GridLayout(1, 6));
        add(chkDraw);
        add(chkMove);
        add(chkResize);
        add(chkRemove);
        add(chkChange);
        add(chkFill);
    
        chkDraw.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                model.setActionChoice(Model.ActionChoice.DRAW);
            }
        });
    
        chkMove.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                model.setActionChoice(Model.ActionChoice.MOVE);
            }
        });
    
        chkResize.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                model.setActionChoice(Model.ActionChoice.RESIZE);
            }
        });
    
        chkRemove.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                model.setActionChoice(Model.ActionChoice.REMOVE);
            }
        });
    
        chkChange.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                model.setActionChoice(Model.ActionChoice.CHANGE);
            }
        });
    
        chkFill.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                model.setFill(chkFill.getState());
            }
        });
    }
    
    /*
    for each component, set the state when it is reset.
    Only one of the first five can be true.
    */
    public void resetComponents() {

        chkDraw.setState(true);
        chkMove.setState(false);
        chkResize.setState(false);
        chkRemove.setState(false);
        chkChange.setState(false);
        chkFill.setState(false);
    }
}