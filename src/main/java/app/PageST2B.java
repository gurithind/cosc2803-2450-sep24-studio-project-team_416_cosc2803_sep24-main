package app;

import java.util.ArrayList;
import java.util.List;

import app.Java_classes.ROrganicGeneration;
import app.Java_classes.RWasteGeneration;
import app.Java_classes.RecyclingGeneration;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

public class PageST2B implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page2B.html";
    JDBCConnection jdbc = new JDBCConnection();
    RecyclingGeneration recycling = null;
    ROrganicGeneration organic = null;
    RWasteGeneration waste = null;
    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" +
               "<title>Subtask 2.B</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
        html = html + "</head>";

       html = html + "<form action = '/page2B.html' method= 'post'>";
       String regionselect = context.formParam("regionselect");
       String wasteselect = context.formParam("wasteselect");
       String statselect = context.formParam("statselect");


        // Add the body
        html = html + """
            <div class='topnav'>
                <a href='/'>Homepage</a>
                <a href='mission.html'>Our Mission</a>
                <a href='page2A.html'>LGA Statistics</a>
                <a href='page2B.html'>Regional Statistics</a>
                <a href='page2C.html'>Sub Task 2.C</a>
                <a href='page3A.html'>Sub Task 3.A</a>
                <a href='page3B.html'>Sub Task 3.B</a>
                <a href='page3C.html'>Sub Task 3.C</a>
                <a href='PageSummary.html'>PageSummary</a>
            </div>
        """;













        // Add header content block
        html = html +
        """
            <div class='header'>
                <h1 style="color:#0d0c0c;">Dive into Regional Statistics</h1>
                <p style="color: #0d0c0c;">This section provides a comprehensive analysis of domestic waste management and recycling trends in New South Wales, focusing on various regional groups like the Sydney Metropolitan Area, Extended Regulated Area, and Regional Regulated Area. It allows you to select specific waste types and set customizable thresholds, allowing for the analysis of key waste metrics like collected, recycled, and disposed amounts. The tool also provides a data-driven view of each region's contribution to waste reduction efforts over time.
                </p>



            </div>


<div class="container">
<div class="dropdown-box">
    <label for="region-select">Choose a Region:</label>
    <select id="region-select" name = "regionselect">
        <option value="SMA">SMA</option>
        <option value="ERA">ERA</option>
        <option value="RRA">RRA</option>
        <option value="Rest of NSW">Rest of NSW</option>
    </select>
</div>

<div class="dropdown-box">
    <label for="waste-select">Choose a waste type:</label>
    <select id="waste-select" name = "wasteselect">
        <option value="Recyclable">Recyclable</option>
        <option value="Organic">Organic</option>
        <option value="Waste">Waste</option>
    </select>
</div>

<div class="dropdown-box">
    <label for="stat-select">Choose a waste Statistic:</label>
    <select id="stat-select" name = "statselect">
        <option value="Collected">Collected</option>
        <option value="Recycled">Recycled</option>
        <option value="Disposed">Disposed</option>
    </select>
</div>
</div>

<div class="dropdown-box">
     <label for="Order-By">Filter Values:</label>
     <select id="Order-By" name=orderby">
       <option value="Ascending">Ascending</option>
       <option value="Descending">Descending</option>
    </select>
</div>

<div class="dropdown-box">
     <label for="Threshold">Select Threshold:</label>
     <select id="Threshold" name=Threshold">
       <option value="500">500</option>
       <option value="1000">1000</option>
       <option value="5000">5000</option>
       <option value="10000">10000</option>
       <option value="50000">50000</option>
    </select>
</div>


<button style= "color:black;font-weight:bold;" onclick="getSelectedOptions()">Search</button>
</form>


        """;


