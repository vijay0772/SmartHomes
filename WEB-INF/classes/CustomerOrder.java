public class CustomerOrder {
    private int orderId;
    private String userName;
    private String customerName;
    private String customerAddress;
    private int creditCardNo;
    private String purchaseDate;
    private String shipDate;
    private String productName;
    private String productType;
    private int productQuantity;
    private double productPrice;
    private double shippingCost;
    private double discountPrice;
    private double orderTotal;
    private boolean isWarrantyIncluded;
    private double warrantyPrice;
    private String deliveryMethod;
    private String maxPickupDate;
    private String pickupStoreName;
    private String maxCancellationDate;

    public CustomerOrder() {
    }

    public CustomerOrder(
            int orderId,
            String userName,
            String customerName,
            String customerAddress,
            int creditCardNo,
            String purchaseDate,
            String shipDate,
            String productName,
            String productType,
            int productQuantity,
            double productPrice,
            double shippingCost,
            double discountPrice,
            double orderTotal,
            boolean isWarrantyIncluded,
            double warrantyPrice,
            String deliveryMethod,
            String maxPickupDate,
            String pickupStoreName,
            String maxCancellationDate
    ) {
        this.orderId = orderId;
        this.userName = userName;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.creditCardNo = creditCardNo;
        this.purchaseDate = purchaseDate;
        this.shipDate = shipDate;
        this.productName = productName;
        this.productType = productType;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
        this.shippingCost = shippingCost;
        this.discountPrice = discountPrice;
        this.orderTotal = orderTotal;
        this.isWarrantyIncluded = isWarrantyIncluded;
        this.warrantyPrice = warrantyPrice;
        this.deliveryMethod = deliveryMethod;
        this.maxPickupDate = maxPickupDate;
        this.pickupStoreName = pickupStoreName;
        this.maxCancellationDate = maxCancellationDate;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public int getCreditCardNo() {
        return creditCardNo;
    }

    public void setCreditCardNo(int creditCardNo) {
        this.creditCardNo = creditCardNo;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public boolean isWarrantyIncluded() {
        return isWarrantyIncluded;
    }

    public void setWarrantyIncluded(boolean warrantyIncluded) {
        isWarrantyIncluded = warrantyIncluded;
    }

    public double getWarrantyPrice() {
        return warrantyPrice;
    }

    public void setWarrantyPrice(double warrantyPrice) {
        this.warrantyPrice = warrantyPrice;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public String getMaxPickupDate() {
        return maxPickupDate;
    }

    public void setMaxPickupDate(String maxPickupDate) {
        this.maxPickupDate = maxPickupDate;
    }

    public String getPickupStoreName() {
        return pickupStoreName;
    }

    public void setPickupStoreName(String pickupStoreName) {
        this.pickupStoreName = pickupStoreName;
    }

    public String getMaxCancellationDate() {
        return maxCancellationDate;
    }

    public void setMaxCancellationDate(String maxCancellationDate) {
        this.maxCancellationDate = maxCancellationDate;
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "orderId=" + orderId +
                ", userName='" + userName + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", creditCardNo=" + creditCardNo +
                ", purchaseDate='" + purchaseDate + '\'' +
                ", shipDate='" + shipDate + '\'' +
                ", productName='" + productName + '\'' +
                ", productType='" + productType + '\'' +
                ", productQuantity=" + productQuantity +
                ", productPrice=" + productPrice +
                ", shippingCost=" + shippingCost +
                ", discountPrice=" + discountPrice +
                ", orderTotal=" + orderTotal +
                ", isWarrantyIncluded=" + isWarrantyIncluded +
                ", warrantyPrice=" + warrantyPrice +
                ", deliveryMethod='" + deliveryMethod + '\'' +
                ", maxPickupDate='" + maxPickupDate + '\'' +
                ", pickupStoreName='" + pickupStoreName + '\'' +
                ", maxCancellationDate='" + maxCancellationDate + '\'' +
                '}';
    }
}
