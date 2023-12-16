import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.text.ParseException;

@WebServlet("/Payment")

public class Payment extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		Utilities utility = new Utilities(request, pw);
		if(!utility.isLoggedin()) {
			HttpSession session = request.getSession(true);
			session.setAttribute("login_msg", "Please Login to Pay");
			response.sendRedirect("Login");
			return;
		}
		// get the payment details like credit card no address processed from cart servlet

		HttpSession session=request.getSession();
		String streetAddress=request.getParameter("streetAddress");
		String cityAddress=request.getParameter("cityAddress");
		String stateAddress=request.getParameter("stateAddress");
		String zipcode=request.getParameter("zipcode");
		String customerName=request.getParameter("customerName");
		String deliveryMethod = request.getParameter("deliveryMethod");
		String creditCardNo=request.getParameter("creditCardNo");
		String userName = session.getAttribute("username").toString();

		double orderTotal = Double.parseDouble(request.getParameter("orderTotal"));
		double warrantyPrice = Double.parseDouble(request.getParameter("warrantyPrice"));
		double discountPrice = Double.parseDouble(request.getParameter("discount"));
		double shippingCost = 25;


		List<Store> storeList = MySqlDataStoreUtilities.getAllStores();

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String orderDate = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
		Calendar cal = Calendar.getInstance();

		cal.setTime(new Date());
		cal.add(Calendar.DATE, 14);
		String deliveryDate = sdf.format(cal.getTime());

		cal.setTime(new Date());
		cal.add(Calendar.DATE, 5);
		String maxOrderCancellationDate = sdf.format(cal.getTime());

		cal.setTime(new Date());
		cal.add(Calendar.DATE, 14);
		String maxPickupDate = sdf.format(cal.getTime());


		if(!creditCardNo.isEmpty() ) {
			int orderId=utility.getOrderPaymentSize()+1;

			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Order Confirmation</a>");
			pw.print("</h2><div class='entry'>");
			pw.print("<form method='post' action='OrderConfirmation'>");

			if (deliveryMethod.equals("In Store Pickup")) {

				pw.print("<h3>Choose your pickup location</h3>");
				pw.print("<br />");

				pw.print("<table  class='gridtable'><tr><td>Pickup Store Name:</td><td>");
				pw.print("<select name='pickupStoreName'>");
				for (Store store : storeList) {
					pw.print("<option name='"+ store.getStoreId() +"'>" + store.getStoreId() + "</option>");
				}

				pw.print("</select>");
				pw.print("</td></tr>");
				pw.print("</table>");
			} else {

				pw.print("<h3>You have opted for " + deliveryMethod);
				pw.print("&nbsp&nbsp");
				pw.print("<br/> Additional shipping cost of $" + shippingCost + " will be added to the total");
				pw.print("</h3>");
			}

			pw.print("<br />");
			pw.print("<input type='hidden' name='orderId' value='" + orderId + "' class='btnbuy'>" +
					"<input type='hidden' name='userName' value='" + userName + "' class='btnbuy'>" +
					"<input type='hidden' name='customerName' value='" + customerName + "' class='btnbuy'>" +
					"<input type='hidden' name='streetAddress' value='" + streetAddress + "' class='btnbuy'>" +
					"<input type='hidden' name='cityAddress' value='" + cityAddress + "' class='btnbuy'>" +
					"<input type='hidden' name='stateAddress' value='" + stateAddress + "' class='btnbuy'>" +
					"<input type='hidden' name='zipcode' value='" + zipcode + "' class='btnbuy'>" +
					"<input type='hidden' name='creditCardNo' value='" + creditCardNo + "' class='btnbuy'>" +
					"<input type='hidden' name='orderDate' value='" + orderDate + "' class='btnbuy'>" +
					"<input type='hidden' name='deliveryDate' value='" + deliveryDate + "' class='btnbuy'>" +
					"<input type='hidden' name='shippingCost' value='" + shippingCost + "' class='btnbuy'>" +
					"<input type='hidden' name='orderTotal' value='" + orderTotal + "' class='btnbuy'>" +
					"<input type='hidden' name='deliveryMethod' value='" + deliveryMethod + "' class='btnbuy'>" +
					"<input type='hidden' name='maxPickupDate' value='" + maxPickupDate + "' class='btnbuy'>" +
					"<input type='hidden' name='maxOrderCancellationDate' value='" + maxOrderCancellationDate + "' class='btnbuy'>" +
					"<input type='submit' name='order' value='Confirm Order' class='btnbuy'>" +
					"</form>");
			pw.print("</div></div></div>");
			utility.printHtml("Footer.html");
		}else
		{
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Order</a>");
			pw.print("</h2><div class='entry'>");

			pw.print("<h4 style='color:red'>Please enter valid address and creditcard number</h4>");
			pw.print("</h2></div></div></div>");
			utility.printHtml("Footer.html");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);


	}
}
