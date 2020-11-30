package api;

import java.util.List;

import Models.ProductModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface productService {
    @POST("products")
    Call<ProductModel> submitProduct(@Body ProductModel product);

    //TODO add single product using it's product id
    @GET("products/{productId}")
    Call<ProductModel> getSingleProduct(@Path("productId") String productId);

    @GET("products/{productOwnerId}")
    Call<List<ProductModel>> getMyProducts(@Path("productOwnerId") String productOwnerId);

}
