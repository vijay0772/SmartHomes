import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/VoiceAssistantList")
public class VoiceAssistantList extends HttpServlet
{
	/* VoiceAssistant Page Displays all the VoiceAssistant and their Information in BESTDeal */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
		String CategoryName = request.getParameter("maker");
		
		HashMap<String, VoiceAssistant> allVoiceAssistants = new HashMap<>();
		HashMap<String, VoiceAssistant> hm = new HashMap<>();

		try
		{
			allVoiceAssistants = MySqlDataStoreUtilities.getVoiceAssistants();
		}
		catch(Exception e)
		{

		}

		if(CategoryName==null)
		{
			//hm.putAll(SaxParserDataStore.voiceAssistants);
			hm.putAll(allVoiceAssistants);
			name = "";
		}
		else
		{
			if(CategoryName.equals("google"))
			{
				//for(Map.Entry<String,VoiceAssistant> entry : SaxParserDataStore.voiceAssistants.entrySet())
				for(Map.Entry<String,VoiceAssistant> entry : allVoiceAssistants.entrySet())
				{
					if(entry.getValue().getRetailer().equals("Google"))
					{
						hm.put(entry.getValue().getId(),entry.getValue());
					}
				}
				name = "Google";
			}
			else if(CategoryName.equals("amazon"))
			{
				for(Map.Entry<String,VoiceAssistant> entry : allVoiceAssistants.entrySet())
				{
					if(entry.getValue().getRetailer().equals("Amazon"))
					{
						hm.put(entry.getValue().getId(),entry.getValue());
					}
				}
				name = "Amazon";
			}
			else if(CategoryName.equals("apple"))
			{
				for(Map.Entry<String,VoiceAssistant> entry : allVoiceAssistants.entrySet())
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

		All the VoiceAssistant and VoiceAssistant information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+name+" Lightings</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		for(Map.Entry<String, VoiceAssistant> entry : hm.entrySet())
		{
			VoiceAssistant voiceAssistant = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+voiceAssistant.getName()+"</h3>");
			pw.print("<strong>$"+voiceAssistant.getPrice()+"</strong><ul>");
			pw.print("<h4> Discount: $" + voiceAssistant.getDiscount() + "</h4><ul>");
			pw.print("<h4 style='text-align: center;'>Rebate: "+ voiceAssistant.getRebate() +"%</h4>");
			pw.print("<li id='item'><img src='images/assistant/"+voiceAssistant.getImage()+"' alt='' /></li>");

			pw.print("<li><form method='post' action='Cart' style='text-align: center;'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='" + voiceAssistant.getProductType() + "'>"+
					"<input type='hidden' name='maker' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>" +
					warrantyCheckbox(voiceAssistant) +
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
			pw.print("<div style='display:flex; justify-content:space-evenly'><li><form method='post' action='WriteReview'>"+
					"<input type='hidden' name='type' value='" + voiceAssistant.getProductType() + "'>"+
					"<input type='hidden' name='name' value='" + voiceAssistant.getName() +"'>"+
					"<input type='hidden' name='maker' value='"+voiceAssistant.getRetailer()+"'>"+
					"<input type='hidden' name='price' value='"+voiceAssistant.getPrice()+"'>"+
					"<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='" + voiceAssistant.getName() + "'>"+
					"<input type='hidden' name='type' value='Voice Assistant'>"+
					"<input type='hidden' name='maker' value='"+voiceAssistant.getRetailer()+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' value='ViewReview' class='btnreview'></form></li></div>");

			if (utility.usertype() != null && utility.usertype().equals("storeManager")) {

				pw.print("<div style='display:flex; justify-content:space-evenly'><li><form method='post' action='ProductModify'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
						"<input type='hidden' name='productId' value='" + voiceAssistant.getId() + "'>"+
						"<input type='hidden' name='productManufacturer' value='"+ voiceAssistant.getRebate() +"'>"+
						"<input type='hidden' name='productType' value='" + voiceAssistant.getProductType() + "'>"+
						"<input type='hidden' name='productName' value='" + voiceAssistant.getName() + "'>"+
						"<input type='hidden' name='productPrice' value='" + voiceAssistant.getPrice() + "'>"+
						"<input type='hidden' name='productWarranty' value='" + voiceAssistant.getWarrantyPrice() + "'>"+
						"<input type='hidden' name='productDiscount' value='" + voiceAssistant.getDiscount() + "'>"+
						"<input type='hidden' name='productRebate' value='" + voiceAssistant.getRebate() + "'>"+
						"<input type='hidden' name='productCondition' value='" + voiceAssistant.getCondition() + "'>"+
						"<input type='hidden' name='productDescription' value='" + voiceAssistant.getDescription() + "'>"+
						"<input type='hidden' name='numberOfAvailableProducts' value='" + voiceAssistant.getNumberOfAvailableProducts() + "'>"+
						"<input type='hidden' name='numberOfItemsSold' value='" + voiceAssistant.getNumberOfItemsSold() + "'>"+
						"<input type='submit' name='button' value='Update' class='btnreview'></form></li>");
				pw.print("<li><form method='post' action='ProductCrud'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
						"<input type='hidden' name='productId' value='" + voiceAssistant.getId() + "'>"+
						"<input type='submit' name='button' value='Delete' class='btnreview'></form></li></div>");
			}

			pw.print("</ul></div></td>");
			if(i%3==0 || i == size) pw.print("</tr>");
			i++;
		}	
		pw.print("</table></div></div></div>");
   
		utility.printHtml("Footer.html");
	}

	private String warrantyCheckbox(VoiceAssistant assistant) {
		return assistant.isHasWarranty()
				? "<input type='checkbox' name='productWarranty' value='yes'><label> Life Time Warranty: $" + assistant.getWarrantyPrice() + "</label>"
				: "<p>Warranty not available</p>";
	}
}