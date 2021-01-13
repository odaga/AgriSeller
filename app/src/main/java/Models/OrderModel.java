package Models;

public class OrderModel {
    private String _id;
    private String productId;
    private String name;
    private String price;
    private String quantity;
    private String productImage;
    private String productCategory;
    private String ownerId;
    private String buyerId;

    public OrderModel(String _id, String productId, String name, String price, String quantity, String productImage, String productCategory, String ownerId, String buyerId) {
        this._id = _id;
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.productImage = productImage;
        this.productCategory = productCategory;
        this.ownerId = ownerId;
        this.buyerId = buyerId;
    }


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }
}



