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
import org.ecocheck.ecocheck.dto.PersonalExposure;

import java.util.ArrayList;


public class PersonalProtectiveEquipAdapter extends BaseAdapter implements Filterable{

    private ArrayList<PersonalExposure> mPersonalExposureList;
    private ArrayList<PersonalExposure> mPersonalExposureListAll;
    private ArrayList<PersonalExposure> mPersonalExposureListSearch;
    private Context mContext;
    private LayoutInflater mInflator;

    public PersonalProtectiveEquipAdapter(Context context, ArrayList<PersonalExposure> departmentList) {
        mContext = context;
        mPersonalExposureList = departmentList;
        mPersonalExposureListAll = (ArrayList<PersonalExposure>) departmentList.clone();
        mPersonalExposureListSearch = new ArrayList<>();
        mInflator = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mPersonalExposureList.size();
    }

    @Override
    public PersonalExposure getItem(int position) {
        return mPersonalExposureList.get(position);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        TextView txtEntry = (TextView)mInflator.inflate(android.R.layout.simple_dropdown_item_1line, viewGroup, false);
        txtEntry.setText(mPersonalExposureList.get(position).getPerProEquipType());

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
            String str = ((PersonalExposure)(resultValue)).getPerProEquipType();
            return str;
        }
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if(constraint != null) {
                mPersonalExposureListSearch.clear();
                for (PersonalExposure assignment : mPersonalExposureListAll) {
                    if(assignment.getPerProEquipType().toLowerCase().startsWith(constraint.toString().toLowerCase())){
                        mPersonalExposureListSearch.add(assignment);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mPersonalExposureListSearch;
                filterResults.count = mPersonalExposureListSearch.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ArrayList<PersonalExposure> filteredList = (ArrayList<PersonalExposure>) results.values;
            if(results != null && results.count > 0) {
                mPersonalExposureList.clear();
                for (PersonalExposure c : filteredList) {
                    mPersonalExposureList.add(c);
                }
                notifyDataSetChanged();
            }
        }
    };

}
