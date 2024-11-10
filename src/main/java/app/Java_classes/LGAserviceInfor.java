package app.Java_classes;

/**
 * Class represeting a LGA from the Studio Project database
 * In the template, this only uses the code and name for 2018 (so you will need to update this to handle all years)
 *
 * @author Halil Ali, 2024. email: halil.ali@rmit.edu.au
 */

 public class LGAserviceInfor {
    // LGA Code
    private int code;
    private int year;
    private int population;
    private int hourseHold;
    private double avgCost;
    private int cleanupOccurences;
    private int residualHouse;
    private int recyclingHouse;
    private int gardenOrganicsHouse;
    private int Food_OrganicHouse;

 
    /**
     * Create an LGA and set the fields
     */
    public LGAserviceInfor(int code, int year, int pop, int org, int house, double avgCost, int cuService, int rdlHouse, int recHouse,int goHouse,int foHouse) {
      this.code = code;
      this.year=year;
      population=pop;
      hourseHold=house;
      this.avgCost=avgCost;
      cleanupOccurences=cuService;
      residualHouse=rdlHouse;
      recyclingHouse=recHouse;
      gardenOrganicsHouse=goHouse;
      Food_OrganicHouse=foHouse;

    }
 
    public int getCode() {
       return code;
    }
 
    public int getYear() {
      return year;
   }
   public int getPopulation() {
      return population;
   }
   public  int getHourseHold() {
      return hourseHold;
   }
   public  double getAverageCost() {
      return avgCost;
   }
   public  int getCleanupOccurences() {
      return cleanupOccurences;
   }
   public  int getResidualHouse() {
      return residualHouse;
   }
   public  int getRecyclingHouse() {
      return recyclingHouse;
   }
   public  int getGardenOrganicsHouse() {
      return gardenOrganicsHouse;
   }
   
   public  int getFood_OrganicHouse() {
      return Food_OrganicHouse;
   }
 }
 