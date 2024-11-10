package app.Java_classes;

/**
 * Class represeting a LGA from the Studio Project database
 * In the template, this only uses the code and name for 2018 (so you will need to update this to handle all years)
 *
 * @author Halil Ali, 2024. email: halil.ali@rmit.edu.au
 */

 public class CleanUp {
  private double RecyclingDisposed;
  private double OrganicsDisposed;
  private double WasteCollected;
  private double WasteRecycled;
  private double WasteDisposed;

 

  public CleanUp(double recDis, double orgDis,double wasCol,double wasRec,double wasDis) {
    RecyclingDisposed=recDis;
    OrganicsDisposed=orgDis;
    WasteCollected=wasCol;
    WasteRecycled=wasRec;
    WasteDisposed=wasDis;
  }
  
  public double getOrganicsDisposed() {
    return OrganicsDisposed;
  }
  public double getRecyclingDisposed() {
    return RecyclingDisposed;
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
 