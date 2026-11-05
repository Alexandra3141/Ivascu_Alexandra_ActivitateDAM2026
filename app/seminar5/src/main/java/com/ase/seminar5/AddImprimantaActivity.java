package com.ase.seminar5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class AddImprimantaActivity extends AppCompatActivity {

    public static final String EXTRA_IMPRIMANTA_BUNDLE = "EXTRA_IMPRIMANTA_BUNDLE";
    public static final String KEY_IMPRIMANTA_OBJ = "KEY_IMPRIMANTA_OBJ";

    private EditText etProducator;
    private EditText etPret;
    private CheckBox cbColor;
    private RadioGroup rgConexiune;
    private RadioButton rbWifi;
    private RadioButton rbUsb;
    private RadioButton rbBluetooth;
    private Spinner spTehnologie;
    private RatingBar rbRating;
    private SwitchCompat swGarantie;
    private Button btnSalveazaImprimanta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_imprimanta);

        initializareControale();
        populareSpinner();

        Intent it = getIntent();
        if(it.hasExtra("imprimanta")){
            Imprimanta imprimanta = it.getParcelableExtra("imprimanta");
            EditText producatorET = findViewById(R.id.etProducator);
            EditText pretET = findViewById(R.id.etPret);
            RadioGroup tipConexiuneRG = findViewById(R.id.rgConexiune);
            RadioButton ratingRB = findViewById(R.id.rbRating);
            SwitchCompat areGarantieCB = findViewById(R.id.swGarantie);

            producatorET.setText(imprimanta.getProducator());
            pretET.setText((int) imprimanta.getPret());
            if ("USB".equalsIgnoreCase(imprimanta.getTipConexiune())) {
                rgConexiune.check(R.id.rbUsb);
            } else if ("Bluetooth".equalsIgnoreCase(imprimanta.getTipConexiune())) {
                rgConexiune.check(R.id.rbBluetooth);
            } else {
                rgConexiune.check(R.id.rbWifi);
            }
        }

        btnSalveazaImprimanta.setOnClickListener(v -> salveazaSiTrimite());
    }

    private void initializareControale() {
        etProducator = findViewById(R.id.etProducator);
        etPret = findViewById(R.id.etPret);
        cbColor = findViewById(R.id.cbColor);
        rgConexiune = findViewById(R.id.rgConexiune);
        rbWifi = findViewById(R.id.rbWifi);
        rbUsb = findViewById(R.id.rbUsb);
        rbBluetooth = findViewById(R.id.rbBluetooth);
        spTehnologie = findViewById(R.id.spTehnologie);
        rbRating = findViewById(R.id.rbRating);
        swGarantie = findViewById(R.id.swGarantie);
        btnSalveazaImprimanta = findViewById(R.id.btnSalveazaImprimanta);
    }

    private void populareSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.tehnologii_imprimanta,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTehnologie.setAdapter(adapter);
    }

    private void salveazaSiTrimite() {
        String producator = etProducator.getText().toString().trim();
        String pretStr = etPret.getText().toString().trim();

        if (producator.isEmpty()) {
            etProducator.setError("Introduceți producătorul!");
            etProducator.requestFocus();
            return;
        }

        if (pretStr.isEmpty()) {
            etPret.setError("Introduceți prețul!");
            etPret.requestFocus();
            return;
        }

        double pret;
        try {
            pret = Double.parseDouble(pretStr);
        } catch (NumberFormatException e) {
            etPret.setError("Preț invalid!");
            etPret.requestFocus();
            return;
        }

        boolean esteColor = cbColor.isChecked();

        String tipConexiune = "Wi-Fi";
        int checkedRadioId = rgConexiune.getCheckedRadioButtonId();
        if (checkedRadioId == R.id.rbUsb) {
            tipConexiune = "USB";
        } else if (checkedRadioId == R.id.rbBluetooth) {
            tipConexiune = "Bluetooth";
        }

        String tehnologie = spTehnologie.getSelectedItem() != null ?
                spTehnologie.getSelectedItem().toString() : "Laser";

        float rating = rbRating.getRating();
        boolean areGarantie = swGarantie.isChecked();

        Imprimanta imprimanta = new Imprimanta(
                producator, pret, esteColor, tipConexiune, tehnologie, rating, areGarantie
        );

        ImprimantaRepository.getInstance().adaugaImprimanta(imprimanta);

        Intent returnIntent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_IMPRIMANTA_OBJ, imprimanta);
        bundle.putSerializable("KEY_IMPRIMANTA_SERIALIZABLE", imprimanta);
        returnIntent.putExtra(EXTRA_IMPRIMANTA_BUNDLE, bundle);

        setResult(RESULT_OK, returnIntent);
        Toast.makeText(this, "Imprimanta a fost salvată cu succes!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
