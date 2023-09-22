package com.coronavirussefety.covidtracer;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class StatsFragment extends Fragment {

    public static final String STATS_URL = " https://api.covid19api.com/summary";

    Context context;
    private ProgressBar progressbar;
    private EditText searchEt;
    private ImageButton sortBtn;
    private RecyclerView statsRv;


    ArrayList<ModelStat> statArrayList;
    AdapterStat adapterStat;

    public StatsFragment() {
        // Required empty public constructor
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
        View view = inflater.inflate(R.layout.fragment_stats, container, false);

        progressbar = view.findViewById(R.id.progressbar);
        searchEt = view.findViewById(R.id.searchEt);
        sortBtn = view.findViewById(R.id.sortBtn);
        statsRv = view.findViewById(R.id.statsRv);


        progressbar.setVisibility(View.VISIBLE);

        loadStatsData();

        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                try {
                    loadStatsData();

                    adapterStat.getFilter().filter(s);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        PopupMenu popupMenu = new PopupMenu(context, sortBtn);
        popupMenu.getMenu().add(Menu.NONE, 0, 0, "Ascending");
        popupMenu.getMenu().add(Menu.NONE, 1, 1, "Descending");
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == 0) {
                    Collections.sort(statArrayList, new SortStatCountryAsc());
                    adapterStat.notifyDataSetChanged();

                } else if (id == 1) {
                    Collections.sort(statArrayList, new SortStatCountryDesc());
                    adapterStat.notifyDataSetChanged();

                }
                return false;
            }
        });


        sortBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupMenu.show();

            }
        });
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        loadStatsData();
    }

    private void loadStatsData() {

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


        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }

    private void handleResponse(String response) {
        statArrayList = new ArrayList<>();
        statArrayList.clear();
        try {

            JSONObject jsonObject = new JSONObject(response);

            JSONArray jsonArray = jsonObject.getJSONArray("Countries");

            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setDateFormat("dd/MM/yyyy hh:mm a");
            Gson gson = gsonBuilder.create();


            //start getting data()

            for (int i = 0; i < jsonArray.length(); i++) {
                ModelStat modelStat = gson.fromJson(jsonArray.getJSONObject(i).toString(), ModelStat.class);
                statArrayList.add(modelStat);

            }
            //setup adapter
            adapterStat = new AdapterStat(context, statArrayList);
            statsRv.setAdapter(adapterStat);

            progressbar.setVisibility(View.GONE);

        } catch (Exception e) {
            progressbar.setVisibility(View.GONE);
            Toast.makeText(context, "" + e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }

    public static class SortStatCountryAsc implements Comparator<ModelStat> {

        @Override
        public int compare(ModelStat left, ModelStat right) {
            return left.getCountry().compareTo(right.getCountry());
        }
    }

    public static class SortStatCountryDesc implements Comparator<ModelStat> {

        @Override
        public int compare(ModelStat left, ModelStat right) {
            return right.getCountry().compareTo(left.getCountry());
        }
    }
}
