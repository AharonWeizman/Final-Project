package org.ecocheck.ecocheck.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.ecocheck.ecocheck.R;
import org.ecocheck.ecocheck.dto.Factory;
import org.ecocheck.ecocheck.dto.Visits;

import java.util.ArrayList;

/**
 * Created by Ron on 19/03/2016.
 * visit array adapter
 */
public class VisitArrayAdapter extends ArrayAdapter<Visits>
{
    private Activity activity;
    private ArrayList<Visits> lVisits;//put all the data in array list
    private static LayoutInflater inflater = null;

    public VisitArrayAdapter(Activity activity,int textViewResourceId,
                             ArrayList<Visits> _lVisits)
    {
        super(activity, textViewResourceId, _lVisits);
        try
        {
            this.activity = activity;
            this.lVisits =  _lVisits;

            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }
        catch (Exception e)
        {

        }
    }

    public int getCount()
    {
        return lVisits.size();
    }//the Visits size

    public Visits getItem(Visits position)
    {
        return position;
    }

    public long getItemId(int position)
    {
        return position;
    }

    public static class ViewHolder
    {
        public TextView Visits_ReportNumber;

    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        View vi = convertView;
        final ViewHolder holder;
        try
        {
            if (convertView == null)
            {
                // vi = inflater.inflate(R.layout.list_item, null);// to show need to vreate visit layouyt
                holder = new ViewHolder();

                //   holder.Visits_ReportNumber = (TextView) vi
                //   .findViewById(R.id.factory_name);// to show need to vreate visit layouyt

                vi.setTag(holder);
            }
            else
            {
                holder = (ViewHolder) vi.getTag();
            }

            holder.Visits_ReportNumber.setText(lVisits.get(position).getFactory());

        }
        catch (Exception e)
        {

        }
        return vi;
    }


}
