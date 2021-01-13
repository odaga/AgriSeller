package Models;

public class ConfirmOrderModel {
    private String productId;
    private String buyerId;
    private int stock;

    public ConfirmOrderModel(String productId, String buyerId, int stock) {
        this.productId = productId;
        this.buyerId = buyerId;
        this.stock = stock;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }
}
