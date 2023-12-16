import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@WebServlet("/UpdateOrder")

public class UpdateOrder extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        Utilities utility = new Utilities(request, pw);

        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");
        pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
        pw.print("<a style='font-size: 24px;'>Order</a>");
        pw.print("</h2><div class='entry'>");
        pw.print("<form action='UpdateOrder' method='post'>");

        if (request.getParameter("orderName") != null) {
            String orderName = request.getParameter("orderName");
            System.out.println("OrderName: " + orderName);
            int orderId = 0;
            orderId = Integer.parseInt(request.getParameter("orderId"));
            System.out.println("orderId: " + orderId);
            ArrayList<CustomerOrder> ListOrderPayment = new ArrayList<>();
            HashMap<Integer, ArrayList<CustomerOrder>> customerOrder = new HashMap<>();
            //get the order from file
            try {
                customerOrder = MySqlDataStoreUtilities.selectOrder();
                System.out.println("Customer Order: " + customerOrder.toString());
            } catch (Exception e) {
                System.out.println("error: " + e.getMessage());
            }
            //get the exact order with same ordername and add it into cancel list to remove it later
            for (CustomerOrder oi : customerOrder.get(orderId)) {
                String[] address = oi.getCustomerAddress().split(", ");
                String street = address[0];
                String city = address[1];
                String[] stateZip = address[2].split(" - ");
                String state = stateZip[0];
                String zipcode = stateZip[1];

                if (oi.getProductName().equals(orderName)) {
                    pw.print("<table style='width: 100%;' class='gridtable'>" +
                            "<tr>" +
                            "<td>Customer Name</td>" +
                            "<td><input type='text' name='customerName' value='" + oi.getCustomerName() + "' /></td>" +
                            "</tr>" +
                            "<tr>" +
                            "<td>Street</td>" +
                            "<td><input type='text' name='street' value='" + street + "' /></td>" +
                            "</tr>" +
                            "<tr>" +
                            "<td>City</td>" +
                            "<td><input type='text' name='city' value='" + city + "' /></td>" +
                            "</tr>" +
                            "<tr>" +
                            "<td>State</td>" +
                            "<td><input type='text' name='state' value='" + state + "' /></td>" +
                            "</tr>" +
                            "<tr>" +
                            "<td>Zipcode</td>" +
                            "<td><input type='text' name='zipcode' value='" + zipcode + "' /></td>" +
                            "</tr>" +
                            "<tr>" +
                            "<td>Credit Card No</td>" +
                            "<td><input type='text' name='creditCardNo' value='" + oi.getCreditCardNo() + "' /></td>" +
                            "</tr>" +
                            "<tr>" +
                            "<td>Delivery Method</td>" +
                            "<td><select name='deliveryMethod'>" +
                            "<option value='Home Delivery' " + isSelected("Home Delivery", oi.getDeliveryMethod()) + ">Home Delivery</option>" +
                            "<option value='In Store Pickup' " + isSelected("In Store Pickup", oi.getDeliveryMethod()) + ">In Store Pickup</option>" +
                            "</select>" +
                            "</td>" +
                            "</tr>" +
                            "<tr>" +
                            "<td>Store Pickup Location</td>" +
                            "<td><select name='pickupStoreName'>" +
                            "<option value=''></option>");
                    for (Store store : MySqlDataStoreUtilities.getAllStores()) {
                        pw.print("<option value='" + store.getStoreId() + "' " + isSelected(store.getStoreId(), oi.getPickupStoreName()) + ">" + store.getStoreId() + "</option>");
                    }
                    pw.print("</select></td></tr>");
                    pw.print("<tr><td colspan='2'><input type='submit' name='button' value='Update Order' class='btnbuy'></td></tr>");

                    pw.print("<input type='hidden' name='orderId' value='" + oi.getOrderId() + "'/>");
                    pw.print("<input type='hidden' name='productName' value='" + oi.getProductName() + "'/>");
                    pw.print("<input type='hidden' name='userName' value='" + oi.getUserName() + "'/>");
                    pw.print("</table>");
                }
            }
            pw.print("</form></div></div></div>");
            utility.printHtml("Footer.html");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        Utilities utility = new Utilities(request, pw);

        String customerName = request.getParameter("customerName");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zipcode = request.getParameter("zipcode");
        int creditCardNo = Integer.parseInt(request.getParameter("creditCardNo"));
        String deliveryMethod = request.getParameter("deliveryMethod");
        String pickupStoreName = request.getParameter("pickupStoreName");
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        String productName = request.getParameter("productName");
        String userName = request.getParameter("userName");

        String customerAddress = street + ", " + city + ", " + state + " - " + zipcode;

        MySqlDataStoreUtilities.updateOrder(
                customerName,
                customerAddress,
                creditCardNo,
                deliveryMethod,
                pickupStoreName,
                orderId,
                productName,
                userName
        );

        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");
        pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
        pw.print("<a style='font-size: 24px;'>Order</a>");
        pw.print("</h2><div class='entry'>");
        pw.print("<h3>Order Updated</h3>");
        pw.print("</div></div></div>");
        utility.printHtml("Footer.html");
    }

    private String isSelected(String type, String check) {
        return type.equals(check) ? "selected" : "";
    }
}
