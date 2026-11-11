package com.ase.ex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

public class AsigurareAdapter extends BaseAdapter {
    private final Context context;
    private final int resource;
    private final List<Asigurare> asigurari;

    public AsigurareAdapter(Context context, int resource, List<Asigurare> asigurari) {
        this.context = context;
        this.resource = resource;
        this.asigurari = asigurari;
    }

    @Override
    public int getCount() {
        return asigurari.size();
    }

    @Override
    public Object getItem(int position) {
        return asigurari.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private static class HolderView{
        TextView tvId;
        TextView tvTitular;
        TextView tvCost;
        TextView tvTip;
        TextView tvStatus;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderView holder;

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_asigurare, parent, false);

            holder = new HolderView();

            holder.tvId = convertView.findViewById(R.id.tvId);
            holder.tvCost = convertView.findViewById(R.id.tvCost);
            holder.tvStatus = convertView.findViewById(R.id.tvStatus);
            holder.tvTitular = convertView.findViewById(R.id.tvTitular);
            holder.tvTip = convertView.findViewById(R.id.tvTip);

            convertView.setTag(holder);
        }
        else {
            holder = (HolderView) convertView.getTag();
        }

        Asigurare asigurare = asigurari.get(position);

        if(asigurare != null){
            holder.tvTip.setText(asigurare.getTipAsigurare());
            holder.tvId.setText(String.valueOf(asigurare.getIdAsigurare()));
            holder.tvTitular.setText(asigurare.getTitular());
            holder.tvCost.setText(String.valueOf(asigurare.getCostPolita()));
            holder.tvStatus.setText(asigurare.getEsteActivata() == true ? "Activ" : "Inactiv");
        }

        return convertView;
    }
}
