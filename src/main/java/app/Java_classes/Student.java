
package app.Java_classes;


public class Student {
    public static int ID;
    public static String Name;
   
    public Student(int number, String name){

    this.ID = number;
    this.Name = name;

    }
   

    public void setID(int ID){
        this.ID = ID;
    }
    
    public void setName(String Name){
        this.Name = Name;
    }
    
    public int getID(){
        return this.ID;
    }

    public String getName(){
        return Name;
    }
}