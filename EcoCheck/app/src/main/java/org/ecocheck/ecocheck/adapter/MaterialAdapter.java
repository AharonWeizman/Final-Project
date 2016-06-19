package org.ecocheck.ecocheck.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;

import org.ecocheck.ecocheck.R;
import org.ecocheck.ecocheck.dto.Material;

import java.util.ArrayList;

public class MaterialAdapter extends ArrayAdapter<Material> {

    private Activity activity;
    
    ArrayList<Material> dataMaterials;
    private static LayoutInflater inflater = null;

/

    public MaterialAdapter(Activity activity, int textViewResourceId, ArrayList<Material> lMaterial)
    {
        super(activity, textViewResourceId, lMaterial);

/
        this.activity = activity;
        this.dataMaterials = lMaterial;
        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        
        return dataMaterials.size();
    }



    @Override
    public long getItemId(int position) {
        
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        

        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.materialrow, null);
        TextView text = (TextView) vi.findViewById(R.id.text);
        TextView text1 = (TextView) vi.findViewById(R.id.text1);
        TextView text2 = (TextView) vi.findViewById(R.id.text2);
        TextView text3 = (TextView) vi.findViewById(R.id.text3);
        TextView text4 = (TextView) vi.findViewById(R.id.text4);
        TextView text5 = (TextView) vi.findViewById(R.id.text5);
        TextView text6 = (TextView) vi.findViewById(R.id.text6);
        TextView text7 = (TextView) vi.findViewById(R.id.text7);
        TextView text8 = (TextView) vi.findViewById(R.id.text8);
        TextView text9 = (TextView) vi.findViewById(R.id.text9);
        TextView text10 = (TextView) vi.findViewById(R.id.text10);


        Material material = dataMaterials.get(position);

        text1.setText( material.getMaterialIDImp());
        text2.setText( material.getDepProcIDImp());
        text3.setText( material.getJobID());
        text4.setText( material.getDepProcID());
        text5.setText( material.getHeader1());
        text6.setText( material.getShemMisharyCode());
        text7.setText( material.getHeader2());
        text8.setText( material.getMaterialID());
        text9.setText( material.getHeader3());
        text10.setText( material.getMSDS());

        return vi;
    }
}
