import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebServlet("/Utilities")

/* 
	Utilities class contains class variables of type HttpServletRequest, PrintWriter,String and HttpSession.

	Utilities class has a constructor with  HttpServletRequest, PrintWriter variables.
	  
*/

public class Utilities extends HttpServlet{
	HttpServletRequest req;
	PrintWriter pw;
	String url;
	HttpSession session;
	public Utilities(HttpServletRequest req, PrintWriter pw) {
		this.req = req;
		this.pw = pw;
		this.url = this.getFullURL();
		this.session = req.getSession(true);
	}



	/*  Printhtml Function gets the html file name as function Argument, 
		If the html file name is Header.html then It gets Username from session variables.
		Account ,Cart Information ang Logout Options are Displayed*/

	public void printHtml(String file) {
		String result = HtmlToString(file);
		//to print the right navigation in header of username cart and logout etc
		if (file == "Header.html") {
			result=result+"<div id='menu' style='float: right;'><ul>";
			if (session.getAttribute("username")!=null)
			{
				String username = session.getAttribute("username").toString();
				username = Character.toUpperCase(username.charAt(0)) + username.substring(1);
				String usertype = session.getAttribute("usertype").toString();
				if(usertype.equalsIgnoreCase("storeManager"))
				{
					//Store manager can add/update/delete products
					//result = result + "<li><a href='ProductCatalog'><span class='glyphicon'>ProductCatalog</span></a></li>";
					/*result = result + "<li><a href='ProductModify?button=Addproduct'><span class='glyphicon'>AddProduct</span></a></li>"
							+ "<li><a href='DataVisualization'><span class='glyphicon'>DataVisualization</span></a></li>"
							+ "<li><a href='DataAnalytics'><span class='glyphicon'>DataAnalytics</span></a></li>";
									//+ "<li><a href='ProductModify?button=Updateproduct'><span class='glyphicon'>UpdateProduct</span></a></li>"
									//+ "<li><a href='ProductModify?button=Deleteproduct'><span class='glyphicon'>DeleteProduct</span></a></li>";*/

					/*result = result + "<li><a href='ProductModify?button=Addproduct'><span class='glyphicon'>Addproduct</span></a></li>"
							+ "<li><a href='DataVisualization'><span class='glyphicon'>DataVisualization</span></a></li>"
							+ "<li><a href='DataAnalytics'><span class='glyphicon'>DataAnalytics</span></a></li>"
							+ "<li><a href='ViewOrder'><span class='glyphicon'>ViewOrder</span></a></li>"
							+ "<li><a><span class='glyphicon'>Hello,"+username+"</span></a></li>"
							+ "<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>";*/


					result = result + "<li><div class='dropdown'><a onclick='dropdown()' class='dropbtn' style='font-family: Glyphicons Halflings;'>Reports &#9662;</a><div id='myDropdown' class='dropdown-content'>"
							+ "<a href='Inventory' style='font-family: Glyphicons Halflings;'>Inventory</a>"
							+ "<a href='SalesReport' style='font-family: Glyphicons Halflings;'>Sales Report</a>"
							+ "<a href='DataAnalytics'><span class='glyphicon'>DataAnalytics</span></a>"
							+ "<a href='DataVisualization'><span class='glyphicon'>DataVisualization</span></a>"
							+ "</div></div></li>"
							+ "<li><div class='dropdown'><a onclick='dropdown1()' class='dropbtn' style='font-family: Glyphicons Halflings;'>ProductsCatalog &#9662;</a><div id='myDropdown1' class='dropdown-content'>"
							+ "<a href='ProductModify?button=Addproduct' style='font-family: Glyphicons Halflings;'>AddProduct</a>"
							+ "</div></div></li>"
							+ "<li><a href='ViewOrder'><span class='glyphicon'>ViewOrder</span></a></li>"
							+ "<li><a><span class='glyphicon'>Hello,"+username+"</span></a></li>"
							+ "<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>";
				}
				else if(usertype.equalsIgnoreCase("salesman"))
				{
					//salesman can add/update/delete customer orders, add customers
					/*result = result + "<li><a href='AddCustomer'><span class='glyphicon'>AddCustomers</span></a></li>"
									+ "<li><a href='CustomerOrders'><span class='glyphicon'>CustomerOrders</span></a></li>";*/
					result = result + "<li><a href='Registration'><span class='glyphicon'>AddCustomers</span></a></li>"
							+ "<li><a href='CustomerOrders'><span class='glyphicon'>CustomerOrders</span></a></li>"
							+ "<li><a href='ViewOrder'><span class='glyphicon'>ViewOrder</span></a></li>"
							+ "<li><a><span class='glyphicon'>Hello,"+username+"</span></a></li>"
							+ "<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>";
				} else {
					result = result + "<li><a href='ViewOrder'><span class='glyphicon'>ViewOrder</span></a></li>"
							+ "<li><a><span class='glyphicon'>Hello,"+username+"</span></a></li>"
							+ "<li><a href='Account'><span class='glyphicon'>Account</span></a></li>"
							+ "<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>";
				}
			}
			else
				result = result +"<li><a href='ViewOrder'><span class='glyphicon'>ViewOrder</span></a></li>"+ "<li><a href='Login'><span class='glyphicon'>Login</span></a></li>";
			result = result +"<li><a href='Cart'><span class='glyphicon'>Cart("+CartCount()+")</span></a></li></ul></div></div><div id='page'>";
			pw.print(result);
		} else
			pw.print(result);
	}


