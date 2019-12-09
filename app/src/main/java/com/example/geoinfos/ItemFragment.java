package com.example.geoinfos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ItemFragment extends Fragment {

    private String elementinfos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_infos, container, false);

        if(getArguments() != null){
            elementinfos = getArguments().getString("arg_infos");
        }
        TextView infos =  view.findViewById(R.id.item_infos);
        infos.setText(elementinfos);
        return view;
    }
}
