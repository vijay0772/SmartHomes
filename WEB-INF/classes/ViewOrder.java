import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/ViewOrder")

public class ViewOrder extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		Utilities utility = new Utilities(request, pw);
		//check if the user is logged in
		if(!utility.isLoggedin())
		{
			HttpSession session = request.getSession(true);
			session.setAttribute("login_msg", "Please Login to View your Orders");
			response.sendRedirect("Login");
			return;
		}
		String username=utility.username();
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<form name ='ViewOrder' action='ViewOrder' method='get'>");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Order</a>");
		pw.print("</h2><div class='entry'>");

		/*check if the order button is clicked 
		if order button is not clicked that means the view order page is visited freshly
		then user will get textbox to give order number by which he can view order 
		if order button is clicked user will be directed to this same servlet and user has given order number 
		then this page shows all the order details*/

		if(request.getParameter("Order")==null)
		{
			pw.print("<table align='center'><tr><td>Enter OrderNo &nbsp&nbsp<input name='orderId' type='text'></td>");
			pw.print("<td><input type='submit' name='Order' value='ViewOrder' class='btnbuy'></td></tr></table>");
		}

		//hashmap gets all the order details from file 

		HashMap<Integer, ArrayList<CustomerOrder>> customerOrder = new HashMap<>();
		Transaction transaction = new Transaction();
		/*
		String TOMCAT_HOME = System.getProperty("catalina.home");

		try
		{
			FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\SmartPortables\\PaymentDetails.txt"));
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
			orderPayments = (HashMap)objectInputStream.readObject();
		}
		catch(Exception e)
		{
		}
		*/

		/*if order button is clicked that is user provided a order number to view order 
		order details will be fetched and displayed in  a table 
		Also user will get an button to cancel the order */

		if(request.getParameter("Order")!=null && request.getParameter("Order").equals("ViewOrder"))
		{
			if (request.getParameter("orderId") != null && request.getParameter("orderId") != "" )
			{
				int orderId = Integer.parseInt(request.getParameter("orderId"));
				pw.print("<input type='hidden' name='orderId' value='"+orderId+"'>");
				//get the order details from file
				try
				{
					/*
					FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\SmartPortables\\PaymentDetails.txt"));
					ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
					orderPayments = (HashMap)objectInputStream.readObject();
					*/
					customerOrder = MySqlDataStoreUtilities.selectOrder();
					transaction = MySqlDataStoreUtilities.selectTransaction(orderId);

					System.out.println("customer orders: " + customerOrder.get(orderId).toString());
				}
				catch(Exception e) {

				}
				int size=0;
			

				/*get the order size and check if there exist an order with given order number 
				if there is no order present give a message no order stored with this id */

				if(customerOrder.get(orderId)!=null)
				{
					for(CustomerOrder od : customerOrder.get(orderId))
						if(od.getUserName().equals(username))
							size = customerOrder.get(orderId).size();
				}
				// display the orders if there exist order with order id
				if(size>0) {
					pw.print("<table  class='gridtable'>");
					pw.print("<tr><td></td>");
					pw.print("<td>Order ID:</td>");
					pw.print("<td>User Name:</td>");
					pw.print("<td>Product Name:</td>");
					pw.print("<td>Product Price:</td></tr>");
					for (CustomerOrder oi : customerOrder.get(orderId))
					{
						pw.print("<tr>");
						pw.print("<td><input type='radio' name='orderName' value='"+oi.getProductName()+"'></td>");
						pw.print("<td>"+oi.getOrderId()+".</td>" +
								"<td>"+oi.getUserName()+"</td>" +
								"<td>"+oi.getProductName()+"</td>" +
								"<td>Price: "+oi.getProductPrice()+"</td>");
						pw.print("<td><input type='submit' name='Order' value='CancelOrder' class='btnbuy'></td>");
						pw.print("</tr>");
					}
					pw.print("</table>");
				}
				else {
					pw.print("<h4 style='color:red'>You have not placed any order with this order id</h4>");
				}
			} else {
				pw.print("<h4 style='color:red'>Please enter the valid order number</h4>");
			}
		}
		//if the user presses cancel order from order details shown then process to cancel the order
		if(request.getParameter("Order")!=null && request.getParameter("Order").equals("CancelOrder")) {
			System.out.println("CancelOrder ");
			if(request.getParameter("orderName") != null) {
				String orderName=request.getParameter("orderName");
				System.out.println("OrderName: " + orderName);
				int orderId=0;
				orderId=Integer.parseInt(request.getParameter("orderId"));
				System.out.println("orderId: " + orderId);
				ArrayList<CustomerOrder> ListOrderPayment =new ArrayList<>();
				//get the order from file
				try {
					/*
					FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\SmartPortables\\PaymentDetails.txt"));
					ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
					orderPayments = (HashMap)objectInputStream.readObject();
					*/
					customerOrder = MySqlDataStoreUtilities.selectOrder();
					System.out.println("Customer ORder: " + customerOrder.toString());
				}
				catch(Exception e) {
						System.out.println("error: " + e.getMessage());
				}
				//get the exact order with same ordername and add it into cancel list to remove it later
				for (CustomerOrder oi : customerOrder.get(orderId)) {
					System.out.println("oi: " + oi.getProductName());
					if(oi.getProductName().equals(orderName)) {
						String maxDate = oi.getMaxCancellationDate();
						SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
						//Date maxDate = sdf.parse(maxdate); //date1
						try
						{
							Date formattedMaxDate = new SimpleDateFormat("MM/dd/yyyy").parse(maxDate);

							String currentDate = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
							Date formattedCurrentDate = new SimpleDateFormat("MM/dd/yyyy").parse(currentDate);

							if(formattedMaxDate.compareTo(formattedCurrentDate) > 0)
							{
								MySqlDataStoreUtilities.deleteOrder(orderId, orderName); // check it once
								ListOrderPayment.add(oi);
								pw.print("<h4 style='color:red'>Your Order is Cancelled</h4>");
							}
							else
							{
								pw.print("<h4 style='color:red'>Less than 5 days left for delivery. You cannot cancel order now.</h4>");
							}
						}
						catch(Exception e)
						{

						}								
					}
				}
				//remove all the orders from hashmap that exist in cancel list
				customerOrder.get(orderId).removeAll(ListOrderPayment);
				if(customerOrder.get(orderId).size()==0)
				{
					customerOrder.remove(orderId);
				}

				/*
				//save the updated hashmap with removed order to the file	
				try
				{	
					FileOutputStream fileOutputStream = new FileOutputStream(new File(TOMCAT_HOME+"\\webapps\\SmartPortables\\PaymentDetails.txt"));
					ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
					objectOutputStream.writeObject(orderPayments);
					objectOutputStream.flush();
					objectOutputStream.close();       
					fileOutputStream.close();
				}
				catch(Exception e)
				{
				
				}
				*/
			}
			else
			{
				pw.print("<h4 style='color:red'>Please select any product</h4>");
			}
		}
		pw.print("</form></div></div></div>");		
		utility.printHtml("Footer.html");
	}

	private void displayUpdateOrder(PrintWriter pw, CustomerOrder order) {
		List<Store> allStores = MySqlDataStoreUtilities.getAllStores();
		String[] address = order.getCustomerAddress().split(", ");
		String street = address[0];
		String city = address[1];
		String stateZip = address[2];
		String[] stateZipCode = stateZip.split(" - ");
		String state = stateZipCode[0];
		String zipcode = stateZipCode[1];

	}
}


