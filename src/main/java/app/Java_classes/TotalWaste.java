package app.Java_classes;

/**
 * Class represeting a LGA from the Studio Project database
 * In the template, this only uses the code and name for 2018 (so you will need to update this to handle all years)
 *
 * @author Halil Ali, 2024. email: halil.ali@rmit.edu.au
 */

 public class TotalWaste {
  private double Collected;
  private double Recycled;
  private double WasteCollected;
  private double WasteRecycled;
  private double WasteDisposed;

 

  public TotalWaste(double col, double rec,double wasCol,double wasRec,double wasDis) {
    Collected=col;
    Recycled=rec;
    WasteCollected=wasCol;
    WasteRecycled=wasRec;
    WasteDisposed=wasDis;
  }
  
  public double getCollected() {
    return Collected;
  }
  public double getRecycled() {
    return Recycled;
  }
  public double getWasteCollected() {
    return WasteCollected;
  }
  public double getWasteRecycled() {
    return WasteRecycled;
  }
  public double getWasteDisposed() {
    return WasteDisposed;
  }
}
 