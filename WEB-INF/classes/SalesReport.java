import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.io.*;
import java.sql.*;

@WebServlet("/SalesReport")
public class SalesReport extends HttpServlet
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
        //displayInventory(request, response);
        Utilities utility = new Utilities(request, pw);

        try
        {
            if(!utility.isLoggedin())
			{
				HttpSession session = request.getSession(true);				
				session.setAttribute("login_msg", "Please Login to view Sales Report.");
				response.sendRedirect("Login");
				return;
            }
            
            HttpSession session=request.getSession(); 	
			utility.printHtml("Header.html");
            utility.printHtml("LeftNavigationBar.html");

            pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Sales Report</a>");
            pw.print("</h2><div class='entry'>");
            
            pw.print("<h4>Total Products sold and Sales</h4>");
            pw.print("<table class='gridtable gridtableFull gridDataTable' id='tblSalesReportProductsSold'>");
            pw.print("<thead><tr><td>Sr No</td>");
            pw.print("<td>Product Name</td>");
            pw.print("<td>Product Price</td>");
            pw.print("<td>Number of Products Sold</td>");
            pw.print("<td>Total Sales of Product (Price * No of Products sold)</td>");
            pw.print("</tr></thead><tbody>");

            //HashMap<String, ArrayList<NoOfAvailableProducts>> availableProductsList = new HashMap<String, ArrayList<NoOfAvailableProducts>>();
            ArrayList <NoOfProductsSold> totalSoldProductsList = new ArrayList <NoOfProductsSold> ();
            totalSoldProductsList = MySqlDataStoreUtilities.totalSoldProductsList();
            int i = 1;
            for(NoOfProductsSold product : totalSoldProductsList)
            {
                pw.print("<tr>");
                pw.print("<td>"+i+"</td>" +
                        "<td>"+product.getProductName()+"</td>" +
                        "<td>"+product.getProductPrice()+"</td>" +
                        "<td>"+product.getNoOfProductsSold()+"</td>" +
                        "<td>"+product.getProductTotalSales()+"</td>" +
                        "</tr>");
                i++;
            }

            pw.print("</tbody></table>");
            /***************** Bar chart ***************************/
            pw.print("<h4>Total Products sold and Sales (Graphical Representation) </h4>");
            //pw.print("<h3><button id='btnGetChartData'>View Chart</h3>");
            pw.println("<div id='chartDivTotalProductSales'></div>");
            /********************************************/
            
            pw.print("<h4>Total Daily Sales</h4>");
            pw.print("<table class='gridtable gridtableFull gridDataTable' id='tblSalesReportTotalDailySales'>");
            pw.print("<thead><tr><td>Sr No</td>");
            pw.print("<td>Date</td>");
            pw.print("<td>Product and Price List</td>");
            pw.print("<td>Total Sales</td>");
            pw.print("</tr></thead><tbody>");

            //HashMap<String, ArrayList<NoOfAvailableProducts>> availableProductsList = new HashMap<String, ArrayList<NoOfAvailableProducts>>();
            ArrayList <TotalSalesDaily> totalSalesDailyOrdersList = new ArrayList <TotalSalesDaily> ();
            totalSalesDailyOrdersList = MySqlDataStoreUtilities.totalSalesDailyOrdersList();
            i = 1;
            for(TotalSalesDaily order : totalSalesDailyOrdersList)
            {
                pw.print("<tr>");
                pw.print("<td>"+i+"</td><td>"+order.getOrderDate()+"</td><td>"+order.getProductsListDesciption()+"</td><td>"+order.getTotalDailySales()+"</td></tr>");
                i++;
            }

            pw.print("</tbody></table>");
            /********************************************/
            
            pw.print("</div></div></div>"); //</h2></div></div></div>
            pw.println("<script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>");
            pw.println("<script type='text/javascript' src='salesReport.js'></script>");
            utility.printHtml("Footer.html");
        }
        catch(Exception e)
        {

        }
    }
}