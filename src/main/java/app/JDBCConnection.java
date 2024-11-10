package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.Java_classes.LGA;
import app.Java_classes.ROrganicGeneration;
import app.Java_classes.RWasteGeneration;
import app.Java_classes.RecyclingGeneration;
import app.Java_classes.web2A;

/**
 * Class for Managing the JDBC Connection to a SQLLite Database.
 * Allows SQL queries to be used with the SQLLite Databse in Java.
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 * @author Halil Ali, 2024. email: halil.ali@rmit.edu.au
 */

 //@@@@@@@@@@@@@@@@@@@ basicly, use this for sql to java.

public class JDBCConnection {

    // Name of database file (contained in database folder)
    public static final String DATABASE = "jdbc:sqlite:database/WasteRecycling.db";
    ROrganicGeneration organicsgen = null;
    RecyclingGeneration recyclingGen=null;
    RWasteGeneration wasteGen= null;
    /**
     * This creates a JDBC Object so we can keep talking to the database
     */
    public JDBCConnection() {
        System.out.println("Created JDBC Connection Object");
    }

    /**
     * Get all of the Countries in the database.
     * @return
     *    Returns an ArrayList of LGA objects
     */
    public ArrayList<LGA> getAllCountries() {
        // Create the ArrayList of LGA objects to return
        ArrayList<LGA> lgas = new ArrayList<LGA>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT * FROM lga";

            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            while (results.next()) {
                // Lookup the columns we need
                int code     = results.getInt("LGA_code");
                String name  = results.getString("LGA_name");

                // Create an LGA Object
                LGA lga = new LGA(code, name);

                // Add the LGA object to the array
                lgas.add(lga);
            }

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the countries
        return lgas;
    }

    // TODO: Add your required methods here

