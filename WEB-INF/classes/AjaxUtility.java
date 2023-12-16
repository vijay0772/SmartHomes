import java.io.*;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import java.util.*;
import java.text.*;

import java.sql.*;

import java.io.IOException;
import java.io.*;



public class AjaxUtility {
	StringBuffer sb = new StringBuffer();
	boolean namesAdded = false;
	static Connection conn = null;
    static String message;
	public static String getConnection()
	{

		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vijay","root","root");
			message="Successfull";
			return message;
		}
		catch(SQLException e)
		{
			 message="unsuccessful";
		     return message;
		}
		catch(Exception e)
		{
			 message="unsuccessful";
		     return message;
		}
	}
	
	public  StringBuffer readdata(String searchId)
	{	
		HashMap<String,Product> data;
		data=getData();

		System.out.println("Data: "  + data);
		
 	    Iterator it = data.entrySet().iterator();
		 try {
			 while (it.hasNext())
			 {
				 Map.Entry pi = (Map.Entry)it.next();
				 if(pi!=null)
				 {
					 Product p=(Product)pi.getValue();
					 if (p.getName().toLowerCase().startsWith(searchId))
					 {
						 sb.append("<product>");
						 sb.append("<id>" + p.getId() + "</id>");
						 sb.append("<productName>" + p.getName() + "</productName>");
						 sb.append("</product>");
					 }
				 }
			 }
		 } catch (Exception e) {
			 System.out.println("Error: " + e.getMessage());
		 }
	   
	   return sb;
	}
	
	public static HashMap<String,Product> getData()
	{
		HashMap<String,Product> hm=new HashMap<String,Product>();
		try
		{
			getConnection();
			
		    String selectproduct="select * from  productDetails";
		    PreparedStatement pst = conn.prepareStatement(selectproduct);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{	Product p = new Product(
					rs.getString("productId"),
					rs.getString("productName"),
					rs.getDouble("productPrice"),
					rs.getString("productImage"),
					rs.getString("productManufacturer"),
					rs.getString("productCondition"),
					rs.getString("productType"),
					rs.getDouble("productDiscount"),
					rs.getInt("manufacturerRebate"),
					rs.getString("productDescription"),
					rs.getBoolean("productHasWarranty"),
					rs.getDouble("productWarranty"),
					rs.getInt("numberOfAvailableProducts"),
					rs.getInt("numberOfItemsSold")
			);
				hm.put(rs.getString("productId"), p);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		}
		return hm;			
	}
	public static void storeData(HashMap<String,Product> productdata)
	{
		try
		{
		
			getConnection();
				
			String insertIntoProductQuery = "INSERT INTO product(productId,productName,image,retailer,productCondition,productPrice,productType,discount) "
			+ "VALUES (?,?,?,?,?,?,?,?);";	
			for(Map.Entry<String, Product> entry : productdata.entrySet())
			{	

				PreparedStatement pst = conn.prepareStatement(insertIntoProductQuery);
				//set the parameter for each column and execute the prepared statement
				pst.setString(1,entry.getValue().getId());
				pst.setString(2,entry.getValue().getName());
				pst.setString(3,entry.getValue().getImage());
				pst.setString(4,entry.getValue().getRetailer());
				pst.setString(5,entry.getValue().getCondition());
				pst.setDouble(6,entry.getValue().getPrice());
				pst.setString(7,entry.getValue().getType());
				pst.setDouble(8,entry.getValue().getDiscount());
				pst.execute();
			}
		}
		catch(Exception e)
		{	
	
		}		
	}

}
