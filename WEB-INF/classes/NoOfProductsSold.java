import java.io.IOException;
import java.io.*;

public class NoOfProductsSold implements Serializable
{
    private String productName;
    private String productPrice;
    private String noOfProductsSold;
    private String productTotalSales;

    public NoOfProductsSold(String productName, String productPrice, String noOfProductsSold, String productTotalSales)
    {
        this.productName = productName;
        this.productPrice = productPrice;
        this.noOfProductsSold = noOfProductsSold;
        this.productTotalSales = productTotalSales;
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

    public String getNoOfProductsSold()
    {
		return noOfProductsSold;
    }

    public void setNoOfProductsSold(String noOfProductsSold)
    {
		this.noOfProductsSold = noOfProductsSold;
    }
    
    public String getProductTotalSales()
    {
		return productTotalSales;
    }

    public void setProductTotalSales(String productTotalSales)
    {
		this.productTotalSales = productTotalSales;
    }
}