package com.ase.ivascu_alexandra_activitatedam2026;

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

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class AddImprimantaAiActivity extends AppCompatActivity {

    private EditText etAiProducator;
    private EditText etAiPret;
    private CheckBox cbAiColor;
    private RadioGroup rgAiConexiune;
    private RadioButton rbAiWifi;
    private RadioButton rbAiUsb;
    private RadioButton rbAiBluetooth;
    private Spinner spAiTehnologie;
    private RatingBar rbAiRating;
    private SwitchCompat swAiGarantie;
    private Button btnAiSalveaza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_imprimanta_ai);

        initializareControale();
        populareSpinner();

        btnAiSalveaza.setOnClickListener(v -> salveazaCuAi());
    }

    private void initializareControale() {
        etAiProducator = findViewById(R.id.etAiProducator);
        etAiPret = findViewById(R.id.etAiPret);
        cbAiColor = findViewById(R.id.cbAiColor);
        rgAiConexiune = findViewById(R.id.rgAiConexiune);
        rbAiWifi = findViewById(R.id.rbAiWifi);
        rbAiUsb = findViewById(R.id.rbAiUsb);
        rbAiBluetooth = findViewById(R.id.rbAiBluetooth);
        spAiTehnologie = findViewById(R.id.spAiTehnologie);
        rbAiRating = findViewById(R.id.rbAiRating);
        swAiGarantie = findViewById(R.id.swAiGarantie);
        btnAiSalveaza = findViewById(R.id.btnAiSalveaza);
    }

    private void populareSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.tehnologii_imprimanta,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAiTehnologie.setAdapter(adapter);
    }

    private void salveazaCuAi() {
        String producator = etAiProducator.getText().toString().trim();
        String pretStr = etAiPret.getText().toString().trim();

        if (producator.isEmpty()) {
            etAiProducator.setError("Vă rugăm să introduceți marca imprimantei!");
            etAiProducator.requestFocus();
            return;
        }

        if (pretStr.isEmpty()) {
            etAiPret.setError("Vă rugăm să specificați prețul!");
            etAiPret.requestFocus();
            return;
        }

        double pret;
        try {
            pret = Double.parseDouble(pretStr);
        } catch (NumberFormatException e) {
            etAiPret.setError("Prețul este invalid!");
            etAiPret.requestFocus();
            return;
        }

        boolean esteColor = cbAiColor.isChecked();

        String tipConexiune = "Wi-Fi";
        int checkedId = rgAiConexiune.getCheckedRadioButtonId();
        if (checkedId == R.id.rbAiUsb) {
            tipConexiune = "USB";
        } else if (checkedId == R.id.rbAiBluetooth) {
            tipConexiune = "Bluetooth";
        }

        String tehnologie = spAiTehnologie.getSelectedItem() != null ?
                spAiTehnologie.getSelectedItem().toString() : "Laser";

        float rating = rbAiRating.getRating();
        boolean areGarantie = swAiGarantie.isChecked();

        Imprimanta imprimanta = new Imprimanta(
                producator, pret, esteColor, tipConexiune, tehnologie, rating, areGarantie
        );

        ImprimantaRepository.getInstance().adaugaImprimanta(imprimanta);

        Intent returnIntent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putParcelable(AddImprimantaActivity.KEY_IMPRIMANTA_OBJ, imprimanta);
        bundle.putSerializable("KEY_IMPRIMANTA_SERIALIZABLE", imprimanta);
        returnIntent.putExtra(AddImprimantaActivity.EXTRA_IMPRIMANTA_BUNDLE, bundle);

        setResult(RESULT_OK, returnIntent);
        Toast.makeText(this, "AI Design: Imprimantă înregistrată cu succes!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
