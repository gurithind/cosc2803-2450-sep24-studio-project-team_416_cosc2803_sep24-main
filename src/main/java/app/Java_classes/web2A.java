package app.Java_classes;

 public class web2A {
    // LGA Code
    private int code;
 
    // LGA Name
    private String name;
    private int population;
    private int house;
    private double totalWasteColllected;
    private double totalRecyclingWasteRecycled;
    private double AvgRecycled;
    private double AvgWastePerHouse;
    
    //


    public web2A(int code, String name) {
      this.code = code;
      this.name = name;
   }
   public void setName(String name){
      this.name=name;
   }
   public void setPopulation(int population){
      this.population=population;
   }
   public void setHouseholdSurvey(int house){
      this.house=house;
   }
   public void setTotalRecyclingWasteCollected(double totalWasteColllected){
      this.totalWasteColllected=totalWasteColllected;
   }
   public void setTotalRecyclingWasteRecycled(double totalRecyclingWasteRecycled){
      this.totalRecyclingWasteRecycled=totalRecyclingWasteRecycled;
   }
   public void setAveragePercentageRecycled(double AvgRecycled){
      this.AvgRecycled=AvgRecycled;
   }
   public void setAverageWastePerHousehold(double AvgWastePerHouse){
      this.AvgWastePerHouse=AvgWastePerHouse;
   }



   
   public String getName(){
      return name;
   }
   public String getPopulation(){
      return String.valueOf(population);
   }
   public String getHouse(){
      return String.valueOf(house);
   }
   public String getTotalWasteColllected(){
      return String.valueOf(totalWasteColllected);
   }
   public String getTotalRecyclingWasteRecycled(){
      return String.valueOf(totalRecyclingWasteRecycled);
   }
   public String getAvgRecycled(){
      return String.valueOf(AvgRecycled);
   }
   public String getAvgWastePerHouse(){
      return String.valueOf(AvgWastePerHouse);
   }





   public web2A() {
   }


 }
 