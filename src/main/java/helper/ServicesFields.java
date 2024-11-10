package helper;

/*
* this file track the position of fields in each CSV file 
for example, this file is point to NSW_WasteRecycling_2018-2019_LGA_Services.csv (the 1st one)
you can see in that CSV file, the first column is the name of column
the number is used to located them
as each comma (,)  divide them, the number increased 1.
the number start from 0 is the first one.
*/

public class ServicesFields {
    public static final int LGA_CODE = 0;
    public static final int LGA_NAME  = 1;
    public static final int REGION_TYPE = 2;
    public static final int COUNCIL_NAME = 3;
    public static final int REGIONAL_GROUP = 4;
    public static final int POPULATION = 7;
    public static final int HH_SURVEYED = 8;
    public static final int DWMC = 9;
    public static final int HH_RESIDUAL_WASTE_SERVICE = 10;
    public static final int HH_RECYCLING = 11;
    public static final int HH_GARDEN_ORGANICS = 12;
    public static final int HH_FOOD_GARDEN_ORGANICS = 13;
    public static final int CLEANUP_SERVICE = 14;
    public static final int DROPOFF_ACCESS = 15;
    public static final int DROPOFF_1 = 17;
    public static final int DROPOFF_2 = 18;
    public static final int DROPOFF_3 = 19;
    public static final int DROPOFF_4 = 20;
    public static final int DROPOFF_5 = 21;
    public static final int DROPOFF_6 = 22;
}
