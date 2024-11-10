package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class PageST3C implements Handler {

    public static final String URL = "/page3C.html";

    @Override
    public void handle(Context context) throws Exception {
        String html = "<html>";
        html += "<head><title>Subtask 3.C</title>";
        html += "<link rel='stylesheet' type='text/css' href='common.css' />";
        html += "</head><body>";
        
        html += """
            <div class='topnav'>
                <a href='/'>Homepage</a>
                <a href='mission.html'>Our Mission</a>
                <a href='page2A.html'>LGA Statistics</a>
                <a href='page2B.html'>Regional Statistics</a>
                <a href='page2C.html'>Sub Task 2.C</a>
                <a href='page3A.html'>Sub Task 3.A</a>
                <a href='page3B.html'>Sub Task 3.B</a>
                <a href='page3C.html'>Sub Task 3.C</a>
                <a href='PageSummary.html'>Page Summary</a>
            </div>
        """;

        html += """
            <div class='header'>
                <h1>Advanced LGA Data Analysis</h1>
            </div>
        """;

        html += "<div class='content'>";
        html += generateLGADropdown();
        html += generatePeriodDropdown();

        html += """
            <button onclick="submitFilterForm()">Submit</button>
            <div id="resultsDisplay"></div>
        """;

        html += generateFilterScript();
        html += "</div>";

        html += """
            <div class='footer'>
                <p>COSC2803 - Studio Project Starter Code (Sep24)</p>
            </div>
        """;

        html += "</body></html>";
        
        context.html(html);
    }

    private String generateLGADropdown() {
        ArrayList<String> lgaNames = getLGAName();
        StringBuilder dropdown = new StringBuilder("<div class='dropdown'><input type='text' placeholder='Search LGA names' id='searchInput' onkeyup='filterOptions()'><div id='LGA' class='LGA'>");

        int num = 1;
        for (String name : lgaNames) {
            dropdown.append("<div><input type='checkbox' id='lga")
                    .append(num)
                    .append("' name='LGA' value='")
                    .append(name)
                    .append("'><label for='lga")
                    .append(num)
                    .append("'>")
                    .append(name)
                    .append("</label></div>");
            num++;
        }
        dropdown.append("</div></div>");
        return dropdown.toString();
    }

    private String generatePeriodDropdown() {
        return """
            <br><label for='Time_choices'>Choose a period:</label>
            <select id='Time_choices' name='Time_choices'>
                <option value=''>Select a period</option>
                <option value='2018-2019'>2018-2019</option>
                <option value='2019-2020'>2019-2020</option>
            </select><br>
        """;
    }

    private String generateFilterScript() {
        return """
            <script>
                async function submitFilterForm() {
                    const time = document.getElementById('Time_choices').value;
                    const lgas = Array.from(document.querySelectorAll('input[name="LGA"]:checked')).map(cb => cb.value);
                    const params = new URLSearchParams({
                        time: time,
                        lgas: lgas.join(',')
                    });
                    const response = await fetch(`/processValues?${params.toString()}`, { method: 'GET' });
                    const data = await response.text();
                    document.getElementById('resultsDisplay').innerHTML = data;
                }
            </script>
        """;
    }

    public ArrayList<String> getLGAName() {
        ArrayList<String> lgas = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(JDBCConnection.DATABASE);
             Statement statement = connection.createStatement()) {
            
            String query = "SELECT LGA_name FROM lga";
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                lgas.add(results.getString("LGA_name"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return lgas;
    }
}
