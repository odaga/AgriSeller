package Models;

public class ProductModel {
    private String _id;
    private String productName;
    private String productDescription;
    private String productCategory;
    private String productImageUrl;
    private String productPrice;
    private String productOwnerId;
  //  private Boolean productApprovalStatus;
    private String productBuyerId;

    public ProductModel() {
        //Empty constructor needed by Firebase
    }


    public ProductModel(String _id, String productName, String productDescription, String productCategory, String productImageUrl, String productPrice, String productOwnerId, String productBuyerId) {
        this._id = _id;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productCategory = productCategory;
        this.productImageUrl = productImageUrl;
        this.productPrice = productPrice;
        this.productOwnerId = productOwnerId;
        this.productBuyerId = productBuyerId;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductOwnerId() {
        return productOwnerId;
    }

    public void setProductOwnerId(String productOwnerId) {
        this.productOwnerId = productOwnerId;
    }

    public String getProductBuyerId() {
        return productBuyerId;
    }

    public void setProductBuyerId(String productBuyerId) {
        this.productBuyerId = productBuyerId;
    }
}
