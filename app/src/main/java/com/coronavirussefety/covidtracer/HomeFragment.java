package com.coronavirussefety.covidtracer;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;


public class HomeFragment extends Fragment {

    public static final String STATS_URL = "https://api.covid19api.com/summary";
    Context context;

    private ProgressBar progressbar;
    private TextView totalCasesTv, newCasesTv, totalDeathsTv, newDeathsTv, totalRecoveredTv, newRecoveredTv;

    public HomeFragment() {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        progressbar = view.findViewById(R.id.progressbar);
        totalCasesTv = view.findViewById(R.id.totalCasesTv);
        totalDeathsTv = view.findViewById(R.id.totalDeathsTv);
        newDeathsTv = view.findViewById(R.id.newDeathsTv);
        newCasesTv = view.findViewById(R.id.newCasesTv);
        totalRecoveredTv = view.findViewById(R.id.totalRecoveredTv);
        newRecoveredTv = view.findViewById(R.id.newRecoveredTv);

        progressbar.setVisibility(View.INVISIBLE);

        loadHomeData();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadHomeData();
    }

    private void loadHomeData() {
        progressbar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, STATS_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                handleResponse(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressbar.setVisibility(View.GONE);
                Toast.makeText(context, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        //add request to queue
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    private void handleResponse(String response) {

        try {
            JSONObject jsonObject = new JSONObject(response);

            JSONObject globalJo = jsonObject.getJSONObject("Global");

            String newConfirmed = globalJo.getString("NewConfirmed");
            String totalConfirmed = globalJo.getString("TotalConfirmed");
            String newDeaths = globalJo.getString("NewDeaths");
            String totalDeaths = globalJo.getString("TotalDeaths");
            String totalRecovered = globalJo.getString("TotalRecovered");
            String newRecovered = globalJo.getString("NewRecovered");


            totalCasesTv.setText(totalConfirmed);
            newCasesTv.setText(newConfirmed);
            totalDeathsTv.setText(totalDeaths);
            newDeathsTv.setText(newDeaths);
            totalRecoveredTv.setText(totalRecovered);
            newRecoveredTv.setText(newRecovered);

            progressbar.setVisibility(View.GONE);

        } catch (Exception e) {
            progressbar.setVisibility(View.GONE);
            Toast.makeText(context, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}