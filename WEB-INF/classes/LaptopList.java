import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LaptopList")
public class LaptopList extends HttpServlet
{
	/* Laptop Page Displays all the Laptop and their Information in BESTDeal */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
		String CategoryName = request.getParameter("maker");
		
		HashMap<String, Laptop> allLaptops = new HashMap<>();
		HashMap<String, Laptop> hm = new HashMap<>();

		try
		{
			allLaptops = MySqlDataStoreUtilities.getLaptops();
		}
		catch(Exception e)
		{

		}

		if(CategoryName==null)
		{
			hm.putAll(allLaptops);
			//hm.putAll(SaxParserDataStore.laptops);
			name = "";
		}
		else
		{
			if(CategoryName.equals("apple"))
			{
				//for(Map.Entry<String,Laptop> entry : SaxParserDataStore.laptops.entrySet())
				for(Map.Entry<String,Laptop> entry : allLaptops.entrySet())
				{
					if(entry.getValue().getRetailer().equals("Apple"))
					{
						hm.put(entry.getValue().getId(),entry.getValue());
					}
				}
				name = "Apple";
			}
			else if(CategoryName.equals("dell"))
			{
				for(Map.Entry<String,Laptop> entry : allLaptops.entrySet())
				{
					if(entry.getValue().getRetailer().equals("Dell"))
					{
						hm.put(entry.getValue().getId(),entry.getValue());
					}
				}
				name = "dell";
			}
			else if(CategoryName.equals("microsoft"))
			{
				for(Map.Entry<String,Laptop> entry : allLaptops.entrySet())
				{
					if(entry.getValue().getRetailer().equals("Microsoft"))
					{
						hm.put(entry.getValue().getId(),entry.getValue());
					}
				}
				name = "Microsoft";
			}
			else if(CategoryName.equals("hp"))
			{
				for(Map.Entry<String,Laptop> entry : allLaptops.entrySet())
				{
					if(entry.getValue().getRetailer().equals("HP"))
					{
						hm.put(entry.getValue().getId(),entry.getValue());
					}
				}
				name = "HP";
			}
		}
		
		/* Header, Left Navigation Bar are Printed.

		All the Laptop and Laptop information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+name+" Speakers</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, Laptop> entry : hm.entrySet())
		{
			Laptop laptop = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+laptop.getName()+"</h3>");
			pw.print("<strong>$"+laptop.getPrice()+"</strong><ul>");
			pw.print("<h4> Discount: $" + laptop.getDiscount() + "</h4><ul>");
			pw.print("<h4 style='text-align: center;'>Rebate: "+ laptop.getRebate() +"%</h4>");
			pw.print("<li id='item'><img src='images/laptops/"+laptop.getImage()+"' alt='' /></li>");

			pw.print("<li><form method='post' action='Cart' style='text-align: center;'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='" + laptop.getProductType() + "'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>" +
					warrantyCheckbox(laptop) +
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
			pw.print("<div style='display:flex; justify-content:space-evenly'><li><form method='post' action='WriteReview'>"+
					"<input type='hidden' name='type' value='" + laptop.getProductType() + "'>"+
					"<input type='hidden' name='name' value='" + laptop.getName() +"'>"+
					"<input type='hidden' name='maker' value='"+laptop.getRetailer()+"'>"+
					"<input type='hidden' name='price' value='"+laptop.getPrice()+"'>"+
					"<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='" + laptop.getName() + "'>"+
					"<input type='hidden' name='type' value='Laptop'>"+
					"<input type='hidden' name='maker' value='"+laptop.getRetailer()+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' value='ViewReview' class='btnreview'></form></li></div>");

			if (utility.usertype() != null && utility.usertype().equals("storeManager")) {

				pw.print("<div style='display:flex; justify-content:space-evenly'><li><form method='post' action='ProductModify'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
						"<input type='hidden' name='productId' value='" + laptop.getId() + "'>"+
						"<input type='hidden' name='productManufacturer' value='"+ laptop.getRetailer() +"'>"+
						"<input type='hidden' name='productType' value='" + laptop.getProductType() + "'>"+
						"<input type='hidden' name='productName' value='" + laptop.getName() + "'>"+
						"<input type='hidden' name='productPrice' value='" + laptop.getPrice() + "'>"+
						"<input type='hidden' name='productWarranty' value='" + laptop.getWarrantyPrice() + "'>"+
						"<input type='hidden' name='productDiscount' value='" + laptop.getDiscount() + "'>"+
						"<input type='hidden' name='productRebate' value='" + laptop.getRebate() + "'>"+
						"<input type='hidden' name='productCondition' value='" + laptop.getCondition() + "'>"+
						"<input type='hidden' name='productDescription' value='" + laptop.getDescription() + "'>"+
						"<input type='hidden' name='numberOfAvailableProducts' value='" + laptop.getNumberOfAvailableProducts() + "'>"+
						"<input type='hidden' name='numberOfItemsSold' value='" + laptop.getNumberOfItemsSold() + "'>"+
						"<input type='submit' name='button' value='Update' class='btnreview'></form></li>");
				pw.print("<li><form method='post' action='ProductCrud'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
						"<input type='hidden' name='productId' value='" + laptop.getId() + "'>"+
						"<input type='submit' name='button' value='Delete' class='btnreview'></form></li></div>");
			}

			pw.print("</ul></div></td>");
			if(i%3==0 || i == size) pw.print("</tr>");
			i++;
		}	
		pw.print("</table></div></div></div>");
   
		utility.printHtml("Footer.html");
	}

	private String warrantyCheckbox(Laptop laptop) {
		return laptop.isHasWarranty()
				? "<input type='checkbox' name='productWarranty' value='yes'><label> Life Time Warranty: $" + laptop.getWarrantyPrice() + "</label>"
				: "<p>Warranty not available</p>";
	}
}