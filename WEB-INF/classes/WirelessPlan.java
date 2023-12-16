import java.util.*;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/WirelessPlan")

/* 
	WirelessPlan class contains class variables name,price,image,retailer,condition,discount.

	WirelessPlan class constructor with Arguments name,price,image,retailer,condition,discount.
	  
	Accessory class contains getters and setters for name,price,image,retailer,condition,discount.

*/

public class WirelessPlan extends HttpServlet
{
	private String id;
	private String name;
	private double price;
	private String image;
	private String retailer;
	private String condition;
	private double discount;
	
	public WirelessPlan(String name, double price, String image, String retailer,String condition,double discount)
	{
		this.name=name;
		this.price=price;
		this.image=image;
		this.retailer = retailer;
		this.condition=condition;
		this.discount = discount;
	}
	
	public WirelessPlan()
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
}