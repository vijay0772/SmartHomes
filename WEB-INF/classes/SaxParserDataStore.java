
/*********


http://www.saxproject.org/

SAX is the Simple API for XML, originally a Java-only API. 
SAX was the first widely adopted API for XML in Java, and is a �de facto� standard. 
The current version is SAX 2.0.1, and there are versions for several programming language environments other than Java. 

The following URL from Oracle is the JAVA documentation for the API

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html


*********/
import org.xml.sax.InputSource;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.io.StringReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

////////////////////////////////////////////////////////////

/**************
 * 
 * SAX parser use callback function to notify client object of the XML document
 * structure.
 * You should extend DefaultHandler and override the method when parsin the XML
 * document
 * 
 ***************/

////////////////////////////////////////////////////////////

public class SaxParserDataStore extends DefaultHandler {
	Console console;
	Game game;
	Tablet tablet;
	Accessory accessory;
	Television television;
	SoundSystem soundSystem;
	Phone phone;
	Laptop laptop;
	VoiceAssistant voiceAssistant;
	FitnessWatch fitnessWatch;
	SmartWatch smartWatch;
	Headphone headphone;
	WirelessPlan wirelessPlan;
	VirtualReality virtualReality;
	PetTracker petTracker;

	static HashMap<String, Console> consoles;
	static HashMap<String, Game> games;
	static HashMap<String, Tablet> tablets;
	static HashMap<String, Accessory> accessories;
	static HashMap<String, Television> televisions;
	static HashMap<String, SoundSystem> soundSystems;
	static HashMap<String, Phone> phones;
	static HashMap<String, Laptop> laptops;
	static HashMap<String, VoiceAssistant> voiceAssistants;
	static HashMap<String, FitnessWatch> fitnessWatches;
	static HashMap<String, SmartWatch> smartWatches;
	static HashMap<String, Headphone> headphones;
	static HashMap<String, WirelessPlan> wirelessPlans;
	static HashMap<String, VirtualReality> virtualRealities;
	static HashMap<String, PetTracker> petTrackers;
	String consoleXmlFileName;
	HashMap<String, String> accessoryHashMap;
	String elementValueRead;
	String currentElement = "";

	public SaxParserDataStore() {
	}

	public SaxParserDataStore(String consoleXmlFileName) {
		this.consoleXmlFileName = consoleXmlFileName;
		consoles = new HashMap<String, Console>();
		games = new HashMap<String, Game>();
		tablets = new HashMap<String, Tablet>();
		accessories = new HashMap<String, Accessory>();
		accessoryHashMap = new HashMap<String, String>();
		televisions = new HashMap<String, Television>();
		soundSystems = new HashMap<String, SoundSystem>();
		phones = new HashMap<String, Phone>();
		laptops = new HashMap<String, Laptop>();
		voiceAssistants = new HashMap<String, VoiceAssistant>();
		fitnessWatches = new HashMap<String, FitnessWatch>();
		smartWatches = new HashMap<String, SmartWatch>();
		headphones = new HashMap<String, Headphone>();
		wirelessPlans = new HashMap<String, WirelessPlan>();
		virtualRealities = new HashMap<>();
		petTrackers = new HashMap<>();
		parseDocument();
	}

