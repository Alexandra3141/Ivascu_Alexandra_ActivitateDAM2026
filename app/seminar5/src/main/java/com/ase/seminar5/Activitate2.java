package com.ase.seminar5;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Activitate2 extends AppCompatActivity {

    public static final String TAG = "Activitate2";

    private TextView tvTotalImprimante;
    private Button btnDeschideListView;

    private Button btnDeschideAdaugareClasic;
    private Button btnDeschideAdaugareAi;
    private TextView tvUltimulObiectPrimit;

    private EditText etMesajPentruAct3;
    private EditText etValoare1;
    private EditText etValoare2;
    private Button btnDeschideAct3;
    private TextView tvRezultatAct3;
    private Button btnDeschideLifecycle;

    private ArrayList<Imprimanta> listaImprimante;

    private ActivityResultLauncher<Intent> act3Launcher;
    private ActivityResultLauncher<Intent> addImprimantaLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitate2);

        Log.v(TAG, "onCreate: Activitatea 2 lansată.");

        listaImprimante = ImprimantaRepository.getInstance().getListaImprimante();

        initializareControale();
        configurareLaunchers();
        atasareEvenimente();
        actualizeazaContor();
    }

    private void initializareControale() {
        tvTotalImprimante = findViewById(R.id.tvTotalImprimante);
        btnDeschideListView = findViewById(R.id.btnDeschideListView);

        btnDeschideAdaugareClasic = findViewById(R.id.btnDeschideAdaugareClasic);
        btnDeschideAdaugareAi = findViewById(R.id.btnDeschideAdaugareAi);
        tvUltimulObiectPrimit = findViewById(R.id.tvUltimulObiectPrimit);

        etMesajPentruAct3 = findViewById(R.id.etMesajPentruAct3);
        etValoare1 = findViewById(R.id.etValoare1);
        etValoare2 = findViewById(R.id.etValoare2);
        btnDeschideAct3 = findViewById(R.id.btnDeschideAct3);
        tvRezultatAct3 = findViewById(R.id.tvRezultatAct3);
        btnDeschideLifecycle = findViewById(R.id.btnDeschideLifecycle);
    }

    private void configurareLaunchers() {
        act3Launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Bundle bundleRetur = result.getData().getExtras();
                        if (bundleRetur != null) {
                            String mesajRetur = bundleRetur.getString(Activitate3.KEY_MESAJ_RETUR, "");
                            int sumaCalculata = bundleRetur.getInt(Activitate3.KEY_SUMA, 0);

                            String textToast = "Răspuns primit din Activitatea 3:\n" +
                                    "Mesaj: " + mesajRetur + "\n" +
                                    "Suma calculată: " + sumaCalculata;
                            Toast.makeText(Activitate2.this, textToast, Toast.LENGTH_LONG).show();

                            tvRezultatAct3.setText("Rezultat primit din Activitatea 3:\n• Mesaj: " +
                                    mesajRetur + "\n• Sumă: " + sumaCalculata);
                        }
                    }
                }
        );

        addImprimantaLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Bundle bundlePrimit = result.getData().getBundleExtra(AddImprimantaActivity.EXTRA_IMPRIMANTA_BUNDLE);
                        Imprimanta nouaImprimanta = null;

                        if (bundlePrimit != null) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                nouaImprimanta = bundlePrimit.getParcelable(AddImprimantaActivity.KEY_IMPRIMANTA_OBJ, Imprimanta.class);
                            } else {
                                nouaImprimanta = bundlePrimit.getParcelable(AddImprimantaActivity.KEY_IMPRIMANTA_OBJ);
                            }

                            if (nouaImprimanta == null) {
                                nouaImprimanta = (Imprimanta) bundlePrimit.getSerializable("KEY_IMPRIMANTA_SERIALIZABLE");
                            }
                        }

                        if (nouaImprimanta != null) {
                            if (!listaImprimante.contains(nouaImprimanta)) {
                                listaImprimante.add(nouaImprimanta);
                            }

                            String info = "Obiect primit prin Bundle:\n" + nouaImprimanta.toString();
                            Toast.makeText(Activitate2.this, info, Toast.LENGTH_LONG).show();

                            tvUltimulObiectPrimit.setText("Ultimul obiect adăugat:\n" + nouaImprimanta.toString());
                            actualizeazaContor();
                        }
                    }
                }
        );
    }

    private void atasareEvenimente() {
        btnDeschideListView.setOnClickListener(v -> {
            Intent intent = new Intent(Activitate2.this, ListaImprimanteActivity.class);
            intent.putParcelableArrayListExtra(ListaImprimanteActivity.EXTRA_LISTA_IMPRIMANTE, listaImprimante);
            startActivity(intent);
        });

        btnDeschideAdaugareClasic.setOnClickListener(v -> {
            Intent intent = new Intent(Activitate2.this, AddImprimantaActivity.class);
            addImprimantaLauncher.launch(intent);
        });

        btnDeschideAdaugareAi.setOnClickListener(v -> {
            Intent intent = new Intent(Activitate2.this, AddImprimantaAiActivity.class);
            addImprimantaLauncher.launch(intent);
        });

        btnDeschideAct3.setOnClickListener(v -> deschideActivitatea3());

        btnDeschideLifecycle.setOnClickListener(v -> {
            Intent intent = new Intent(Activitate2.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void deschideActivitatea3() {
        String mesaj = etMesajPentruAct3.getText().toString().trim();
        if (mesaj.isEmpty()) {
            mesaj = "Mesaj din Activitatea 2";
        }

        int val1 = 15;
        int val2 = 27;

        try {
            val1 = Integer.parseInt(etValoare1.getText().toString().trim());
        } catch (NumberFormatException ignored) {}

        try {
            val2 = Integer.parseInt(etValoare2.getText().toString().trim());
        } catch (NumberFormatException ignored) {}

        Intent intent = new Intent(Activitate2.this, Activitate3.class);
        Bundle bundle = new Bundle();
        bundle.putString(Activitate3.KEY_MESAJ_PRIMIT, mesaj);
        bundle.putInt(Activitate3.KEY_VALOARE1, val1);
        bundle.putInt(Activitate3.KEY_VALOARE2, val2);
        intent.putExtras(bundle);

        act3Launcher.launch(intent);
    }

    private void actualizeazaContor() {
        if (tvTotalImprimante != null) {
            tvTotalImprimante.setText("Total imprimante în listă: " + listaImprimante.size());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        actualizeazaContor();
        Log.d(TAG, "onResume: Activitatea 2 este din nou în prim-plan.");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart apelat.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w(TAG, "onPause apelat.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop apelat.");
    }
}
