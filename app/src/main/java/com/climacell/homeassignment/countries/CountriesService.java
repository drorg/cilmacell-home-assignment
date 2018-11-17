package com.climacell.homeassignment.countries;

import android.content.Context;

import com.climacell.homeassignment.network.ClimaCellOKHttpClient;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class CountriesService {
    private CountriesRxApi countriesApi;

    public CountriesService(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://restcountries.eu/rest/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(new ClimaCellOKHttpClient().getCacheClient(context))
                .build();

        countriesApi = retrofit.create(CountriesRxApi.class);
    }


    public Observable<List<CountryDataModel>> getCountriesList() {
        return countriesApi.listOfCountries("name;capital;flag;latlng");
    }
}
