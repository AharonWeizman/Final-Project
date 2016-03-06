package org.ecocheck.ecocheck.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.ecocheck.ecocheck.dto.Factory;
import org.ecocheck.ecocheck.R;

import java.util.ArrayList;

/**
 * Created by Ron on 15/02/2016.
 * this class is adapter extend factories to array
 */
public class FactoryArrayAdapter extends ArrayAdapter<Factory>
{
    private Activity activity;
    private ArrayList<Factory> lFactory;//put all the factory data in array list
    private static LayoutInflater inflater = null;

    public FactoryArrayAdapter(Activity activity, int textViewResourceId,
                               ArrayList<Factory> _lFactory)
    {
        super(activity, textViewResourceId, _lFactory);
        try
        {
            this.activity = activity;
            this.lFactory = _lFactory;

            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }
        catch (Exception e)
        {

        }
    }

    public int getCount()
    {
        return lFactory.size();
    }//the factory size

    public Factory getItem(Factory position)
    {
        return position;
    }

    public long getItemId(int position)
    {
        return position;
    }

    public static class ViewHolder
    {
        public TextView factory_name;

    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        View vi = convertView;
        final ViewHolder holder;
        try
        {
            if (convertView == null)
            {
                vi = inflater.inflate(R.layout.list_item, null);
                holder = new ViewHolder();

                holder.factory_name = (TextView) vi
                        .findViewById(R.id.factory_name);

                vi.setTag(holder);
            }
            else
            {
                holder = (ViewHolder) vi.getTag();
            }

            holder.factory_name.setText(lFactory.get(position).getFactory());

        }
        catch (Exception e)
        {

        }
        return vi;
    }
}
