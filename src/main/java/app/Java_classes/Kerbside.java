package app.Java_classes;

/**
 * Class represeting a LGA from the Studio Project database
 * In the template, this only uses the code and name for 2018 (so you will need to update this to handle all years)
 *
 * @author Halil Ali, 2024. email: halil.ali@rmit.edu.au
 */

 public class Kerbside {
  private double RecyclingDisposed;
  private double OrganicsDisposed;

 

  public Kerbside(double recDis, double orgDis) {
    RecyclingDisposed=recDis;
    OrganicsDisposed=orgDis;
  }
  
  public double getOrganicsDisposed() {
    return OrganicsDisposed;
  }
  public double getRecyclingDisposed() {
    return RecyclingDisposed;
  }
}
 