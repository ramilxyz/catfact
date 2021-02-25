package xyz.ramil.catfact.data;

import io.reactivex.Single;
import retrofit2.http.GET;
import xyz.ramil.catfact.model.Facts;


interface ApiService {
    @GET("/facts/random")
    Single<Facts> getFact();
}

