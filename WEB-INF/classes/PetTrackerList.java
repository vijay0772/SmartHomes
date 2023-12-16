import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/PetTrackerList")
public class PetTrackerList extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        String name = null;
        String CategoryName = request.getParameter("maker");

        HashMap<String, PetTracker> allPetTrackers = new HashMap<>();
        HashMap<String, PetTracker> hm = new HashMap<>();

        try
        {
            allPetTrackers = MySqlDataStoreUtilities.getPetTracker();
        }
        catch(Exception e)
        {

        }

        if(CategoryName==null)
        {
            //hm.putAll(SaxParserDataStore.phones);
            hm.putAll(allPetTrackers);
            name = "";
        }
        else
        {
            if(CategoryName.equals("apple"))
            {
                //for(Map.Entry<String, PetTracker> entry : SaxParserDataStore.phones.entrySet())
                for(Map.Entry<String, PetTracker> entry : allPetTrackers.entrySet())
                {
                    if(entry.getValue().getRetailer().equals("Apple"))
                    {
                        hm.put(entry.getValue().getId(),entry.getValue());
                    }
                }
                name = "Apple";
            }
        }

		/* Header, Left Navigation Bar are Printed.

		All the Phone and Phone information are dispalyed in the Content Section

		and then Footer is Printed*/

        Utilities utility = new Utilities(request,pw);
        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");
        pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
        pw.print("<a style='font-size: 24px;'>"+name+" Pet Tracker</a>");
        pw.print("</h2><div class='entry'><table id='bestseller'>");
        int i = 1; int size= hm.size();
        System.out.println("Size: " + size);
        for(Map.Entry<String, PetTracker> entry : hm.entrySet())
        {
            PetTracker petTracker = entry.getValue();
            if(i%3==1) pw.print("<tr>");
            pw.print("<td><div id='shop_item'>");
            pw.print("<h3>"+petTracker.getName()+"</h3>");
            pw.print("<h4>" + petTracker.getDescription() + "</h5>");
            pw.print("<strong>$"+petTracker.getPrice()+"</strong>");
            pw.print("<h4> Discount: $" + petTracker.getDiscount() + "</h4><ul>");
            pw.print("<h4 style='text-align: center;'>Rebate: "+ petTracker.getRebate() +"%</h4>");
            pw.print("<li id='item'><img src='images/wearables/"+petTracker.getImage()+"' alt='' /></li>");

            pw.print("<li><form method='post' action='Cart'>" +
                    "<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
                    "<input type='hidden' name='type' value='" + petTracker.getProductType() + "'>"+
                    "<input type='hidden' name='maker' value='"+CategoryName+"'>"+
                    "<input type='hidden' name='access' value=''>" +
                    warrantyCheckbox(petTracker) +
                    "<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
            pw.print("<div style='display:flex; justify-content:space-evenly'><li><form method='post' action='WriteReview'>"+
                    "<input type='hidden' name='type' value='" + petTracker.getProductType() + "'>"+
                    "<input type='hidden' name='name' value='" + petTracker.getName() +"'>"+
                    "<input type='hidden' name='maker' value='"+petTracker.getRetailer()+"'>"+
                    "<input type='hidden' name='price' value='"+petTracker.getPrice()+"'>"+
                    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
            pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='" + petTracker.getName() + "'>"+
                    "<input type='hidden' name='type' value='petTracker'>"+
                    "<input type='hidden' name='maker' value='"+petTracker.getRetailer()+"'>"+
                    "<input type='hidden' name='access' value=''>"+
                    "<input type='submit' value='ViewReview' class='btnreview'></form></li></div>");

            if (utility.usertype() != null && utility.usertype().equals("storeManager")) {

                pw.print("<div style='display:flex; justify-content:space-evenly'><li><form method='post' action='ProductModify'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
                        "<input type='hidden' name='productId' value='" + petTracker.getId() + "'>"+
                        "<input type='hidden' name='productManufacturer' value='"+ petTracker.getRetailer() +"'>"+
                        "<input type='hidden' name='productType' value='" + petTracker.getProductType() + "'>"+
                        "<input type='hidden' name='productName' value='" + petTracker.getName() + "'>"+
                        "<input type='hidden' name='productPrice' value='" + petTracker.getPrice() + "'>"+
                        "<input type='hidden' name='productWarranty' value='" + petTracker.getWarrantyPrice() + "'>"+
                        "<input type='hidden' name='productDiscount' value='" + petTracker.getDiscount() + "'>"+
                        "<input type='hidden' name='productRebate' value='" + petTracker.getRebate() + "'>"+
                        "<input type='hidden' name='productCondition' value='" + petTracker.getCondition() + "'>"+
                        "<input type='hidden' name='productDescription' value='" + petTracker.getDescription() + "'>"+
                        "<input type='hidden' name='numberOfAvailableProducts' value='" + petTracker.getNumberOfAvailableProducts() + "'>"+
                        "<input type='hidden' name='numberOfItemsSold' value='" + petTracker.getNumberOfItemsSold() + "'>"+
                        "<input type='submit' name='button' value='Update' class='btnreview'></form></li>");
                pw.print("<li><form method='post' action='ProductCrud'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
                        "<input type='hidden' name='productId' value='" + petTracker.getId() + "'>"+
                        "<input type='submit' name='button' value='Delete' class='btnreview'></form></li></div>");
            }

            pw.print("</ul></div></td>");
            if(i%3==0 || i == size) pw.print("</tr>");
            i++;
        }
        pw.print("</table></div></div></div>");

        utility.printHtml("Footer.html");
    }

    private String warrantyCheckbox(PetTracker petTracker) {
        return petTracker.isHasWarranty()
                ? "<input type='checkbox' name='productWarranty' value='yes'><label> Life Time Warranty: $" + petTracker.getWarrantyPrice() + "</label>"
                : "<p>Warranty not available</p>";
    }
}