	/*  getFullURL Function - Reconstructs the URL user request  */

	public String getFullURL() {
		String scheme = req.getScheme();
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		String contextPath = req.getContextPath();
		StringBuffer url = new StringBuffer();
		url.append(scheme).append("://").append(serverName);

		if ((serverPort != 80) && (serverPort != 443)) {
			url.append(":").append(serverPort);
		}
		url.append(contextPath);
		url.append("/");
		return url.toString();
	}

	/*  HtmlToString - Gets the Html file and Converts into String and returns the String.*/
	public String HtmlToString(String file) {
		String result = null;
		try {
			String webPage = url + file;
			URL url = new URL(webPage);
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			result = sb.toString();
		}
		catch (Exception e) {
		}
		return result;
	}

	/*  logout Function removes the username , usertype attributes from the session variable*/

	public void logout(){
		session.removeAttribute("username");
		session.removeAttribute("usertype");
	}

	/*  logout Function checks whether the user is loggedIn or Not*/

	public boolean isLoggedin(){
		if (session.getAttribute("username")==null)
			return false;
		return true;
	}

	/*  username Function returns the username from the session variable.*/

	public String username(){
		if (session.getAttribute("username")!=null)
			return session.getAttribute("username").toString();
		return null;
	}

	/*  usertype Function returns the usertype from the session variable.*/
	public String usertype(){
		if (session.getAttribute("usertype")!=null)
			return session.getAttribute("usertype").toString();
		return null;
	}

	/*  getUser Function checks the user is a customer or retailer or manager and returns the user class variable.*/
	public User getUser(){
		String usertype = usertype();
		HashMap<String, User> hm=new HashMap<String, User>();
		String TOMCAT_HOME = System.getProperty("catalina.home");
		try
		{
				/*		
				FileInputStream fileInputStream=new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\SmartPortables\\UserDetails.txt"));
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
				hm= (HashMap)objectInputStream.readObject();
				*/
			hm=MySqlDataStoreUtilities.selectUser();
		}
		catch(Exception e)
		{
		}
		User user = hm.get(username());
		return user;
	}

	/*  getCustomerOrders Function gets  the Orders for the user*/
	public ArrayList<OrderItem> getCustomerOrders(){
		ArrayList<OrderItem> order = new ArrayList<OrderItem>();
		if(OrdersHashMap.orders.containsKey(username()))
			order= OrdersHashMap.orders.get(username());
		return order;
	}

	/*  getOrdersPaymentSize Function gets  the size of OrderPayment */
	public int getOrderPaymentSize()
	{
		HashMap<Integer, ArrayList<CustomerOrder>> customerOrders = new HashMap<>();
		String TOMCAT_HOME = System.getProperty("catalina.home");
		try
		{
			/*
			FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\SmartPortables\\PaymentDetails.txt"));
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
			orderPayments = (HashMap)objectInputStream.readObject();
			*/
			customerOrders = MySqlDataStoreUtilities.selectOrder();
		}
		catch(Exception e)
		{

		}

		return customerOrders.size() + 1;
		/*
		int size=0;
		for(Map.Entry<Integer, ArrayList<CustomerOrder>> entry : customerOrders.entrySet()) {
			size=size + 1;	 
		}
		return size;*/
	}

	/*  CartCount Function gets  the size of User Orders*/
	public int CartCount()
	{
		if(isLoggedin())
			return getCustomerOrders().size();
		return 0;
	}