if (regionselect == null || wasteselect== null || statselect == null) {
    // If NULL or empty, no selections were made
    html = html + "<h2><i>No Results to show for checkboxes</i></h2>";
}else{

html = html + """
<script>
function getSelectedOptions() {
    const region = document.getElementById("region-select").value;
    const wasteType = document.getElementById("waste-select").value;
    const statistics = document.getElementById("stat-select").value;

    // Update table cells with selected values
    document.getElementById("selected-region").textContent = region;
    document.getElementById("selected-waste-type").textContent = wasteType;
    document.getElementById("selected-statistic").textContent = statistics;
}
</script>


<div class = "table">
<h2>Statistics for Selected Regional Group</h2>

<table style=" margin-top: 2rem;
  background-color: white;
  padding: 1rem;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);"
>
    <tr>
        <th style="background-color: #f2f2f2;font-weight: bold;padding: 0.80rem; text-align: left; border-bottom: 1px solid #ddd">Regional Group</th>
        <th style="background-color: #f2f2f2;font-weight: bold;padding: 0.80rem; text-align: left; border-bottom: 1px solid #ddd">Waste Type</th>
        <th style="background-color: #f2f2f2;font-weight: bold;padding: 0.80rem; text-align: left; border-bottom: 1px solid #ddd">Statistic Type</th>
        <th style="background-color: #f2f2f2;font-weight: bold;padding: 0.80rem; text-align: left; border-bottom: 1px solid #ddd">Total Waste Collected</th>
        <th style="background-color: #f2f2f2;font-weight: bold;padding: 0.80rem; text-align: left; border-bottom: 1px solid #ddd">Total Waste Recycled</th>
        <th style="background-color: #f2f2f2;font-weight: bold;padding: 0.80rem; text-align: left; border-bottom: 1px solid #ddd">Total Waste Disposed</th>
        <th style="background-color: #f2f2f2;font-weight: bold;padding: 0.80rem; text-align: left; border-bottom: 1px solid #ddd">Annual Time Period</th>
    </tr>
    <tr>


        <td style="padding: 0.80rem; text-align: left; border-bottom: 1px solid #ddd" id="selected-region">""";

        html = html + regionselect;

        html = html + """
        </td>
        <td padding: 0.80rem; text-align: left; border-bottom: 1px solid #ddd" id="selected-waste-type">""";
        html=html+wasteselect;

        html=html+"""


        </td>

        <td padding: 0.80rem; text-align: left; border-bottom: 1px solid #ddd" id="selected-statistic">""";
        html=html+statselect;
        html=html+"""



        </td>
        <td>

                """;

if(wasteselect.equals("Recyclable")){

    // ro = jdbc.getOrganic(
    recycling = jdbc.getRecycled(regionselect);
    html = html + recycling.getCollected();
    html = html +
    """

        </td>
        <td>

                """;

                html = html + recycling.getRecycled();

    html = html +    """
    </td>
        <td>


                """;
        html = html + recycling.getDisposed();

        html = html + """


        </td>
        <td>
            """;
            html = html + recycling.getYear();

            html = html + """


            </td>

                """;
}else if(wasteselect.equals("Organic")){

    // ro = jdbc.getOrganic(
    organic = jdbc.getOrganic(regionselect);
    html = html + organic.getcollected();

    html = html +
    """

        </td>
        <td>

                """;

                html = html + organic.getrecycled();

    html = html +    """
    </td>
        <td>


                """;
        html = html + organic.getdisposed();

        html = html + """


        </td>
        <td>
            """;

            html = html + organic.getYear();

        html = html + """


        </td>

            """;



}else if(wasteselect.equals("Waste")){

    // ro = jdbc.getOrganic(
    waste = jdbc.getwaste(regionselect);
    html = html + waste.getcollected();

    html = html +
    """

        </td>
        <td>

                """;

                html = html + waste.getrecycled();

    html = html +    """
    </td>
        <td>


                """;
        html = html + waste.getdisposed();

        html = html + """


        </td>
        <td>
            """;

            html = html + waste.getYear();

        html = html + """


        </td>

            """;



}

   html = html + """


    </tr>
</table>

   </div>
                  """;
}


        html = html + "</body>" + "</html>";


        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}
