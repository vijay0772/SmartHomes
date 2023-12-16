import java.util.*;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Laptop")

/* 
	Laptop class contains class variables name,price,image,retailer,condition,discount.

	Laptop class constructor with Arguments name,price,image,retailer,condition,discount.
	  
	Accessory class contains getters and setters for name,price,image,retailer,condition,discount.

*/

public class Laptop extends HttpServlet
{
	private String id;
	private String name;
	private double price;
	private String image;
	private String retailer;
	private String condition;
	private double discount;
	private int rebate;
	private String description;
	private boolean hasWarranty;
	private double warrantyPrice;
	private String productType;
	private int numberOfAvailableProducts;
	private int numberOfItemsSold;
	
	public Laptop(String name, double price, String image, String retailer, String condition, double discount, int rebate,
				  String description, boolean hasWarranty, double warrantyPrice, String productType,
				  int numberOfAvailableProducts,
				  int numberOfItemsSold)
	{
		this.name=name;
		this.price=price;
		this.image=image;
		this.retailer = retailer;
		this.condition=condition;
		this.discount = discount;
		this.rebate = rebate;
		this.description = description;
		this.hasWarranty = hasWarranty;
		this.warrantyPrice = warrantyPrice;
		this.productType = productType;
		this.numberOfAvailableProducts = numberOfAvailableProducts;
		this.numberOfItemsSold = numberOfItemsSold;
	}
	
	public Laptop()
	{
		
	}
	
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
	public double getPrice()
	{
		return price;
	}
	public void setPrice(double price)
	{
		this.price = price;
	}
	
	public String getImage()
	{
		return image;
	}
	public void setImage(String image)
	{
		this.image = image;
	}
	
	public String getRetailer()
	{
		return retailer;
	}
	public void setRetailer(String retailer)
	{
		this.retailer = retailer;
	}
	
	public String getCondition()
	{
		return condition;
	}

	public void setCondition(String condition)
	{
		this.condition = condition;
	}

	public double getDiscount()
	{
		return discount;
	}

	public void setDiscount(double discount)
	{
		this.discount = discount;
	}

	public int getRebate() {
		return rebate;
	}

	public void setRebate(int rebate) {
		this.rebate = rebate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isHasWarranty() {
		return hasWarranty;
	}

	public void setHasWarranty(boolean hasWarranty) {
		this.hasWarranty = hasWarranty;
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

	public int getNumberOfAvailableProducts() {
		return numberOfAvailableProducts;
	}

	public void setNumberOfAvailableProducts(int numberOfAvailableProducts) {
		this.numberOfAvailableProducts = numberOfAvailableProducts;
	}

	public int getNumberOfItemsSold() {
		return numberOfItemsSold;
	}

	public void setNumberOfItemsSold(int numberOfItemsSold) {
		this.numberOfItemsSold = numberOfItemsSold;
	}
}