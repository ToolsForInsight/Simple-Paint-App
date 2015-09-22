/*
q classes should implement this interface if it wants to provide a method
to reset its state to default values.
*/
package interfaces;

public interface Resettable {
  
    public void resetComponents();
}