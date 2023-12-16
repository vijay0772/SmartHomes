import java.util.*;


public class Product {
	private String id;
	private String name;
	private double price;
	private String image;
	private String retailer;
	private String condition;
	private String type;
	private double discount;
	private int rebate;
	private String description;
	private boolean hasWarranty;
	private double warrantyPrice;
	private int numberOfAvailableProducts;
	private int numberOfItemsSold;

	HashMap<String,String> accessories;

	public Product(
			String id,
			String name,
			double price,
			String image,
			String retailer,
			String condition,
			String type,
			double discount,
			int rebate,
			String description,
			boolean hasWarranty,
			double warrantyPrice,
			int numberOfAvailableProducts,
			int numberOfItemsSold
	){
		this.id=id;
		this.name=name;
		this.price=price;
		this.image=image;
		this.retailer = retailer;
		this.condition=condition;
		this.type=type;
		this.discount = discount;
		this.rebate = rebate;
		this.description = description;
		this.hasWarranty = hasWarranty;
		this.warrantyPrice = warrantyPrice;
		this.numberOfAvailableProducts = numberOfAvailableProducts;
		this.numberOfItemsSold = numberOfItemsSold;

		this.accessories=new HashMap<String,String>();
	}
	
    HashMap<String,String> getAccessories() {
		return accessories;
		}

	public Product(){
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type =type;
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

	public void setAccessories( HashMap<String,String> accessories) {
		this.accessories = accessories;
	}
	
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
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
