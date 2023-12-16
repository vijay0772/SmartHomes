import java.io.IOException;
import java.io.*;


/* 
	Review class contains class variables username,productname,reviewtext,reviewdate,reviewrating

	Review class has a constructor with Arguments username,productname,reviewtext,reviewdate,reviewrating
	  
	Review class contains getters and setters for username,productname,reviewtext,reviewdate,reviewrating
*/

public class Review implements Serializable {
	private String productModelName;
	private String productCategory;
	private double productPrice;
	private String storeId;
	private String storeCity;
	private String storeState;
	private String storeZip;
	private String productOnSale;
	private String manufacturerName;
    private String manufacturerRebate;
    private String userId;
    private int userAge;
    private String userGender;
    private String userOccupation;
    private int reviewRating;
    private String reviewDate;
    private String reviewText;

    public Review() {
    }

    public Review(String productModelName, String productCategory, double productPrice, String storeId, String storeCity, String storeState, String storeZip, String productOnSale, String manufacturerName, String manufacturerRebate, String userId, int userAge, String userGender, String userOccupation, int reviewRating, String reviewDate, String reviewText) {
        this.productModelName = productModelName;
        this.productCategory = productCategory;
        this.productPrice = productPrice;
        this.storeId = storeId;
        this.storeCity = storeCity;
        this.storeState = storeState;
        this.storeZip = storeZip;
        this.productOnSale = productOnSale;
        this.manufacturerName = manufacturerName;
        this.manufacturerRebate = manufacturerRebate;
        this.userId = userId;
        this.userAge = userAge;
        this.userGender = userGender;
        this.userOccupation = userOccupation;
        this.reviewRating = reviewRating;
        this.reviewDate = reviewDate;
        this.reviewText = reviewText;
    }

    public String getProductModelName() {
        return productModelName;
    }

    public void setProductModelName(String productModelName) {
        this.productModelName = productModelName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getStoreCity() {
        return storeCity;
    }

    public void setStoreCity(String storeCity) {
        this.storeCity = storeCity;
    }

    public String getStoreState() {
        return storeState;
    }

    public void setStoreState(String storeState) {
        this.storeState = storeState;
    }

    public String getStoreZip() {
        return storeZip;
    }

    public void setStoreZip(String storeZip) {
        this.storeZip = storeZip;
    }

    public String getProductOnSale() {
        return productOnSale;
    }

    public void setProductOnSale(String productOnSale) {
        this.productOnSale = productOnSale;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getManufacturerRebate() {
        return manufacturerRebate;
    }

    public void setManufacturerRebate(String manufacturerRebate) {
        this.manufacturerRebate = manufacturerRebate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserOccupation() {
        return userOccupation;
    }

    public void setUserOccupation(String userOccupation) {
        this.userOccupation = userOccupation;
    }

    public int getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(int reviewRating) {
        this.reviewRating = reviewRating;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
}
