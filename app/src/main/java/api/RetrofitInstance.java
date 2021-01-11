package api;

import Models.Seller;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import untils.Credentials;

public class RetrofitInstance {

    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl(Credentials.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    //Call the different methods in the SellerService interface
    private static  SellerService sellerService = retrofit.create(SellerService.class);

    public SellerService getSellerService() {
        return  sellerService;
    }
}
