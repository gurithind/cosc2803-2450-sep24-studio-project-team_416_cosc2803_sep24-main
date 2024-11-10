package helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class WasteRecyclingProcessCSV {

   //
/**********some part of SQl database is done without use this code, do not run this file!!!!!!!!, otherwise most of data will lost!!!!!!!!!!!!!!!!!!!!!!**************************************************************************************************** */
//
//   
   private static final String DATABASE = "jdbc:sqlite:database/WasteRecycling.db";
   private static final String WR_2018_19_LGA_SERVICES_CSV_FILE = "database/NSW_WasteRecycling_2018-2019_LGA_Services.csv";
   private static final String WR_2019_20_LGA_SERVICES_CSV_FILE = "database/NSW_WasteRecycling_2019-2020_LGA_Services.csv";
   

   public static void main (String[] args) {

      // *@^$@*&$@*$&@^*&$@ don't uncomment!! *@^$@*&$@*$&@^*&$@
      
      //dropTablesAndRecreateTables();
      //loadLGA();

      return;
   }

   public static void dropTablesAndRecreateTables() {

      Connection connection = null;
      Scanner s = new Scanner(System.in);
      String response = null;


      try {
         connection = DriverManager.getConnection(DATABASE);

         Statement statement = connection.createStatement();

         String query = null;
         query = "PRAGMA foreign_keys = OFF";
         System.out.println("Executing: \n" + query);
         statement.execute(query);
         query = "DROP TABLE IF EXISTS LGA";
         System.out.println("Executing: \n" + query);
         statement.execute(query);
         query = "DROP TABLE IF EXISTS Total_Domestic_Generation";
         System.out.println("Executing: \n" + query);
         statement.execute(query);
         query = "DROP TABLE IF EXISTS LGA_Service_Information";
         System.out.println("Executing: \n" + query);
         statement.execute(query);
         query = "DROP TABLE IF EXISTS Drop_Off_Facility";
         System.out.println("Executing: \n" + query);
         statement.execute(query);
         query = "DROP TABLE IF EXISTS  Waste_Kerbside_Waste_Bin";
         System.out.println("Executing: \n" + query);
         statement.execute(query);
         query = "DROP TABLE IF EXISTS Total_Domestic_Residual_Generation_and_Disposal";
         System.out.println("Executing: \n" + query);
         statement.execute(query);
         query = "DROP TABLE IF EXISTS  Waste_Clean_Up";
         System.out.println("Executing: \n" + query);
         statement.execute(query);

         query = "DROP TABLE IF EXISTS Waste_Drop_Off";
         System.out.println("Executing: \n" + query);
         statement.execute(query);
         query = "DROP TABLE IF EXISTS Waste_Total";
         System.out.println("Executing: \n" + query);
         statement.execute(query);
         query = "DROP TABLE IF EXISTS Waste_Kerbside";
         System.out.println("Executing: \n" + query);
         statement.execute(query);
         query = "DROP TABLE IF EXISTS Total_Domestic_Recycling_Generation";
         System.out.println("Executing: \n" + query);
         statement.execute(query);
         query = "DROP TABLE IF EXISTS Total_Domestic_Organics_Generation";
         System.out.println("Executing: \n" + query);
         statement.execute(query);
         query = "PRAGMA foreign_keys = ON";
         System.out.println("Executing: \n" + query);
         statement.execute(query);
         
         query = "CREATE TABLE LGA (" + "\n" +
                 "      LGA_code                                 INTEGER NOT NULL," + "\n" +
                 "      LGA_name                                 TEXT    ," + "\n" +
                 "      region_type                              TEXT    ," + "\n" +
                 "      council_name                             TEXT    ," + "\n" +
                 "      Regional_Groups                          TEXT    ," + "\n" +
                 "      year                                     TEXT   NOT NULL ," + "\n" +
                 "      PRIMARY KEY (LGA_code, year)" + "\n" +
                 ")";
         System.out.println("Executing: \n" + query);
         statement.execute(query);
         
         query = "CREATE TABLE Total_Domestic_Generation (" + "\n" +
                 "      LGA_code                                 INTEGER NOT NULL," + "\n" +
                 "      year                                     INTEGER NOT NULL," + "\n" +
                 "      recyclables                              INTEGER," + "\n" +
                 "      organics                                 INTEGER," + "\n" +
                 "      residual_waste                           INTEGER," + "\n" +
                 "      total_domestic_collected                 INTEGER," + "\n" +
                 "      total_recycled                           INTEGER," + "\n" +
                 "      total_disposed                           INTEGER," + "\n" +
                 "      recycling_rate                           FLOAT," + "\n" +
                 "      PRIMARY KEY (LGA_code, year)," + "\n" +
                 "      FOREIGN KEY (LGA_code, year) REFERENCES LGA(LGA_code, year)" + "\n" +
                 ")";
         System.out.println("Executing: \n" + query);
         statement.execute(query);

         query = "CREATE TABLE Total_Domestic_Recycling_Generation (" + "\n" +
                 "      LGA_code                                 INTEGER NOT NULL," + "\n" +
                 "      year                                     INTEGER NOT NULL," + "\n" +
                 "      type                                     text," + "\n" +
                 "      collected                                INTEGER," + "\n" +
                 "      recycled                                 INTEGER," + "\n" +
                 "      disposed                                 INTEGER," + "\n" +
                 "      PRIMARY KEY (LGA_code, year)," + "\n" +
                 "      FOREIGN KEY (LGA_code, year) REFERENCES LGA(LGA_code, year)" + "\n" +
                 ")";
         System.out.println("Executing: \n" + query);
         statement.execute(query);

         query = "CREATE TABLE Total_Domestic_Organics_Generation (" + "\n" +
                 "      LGA_code                                 INTEGER NOT NULL," + "\n" +
                 "      year                                     INTEGER NOT NULL," + "\n" +
                 "      type                                     text," + "\n" +
                 "      collected                                INTEGER," + "\n" +
                 "      recycled                                 INTEGER," + "\n" +
                 "      disposed                                 INTEGER," + "\n" +
                 "      PRIMARY KEY (LGA_code, year)," + "\n" +
                 "      FOREIGN KEY (LGA_code, year) REFERENCES LGA(LGA_code, year)" + "\n" +
                 ")";
         System.out.println("Executing: \n" + query);
         statement.execute(query);
         
         query = "CREATE TABLE LGA_Service_Information (" + "\n" +
                 "      LGA_code                                 INTEGER NOT NULL," + "\n" +
                 "      year                                     INTEGER NOT NULL," + "\n" +
                 "      population                               INTEGER," + "\n" +
                 "      household_survey                         INTEGER," + "\n" +
                 "      average_domestic_waste_management_cost   INTEGER," + "\n" +
                 "      cleanup_service_occurrences              INTEGER," + "\n" +
                 "      households_with_residual_services        INTEGER," + "\n" +
                 "      households_with_recycling_service        INTEGER," + "\n" +
                 "      households_with_organics_service         INTEGER," + "\n" +
                 "      PRIMARY KEY (LGA_code, year)," + "\n" +
                 "      FOREIGN KEY (LGA_code, year) REFERENCES LGA(LGA_code, year)" + "\n" +
                 ")";
         System.out.println("Executing: \n" + query);
         statement.execute(query);
         
         query = "CREATE TABLE Drop_Off_Facility (" + "\n" +
                 "      LGA_code                                 INTEGER NOT NULL," + "\n" +
                 "      facility_name                            TEXT," + "\n" +
                 "      access                                   TEXT," + "\n" +
                 "      year                                     INTEGER NOT NULL," + "\n" +
                 "      PRIMARY KEY (LGA_code, year)," + "\n" +
                 "      FOREIGN KEY (LGA_code, year) REFERENCES LGA(LGA_code, year)" + "\n" +
                 ")";
         System.out.println("Executing: \n" + query);
         statement.execute(query);
         
         query = "CREATE TABLE Waste_Kerbside_Waste_Bin (" + "\n" +
                 "      LGA_code                                 INTEGER NOT NULL," + "\n" +
                 "      collected                                INTEGER," + "\n" +
                 "      recycled                                 INTEGER," + "\n" +
                 "      disposed                                 INTEGER," + "\n" +
                 "      year                                     INTEGER NOT NULL," + "\n" +
                 "      PRIMARY KEY (LGA_code, year)," + "\n" +
                 "      FOREIGN KEY (LGA_code, year) REFERENCES LGA(LGA_code, year)" + "\n" +
                 ")";
         System.out.println("Executing: \n" + query);
         statement.execute(query);

         query = "CREATE TABLE Waste_Kerbside (" + "\n" +
                 "      LGA_code                                 INTEGER NOT NULL," + "\n" +
                 "      recycling bin disposed                                INTEGER," + "\n" +
                 "      organics bin disposed                                 INTEGER," + "\n" +
                 "      year                                     INTEGER NOT NULL," + "\n" +
                 "      PRIMARY KEY (LGA_code, year)," + "\n" +
                 "      FOREIGN KEY (LGA_code, year) REFERENCES LGA(LGA_code, year)" + "\n" +
                 ")";
         System.out.println("Executing: \n" + query);
         statement.execute(query);
         
         query = "CREATE TABLE Total_Domestic_Residual_Generation_and_Disposal (" + "\n" +
                 "      LGA_code                                 INTEGER NOT NULL," + "\n" +
                 "      year                                     INTEGER NOT NULL," + "\n" +
                 "      total_disposed_to_landfill               INTEGER," + "\n" +
                 "      PRIMARY KEY (LGA_code, year)," + "\n" +
                 "      FOREIGN KEY (LGA_code, year) REFERENCES LGA(LGA_code, year)" + "\n" +
                 ")";
         System.out.println("Executing: \n" + query);
         statement.execute(query);
         
         query = "CREATE TABLE Waste_Clean_Up (" + "\n" +
                 "      LGA_code                                 INTEGER NOT NULL," + "\n" +
                 "      year                                     INTEGER NOT NULL," + "\n" +
                 "      recycling_disposed                       INTEGER," + "\n" +
                 "      organics_disposed                        INTEGER," + "\n" +
                 "      waste_disposed                           INTEGER," + "\n" +
                 "      waste_collected                          INTEGER," + "\n" +
                 "      waste_recycled                           INTEGER," + "\n" +
                 "      PRIMARY KEY (LGA_code, year)," + "\n" +
                 "      FOREIGN KEY (LGA_code, year) REFERENCES LGA(LGA_code, year)" + "\n" +
                 ")";
         System.out.println("Executing: \n" + query);
         statement.execute(query);
         
         query = "CREATE TABLE Waste_Drop_Off (" + "\n" +
                 "      LGA_code                                 INTEGER NOT NULL," + "\n" +
                 "      year                                     INTEGER NOT NULL," + "\n" +
                 "      recycling_disposed                       INTEGER," + "\n" +
                 "      organics_disposed                        INTEGER," + "\n" +
                 "      waste_disposed                           INTEGER," + "\n" +
                 "      waste_collected                          INTEGER," + "\n" +
                 "      waste_recycled                           INTEGER," + "\n" +
                 "      PRIMARY KEY (LGA_code, year)," + "\n" +
                 "      FOREIGN KEY (LGA_code, year) REFERENCES LGA(LGA_code, year)" + "\n" +
                 ")";
         System.out.println("Executing: \n" + query);
         statement.execute(query);
         
         query = "CREATE TABLE Waste_Total (" + "\n" +
                 "      LGA_code                                 INTEGER NOT NULL," + "\n" +
                 "      year                                     INTEGER NOT NULL," + "\n" +
                 "      collected                                INTEGER," + "\n" +
                 "      recycled                                 INTEGER," + "\n" +
                 "      waste_disposed                           INTEGER," + "\n" +
                 "      recycling_disposed                       INTEGER," + "\n" +
                 "      organics_disposed                        INTEGER," + "\n" +
                 "      PRIMARY KEY (LGA_code, year)," + "\n" +
                 "      FOREIGN KEY (LGA_code, year) REFERENCES LGA(LGA_code, year)" + "\n" +
                 ")";
         System.out.println("Executing: \n" + query);
         statement.execute(query);
         
         
         } catch (Exception e) {
             e.printStackTrace();
         }
   }
         

   //This is the method for import LGA data into LGA table
   //Hi Imath, you can copy and paste this method as a temple for other tables
   // I'll comment the part that need modify.
   // by the way, I modify the strcuture of table. Now all table have code and year as Primary key.
   public static void loadLGA() {
    Connection connection = null;
    PreparedStatement statement = null;
    BufferedReader reader = null;
    String line;
    // set the year, some table have years not in 2018-2020
    String year = "2018-2019";
    try {
      // find the file path
      // the deep blue part is the name of the CSV file, set the name and actual path on the top of this java file
      // you should be able to set them on top of this file
        reader = new BufferedReader(new FileReader(WR_2018_19_LGA_SERVICES_CSV_FILE));
        String header = reader.readLine();
        System.out.println("Heading row\n" + header + "\n");
        connection = DriverManager.getConnection(DATABASE);
        while ((line = reader.readLine()) != null) {
            String[] splitline = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            // store the value of CSV into these String
            // modify them as each table have different attributes
            // the deep blue part is used from the ServicesFields.java file, in the helper folder. They locate thelocation of data in CSV file
            // depend on the CSV file, create new file similar to ServicesFields.java when you need
            // I put comment in ServicesFields.java to help you quickly understand what are they for.
            String lgaCode = (splitline[ServicesFields.LGA_CODE]).replaceAll("^\"|\"$", "");
            String lgaName = (splitline[ServicesFields.LGA_NAME]).replaceAll("^\"|\"$", "");
            String regionType = (splitline[ServicesFields.REGION_TYPE]).replaceAll("^\"|\"$", "");
            String councilName = (splitline[ServicesFields.COUNCIL_NAME]).replaceAll("^\"|\"$", "");
            String regionGroup = (splitline[ServicesFields.REGIONAL_GROUP]).replaceAll("^\"|\"$", "");
            // change lGA_code... base on the name of attributes and add more "?"
            //for example:
            //   .....(A) Values (?);
            //   .....(A, B) Values (?, ?);
            //   .....(A, B, C) Values (?, ?, ?);
            String myStatement = "INSERT INTO lga (LGA_Code, LGA_Name, Region_Type, Council_Name, Regional_Groups, year) VALUES (?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(myStatement);
            //this add the value into SQL
            //these String (lgaCode, lgaName...) is initializing and get the value on the code above
            //the number is the order in the table of SQL, as table is created, fill them in based on the corresponding column order of that table (LGA)
            statement.setString(1, lgaCode);
            statement.setString(2, lgaName);
            statement.setString(3, regionType);
            statement.setString(4, councilName);
            statement.setString(5, regionGroup);
            statement.setString(6, year);
            statement.executeUpdate();
        }
        // this is just a message to terminal, tell you that this part is done successfully
        System.out.println("Inserted all " + year + " LGA data\n");
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (reader != null) reader.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // Below is the same process to adding data of another year(2019-2020).
    // as table have code and year, same table have different year not in 2018-2020, be careful with them.
    // better check the csv file in excel before.
    year = "2019-2020";
    try {
        reader = new BufferedReader(new FileReader(WR_2019_20_LGA_SERVICES_CSV_FILE));
        String header = reader.readLine();
        System.out.println("Heading row\n" + header + "\n");
        connection = DriverManager.getConnection(DATABASE);
        while ((line = reader.readLine()) != null) {
            String[] splitline = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            String lgaCode = (splitline[ServicesFields.LGA_CODE]).replaceAll("^\"|\"$", "");
            String lgaName = (splitline[ServicesFields.LGA_NAME]).replaceAll("^\"|\"$", "");
            String regionType = (splitline[ServicesFields.REGION_TYPE]).replaceAll("^\"|\"$", "");
            String councilName = (splitline[ServicesFields.COUNCIL_NAME]).replaceAll("^\"|\"$", "");
            String regionGroup = (splitline[ServicesFields.REGIONAL_GROUP]).replaceAll("^\"|\"$", "");
            String myStatement = "INSERT INTO lga (LGA_Code, LGA_Name, Region_Type, Council_Name, Regional_Groups, year) VALUES (?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(myStatement);
            statement.setString(1, lgaCode);
            statement.setString(2, lgaName);
            statement.setString(3, regionType);
            statement.setString(4, councilName);
            statement.setString(5, regionGroup);
            statement.setString(6, year);
            statement.executeUpdate();
        }
        System.out.println("Inserted all " + year + " LGA data\n");
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (reader != null) reader.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





















    
}


























/*
   public static void loadPeriods() {
      // JDBC Database Object
      Connection connection = null;

      // Like JDBCConnection, we need some error handling.
      try {
         connection = DriverManager.getConnection(DATABASE);

         for (int i = START_YEAR; i != END_YEAR; ++i) {
            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();

            // Create Insert Statement
            String query = "INSERT into period VALUES (\""
                           + i + "-" + (i+1) + "\", "
                           + i + ", " + (i+1)
                           + ")";

            // Execute the INSERT
            System.out.println("Executing: \n" + query);
            statement.execute(query);
         }
         System.out.println("\ninserted all periods\npress enter to continue");
         System.in.read();

      } catch (Exception e) {
         e.printStackTrace();
      }
   }


   // Load up the RegionTypes table
   // This only needs to be done once
   // Comment this out after runnning it the first time
   public static void loadRegionTypes() {
      // JDBC Database Object
      Connection connection = null;
      PreparedStatement statement = null;
      String Regions[][] = {{"A", "Area"},{"C", "City"}};

      // Like JDBCConnection, we need some error handling.
      try {
         connection = DriverManager.getConnection(DATABASE);

         for (int i = 0; i != Regions.length; ++i) {

            // Prepare a new SQL Query & Set a timeout
            String myStatement = " INSERT INTO RegionType (regionType, name) VALUES (?, ?)";
            statement= connection.prepareStatement(myStatement );
            statement.setString(1, Regions[i][REGION_TYPE]);
            statement.setString(2, Regions[i][REGION_NAME]);
            System.out.println(statement.toString());
            statement.executeUpdate();
         }
         System.out.println("\ninserted all regiontypes\npress enter to continue");
         System.in.read();

      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   // loads the '2018-2019' LGA data into the LGA table
   // note it only loads the code, name, and region type
   // need to update this to handle other fields as necessary (based on your design)
   // also need to load the 2019-2020 data as necessary
   public static void load2018_19LGAs() {
      // JDBC Database Object
      Connection connection = null;
      PreparedStatement statement = null;
      BufferedReader reader = null;
      String line;
      int year = 2018;

      // We need some error handling.
      try {
         // Open A CSV File to process, one line at a time
         // CHANGE THIS to process a different file
         reader = new BufferedReader(new FileReader(WR_2018_19_LGA_SERVICES_CSV_FILE));

         // Read (and ignore) the first line of "headings"
         String header = reader.readLine();

         System.out.println("Heading row\n" + header + "\n");

         // Setup JDBC
         // Connect to JDBC database
         connection = DriverManager.getConnection(DATABASE);

         //read CSV file line by line, stop if not more lines
         while ((line = reader.readLine())!=null) {
            //System.out.println(row);
            // split the line up by commas (ignoring commas within quoted fields)
            String[] splitline = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);

            // Get some of the columns in order (and remove double quotes)
            // Note you will need to do the same in the other csv files to match
            // Add additional code to retrieve other columns as necessary
            String lgaCode = (splitline[ServicesFields.LGA_CODE]).replaceAll("^\"|\"$", "");
            String lgaName = (splitline[ServicesFields.LGA_NAME]).replaceAll("^\"|\"$", "");
            String regionType = (splitline[ServicesFields.REGION_TYPE]).replaceAll("^\"|\"$", "");


            // Create Insert Statement
            String myStatement = " INSERT INTO lga (code, name, regionType) VALUES (?, ?, ?)";
            statement= connection.prepareStatement(myStatement );
            statement.setString(1, lgaCode);
            statement.setString(2, lgaName);
            statement.setString(3, regionType);
            System.out.println(statement.toString());
            statement.executeUpdate();
            
         }
         System.out.println("\ninserted all " + year + " lga data\npress enter to continue");
         System.in.read();

      } catch (Exception e) {
         e.printStackTrace();
      }
      finally {
         if(reader!=null) {
            try{
            reader.close();
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      }
   }

   // Loads the '2018-2019' Organics data for each LGA into the LgaOrganicsStatistics table
   // note it only loads the code, year, and KerbsideOrganicsCollected statistics
   // need to update this to handle other fields as necessary (based on your design)
   // need to also load the 2019-2020 data
   // Comment this out after runnning it the first time
   public static void load2018_19Organics() {
      // JDBC Database Object
      Connection connection = null;
      PreparedStatement statement = null;
      BufferedReader reader = null;
      String line;
      String period = "2018-2019";

      // We need some error handling.
      try {
         // Open A CSV File to process, one line at a time
         // copy and CHANGE THIS to process a different file
         reader = new BufferedReader(new FileReader(WR_2018_19_LGA_ORGANICS_CSV_FILE));
//fff
         // Read (and ignore) the first 3 lines of "headings"
         String header = reader.readLine();
         header = reader.readLine();
         header = reader.readLine();

         System.out.println("Heading row\n" + header + "\n");

         // Setup JDBC
         // Connect to JDBC database
         connection = DriverManager.getConnection(DATABASE);

         //read CSV file line by line, stop if not more lines
         while ((line = reader.readLine())!=null) {

            // split the line up by commas (ignoring commas within quoted fields)
            String[] splitline = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);

            // Get relevant columns in order (and remove double quotes)
            // Note you will need to do the same in the other csv files to match
            String lgaCode = (splitline[OrganicsFields.LGACODE]).replaceAll("^\"|\"$", "");
            // remove double quotes, commas and hyphens within the statistics data (otherwise it will be text not int)
            String KerbsideOrganicsCollected = (splitline[OrganicsFields.KERBSIDE_ORGANICS_COLLECTED]).replaceAll("^\"| |,|-|\"$", "");
            //String KerbsideOrganicsRecycled  = (splitline[OrganicsFields.KERBSIDE_ORGANICS_RECYCLED]).replaceAll("^\"| |,|-|\"$", "");
            //String KerbsideOrganicsDisposed  = (splitline[OrganicsFields.KERBSIDE_ORGANICS_DISPOSED]).replaceAll("^\"| |,|-|\"$", "");

            // Create Insert Statement
            String myStatement = " INSERT INTO LgaOrganicsStatistics (code, period, KerbsideOrganicsCollected) VALUES (?, ?, ?)";
            statement= connection.prepareStatement(myStatement );
            statement.setString(1, lgaCode);
            statement.setString(2, period);
            statement.setString(3, KerbsideOrganicsCollected);
            System.out.println(statement.toString());
            statement.executeUpdate();
         }
         System.out.println("\ninserted all " + period + " LGA Kerbside Collected Organics data\npress enter to continue");
         System.in.read();

      } catch (Exception e) {
         e.printStackTrace();
      }
      finally {
         if(reader!=null) {
            try{
            reader.close();
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      }
   }

       */
}