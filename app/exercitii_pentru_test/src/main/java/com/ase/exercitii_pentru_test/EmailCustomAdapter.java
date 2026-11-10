package com.ase.exercitii_pentru_test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class EmailCustomAdapter extends BaseAdapter {
    private final Context context;
    private final int resource;
    private final List<Email> emailuri;

    public EmailCustomAdapter(Context context, int resource, List<Email> emailuri) {
        this.context = context;
        this.resource = resource;
        this.emailuri = emailuri;
    }

    @Override
    public int getCount() {
        return emailuri.size();
    }

    @Override
    public Object getItem(int position) {
        return emailuri.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private static class ViewHolder{
        ImageView imagine;
        TextView tvFrom;
        TextView tvTo;
        TextView tvDate;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, parent, false);

            holder = new ViewHolder();
            holder.imagine = convertView.findViewById(R.id.ivItemImagine);
            holder.tvFrom = convertView.findViewById(R.id.tvFrom);
            holder.tvTo = convertView.findViewById(R.id.tvTo);
            holder.tvDate = convertView.findViewById(R.id.tvData);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        Email email = (Email) getItem(position);

        if(email != null){
            holder.imagine.setImageResource(email.getImagine());
            holder.tvFrom.setText(email.getFrom());
            holder.tvTo.setText(email.getTo());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            String dateSdf = email.getDataTrimitere() != null ? sdf.format(email.getDataTrimitere()) : "-";
            holder.tvDate.setText(dateSdf);
        }

        return convertView;
    }
}
