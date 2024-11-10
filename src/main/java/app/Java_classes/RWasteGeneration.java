package app.Java_classes;



public class RWasteGeneration {

    public  String WASTEcollected;
    public  String WASTErecycled;
    public  String WASTEdisposed;
    public String Wasteyear;
    public RWasteGeneration(String collected, String recycled, String disposed){
        WASTEcollected = collected;
        WASTErecycled = recycled;
        WASTEdisposed = disposed;

    }
   public RWasteGeneration(){}
    public String getcollected(){
     return this.WASTEcollected;

    }

    public String getrecycled(){
        return this.WASTErecycled;

       }

       public String getdisposed(){
        return this.WASTEdisposed;
       }

        public String getYear(){
            return this.Wasteyear;

       }


}