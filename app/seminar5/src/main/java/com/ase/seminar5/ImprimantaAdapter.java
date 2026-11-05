package com.ase.seminar5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class ImprimantaAdapter extends BaseAdapter {
    private Context ctx;
    private List<Imprimanta> imprimante;
    private int resurseLayout;

    public ImprimantaAdapter(Context ctx, List<Imprimanta> imprimante, int resurseLayout){
        this.ctx = ctx;
        this.imprimante = imprimante;
        this.resurseLayout = resurseLayout;
    }

    @Override
    public int getCount() {
        return this.imprimante.size();
    }

    @Override
    public Object getItem(int position) {
        return this.imprimante.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(resurseLayout, viewGroup, false);

        TextView producatorTV = v.findViewById(R.id.producator);
        TextView pretTV = v.findViewById(R.id.pret);
        TextView tipConexiuneTV = v.findViewById(R.id.tipConexiune);
        TextView ratingTV = v.findViewById(R.id.rating);
        CheckBox areGarantieCB = v.findViewById(R.id.areGarantie);

        Imprimanta imprimante = (Imprimanta) getItem(position);

        producatorTV.setText(imprimante.getProducator());
        pretTV.setText((int) imprimante.getPret());
        tipConexiuneTV.setText(imprimante.getTipConexiune());
        ratingTV.setText((int) imprimante.getRating());
        areGarantieCB.setChecked(imprimante.isAreGarantieExtinsa());

        return v;
    }
}
