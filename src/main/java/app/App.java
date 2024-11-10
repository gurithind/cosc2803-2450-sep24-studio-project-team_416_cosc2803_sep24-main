package app;

import java.util.ArrayList;
import java.util.Arrays;

import app.Java_classes.web2A;
import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;


/**
 * Main Application Class.
 * <p>
 * Running this class as regular java application will start the
 * Javalin HTTP Server and our web application.
 *
 *
 * WEBSITE LINK: ***********************************  localhost:7001  ***************************************
 *                                       stop and run the app.java to active your change
 *
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 * @author Halil Ali, 2024. email: halil.ali@rmit.edu.au
 */

public class App {

    public static final int         JAVALIN_PORT    = 7001;
    public static final String      CSS_DIR         = "css/";
    public static final String      IMAGES_DIR      = "images/";

    public static void main(String[] args) {
        // Create our HTTP server and listen in port 7001
        Javalin app = Javalin.create(config -> {
            config.registerPlugin(new RouteOverviewPlugin("/help/routes"));

            // Uncomment this if you have files in the CSS Directory
            config.addStaticFiles(CSS_DIR);

            // Uncomment this if you have files in the Images Directory
            config.addStaticFiles(IMAGES_DIR);
        }).start(JAVALIN_PORT);


        // Configure Web Routes
        configureRoutes(app);
    }

    public static void configureRoutes(Javalin app) {
        app.get(PageIndex.URL, new PageIndex());
        app.get(PageMission.URL, new PageMission());
        app.get(PageST2A.URL, new PageST2A());
        app.get(PageST2B.URL, new PageST2B());
        app.get(PageST3A.URL, new PageST3A());
        app.get(PageST3B.URL, new PageST3B());
        app.get(PageSummary.URL, new PageSummary());
        app.get(PageST2C.URL, new PageST2C());
        app.post(PageST2C.URL, new PageST2C());
        app.get(PageST3C.URL, new PageST3C());
        app.post(PageST3C.URL, new PageST3C());



        app.post(PageST2B.URL, new PageST2B());










//
        // the post for 2A, LGAs
        // can't  workign now, need fix. everywhere.



        app.get("/processValues", ctx -> {


            String order = ctx.queryParam("order") != null ? ctx.queryParam("order") : "";
            String orderData = ctx.queryParam("orderData") != null ? ctx.queryParam("orderData") : "";
            String time = ctx.queryParam("time") != null ? ctx.queryParam("time") : "";
            String type = ctx.queryParam("type") != null ? ctx.queryParam("type") : "";
            ArrayList<String> lgas = new ArrayList<>(Arrays.asList(
                ctx.queryParam("lgas") != null ? ctx.queryParam("lgas").split(",") : new String[0]
            ));
            ArrayList<String> subtypes = new ArrayList<>(Arrays.asList(
                ctx.queryParam("subtypes") != null ? ctx.queryParam("subtypes").split(",") : new String[0]
            ));

            JDBCConnection jdbc = new JDBCConnection();
            ArrayList<web2A> results = jdbc.get2Adata(order, time, type, lgas, subtypes,orderData);



            StringBuilder responseBuilder = new StringBuilder();



            if (results.isEmpty()) {
                ctx.result("No results found");
            } else {

                StringBuilder jsonBuilder = new StringBuilder("[");

                for (int i = 0; i < results.size(); i++) {
                    web2A lga = results.get(i);
                    jsonBuilder.append("{")
                        .append("\"name\":\"").append(lga.getName()).append("\",")
                        .append("\"population\":").append(lga.getPopulation()).append(",")
                        .append("\"house\":").append(lga.getHouse()).append(",")
                        .append("\"totalWasteCollected\":").append(lga.getTotalWasteColllected()).append(",")
                        .append("\"totalRecyclingWasteRecycled\":").append(lga.getTotalRecyclingWasteRecycled()).append(",")
                        .append("\"avgRecycled\":").append(lga.getAvgRecycled()).append(",")
                        .append("\"avgWastePerHouse\":").append(lga.getAvgWastePerHouse()).append("}");

                    if (i < results.size() - 1) {
                        jsonBuilder.append(",");
                    }
                }

                jsonBuilder.append("]");

                ctx.result(jsonBuilder.toString()); // Send JSON to the client

                // Print the final SQL query for debugging
                //testing, use to show the dql query to find problem.
                //String finalQuery = jdbc.getLastQuery();
                //System.out.println("Final SQL Query:\n" + finalQuery);

                
            }
        });



































        }



}
