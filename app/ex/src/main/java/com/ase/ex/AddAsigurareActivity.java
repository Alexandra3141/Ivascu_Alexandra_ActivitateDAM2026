package com.ase.ex;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class AddAsigurareActivity extends AppCompatActivity {
    private EditText etId;
    private EditText etTitular;
    private EditText etCost;
    private Spinner spTip;
    private CheckBox cbActivata;
    private Button btnSalvare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_asigurare);

        etTitular = findViewById(R.id.ivascu_alexandra_etTitular);
        etId = findViewById(R.id.ivascu_alexandra_etId);
        etCost = findViewById(R.id.ivascu_alexandra_etCost);
        spTip = findViewById(R.id.ivascu_alexandra_spTip);
        cbActivata = findViewById(R.id.ivascu_alexandra_cbActivata);
        btnSalvare = findViewById(R.id.ivascu_alexandra_btnSalvare);

        String[] optiuni = {"RCA", "de viata", "apartament"};
        ArrayAdapter adapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, optiuni);
        spTip.setAdapter(adapter);

        btnSalvare.setOnClickListener(v -> {
            String titular = etTitular.getText().toString().trim();
            Integer id = Integer.parseInt(etId.getText().toString().trim());
            double cost = Double.parseDouble(etCost.getText().toString().trim());
            String tip = spTip.getSelectedItem().toString();
            Boolean activa = cbActivata.isChecked();

            Asigurare asigurare = new Asigurare(id, titular, cost, tip, activa);

            Bundle bundle = new Bundle();
            bundle.putParcelable("asigurare", asigurare);
            Intent intent = new Intent();
            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}