	/* StoreProduct Function stores the Purchased product in Orders HashMap according to the User Names.*/

	public void storeProduct(String name,String type,String maker, String acc, boolean isWarrantyIncluded)
	{
		if(!OrdersHashMap.orders.containsKey(username()))
		{
			ArrayList<OrderItem> arr = new ArrayList<OrderItem>();
			OrdersHashMap.orders.put(username(), arr);
		}
		ArrayList<OrderItem> orderItems = OrdersHashMap.orders.get(username());
		HashMap<String, PetTracker> allPetTrackers = new HashMap<>();
		HashMap<String, VirtualReality> allVirtualRealities = new HashMap<>();
		HashMap<String, FitnessWatch> allFitnessWatches = new HashMap<>();
		HashMap<String, Headphone> allHeadphones = new HashMap<>();
		HashMap<String, Laptop> allLaptops = new HashMap<>();
		HashMap<String, Phone> allPhones = new HashMap<>();
		HashMap<String, SmartWatch> allSmartWatches = new HashMap<>();
		HashMap<String, VoiceAssistant> allVoiceAssistants = new HashMap<>();
		//HashMap<String, Tablet> allTablets = new HashMap<String, Tablet>();//not in use
		
		/*if(type.equals("consoles"))
		{
			Console console;
			console = SaxParserDataStore.consoles.get(name);
			OrderItem orderitem = new OrderItem(console.getName(), console.getPrice(), console.getImage(), console.getRetailer());
			orderItems.add(orderitem);
		}
		if(type.equals("games"))
		{
			Game game = null;
			game = SaxParserDataStore.games.get(name);
			OrderItem orderitem = new OrderItem(game.getName(), game.getPrice(), game.getImage(), game.getRetailer());
			orderItems.add(orderitem);
		}*/
		/*
		if(type.equals("tablets"))
		{
			Tablet tablet = null;
			try
			{
				allTablets = MySqlDataStoreUtilities.getTablets();
			}
			catch(Exception e)
			{

			}
			tablet = allTablets.get(name);
			OrderItem orderitem = new OrderItem(tablet.getName(), tablet.getPrice(), tablet.getImage(), tablet.getRetailer());
			orderItems.add(orderitem);
		}
		*/
		if(type.equals("accessories"))
		{
			Accessory accessory = SaxParserDataStore.accessories.get(name);
			OrderItem orderitem = new OrderItem(
					accessory.getName(),
					accessory.getPrice(),
					accessory.getImage(),
					accessory.getRetailer(),
					false,
					0.0,
					0.0,
					"Accessory"
			);
			orderItems.add(orderitem);
		}
		if(type.equals("Phone"))
		{
			Phone phone;
			try
			{
				allPhones = MySqlDataStoreUtilities.getPhones();
			}
			catch(Exception e)
			{

			}
			phone = allPhones.get(name);
			OrderItem orderitem = new OrderItem(
					phone.getName(),
					phone.getPrice(),
					phone.getImage(),
					phone.getRetailer(),
					isWarrantyIncluded,
					phone.getDiscount(),
					phone.getWarrantyPrice(),
					"Phone"
			);
			orderItems.add(orderitem);
		}
		if(type.equals("Laptop"))
		{
			Laptop laptop;
			try
			{
				allLaptops = MySqlDataStoreUtilities.getLaptops();
			}
			catch(Exception e)
			{

			}
			laptop = allLaptops.get(name);
			OrderItem orderitem = new OrderItem(laptop.getName(), laptop.getPrice(), laptop.getImage(), laptop.getRetailer(), isWarrantyIncluded,
					laptop.getDiscount(),
					laptop.getWarrantyPrice(), "Laptop");
			orderItems.add(orderitem);
		}
		if(type.equals("Voice Assistant")) {
			VoiceAssistant voiceAssistant;
			try
			{
				allVoiceAssistants = MySqlDataStoreUtilities.getVoiceAssistants();
			}
			catch(Exception e)
			{

			}
			voiceAssistant = allVoiceAssistants.get(name);
			OrderItem orderitem = new OrderItem(voiceAssistant.getName(), voiceAssistant.getPrice(), voiceAssistant.getImage(), voiceAssistant.getRetailer(), isWarrantyIncluded,
					voiceAssistant.getDiscount(),
					voiceAssistant.getWarrantyPrice(), "Voice Assistant");
			orderItems.add(orderitem);
		}
		if(type.equals("Fitness Watch"))
		{
			FitnessWatch fitnessWatch;
			try
			{
				allFitnessWatches = MySqlDataStoreUtilities.getFitnessWatches();
			}
			catch(Exception e)
			{

			}
			fitnessWatch = allFitnessWatches.get(name);
			OrderItem orderitem = new OrderItem(fitnessWatch.getName(), fitnessWatch.getPrice(), fitnessWatch.getImage(), fitnessWatch.getRetailer(), false,
					0.0,
					0.0, "Fitness Watch");
			orderItems.add(orderitem);
		}
		if(type.equals("Smart Watch"))
		{
			SmartWatch smartWatch;
			try
			{
				allSmartWatches = MySqlDataStoreUtilities.getSmartWatches();
			}
			catch(Exception e)
			{

			}
			smartWatch = allSmartWatches.get(name);
			OrderItem orderitem = new OrderItem(smartWatch.getName(), smartWatch.getPrice(), smartWatch.getImage(), smartWatch.getRetailer(), false,
					0.0,
					0.0, "Smart Watch");
			orderItems.add(orderitem);
		}
		if(type.equals("Headphone"))
		{
			Headphone headphone;
			try
			{
				allHeadphones = MySqlDataStoreUtilities.getHeadphones();
			}
			catch(Exception e)
			{

			}
			headphone = allHeadphones.get(name);
			OrderItem orderitem = new OrderItem(headphone.getName(), headphone.getPrice(), headphone.getImage(), headphone.getRetailer(), false,
					0.0,
					0.0, "Headphone");
			orderItems.add(orderitem);
		}
		if(type.equals("Pet Tracker"))
		{
			PetTracker petTracker;
			try
			{
				allPetTrackers = MySqlDataStoreUtilities.getPetTracker();
			}
			catch(Exception e)
			{

			}
			//wirelessPlan = SaxParserDataStore.wirelessPlans.get(name);
			petTracker = allPetTrackers.get(name);
			OrderItem orderitem = new OrderItem(
					petTracker.getName(),
					petTracker.getPrice(),
					petTracker.getImage(),
					petTracker.getRetailer(),
					false,
					0.0,
					0.0,
					"Pet Tracker"
			);
			orderItems.add(orderitem);
		}

	}
	// store the payment details for orders
	public void storePayment(CustomerOrder order){
		HashMap<Integer, ArrayList<CustomerOrder>> customerOrders = new HashMap<>();
		int orderId = order.getOrderId();
		//String TOMCAT_HOME = System.getProperty("catalina.home");
		// get the payment details file
		try {
			/*
			FileInputStream fileInputStream = new FileInputStream(new File(TOMCAT_HOME+"\\webapps\\SmartPortables\\PaymentDetails.txt"));
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);	      
			orderPayments = (HashMap)objectInputStream.readObject();
			*/
			customerOrders = MySqlDataStoreUtilities.selectOrder();
		}
		catch(Exception e) {

		}
		if(customerOrders == null) {
			customerOrders = new HashMap<Integer, ArrayList<CustomerOrder>>();
		}
		// if there exist order id already add it into same list for order id or create a new record with order id

		if(!customerOrders.containsKey(orderId)) {
			ArrayList<CustomerOrder> arr = new ArrayList<>();
			customerOrders.put(orderId, arr);
		}
		ArrayList<CustomerOrder> listCustomerOrder = customerOrders.get(orderId);
		/*CustomerOrder customerOrder = new CustomerOrder(
				orderId,
				order.getUserName(),
				order.getOrderName(),
				order.getOrderPrice(),
				order.getStreetAddress() + ", "+order.getCityAddress() + ", " + order.getStateAddress() +" - " + order.getZipcode(),
				order.getCreditCardNo(),
				order.getOrderDate(),
				order.getDeliveryDate(),
				order.getMaxOrderCancellationDate()
		);*/
		listCustomerOrder.add(order);

		// add order details into file

		try
		{
			/*
			FileOutputStream fileOutputStream = new FileOutputStream(new File(TOMCAT_HOME+"\\webapps\\SmartPortables\\PaymentDetails.txt"));
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(orderPayments);
			objectOutputStream.flush();
			objectOutputStream.close();       
			fileOutputStream.close();
			*/
			MySqlDataStoreUtilities.insertCustomerOrder(order);
		}
		catch(Exception e)
		{
			System.out.println("inside exception file not written properly");
		}
	}

