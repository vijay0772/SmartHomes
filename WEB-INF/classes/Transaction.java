public class Transaction {
    private int orderId;
    private String userName;
    private String customerName;
    private String streetAddress;
    private String cityAddress;
    private String stateAddress;
    private String zipcode;
    private String creditCardNo;
    private String deliveryMethod;
    private String pickupStoreName;
    private String orderDate;
    private String deliveryDate;
    private String maxOrderCancellationDate;
    private String maxPickupDate;

    public Transaction() {
    }

    public Transaction(int orderId, String userName, String customerName, String streetAddress, String cityAddress, String stateAddress, String zipcode, String creditCardNo, String deliveryMethod, String pickupStoreName, String orderDate, String deliveryDate, String maxOrderCancellationDate, String maxPickupDate) {
        this.orderId = orderId;
        this.userName = userName;
        this.customerName = customerName;
        this.streetAddress = streetAddress;
        this.cityAddress = cityAddress;
        this.stateAddress = stateAddress;
        this.zipcode = zipcode;
        this.creditCardNo = creditCardNo;
        this.deliveryMethod = deliveryMethod;
        this.pickupStoreName = pickupStoreName;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.maxOrderCancellationDate = maxOrderCancellationDate;
        this.maxPickupDate = maxPickupDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCityAddress() {
        return cityAddress;
    }

    public void setCityAddress(String cityAddress) {
        this.cityAddress = cityAddress;
    }

    public String getStateAddress() {
        return stateAddress;
    }

    public void setStateAddress(String stateAddress) {
        this.stateAddress = stateAddress;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCreditCardNo() {
        return creditCardNo;
    }

    public void setCreditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public String getPickupStoreName() {
        return pickupStoreName;
    }

    public void setPickupStoreName(String pickupStoreName) {
        this.pickupStoreName = pickupStoreName;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getMaxOrderCancellationDate() {
        return maxOrderCancellationDate;
    }

    public void setMaxOrderCancellationDate(String maxOrderCancellationDate) {
        this.maxOrderCancellationDate = maxOrderCancellationDate;
    }

    public String getMaxPickupDate() {
        return maxPickupDate;
    }

    public void setMaxPickupDate(String maxPickupDate) {
        this.maxPickupDate = maxPickupDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
