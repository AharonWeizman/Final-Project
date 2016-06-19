package org.ecocheck.ecocheck.adapter;

/**
 * Created by Ron on 26/05/2016.
 */


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import org.ecocheck.ecocheck.dto.Assignment;
import org.ecocheck.ecocheck.dto.ChemicalExposure;

import java.util.ArrayList;

public class ChemicalExposureAdapter extends BaseAdapter implements Filterable{

    private ArrayList<ChemicalExposure> mChemicalExposureList;
    private ArrayList<ChemicalExposure> mChemicalExposureListAll;
    private ArrayList<ChemicalExposure> mChemicalExposureListSearch;
    private Context mContext;
    private LayoutInflater mInflator;

    public ChemicalExposureAdapter(Context context, ArrayList<ChemicalExposure> departmentList) {
        mContext = context;
        mChemicalExposureList = departmentList;
        mChemicalExposureListAll = (ArrayList<ChemicalExposure>) departmentList.clone();
        mChemicalExposureListSearch = new ArrayList<>();
        mInflator = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mChemicalExposureList.size();
    }

    @Override
    public ChemicalExposure getItem(int position) {
        return mChemicalExposureList.get(position);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        TextView txtEntry = (TextView)mInflator.inflate(android.R.layout.simple_dropdown_item_1line, viewGroup, false);
        txtEntry.setText(mChemicalExposureList.get(position).getChemicalExposureName());

        return txtEntry;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    Filter nameFilter = new Filter() {
        @Override
        public String convertResultToString(Object resultValue) {
            String str = ((ChemicalExposure)(resultValue)).getChemicalExposureName();
            return str;
        }
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if(constraint != null) {
                mChemicalExposureListSearch.clear();
                for (ChemicalExposure chemicalExposure : mChemicalExposureListAll) {
                    if(chemicalExposure.getChemicalExposureName().toLowerCase().startsWith(constraint.toString().toLowerCase())){
                        mChemicalExposureListSearch.add(chemicalExposure);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mChemicalExposureListSearch;
                filterResults.count = mChemicalExposureListSearch.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            ArrayList<ChemicalExposure> filteredList = (ArrayList<ChemicalExposure>) results.values;
            if(results != null && results.count > 0) {
                mChemicalExposureList.clear();
                for (ChemicalExposure c : filteredList) {
                    mChemicalExposureList.add(c);
                }
                notifyDataSetChanged();
            }
        }
    };

}

