import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;

import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.AggregationOutput;
import java.util.*;
import java.net.UnknownHostException;

public class MongoDBDataStoreUtilities
{
    static DBCollection myReviews;
    public static DBCollection getConnection()
    {
        MongoClient mongo;
        mongo = new MongoClient("localhost", 27017);

        //DB db = mongo.getDB("CustomerReviews");
        DB db = mongo.getDB("smartportablesreviews");
        myReviews = db.getCollection("myReviews");
        return myReviews;
    }

    public static String insertCustomerReview(Review review) {
        try {
            getConnection();

            BasicDBObject doc = new BasicDBObject("title", "myReviews").
                    append("productModelName", review.getProductModelName()).
                    append("productCategory", review.getProductCategory()).
                    append("productPrice", review.getProductPrice()).
                    append("storeId", review.getStoreId()).
                    append("storeCity", review.getStoreCity()).
                    append("storeState", review.getStoreState()).
                    append("storeZip", review.getStoreZip()).
                    append("productOnSale", review.getProductOnSale()).
                    append("manufacturerName", review.getManufacturerName()).
                    append("manufacturerRebate", review.getManufacturerRebate()).
                    append("userId", review.getUserId()).
                    append("userAge", review.getUserAge()).
                    append("userGender", review.getUserGender()).
                    append("userOccupation", review.getUserOccupation()).
                    append("reviewRating", review.getReviewRating()).
                    append("reviewDate", review.getReviewDate()).
                    append("reviewText", review.getReviewText());
            myReviews.insert(doc);
            return "Successfull";
        }
        catch(Exception e)
        {
            return "UnSuccessfull";
        }
    }

    public static HashMap<String, ArrayList<Review>> selectReview()
    {
        HashMap<String, ArrayList<Review>> reviews=null;

        try
        {
            getConnection();
            DBCursor cursor = myReviews.find();
            reviews=new HashMap<String, ArrayList<Review>>();
            while (cursor.hasNext())
            {
                BasicDBObject obj = (BasicDBObject) cursor.next();

                if(!reviews.containsKey(obj.getString("productModelName")))
                {
                    ArrayList<Review> arr = new ArrayList<Review>();
                    reviews.put(obj.getString("productModelName"), arr);
                }
                ArrayList<Review> listReview = reviews.get(obj.getString("productModelName"));
                Review review = new Review(
                        obj.getString("productModelName"),
                        obj.getString("productCategory"),
                        obj.getDouble("productPrice"),
                        obj.getString("storeId"),
                        obj.getString("storeCity"),
                        obj.getString("storeState"),
                        obj.getString("storeZip"),
                        obj.getString("productOnSale"),
                        obj.getString("manufacturerName"),
                        obj.getString("manufacturerRebate"),
                        obj.getString("userId"),
                        obj.getInt("userAge"),
                        obj.getString("userGender"),
                        obj.getString("userOccupation"),
                        obj.getInt("reviewRating"),
                        obj.getString("reviewDate"),
                        obj.getString("reviewText"));
                //add to review hashmap
                listReview.add(review);
            }
            return reviews;
        }
        catch(Exception e)
        {
            reviews=null;
            return reviews;
        }
    }

