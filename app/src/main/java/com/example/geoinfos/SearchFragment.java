package com.example.geoinfos;

import android.graphics.Movie;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;
import android.widget.ArrayAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchFragment extends Fragment {

    private TextView textViewResult;
    private ListView listViewResult;
    private ArrayList<String> ARcountries;
    private  ArrayAdapter<String> arrayAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

     View view = inflater.inflate(R.layout.fragment_search,container,false);
           textViewResult = view.findViewById(R.id.text_view_result);
            listViewResult =  view.findViewById(R.id.list_view_result);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://restcountries.eu/rest/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);

        Call<List<Country>> call = api.getCountries();

        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                        if(!response.isSuccessful()){
                            textViewResult.setText("Code : " + response.code());
                            return;
                        }

                        List<Country> countries = response.body();


                ARcountries = new ArrayList<>();
                for (Country country : countries){
                ARcountries.add("\nCountry : " + country.getName() + "\n" + "Capital : " + country.getCapital() + "\n" + "Region : " + country.getRegion() + "\n" + "Population : " + country.getPopulation() + "\n" + "Flag : " + country.getFlag() + "\n");
                }
                arrayAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_list_item_1,ARcountries);


                       listViewResult.setAdapter(arrayAdapter);
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                    textViewResult.setText(t.getMessage());
            }

        });
        return view;
    }
}
