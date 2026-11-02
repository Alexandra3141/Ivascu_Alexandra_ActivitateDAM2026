package com.ase.seminar1;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivitateRezultat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activitate_rezultat);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent it = getIntent();
        Bundle bundle = it.getExtras();
        String mesaj = bundle.getString("mesaj", "");
        int nr1 = bundle.getInt("numar1", 0);
        int nr2 = bundle.getInt("numar2", 0);

        StringBuilder builder = new StringBuilder(mesaj);
        builder.append(" ");
        builder.append(nr1);
        builder.append(" ");
        builder.append(nr2);

        Toast.makeText(this, mesaj + " " + nr1 + " " + nr2, Toast.LENGTH_SHORT).show();
    }
}
