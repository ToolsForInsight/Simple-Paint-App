/*
Classes should implement this interface if they want to be compared to
other shapes.
Currently, the only comparisons allowed envisioned are between closed shapes
(e.g., that have area and perimeter).  This is from a previous build, before
the Line class made an appearance.
Needs rework to be useful. 
*/
package interfaces;

public interface ComparableShape {

    public double calculateArea();
    public void setArea(double area);
    public double getArea();
    
    public double calculatePerimeter();
    public void setPerimeter(double perimeter);
    public double getPerimeter();
}