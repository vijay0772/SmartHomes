import java.io.IOException;
import java.io.*;

public class NoOfAvailableProducts implements Serializable
{
    private String productName;
    private String productPrice;
    private String numberOfAvailableProducts;
    private String productDiscount;
    private String manufacturerRebate;

    public NoOfAvailableProducts(String productName, String productPrice, String productDiscount, String numberOfAvailableProducts, String manufacturerRebate)
    {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDiscount = productDiscount;
        this.numberOfAvailableProducts = numberOfAvailableProducts;
        this.manufacturerRebate = manufacturerRebate;
    }

    public String getProductName()
    {
		return productName;
    }

    public void setProductName(String productName)
    {
		this.productName = productName;
    }
    
    public String getProductPrice()
    {
		return productPrice;
    }

    public void setProductPrice(String productPrice)
    {
		this.productPrice = productPrice;
    }
    
    public String getNumberOfAvailableProducts()
    {
		return numberOfAvailableProducts;
    }

    public void setNumberOfAvailableProducts(String numberOfAvailableProducts)
    {
		this.numberOfAvailableProducts = numberOfAvailableProducts;
    }

    public String getProductDiscount()
    {
		return productDiscount;
    }

    public void setProductDiscount(String productDiscount)
    {
		this.productDiscount = productDiscount;
    }

    public String getManufacturerRebate()
    {
		return manufacturerRebate;
    }

    public void setManufacturerRebate(String manufacturerRebate)
    {
		this.manufacturerRebate = manufacturerRebate;
    }
}