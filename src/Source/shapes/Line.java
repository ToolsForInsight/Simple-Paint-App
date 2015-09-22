/*
A line.  A shape that can be drawn.  Provides some useful descriptors, like
total length, slope, and y intercept.
One cool addition would be to add the lengthBetweenPoints and angle variables
to curve the line: lengthBetweenPoints would be straight line length
between points, totalLength would be length of the visible, curved line, and
angle would indicate how curved the line is (if only one angle, then it would
be a symmetrical curve.
*/
package shapes;

import java.awt.Graphics;
import java.awt.Color;

public class Line extends Shape {

    int xTwo, yTwo;
/*  private double lengthBetweenPoints; 
    private double angle;  if we want to curve the line */
    private double totalLength;
    private double slope;
    private double yIntercept;
    
    // constructor for our lines
    public Line(int topLeftX, int topLeftY, Color lineColor,
                boolean isSelected, int xTwo, int yTwo) {

        super(topLeftX, topLeftY, lineColor, isSelected);
        this.xTwo = xTwo;
        this.yTwo = yTwo;
        
        doAndSetCalculations();
//      this.lengthBetweenPoints = lengthBetweenPoints;
    }
    
    /*
    The usual coordinate system's quadrant "IV" is Java's quadrant "I" so, y
    values (and therefore slope) will be opposite of expected.
    */
    public double calculateSlope(int topLeftX, int topLeftY,
                               int xTwo, int yTwo) {
        double calculation;
        
        double xA = (double)topLeftX;
        double yA = (double)topLeftY;
        double xB = (double)xTwo;
        double yB = (double)yTwo;
        
        calculation = ((yB - yA) / (xB - xA));
        
        return calculation;
    }
    
    /*
    Calculate length of the line based on the two coordinates
    */
    public double calculateTotalLength(int topLeftX, int topLeftY,
                                     int xTwo, int yTwo) {
      
        double total;
        
        double xA = (double)topLeftX;
        double yA = (double)topLeftY;
        double xB = (double)xTwo;
        double yB = (double)yTwo;
        
        total = Math.sqrt(
                                    Math.pow((xB - xA), 2) +
                                    Math.pow((yB - yA), 2)
                                    );
        
        return total;
    }
    
    /*
    Calculate the y intercept.  This is going to look funny because Java y
    values increase as they go down the screen.
    */
    public double calculateYIntercept(int topLeftX, int topLeftY,
                                    int xTwo, int yTwo) {

        double calculation;
        
        double xA = (double)topLeftX;
        double yA = (double)topLeftY;
        double xB = (double)xTwo;
        double yB = (double)yTwo;
        
        calculation = (yA - (((yB - yA) / (xB - xA)) * xA));
        
        return calculation;
    }
    
    /*
    Calculate and set important line variables.
    */
    public void doAndSetCalculations() {
      
        setSlope(calculateSlope(getTopLeftX(), getTopLeftY(), xTwo, yTwo));
        setYIntercept(calculateYIntercept(getTopLeftX(), getTopLeftY(),
                                          xTwo, yTwo));
        setTotalLength(calculateTotalLength(getTopLeftX(), getTopLeftY(),
                                            xTwo, yTwo));
    }

    /*
    draw the line
    */
    public void draw(Graphics g) {
      // Be nice.  Save the state of the object before changing it.
        Color oldColor = g.getColor();
        g.setColor(getLineColor());
        g.drawLine(getTopLeftX(), getTopLeftY(), getXTwo(), getYTwo());
        // Set the state back when done.
        
        g.setColor(oldColor);
    }
    
    /*
    Highlight the two endpoints of a line when it is selected.
    */
    public void drawVisualSelectionCue(Graphics g) {
        
        Color oldColor = g.getColor();
        
        g.setColor(Color.RED);
            
        g.drawRect(getTopLeftX()-5, getTopLeftY()-5, 10, 10);
        g.drawRect(xTwo-5, yTwo-5, 10, 10);
        
        g.setColor(oldColor);
    }
    
    /*
    Check if an (x,y) is on the line (give some pixel wiggle room because this
    will be used to select the line with mouse).
    */
    public boolean containsLocation(int x, int y) {
      
        if (((double)y - (slope)*(double)x >= ((yIntercept) - 5)) &&
            ((double)y - (slope)*(double)x <= ((yIntercept) + 5)))
            return true;
        return false;
    }
    
    /*
    Move the line: xOne and yOne go to given (x,y), keeping slope and
    and length but not intercept
    */
    public void moveTo(int topLeftX, int topLeftY) {

        int oldTopLeftX = getTopLeftX();
        int oldTopLeftY = getTopLeftY();
        
        setTopLeftX(oldTopLeftX + (topLeftX - oldTopLeftX));
        setTopLeftY(oldTopLeftY + (topLeftY - oldTopLeftY));
        setXTwo(xTwo + (topLeftX - oldTopLeftX));
        setYTwo(yTwo + (topLeftY - oldTopLeftY));
        setYIntercept(calculateYIntercept(getTopLeftX(), getTopLeftY(),
                                          xTwo, yTwo));
    }

    /*
    public void setCurvature() {
    
        code here would set the curvature variable.
        would also have to change totalLength.
    }
    */
    
    public void setXTwo(int xTwo) {
     
        this.xTwo = xTwo;
    }  
    
    public int getXTwo() {
      
        return xTwo;
    }
    
    public void setYTwo(int yTwo) {
      
        this.yTwo = yTwo;
    }  
  
    public int getYTwo() {
    
        return yTwo;
    }

    public double getTotalLength() {
      
        return totalLength;
    }
    
    public void setTotalLength(double totalLength) {
      
        this.totalLength = totalLength;
    }
    
    public void setSlope(double slope) {
      
        this.slope = slope;
    }
    
    public double getSlope() {
      
        return slope;
    }
    
    public void setYIntercept(double yIntercept) {
      
        this.yIntercept = yIntercept;
    }
    
    public double getYIntercept() {
      
        return yIntercept;
    }
    
    /*
    These next three methods would only be used with curvature implementation.
    
    public double getAngle() {
      
        return angle;
    }  
    
    public void setLengthBetweenPoints() {
      
        // some code, maybe mouse input
        // would change lengthBetweenPoints/totalLength/angle, but keep orientation
    }
    
    public double getLengthBetweenPoints() {
      
        return lengthBetweenPoints;
    }
    */
    
    public String toString() {
    
    return "Line: \n\tFirst Point: (" + getTopLeftX() + ", " + getTopLeftY() + ")" +
           "\n\tSecond Point: (" + getXTwo() + ", " + getYTwo() + ")" +
           "\n\tSlope: " + getSlope() + "\n\tY Intercept: " + getYIntercept() +
           "\n\tTotal Length: " + getTotalLength();
    }
}