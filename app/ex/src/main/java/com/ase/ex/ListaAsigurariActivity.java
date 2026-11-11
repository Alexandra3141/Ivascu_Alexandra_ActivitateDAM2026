package com.ase.ex;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListaAsigurariActivity extends AppCompatActivity {
    private ArrayList<Asigurare> listaAsigurari = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_asigurari);

        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            listaAsigurari = intent.getParcelableArrayListExtra("lista", Asigurare.class);
        }
        else{
            listaAsigurari = intent.getParcelableArrayListExtra("lista");
        }

        ListView lvAsigurari = findViewById(R.id.ivascu_alexandra_lvAsigurari);
        AsigurareAdapter adapter = new AsigurareAdapter(this, R.layout.item_asigurare, listaAsigurari);
        lvAsigurari.setAdapter(adapter);

        lvAsigurari.setOnItemClickListener((parent, view, position, id) -> {
            Asigurare asig = listaAsigurari.get(position);
            Toast.makeText(this, asig.toString(), Toast.LENGTH_SHORT).show();
        });

        lvAsigurari.setOnItemLongClickListener((parent, view, position, id) -> {
            Asigurare asig = listaAsigurari.get(position);
            asig.setCostPolita(asig.getCostPolita() * 0.9);
            adapter.notifyDataSetChanged();
            return true;
        });
    }
}
