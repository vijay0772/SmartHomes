import java.io.IOException;
import java.io.*;

public class TotalSalesDaily implements Serializable
{
    private String orderDate;
    private String totalDailySales;
    private String productsListDescription;

    public TotalSalesDaily(String orderDate, String totalDailySales, String productsListDescription)
    {
        this.orderDate = orderDate;
        this.totalDailySales = totalDailySales;
        this.productsListDescription = productsListDescription;
    }

    public String getOrderDate()
    {
		return orderDate;
    }

    public void setOrderDate(String orderDate)
    {
		this.orderDate = orderDate;
    }
    
    public String getTotalDailySales()
    {
		return totalDailySales;
    }

    public void setTotalDailySales(String totalDailySales)
    {
		this.totalDailySales = totalDailySales;
    }

    public String getProductsListDesciption()
    {
		return productsListDescription;
    }

    public void setProductsListDesciption(String productsListDesciption)
    {
		this.productsListDescription = productsListDesciption;
    }
}