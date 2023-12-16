import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/ProductCrud")
@MultipartConfig(fileSizeThreshold = 1048576,
        maxFileSize = 5242880L,
        maxRequestSize = 26214400L)
public class ProductCrud extends HttpServlet {
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request, pw);
        String action = request.getParameter("button");
        System.out.print(action);
        String msg = "good";
        String productType= "",
                productId="",
                productName="",
                productImage="",
                productManufacturer="",
                productCondition="",
                productDescription="",
                prod = "";
        int productRebate = 0, numberOfAvailableProducts = 0, numberOfItemsSold = 0;
        double productPrice = 0.0, productDiscount = 0.0, productWarranty = 0.0;
        boolean productHasWarranty = false;
        /*
        HashMap<String,Console> allconsoles = new HashMap<String,Console> ();
        HashMap<String,Tablet> alltablets = new HashMap<String,Tablet> ();
        HashMap<String,Game> allgames = new HashMap<String,Game> ();
        HashMap<String,Accessory> allaccessories=new HashMap<String,Accessory>();
        */
        HashMap<String, FitnessWatch> allFitnessWatches = new HashMap<String, FitnessWatch>();
        HashMap<String, Headphone> allHeadphones = new HashMap<String, Headphone>();
        HashMap<String, Laptop> allLaptops = new HashMap<String, Laptop>();
        HashMap<String, Phone> allPhones = new HashMap<String, Phone>();
        HashMap<String, SmartWatch> allSmartWatches = new HashMap<String, SmartWatch>();
        HashMap<String, VoiceAssistant> allVoiceAssistants = new HashMap<String, VoiceAssistant>();
        HashMap<String, PetTracker> allPetTracker = new HashMap<>();
        HashMap<String, VirtualReality> allVirtualReality = new HashMap<>();
        if (action.equals("Add Product") || action.equals("Update Product")) {
            productType = request.getParameter("productType");
            productName = request.getParameter("productName");
            productId = productName.toLowerCase(Locale.ROOT).concat(productType).replace(" ", "_");
            productPrice = Double.parseDouble(request.getParameter("productPrice"));
            productImage = request.getParameter("productImage");
            productManufacturer = request.getParameter("productManufacturer");
            productCondition = request.getParameter("productCondition");
            productDiscount = Double.parseDouble(request.getParameter("productDiscount"));
            productRebate = Integer.parseInt(request.getParameter("productRebate"));
            productDescription = request.getParameter("productDescription");
            productWarranty = Double.parseDouble(request.getParameter("productWarranty"));
            productHasWarranty = productWarranty > 0.0;
            numberOfAvailableProducts = Integer.parseInt(request.getParameter("numberOfAvailableProducts"));
            numberOfItemsSold = Integer.parseInt(request.getParameter("numberOfItemsSold"));
        } else {
            productId   = request.getParameter("productId");
        }
        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");

        if(action.equals("Add Product")) {
            if(productType.equals("fitnessWatch")) {
                allFitnessWatches = MySqlDataStoreUtilities.getFitnessWatches();
                if(allFitnessWatches.containsKey(productId)) {
                    msg = "Product already available";
                } else {
                    allFitnessWatches.put(productId, new FitnessWatch(
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
                            productType,
                            numberOfAvailableProducts,
                            numberOfItemsSold
                    ));
                }
            }
            else if(productType.equals("headphone")) {
                allHeadphones = MySqlDataStoreUtilities.getHeadphones();
                if(allHeadphones.containsKey(productId)) {
                    msg = "Product already available";
                } else {
                    allHeadphones.put(productId, new Headphone(
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
                            productType,
                            numberOfAvailableProducts,
                            numberOfItemsSold
                    ));
                }
            }
            else if(productType.equals("laptop")) {
                allLaptops = MySqlDataStoreUtilities.getLaptops();
                if(allLaptops.containsKey(productId)) {
                    msg = "Product already available";
                } else {
                    allLaptops.put(productId, new Laptop(
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
                            productType,
                            numberOfAvailableProducts,
                            numberOfItemsSold
                    ));
                }
            }
            else if(productType.equals("phone")) {
                allPhones = MySqlDataStoreUtilities.getPhones();
                if(allPhones.containsKey(productId)) {
                    msg = "Product already available";
                } else {
                    allPhones.put(productId, new Phone(
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
                            productType,
                            numberOfAvailableProducts,
                            numberOfItemsSold
                    ));
                }
            }
            else if(productType.equals("smartWatch")) {
                allSmartWatches = MySqlDataStoreUtilities.getSmartWatches();
                if(allSmartWatches.containsKey(productId)) {
                    msg = "Product already available";
                } else {
                    allSmartWatches.put(productId, new SmartWatch(
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
                            productType,
                            numberOfAvailableProducts,
                            numberOfItemsSold
                    ));
                }
            }
            else if(productType.equals("voiceAssistant")) {
                allVoiceAssistants = MySqlDataStoreUtilities.getVoiceAssistants();
                if(allVoiceAssistants.containsKey(productId))
                {
                    msg = "Product already available";
                } else {
                    allVoiceAssistants.put(productId, new VoiceAssistant(
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
                            productType,
                            numberOfAvailableProducts,
                            numberOfItemsSold
                    ));
                }
            }
            else if(productType.equals("petTracker")) {
                allPetTracker = MySqlDataStoreUtilities.getPetTracker();
                if(allPetTracker.containsKey(productId))
                {
                    msg = "Product already available";
                } else {
                    allPetTracker.put(productId, new PetTracker(
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
                            productType,
                            numberOfAvailableProducts,
                            numberOfItemsSold
                    ));
                }
            }
            else if(productType.equals("virtualReality")) {
                allVirtualReality = MySqlDataStoreUtilities.getVirtualReality();
                if(allVirtualReality.containsKey(productId))
                {
                    msg = "Product already available";
                } else {
                    allVirtualReality.put(productId, new VirtualReality(
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
                            productType,
                            numberOfAvailableProducts,
                            numberOfItemsSold
                    ));
                }
            }
            /*
            else if (productType.equals("accessories"))
            {  
                if(!request.getParameter("product").isEmpty())
                {
                    prod = request.getParameter("product");
                    allconsoles = MySqlDataStoreUtilities.getConsoles();
                    if(allconsoles.containsKey(prod))
                    {
                        allaccessories = MySqlDataStoreUtilities.getAccessories();
                        if(allaccessories.containsKey(productId))
                        {
                            msg = "Product already available";
                        }
                    }
                    else
                    {
                        msg = "The product related to accessories is not available";
                    }
                }
                
                else
                {
                    msg = "Please add the prodcut name";
                }
                	  
            }
            */

            if (msg.equals("good")) {
                try {
                    msg = MySqlDataStoreUtilities.addProduct(
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
                catch(Exception e) {
                    msg = "Product cannot be inserted";
                }
                msg = "Product has been successfully added";
            }
        }
        else if(action.equals("update")) {
            if(productType.equals("fitnessWatch")) {
                allFitnessWatches = MySqlDataStoreUtilities.getFitnessWatches();
                if(allFitnessWatches.containsKey(productId)) {
                    msg = "Product already available";
                } else {
                    allFitnessWatches.put(productId, new FitnessWatch(
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
                            productType,
                            numberOfAvailableProducts,
                            numberOfItemsSold
                    ));
                }
            }
            else if(productType.equals("headphone")) {
                allHeadphones = MySqlDataStoreUtilities.getHeadphones();
                if(allHeadphones.containsKey(productId)) {
                    msg = "Product already available";
                } else {
                    allHeadphones.put(productId, new Headphone(
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
                            productType,
                            numberOfAvailableProducts,
                            numberOfItemsSold
                    ));
                }
            }
            else if(productType.equals("laptop")) {
                allLaptops = MySqlDataStoreUtilities.getLaptops();
                if(allLaptops.containsKey(productId)) {
                    msg = "Product already available";
                } else {
                    allLaptops.put(productId, new Laptop(
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
                            productType,
                            numberOfAvailableProducts,
                            numberOfItemsSold
                    ));
                }
            }
            else if(productType.equals("phone")) {
                allPhones = MySqlDataStoreUtilities.getPhones();
                if(allPhones.containsKey(productId)) {
                    msg = "Product already available";
                } else {
                    allPhones.put(productId, new Phone(
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
                            productType,
                            numberOfAvailableProducts,
                            numberOfItemsSold
                    ));
                }
            }
            else if(productType.equals("smartWatch")) {
                allSmartWatches = MySqlDataStoreUtilities.getSmartWatches();
                if(allSmartWatches.containsKey(productId)) {
                    msg = "Product already available";
                } else {
                    allSmartWatches.put(productId, new SmartWatch(
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
                            productType,
                            numberOfAvailableProducts,
                            numberOfItemsSold
                    ));
                }
            }
            else if(productType.equals("voiceAssistant")) {
                allVoiceAssistants = MySqlDataStoreUtilities.getVoiceAssistants();
                if(allVoiceAssistants.containsKey(productId))
                {
                    msg = "Product already available";
                } else {
                    allVoiceAssistants.put(productId, new VoiceAssistant(
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
                            productType,
                            numberOfAvailableProducts,
                            numberOfItemsSold
                    ));
                }
            }
            else if(productType.equals("petTracker")) {
                allPetTracker = MySqlDataStoreUtilities.getPetTracker();
                if(allPetTracker.containsKey(productId))
                {
                    msg = "Product already available";
                } else {
                    allPetTracker.put(productId, new PetTracker(
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
                            productType,
                            numberOfAvailableProducts,
                            numberOfItemsSold
                    ));
                }
            }
            else if(productType.equals("virtualReality")) {
                allVirtualReality = MySqlDataStoreUtilities.getVirtualReality();
                if(allVirtualReality.containsKey(productId))
                {
                    msg = "Product already available";
                } else {
                    allVirtualReality.put(productId, new VirtualReality(
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
                            productType,
                            numberOfAvailableProducts,
                            numberOfItemsSold
                    ));
                }
            }

            /*
            if(productType.equals("console"))
            {
                allconsoles = MySqlDataStoreUtilities.getConsoles();
                if(!allconsoles.containsKey(productId))
                {
                    msg = "Product not available";
                }
            }
            else if (productType.equals("tablets"))
            {
                alltablets = MySqlDataStoreUtilities.getTablets();
                if(!alltablets.containsKey(productId))
                {
                    msg = "Product not available";
                }
            }
            else if (productType.equals("accessories"))
            {
                allaccessories = MySqlDataStoreUtilities.getAccessories();
                if(!allaccessories.containsKey(productId))
                {
                    msg = "Product not available";
                }
            }
            */
            if (msg.equals("good")) {
                try {
                    msg = MySqlDataStoreUtilities.updateProduct(
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
                            numberOfItemsSold
                    );
                }
                catch(Exception e) {
                    msg = "Product cannot be updated";
                }
                msg = "Product has been successfully updated";
            }
        }
        else if(action.equals("delete")) {
            /*
            msg = "bad";

            allTelevisions = MySqlDataStoreUtilities.getTelevisions();
            if(allTelevisions.containsKey(productId))
            {
                msg = "good";
            }
		       		
            if (msg.equals("good"))
            {
            */
            try
            {
                System.out.print("delete the prodcut");
                msg = MySqlDataStoreUtilities.deleteProduct(productId);
            }
            catch(Exception e)
            {
                msg = "Product cannot be deleted";
            }
            msg = "Product has been successfully deleted";
            /*
            }
            else
            {
                msg = "Product not available";
            }
            */
        }

        pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
        pw.print("<a style='font-size: 24px;'>Order</a>");
        pw.print("</h2><div class='entry'>");
        pw.print("<h4 style='color:blue'>"+msg+"</h4>");
        pw.print("</div></div></div>");
        utility.printHtml("Footer.html");
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request, pw);
        String action = request.getParameter("button");
        String msg = "good";
        String productType= "",
                productId="",
                productName="",
                productImage="",
                productManufacturer="",
                productCondition="",
                productDescription="",
                fileName="",
                prod = "";
        double productPrice = 0.0, productDiscount = 0.0, productWarranty = 0.0;
        boolean productHasWarranty = false;
        int productRebate = 0, numberOfAvailableProducts = 0, numberOfItemsSold = 0;
        Part part = null;

        HashMap<String, FitnessWatch> allFitnessWatches = new HashMap<>();
        HashMap<String, PetTracker> allPetTrackers = new HashMap<>();
        HashMap<String, VirtualReality> allVirtualRealities = new HashMap<>();
        HashMap<String, Headphone> allHeadphones = new HashMap<>();
        HashMap<String, Laptop> allLaptops = new HashMap<>();
        HashMap<String, Phone> allPhones = new HashMap<>();
        HashMap<String, SmartWatch> allSmartWatches = new HashMap<>();
        HashMap<String, VoiceAssistant> allVoiceAssistants = new HashMap<>();
        HashMap<String, PetTracker> allPetTracker = new HashMap<>();
        HashMap<String, VirtualReality> allVirtualReality = new HashMap<>();

        if (action.equals("Add Product") || action.equals("Update Product")) {
            productType = request.getParameter("productType");
            productName = request.getParameter("productName");
            productId = productName.toLowerCase(Locale.ROOT).concat(" " + productType).replace(" ", "_");
            productPrice = Double.parseDouble(request.getParameter("productPrice"));
            part = request.getPart("productImage");
            fileName = getFileName(part);
            productManufacturer = request.getParameter("productManufacturer");
            productCondition = request.getParameter("productCondition");
            productDiscount = Double.parseDouble(request.getParameter("productDiscount"));
            productRebate = Integer.parseInt(request.getParameter("productRebate"));
            productDescription = request.getParameter("productDescription");
            productWarranty = Double.parseDouble(request.getParameter("productWarranty"));
            productHasWarranty = productWarranty > 0.0;
            numberOfAvailableProducts = Integer.parseInt(request.getParameter("numberOfAvailableProducts"));
            numberOfItemsSold = Integer.parseInt(request.getParameter("numberOfItemsSold"));
        } else {
            productId   = request.getParameter("productId");
        }
        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");

        if(action.equals("Add Product")) {
            if(productType.equals("fitnessWatch")) {
                allFitnessWatches = MySqlDataStoreUtilities.getFitnessWatches();
                if(allFitnessWatches.containsKey(productId)) {
                    msg = "Product already available";
                } else {
                    allFitnessWatches.put(productId, new FitnessWatch(
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
                            productType,
                            numberOfAvailableProducts,
                            numberOfItemsSold
                    ));
                }
            }
            else if(productType.equals("headphone")) {
                allHeadphones = MySqlDataStoreUtilities.getHeadphones();
                if(allHeadphones.containsKey(productId)) {
                    msg = "Product already available";
                } else {
                    allHeadphones.put(productId, new Headphone(
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
                            productType,
                            numberOfAvailableProducts,
                            numberOfItemsSold
                    ));
                }
            }
            else if(productType.equals("laptop")) {
                allLaptops = MySqlDataStoreUtilities.getLaptops();
                if(allLaptops.containsKey(productId)) {
                    msg = "Product already available";
                } else {
                    allLaptops.put(productId, new Laptop(
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
                            productType,
                            numberOfAvailableProducts,
                            numberOfItemsSold
                    ));
                }
            }
            else if(productType.equals("phone")) {
                allPhones = MySqlDataStoreUtilities.getPhones();
                if(allPhones.containsKey(productId)) {
                    msg = "Product already available";
                } else {
                    allPhones.put(productId, new Phone(
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
                            productType,
                            numberOfAvailableProducts,
                            numberOfItemsSold
                    ));
                }
            }
            else if(productType.equals("smartWatch")) {
                allSmartWatches = MySqlDataStoreUtilities.getSmartWatches();
                if(allSmartWatches.containsKey(productId)) {
                    msg = "Product already available";
                } else {
                    allSmartWatches.put(productId, new SmartWatch(
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
                            productType,
                            numberOfAvailableProducts,
                            numberOfItemsSold
                    ));
                }
            }
            else if(productType.equals("voiceAssistant")) {
                allVoiceAssistants = MySqlDataStoreUtilities.getVoiceAssistants();
                if(allVoiceAssistants.containsKey(productId))
                {
                    msg = "Product already available";
                } else {
                    allVoiceAssistants.put(productId, new VoiceAssistant(
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
                            productType,
                            numberOfAvailableProducts,
                            numberOfItemsSold
                    ));
                }
            }
            else if(productType.equals("petTracker")) {
                allPetTracker = MySqlDataStoreUtilities.getPetTracker();
                if(allPetTracker.containsKey(productId))
                {
                    msg = "Product already available";
                } else {
                    allPetTracker.put(productId, new PetTracker(
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
                            productType,
                            numberOfAvailableProducts,
                            numberOfItemsSold
                    ));
                }
            }
            else if(productType.equals("virtualReality")) {
                allVirtualReality = MySqlDataStoreUtilities.getVirtualReality();
                if(allVirtualReality.containsKey(productId))
                {
                    msg = "Product already available";
                } else {
                    allVirtualReality.put(productId, new VirtualReality(
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
                            productType,
                            numberOfAvailableProducts,
                            numberOfItemsSold
                    ));
                }
            }

            /*
            else if (productType.equals("accessories"))
            {
                if(!request.getParameter("product").isEmpty())
                {
                    prod = request.getParameter("product");
                    allconsoles = MySqlDataStoreUtilities.getConsoles();
                    if(allconsoles.containsKey(prod))
                    {
                        allaccessories = MySqlDataStoreUtilities.getAccessories();
                        if(allaccessories.containsKey(productId))
                        {
                            msg = "Product already available";
                        }
                    }
                    else
                    {
                        msg = "The product related to accessories is not available";
                    }
                }

                else
                {
                    msg = "Please add the prodcut name";
                }

            }
            */

            if (msg.equals("good")) {
                try {
                    msg = MySqlDataStoreUtilities.addProduct(
                            productType,
                            productId,
                            productName,
                            productPrice,
                            fileName,
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

                    uploadFile(part, productType);
                }
                catch(Exception e) {
                    msg = "Product cannot be inserted";
                }
                msg = "Product has been successfully added";
            }
        }
        else if(action.equals("Update Product")) {
            System.out.println("Product Type: " + productType);
            if (productType.equals("FitnessWatch")) {
                allFitnessWatches = MySqlDataStoreUtilities.getFitnessWatches();
                if(!allFitnessWatches.containsKey(productId)) {
                    msg = "Product not available";
                }
            }
            else if (productType.equals("Headphone")) {
                allHeadphones = MySqlDataStoreUtilities.getHeadphones();
                if(!allHeadphones.containsKey(productId)) {
                    msg = "Product not available";
                }
            }
            else if (productType.equals("Laptop")) {
                allLaptops = MySqlDataStoreUtilities.getLaptops();
                if(!allLaptops.containsKey(productId)) {
                    msg = "Product not available";
                }
            }
            else if (productType.equals("Phone")) {
                System.out.println("Phone here!");
                allPhones = MySqlDataStoreUtilities.getPhones();
                System.out.println(productId + ", " + allPhones.size());
                if(!allPhones.containsKey(productId)) {
                    msg = "Product not available";
                }
            }
            else if (productType.equals("Smart Watch")) {
                allSmartWatches = MySqlDataStoreUtilities.getSmartWatches();
                if(!allSmartWatches.containsKey(productId)) {
                    msg = "Product not available";
                }
            }
            else if (productType.equals("Voice Assistant"))
            {
                allVoiceAssistants = MySqlDataStoreUtilities.getVoiceAssistants();
                if(!allVoiceAssistants.containsKey(productId)) {
                    msg = "Product not available";
                }
            }

            if (msg.equals("good")) {
                try {
                    msg = MySqlDataStoreUtilities.updateProduct(
                            productType,
                            productId,
                            productName,
                            productPrice,
                            fileName,
                            productManufacturer,
                            productCondition,
                            productDiscount,
                            productRebate,
                            productDescription,
                            productHasWarranty,
                            productWarranty,
                            numberOfAvailableProducts,
                            numberOfItemsSold
                    );

                    uploadFile(part, productType);
                }
                catch(Exception e) {
                    msg = "Product cannot be updated";
                }
                msg = "Product has been successfully updated";
            }
        }
        else if(action.equals("Delete")) {
            try {
                System.out.print("delete the prodcut");
                msg = MySqlDataStoreUtilities.deleteProduct(productId);
            }
            catch(Exception e) {
                msg = "Product cannot be deleted";
            }
            msg = "Product has been successfully deleted";
        }

        pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
        pw.print("<a style='font-size: 24px;'>Product</a>");
        pw.print("</h2><div class='entry'>");
        pw.print("<h4 style='color:blue'>"+ msg +"</h4>");
        pw.print("</div></div></div>");
        utility.printHtml("Footer.html");
    }

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename"))
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
        }
        return "filename.jpg";
    }

    private void uploadFile(Part part, String productType) throws IOException {
        OutputStream out = null;
        InputStream filecontent = null;
        String str1 = "";
        String fileName = getFileName(part);
        switch (productType) {
            case "Phone":
                str1 = "phones";
                break;
            case "Fitness Watch":
            case "Smart Watch":
            case "Headphone":
            case "Virtual Reality":
            case "Pet Tracker":
                str1 = "wearables";
                break;
            case "Laptop":
                str1 = "laptops";
                break;
            case "Voice Assistant":
                str1 = "assistant";
                break;
            case "Accessory":
                str1 = "accessories";
                break;
        }
        try {
            String uploadFilePath = getServletContext().getRealPath("") + File.separator + "images" + File.separator + str1;
            out = new FileOutputStream(new File(uploadFilePath + File.separator
                    + fileName));
            filecontent = part.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
        } catch (FileNotFoundException fne) {
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
            if (filecontent != null) {
                filecontent.close();
            }
        }
    }
}