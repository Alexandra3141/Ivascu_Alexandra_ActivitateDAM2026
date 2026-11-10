package com.ase.lucru_individual;

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
    private ActivityResultLauncher<Intent> activityResultLauncher;

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
                        Apartament apartamentPrimit;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            apartamentPrimit = result.getData().getParcelableExtra("apartament", Apartament.class);
                        }
                        else{
                            apartamentPrimit = result.getData().getParcelableExtra("apartament");
                        }
                        if(apartamentPrimit != null){
                            apartamente.add(apartamentPrimit);
                        }
                    }
                }
        );

        Button btnAdaugaApartament = findViewById(R.id.adaugaApartament);
        btnAdaugaApartament.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddApartamentActivity.class);
            activityResultLauncher.launch(intent);
        });
    }
}