    public static  ArrayList <Bestrating> topProducts()
    {
        ArrayList <Bestrating> Bestrate = new ArrayList <Bestrating> ();
        try
        {

            getConnection();
            int retlimit =5;
            DBObject sort = new BasicDBObject();
            sort.put("reviewRating",-1);
            DBCursor cursor = myReviews.find().limit(retlimit).sort(sort);
            while(cursor.hasNext())
            {
                BasicDBObject obj = (BasicDBObject) cursor.next();
                String prodcutnm = obj.get("productModelName").toString();
                String rating = obj.get("reviewRating").toString();
                Bestrating best = new Bestrating(prodcutnm,rating);
                Bestrate.add(best);
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return Bestrate;
    }

    public static ArrayList <Mostsoldzip> mostSoldZip()
    {
        ArrayList <Mostsoldzip> mostSoldzip = new ArrayList <Mostsoldzip> ();
        try
        {

            getConnection();
            DBObject groupProducts = new BasicDBObject("_id", "$storeZip");
            groupProducts.put("count", new BasicDBObject("$sum",1));
            DBObject group = new BasicDBObject("$group", groupProducts);
            DBObject limit=new BasicDBObject();
            limit=new BasicDBObject("$limit", 5);

            DBObject sortFields = new BasicDBObject("count", -1);
            DBObject sort = new BasicDBObject("$sort", sortFields);
            AggregationOutput output = myReviews.aggregate(group, sort, limit);
            for (DBObject res : output.results())
            {
                String zipcode =(res.get("_id")).toString();
                String count = (res.get("count")).toString();
                Mostsoldzip mostsldzip = new Mostsoldzip(zipcode,count);
                mostSoldzip.add(mostsldzip);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return mostSoldzip;
    }

    public static ArrayList <Mostsold> mostSoldProducts()
    {
        ArrayList <Mostsold> mostSold = new ArrayList <Mostsold> ();
        try
        {
            getConnection();
            DBObject groupProducts = new BasicDBObject("_id","$productModelName");
            groupProducts.put("count",new BasicDBObject("$sum",1));
            DBObject group = new BasicDBObject("$group",groupProducts);
            DBObject limit=new BasicDBObject();
            limit=new BasicDBObject("$limit",5);

            DBObject sortFields = new BasicDBObject("count",-1);
            DBObject sort = new BasicDBObject("$sort",sortFields);
            AggregationOutput output = myReviews.aggregate(group,sort,limit);

            for (DBObject res : output.results())
            {
                String prodcutname =(res.get("_id")).toString();
                String count = (res.get("count")).toString();
                Mostsold mostsld = new Mostsold(prodcutname,count);
                mostSold.add(mostsld);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return mostSold;
    }

    //Get all the reviews grouped by product and zip code;
    public static ArrayList<Review> selectReviewForChart() {
        ArrayList<Review> reviewList = new ArrayList<>();
        try {

            getConnection();
            Map<String, Object> dbObjIdMap = new HashMap<String, Object>();
            dbObjIdMap.put("storeZip", "$storeZip");
            dbObjIdMap.put("productModelName", "$productModelName");
            DBObject groupFields = new BasicDBObject("_id", new BasicDBObject(dbObjIdMap));
            groupFields.put("count", new BasicDBObject("$sum", 1));
            DBObject group = new BasicDBObject("$group", groupFields);

            DBObject projectFields = new BasicDBObject("_id", 0);
            projectFields.put("storeZip", "$_id");
            projectFields.put("productModelName", "$productModelName");
            projectFields.put("reviewCount", "$count");
            DBObject project = new BasicDBObject("$project", projectFields);

            DBObject sort = new BasicDBObject();
            sort.put("reviewCount", -1);

            DBObject orderby = new BasicDBObject();
            orderby = new BasicDBObject("$sort",sort);


            AggregationOutput aggregate = myReviews.aggregate(group, project, orderby);

            System.out.println("Aggregate: " + aggregate.toString());

            for (DBObject result : aggregate.results()) {

                System.out.println("Aggregate result: " + result.toString());

                BasicDBObject obj = (BasicDBObject) result;
                Object o = com.mongodb.util.JSON.parse(obj.getString("storeZip"));
                BasicDBObject dbObj = (BasicDBObject) o;

                System.out.println("dbObj: " + dbObj.toString());

                Review review = new Review(
                        dbObj.getString("productModelName"),
                        null,
                        0,
                        null,
                        null,
                        null,
                        dbObj.getString("storeZip"),
                        null,
                        null,
                        null,
                        null,
                        0,
                        null,
                        null,
                        obj.getInt("reviewCount"),
                        null,
                        null
                );
                reviewList.add(review);
            }

            System.out.println("reviewList: " + reviewList.toArray().toString());
            return reviewList;
        }

        catch (Exception e) {
            reviewList = null;

            return reviewList;
        }
    }
}	