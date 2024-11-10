package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class PageST2A implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page2A.html";

    @Override
    public void handle(Context context) throws Exception {
        String html = "<html>";
        html = html + "<head>" + 
               "<title>Subtask 2.A</title>";

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
                <a href='page2C.html'>Sub Task 2.C</a>
                <a href='page3A.html'>Sub Task 3.A</a>
                <a href='page3B.html'>Sub Task 3.B</a>
                <a href='page3C.html'>Sub Task 3.C</a>
                <a href='PageSummary.html'>PageSummary</a>
            </div>
        """;

        // Add header content block
        html = html + """
            <div class='header'>
                <h1>Dive Into LGA Statics</h1>
            </div>
        """;

        // Add Div for page Content
        html = html + "<div class='content'>";

        // LGA name search bar UI
        html = html + """
        
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Choose LGA Name</title>
    <style>
        .dropdown {
            position: relative;
            display: inline-block;
            width: 200px;
        }
        .dropdown input {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }
        .LGA {
            display: none;
            position: absolute;
            background-color: #f9f9f9;
            border: 1px solid #ccc;
            width: 100%;
            max-height: 300px;
            overflow-y: auto;
            z-index: 1;
        }
        .LGA div {
            padding: 8px;
            cursor: pointer;
        }
        .LGA div:hover {
            background-color: #ddd;
        }
        .LGA input[type="checkbox"] {
            margin-right: 8px;
        }
    </style>
</head>
<body>
<div class="dropdown">
    <input type="text" placeholder="Search LGA names" id="searchInput" onkeyup="filterOptions()">

    <div id="LGA" class="LGA">
    """;
        // use method to fillin optons with all LGA names 
        ArrayList<String> lgaNames = getLGAName();
        int num=1;
        for (String name : lgaNames) {
            html = html + "<div> <input type=\"checkbox\" id=\"lga" + num + "\" name=\"LGA\" value=\"" + name + "\">\r\n" + 
                                 "            <label for=\"lga" + num + "\">" + name + "</label>\r\n" + 
                                 "        </div>";
            num++;
        }        
        html = html + """
</div>

<script>
    document.getElementById("searchInput").addEventListener("click", function() {
        document.getElementById("LGA").style.display = "block";
    });

    const LGAs = Array.from(document.querySelectorAll('input[name="LGA"]:checked')).map(cb => cb.value);

    function filterOptions() {
        const input = document.getElementById("searchInput").value.toLowerCase();
        const options = document.getElementById("LGA").getElementsByTagName("div");
        for (let i = 0; i < options.length; i++) {
            const option = options[i];
            const label = option.querySelector('label').innerHTML;
            if (label.toLowerCase().includes(input)) {
                option.style.display = "";
            } else {
                option.style.display = "none";
            }
        }
    }

    document.getElementById("LGA").addEventListener("click", function(event) {
        if (event.target.tagName === "LABEL") {
            const checkbox = event.target.previousElementSibling;
            checkbox.checked = !checkbox.checked;
        }
    });

    document.addEventListener("click", function(event) {
        const dropdown = document.querySelector(".dropdown");
        if (!dropdown.contains(event.target)) {
            document.getElementById("LGA").style.display = "none";
        }
    });
</script>
""";
        // time choose
        html = html + """
        <br><br>
        <label for="choices">Choose a period:</label>
        <select id="Time_choices" name="Time_choices" onchange="captureSelection()">
        <option value="">Choose an period</option>
        <option value="2018-2019">2018-2019</option>
        <option value="2019-2020">2019-2020</option>
        </select>
        """;

        // type choose
        html = html + """
        <br><br>
        
        <label for="Type_choices">Choose a type:</label>
        <select id="Type_choices" name="Type_choices" onchange="updateCheckboxes()">
        <option value="">Choose a type</option>
        <option value="Recycling">Recycling</option>
        <option value="Organics">Organics</option>
        <option value="Waste">Waste</option>
        </select>
        """;

        // subtype choose
        html = html + """
        <br><br>
        <div id="Subtype_choices">
        <p>Choose a type and subtype will showing out:</p>
        </div>
        
        <script>
        const options = {
            "Recycling": ["Kerbside Recycling Bin", "CDS Recycling", "Drop off Recycling", "Cleanup Recycling"],
            "Organics": ["Kerbside Organics Bin", "Kerbside FOGO Organics", "Drop off Organics", "Cleanup Organics", "Other Council Garden Organics"],
            "Waste": ["Kerbside Waste Bin", "Drop Off", "Clean Up"]
        };
        
        function updateCheckboxes() {
    const typeSelection = document.getElementById("Type_choices").value;
    const subtypeContainer = document.getElementById("Subtype_choices");

    subtypeContainer.innerHTML = '<p>Choose a subtype:</p>';

    if (options[typeSelection]) {
        options[typeSelection].forEach(subtype => {
            const checkbox = document.createElement("input");
            checkbox.type = "checkbox";
            checkbox.id = subtype; 
            checkbox.name = "Subtype_choices"; // Add this line
            checkbox.value = subtype;

            const label = document.createElement("label");
            label.htmlFor = subtype; 
            label.textContent = subtype; 

            subtypeContainer.appendChild(checkbox);
            subtypeContainer.appendChild(label);
            subtypeContainer.appendChild(document.createElement("br")); 
        });
    }
}

    </script>
    """;
    // order choose
    html = html + """
        <br><br>
        <label for="choices">Choose a order:</label>
        <select id="Order_choices" name="Order_choices" onchange="captureSelection()">
        <option value="ASC">Ascend</option>
        <option value="DESC">Descend</option>
        </select>
        """;
        html = html + """
        <br><br>
        <label for="choices">order data:</label>
        <select id="OrderData_choices" name="OrderData_choices" onchange="captureSelection()">
        <option value="name">LGA</option>
        <option value="population">population</option>
        <option value="house">houses surveyed</option>
        <option value="total_collected">total waste collected</option>
        <option value="total_recycled">total waste recycled</option>
        <option value="AvgRecycled">average percentage recycled</option>
        <option value="AvgWastePerHouse">average waste per household</option>
        </select>
        """;

        /*
         "LGA.LGA_name AS , " +
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
         "END AS AvgWastePerHouse " + */
        // need to add a order target data.@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

// here below is the code for submit, they will get the data from selection and ask a post for run sql with the selected value.
// running sql need fix in JDBCC
// store the value of order,time,subtype,LGA and type
        html = html + """
        <button onclick="submitFilterForm()">Submit</button>
<div id="selectedValuesDisplay"></div>
<div id="resultsDisplay"></div> <!-- Ensure there is a div to display results -->

<script>
async function submitFilterForm() {
    const Order = document.getElementById('Order_choices').value;
    const OrderData = document.getElementById('OrderData_choices').value;
    const Time = document.getElementById('Time_choices').value;
    const Type = document.getElementById('Type_choices').value;
    const LGAs = Array.from(document.querySelectorAll('input[name="LGA"]:checked')).map(cb => cb.value);
    const SubTypes = Array.from(document.querySelectorAll('input[name="Subtype_choices"]:checked')).map(cb => cb.value);


    const params = new URLSearchParams({
        order: Order,
        orderData: OrderData,
        time: Time,
        type: Type,
        lgas: LGAs.join(','), 
        subtypes: SubTypes.join(',') 
    });

    try {
        // Make a GET request with query parameters
        const response = await fetch(`/processValues?${params.toString()}`, {
            method: 'GET', // Use GET for this request
        });

        // Get the response text and display it
        const data = await response.text();
        displayResults(data); // Call the function to display results
    } catch (error) {
        console.error('Error:', error);
    }
}

function displayResults(results) {
    const resultsDisplay = document.getElementById('resultsDisplay');
    resultsDisplay.innerHTML = ''; // Clear previous results

    // Display the results returned from the server
    const resultItem = document.createElement('div');
    resultItem.textContent = results; // Display the results directly
    resultsDisplay.appendChild(resultItem);
}
</script>







        """;
        



        
        
        
        
        

            
/*function I need:
 * 1 
 * 2  
 * 3 
 * 4 @@@ Store the choosed value, use them for chart
 * 5 choose sort order and the selected value used for order.
 * 6
 * 7 a chart will generate after the submit.
 * 
 * 
 * done:
 * time choose, 1 in 2
 * choose from waste types
 * search bar and choose LGA names
 * multiple choose from sub types, change depend on waste type
 *  a submit button 
 * 
*/


































































        // Close Content div
        html = html + "</div>";

        // Footer
        html = html + """
            <div class='footer'>
                <p>COSC2803 - Studio Project Starter Code (Sep24)</p>
            </div>
        """;

        // Finish the HTML webpage
        html = html + "</body>" + "</html>";
        

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }



























    public ArrayList<String> getLGAName() {
        ArrayList<String> lgas = new ArrayList<String>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(JDBCConnection.DATABASE);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String query = "SELECT * FROM lga";
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                String lgaName  = results.getString("LGA_name");
                lgas.add(lgaName);
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
        return lgas;
    }





    
}
