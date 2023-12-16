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

@WebServlet("/Cart")

public class Cart extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();


		/* From the HttpServletRequest variable name,type,maker and acessories information are obtained.*/

		Utilities utility = new Utilities(request, pw);
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String maker = request.getParameter("maker");
		String access = request.getParameter("access");
		boolean isWarrantyIncluded = request.getParameter("productWarranty") != null;
		/*
		double orderPrice = 0;
		//double orderPrice = Double.parseDouble(request.getParameter("orderPrice"));
		try
		{
			orderPrice = Double.parseDouble(request.getParameter("orderPrice"));
		}
		catch(Exception e)
		{
			
		}
		//String orderPrice = request.getParameter("orderPrice");
		String orderName = request.getParameter("orderName");
		String deleteItemFromCart = request.getParameter("deleteItemFromCart");
		

		if(orderPrice != 0 && orderName != null && deleteItemFromCart.equalsIgnoreCase("DeleteItem"))
		//if(orderPrice != null && orderName != null && deleteItemFromCart.equalsIgnoreCase("DeleteItem"))
		{
			ArrayList<OrderItem> order = new ArrayList<OrderItem>();
			order = utility.getCustomerOrders();
			for (OrderItem oi : utility.getCustomerOrders())
			{
				//if(oi.getName().equalsIgnoreCase(orderName) && oi.getPrice().toString().equals(orderPrice))
				if(oi.getName().equalsIgnoreCase(orderName) && oi.getPrice() == orderPrice)
				//if(oi.getName().equalsIgnoreCase(orderName))
				{
					//&& oi.getPrice().equals(orderPrice)
					//Delete item from Hashmap
					order.remove(oi);
				}
			}
		}
		*/
		System.out.print("name" + name + "type" + type + "maker" + maker + "accesee" + access);

		/* StoreProduct Function stores the Purchased product in Orders HashMap.*/	
		
		utility.storeProduct(name, type, maker, access, isWarrantyIncluded);
		displayCart(request, response);
	}
	

