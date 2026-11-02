package com.ase.ivascu_alexandra_activitatedam2026;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    public static final String KEY_MESAJ_PRIMIT = "KEY_MESAJ_PRIMIT";
    public static final String KEY_VALOARE1 = "KEY_VALOARE1";
    public static final String KEY_VALOARE2 = "KEY_VALOARE2";

    public static final String KEY_MESAJ_RETUR = "KEY_MESAJ_RETUR";
    public static final String KEY_SUMA = "KEY_SUMA";

    private TextView tvMesajPrimit;
    private TextView tvValoriPrimite;
    private TextView tvSumaCalculata;
    private EditText etMesajRetur;
    private Button btnTrimiteSumaInapoi;

    private int valoare1 = 0;
    private int valoare2 = 0;
    private int suma = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        tvMesajPrimit = findViewById(R.id.tvMesajPrimit);
        tvValoriPrimite = findViewById(R.id.tvValoriPrimite);
        tvSumaCalculata = findViewById(R.id.tvSumaCalculata);
        etMesajRetur = findViewById(R.id.etMesajRetur);
        btnTrimiteSumaInapoi = findViewById(R.id.btnTrimiteSumaInapoi);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            String mesajPrimit = bundle.getString(KEY_MESAJ_PRIMIT, "Fără mesaj");
            valoare1 = bundle.getInt(KEY_VALOARE1, 0);
            valoare2 = bundle.getInt(KEY_VALOARE2, 0);
            suma = valoare1 + valoare2;

            tvMesajPrimit.setText("Mesaj primit: " + mesajPrimit);
            tvValoriPrimite.setText("Valoare 1: " + valoare1 + " | Valoare 2: " + valoare2);
            tvSumaCalculata.setText("Suma calculată (" + valoare1 + " + " + valoare2 + "): " + suma);

            String toastText = "Activitatea 3 a primit:\n" +
                    "Mesaj: " + mesajPrimit + "\n" +
                    "Valori: " + valoare1 + " și " + valoare2;
            Toast.makeText(this, toastText, Toast.LENGTH_LONG).show();
        }

        btnTrimiteSumaInapoi.setOnClickListener(v -> trimiteSumaInapoi());
    }

    private void trimiteSumaInapoi() {
        String altMesaj = etMesajRetur.getText().toString().trim();
        if (altMesaj.isEmpty()) {
            altMesaj = "Răspuns din Activitatea 3 cu suma calculată!";
        }

        Intent returnIntent = new Intent();
        Bundle returnBundle = new Bundle();
        returnBundle.putString(KEY_MESAJ_RETUR, altMesaj);
        returnBundle.putInt(KEY_SUMA, suma);
        returnIntent.putExtras(returnBundle);

        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
