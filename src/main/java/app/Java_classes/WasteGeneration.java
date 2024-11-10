package app.Java_classes;
import java.util.ArrayList;

/**
 * Class represeting a LGA from the Studio Project database
 * In the template, this only uses the code and name for 2018 (so you will need to update this to handle all years)
 *
 * @author Halil Ali, 2024. email: halil.ali@rmit.edu.au
 */

 public class WasteGeneration {

   private int Year;
   private int TotalLandfillDisposed;
   private ArrayList<KerbsideBin> KersideBin;
   private ArrayList<Kerbside> Kerside;
   private ArrayList<DropOff> DropOff;
   private ArrayList<CleanUp> CleanUp;
   private ArrayList<TotalWaste> TotalWaste;

 

    public WasteGeneration(int year, int total) {
      this.Year=year;
      TotalLandfillDisposed=total;

    }
 
    public int getYear() {
      return Year;
   }
   public int getTotalDisposed() {
      return TotalLandfillDisposed;
   }
 }
 