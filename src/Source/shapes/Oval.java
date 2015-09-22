/*
The oval shape.
*/

package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Oval extends Rectangle {

    private double semiMajorAxis, semiMinorAxis;
    private int fociOneX;
    private int fociOneY;
    private int fociTwoX;
    private int fociTwoY;
      
    /*
    Oval constructor.
    */
    public Oval(int topLeftX, int topLeftY, Color lineColor,
                boolean isSelected, int width, int height, boolean fill,
                Color fillColor) {
          
        super(topLeftX, topLeftY, lineColor, isSelected, width, height,
              fill, fillColor);
        
        doAndSetCalculations();
    }
    
    /*
    Normalize the coordinates compared to the ellipse having a center at
    0,0 and a radius of 0.5.
    (I can't take credit for this code, I found it possibly on stack overflow) 
    */
    public boolean containsLocation(int x, int y) {

        double ellw = getWidth();
      
        if (ellw <= 0.0) {
            return false;
        }
      
        double normx = (x - getTopLeftX()) / ellw - 0.5;
        double ellh = getHeight();
    
        if (ellh <= 0.0) {
            return false;
        }
        double normy = (y - getTopLeftY()) / ellh - 0.5;
      
        return (normx * normx + normy * normy) < 0.25;
    }
    
    /*
    Calculate the semi major axis given width and height
    */
    public double calculateSemiMajorAxis() {
      
        double axis = .5 * (double)((getWidth() < getHeight()) ? 
                                     getHeight() : getWidth());
        return axis;
    }
    
    /*
    Calculate the semi minor axis given width and height
    */
    public double calculateSemiMinorAxis() {
      
        double axis = .5 * (double)((getWidth() <= getHeight()) ?
                                     getWidth() : getHeight());
        return axis;
    }
    
    /*
    Calculate the foci based on semi axes
    f is the distance from each focus to the center
    */ 
    public int[] calculateFoci() {
        
          int[] foci = new int[4];
          double f;
          
          if (getWidth() <= getHeight())
          {
              f = Math.sqrt((Math.pow((getHeight()/2),2) - 
                             Math.pow((getWidth()/2),2)));
              foci[0] = (getTopLeftX()+(getWidth()/2));
              foci[1] = (getTopLeftY()+(getHeight()/2)-(int)f);
              foci[2] = (getTopLeftX()+(getWidth()/2));
              foci[3] = (getTopLeftY()+(getHeight()/2)+(int)f);
          }
          
          if (getWidth() > getHeight())
          {
              f = Math.sqrt((Math.pow((getWidth()/2),2) - 
                             Math.pow((getHeight()/2),2)));
              foci[0] = (getTopLeftX()+(getWidth()/2)-(int)f);
              foci[1] = (getTopLeftY()+(getHeight()/2));
              foci[2] = (getTopLeftX()+(getWidth()/2)+(int)f);
              foci[3] = (getTopLeftY()+(getHeight()/2));
          }
          
          return foci;
    }
    
    /*
    Calculate the perimeter
    */
    @Override
    public double calculatePerimeter() {

        double total = 2*Math.PI*Math.sqrt((Math.pow(semiMajorAxis, 2)
                       +Math.pow(semiMinorAxis, 2))/2);
        return total;
    }

    /*
    Calculate the area
    */
    @Override
    public double calculateArea() {

        double total = (Math.PI)*semiMajorAxis*semiMinorAxis;
        return total;
    }
    
    /*
    Make all oval calculations and set relevant variables
    */
    public void doAndSetCalculations() {
        
        semiMajorAxis = calculateSemiMajorAxis();
        semiMinorAxis = calculateSemiMinorAxis();
        setArea(calculateArea());
        setPerimeter(calculatePerimeter());
        setFoci(calculateFoci());
    }

    /*
    draw an oval.
    */
    public void draw(Graphics g) {
        // Be nice. Save the state of the object before changing it.
        Color oldColor = g.getColor();
        if (isFill()) {
            g.setColor(getFillColor());
            g.fillOval(getTopLeftX(), getTopLeftY(), getWidth(), getHeight());
        }
        g.setColor(getLineColor());
        g.drawOval(getTopLeftX(), getTopLeftY(), getWidth(), getHeight());
        // Set the state back when done.
        
        g.setColor(oldColor);
    }
    
    /*
    draw visual highlighting that lets user know they selected the oval.
    red squares will highlight the endpoints of the axes.
    */
    public void drawVisualSelectionCue(Graphics g) {
           
        Color oldColor = g.getColor();
        
        g.setColor(Color.RED);
        
        g.drawRect(getTopLeftX()+(getWidth()/2)-5, getTopLeftY()-5, 10, 10);
        g.drawRect(getTopLeftX()+(getWidth()/2)-5, getTopLeftY()+getHeight()-5,
                   10, 10);
        g.drawRect(getTopLeftX()-5, getTopLeftY()+(getHeight()/2)-5, 10, 10);
        g.drawRect(getTopLeftX()+getWidth()-5, getTopLeftY()+(getHeight()/2)-5,
                   10, 10);
        
        g.setColor(oldColor);
    }

    public double getSemiMajorAxis() {
      
        return semiMajorAxis;
    }

    public void setSemiMajorAxis(double majorAxis) {

        this.semiMajorAxis = majorAxis;
    }

    public double getSemiMinorAxis() {
   
        return semiMinorAxis;
    }

    public void setSemiMinorAxis(double minorAxis) {

        this.semiMinorAxis = minorAxis;
    }
    
    public void setFoci(int[] foci) {
        
        fociOneX = foci[0];
        fociOneY = foci[1];
        fociTwoX = foci[2];
        fociTwoY = foci[3];
    }

    public int getFociOneX() {
      
        return fociOneX;
    }

    public void setFociOneX(int fociOneX) {

        this.fociOneX = fociOneX;
    }

    public int getFociOneY() {
    
        return fociOneY;
    }

    public void setFociOneY(int fociOneY) {

        this.fociOneY = fociOneY;
    }

    public int getFociTwoX() {
      
        return fociTwoX;
    }

    public void setFociTwoX(int fociTwoX) {

        this.fociTwoX = fociTwoX;
    }

    public int getFociTwoY() {
      
        return fociTwoY;
    }

    public void setFociTwoY(int fociTwoY) {

        this.fociTwoY = fociTwoY;
    }
    
    public String toString() {
      
        return "Oval: \n\tx = " + getTopLeftX() + " \n\ty = " + getTopLeftY() + 
               "\n\tSemi Major Axis = " + getSemiMajorAxis() +
               "\n\tSemi Minor Axis = " + getSemiMinorAxis() +
               "\n\tFoci One = (" + fociOneX + ", " + fociOneY + ")" +
               "\n\tFoci Two = (" + fociTwoX + ", " + fociTwoY + ")" + 
               "\n\tPerimeter = " + getPerimeter() + "\n\tArea = " + getArea();
    }
}