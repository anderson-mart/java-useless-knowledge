package engine.rest;

import modules.fact.Fact;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class RequestHandler {

    static final FactService service = new Retrofit.Builder()
        .baseUrl("http://mentalfloss.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(FactService.class);


    public String getNewFact() throws IOException {
        List<Fact> facts = service.getFacts().execute().body();
        Collections.shuffle(facts);
        return facts.remove(0).getShortHeadline();
    }

}
