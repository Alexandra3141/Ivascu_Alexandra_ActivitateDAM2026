package com.ase.seminar5;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "LifecycleMainActivity";

    private Button btnInapoiLaActivitate2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.v(TAG, "onCreate() apelat: Activitatea 1 a fost inițializată.");

        btnInapoiLaActivitate2 = findViewById(R.id.btnInapoiLaActivitate2);
        btnInapoiLaActivitate2.setOnClickListener(v -> {
            if (isTaskRoot()) {
                startActivity(new Intent(MainActivity.this, Activitate2.class));
            }
            finish();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart() apelat: Activitatea devine vizibilă pentru utilizator.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() apelat: Activitatea este în prim-plan și gata de interacțiune.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w(TAG, "onPause apelat: Activitatea este parțial acoperită sau trece în fundal.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop() apelat: Activitatea nu mai este vizibilă pe ecran.");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart() apelat: Activitatea este repornită înainte de onStart.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy() apelat: Activitatea a fost distrusă complet.");
    }
}
