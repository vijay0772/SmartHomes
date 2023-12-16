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

@WebServlet("/OrderConfirmation")
public class OrderConfirmation extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        Utilities utility = new Utilities(request, pw);

        int orderId = Integer.parseInt(request.getParameter("orderId"));
        String userName = request.getParameter("userName");
        String customerName=request.getParameter("customerName");
        String streetAddress=request.getParameter("streetAddress");
        String cityAddress=request.getParameter("cityAddress");
        String stateAddress=request.getParameter("stateAddress");
        String zipcode=request.getParameter("zipcode");
        int creditCardNo = Integer.parseInt(request.getParameter("creditCardNo"));
        String deliveryMethod = request.getParameter("deliveryMethod");
        double shippingCost = Double.parseDouble(request.getParameter("shippingCost"));
        double orderTotal = Double.parseDouble(request.getParameter("orderTotal"));

        String pickupStoreName=request.getParameter("pickupStoreName");
        String orderDate=request.getParameter("orderDate");
        String deliveryDate=request.getParameter("deliveryDate");
        String maxOrderCancellationDate = request.getParameter("maxOrderCancellationDate");
        String maxPickupDate = request.getParameter("maxPickupDate");

        String customerAddress = streetAddress + ", " + cityAddress + ", " + stateAddress + " - " + zipcode;
        /*Transaction transaction = new Transaction(
                orderId,
                userName,
                customerName,
                streetAddress,
                cityAddress,
                stateAddress,
                zipcode,
                creditCardNo,
                deliveryMethod,
                pickupStoreName,
                orderDate,
                null,
                maxOrderCancellationDate,
                maxPickupDate
        );*/

        for (OrderItem oi : utility.getCustomerOrders()) {

            //set the parameter for each column and execute the prepared statement\
            CustomerOrder order = new CustomerOrder(
                    orderId,
                    userName,
                    customerName,
                    customerAddress,
                    creditCardNo,
                    orderDate,
                    !deliveryMethod.equals("In Store Pickup") ? deliveryDate : null,
                    oi.getName(),
                    oi.getProductType(),
                    1,
                    oi.getPrice(),
                    !deliveryMethod.equals("In Store Pickup") ? shippingCost : 0,
                    oi.getDiscount(),
                    orderTotal + shippingCost,
                    oi.isWarrantyIncluded(),
                    oi.getWarrantyPrice(),
                    deliveryMethod,
                    deliveryMethod.equals("In Store Pickup") ? maxPickupDate : null,
                    deliveryMethod.equals("In Store Pickup") ? pickupStoreName: null,
                    maxOrderCancellationDate
            );

            System.out.println("customer order: " + order.toString());

            utility.storePayment(order);
        }

        Customer customer = new Customer(
                customerName,
                streetAddress,
                cityAddress,
                stateAddress,
                zipcode
        );
        utility.storeCustomer(customer);

        //remove the order details from cart after processing
        OrdersHashMap.orders.remove(utility.username());

        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");
        pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
        pw.print("<a style='font-size: 24px;'>Order Confirmation</a>");
        pw.print("</h2><div class='entry'>");
        pw.print("<h3>Your Order");
        pw.print("&nbsp&nbsp");
        pw.print("is stored ");
        pw.print("<br>Your Order No is "+(orderId));
        pw.print("<br>Your Order Date is "+(orderDate));
        if (deliveryMethod.equals("Home Delivery")) {
            pw.print("<br>Delivery Date is " + (deliveryDate) + " to " + customerAddress);
        } else {
            pw.print("<br>Pickup Date is before " + (maxPickupDate) + " from " + pickupStoreName);
        }
        pw.print("<br>You can cancel your order before "+(maxOrderCancellationDate));
        pw.print("</h3>");
        pw.print("</div></div></div>");
        utility.printHtml("Footer.html");

//        MySqlDataStoreUtilities.insertTransaction(transaction);
    }
}
