package app.Java_classes;

/**
 * Class represeting a LGA from the Studio Project database
 * In the template, this only uses the code and name for 2018 (so you will need to update this to handle all years)
 *
 * @author Halil Ali, 2024. email: halil.ali@rmit.edu.au
 */

 public class RecyclingGeneration {

    public  String RECYCLINGcollected;
    public  String RECYCLINGrecycled;
    public  String RECYCLINGdisposed;
    public  String Recyclingyear;

    public RecyclingGeneration(String collected, String recycled, String disposed) {
        RECYCLINGcollected = collected;
        RECYCLINGrecycled = recycled;
        RECYCLINGdisposed = disposed;

    }

    public RecyclingGeneration() {}

    public String getCollected() {
        return RECYCLINGcollected;
    }

    public String getRecycled() {
        return RECYCLINGrecycled;
    }

    public String getDisposed() {
        return RECYCLINGdisposed;
    }

    public String getYear(){
        return Recyclingyear;
    }
 }
