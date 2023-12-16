import java.sql.*;
import java.util.*;

public class MySqlDataStoreUtilities
{
    static Connection conn = null;

    public static void getConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vijay","root","root");
        }
        catch(Exception e) {
            System.out.println("Unable to connect to DB");
        }
    }


    public static void deleteOrder(int orderId,String orderName) {
        try {
            getConnection();
            String deleteOrderQuery ="Delete from customerOrders where orderId=? and productName=?";
            PreparedStatement pst = conn.prepareStatement(deleteOrderQuery);
            pst.setInt(1,orderId);
            pst.setString(2,orderName);
            pst.executeUpdate();
        }
        catch(Exception e) {

        }
    }

    public static void insertCustomerOrder(CustomerOrder order) {
        try {
            getConnection();
            String insertCustomerOrderQuery = "Insert into customerOrders (" +
                    "orderId," +
                    "userName," +
                    "customerName," +
                    "customerAddress," +
                    "creditCardNo," +
                    "purchaseDate," +
                    "shipDate," +
                    "productName," +
                    "productType," +
                    "productQuantity," +
                    "productPrice," +
                    "shippingCost," +
                    "discountPrice," +
                    "orderTotal," +
                    "isWarrantyIncluded," +
                    "warrantyPrice," +
                    "deliveryMethod," +
                    "maxPickupDate," +
                    "pickupStoreName," +
                    "maxCancellationDate" +
                    ") "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

            PreparedStatement pst = conn.prepareStatement(insertCustomerOrderQuery);
            //set the parameter for each column and execute the prepared statement
            pst.setInt(1,order.getOrderId());
            pst.setString(2,order.getUserName());
            pst.setString(3,order.getCustomerName());
            pst.setString(4,order.getCustomerAddress());
            pst.setInt(5,order.getCreditCardNo());
            pst.setString(6,order.getPurchaseDate());
            pst.setString(7,order.getShipDate());
            pst.setString(8,order.getProductName());
            pst.setString(9,order.getProductType());
            pst.setInt(10,order.getProductQuantity());
            pst.setDouble(11,order.getProductPrice());
            pst.setDouble(12,order.getShippingCost());
            pst.setDouble(13,order.getDiscountPrice());
            pst.setDouble(14,order.getOrderTotal());
            pst.setBoolean(15,order.isWarrantyIncluded());
            pst.setDouble(16,order.getWarrantyPrice());
            pst.setString(17,order.getDeliveryMethod());
            pst.setString(18,order.getMaxPickupDate());
            pst.setString(19,order.getPickupStoreName());
            pst.setString(20,order.getMaxCancellationDate());
            System.out.println("insert customer order Query: " + pst.toString());

            pst.executeUpdate();
        }
        catch(Exception e) {
            System.out.println("Unable to insert customer order: " + e.getMessage() );
        }
    }

    public static HashMap<Integer, ArrayList<CustomerOrder>> selectOrder() {

        HashMap<Integer, ArrayList<CustomerOrder>> customerOrders = new HashMap<>();

        try {
            getConnection();
            //select the table 
            String selectOrderQuery ="select * from customerOrders";
            PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
            ResultSet rs = pst.executeQuery();
            ArrayList<CustomerOrder> orderList = new ArrayList<>();
            while(rs.next()) {
                if(!customerOrders.containsKey(rs.getInt("orderId"))) {
                    ArrayList<CustomerOrder> arr = new ArrayList<>();
                    customerOrders.put(rs.getInt("orderId"), arr);
                }
                ArrayList<CustomerOrder> listCustomerOrders = customerOrders.get(rs.getInt("orderId"));
                System.out.println("data is: " + rs.getInt("orderId") + customerOrders.get(rs.getInt("orderId")));

                //add to order payment hashmap
                CustomerOrder order = new CustomerOrder(
                        rs.getInt("orderId"),
                        rs.getString("userName"),
                        rs.getString("customerName"),
                        rs.getString("customerAddress"),
                        rs.getInt("creditCardNo"),
                        rs.getString("purchaseDate"),
                        rs.getString("shipDate"),
                        rs.getString("productName"),
                        rs.getString("productType"),
                        rs.getInt("productQuantity"),
                        rs.getDouble("productPrice"),
                        rs.getDouble("shippingCost"),
                        rs.getDouble("discountPrice"),
                        rs.getDouble("orderTotal"),
                        rs.getBoolean("isWarrantyIncluded"),
                        rs.getDouble("warrantyPrice"),
                        rs.getString("deliveryMethod"),
                        rs.getString("maxPickupDate"),
                        rs.getString("pickupStoreName"),
                        rs.getString("maxCancellationDate")
                );
                listCustomerOrders.add(order);
            }
        }
        catch(Exception e) {
            System.out.println("Unable to perform select customer order : " + e.getMessage());
        }
        return customerOrders;
    }


    public static void registerUser(String username, String password, String repassword, String usertype) {
        try {

            getConnection();
            String registerUserQuery = "Insert into registration(" +
                    "username," +
                    "password," +
                    "repassword," +
                    "usertype) "
                    + "VALUES (?,?,?,?);";

            PreparedStatement pst = conn.prepareStatement(registerUserQuery);
            pst.setString(1,username);
            pst.setString(2,password);
            pst.setString(3,repassword);
            pst.setString(4,usertype);
            pst.execute();
        }
        catch(Exception e) {

        }
    }

    public static HashMap<String, User> selectUser()
    {
        HashMap<String,User> hm=new HashMap<>();
        try {
            getConnection();
            Statement stmt = conn.createStatement();
            String selectCustomerQuery = "Select * from  registration";
            ResultSet rs = stmt.executeQuery(selectCustomerQuery);
            while(rs.next()) {
                User user = new User(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("usertype")
                );
                hm.put(rs.getString("username"), user);
            }
        }
        catch(Exception e) {
        }
        return hm;
    }

    public static HashMap<String, PetTracker> getPetTracker() {
        HashMap<String, PetTracker> hm = new HashMap<>();
        try {
            getConnection();

            String selectWirelessPlan="Select * from  productDetails where productType=?";
            PreparedStatement pst = conn.prepareStatement(selectWirelessPlan);
            pst.setString(1,"Pet Tracker");
            ResultSet rs = pst.executeQuery();

            while(rs.next())
            {
                PetTracker petTracker = new PetTracker(
                        rs.getString("productName"),
                        rs.getDouble("productPrice"),
                        rs.getString("productImage"),
                        rs.getString("productManufacturer"),
                        rs.getString("productCondition"),
                        rs.getDouble("productDiscount"),
                        rs.getInt("manufacturerRebate"),
                        rs.getString("productDescription"),
                        rs.getBoolean("productHasWarranty"),
                        rs.getDouble("productWarranty"),
                        rs.getString("productType"),
                        rs.getInt("numberOfAvailableProducts"),
                        rs.getInt("numberOfItemsSold")
                );
                hm.put(rs.getString("productId"), petTracker);
                petTracker.setId(rs.getString("productId"));
                
                /*
                try
                {
                    String selectaccessory = "Select * from Product_accessories where productName=?";
                    PreparedStatement pstacc = conn.prepareStatement(selectaccessory);
                    pstacc.setString(1,rs.getString("Id"));
                    ResultSet rsacc = pstacc.executeQuery();
                    
                    HashMap<String,String> acchashmap = new HashMap<String,String>();
                    while(rsacc.next())
                    {    
                        if (rsacc.getString("accessoriesName") != null)
                        {
                        
                        acchashmap.put(rsacc.getString("accessoriesName"),rsacc.getString("accessoriesName"));
                        con.setAccessories(acchashmap);
                        }
                    }					
                }
                catch(Exception e)
                {
                        e.printStackTrace();
                }
                */
            }
        }
        catch(Exception e) {

        }
        return hm;
    }

    public static HashMap<String, VirtualReality> getVirtualReality()
    {
        HashMap<String,VirtualReality> hm = new HashMap<>();
        try {
            getConnection();

            String selectVirtualReality="select * from  productDetails where productType=?";
            PreparedStatement pst = conn.prepareStatement(selectVirtualReality);
            pst.setString(1,"Virtual Reality");
            ResultSet rs = pst.executeQuery();

            while(rs.next())
            {
                VirtualReality virtualReality = new VirtualReality(
                        rs.getString("productName"),
                        rs.getDouble("productPrice"),
                        rs.getString("productImage"),
                        rs.getString("productManufacturer"),
                        rs.getString("productCondition"),
                        rs.getDouble("productDiscount"),
                        rs.getInt("manufacturerRebate"),
                        rs.getString("productDescription"),
                        rs.getBoolean("productHasWarranty"),
                        rs.getDouble("productWarranty"),
                        rs.getString("productType"),
                        rs.getInt("numberOfAvailableProducts"),
                        rs.getInt("numberOfItemsSold")
                );
                hm.put(rs.getString("productId"), virtualReality);
                virtualReality.setId(rs.getString("productId"));
            }
        }
        catch(Exception e) {

        }
        return hm;
    }

    public static HashMap<String, FitnessWatch> getFitnessWatches()
    {
        HashMap<String, FitnessWatch> hm = new HashMap<>();
        try
        {
            getConnection();

            String selectFitnessWatch="select * from  Productdetails where ProductType=?";
            PreparedStatement pst = conn.prepareStatement(selectFitnessWatch);
            pst.setString(1,"Fitness Watch");
            ResultSet rs = pst.executeQuery();

            while(rs.next())
            {
                FitnessWatch fitnessWatch = new FitnessWatch(
                        rs.getString("productName"),
                        rs.getDouble("productPrice"),
                        rs.getString("productImage"),
                        rs.getString("productManufacturer"),
                        rs.getString("productCondition"),
                        rs.getDouble("productDiscount"),
                        rs.getInt("manufacturerRebate"),
                        rs.getString("productDescription"),
                        rs.getBoolean("productHasWarranty"),
                        rs.getDouble("productWarranty"),
                        rs.getString("productType"),
                        rs.getInt("numberOfAvailableProducts"),
                        rs.getInt("numberOfItemsSold")
                );
                hm.put(rs.getString("productId"), fitnessWatch);
                fitnessWatch.setId(rs.getString("productId"));
            }
        }
        catch(Exception e) {

        }
        return hm;
    }

    public static HashMap<String,Headphone> getHeadphones() {
        HashMap<String,Headphone> hm=new HashMap<>();
        try {
            getConnection();

            String selectHeadphone="select * from  productDetails where productType=?";
            PreparedStatement pst = conn.prepareStatement(selectHeadphone);
            pst.setString(1,"Headphone");
            ResultSet rs = pst.executeQuery();

            while(rs.next())
            {
                Headphone headphone = new Headphone(
                        rs.getString("productName"),
                        rs.getDouble("productPrice"),
                        rs.getString("productImage"),
                        rs.getString("productManufacturer"),
                        rs.getString("productCondition"),
                        rs.getDouble("productDiscount"),
                        rs.getInt("manufacturerRebate"),
                        rs.getString("productDescription"),
                        rs.getBoolean("productHasWarranty"),
                        rs.getDouble("productWarranty"),
                        rs.getString("productType"),
                        rs.getInt("numberOfAvailableProducts"),
                        rs.getInt("numberOfItemsSold")
                );
                hm.put(rs.getString("productId"), headphone);
                headphone.setId(rs.getString("productId"));
            }
        }
        catch(Exception e) {

        }
        return hm;
    }

    public static HashMap<String,Laptop> getLaptops()
    {
        HashMap<String,Laptop> hm=new HashMap<>();
        try {
            getConnection();

            String selectLaptop="select * from  productDetails where productType=?";
            PreparedStatement pst = conn.prepareStatement(selectLaptop);
            pst.setString(1,"Laptop");
            ResultSet rs = pst.executeQuery();

            while(rs.next())
            {
                Laptop laptop = new Laptop(
                        rs.getString("productName"),
                        rs.getDouble("productPrice"),
                        rs.getString("productImage"),
                        rs.getString("productManufacturer"),
                        rs.getString("productCondition"),
                        rs.getDouble("productDiscount"),
                        rs.getInt("manufacturerRebate"),
                        rs.getString("productDescription"),
                        rs.getBoolean("productHasWarranty"),
                        rs.getDouble("productWarranty"),
                        rs.getString("productType"),
                        rs.getInt("numberOfAvailableProducts"),
                        rs.getInt("numberOfItemsSold")
                );
                hm.put(rs.getString("productId"), laptop);
                laptop.setId(rs.getString("productId"));
            }
        }
        catch(Exception e) {

        }
        return hm;
    }

    public static HashMap<String,Phone> getPhones()
    {
        HashMap<String,Phone> hm = new HashMap<>();
        try {
            getConnection();

            String selectPhone="select * from  productDetails where productType=?";
            PreparedStatement pst = conn.prepareStatement(selectPhone);
            pst.setString(1,"Phone");
            ResultSet rs = pst.executeQuery();

            while(rs.next())
            {
                Phone phone = new Phone(
                        rs.getString("productName"),
                        rs.getDouble("productPrice"),
                        rs.getString("productImage"),
                        rs.getString("productManufacturer"),
                        rs.getString("productCondition"),
                        rs.getDouble("productDiscount"),
                        rs.getInt("manufacturerRebate"),
                        rs.getString("productDescription"),
                        rs.getBoolean("productHasWarranty"),
                        rs.getDouble("productWarranty"),
                        rs.getString("productType"),
                        rs.getInt("numberOfAvailableProducts"),
                        rs.getInt("numberOfItemsSold")
                );
                hm.put(rs.getString("productId"), phone);
                phone.setId(rs.getString("productId"));
            }
        }
        catch(Exception e)
        {

        }
        return hm;
    }

    public static HashMap<String,SmartWatch> getSmartWatches()
    {
        HashMap<String,SmartWatch> hm=new HashMap<>();
        try {
            getConnection();

            String selectSmartWatch="select * from  productDetails where productType=?";
            PreparedStatement pst = conn.prepareStatement(selectSmartWatch);
            pst.setString(1,"Smart Watch");
            ResultSet rs = pst.executeQuery();

            while(rs.next())
            {
                SmartWatch smartWatch = new SmartWatch(
                        rs.getString("productName"),
                        rs.getDouble("productPrice"),
                        rs.getString("productImage"),
                        rs.getString("productManufacturer"),
                        rs.getString("productCondition"),
                        rs.getDouble("productDiscount"),
                        rs.getInt("manufacturerRebate"),
                        rs.getString("productDescription"),
                        rs.getBoolean("productHasWarranty"),
                        rs.getDouble("productWarranty"),
                        rs.getString("productType"),
                        rs.getInt("numberOfAvailableProducts"),
                        rs.getInt("numberOfItemsSold")
                );
                hm.put(rs.getString("productId"), smartWatch);
                smartWatch.setId(rs.getString("productId"));
            }
        }
        catch(Exception e) {

        }
        return hm;
    }

    public static HashMap<String,VoiceAssistant> getVoiceAssistants()
    {
        HashMap<String,VoiceAssistant> hm=new HashMap<>();
        try {
            getConnection();

            String selectVoiceAssistant="select * from  productDetails where productType=?";
            PreparedStatement pst = conn.prepareStatement(selectVoiceAssistant);
            pst.setString(1,"Voice Assistant");
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                VoiceAssistant voiceAssistant = new VoiceAssistant(
                        rs.getString("productName"),
                        rs.getDouble("productPrice"),
                        rs.getString("productImage"),
                        rs.getString("productManufacturer"),
                        rs.getString("productCondition"),
                        rs.getDouble("productDiscount"),
                        rs.getInt("manufacturerRebate"),
                        rs.getString("productDescription"),
                        rs.getBoolean("productHasWarranty"),
                        rs.getDouble("productWarranty"),
                        rs.getString("productType"),
                        rs.getInt("numberOfAvailableProducts"),
                        rs.getInt("numberOfItemsSold")
                );
                hm.put(rs.getString("productId"), voiceAssistant);
                voiceAssistant.setId(rs.getString("productId"));
            }
        }
        catch(Exception e) {

        }
        return hm;
    }

    public static String addProduct(
            String productType,
            String productId,
            String productName,
            double productPrice,
            String productImage,
            String productManufacturer,
            String productCondition,
            double productDiscount,
            int productRebate,
            String productDescription,
            boolean productHasWarranty,
            double productWarranty,
            int numberOfAvailableProducts,
            int numberOfItemsSold,
            String prod
    ) {
        String msg = "Product is added successfully";
        try
        {
            getConnection();
            String addProductQurey = "Insert into productDetails(" +
                    "productId," +
                    "productType," +
                    "productName," +
                    "productPrice," +
                    "productImage," +
                    "productManufacturer," +
                    "productCondition," +
                    "productDiscount," +
                    "manufacturerRebate," +
                    "productDescription," +
                    "productHasWarranty," +
                    "productWarranty," +
                    "numberOfAvailableProducts," +
                    "numberOfItemsSold" +
                    ")" +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

            String name = productType;

            PreparedStatement pst = conn.prepareStatement(addProductQurey);
            pst.setString(1,productId);
            pst.setString(2,productType);
            pst.setString(3,productName);
            pst.setDouble(4,productPrice);
            pst.setString(5,productImage);
            pst.setString(6,productManufacturer);
            pst.setString(7,productCondition);
            pst.setDouble(8,productDiscount);
            pst.setInt(9,productRebate);
            pst.setString(10,productDescription);
            pst.setBoolean(11,productHasWarranty);
            pst.setDouble(12,productWarranty);
            pst.setInt(13,numberOfAvailableProducts);
            pst.setInt(14,numberOfItemsSold);


            pst.executeUpdate();
            try {
                if (!prod.isEmpty())
                {
                    String addProductAccessoriesQuery =  "Insert INTO  productAccessories(" +
                            "productName," +
                            "accessoriesName)" +
                            "VALUES (?,?);";
                    PreparedStatement pst1 = conn.prepareStatement(addProductAccessoriesQuery);
                    pst1.setString(1,prod);
                    pst1.setString(2,productId);
                    pst1.executeUpdate();
                }
            }
            catch(Exception e)
            {
                msg = "Erro while adding the product";
                e.printStackTrace();
            }
        }
        catch(Exception e)
        {
            msg = "Erro while adding the product";
            e.printStackTrace();

        }
        return msg;
    }
    public static String updateProduct(
            String productType,
            String productId,
            String productName,
            double productPrice,
            String productImage,
            String productManufacturer,
            String productCondition,
            double productDiscount,
            int productRebate,
            String productDescription,
            boolean productHasWarranty,
            double productWarranty,
            int numberOfAvailableProducts,
            int numberOfItemsSold
    ) {
        String msg = "Product is updated successfully";
        try
        {
            getConnection();
            String updateProductQurey = "Update productDetails SET " +
                    "productType=?," +
                    "productName=?," +
                    "productPrice=?," +
                    "productImage=?," +
                    "productManufacturer=?," +
                    "productCondition=?," +
                    "productDiscount=?, " +
                    "manufacturerRebate=?, " +
                    "productDescription=?, " +
                    "productHasWarranty=?," +
                    "productWarranty=?," +
                    "numberOfAvailableProducts=?," +
                    "numberOfItemsSold=? " +
                    "where productId =?;" ;
            PreparedStatement pst = conn.prepareStatement(updateProductQurey);

            pst.setString(1,productType);
            pst.setString(2,productName);
            pst.setDouble(3,productPrice);
            pst.setString(4,productImage);
            pst.setString(5,productManufacturer);
            pst.setString(6,productCondition);
            pst.setDouble(7,productDiscount);
            pst.setInt(8,productRebate);
            pst.setString(9,productDescription);
            pst.setBoolean(10,productHasWarranty);
            pst.setDouble(11,productWarranty);
            pst.setInt(12,numberOfAvailableProducts);
            pst.setInt(13,numberOfItemsSold);
            pst.setString(14,productId);

            System.out.println("pst : "  + pst.toString());

            pst.executeUpdate();
        }
        catch(Exception e) {
            msg = "Product cannot be updated";
            e.printStackTrace();
        }
        return msg;
    }

    public static String deleteProduct(String productId) {
        String msg = "Product is deleted successfully";
        try {
            getConnection();
            String deleteProductQuery ="Delete from productDetails where productId=?";
            PreparedStatement pst = conn.prepareStatement(deleteProductQuery);
            pst.setString(1,productId);
            pst.executeUpdate();
        }
        catch(Exception e) {
            msg = "Proudct cannot be deleted";
        }
        return msg;
    }

    public static List<Store> getAllStores() {
        List<Store> allStores = new ArrayList<>();
        try {
            getConnection();

            String getAllStoresQuery = "Select * from store";
            PreparedStatement pst = conn.prepareStatement(getAllStoresQuery);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Store store = new Store(
                        rs.getString("storeId"),
                        rs.getString("street"),
                        rs.getString("city"),
                        rs.getString("state"),
                        rs.getString("zipcode")
                );

                allStores.add(store);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allStores;
    }

    public static List<String> getAllProducts() {
        List<String> allProducts = new ArrayList<>();

        try {
            getConnection();

            String getAllProductsQuery = "Select * from productDetails";
            PreparedStatement pst = conn.prepareStatement(getAllProductsQuery);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                allProducts.add(rs.getString("productName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error getting all products: " + e.getMessage());
        }

        return allProducts;
    }

    public static void insertCustomerInfo(Customer customer) {
        try {
            getConnection();

            String addProductQurey = "Insert into customer(" +
                    "customerName," +
                    "street," +
                    "city," +
                    "state," +
                    "zipcode" +
                    ")" +
                    "VALUES (?,?,?,?,?);";

            PreparedStatement pst = conn.prepareStatement(addProductQurey);
            pst.setString(1, customer.getCustomerName());
            pst.setString(2, customer.getStreet());
            pst.setString(3, customer.getCity());
            pst.setString(4, customer.getState());
            pst.setString(5, customer.getZipcode());

            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error inserting customer info: " + e.getMessage());
        }
    }

    public static void insertTransaction(Transaction transaction) {
        try {
            getConnection();

            String insertTransactionQurey = "Insert into transactions(" +
                    "orderId, " +
                    "customerName," +
                    "streetAddress," +
                    "cityAddress," +
                    "stateAddress," +
                    "zipcode," +
                    "creditCardNo," +
                    "deliveryMethod," +
                    "pickupStoreName," +
                    "orderDate," +
                    "deliveryDate," +
                    "maxOrderCancellationDate," +
                    "maxPickupDate," +
                    "userName" +
                    ")" +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

            PreparedStatement pst = conn.prepareStatement(insertTransactionQurey);
            pst.setInt(1, transaction.getOrderId());
            pst.setString(2, transaction.getCustomerName());
            pst.setString(3, transaction.getStreetAddress());
            pst.setString(4, transaction.getCityAddress());
            pst.setString(5, transaction.getStateAddress());
            pst.setString(6, transaction.getZipcode());
            pst.setString(7, transaction.getCreditCardNo());
            pst.setString(8, transaction.getDeliveryMethod());
            pst.setString(9, transaction.getPickupStoreName());
            pst.setString(10, transaction.getOrderDate());
            pst.setString(11, transaction.getDeliveryDate());
            pst.setString(12, transaction.getMaxOrderCancellationDate());
            pst.setString(13, transaction.getMaxPickupDate());
            pst.setString(14, transaction.getUserName());

            System.out.println("insert transaction query" + pst.toString());

            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error inserting transaction: " + e.getMessage());
        }
    }

    public static Transaction selectTransaction(int orderId) {
        Transaction transaction = new Transaction();

        try {
            getConnection();

            String getTransactionQuery = "Select * from transactions where orderId=?";

            PreparedStatement pst = conn.prepareStatement(getTransactionQuery);
            pst.setInt(1, orderId);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                /*orderId integer,
                userName varchar(80),
                        streetAddress varchar(40),
                        cityAddress varchar(40),
                        stateAddress varchar(40),
                        zipcode varchar(40),
                        creditCardNo varchar(12),
                        deliveryMethod varchar(40),
                        pickupStoreName varchar(40),
                        orderDate varchar(12),
                        deliveryDate varchar(12),
                        maxOrderCancellationDate varchar(12),
                        maxPickupDate varchar(12),*/
                transaction = new Transaction(
                        rs.getInt("orderId"),
                        rs.getString("userName"),
                        rs.getString("customerName"),
                        rs.getString("streetAddress"),
                        rs.getString("cityAddress"),
                        rs.getString("stateAddress"),
                        rs.getString("zipcode"),
                        rs.getString("creditCardNo"),
                        rs.getString("deliveryMethod"),
                        rs.getString("pickupStoreName"),
                        rs.getString("orderDate"),
                        rs.getString("deliveryDate"),
                        rs.getString("maxOrderCancellationDate"),
                        rs.getString("maxPickupDate")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error getting transaction: " + e.getMessage());
        }

        return transaction;
    }

    public static void updateOrder(
            String customerName,
            String customerAddress,
            int creditCardNo,
            String deliveryMethod,
            String pickupStoreName,
            int orderId,
            String productName,
            String userName
    ) {
        try {
            getConnection();

            // SET column_1 = value_1, column_2 = value_2 WHERE column_3 = value_3

            String updateOrderQurey = "Update customerOrders SET " +
                    "customerName=?," +
                    "customerAddress=?," +
                    "creditCardNo=?," +
                    "deliveryMethod=?," +
                    "pickupStoreName=? " +
                    "where orderId=? and productName=? and userName=?;";
            PreparedStatement pst = conn.prepareStatement(updateOrderQurey);
            pst.setString(1, customerName);
            pst.setString(2, customerAddress);
            pst.setInt(3, creditCardNo);
            pst.setString(4, deliveryMethod);
            pst.setString(5, pickupStoreName);
            pst.setInt(6, orderId);
            pst.setString(7, productName);
            pst.setString(8, userName);

            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro updating order: " + e.getMessage());
        }
    }

    public static ArrayList<NoOfAvailableProducts> availableProductsList()
    {
        ArrayList<NoOfAvailableProducts> availableProductsList = new ArrayList<NoOfAvailableProducts>();

        try
        {
            getConnection();

            String availableProductsListQuery="SELECT productName, productPrice, productDiscount, numberOfAvailableProducts, manufacturerRebate FROM productDetails;";
            PreparedStatement pst = conn.prepareStatement(availableProductsListQuery);
            ResultSet rs = pst.executeQuery();

            while(rs.next())
            {
                NoOfAvailableProducts product = new NoOfAvailableProducts(
                        rs.getString("productName"),
                        rs.getString("productPrice"),
                        rs.getString("productDiscount"),
                        rs.getString("numberOfAvailableProducts"),
                        rs.getString("manufacturerRebate")
                );
                availableProductsList.add(product);
            }
        }
        catch(Exception e)
        {
            System.out.println("availableProductsList(): " + e);
        }

        return availableProductsList;
    }

    public static ArrayList<NoOfAvailableProducts> currentOnSaleProductsList()
    {
        ArrayList<NoOfAvailableProducts> currentOnSaleProductsList = new ArrayList<NoOfAvailableProducts>();

        try
        {
            getConnection();

            String currentOnSaleProductsListQuery="SELECT productName, productPrice, productDiscount, numberOfAvailableProducts, manufacturerRebate FROM productDetails WHERE productDiscount > 0;";
            PreparedStatement pst = conn.prepareStatement(currentOnSaleProductsListQuery);
            ResultSet rs = pst.executeQuery();

            while(rs.next())
            {
                NoOfAvailableProducts product = new NoOfAvailableProducts(
                        rs.getString("productName"),
                        rs.getString("productPrice"),
                        rs.getString("productDiscount") ,
                        rs.getString("numberOfAvailableProducts"),
                        rs.getString("manufacturerRebate")
                );
                currentOnSaleProductsList.add(product);
            }
        }
        catch(Exception e)
        {
            System.out.println("currentOnSaleProductsList(): " + e);
        }

        return currentOnSaleProductsList;
    }

    //manufacturerRebateProductsList
    public static ArrayList<NoOfAvailableProducts> manufacturerRebateProductsList()
    {
        ArrayList<NoOfAvailableProducts> manufacturerRebateProductsList = new ArrayList<NoOfAvailableProducts>();

        try
        {
            getConnection();

            String manufacturerRebateProductsListQuery="SELECT productName, productPrice, productDiscount,numberOfAvailableProducts, manufacturerRebate FROM productDetails WHERE manufacturerRebate > 0;";
            PreparedStatement pst = conn.prepareStatement(manufacturerRebateProductsListQuery);
            ResultSet rs = pst.executeQuery();

            while(rs.next())
            {
                NoOfAvailableProducts product = new NoOfAvailableProducts(
                        rs.getString("productName"),
                        rs.getString("productPrice"),
                        rs.getString("productDiscount") ,
                        rs.getString("numberOfAvailableProducts") ,
                        rs.getString("manufacturerRebate")
                );
                manufacturerRebateProductsList.add(product);
            }
        }
        catch(Exception e)
        {
            System.out.println("manufacturerRebateProductsList(): "+e);
        }

        return manufacturerRebateProductsList;
    }

    public static ArrayList<NoOfProductsSold> totalSoldProductsList()
    {
        ArrayList<NoOfProductsSold> totalSoldProductsList = new ArrayList<NoOfProductsSold>();

        try
        {
            getConnection();

            String totalSoldProductsListQuery="SELECT productName, productPrice, numberOfItemsSold, (productPrice * numberOfItemsSold) AS productTotalSales FROM productDetails;";
            PreparedStatement pst = conn.prepareStatement(totalSoldProductsListQuery);
            ResultSet rs = pst.executeQuery();

            while(rs.next())
            {
                NoOfProductsSold product = new NoOfProductsSold(
                        rs.getString("productName"),
                        rs.getString("productPrice"),
                        rs.getString("numberOfItemsSold") ,
                        rs.getString("productTotalSales")
                );
                totalSoldProductsList.add(product);
            }
        }
        catch(Exception e)
        {
            System.out.println("totalSoldProductsList(): "+e);
        }

        return totalSoldProductsList;
    }

    public static ArrayList<TotalSalesDaily> totalSalesDailyOrdersList()
    {
        ArrayList<TotalSalesDaily> totalSalesDailyOrdersList = new ArrayList<TotalSalesDaily>();

        try
        {
            getConnection();

            String totalSalesDailyOrdersListQuery="SELECT purchaseDate, SUM(orderTotal) AS totalDailySales, GROUP_CONCAT(CONCAT(productName, ' (', orderTotal, ')')) AS productsListDescription FROM customerorders GROUP BY purchaseDate;";
            PreparedStatement pst = conn.prepareStatement(totalSalesDailyOrdersListQuery);
            ResultSet rs = pst.executeQuery();

            while(rs.next())
            {
                TotalSalesDaily order = new TotalSalesDaily(
                        rs.getString("purchaseDate"),
                        rs.getString("totalDailySales"),
                        rs.getString("productsListDescription")
                );
                totalSalesDailyOrdersList.add(order);
            }
        }
        catch(Exception e)
        {
            System.out.println("totalSalesDailyOrdersList(): "+e);
        }

        return totalSalesDailyOrdersList;
    }
}