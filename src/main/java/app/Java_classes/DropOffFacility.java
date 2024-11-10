package app.Java_classes;

/**
 * Class represeting a LGA from the Studio Project database
 * In the template, this only uses the code and name for 2018 (so you will need to update this to handle all years)
 *
 * @author Halil Ali, 2024. email: halil.ali@rmit.edu.au
 */

 public class DropOffFacility {
    // LGA Code
 
    // LGA Name
    private String facilityName;
    private String Access;
 
    /**
     * Create an LGA and set the fields
     */
    public DropOffFacility(String name, String access) {
       this.Access = access;
       this.facilityName = name;
    }
 
    public String getAccess() {
       return Access;
    }
 
    public String getName() {
       return facilityName;
    }
 }
 