	public String storeReview(Review review) {
		String message=MongoDBDataStoreUtilities.insertCustomerReview(review);

		if(!message.equals("Successfull"))
		{
			return "UnSuccessfull";
		}
		else
		{
			HashMap<String, ArrayList<Review>> reviews= new HashMap<String, ArrayList<Review>>();
			try
			{
				reviews=MongoDBDataStoreUtilities.selectReview();
			}
			catch(Exception e)
			{

			}
			if(reviews==null)
			{
				reviews = new HashMap<String, ArrayList<Review>>();
			}
			// if there exist product review already add it into same list for productname or create a new record with product name

			if(!reviews.containsKey(review.getProductModelName()))
			{
				ArrayList<Review> arr = new ArrayList<>();
				reviews.put(review.getProductModelName(), arr);
			}
			ArrayList<Review> listReview = reviews.get(review.getProductModelName());
			//Review r = new Review(productname,username(),producttype,productmaker,reviewrating,reviewdate,reviewtext,reatilerpin,price,city,reviewerAge,reviewerGender,reviewerOccupation);
			listReview.add(review);

			// add Reviews into database

			return "Successfull";
		}
	}

	public void storeCustomer(Customer customer) {
		MySqlDataStoreUtilities.insertCustomerInfo(customer);
	}

