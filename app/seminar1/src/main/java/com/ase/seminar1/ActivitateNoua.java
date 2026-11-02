package com.ase.seminar1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class ActivitateNoua extends AppCompatActivity {
    public void deschideActivitate(View view) {
        Intent it = new Intent(this, ActivitateRezultat.class);

        Bundle bundle = new Bundle();
        bundle.putString("mesaj", "Mesaj din activitate noua");
        bundle.putInt("numar1", 5);
        bundle.putInt("numar2", 7);
        it.putExtras(bundle);

        startActivity(it);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List<Magazin> cafele = new ArrayList<>();
        cafele.add(new Magazin(1, "Lidl", "Calea Dorobanti", 10));
        cafele.add(new Magazin(2, "Profi", "Calea Dorobanti", 5));
        cafele.add(new Magazin(3, "Mega Image", "Calea Dorobanti", 10));
        cafele.add(new Magazin(4, "Penny", "Calea Dorobanti", 10));
    }
}