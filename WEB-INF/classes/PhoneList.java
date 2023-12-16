import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PhoneList")
public class PhoneList extends HttpServlet
{
	/* Phone Page Displays all the Phone and their Information in BESTDeal */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
		String CategoryName = request.getParameter("maker");
		
		HashMap<String, Phone> allPhones = new HashMap<>();
		HashMap<String, Phone> hm = new HashMap<>();

		try
		{
			allPhones = MySqlDataStoreUtilities.getPhones();
			System.out.println("All Phones: " + allPhones.size());
		}
		catch(Exception e)
		{

		}

		if(CategoryName==null)
		{
			//hm.putAll(SaxParserDataStore.phones);
			hm.putAll(allPhones);
			name = "";
		}
		else
		{
			if(CategoryName.equals("apple"))
			{
				//for(Map.Entry<String,Phone> entry : SaxParserDataStore.phones.entrySet())
				for(Map.Entry<String,Phone> entry : allPhones.entrySet())
				{
					if(entry.getValue().getRetailer().equals("Apple"))
					{
						hm.put(entry.getValue().getId(),entry.getValue());
					}
				}
				name = "Apple";
			}
			else if(CategoryName.equals("samsung"))
			{
				for(Map.Entry<String,Phone> entry : allPhones.entrySet())
				{
					if(entry.getValue().getRetailer().equals("Samsung"))
					{
						hm.put(entry.getValue().getId(),entry.getValue());
					}
				}
				name = "Samsung";
			}
			else if(CategoryName.equals("google"))
			{
				for(Map.Entry<String,Phone> entry : allPhones.entrySet())
				{
					if(entry.getValue().getRetailer().equals("Google"))
					{
						hm.put(entry.getValue().getId(),entry.getValue());
					}
				}
				name = "Google";
			}
			else if(CategoryName.equals("huawei"))
			{
				for(Map.Entry<String,Phone> entry : allPhones.entrySet())
				{
					if(entry.getValue().getRetailer().equals("Huawei"))
					{
						hm.put(entry.getValue().getId(),entry.getValue());
					}
				}
				name = "Huawei";
			}
			else if(CategoryName.equals("oneplus"))
			{
				for(Map.Entry<String,Phone> entry : allPhones.entrySet())
				{
					if(entry.getValue().getRetailer().equals("OnePlus"))
					{
						hm.put(entry.getValue().getId(),entry.getValue());
					}
				}
				name = "OnePlus";
			}
		}
		
		/* Header, Left Navigation Bar are Printed.

		All the Phone and Phone information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+name+" Doorbells</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		System.out.println("Size: " + size);
		for(Map.Entry<String, Phone> entry : hm.entrySet())
		{
			Phone phone = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+phone.getName()+"</h3>");
			pw.print("<h4>" + phone.getDescription() + "</h5>");
			pw.print("<strong>$"+phone.getPrice()+"</strong>");
			pw.print("<h4>Discount: $" + phone.getDiscount() + "</h4><ul>");
			pw.print("<h4 style='text-align: center;'>Rebate: "+ phone.getRebate() +"%</h4>");
			pw.print("<li id='item'><img src='images/phones/"+phone.getImage()+"' alt='' /></li>");
			
			pw.print("<li><form method='post' action='Cart' style='text-align: center;'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='" + phone.getProductType() + "'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>" +
					warrantyCheckbox(phone) +
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
			pw.print("<div style='display:flex; justify-content:space-evenly'><li><form method='post' action='WriteReview'>"+
					"<input type='hidden' name='type' value='" + phone.getProductType() + "'>"+
					"<input type='hidden' name='name' value='" + phone.getName() +"'>"+
					"<input type='hidden' name='maker' value='"+phone.getRetailer()+"'>"+
					"<input type='hidden' name='price' value='"+phone.getPrice()+"'>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='" + phone.getName() + "'>"+
					"<input type='hidden' name='type' value='Phone'>"+
					"<input type='hidden' name='maker' value='"+phone.getRetailer()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='ViewReview' class='btnreview'></form></li></div>");

			if (utility.usertype() != null && utility.usertype().equals("storeManager")) {

				pw.print("<div style='display:flex; justify-content:space-evenly'><li><form method='post' action='ProductModify'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
						"<input type='hidden' name='productId' value='" + phone.getId() + "'>"+
						"<input type='hidden' name='productManufacturer' value='"+ phone.getRetailer() +"'>"+
						"<input type='hidden' name='productType' value='" + phone.getProductType() + "'>"+
						"<input type='hidden' name='productName' value='" + phone.getName() + "'>"+
						"<input type='hidden' name='productPrice' value='" + phone.getPrice() + "'>"+
						"<input type='hidden' name='productWarranty' value='" + phone.getWarrantyPrice() + "'>"+
						"<input type='hidden' name='productDiscount' value='" + phone.getDiscount() + "'>"+
						"<input type='hidden' name='productRebate' value='" + phone.getRebate() + "'>"+
						"<input type='hidden' name='productCondition' value='" + phone.getCondition() + "'>"+
						"<input type='hidden' name='productDescription' value='" + phone.getDescription() + "'>"+
						"<input type='hidden' name='numberOfAvailableProducts' value='" + phone.getNumberOfAvailableProducts() + "'>"+
						"<input type='hidden' name='numberOfItemsSold' value='" + phone.getNumberOfItemsSold() + "'>"+
						"<input type='submit' name='button' value='Update' class='btnreview'></form></li>");
				pw.print("<li><form method='post' action='ProductCrud'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
						"<input type='hidden' name='productId' value='" + phone.getId() + "'>"+
						"<input type='submit' name='button' value='Delete' class='btnreview'></form></li></div>");
			}

			pw.print("</ul></div></td>");
			if(i%3==0 || i == size) pw.print("</tr>");
			i++;
		}	
		pw.print("</table></div></div></div>");
   
		utility.printHtml("Footer.html");
	}

	private String warrantyCheckbox(Phone phone) {
		return phone.isHasWarranty()
				? "<input type='checkbox' name='productWarranty' value='yes'><label> Life Time Warranty: $" + phone.getWarrantyPrice() + "</label>"
				: "<p>Warranty not available</p>";
	}
}