package com.ase.seminar6;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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
    private ArrayList<Automobil> automobile = new ArrayList<>();
    private ActivityResultLauncher automobileLaucher;

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

        automobileLaucher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == RESULT_OK && result.getData() != null){
                        Automobil automobilPrimit;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            automobilPrimit = result.getData().getParcelableExtra("automobil", Automobil.class);
                        }
                        else{
                            automobilPrimit = result.getData().getParcelableExtra("automobil");
                        }
                        if(automobilPrimit != null){
                            automobile.add(automobilPrimit);
                        }
                    }
                }
        );

        Button btnAdauga = findViewById(R.id.btnAdauga);
        btnAdauga.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddAutomobil.class);
            automobileLaucher.launch(intent);
        });
    }
}