	// parse the xml using sax parser to get the data
	private void parseDocument() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			parser.parse(consoleXmlFileName, this);
		} catch (ParserConfigurationException e) {
			System.out.println("ParserConfig error");
		} catch (SAXException e) {
			System.out.println("SAXException : xml not well formed");
		} catch (IOException e) {
			System.out.println("IO error");
		}
	}

	////////////////////////////////////////////////////////////

	/*************
	 * 
	 * There are a number of methods to override in SAX handler when parsing your
	 * XML document :
	 * 
	 * Group 1. startDocument() and endDocument() : Methods that are called at the
	 * start and end of an XML document.
	 * Group 2. startElement() and endElement() : Methods that are called at the
	 * start and end of a document element.
	 * Group 3. characters() : Method that is called with the text content in
	 * between the start and end tags of an XML document element.
	 * 
	 * 
	 * There are few other methods that you could use for notification for different
	 * purposes, check the API at the following URL:
	 * 
	 * https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html
	 * 
	 ***************/

	////////////////////////////////////////////////////////////

	// when xml start element is parsed store the id into respective hashmap for
	// console,games etc
	@Override
	public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {

		if (elementName.equalsIgnoreCase("console")) {
			currentElement = "console";
			console = new Console();
			console.setId(attributes.getValue("id"));
		}
		if (elementName.equalsIgnoreCase("tablet")) {
			currentElement = "tablet";
			tablet = new Tablet();
			tablet.setId(attributes.getValue("id"));
		}
		if (elementName.equalsIgnoreCase("game")) {
			currentElement = "game";
			game = new Game();
			game.setId(attributes.getValue("id"));
		}
		if (elementName.equals("accessory") && !currentElement.equals("console")) {
			currentElement = "accessory";
			accessory = new Accessory();
			accessory.setId(attributes.getValue("id"));
		}
		if (elementName.equalsIgnoreCase("television")) {
			currentElement = "television";
			television = new Television();
			television.setId(attributes.getValue("id"));
		}
		if (elementName.equalsIgnoreCase("soundSystem")) {
			currentElement = "soundSystem";
			soundSystem = new SoundSystem();
			soundSystem.setId(attributes.getValue("id"));
		}
		if (elementName.equalsIgnoreCase("phone")) {
			currentElement = "phone";
			phone = new Phone();
			phone.setId(attributes.getValue("id"));
		}
		if (elementName.equalsIgnoreCase("laptop")) {
			currentElement = "laptop";
			laptop = new Laptop();
			laptop.setId(attributes.getValue("id"));
		}
		if (elementName.equalsIgnoreCase("voiceAssistant")) {
			currentElement = "voiceAssistant";
			voiceAssistant = new VoiceAssistant();
			voiceAssistant.setId(attributes.getValue("id"));
		}
		if (elementName.equalsIgnoreCase("fitnessWatch")) {
			currentElement = "fitnessWatch";
			fitnessWatch = new FitnessWatch();
			fitnessWatch.setId(attributes.getValue("id"));
		}
		if (elementName.equalsIgnoreCase("smartWatch")) {
			currentElement = "smartWatch";
			smartWatch = new SmartWatch();
			smartWatch.setId(attributes.getValue("id"));
		}
		if (elementName.equalsIgnoreCase("headphone")) {
			currentElement = "headphone";
			headphone = new Headphone();
			headphone.setId(attributes.getValue("id"));
		}
		if (elementName.equalsIgnoreCase("wirelessPlan")) {
			currentElement = "wirelessPlan";
			wirelessPlan = new WirelessPlan();
			wirelessPlan.setId(attributes.getValue("id"));
		}
		if (elementName.equalsIgnoreCase("virtualReality")) {
			currentElement = "virtualReality";
			virtualReality = new VirtualReality();
			virtualReality.setId(attributes.getValue("id"));
		}
		if (elementName.equalsIgnoreCase("petTracker")) {
			currentElement = "petTracker";
			petTracker = new PetTracker();
			petTracker.setId(attributes.getValue("id"));
		}

	}

	// when xml end element is parsed store the data into respective hashmap for
	// console,games etc respectively
	@Override
	public void endElement(String str1, String str2, String element) throws SAXException {
		try {

			if (element.equals("console")) {
				consoles.put(console.getId(), console);
				return;
			}

			if (element.equals("tablet")) {
				tablets.put(tablet.getId(), tablet);
				return;
			}
			if (element.equals("game")) {
				games.put(game.getId(), game);
				return;
			}
			if (element.equals("television")) {
				televisions.put(television.getId(), television);
				return;
			}
			if (element.equals("soundSystem")) {
				soundSystems.put(soundSystem.getId(), soundSystem);
				return;
			}
			if (element.equals("phone")) {
				phones.put(phone.getId(), phone);
				/*
				 * MySqlDataStoreUtilities.addProduct(
				 * phone.getProductType(),
				 * phone.getId(),
				 * phone.getName(),
				 * phone.getPrice(),
				 * phone.getImage(),
				 * phone.getRetailer(),
				 * phone.getCondition(),
				 * phone.getDiscount(),
				 * phone.getRebate(),
				 * phone.getDescription(),
				 * phone.isHasWarranty(),
				 * phone.getWarrantyPrice(),
				 * phone.getNumberOfAvailableProducts(),
				 * phone.getNumberOfItemsSold(),
				 * ""
				 * );
				 */
				return;
			}
			if (element.equals("laptop")) {
				laptops.put(laptop.getId(), laptop);
				/*
				 * MySqlDataStoreUtilities.addProduct(
				 * laptop.getProductType(),
				 * laptop.getId(),
				 * laptop.getName(),
				 * laptop.getPrice(),
				 * laptop.getImage(),
				 * laptop.getRetailer(),
				 * laptop.getCondition(),
				 * laptop.getDiscount(),
				 * laptop.getRebate(),
				 * laptop.getDescription(),
				 * laptop.isHasWarranty(),
				 * laptop.getWarrantyPrice(),
				 * laptop.getNumberOfAvailableProducts(),
				 * laptop.getNumberOfItemsSold(),
				 * ""
				 * );
				 */
				return;
			}
			if (element.equals("voiceAssistant")) {
				voiceAssistants.put(voiceAssistant.getId(), voiceAssistant);
				/*
				 * MySqlDataStoreUtilities.addProduct(
				 * voiceAssistant.getProductType(),
				 * voiceAssistant.getId(),
				 * voiceAssistant.getName(),
				 * voiceAssistant.getPrice(),
				 * voiceAssistant.getImage(),
				 * voiceAssistant.getRetailer(),
				 * voiceAssistant.getCondition(),
				 * voiceAssistant.getDiscount(),
				 * voiceAssistant.getRebate(),
				 * voiceAssistant.getDescription(),
				 * voiceAssistant.isHasWarranty(),
				 * voiceAssistant.getWarrantyPrice(),
				 * voiceAssistant.getNumberOfAvailableProducts(),
				 * voiceAssistant.getNumberOfItemsSold(),
				 * ""
				 * );
				 */
				return;
			}
			if (element.equals("fitnessWatch")) {
				fitnessWatches.put(fitnessWatch.getId(), fitnessWatch);
				System.out.println("fitnessWatch " + fitnessWatch.toString());
				/*
				 * MySqlDataStoreUtilities.addProduct(
				 * fitnessWatch.getProductType(),
				 * fitnessWatch.getId(),
				 * fitnessWatch.getName(),
				 * fitnessWatch.getPrice(),
				 * fitnessWatch.getImage(),
				 * fitnessWatch.getRetailer(),
				 * fitnessWatch.getCondition(),
				 * fitnessWatch.getDiscount(),
				 * fitnessWatch.getRebate(),
				 * fitnessWatch.getDescription(),
				 * fitnessWatch.isHasWarranty(),
				 * fitnessWatch.getWarrantyPrice(),
				 * fitnessWatch.getNumberOfAvailableProducts(),
				 * fitnessWatch.getNumberOfItemsSold(),
				 * ""
				 * );
				 */
				return;
			}
			if (element.equals("smartWatch")) {
				smartWatches.put(smartWatch.getId(), smartWatch);
				System.out.println("smartWatch " + smartWatch.toString());
				/*
				 * MySqlDataStoreUtilities.addProduct(
				 * smartWatch.getProductType(),
				 * smartWatch.getId(),
				 * smartWatch.getName(),
				 * smartWatch.getPrice(),
				 * smartWatch.getImage(),
				 * smartWatch.getRetailer(),
				 * smartWatch.getCondition(),
				 * smartWatch.getDiscount(),
				 * smartWatch.getRebate(),
				 * smartWatch.getDescription(),
				 * smartWatch.isHasWarranty(),
				 * smartWatch.getWarrantyPrice(),
				 * smartWatch.getNumberOfAvailableProducts(),
				 * smartWatch.getNumberOfItemsSold(),
				 * ""
				 * );
				 */
				return;
			}
			if (element.equals("headphone")) {
				headphones.put(headphone.getId(), headphone);
				/*
				 * MySqlDataStoreUtilities.addProduct(
				 * headphone.getProductType(),
				 * headphone.getId(),
				 * headphone.getName(),
				 * headphone.getPrice(),
				 * headphone.getImage(),
				 * headphone.getRetailer(),
				 * headphone.getCondition(),
				 * headphone.getDiscount(),
				 * headphone.getRebate(),
				 * headphone.getDescription(),
				 * headphone.isHasWarranty(),
				 * headphone.getWarrantyPrice(),
				 * headphone.getNumberOfAvailableProducts(),
				 * headphone.getNumberOfItemsSold(),
				 * ""
				 * );
				 */
				return;
			}
			if (element.equals("wirelessPlan")) {
				wirelessPlans.put(wirelessPlan.getId(), wirelessPlan);
				return;
			}
			if (element.equals("virtualReality")) {
				virtualRealities.put(virtualReality.getId(), virtualReality);
				/*
				 * MySqlDataStoreUtilities.addProduct(
				 * virtualReality.getProductType(),
				 * virtualReality.getId(),
				 * virtualReality.getName(),
				 * virtualReality.getPrice(),
				 * virtualReality.getImage(),
				 * virtualReality.getRetailer(),
				 * virtualReality.getCondition(),
				 * virtualReality.getDiscount(),
				 * virtualReality.getRebate(),
				 * virtualReality.getDescription(),
				 * virtualReality.isHasWarranty(),
				 * virtualReality.getWarrantyPrice(),
				 * virtualReality.getNumberOfAvailableProducts(),
				 * virtualReality.getNumberOfItemsSold(),
				 * ""
				 * );
				 */
				return;
			}
			if (element.equals("petTracker")) {
				petTrackers.put(petTracker.getId(), petTracker);
				/*
				 * MySqlDataStoreUtilities.addProduct(
				 * petTracker.getProductType(),
				 * petTracker.getId(),
				 * petTracker.getName(),
				 * petTracker.getPrice(),
				 * petTracker.getImage(),
				 * petTracker.getRetailer(),
				 * petTracker.getCondition(),
				 * petTracker.getDiscount(),
				 * petTracker.getRebate(),
				 * petTracker.getDescription(),
				 * petTracker.isHasWarranty(),
				 * petTracker.getWarrantyPrice(),
				 * petTracker.getNumberOfAvailableProducts(),
				 * petTracker.getNumberOfItemsSold(),
				 * ""
				 * );
				 */
				return;
			}
			if (element.equals("accessory") && currentElement.equals("accessory")) {
				accessories.put(accessory.getId(), accessory);
				return;
			}
			if (element.equals("accessory") && currentElement.equals("console")) {
				accessoryHashMap.put(elementValueRead, elementValueRead);
			}
			if (element.equalsIgnoreCase("accessories") && currentElement.equals("console")) {
				console.setAccessories(accessoryHashMap);
				accessoryHashMap = new HashMap<String, String>();
				return;
			}
			if (element.equalsIgnoreCase("image")) {
				if (currentElement.equals("console"))
					console.setImage(elementValueRead);
				if (currentElement.equals("game"))
					game.setImage(elementValueRead);
				if (currentElement.equals("tablet"))
					tablet.setImage(elementValueRead);
				if (currentElement.equals("accessory"))
					accessory.setImage(elementValueRead);
				if (currentElement.equals("television"))
					television.setImage(elementValueRead);
				if (currentElement.equals("soundSystem"))
					soundSystem.setImage(elementValueRead);
				if (currentElement.equals("phone"))
					phone.setImage(elementValueRead);
				if (currentElement.equals("laptop"))
					laptop.setImage(elementValueRead);
				if (currentElement.equals("voiceAssistant"))
					voiceAssistant.setImage(elementValueRead);
				if (currentElement.equals("fitnessWatch"))
					fitnessWatch.setImage(elementValueRead);
				if (currentElement.equals("smartWatch"))
					smartWatch.setImage(elementValueRead);
				if (currentElement.equals("headphone"))
					headphone.setImage(elementValueRead);
				if (currentElement.equals("wirelessPlan"))
					wirelessPlan.setImage(elementValueRead);
				if (currentElement.equals("virtualReality"))
					virtualReality.setImage(elementValueRead);
				if (currentElement.equals("petTracker"))
					petTracker.setImage(elementValueRead);

				return;
			}

			if (element.equalsIgnoreCase("discount")) {
				if (currentElement.equals("console"))
					console.setDiscount(Double.parseDouble(elementValueRead));
				if (currentElement.equals("game"))
					game.setDiscount(Double.parseDouble(elementValueRead));
				if (currentElement.equals("tablet"))
					tablet.setDiscount(Double.parseDouble(elementValueRead));
				if (currentElement.equals("accessory"))
					accessory.setDiscount(Double.parseDouble(elementValueRead));
				if (currentElement.equals("television"))
					television.setDiscount(Double.parseDouble(elementValueRead));
				if (currentElement.equals("soundSystem"))
					soundSystem.setDiscount(Double.parseDouble(elementValueRead));
				if (currentElement.equals("phone"))
					phone.setDiscount(Double.parseDouble(elementValueRead));
				if (currentElement.equals("laptop"))
					laptop.setDiscount(Double.parseDouble(elementValueRead));
				if (currentElement.equals("voiceAssistant"))
					voiceAssistant.setDiscount(Double.parseDouble(elementValueRead));
				if (currentElement.equals("fitnessWatch"))
					fitnessWatch.setDiscount(Double.parseDouble(elementValueRead));
				if (currentElement.equals("smartWatch"))
					smartWatch.setDiscount(Double.parseDouble(elementValueRead));
				if (currentElement.equals("headphone"))
					headphone.setDiscount(Double.parseDouble(elementValueRead));
				if (currentElement.equals("wirelessPlan"))
					wirelessPlan.setDiscount(Double.parseDouble(elementValueRead));
				if (currentElement.equals("virtualReality"))
					virtualReality.setDiscount(Double.parseDouble(elementValueRead));
				if (currentElement.equals("petTracker"))
					petTracker.setDiscount(Double.parseDouble(elementValueRead));

				return;
			}

			if (element.equalsIgnoreCase("condition")) {
				if (currentElement.equals("console"))
					console.setCondition(elementValueRead);
				if (currentElement.equals("game"))
					game.setCondition(elementValueRead);
				if (currentElement.equals("tablet"))
					tablet.setCondition(elementValueRead);
				if (currentElement.equals("accessory"))
					accessory.setCondition(elementValueRead);
				if (currentElement.equals("television"))
					television.setCondition(elementValueRead);
				if (currentElement.equals("soundSystem"))
					soundSystem.setCondition(elementValueRead);
				if (currentElement.equals("phone"))
					phone.setCondition(elementValueRead);
				if (currentElement.equals("laptop"))
					laptop.setCondition(elementValueRead);
				if (currentElement.equals("voiceAssistant"))
					voiceAssistant.setCondition(elementValueRead);
				if (currentElement.equals("fitnessWatch"))
					fitnessWatch.setCondition(elementValueRead);
				if (currentElement.equals("smartWatch"))
					smartWatch.setCondition(elementValueRead);
				if (currentElement.equals("headphone"))
					headphone.setCondition(elementValueRead);
				if (currentElement.equals("wirelessPlan"))
					wirelessPlan.setCondition(elementValueRead);
				if (currentElement.equals("virtualReality"))
					virtualReality.setDiscount(Double.parseDouble(elementValueRead));
				if (currentElement.equals("petTracker"))
					petTracker.setDiscount(Double.parseDouble(elementValueRead));
				return;
			}

			if (element.equalsIgnoreCase("manufacturer")) {
				if (currentElement.equals("console"))
					console.setRetailer(elementValueRead);
				if (currentElement.equals("game"))
					game.setRetailer(elementValueRead);
				if (currentElement.equals("tablet"))
					tablet.setRetailer(elementValueRead);
				if (currentElement.equals("accessory"))
					accessory.setRetailer(elementValueRead);
				if (currentElement.equals("television"))
					television.setRetailer(elementValueRead);
				if (currentElement.equals("soundSystem"))
					soundSystem.setRetailer(elementValueRead);
				if (currentElement.equals("phone"))
					phone.setRetailer(elementValueRead);
				if (currentElement.equals("laptop"))
					laptop.setRetailer(elementValueRead);
				if (currentElement.equals("voiceAssistant"))
					voiceAssistant.setRetailer(elementValueRead);
				if (currentElement.equals("fitnessWatch"))
					fitnessWatch.setRetailer(elementValueRead);
				if (currentElement.equals("smartWatch"))
					smartWatch.setRetailer(elementValueRead);
				if (currentElement.equals("headphone"))
					headphone.setRetailer(elementValueRead);
				if (currentElement.equals("wirelessPlan"))
					wirelessPlan.setRetailer(elementValueRead);
				if (currentElement.equals("virtualReality"))
					virtualReality.setDiscount(Double.parseDouble(elementValueRead));
				if (currentElement.equals("petTracker"))
					petTracker.setDiscount(Double.parseDouble(elementValueRead));
				return;
			}

			if (element.equalsIgnoreCase("name")) {
				if (currentElement.equals("console"))
					console.setName(elementValueRead);
				if (currentElement.equals("game"))
					game.setName(elementValueRead);
				if (currentElement.equals("tablet"))
					tablet.setName(elementValueRead);
				if (currentElement.equals("accessory"))
					accessory.setName(elementValueRead);
				if (currentElement.equals("television"))
					television.setName(elementValueRead);
				if (currentElement.equals("soundSystem"))
					soundSystem.setName(elementValueRead);
				if (currentElement.equals("phone"))
					phone.setName(elementValueRead);
				if (currentElement.equals("laptop"))
					laptop.setName(elementValueRead);
				if (currentElement.equals("voiceAssistant"))
					voiceAssistant.setName(elementValueRead);
				if (currentElement.equals("fitnessWatch"))
					fitnessWatch.setName(elementValueRead);
				if (currentElement.equals("smartWatch"))
					smartWatch.setName(elementValueRead);
				if (currentElement.equals("headphone"))
					headphone.setName(elementValueRead);
				if (currentElement.equals("wirelessPlan"))
					wirelessPlan.setName(elementValueRead);
				if (currentElement.equals("virtualReality"))
					virtualReality.setDiscount(Double.parseDouble(elementValueRead));
				if (currentElement.equals("petTracker"))
					petTracker.setDiscount(Double.parseDouble(elementValueRead));
				return;
			}

			if (element.equalsIgnoreCase("price")) {
				if (currentElement.equals("console"))
					console.setPrice(Double.parseDouble(elementValueRead));
				if (currentElement.equals("game"))
					game.setPrice(Double.parseDouble(elementValueRead));
				if (currentElement.equals("tablet"))
					tablet.setPrice(Double.parseDouble(elementValueRead));
				if (currentElement.equals("accessory"))
					accessory.setPrice(Double.parseDouble(elementValueRead));
				if (currentElement.equals("television"))
					television.setPrice(Double.parseDouble(elementValueRead));
				if (currentElement.equals("soundSystem"))
					soundSystem.setPrice(Double.parseDouble(elementValueRead));
				if (currentElement.equals("phone"))
					phone.setPrice(Double.parseDouble(elementValueRead));
				if (currentElement.equals("laptop"))
					laptop.setPrice(Double.parseDouble(elementValueRead));
				if (currentElement.equals("voiceAssistant"))
					voiceAssistant.setPrice(Double.parseDouble(elementValueRead));
				if (currentElement.equals("fitnessWatch"))
					fitnessWatch.setPrice(Double.parseDouble(elementValueRead));
				if (currentElement.equals("smartWatch"))
					smartWatch.setPrice(Double.parseDouble(elementValueRead));
				if (currentElement.equals("headphone"))
					headphone.setPrice(Double.parseDouble(elementValueRead));
				if (currentElement.equals("wirelessPlan"))
					wirelessPlan.setPrice(Double.parseDouble(elementValueRead));
				if (currentElement.equals("virtualReality"))
					virtualReality.setDiscount(Double.parseDouble(elementValueRead));
				if (currentElement.equals("petTracker"))
					petTracker.setDiscount(Double.parseDouble(elementValueRead));
				return;
			}

			if (element.equalsIgnoreCase("description")) {
				if (currentElement.equals("phone"))
					phone.setDescription(elementValueRead);
				if (currentElement.equals("laptop"))
					laptop.setDescription(elementValueRead);
				if (currentElement.equals("voiceAssistant"))
					voiceAssistant.setDescription(elementValueRead);
				if (currentElement.equals("fitnessWatch"))
					fitnessWatch.setDescription(elementValueRead);
				if (currentElement.equals("smartWatch"))
					smartWatch.setDescription(elementValueRead);
				if (currentElement.equals("headphone"))
					headphone.setDescription(elementValueRead);
				if (currentElement.equals("virtualReality"))
					virtualReality.setDescription(elementValueRead);
				if (currentElement.equals("petTracker"))
					petTracker.setDescription(elementValueRead);

				return;
			}

			if (element.equalsIgnoreCase("type")) {
				if (currentElement.equals("phone"))
					phone.setProductType(elementValueRead);
				if (currentElement.equals("laptop"))
					laptop.setProductType(elementValueRead);
				if (currentElement.equals("voiceAssistant"))
					voiceAssistant.setProductType(elementValueRead);
				if (currentElement.equals("fitnessWatch"))
					fitnessWatch.setProductType(elementValueRead);
				if (currentElement.equals("smartWatch"))
					smartWatch.setProductType(elementValueRead);
				if (currentElement.equals("headphone"))
					headphone.setProductType(elementValueRead);
				if (currentElement.equals("virtualReality"))
					virtualReality.setProductType(elementValueRead);
				if (currentElement.equals("petTracker"))
					petTracker.setProductType(elementValueRead);
				return;
			}

			if (element.equalsIgnoreCase("has-warranty")) {
				if (currentElement.equals("phone"))
					phone.setHasWarranty(elementValueRead.equalsIgnoreCase("Yes"));
				if (currentElement.equals("laptop"))
					laptop.setHasWarranty(elementValueRead.equalsIgnoreCase("Yes"));
				if (currentElement.equals("voiceAssistant"))
					voiceAssistant.setHasWarranty(elementValueRead.equalsIgnoreCase("Yes"));
				if (currentElement.equals("fitnessWatch"))
					fitnessWatch.setHasWarranty(elementValueRead.equalsIgnoreCase("Yes"));
				if (currentElement.equals("smartWatch"))
					smartWatch.setHasWarranty(elementValueRead.equalsIgnoreCase("Yes"));
				if (currentElement.equals("headphone"))
					headphone.setHasWarranty(elementValueRead.equalsIgnoreCase("Yes"));
				if (currentElement.equals("virtualReality"))
					virtualReality.setHasWarranty(elementValueRead.equalsIgnoreCase("Yes"));
				if (currentElement.equals("petTracker"))
					petTracker.setHasWarranty(elementValueRead.equalsIgnoreCase("Yes"));

				return;
			}

			if (element.equalsIgnoreCase("warranty")) {
				if (currentElement.equals("phone"))
					phone.setWarrantyPrice(Double.parseDouble(elementValueRead));
				if (currentElement.equals("laptop"))
					laptop.setWarrantyPrice(Double.parseDouble(elementValueRead));
				if (currentElement.equals("voiceAssistant"))
					voiceAssistant.setWarrantyPrice(Double.parseDouble(elementValueRead));
				if (currentElement.equals("fitnessWatch"))
					fitnessWatch.setWarrantyPrice(Double.parseDouble(elementValueRead));
				if (currentElement.equals("smartWatch"))
					smartWatch.setWarrantyPrice(Double.parseDouble(elementValueRead));
				if (currentElement.equals("headphone"))
					headphone.setWarrantyPrice(Double.parseDouble(elementValueRead));
				if (currentElement.equals("virtualReality"))
					virtualReality.setWarrantyPrice(Double.parseDouble(elementValueRead));
				if (currentElement.equals("petTracker"))
					petTracker.setWarrantyPrice(Double.parseDouble(elementValueRead));

				return;
			}

			if (element.equalsIgnoreCase("rebate")) {
				if (currentElement.equals("phone"))
					phone.setRebate(Integer.parseInt(elementValueRead));
				if (currentElement.equals("laptop"))
					laptop.setRebate(Integer.parseInt(elementValueRead));
				if (currentElement.equals("voiceAssistant"))
					voiceAssistant.setRebate(Integer.parseInt(elementValueRead));
				if (currentElement.equals("fitnessWatch"))
					fitnessWatch.setRebate(Integer.parseInt(elementValueRead));
				if (currentElement.equals("smartWatch"))
					smartWatch.setRebate(Integer.parseInt(elementValueRead));
				if (currentElement.equals("headphone"))
					headphone.setRebate(Integer.parseInt(elementValueRead));
				if (currentElement.equals("virtualReality"))
					virtualReality.setRebate(Integer.parseInt(elementValueRead));
				if (currentElement.equals("petTracker"))
					petTracker.setRebate(Integer.parseInt(elementValueRead));

				return;
			}

			if (element.equalsIgnoreCase("numberOfAvailableProducts")) {
				if (currentElement.equals("phone"))
					phone.setNumberOfAvailableProducts(Integer.parseInt(elementValueRead));
				if (currentElement.equals("laptop"))
					laptop.setNumberOfAvailableProducts(Integer.parseInt(elementValueRead));
				if (currentElement.equals("voiceAssistant"))
					voiceAssistant.setNumberOfAvailableProducts(Integer.parseInt(elementValueRead));
				if (currentElement.equals("fitnessWatch"))
					fitnessWatch.setNumberOfAvailableProducts(Integer.parseInt(elementValueRead));
				if (currentElement.equals("smartWatch"))
					smartWatch.setNumberOfAvailableProducts(Integer.parseInt(elementValueRead));
				if (currentElement.equals("headphone"))
					headphone.setNumberOfAvailableProducts(Integer.parseInt(elementValueRead));
				if (currentElement.equals("virtualReality"))
					virtualReality.setNumberOfAvailableProducts(Integer.parseInt(elementValueRead));
				if (currentElement.equals("petTracker"))
					petTracker.setNumberOfAvailableProducts(Integer.parseInt(elementValueRead));

				return;
			}

			if (element.equalsIgnoreCase("numberOfItemsSold")) {
				if (currentElement.equals("phone"))
					phone.setNumberOfItemsSold(Integer.parseInt(elementValueRead));
				if (currentElement.equals("laptop"))
					laptop.setNumberOfItemsSold(Integer.parseInt(elementValueRead));
				if (currentElement.equals("voiceAssistant"))
					voiceAssistant.setNumberOfItemsSold(Integer.parseInt(elementValueRead));
				if (currentElement.equals("fitnessWatch"))
					fitnessWatch.setNumberOfItemsSold(Integer.parseInt(elementValueRead));
				if (currentElement.equals("smartWatch"))
					smartWatch.setNumberOfItemsSold(Integer.parseInt(elementValueRead));
				if (currentElement.equals("headphone"))
					headphone.setNumberOfItemsSold(Integer.parseInt(elementValueRead));
				if (currentElement.equals("virtualReality"))
					virtualReality.setNumberOfItemsSold(Integer.parseInt(elementValueRead));
				if (currentElement.equals("petTracker"))
					petTracker.setNumberOfItemsSold(Integer.parseInt(elementValueRead));

				return;
			}
		} catch (Exception e) {
			System.out.println("element: " + currentElement + "  " + elementValueRead + "  " + e.getMessage());
		}

	}

	// get each element in xml tag
	@Override
	public void characters(char[] content, int begin, int end) throws SAXException {
		elementValueRead = new String(content, begin, end);
	}

	/////////////////////////////////////////
	// Kick-Start SAX in main //
	////////////////////////////////////////

	// call the constructor to parse the xml and get product details
	public static void addHashmap() {
		String TOMCAT_HOME = System.getProperty("catalina.home");
		new SaxParserDataStore(TOMCAT_HOME + "\\webapps\\SmartHomes\\ProductCatalog.xml");
	}
}
