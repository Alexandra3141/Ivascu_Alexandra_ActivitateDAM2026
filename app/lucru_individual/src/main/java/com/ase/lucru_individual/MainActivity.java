package com.ase.lucru_individual;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Apartament> apartamente = new ArrayList<>();
    private ActivityResultLauncher activityResultLauncher;

    @SuppressLint("MissingInflatedId")
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

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == RESULT_OK && result.getData() != null){
                        Apartament apartament;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            apartament = result.getData().getParcelableExtra("apartament", Apartament.class);
                        }
                        else{
                            apartament = result.getData().getParcelableExtra("apartament");
                        }
                        if(apartament != null){
                            apartamente.add(apartament);
                        }
                    }
                }
        );

        Button btnAdauga = findViewById(R.id.adaugaApartament);
        Button btnAfiseaza = findViewById(R.id.afisare);

        btnAdauga.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddApartamentActivity.class);
            activityResultLauncher.launch(intent);
        });

        btnAfiseaza.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AfisareApartamente.class);
            intent.putParcelableArrayListExtra("apartament", apartamente);
            startActivity(intent);
        });
    }
}