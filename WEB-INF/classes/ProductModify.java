import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ProductModify")

public class ProductModify extends HttpServlet {
	protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String action = request.getParameter("button");
		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		if(action.equals("Addproduct")) {
			pw.print("<div id='content'><div class='post'>");
			pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Add Product</a></h2>"
					+ "<div class='entry'>");

			pw.print("<form method='post' action='ProductCrud' enctype='multipart/form-data'>");
			pw.print("<table  class='gridtable addProduct'>");
			pw.print("<tr><td>Product Name</td>");
			pw.print("<td><input class='input' type='text' name='productName' id ='productName' /></td></tr>");
			pw.print("<tr><td>Product Type</td>");
			pw.print("<td>" +
					"<select name='productType' id='productType'>" +
					"<option name='' value=''></option>" +
					"<option name='fitnessWatch' value='Fitness Watch'>Fitness Watch</option>" +
					"<option name='smartWatch' value='Smart Watch'>Smart Watch</option>" +
					"<option name='headphone' value='Headphone'>Headphone</option>" +
					"<option name='virtualReality' value='Virtual Reality'>Virtual Reality</option>" +
					"<option name='petTracker' value='Pet Tracker'>Pet Tracker</option>" +
					"<option name='phone' value='Phone'>Phone</option>" +
					"<option name='laptop' value='Laptop'>Laptop</option>" +
					"<option name='voiceAssistant' value='Voice Assistant'>Voice Assistant</option>" +
					"<option name='accessory' value='Accessory'>Accessory</option>" +
					"</select></td></tr>");
			pw.print("<tr><td>Price</td>");
			pw.print("<td><input class='input' type='number' name='productPrice' id ='productPrice' placeholder='please enter numeric data' /></td></tr>");
			pw.print("<tr><td>Warranty</td>");
			pw.print("<td><input class='input' type='number' name='productWarranty' id ='productWarranty' placeholder='please enter numeric data' /></td></tr>");
			pw.print("<tr><td>Image</td>");
			pw.print("<td><input class='input' type='file' accept='image/png, image/jpeg' name='productImage' id ='productImage' /></td></tr>");
			pw.print("<tr><td>Manufacturer</td>");
			pw.print("<td><input class='input' type='text' name='productManufacturer' id ='productManufacturer' /></td></tr>");
			pw.print("<tr><td>Condition</td>");
			pw.print("<td><select name='productCondition' id='productCondition'>" +
					"<option name='' value=''></option>" +
					"<option name='New' value='New'>New</option>" +
					"<option name='Used' value='Used'>Used</option>" +
					"</select></td></tr>");
			pw.print("<tr><td>Discount</td>");
			pw.print("<td><input class='input' type='number' name='productDiscount' id ='productDiscount' placeholder='please enter numeric data' /></td></tr>");
			pw.print("<tr><td>Description</td>");
			pw.print("<td><input class='input' type='text' name='productDescription' id ='productDescription' /></td></tr>");
			pw.print("<tr><td>Rebate</td>");
			pw.print("<td><input class='input' type='number' name='productRebate' id ='productRebate' /></td></tr>");
			pw.print("<tr><td>Number of Available Products</td>");
			pw.print("<td><input class='input' type='number' name='numberOfAvailableProducts' id ='numberOfAvailableProducts' /></td></tr>");
			pw.print("<tr><td>Number of Items Sold</td>");
			pw.print("<td><input class='input' type='number' name='numberOfItemsSold' id ='numberOfItemsSold' /></td></tr>");
			pw.print("<tr><td colspan='2'><input type='submit' name='button' value='Add Product' class='btnbuy'></td></tr>");
			pw.print("</table></form></div></div></div>");

