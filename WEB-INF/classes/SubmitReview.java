import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SubmitReview")

public class SubmitReview extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {	
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Utilities utility= new Utilities(request, pw);
        storeReview(request, response);
    }
    
    protected void storeReview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            Utilities utility = new Utilities(request,pw);
            if(!utility.isLoggedin())
            {
                HttpSession session = request.getSession(true);				
                session.setAttribute("login_msg", "Please Login to add items to cart");
                response.sendRedirect("Login");
                return;
            }
            String productname=request.getParameter("productname");		
            String producttype=request.getParameter("producttype");
            String productprice=request.getParameter("productprice");
            String productmaker=request.getParameter("productmaker");
            String reviewrating=request.getParameter("reviewrating");
            String reviewdate=request.getParameter("reviewdate");
            String reviewtext=request.getParameter("reviewtext");
            String retailerpin=request.getParameter("zipcode");
            String retailercity = request.getParameter("retailercity");
            String reviewerAge = request.getParameter("reviewerAge");
            String reviewerGender = request.getParameter("reviewerGender");
            String reviewerOccupation = request.getParameter("reviewerOccupation");

            Review review = new Review(
                    request.getParameter("productModelName"),
                    request.getParameter("productCategory"),
                    Double.parseDouble(request.getParameter("productPrice")),
                    request.getParameter("storeId"),
                    request.getParameter("storeCity"),
                    request.getParameter("storeState"),
                    request.getParameter("storeZip"),
                    request.getParameter("productOnSale"),
                    request.getParameter("manufacturerName"),
                    request.getParameter("manufacturerRebate"),
                    request.getParameter("userId"),
                    Integer.parseInt(request.getParameter("userAge")),
                    request.getParameter("userGender"),
                    request.getParameter("userOccupation"),
                    Integer.parseInt(request.getParameter("reviewRating")),
                    request.getParameter("reviewDate"),
                    request.getParameter("reviewText")
            );


            String message=utility.storeReview(review);

            utility.printHtml("Header.html");
            utility.printHtml("LeftNavigationBar.html");
            pw.print("<form name ='Cart' action='CheckOut' method='post'>");
            pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
            pw.print("<a style='font-size: 24px;'>Review</a>");
            pw.print("</h2><div class='entry'>");
            if(message.equals("Successfull"))
                pw.print("<h2>Review for &nbsp"+ review.getProductModelName() +" Stored </h2>");
            else
                pw.print("<h2>Mongo Db is not up and running </h2>");

            pw.print("</div></div></div>");		
            utility.printHtml("Footer.html");
        }
        catch(Exception e)
		{
            System.out.println(e.getMessage());
		}  				
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {	
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();	
    }
}