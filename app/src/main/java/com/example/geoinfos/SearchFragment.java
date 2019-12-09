package com.example.geoinfos;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class
SearchFragment extends Fragment {

    private TextView textViewResult;
    private ListView listViewResult;
    private ArrayList<String> ARcountries;
    private ArrayList<String> ARFcountries;
    private  ArrayAdapter<String> arrayAdapter;
    private List<Country> countries;
    private Button addButton;
    private EditText editText;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

     View view = inflater.inflate(R.layout.fragment_search,container,false);
           textViewResult = view.findViewById(R.id.text_view_result);
            listViewResult =  view.findViewById(R.id.list_view_result);
            addButton = view.findViewById(R.id.addBtn);
            editText = view.findViewById(R.id.editTxt);


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

                         countries = response.body();

                ARcountries = new ArrayList<>();
                ARFcountries = new ArrayList<>();

                for (Country country : countries){
                ARcountries.add("\nCountry : " + country.getName() + "\n" + "Capital : " + country.getCapital() + "\n" + "Region : " + country.getRegion() + "\n" + "Population : " + country.getPopulation() + "\n");
                ARFcountries.add("\n" +country.getName()+ "\n");

                }
                arrayAdapter = new ArrayAdapter<>(getActivity().getBaseContext(), android.R.layout.simple_list_item_1,ARFcountries);


                       listViewResult.setAdapter(arrayAdapter);
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                    textViewResult.setText(t.getMessage());
            }

        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(editText.getText().toString().isEmpty()){
                    Toast.makeText(getActivity().getBaseContext(),"Error : Text can not be empty",Toast.LENGTH_LONG).show();

                }else{
                    // this line adds the data of your EditText and puts in your array
                    ARcountries.add(editText.getText().toString());
                    ARFcountries.add(editText.getText().toString().substring(0, editText.getText().toString().indexOf("\n")));
                    // next thing you have to do is check if your adapter has changed
                    arrayAdapter.notifyDataSetChanged();
                    Toast.makeText(getActivity().getBaseContext(),"You successfully added a new element",Toast.LENGTH_LONG).show();
                    editText.setText(null);
                }

            }
        });

        listViewResult.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                AppCompatActivity activity = (AppCompatActivity) view.getContext();

                ItemFragment fragment = new ItemFragment();
                Bundle args = new Bundle();
                args.putString("arg_infos", ARcountries.get(position));
                fragment.setArguments(args);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragment).commit();
            }
        });
        return view;
    }
}