	/* getConsoles Functions returns the Hashmap with all consoles in the store.*/

	public HashMap<String, Console> getConsoles()
	{
		HashMap<String, Console> hm = new HashMap<String, Console>();
		hm.putAll(SaxParserDataStore.consoles);
		return hm;
	}

	/* getGames Functions returns the  Hashmap with all Games in the store.*/

	public HashMap<String, Game> getGames()
	{
		HashMap<String, Game> hm = new HashMap<String, Game>();
		hm.putAll(SaxParserDataStore.games);
		return hm;
	}

	/* getTablets Functions returns the Hashmap with all Tablet in the store.*/

	public HashMap<String, Tablet> getTablets()
	{
		HashMap<String, Tablet> hm = new HashMap<String, Tablet>();
		hm.putAll(SaxParserDataStore.tablets);
		return hm;
	}

	/* getSoundSystems Functions returns the Hashmap with all consoles in the store.*/

	public HashMap<String, SoundSystem> getSoundSystems()
	{
		HashMap<String, SoundSystem> hm = new HashMap<String, SoundSystem>();
		hm.putAll(SaxParserDataStore.soundSystems);
		return hm;
	}

	/* getPhones Functions returns the Hashmap with all consoles in the store.*/

	public HashMap<String, Phone> getPhones()
	{
		HashMap<String, Phone> hm = new HashMap<String, Phone>();
		hm.putAll(SaxParserDataStore.phones);
		return hm;
	}

	/* getLaptops Functions returns the Hashmap with all consoles in the store.*/

	public HashMap<String, Laptop> getLaptops()
	{
		HashMap<String, Laptop> hm = new HashMap<String, Laptop>();
		hm.putAll(SaxParserDataStore.laptops);
		return hm;
	}

	/* getVoiceAssistants Functions returns the Hashmap with all consoles in the store.*/

	public HashMap<String, VoiceAssistant> getVoiceAssistants()
	{
		HashMap<String, VoiceAssistant> hm = new HashMap<String, VoiceAssistant>();
		hm.putAll(SaxParserDataStore.voiceAssistants);
		return hm;
	}

	/* getFitnessWatches Functions returns the Hashmap with all consoles in the store.*/

	public HashMap<String, FitnessWatch> getFitnessWatches()
	{
		HashMap<String, FitnessWatch> hm = new HashMap<String, FitnessWatch>();
		hm.putAll(SaxParserDataStore.fitnessWatches);
		return hm;
	}

	/* getSmartWatches Functions returns the Hashmap with all consoles in the store.*/

	public HashMap<String, SmartWatch> getSmartWatches()
	{
		HashMap<String, SmartWatch> hm = new HashMap<String, SmartWatch>();
		hm.putAll(SaxParserDataStore.smartWatches);
		return hm;
	}

	/* getHeadphones Functions returns the Hashmap with all consoles in the store.*/

	public HashMap<String, Headphone> getHeadphones()
	{
		HashMap<String, Headphone> hm = new HashMap<String, Headphone>();
		hm.putAll(SaxParserDataStore.headphones);
		return hm;
	}

	/* getWirelessPlans Functions returns the Hashmap with all consoles in the store.*/