/* displayCart Function shows the products that users has bought, these products will be displayed with Total Amount.*/

	protected void displayCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request,pw);
		Carousel carousel = new Carousel();
		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to add items to cart");
			response.sendRedirect("Login");
			return;
		}
		
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Cart("+utility.CartCount()+")</a>");
		pw.print("</h2><div class='entry'>");
		pw.print("<form name ='Cart' action='CheckOut' method='post'>");
		if(utility.CartCount()>0)
		{
			pw.print("<table  class='gridtable'>");
			int i = 1;
			double totalOrderPrice = 0;
			double discount = 0;
			double warranty = 0;
			double finalOrderTotal = 0;
			for (OrderItem oi : utility.getCustomerOrders()) 
			{
				pw.print("<tr>");
				pw.print("<td>"+i+".</td>" +
						"<td>"+oi.getName()+"</td>" +
						"<td>Price: $"+oi.getPrice()+"</td>" +
						"<td>Discount: $"+oi.getDiscount()+"</td>"+
						"<td>Warranty Price: $"+getWarrantyPrice(oi)+"</td>"
				);
				pw.print("<input type='hidden' name='orderName' value='"+oi.getName()+"'>");
				pw.print("<input type='hidden' name='orderPrice' value='"+oi.getPrice()+"'>");
				pw.print("<input type='hidden' name='discount' value='"+oi.getDiscount()+"'>");
				pw.print("<input type='hidden' name='warrantyPrice' value='"+oi.getWarrantyPrice()+"'>");
				pw.print("<td><a href='DeleteFromCart?name="+oi.getName()+"&price="+oi.getPrice()+"'>Delete Item</a></td>");
				/* <form action='Cart' method='POST'><input type='hidden' name='orderPrice' value='"+oi.getPrice()+"'><input type='hidden' name='orderName' value='"+oi.getName()+"'><input type='submit' name='deleteItemFromCart' value='DeleteItem' /></form> */
				pw.print("</tr>");
				totalOrderPrice = Math.round(totalOrderPrice + oi.getPrice());
				if (oi.isWarrantyIncluded()) totalOrderPrice += oi.getWarrantyPrice();
				discount = discount + oi.getDiscount();
				i++;
			}
			finalOrderTotal = Math.round(totalOrderPrice - discount);
			pw.print("<input type='hidden' name='orderTotal' value='"+finalOrderTotal+"'>");
			pw.print("<tr><th></th><th>Total Cost: $</th><th>"+totalOrderPrice+"</th>");
			pw.print("<tr><th></th><th>Total Discount: $</th><th>"+discount+"</th>");
			pw.print("<tr><th></th><th>Final Cost: $</th><th>"+finalOrderTotal+"</th>");
			pw.print("<tr><td></td><td></td><td><input type='submit' name='CheckOut' value='CheckOut' class='btnbuy' /></td>");
			pw.print("</table></form>");
			/* This code is calling Carousel.java code to implement carousel feature*/
			//pw.print(carousel.carouselfeature(utility));
			pw.print("<div id='content'><div class='post'><h2 class='title meta'><a style='font-size: 24px;'>Other Accessories</a></h2><div id='myCarousel' class='carousel slide' data-ride='carousel'><ol class='carousel-indicators'><li data-target='#myCarousel' data-slide-to='0' class='active'></li><li data-target='#myCarousel' data-slide-to='1'></li><li data-target='#myCarousel' data-slide-to='2'></li></ol><div class='carousel-inner'><div class='item active'><div class='col-md-6' style='background-color: #58acfa;border :1px solid #cfd1d3'><div id='shop_item'><h3>HP Pendrive</h3><strong>10.99$</strong><ul><li id='item'><img src='images/accessories/hppd.jpg' alt=''></li><li><form method='post' action='Cart'><input type='hidden' name='name' value='xbox360_rs'><input type='hidden' name='type' value='accessories'><input type='hidden' name='maker' value='microsoft'><input type='hidden' name='access' value='xbox360'><input type='submit' class='btnbuy' value='Buy Now'></form></li><li><form method='post' action='WriteReview'><input type='hidden' name='name' value='xbox360_rs'><input type='hidden' name='type' value='accessories'><input type='hidden' name='maker' value='microsoft'><input type='hidden' name='access' value='xbox360'><input type='submit' value='WriteReview' class='btnreview'></form></li><li><form method='post' action='ViewReview'><input type='hidden' name='name' value='xbox360_rs'><input type='hidden' name='type' value='accessories'><input type='hidden' name='maker' value='microsoft'><input type='hidden' name='access' value='xbox360'><input type='submit' value='ViewReview' class='btnreview'></form></li></ul></div></div></div><div class='item'><div class='col-md-6' style='background-color: #58acfa;border :1px solid #cfd1d3'><div id='shop_item'><h3>Intex PowerBank</h3><strong>22.99$</strong><ul><li id='item'><img src='images/accessories/ipb.jpg' alt=''></li><li><form method='post' action='Cart'><input type='hidden' name='name' value='xbox360_wc'><input type='hidden' name='type' value='accessories'><input type='hidden' name='maker' value='microsoft'><input type='hidden' name='access' value='xbox360'><input type='submit' class='btnbuy' value='Buy Now'></form></li><li><form method='post' action='WriteReview'><input type='hidden' name='name' value='xbox360_wc'><input type='hidden' name='type' value='accessories'><input type='hidden' name='maker' value='microsoft'><input type='hidden' name='access' value='xbox360'><input type='submit' value='WriteReview' class='btnreview'></form></li><li><form method='post' action='ViewReview'><input type='hidden' name='name' value='xbox360_wc'><input type='hidden' name='type' value='accessories'><input type='hidden' name='maker' value='microsoft'><input type='hidden' name='access' value='xbox360'><input type='submit' value='ViewReview' class='btnreview'></form></li></ul></div></div></div><div class='item'><div class='col-md-6' style='background-color: #58acfa;border :1px solid #cfd1d3'><div id='shop_item'><h3>Charger</h3><strong>89.99$</strong><ul><li id='item'><img src='images/accessories/charger.jpg' alt=''></li><li><form method='post' action='Cart'><input type='hidden' name='name' value='xbox360_kg'><input type='hidden' name='type' value='accessories'><input type='hidden' name='maker' value='microsoft'><input type='hidden' name='access' value='xbox360'><input type='submit' class='btnbuy' value='Buy Now'></form></li><li><form method='post' action='WriteReview'><input type='hidden' name='name' value='xbox360_kg'><input type='hidden' name='type' value='accessories'><input type='hidden' name='maker' value='microsoft'><input type='hidden' name='access' value='xbox360'><input type='submit' value='WriteReview' class='btnreview'></form></li><li><form method='post' action='ViewReview'><input type='hidden' name='name' value='xbox360_kg'><input type='hidden' name='type' value='accessories'><input type='hidden' name='maker' value='microsoft'><input type='hidden' name='access' value='xbox360'><input type='submit' value='ViewReview' class='btnreview'></form></li></ul></div></div></div></div><a class='left carousel-control' href='#myCarousel' data-slide='prev'><span class='glyphicon glyphicon-chevron-left'></span><span class='sr-only'>Previous</span></a><a class='right carousel-control' href='#myCarousel' data-slide='next'><span class='glyphicon glyphicon-chevron-right'></span><span class='sr-only'>Next</span></a></div></div></div>");
		}
		else
		{
			pw.print("<h4 style='color:red'>Your Cart is empty</h4>");
		}
		pw.print("</div></div></div>");		
		utility.printHtml("Footer.html");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		displayCart(request, response);
	}

	private double getWarrantyPrice(OrderItem orderItem) {
		return orderItem.isWarrantyIncluded() ? orderItem.getWarrantyPrice() : 0;
	}
}
