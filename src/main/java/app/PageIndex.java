package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import io.javalin.http.Context;
import io.javalin.http.Handler;


/**
 * Example Index HTML class using Javalin
 * <p>
 * Generate a static HTML page using Javalin
 * by writing the raw HTML into a Java String object
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 * @author Halil Ali, 2024. email: halil.ali@rmit.edu.au
 */

public class PageIndex implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Header information
        html = html + "<head>" + 
               "<title>Homepage</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
        html = html + "</head>";

        // Add the body
        html = html + "<body>";

        // Add the topnav
        // This uses a Java v15+ Text Block
        html = html + """
            <div class='topnav'>
                <a href='/'>Homepage</a>
                <a href='mission.html'>Our Mission</a>
                <a href='page2A.html'>LGA Statistics</a>
                <a href='page2B.html'>Regional Statistics</a>
                <a href='page2B.html'>Sub Task 2.C</a>
                <a href='page3A.html'>Sub Task 3.A</a>
                <a href='page3B.html'>Sub Task 3.B</a>
                <a href='page2B.html'>Sub Task 3.C</a>
                <a href='page2B.html'>PageSummary</a>
            </div>
        """;

        // Add header content block
        html = html + """
            <div class='header'>
                <h1>
                    <strong>SmartRecycle</strong>
                </h1>
            </div>
        """;

        // Add Div for page Content
        html = html + "<div class='content'>";

        // Add HTML for the page content
        html = html + """
  <div class="content">
    <h1>Discover Domestic Waste Management Across LGAs on NSW</h1>

    <div class="intro">
      <div class="text-box">This platform offers insightful data on waste management and recycling, focusing on key statistics across Local Government Areas (LGAs). Whether you're here to explore trends or gather data, our website provides everything you need at a glance.</div>
      <div class="large-box"></div>
    </div>

    <div class="report">
      <div class="report-box">
        <h3>Bref report</h3>
        <h3>2018-2019</h3>
        <p>LGAs: 
        """;
        int lga = getLGACount("2018-2019");
        html = html + String.valueOf(lga) +"""
        </p>
        <p>houses surveyed:  
        """;
        int house18 = getHouseCount("2018-2019");
        html = html + String.valueOf(house18) +"""
        </p>
        
      </div>


      <div class="report-box">
        <h3>2019-2020</h3>
        <p>LGAs: 
        """;
        lga = getLGACount("2019-2020");
        html = html + String.valueOf(lga) +"""
        </p>
        <p>houses surveyed:  
        """;
        int house19 = getHouseCount("2019-2020");
        html = html + String.valueOf(house19) +"""
        </p>
        
        <p> The number of surveyed house had  """;
        double change= (double)house19/house18;
        html = html + " "+String.format("%.2f", change) +"""
        % increase</p>

      </div>
    </div>
            """;

        

        // Close Content div
        html = html + "</div>";

       

        // Finish the HTML webpage
        html = html + "</body>" + "</html>";


        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }





     




    public int getLGACount(String year) {
        int count=-1;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(JDBCConnection.DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "SELECT COUNT(LGA_code) AS lgaCount FROM LGA_Service_Information WHERE year = '" + year + "';";
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                count = results.getInt("lgaCount");
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
        return count;
    }
    public int getHouseCount(String year) {
        int count=-1;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(JDBCConnection.DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "SELECT SUM(household_survey) AS houseCount FROM LGA_Service_Information WHERE year = '" + year + "';";
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                count = results.getInt("houseCount");
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
        return count;
    }
}
