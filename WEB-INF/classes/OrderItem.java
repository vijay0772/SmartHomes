import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/OrderItem")

/* 
	OrderItem class contains class variables name,price,image,retailer.

	OrderItem  class has a constructor with Arguments name,price,image,retailer.
	  
	OrderItem  class contains getters and setters for name,price,image,retailer.
*/

public class OrderItem extends HttpServlet {
	private String name;
	private double price;
	private String image;
	private String retailer;
	private boolean warrantyIncluded;
	private double discount;
	private double warrantyPrice;
	private String productType;
	
	public OrderItem(String name, double price, String image, String retailer, boolean warrantyIncluded,
					 double discount, double warrantyPrice, String productType){
		this.name=name;
		this.price=price;
		this.image=image;
		this.retailer = retailer;
		this.warrantyIncluded = warrantyIncluded;
		this.warrantyPrice = warrantyPrice;
		this.discount = discount;
		this.productType = productType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getRetailer() {
		return retailer;
	}

	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}

	public boolean isWarrantyIncluded() {
		return warrantyIncluded;
	}

	public void setWarrantyIncluded(boolean warrantyIncluded) {
		this.warrantyIncluded = warrantyIncluded;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getWarrantyPrice() {
		return warrantyPrice;
	}

	public void setWarrantyPrice(double warrantyPrice) {
		this.warrantyPrice = warrantyPrice;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}
}
