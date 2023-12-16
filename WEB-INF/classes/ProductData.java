
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


@WebServlet("/ProductData")
public class ProductData extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{

            PrintWriter pw= response.getWriter();
            response.setContentType("text/html");
            pw.println("<html>");
            pw.println("<body>");

            Utilities utility = new Utilities(request,pw);
            utility.printHtml("Header.html");
            utility.printHtml("LeftNavigationBar.html");
            pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
            pw.print("</h2><div class='entry'><table id='bestseller'>");
            pw.print("<tr>");
            pw.print("<td><div id='shop_item'>");
            Product data= (Product)request.getAttribute("data");

            pw.print("<h3>"+data.getName()+"</h3>");
            pw.print("<h4>" + data.getDescription() + "</h5>");
            pw.print("<strong>$"+data.getPrice()+"</strong>");
            pw.print("<h4>Discount: $" + data.getDiscount() + "</h4><ul>");
            pw.print("<h4 style='text-align: center;'>"+ data.getRebate() +"%</h4>");

            pw.print("<li id='item'><img src='images/"+getImagePath(data.getType())+"/"+data.getImage()+"' alt='' /></li>");
            pw.print("<li><form method='post' action='Cart' style='text-align: center;'>" +
                    "<input type='hidden' name='name' value='"+data.getId()+"'>"+
                    "<input type='hidden' name='type' value='"+data.getType()+"'>"+
                    "<input type='hidden' name='maker' value='"+data.getRetailer()+"'>"+
                    "<input type='hidden' name='price' value='"+data.getPrice()+"'>"+
                    "<input type='hidden' name='access' value=''>"+
                    warrantyCheckbox(data) +
                    "<input type='submit' class='btnbuy' value='Buy Now'></form></li>");

            pw.print("<div style='display:flex; justify-content:space-evenly'><li><form method='post' action='WriteReview'>"+
                    "<input type='hidden' name='type' value='" + data.getType() + "'>"+
                    "<input type='hidden' name='name' value='" + data.getName() +"'>"+
                    "<input type='hidden' name='maker' value='"+data.getRetailer()+"'>"+
                    "<input type='hidden' name='price' value='"+data.getPrice()+"'>"+
                    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
            pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='" + data.getName() + "'>"+
                    "<input type='hidden' name='type' value='" + data.getType() + "'>"+
                    "<input type='hidden' name='maker' value='"+data.getRetailer()+"'>"+
                    "<input type='hidden' name='access' value=''>"+
                    "<input type='submit' value='ViewReview' class='btnreview'></form></li></div>");

            pw.print("</ul></div></td>");
            pw.print("</tr>");
            pw.print("</table></div></div></div>");
            utility.printHtml("Footer.html");
        }
        catch(Exception e)
        {

        }
    }

    public void destroy()	{
        // do nothing.
    }

    private String getImagePath(String productType) {
        switch (productType) {
            case "Phone":
                return "phones";
            case "Laptop":
                return "laptops";
            case "Voice Assistant":
                return "assistant";
            case "Smart Watch":
            case "Fitness Watch":
            case "Headphone":
            case "Virtual Reality":
            case "PetTracker":
                return "wearables";
            default:
                return "";
        }
    }

    private String warrantyCheckbox(Product product) {
        return product.isHasWarranty()
                ? "<input type='checkbox' name='productWarranty' value='yes'><label> Life Time Warranty: $" + product.getWarrantyPrice() + "</label>"
                : "<p>Warranty not available</p>";
    }
}