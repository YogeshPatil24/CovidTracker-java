package com.coronavirussefety.covidtracer;

import android.widget.Filter;

import java.util.ArrayList;

public class FilterStat extends Filter {

    public AdapterStat adapter;
    public ArrayList<ModelStat> filterList;

    public FilterStat(AdapterStat adapter, ArrayList<ModelStat> filterList) {
        this.adapter = adapter;
        this.filterList = filterList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        if (constraint != null && constraint.length() > 0) {

            constraint = constraint.toString().toUpperCase();

            ArrayList<ModelStat> filtereModels = new ArrayList<>();


            for (int i = 0; i < filterList.size(); i++) {
                if (filterList.get(i).getCountry().toUpperCase().contains(constraint)) {
                    filtereModels.add(filterList.get(i));
                }

            }

            results.count = filtereModels.size();
            results.values = filtereModels;
        } else {
            results.count = filterList.size();
            results.values = filterList;

        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults results) {


        adapter.statArrayList = (ArrayList<ModelStat>) results.values;

        adapter.notifyDataSetChanged();

    }
}
