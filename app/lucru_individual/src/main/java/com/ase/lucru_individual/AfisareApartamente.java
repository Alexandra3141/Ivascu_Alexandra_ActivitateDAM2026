package com.ase.lucru_individual;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class AfisareApartamente extends AppCompatActivity {
    private ArrayList<Apartament> apartamentList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afisare_apartamente);

        Intent intent = getIntent();
        Apartament apartament;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            apartamentList = intent.getParcelableArrayListExtra("apartament", Apartament.class);
        }
        else {
            apartamentList = intent.getParcelableArrayListExtra("apartament");
        }
        if(apartamentList == null){
            apartamentList = new ArrayList<>();
        }

        ListView lvApartamente = findViewById(R.id.lvApartamente);
        ApartamentAddapter adapter = new ApartamentAddapter(this, R.layout.item_apartament, apartamentList);
        lvApartamente.setAdapter(adapter);
    }
}