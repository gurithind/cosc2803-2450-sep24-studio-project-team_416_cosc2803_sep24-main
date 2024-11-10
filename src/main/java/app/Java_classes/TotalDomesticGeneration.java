package app.Java_classes;

/**
 * Class represeting a LGA from the Studio Project database
 * In the template, this only uses the code and name for 2018 (so you will need to update this to handle all years)
 *
 * @author Halil Ali, 2024. email: halil.ali@rmit.edu.au
 */

 public class TotalDomesticGeneration {
    // LGA Code
    private int code;
    private int year;
    private double recylables;
    private double organics;
    private double residualWaste;
    private double totalDomesticCollected;
    private double totalRecycled;
    private double totalDisposed;
    private double RecyclingRate;

 
    /**
     * Create an LGA and set the fields
     */
    public TotalDomesticGeneration(int code, int year, double rcy,double org,double res,double totalCol,double totalRec,double totalDis,double rate) {
      this.code = code;
      this.year=year;
      recylables=rcy;
      organics=org;
      residualWaste=res;
      totalDomesticCollected=totalCol;
      totalRecycled=totalRec;
      totalDisposed=totalDis;
      RecyclingRate=rate;
    }
 
    public int getCode() {
       return code;
    }
 
    public int getYear() {
      return year;
   }
   public double getRecylables() {
      return recylables;
   }
   public double getOrganics() {
      return organics;
   }
   public double getResidualWaste() {
      return residualWaste;
   }
   public double getTotalDomesticCollected() {
      return totalDomesticCollected;
   }
   public double getTotalRecycled() {
      return totalRecycled;
   }
   public double getTotalDisposed() {
      return totalDisposed;
   }
   public double getRecyclingRate() {
      return RecyclingRate;
   }
 }
 