			/*pw.print("<form method='get' action='ProductCrud'>"
					+ "<table id='bestseller'><tr><td>"
                    + "<h3>Product Type</h3></td><td><select name='productType' class='input'><option value='television' selected>Television</option>"
                    + "<option value='soundSystem'>Sound System</option><option value='phone'>Phone</option><option value='laptop'>Laptop</option>"
                    + "<option value='voiceAssistant'>Voice Assistant</option><option value='fitnessWatch'>Fitness Watch</option><option value='smartWatch'>Smart Watch</option>"
                    + "<option value='headphone'>Headphone</option><option value='wirelessPlan'>Wireless Plan</option>"
                    + "</select>"
					+ "</td></tr><tr><td>"
					+"<h3>Product</h3></td><td><input type='text' name='product' placeholder='Please mention product if adding accessories' value='' class='input'></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Id</h3></td><td><input type='text' name='productId' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Name</h3></td><td><input type='text' name='productName' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Price</h3></td><td><input type='number' step='any' placeholder='please enter numeric data' name='productPrice' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Image</h3></td><td><input type='text' name='productImage' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Manufacturer</h3></td><td><input type='text' name='productManufacturer' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Condition</h3></td><td><input type='text' name='productCondition' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Discount</h3></td><td><input type='number' step='any' placeholder='please enter numeric data' name='productDiscount' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Rebate</h3></td><td><input type='text' name='productRebate' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Description</h3></td><td><input type='text' name='productDescription' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<input type='submit' class='btnbuy' name='button' value='add' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
					+ "</td></tr><tr><td></td><td>"
					+ "</td></tr></table>"
					+ "</form>" + "</div></div></div>");*/
        }
		utility.printHtml("Footer.html");
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String action = request.getParameter("button");
		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");

		if (action.equals("Update")) {
			String productType = request.getParameter("productType");
			String productName = request.getParameter("productName");
			String productId = request.getParameter("productId");;
			double productPrice = Double.parseDouble(request.getParameter("productPrice"));
			String productManufacturer = request.getParameter("productManufacturer");
			String productCondition = request.getParameter("productCondition");
			double productDiscount = Double.parseDouble(request.getParameter("productDiscount"));
			int productRebate = Integer.parseInt(request.getParameter("productRebate"));
			String productDescription = request.getParameter("productDescription");
			double productWarranty = Double.parseDouble(request.getParameter("productWarranty"));
			int numberOfAvailableProducts = Integer.parseInt(request.getParameter("numberOfAvailableProducts"));
			int numberOfItemsSold = Integer.parseInt(request.getParameter("numberOfItemsSold"));

			pw.print("<div id='content'><div class='post'>");
			pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Update Product</a></h2>"
					+ "<div class='entry'>");

			pw.print("<form method='post' action='ProductCrud' enctype='multipart/form-data'>");
			pw.print("<table  class='gridtable addProduct'>");
			pw.print("<tr><td>Product Name</td>");
			pw.print("<td><input class='input' type='text' name='productName' id ='productName' value='" + productName + "' /></td></tr>");
			pw.print("<tr><td>Product Type</td>");
			pw.print("<td>" +
					"<select name='productType' id='productType'>" +
					"<option name='' value=''></option>" +
					"<option name='fitnessWatch' value='Fitness Watch' " + isSelected(productType, "Fitness Watch") +">Fitness Watch</option>" +
					"<option name='smartWatch' value='Smart Watch' " + isSelected(productType, "Smart Watch") + ">Smart Watch</option>" +
					"<option name='headphone' value='Headphone' " + isSelected(productType, "Headphone") + ">Headphone</option>" +
					"<option name='virtualReality' value='Virtual Reality' "+ isSelected(productType, "Virtual Reality") +">Virtual Reality</option>" +
					"<option name='petTracker' value='Pet Tracker' " + isSelected(productType, "Pet Tracker") +">Pet Tracker</option>" +
					"<option name='phone' value='Phone' " + isSelected(productType, "Phone") + ">Phone</option>" +
					"<option name='laptop' value='Laptop' " + isSelected(productType, "Laptop") + ">Laptop</option>" +
					"<option name='voiceAssistant' value='Voice Assistant' " + isSelected(productType, "Voice Assistant") + ">Voice Assistant</option>" +
					"<option name='accessory' value='Accessory' " + isSelected(productType, "Accessory") + ">Accessory</option>" +
					"</select></td></tr>");
			pw.print("<tr><td>Price</td>");
			pw.print("<td><input class='input' type='number' name='productPrice' id ='productPrice' placeholder='please enter numeric data' value='" + productPrice + "' /></td></tr>");
			pw.print("<tr><td>Warranty</td>");
			pw.print("<td><input class='input' type='number' name='productWarranty' id ='productWarranty' placeholder='please enter numeric data' value='" + productWarranty + "' /></td></tr>");
			pw.print("<tr><td>Image</td>");
			pw.print("<td><input class='input' type='file' accept='image/png, image/jpeg' name='productImage' id ='productImage' /></td></tr>");
			pw.print("<tr><td>Manufacturer</td>");
			pw.print("<td><input class='input' type='text' name='productManufacturer' id ='productManufacturer' value='" + productManufacturer + "' /></td></tr>");
			pw.print("<tr><td>Condition</td>");
			pw.print("<td><select name='productCondition' id='productCondition'>" +
					"<option name='' value=''></option>" +
					"<option name='New' value='New' "  + isSelected(productCondition, "New") + ">New</option>" +
					"<option name='Used' value='Used' "  + isSelected(productCondition, "Used") + ">Used</option>" +
					"</select></td></tr>");
			pw.print("<tr><td>Discount</td>");
			pw.print("<td><input class='input' type='number' name='productDiscount' id ='productDiscount' placeholder='please enter numeric data' value='" + productDiscount + "' /></td></tr>");
			pw.print("<tr><td>Description</td>");
			pw.print("<td><input class='input' type='text' name='productDescription' id ='productDescription' value='" + productDescription + "' /></td></tr>");
			pw.print("<tr><td>Rebate</td>");
			pw.print("<td><input class='input' type='number' name='productRebate' id ='productRebate' value='" + productRebate + "' /></td></tr>");
			pw.print("<tr><td>Number of Available Products</td>");
			pw.print("<td><input class='input' type='number' name='numberOfAvailableProducts' id ='numberOfAvailableProducts' value='"+ numberOfAvailableProducts +"' /></td></tr>");
			pw.print("<tr><td>Number of Items Sold</td>");
			pw.print("<td><input class='input' type='number' name='numberOfItemsSold' id ='numberOfItemsSold' value='"+ numberOfItemsSold +"' /></td></tr>");
			pw.print("<input class='hidden' type='text' name='productId' id ='productId' value='" + productId + "' />");
			pw.print("<tr><td colspan='2'><input type='submit' name='button' value='Update Product' class='btnbuy'></td></tr>");
			pw.print("</table></form></div></div></div>");
		}
		utility.printHtml("Footer.html");
	}

	private String isSelected(String productType, String type) {
		return productType.equals(type) ? "selected" : "";
	}

}