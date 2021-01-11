package api;

import Models.ProductModel;
import Models.Seller;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SellerService {

    /*Saving seller profile*/
    @POST("register")
    Call<Seller> saveSellerProfile(@Body Seller seller);

    /*Getting seller profile*/
    @GET("seller/{id}")
    Call <Seller> getSellerProfile(@Path("id") String firebaseUserId);
}
