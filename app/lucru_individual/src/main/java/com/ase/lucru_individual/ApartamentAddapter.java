package com.ase.lucru_individual;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ApartamentAddapter extends BaseAdapter {
    private final Context context;
    private final int resource;
    private final List<Apartament> apartamente;

    public ApartamentAddapter(Context context, int resource, List<Apartament> apartamente) {
        this.context = context;
        this.resource = resource;
        this.apartamente = apartamente;
    }

    @Override
    public int getCount() {
        return apartamente.size();
    }

    @Override
    public Object getItem(int position) {
        return apartamente.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder{
        TextView tvAdresa;
        TextView tvSuprafata;
        TextView tvPret;
        TextView tvZona;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(resource, parent ,false);

            viewHolder = new ViewHolder();
            viewHolder.tvAdresa = convertView.findViewById(R.id.tvAdresa);
            viewHolder.tvPret = convertView.findViewById(R.id.tvPret);
            viewHolder.tvSuprafata = convertView.findViewById(R.id.tvSuprafata);
            viewHolder.tvZona = convertView.findViewById(R.id.tvZona);

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Apartament apartament = (Apartament) getItem(position);

        if(apartament != null){
            viewHolder.tvAdresa.setText(apartament.getAdresa());
            viewHolder.tvZona.setText(apartament.getZona());
            viewHolder.tvSuprafata.setText((int) apartament.getSuprafata());
            viewHolder.tvPret.setText(Double.valueOf(apartament.getPret()).toString());
        }

        return convertView;
    }
}
