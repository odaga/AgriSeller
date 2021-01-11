package Models;

public class ProductModel {
    private String _id;
    private String name;
    private String description;
    private String productCategory;
    private String productImage;
    private String price;
    private int stock;
    private Boolean approvalStatus;
    private String ownerId;
    private String BuyerId;

    public ProductModel() {
        //Empty constructor needed by Firebase
    }

    public ProductModel(String _id, String name, String description, String productCategory, String productImage, String price, int stock, Boolean approvalStatus, String ownerId, String buyerId) {
        this._id = _id;
        this.name = name;
        this.description = description;
        this.productCategory = productCategory;
        this.productImage = productImage;
        this.price = price;
        this.stock = stock;
        this.approvalStatus = approvalStatus;
        this.ownerId = ownerId;
        BuyerId = buyerId;
    }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Boolean getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Boolean approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getBuyerId() {
        return BuyerId;
    }

    public void setBuyerId(String buyerId) {
        BuyerId = buyerId;
    }
}
