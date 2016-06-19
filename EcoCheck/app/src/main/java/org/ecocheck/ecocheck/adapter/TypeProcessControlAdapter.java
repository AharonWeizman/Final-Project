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

import org.ecocheck.ecocheck.dto.TypeProcessControl;

import java.util.ArrayList;

public class TypeProcessControlAdapter extends BaseAdapter implements Filterable{

    private ArrayList<TypeProcessControl> mTypeProcessControlList;
    private ArrayList<TypeProcessControl> mTypeProcessControlListAll;
    private ArrayList<TypeProcessControl> mTypeProcessControlListSearch;
    private Context mContext;
    private LayoutInflater mInflator;

    public TypeProcessControlAdapter(Context context, ArrayList<TypeProcessControl> departmentList) {
        mContext = context;
        mTypeProcessControlList = departmentList;
        mTypeProcessControlListAll = (ArrayList<TypeProcessControl>) departmentList.clone();
        mTypeProcessControlListSearch = new ArrayList<>();
        mInflator = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mTypeProcessControlList.size();
    }

    @Override
    public TypeProcessControl getItem(int position) {
        return mTypeProcessControlList.get(position);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        TextView txtEntry = (TextView)mInflator.inflate(android.R.layout.simple_dropdown_item_1line, viewGroup, false);
        txtEntry.setText(mTypeProcessControlList.get(position).getTypeOfProcessControl());

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
            String str = ((TypeProcessControl)(resultValue)).getTypeOfProcessControl();
            return str;
        }
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if(constraint != null) {
                mTypeProcessControlListSearch.clear();
                for (TypeProcessControl assignment : mTypeProcessControlListAll) {
                    if(assignment.getTypeOfProcessControl().toLowerCase().startsWith(constraint.toString().toLowerCase())){
                        mTypeProcessControlListSearch.add(assignment);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mTypeProcessControlListSearch;
                filterResults.count = mTypeProcessControlListSearch.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

//            if(results.values!=null){
//               mProcessList =  (ArrayList<Process>) results.values;
//            }else{
//                mProcessList =  new ArrayList<>();
//            }

            ArrayList<TypeProcessControl> filteredList = (ArrayList<TypeProcessControl>) results.values;
            if(results != null && results.count > 0) {
                mTypeProcessControlList.clear();
                for (TypeProcessControl c : filteredList) {
                    mTypeProcessControlList.add(c);
                }
                notifyDataSetChanged();
            }
        }
    };

}

