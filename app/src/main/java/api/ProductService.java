package api;

import java.util.List;

import Models.ConfirmOrderModel;
import Models.OrderModel;
import Models.ProductModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProductService {
    @POST("products")
    Call<ProductModel> submitProduct(@Body ProductModel product);

    //TODO add single product using it's product id
    @GET("products/{productId}")
    Call<ProductModel> getSingleProduct(@Path("productId") String productId);

    @GET("products/{productOwnerId}")
    Call<List<ProductModel>> getMyProducts(@Path("productOwnerId") String productOwnerId);

    //Get product belonging to current seller (farmer)
    @GET("/products/inventory/{productOwnerId}")
    Call<List<ProductModel>> getMyInventory(@Path("productOwnerId") String productOwnerId);

    //Get product orders for farmer
    @GET("orders/{productOwnerId}")
    Call<List<OrderModel>> getMyOrders(@Path("productOwnerId") String productOwnerId);

    //Confirm order update stock
    @POST("products/stock-update")
    Call<Void> updateStock(@Body ConfirmOrderModel order);

}
