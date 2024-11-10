package app.Java_classes;

import java.util.ArrayList;

 public class LGA {
    // LGA Code
    private int code;
 
    // LGA Name
    private String name;
    private int year;
    private String regionType;
    private String council;
    private String regionGroup;
    private ArrayList<DropOffFacility> DropOffFacility;
    private ArrayList<RecyclingGeneration> RecyclingGeneration;
    //private ArrayList<OrganicsGeneration> OrganicsGeneration;
    private ArrayList<WasteGeneration> WasteGeneration;

    
 
    /**
     * Create an LGA and set the fields
     */
    public LGA(int code, String name) {
      this.code = code;
      this.name = name;
   }
   public LGA(int code, String name,String regionType, String cil,String regGP) {
      this.code = code;
      this.name = name;
      this.regionType=regionType;
      this.council=cil;
      this.regionGroup=regGP;
   }
 
   public int getCode() {
      return code;
   }
    public String getName() {
      return name;
   }
   public String getRegionType() {
      return regionType;
   }
   public String getRegionGroup() {
      return regionGroup;
   }
   public String getCouncilName() {
      return council;
   }
 }
 