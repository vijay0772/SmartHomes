import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/DeleteFromCart")
public class DeleteFromCart extends HttpServlet
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request, pw);
        
        double orderPrice = 0;
		try
		{
			orderPrice = Double.parseDouble(request.getParameter("price"));
		}
		catch(Exception e)
		{
			
		}
        String orderName = request.getParameter("name");
        
        if(orderPrice != 0 && orderName != null)
		{
			ArrayList<OrderItem> order = new ArrayList<OrderItem>();
			order = utility.getCustomerOrders();
			for (OrderItem oi : utility.getCustomerOrders())
			{
				if(oi.getName().equalsIgnoreCase(orderName) && oi.getPrice() == orderPrice)
				{
					//&& oi.getPrice().equals(orderPrice)
					//Delete item from Hashmap
                    order.remove(oi);
                    utility.printHtml("Header.html");
                    utility.printHtml("LeftNavigationBar.html");
                    pw.print("<div id='content'><div class='post'><h2 class='title meta'><a style='font-size: 24px;'>Order / Cart Status</a></h2><div class='entry'>");
                    pw.print("<h4 style='color:red'>Order for "+orderName+" having price $"+orderPrice+", removed from cart successfully!</h4>");
                    pw.print("</div></div><div style='clear: both;''>&nbsp;</div></div>");
                    utility.printHtml("Footer.html");
				}
			}
		}
	}
}