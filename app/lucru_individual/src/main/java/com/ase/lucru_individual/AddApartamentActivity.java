package com.ase.lucru_individual;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class AddApartamentActivity extends AppCompatActivity {
    private EditText etAdresa;
    private EditText etSuprafata;
    private EditText etPret;
    private Spinner spZona;
    private CheckBox ckbDecomandat;
    private Button btnSalvare;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_apartament);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etAdresa = findViewById(R.id.adresa);
        etSuprafata = findViewById(R.id.suprafata);
        etPret = findViewById(R.id.pret);
        spZona = findViewById(R.id.zona);
        ckbDecomandat = findViewById(R.id.decomandat);
        btnSalvare = findViewById(R.id.salvare);

        String[] optiuni = {"Central", "Periferie"};
        ArrayAdapter optiuniAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, optiuni);
        spZona.setAdapter(optiuniAdapter);

        btnSalvare.setOnClickListener(v -> {
            String adresa = etAdresa.getText().toString().trim();
            double suprafata = Double.valueOf(etSuprafata.getText().toString().trim());
            double pret = Double.valueOf(etPret.getText().toString().trim());
            String zona = spZona.getSelectedItem().toString();
            Boolean decomandat = ckbDecomandat.isActivated();

            Apartament apartament = new Apartament(adresa, suprafata, pret, zona, decomandat);

            Intent returnIntent = new Intent();
            returnIntent.putExtra("apartament", (Parcelable)apartament);
            setResult(RESULT_OK, returnIntent);
            finish();
        });
    }
}