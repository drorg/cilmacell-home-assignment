package com.climacell.homeassignment.countries;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.climacell.homeassignment.R;
import com.climacell.homeassignment.Svg.SvgImageLoader;

import java.util.ArrayList;
import java.util.List;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder > implements Filterable {

    private Context context;
    private CountriesAdapterListener listener;
    private List<CountryDataModel> countriesList;
    private List<CountryDataModel> countriesListFiltered;

    public CountriesAdapter(Context context, List<CountryDataModel> countriesList, CountriesAdapterListener listener) {
        this.context = context;
        this.listener = listener;
        this.countriesList = countriesList;
        this.countriesListFiltered = countriesList;
    }

    @NonNull
    @Override
    public CountriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.country_search_result, parent, false);

        CountriesViewHolder  vh = new CountriesViewHolder (v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CountriesViewHolder holder, int position) {
        final CountryDataModel country = countriesListFiltered.get(position);
        holder.name.setText(country.getName());
        holder.capital.setText(country.getCapital());

        Uri uri = Uri.parse(country.getFlagImageUrl());
        new SvgImageLoader().loadImage(uri, holder.flag);
    }

    @Override
    public int getItemCount() {
        return countriesListFiltered.size();
    }

    public static class CountriesViewHolder extends RecyclerView.ViewHolder {
        public TextView name, capital;
        public ImageView flag;

        public CountriesViewHolder(View view) {
            super(view);

            flag = view.findViewById(R.id.country_flag_imageview);
            name = view.findViewById(R.id.country_name_textview);
            capital = view.findViewById(R.id.country_capital_textview);
        }
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    countriesListFiltered = countriesList;
                } else {
                    List<CountryDataModel> filteredList = new ArrayList<>();
                    for (CountryDataModel row : countriesList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getCapital().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    countriesListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = countriesListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                countriesListFiltered = (ArrayList<CountryDataModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface CountriesAdapterListener {
        void onCountrySelected(CountryDataModel contact);
    }

}