package com.ase.seminar6;

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

public class AddAutomobil extends AppCompatActivity {
    private EditText etMarca;
    private EditText etAnFabricatie;
    private EditText etPret;
    private Spinner spConbustibil;
    private CheckBox cbGarantie;
    private Button btnSalvare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_automobil);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etMarca = findViewById(R.id.etMarca);
        etAnFabricatie = findViewById(R.id.etAnFabricatie);
        etPret = findViewById(R.id.etPret);
        spConbustibil = findViewById(R.id.spCombustibil);
        cbGarantie = findViewById(R.id.cbGarantie);
        btnSalvare = findViewById(R.id.btnSalvare);

        String[] optiuni = {"Benzina", "Motorina", "Hibrid"};
        ArrayAdapter adapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, optiuni);
        spConbustibil.setAdapter(adapter);

         btnSalvare.setOnClickListener(v -> {
             String marca = etMarca.getText().toString().trim();
             Integer an = Integer.parseInt(etAnFabricatie.getText().toString().trim());
             double pret = Double.parseDouble(etPret.getText().toString().trim());
             String combustibil = spConbustibil.getSelectedItem().toString();
             Boolean garantie = cbGarantie.isChecked();

             Automobil automobil = new Automobil(marca, an, pret, combustibil, garantie);

             Intent intent = new Intent();
             intent.putExtra("automobil", (Parcelable) automobil);
             setResult(RESULT_OK, intent);
             finish();
         });
    }
}