	public HashMap<String, WirelessPlan> getWirelessPlans()
	{
		HashMap<String, WirelessPlan> hm = new HashMap<String, WirelessPlan>();
		hm.putAll(SaxParserDataStore.wirelessPlans);
		return hm;
	}

	/* getTelevisions Functions returns the Hashmap with all consoles in the store.*/

	public HashMap<String, Television> getTelevisions()
	{
		HashMap<String, Television> hm = new HashMap<String, Television>();
		hm.putAll(SaxParserDataStore.televisions);
		return hm;
	}



	/* getProducts Functions returns the Arraylist of consoles in the store.*/

	public ArrayList<String> getProducts()
	{
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Console> entry : getConsoles().entrySet())
		{
			ar.add(entry.getValue().getName());
		}
		return ar;
	}

	/* getProducts Functions returns the Arraylist of games in the store.*/

	public ArrayList<String> getProductsGame()
	{
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Game> entry : getGames().entrySet())
		{
			ar.add(entry.getValue().getName());
		}
		return ar;
	}

	/* getProducts Functions returns the Arraylist of Tablets in the store.*/

	public ArrayList<String> getProductsTablets()
	{
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Tablet> entry : getTablets().entrySet())
		{
			ar.add(entry.getValue().getName());
		}
		return ar;
	}

	/* getProducts Functions returns the Arraylist of Televisions in the store.*/

	public ArrayList<String> getProductsTelevisions()
	{
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Television> entry : getTelevisions().entrySet())
		{
			ar.add(entry.getValue().getName());
		}
		return ar;
	}

	/* getProductsSoundSystems Functions returns the Arraylist of SoundSystems in the store.*/

	public ArrayList<String> getProductsSoundSystems()
	{
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, SoundSystem> entry : getSoundSystems().entrySet())
		{
			ar.add(entry.getValue().getName());
		}
		return ar;
	}

	/* getProductsPhones Functions returns the Arraylist of Phones in the store.*/

	public ArrayList<String> getProductsPhones()
	{
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Phone> entry : getPhones().entrySet())
		{
			ar.add(entry.getValue().getName());
		}
		return ar;
	}

	/* getProductsLaptops Functions returns the Arraylist of Laptop in the store.*/

	public ArrayList<String> getProductsLaptops()
	{
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Laptop> entry : getLaptops().entrySet())
		{
			ar.add(entry.getValue().getName());
		}
		return ar;
	}

	/* getProductsVoiceAssistants Functions returns the Arraylist of VoiceAssistant in the store.*/

	public ArrayList<String> getProductsVoiceAssistants()
	{
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, VoiceAssistant> entry : getVoiceAssistants().entrySet())
		{
			ar.add(entry.getValue().getName());
		}
		return ar;
	}

	/* getProductsFitnessWatches Functions returns the Arraylist of FitnessWatches in the store.*/

	public ArrayList<String> getProductsFitnessWatches()
	{
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, FitnessWatch> entry : getFitnessWatches().entrySet())
		{
			ar.add(entry.getValue().getName());
		}
		return ar;
	}

	/* getProductsSmartWatches Functions returns the Arraylist of Televisions in the store.*/

	public ArrayList<String> getProductsSmartWatches()
	{
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, SmartWatch> entry : getSmartWatches().entrySet())
		{
			ar.add(entry.getValue().getName());
		}
		return ar;
	}

	/* getProductsHeadphones Functions returns the Arraylist of Headphones in the store.*/

	public ArrayList<String> getProductsHeadphones()
	{
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Headphone> entry : getHeadphones().entrySet())
		{
			ar.add(entry.getValue().getName());
		}
		return ar;
	}

	/* getProductsWirelessPlans Functions returns the Arraylist of WirelessPlans in the store.*/

	public ArrayList<String> getProductsWirelessPlans()
	{
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, WirelessPlan> entry : getWirelessPlans().entrySet())
		{
			ar.add(entry.getValue().getName());
		}
		return ar;
	}

	public List<Store> getAllStores() {
		return MySqlDataStoreUtilities.getAllStores();
	}

	public String addProduct(
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
		return MySqlDataStoreUtilities.addProduct(
				productType,
				productId,
				productName,
				productPrice,
				productImage,
				productManufacturer,
				productCondition,
				productDiscount,
				productRebate,
				productDescription,
				productHasWarranty,
				productWarranty,
				numberOfAvailableProducts,
				numberOfItemsSold,
				prod
		);
	}

}
