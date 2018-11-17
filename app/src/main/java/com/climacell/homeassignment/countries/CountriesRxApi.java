package com.climacell.homeassignment.countries;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface CountriesRxApi {


    @GET("all")
    Observable<List<CountryDataModel>> listOfCountries(
            @Query("fields") String fields
    );


}
