package app.Java_classes;

/**
 * Class represeting a LGA from the Studio Project database
 * In the template, this only uses the code and name for 2018 (so you will need to update this to handle all years)
 *
 * @author Halil Ali, 2024. email: halil.ali@rmit.edu.au
 */

 public class KerbsideBin {
  private double Collected;
  private double Recycled;
  private double Disposed;

 

  public KerbsideBin(double col, double rec, double dis) {
    Collected=col;
    Recycled=rec;
    Disposed=dis;
  }
  
  public double getCollected() {
    return Collected;
  }
  public double getRecycled() {
    return Recycled;
  }
  public double getDisposed() {
    return Disposed;
  }
}
 