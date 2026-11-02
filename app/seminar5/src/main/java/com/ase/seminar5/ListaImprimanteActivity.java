package com.ase.seminar5;

import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListaImprimanteActivity extends AppCompatActivity {

    public static final String EXTRA_LISTA_IMPRIMANTE = "EXTRA_LISTA_IMPRIMANTE";

    private ListView lvImprimante;
    private TextView tvContorLista;
    private Button btnInapoiLista;

    private ArrayList<Imprimanta> listaImprimante;
    private ArrayAdapter<Imprimanta> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_imprimante);

        lvImprimante = findViewById(R.id.lvImprimante);
        tvContorLista = findViewById(R.id.tvContorLista);
        btnInapoiLista = findViewById(R.id.btnInapoiLista);

        ArrayList<Imprimanta> primita;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            primita = getIntent().getParcelableArrayListExtra(EXTRA_LISTA_IMPRIMANTE, Imprimanta.class);
        } else {
            primita = getIntent().getParcelableArrayListExtra(EXTRA_LISTA_IMPRIMANTE);
        }

        if (primita != null && !primita.isEmpty()) {
            listaImprimante = primita;
        } else {
            listaImprimante = ImprimantaRepository.getInstance().getListaImprimante();
        }

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                listaImprimante
        );
        lvImprimante.setAdapter(adapter);

        actualizeazaContor();

        lvImprimante.setOnItemClickListener((parent, view, position, id) -> {
            if (position >= 0 && position < listaImprimante.size()) {
                Imprimanta selectata = listaImprimante.get(position);
                Toast.makeText(
                        ListaImprimanteActivity.this,
                        "Selectat:\n" + selectata.toString(),
                        Toast.LENGTH_LONG
                ).show();
            }
        });

        lvImprimante.setOnItemLongClickListener((parent, view, position, id) -> {
            if (position >= 0 && position < listaImprimante.size()) {
                Imprimanta stearsa = listaImprimante.remove(position);

                if (ImprimantaRepository.getInstance().getListaImprimante() != listaImprimante) {
                    ImprimantaRepository.getInstance().stergeImprimanta(position);
                }

                adapter.notifyDataSetChanged();
                actualizeazaContor();

                Toast.makeText(
                        ListaImprimanteActivity.this,
                        "Obiect șters: " + stearsa.getProducator() + " (" + stearsa.getPret() + " RON)",
                        Toast.LENGTH_SHORT
                ).show();
                return true;
            }
            return false;
        });

        btnInapoiLista.setOnClickListener(v -> finish());
    }

    private void actualizeazaContor() {
        if (tvContorLista != null) {
            tvContorLista.setText("Total imprimante în listă: " + listaImprimante.size());
        }
    }
}
