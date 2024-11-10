package app.Java_classes;

public class ROrganicGeneration {

    public String ORGANICcollected;
    public String ORGANICrecycled;
    public String ORGANICdisposed;
    public String Organicyear;

    public ROrganicGeneration(String collected, String recycled, String disposed){
        ORGANICcollected = collected;
        ORGANICrecycled = recycled;
        ORGANICdisposed = disposed;
    }

    public ROrganicGeneration() {

    }
    public String getcollected(){
     return this.ORGANICcollected;

    }

    public String getrecycled(){
        return this.ORGANICrecycled;

       }

    public String getdisposed(){
        return this.ORGANICdisposed;
    }

    public String getYear(){
        return this.Organicyear;


       }


}