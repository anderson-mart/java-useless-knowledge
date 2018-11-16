package engine.rest;

import modules.fact.Fact;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface FactService {
    @GET("/api/facts")
    Call<List<Fact>> getFacts();
}

