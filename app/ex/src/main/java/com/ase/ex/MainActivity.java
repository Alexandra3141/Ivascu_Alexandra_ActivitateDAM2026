package com.ase.ex;

import android.app.Activity;
import android.app.appfunctions.AppFunctionSearchSpec;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Asigurare> listaAsigurari = new ArrayList<>();
    private ActivityResultLauncher adaugaAsigurareLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        adaugaAsigurareLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == RESULT_OK && result.getData() != null){
                        Bundle bundle = new Bundle();
                        bundle = result.getData().getExtras();
                        Asigurare asigurare;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            asigurare = bundle.getParcelable("asigurare", Asigurare.class);
                        }
                        else {
                            asigurare = bundle.getParcelable("asigurare");
                        }
                        if(asigurare != null){
                            listaAsigurari.add(asigurare);
                        }
                    }
                }
        );

        Button adaugaAsigurare = findViewById(R.id.ivascu_alexandra_btnAdauga);
        Button afiseazaLista = findViewById(R.id.ivascu_alexandra_btnAfiseaza);

        adaugaAsigurare.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddAsigurareActivity.class);
            adaugaAsigurareLauncher.launch(intent);
        });

        afiseazaLista.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListaAsigurariActivity.class);
            intent.putExtra("lista", (Parcelable) listaAsigurari);
            startActivity(intent);
        });
    }
}