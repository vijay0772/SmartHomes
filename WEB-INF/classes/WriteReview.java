import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/WriteReview")
	//once the user clicks writereview button from products page he will be directed
 	//to write review page where he can provide reqview for item rating reviewtext	
	
public class WriteReview extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {	
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
        Utilities utility= new Utilities(request, pw);
		review(request, response);
	}
	
    protected void review(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
                   
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            Utilities utility = new Utilities(request,pw);
            if(!utility.isLoggedin())
            {
                HttpSession session = request.getSession(true);				
                session.setAttribute("login_msg", "Please Login to Write a Review");
                response.sendRedirect("Login");
                return;
		    }
            String productname=request.getParameter("name");		
            String producttype=request.getParameter("type");
            String productmaker=request.getParameter("maker");
            String productprice=request.getParameter("price");

            List<Store> allStores = utility.getAllStores();
            LocalDate localDate = LocalDate.now();
            Date date = java.sql.Date.valueOf(localDate);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String today = dateFormat.format(date);
			      
            // on filling the form and clicking submit button user will be directed to submit review page
            utility.printHtml("Header.html");
            utility.printHtml("LeftNavigationBar.html");
            pw.print("<form name ='WriteReview' action='SubmitReview' method='post'>");
            pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
            pw.print("<a style='font-size: 24px;'>Review</a>");
            pw.print("</h2><div class='entry'>");
            pw.print("<table class='gridtable'>");
            pw.print("<tr><td> Product Model Name: </td><td>");
            pw.print(productname);
            pw.print("<input type='hidden' name='productModelName' value='"+productname+"'>");
            pw.print("</td></tr>");
            pw.print("<tr><td> Product Category:</td><td>");
            pw.print(producttype);
            pw.print("<input type='hidden' name='productCategory' value='"+producttype+"'>");
            pw.print("</td></tr>");
            pw.print("<tr><td> Product Price:</td><td>");
            pw.print(productprice);
            pw.print("<input type='hidden' name='productPrice' value='"+productprice+"'>");
            pw.print("</td></tr>");		
            pw.print("<tr><td> Manufacturer Name: </td><td>");
            pw.print(productmaker);
            pw.print("<input type='hidden' name='manufacturerName' value='"+productmaker+"'>");
            pw.print("</td></tr>");
            pw.print("<tr><td> User Name: </td><td>");
            pw.print(utility.getUser().getName());
            pw.print("<input type='hidden' name='userId' value='"+utility.getUser().getName()+"'>");
            pw.print("</td></tr></table>");

            pw.print("<table><tr></tr><tr></tr><tr><td> Review Rating: </td>");
            pw.print("<td>");
            pw.print("<select name='reviewRating'>");
            pw.print("<option value='1' selected>1</option>");
            pw.print("<option value='2'>2</option>");
            pw.print("<option value='3'>3</option>");
            pw.print("<option value='4'>4</option>");
            pw.print("<option value='5'>5</option>");  
            pw.print("</td></tr>");

            pw.print("<tr>");
            pw.print("<td> Store: </td>");
            pw.print("<td>");
            pw.print("<select name='storeId' >");
            for (Store store: allStores) {
                pw.print("<option value='" + store.getStoreId() + "'>" + store.getStoreId() + "</option>");
            }
            pw.print("</select>");
            pw.print("</td></tr>");

            pw.print("<tr>");
            pw.print("<td> Store City: </td>");
            pw.print("<td> <input type='text' name='storeCity'> </td>");
            pw.print("</tr>");

            pw.print("<tr>");
            pw.print("<td> Store State: </td>");
            pw.print("<td> <input type='text' name='storeState'> </td>");
            pw.print("</tr>");

            pw.print("<tr>");
            pw.print("<td> Store Zip Code: </td>");
            pw.print("<td> <input type='number' name='storeZip'> </td>");
            pw.print("</tr>");

            pw.print("<tr><td> Product on Sale: </td><td>");
            pw.print("<select name='productOnSale'>");
            pw.print("<option value='Yes' selected>Yes</option>");
            pw.print("<option value='No'>No</option>");
            pw.print("</td></tr>");

            pw.print("<tr><td> Manufacturer Rebate: </td><td>");
            pw.print("<select name='manufacturerRebate'>");
            pw.print("<option value='Yes' selected>Yes</option>");
            pw.print("<option value='No'>No</option>");
            pw.print("</td></tr>");

            pw.print("<tr>");
            pw.print("<td> User Age: </td>");
            pw.print("<td> <input type='text' name='userAge'> </td>");
            pw.print("</tr>");

            pw.print("<tr><td> User Gender: </td><td>");
            pw.print("<select name='userGender'>");
            pw.print("<option value='Male' selected>Male</option>");
            pw.print("<option value='Female'>Female</option>");
            pw.print("<option value='Other'>Other</option>"); 
            pw.print("</td></tr>");

            pw.print("<tr>");
            pw.print("<td> User Occupation: </td>");
            pw.print("<td> <input type='text' name='userOccupation'> </td>");
            pw.print("</tr>");

            pw.print("<tr>");
            pw.print("<td> Review Date: </td>");
            pw.print("<td> <input type='date' min='2000-01-01' max='"+ today + "' value='"+ today +"' name='reviewDate'> </td>");
            pw.print("</tr>");				

            pw.print("<tr>");
            pw.print("<td> Review Text: </td>");
            pw.print("<td><textarea name='reviewText' rows='4' cols='50'> </textarea></td></tr>");
            pw.print("<tr><td colspan='2'><input type='submit' class='btnbuy' name='SubmitReview' value='Submit Review'></td></tr></table>");

            pw.print("</h2></div></div></div>");		
            utility.printHtml("Footer.html");         	
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
		}  			 	
	}
}
