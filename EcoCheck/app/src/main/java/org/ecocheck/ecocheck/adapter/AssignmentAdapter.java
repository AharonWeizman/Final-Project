package org.ecocheck.ecocheck.adapter;

/**
 * Created by Ron on 26/05/2016.
 *
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
import org.ecocheck.ecocheck.dto.Department;
import org.ecocheck.ecocheck.dto.Process;

import java.util.ArrayList;
public class AssignmentAdapter extends BaseAdapter implements Filterable
{

    private ArrayList<Assignment> mAssignmentList;
    private ArrayList<Assignment> mAssignmentListAll;
    private ArrayList<Assignment> mAssignmentListSearch;
    private Context mContext;
    private LayoutInflater mInflator;

    public AssignmentAdapter(Context context, ArrayList<Assignment> departmentList)
    {
        mContext = context;
        mAssignmentList = departmentList;
        mAssignmentListAll = (ArrayList<Assignment>) departmentList.clone();
        mAssignmentListSearch = new ArrayList<>();
        mInflator = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mAssignmentList.size();
    }

    @Override
    public Assignment getItem(int position) {
        return mAssignmentList.get(position);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup)
    {
        TextView txtEntry = (TextView)mInflator.inflate(android.R.layout.simple_dropdown_item_1line, viewGroup, false);
        txtEntry.setText(mAssignmentList.get(position).getAssignmentName());

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
            String str = ((Assignment)(resultValue)).getAssignmentName();
            return str;
        }
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if(constraint != null) {
                mAssignmentListSearch.clear();
                for (Assignment assignment : mAssignmentListAll) {
                    if(assignment.getAssignmentName().toLowerCase().startsWith(constraint.toString().toLowerCase())){
                        mAssignmentListSearch.add(assignment);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mAssignmentListSearch;
                filterResults.count = mAssignmentListSearch.size();
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

            ArrayList<Assignment> filteredList = (ArrayList<Assignment>) results.values;
            if(results != null && results.count > 0) {
                mAssignmentList.clear();
                for (Assignment c : filteredList) {
                    mAssignmentList.add(c);
                }
                notifyDataSetChanged();
            }
        }
    };

}
