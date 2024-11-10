package app;

//import java.util.ArrayList;

//import app.Java_classes.LGA;
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

public class PageMission implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/mission.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" + 
               "<title>Our Mission</title>";

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
                <h1  style="color: #0d0c0c;">Our Mission</h1>
            </div>

            
        """;  

        
       

        // Add Div for page Content
        html = html + "<div class='content'>";

        // Add HTML for the page content
        html = html + """
        <div class="mission-container">        
            <div class='our-mission'>                
               <h2>Our Mission</h2>                
               <p>Our objective at Recycle Smart is to empower stakeholders in household recycling and trash management in NSW, Australia, 
                by offering a complete online application with unified information and insights. Our goal is to improve awareness of trash 
                generation and recycling patterns,empowering residents and politicians to make more sustainable decisions. 
                We want to promote collaboration and push change in our communities by providing accessible statistics on waste products. 
                Our goal is to create a cleaner and greener environment for everyone.</p>        
            </div>
        </div>
""";

  






        // This example uses JDBC to lookup the countries
       /// JDBCConnection jdbc = new JDBCConnection();

        // Next we will ask this *class* for the Countries
        //ArrayList<LGA> lgas = jdbc.getAllCountries();



         

            html = html + """
            <div class="how-to-use-container">        
                <div class='how-to-use'>                
                    <h2>How to use</h2>                
                    <p>Navigate through the main menu to access information about recycling, waste management, 
                    and neighbouring drop-off locations.Navigate through the interactive charts and graphs to see current recycling and rubbish output trends.
                    In addition, you may run focused material searches to learn how to recycle them and locate local recycling events or programs. 
                    Our user-friendly design ensures that you can find the information you need to contribute to a more sustainable future.</p>        
                </div>
            </div>
            """;

        html = html + """
                    <h1 class='people'>People</h1>

            <div class="features">
        <div class="first">  
           <h2 Style="color:#0d0c0c ; text-align: center;">Emma Thompson</h2>
           <img src="Emma.png" >
           <p><strong>Attributes:</strong>She is full of Passion to her work, skilled at analyze data and communicate with others. Proficient with data tools and web applications.</p>
           <p><strong>Background:</strong>She is a data Analyst at the NSW Department of Environmental. Her role focuses on developing and evaluating policies related to waste management and recycling of NSW. Her job involves making data-driven decisions, presenting her research to stakeholders, and advising governments to make the best decision about recycling and waste reduction.
           She loves to protect the environment, believes in the importance of domestic recycling, and believes her job played an important role in waste management and recycling.
           </p>
           <p><strong>Goals and Experience:</strong>She needs a comprehensive view of waste production and recycling trends across LGAs in NSW. She wants to use this data to find the places with poor recycling programs and resources that should be reallocated.</p>
           <p><strong>Skills and Experience:</strong>Had a complete master's in environmental and management.Proficient in using web applications, databases, and research tools. Regularly uses government databases and analytics software.</p>
       </div>

       <div class="first">
         <h2 Style="color:#0d0c0c; text-align: center;">Omaya Fernandaz</h2>
         <img src="Omaya.png" >
         <p><strong>Attributes:</strong>She is 28 years old.She works as a Sustainability Consultant at Sydney, NSW.</p>
         <p><strong>Background:</strong>Omaya is a consultant at a sustainability consultancy company in NSW which is called Aspire Sustainability Consulting. In her role, she helps the government and several businesses to develop policies and recommend them, making data driven decisions. She got a vast number of experiences at a young age in waste management and recycling strategies through the projects she has done. Most of the time  She has to deal with municipal councils in NSW  day to day to track waste data and make policy by providing visualized research for them.
         </p>
        <p><strong>Goals and Experience:</strong>An application that has detailed waste and recycling data for multiple LGAs in NSW sorting by waste type, year, and also region. She does provide data-driven decisions and recommendations to improve the strategies for LGAs. She intends to compare waste disposal and recycling rates in NSW areas to identify high-performing and low-performing areas to improve them.</p>
        <p><strong>Skills and Experience:</strong>She has done a degree in the field of environmental science so she is well aware of the environmental challenges and solutions for them. She is quite interested in working with complex statistical software like SQL, Minitab, etc but the lack of knowledge and skills doesn’t back her. She has the ability to do public speaking well.</p>
       </div>

       <div class="first">
         <h2 Style="color:#0d0c0c; text-align: center;">John Smith</h2>
         <img src="John.png" >
         <p><strong>Attributes:</strong>John is a skilled environmental management professional with expertise in data analysis and sustainability initiatives, promoting eco-friendly practices and collaborating with local stakeholders to promote sustainability and community welfare.</p>
         <p><strong>Background:</strong>: John Smith is a 42-year-old Local Government Officer based in Newcastle, NSW. He holds a Bachelor’s Degree in Environmental Management and lives with his partner and teenage daughter. John is passionate about environmental sustainability and works with data analysis and community engagement as part of his role. He uses technology extensively, including a desktop for data analysis and mobile apps for community outreach.
         </p>
        <p><strong>Goals and Experience:</strong>John's main goals are to enhance waste management and recycling strategies within his community, ensure residents have easy access to relevant information, and collaborate with other departments to improve waste collection.</p>
        <p><strong>Skills and Experience:</strong>: John has strong skills in data analysis, which he uses for waste management. He is experienced in community outreach, educating residents about environmental topics, and is familiar with local government operations and policies related to sustainability.</p>
       </div>

     </div>



        """;
        







            
            
            


        // Finally we can print out all of the Countries
        //for (LGA lga : lgas) {
            //html = html + "<li>" + lga.getCode()
                        //+ " - " + lga.getName() + "</li>";
        //}

        // Finish the List HTML
        html = html + "</ul>";


        // Close Content div
        html = html + "</div>";

        

        // Finish the HTML webpage
        html = html + "</body>" + "</html>";
        

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}