    public ArrayList<String> getAllRegions() {
    ArrayList<String> regions = new ArrayList<>();
    Connection connection = null;

    try {
        // Connect to the database
        connection = DriverManager.getConnection(JDBCConnection.DATABASE);
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // SQL query to retrieve regional groups
        String query = "SELECT DISTINCT Regional_Groups FROM LGA";
        ResultSet results = statement.executeQuery(query);

        // Process results and add each group to the ArrayList
        while (results.next()) {
            String groups = results.getString("Regional_Groups");
            regions.add(groups);  // group name as a String
        }
        statement.close();
    } catch (SQLException e) {
        System.err.println(e.getMessage());
    } finally {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    return regions;
}







//here's my method to use stored LGA information to run sql for 2A:


        public ArrayList<web2A> get2Adata(String order, String time, String type, List<String> lgas, List<String> subTypes) {
        Connection connection = null;
        ArrayList<web2A> result = new ArrayList<>(); // Initialize result list

        try {
            connection = DriverManager.getConnection(JDBCConnection.DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // Construct the SQL queryString TYPE = "";
            String query = "";
            String TYPE = "";
            if (type.equals("Recycling")) {
                TYPE = "Total_Domestic_Recycling_Generation";
            } else if (type.equals("Organics")) {
                TYPE = "Total_Domestic_Organics_Generation";
            } else if (type.equals("Waste")) {
                TYPE = "Total_Domestic_Waste_Generation";
            } else {
                // Handle unexpected 'type' values
                throw new IllegalArgumentException("Invalid type: " + type);
            }

            query += "SELECT " +
         "LGA.LGA_name AS name, " +
         "LGA_Service_Information.population AS population, " +
         "LGA_Service_Information.household_survey AS house, " +
         "SUM(" + TYPE + ".collected) AS total_collected, " +
         "SUM(" + TYPE + ".recycled) AS total_recycled, " +
         "CASE " +
             "WHEN SUM(" + TYPE + ".collected) > 0 " +
             "THEN (SUM(" + TYPE + ".recycled) * 100.0 / SUM(" + TYPE + ".collected)) " +
             "ELSE 0 " +
         "END AS AvgRecycled, " +
         "CASE " +
             "WHEN LGA_Service_Information.household_survey > 0 " +
             "THEN (SUM(" + TYPE + ".collected) * 1.0 / LGA_Service_Information.household_survey) " +
             "ELSE 0 " +
         "END AS AvgWastePerHouse " +
     "FROM " +
         "LGA " +
     "JOIN " +
         "LGA_Service_Information ON LGA.LGA_code = LGA_Service_Information.LGA_code " +
     "LEFT JOIN " +
         TYPE + " ON LGA.LGA_code = " + TYPE + ".LGA_code " +
         "AND LGA_Service_Information.year = " + TYPE + ".year " +
     "WHERE " +
         "LGA_Service_Information.year = '" + time + "'";

// Check if `lgas` is not empty before appending the `IN` clause for LGA names
if (!lgas.isEmpty()) {
    query += " AND LGA.LGA_name IN (";
    for (int i = 0; i < lgas.size(); i++) {
        query += "'" + lgas.get(i) + "'";
        if (i < lgas.size() - 1) {
            query += ",";
        }
    }
    query += ") ";
}

// Check if `subTypes` is not empty before appending the `IN` clause for subtypes
if (!subTypes.isEmpty()) {
    query += " AND " + TYPE + ".type IN (";
    for (int i = 0; i < subTypes.size(); i++) {
        query += "'" + subTypes.get(i) + "'";
        if (i < subTypes.size() - 1) {
            query += ",";
        }
    }
    query += ") ";
}

query += "GROUP BY " +
         "LGA.LGA_name, " +
         "LGA_Service_Information.population, " +
         "LGA_Service_Information.household_survey;";









    // Execute the query
    ResultSet results = statement.executeQuery(query);
    while (results.next()) {
        String name = results.getString("name");
        int population = results.getInt("population");
        int householdSurvey = results.getInt("house");
        double totalWasteCollected = results.getDouble("total_collected");
        double totalWasteRecycled = results.getDouble("total_recycled");
        double averagePercentageRecycled = results.getDouble("AvgRecycled");
        double averageWastePerHousehold = results.getDouble("AvgWastePerHouse");



    // Create a new instance of web2A and set the values
        web2A lga = new web2A();
        lga.setName(name);
        lga.setPopulation(population);
        lga.setHouseholdSurvey(householdSurvey);
        lga.setTotalRecyclingWasteCollected(totalWasteCollected);
        lga.setTotalRecyclingWasteRecycled(totalWasteRecycled);
        lga.setAveragePercentageRecycled(averagePercentageRecycled);
        lga.setAverageWastePerHousehold(averageWastePerHousehold);
        result.add(lga);
    }
    statement.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }

    // Method to fetch data for a specific council, year, and waste type for Page 2C
public ArrayList<LGA> getCouncilWasteData(String council, String year, String wasteType) {
    ArrayList<LGA> result = new ArrayList<>();
    Connection connection = null;

    try {
        // Establish the connection to the database
        connection = DriverManager.getConnection(DATABASE);

        // Prepare the SQL query to retrieve data based on user input
        String query = "SELECT * FROM WasteData WHERE council_name = ? AND year = ? AND waste_type = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, council);
        stmt.setString(2, year);
        stmt.setString(3, wasteType);
        
        // Execute the query
        ResultSet rs = stmt.executeQuery();

        // Process each result and create LGA objects
        while (rs.next()) {
            int code = rs.getInt("LGA_code");
            String name = rs.getString("LGA_name");
            double collected = rs.getDouble("total_collected");
            double recycled = rs.getDouble("total_recycled");
            int population = rs.getInt("population");

            // Create an LGA Object and add it to the list
            LGA lga = new LGA(code, name, collected, recycled, population);
            result.add(lga);
        }

        // Close the statement
        stmt.close();

    } catch (SQLException e) {
        System.err.println(e.getMessage());
    } finally {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    return result;
}




    public ArrayList<String> getAllServiceInfo() {
    ArrayList<String> serviceInfoList = new ArrayList<>();

    // Use try-with-resources to ensure resources are closed
    try (Connection connection = DriverManager.getConnection(JDBCConnection.DATABASE);
         Statement statement = connection.createStatement()) {

        statement.setQueryTimeout(30);

        // SQL query to retrieve population and household_survey
        String query = "SELECT population, household_survey FROM LGA_Service_Information";
        ResultSet results = statement.executeQuery(query);

        // Process results and add each record to the ArrayList as a formatted String
        while (results.next()) {
            int population = results.getInt("population");
            String householdSurvey = results.getString("household_survey");
            String record = "Population: " + population + ", Household Survey: " + householdSurvey;
            serviceInfoList.add(record);  // Add the formatted string to the list
        }

    } catch (SQLException e) {
        System.err.println("SQL Error: " + e.getMessage());
    }

    return serviceInfoList;

}


public RecyclingGeneration getRecycled(String regional){

    // Create the ArrayList of LGA objects to return

       Connection connection = null;

       try {
           // Connect to JDBC data base
           connection = DriverManager.getConnection(DATABASE);

           // Prepare a new SQL Query & Set a timeout
           Statement statement = connection.createStatement();
           statement.setQueryTimeout( 30);

           // The Query
           String query = "SELECT collected,recycled,disposed FROM Total_Domestic_Recycling_Generation;" ;

           // Get Result
           ResultSet results = statement.executeQuery(query);

           // Process all of the results
           while (results.next()) {
               // Lookup the columns we need




               String wastec = results.getString("collected");
               String recyclingc = results.getString("recycled");
               String disposedc = results.getString("disposed");

               recyclingGen = new RecyclingGeneration(recyclingc,wastec, disposedc);



           }

           // Close the statement because we are done with it
           statement.close();
       } catch (SQLException e) {
           // If there is an error, lets just pring the error
           System.err.println(e.getMessage());
       } finally {
           // Safety code to cleanup
           try {
               if (connection != null) {
                   connection.close();
               }
           } catch (SQLException e) {
               // connection close failed.
               System.err.println(e.getMessage());
           }
       }

      // ArrayList<Persona> personas;
   // Finally we return all of the countries
   return recyclingGen;
}

public ROrganicGeneration getOrganic(String regional){

    // Create the ArrayList of LGA objects to return
       // Setup the variable for the JDBC connection
       Connection connection = null;

       try {
           // Connect to JDBC data base
           connection = DriverManager.getConnection(DATABASE);

           // Prepare a new SQL Query & Set a timeout
           Statement statement = connection.createStatement();
           statement.setQueryTimeout( 30);

           // The Query
           String query = "select collected,recycled,disposed from Total_Domestic_Organics_Generation; " ;

           // Get Result
           ResultSet results = statement.executeQuery(query);

           // Process all of the results
           while (results.next()) {
               // Lookup the columns we need

               String wastec = results.getString("collected");
               String recyclingc = results.getString("recycled");
               String disposedc = results.getString("disposed");

               organicsgen = new ROrganicGeneration( recyclingc,wastec, disposedc);

           }

           // Close the statement because we are done with it
           statement.close();
       } catch (SQLException e) {
           // If there is an error, lets just pring the error
           System.err.println(e.getMessage());
       } finally {
           // Safety code to cleanup
           try {
               if (connection != null) {
                   connection.close();
               }
           } catch (SQLException e) {
               // connection close failed.
               System.err.println(e.getMessage());
           }
       }

      // ArrayList<Persona> personas;
   // Finally we return all of the countries
   return organicsgen;
}


public RWasteGeneration getwaste(String regional){

    // Create the ArrayList of LGA objects to return


       // Setup the variable for the JDBC connection
       Connection connection = null;

       try {
           // Connect to JDBC data base
           connection = DriverManager.getConnection(DATABASE);

           // Prepare a new SQL Query & Set a timeout
           Statement statement = connection.createStatement();
           statement.setQueryTimeout( 30);

           // The Query
           String query = "select collected,recycled,disposed from Total_Domestic_Waste_Generation;  ";

           // Get Result
           ResultSet results = statement.executeQuery(query);

           // Process all of the results
           while (results.next()) {
               // Lookup the columns we need




               String wastec = results.getString("collected");
               String recyclingc = results.getString("recycled");
               String disposedc = results.getString("disposed");

               wasteGen = new RWasteGeneration( recyclingc,wastec, disposedc);



           }

           // Close the statement because we are done with it
           statement.close();
       } catch (SQLException e) {
           // If there is an error, lets just pring the error
           System.err.println(e.getMessage());
       } finally {
           // Safety code to cleanup
           try {
               if (connection != null) {
                   connection.close();
               }
           } catch (SQLException e) {
               // connection close failed.
               System.err.println(e.getMessage());
           }
       }

      // ArrayList<Persona> personas;
   // Finally we return all of the countries
   return wasteGen;
}





}





























