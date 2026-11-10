package com.ase.exercitii_pentru_test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class EmailAdapter extends ArrayAdapter<Email> {
    private final Context context;
    private final int resource;
    private final List<Email> emailuri;
    public EmailAdapter(@NonNull Context context, int resource, @NonNull List<Email> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.emailuri = objects;
    }

    private static class ViewHolder{
        ImageView imagine;
        TextView tvFrom;
        TextView tvTo;
        TextView tvDate;
    }

    public View getView(int position, View view, ViewGroup parent){
        ViewHolder holder;

        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(resource, parent, false);

            holder = new ViewHolder();
            holder.imagine = view.findViewById(R.id.ivItemImagine);
            holder.tvFrom = view.findViewById(R.id.tvFrom);
            holder.tvTo = view.findViewById(R.id.tvTo);
            holder.tvDate = view.findViewById(R.id.tvData);

            view.setTag(holder);
        }
        else{
            holder = (ViewHolder) view.getTag();
        }

        Email email = emailuri.get(position);

        if(email != null){
            holder.imagine.setImageResource(email.getImagine());
            holder.tvFrom.setText(email.getFrom());
            holder.tvTo.setText(email.getTo());

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            String dateSdf = email.getDataTrimitere() != null ? sdf.format(email.getDataTrimitere()) : "-";
            holder.tvDate.setText(dateSdf);
        }

        return view;
    }
}
