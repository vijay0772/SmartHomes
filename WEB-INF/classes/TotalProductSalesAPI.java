import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mongodb.DBCollection;


@WebServlet("/TotalProductSalesAPI")
public class TotalProductSalesAPI extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        try
        {
            ArrayList <NoOfProductsSold> totalSoldProductsList = new ArrayList <NoOfProductsSold> ();
            totalSoldProductsList = MySqlDataStoreUtilities.totalSoldProductsList();
            //ArrayList<Review> topReviewsPerCity = getTop3InEveryCity(reviews);

            String reviewJson = new Gson().toJson(totalSoldProductsList);

            response.setContentType("application/JSON");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(reviewJson);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}