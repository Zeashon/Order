package jne.com.network.service;

import jne.com.model.bean.Business;
import jne.com.model.bean.Favorite;
import jne.com.model.bean.ProductCategory;
import jne.com.model.bean.ResultsPage;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface BusinessService {

    @GET("businesses")
    Observable<ResultsPage<Business>> businesses(@Query("page") int page, @Query("size") int size);

    @GET("businesses/{bid}/products")
    Observable<List<ProductCategory>> products(@Path("bid") String businessId);

    @POST("businesses/{bid}/favorite")
    Observable<Favorite> favorite(@Path("bid") String businessId);
}
