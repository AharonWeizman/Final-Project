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
import org.ecocheck.ecocheck.dto.Process;


import java.util.ArrayList;


public class ProcessAdapter extends BaseAdapter implements Filterable {

    private ArrayList<Process> mProcessList;
    private ArrayList<Process> mProcessListAll;
    private ArrayList<Process> mProcessListSearch;
    private Context mContext;
    private LayoutInflater mInflator;

    public ProcessAdapter(Context context, ArrayList<Process> processList) {
        mContext = context;
        mProcessList = processList;
        mProcessListAll = (ArrayList<Process>)processList.clone();
        mProcessListSearch = new ArrayList<>();
        mInflator = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mProcessList.size();
    }

    @Override
    public Process getItem(int position) {
        return mProcessList.get(position);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        TextView txtEntry = (TextView)mInflator.inflate(android.R.layout.simple_dropdown_item_1line, viewGroup, false);
        txtEntry.setText(mProcessList.get(position).getProcess());

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
            String str = ((Process)(resultValue)).getProcess();
            return str;
        }
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if(constraint != null) {
                mProcessListSearch.clear();
                for (Process process : mProcessListAll) {
                    if(process.getProcess().toLowerCase().startsWith(constraint.toString().toLowerCase())){
                        mProcessListSearch.add(process);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mProcessListSearch;
                filterResults.count = mProcessListSearch.size();
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

            ArrayList<Process> filteredList = (ArrayList<Process>) results.values;
            if(results != null && results.count > 0) {
                mProcessList.clear();
                for (Process c : filteredList) {
                    mProcessList.add(c);
                }
                notifyDataSetChanged();
            }
        }
    };

}

