package com.coronavirussefety.covidtracer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;


public class SymptomsFragment extends Fragment {
    ImageView covid_image;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_symptoms, container, false);
        covid_image = view.findViewById(R.id.covid_image);

        return view;
    }


}