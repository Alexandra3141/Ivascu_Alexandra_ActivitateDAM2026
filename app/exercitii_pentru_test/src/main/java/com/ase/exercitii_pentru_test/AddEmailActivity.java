package com.ase.exercitii_pentru_test;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;

public class AddEmailActivity extends AppCompatActivity {
    private EditText edFrom;
    private EditText edTo;
    private Switch swCitit;
    private EditText edData;
    private Spinner spImagine;
    private Button btnSalvare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_email);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edFrom = findViewById(R.id.editTextText);
        edTo = findViewById(R.id.editTextText2);
        edData = findViewById(R.id.editTextDate);
        swCitit = findViewById(R.id.switch1);
        spImagine = findViewById(R.id.spinner2);
        btnSalvare = findViewById(R.id.button2);

        String optiuni = Arrays.toString(new String[]{"General", "Important", "Spam"});

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Collections.singletonList(optiuni));

        spImagine.setAdapter(spinnerAdapter);

        SimpleDateFormat sdfDefault = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        edData.setText(sdfDefault.format(new Date()));

        btnSalvare.setOnClickListener(v -> {
            String from = edFrom.getText().toString().trim();
            String to = edTo.getText().toString().trim();
            String dateStr = edData.getText().toString().trim();

            if(from.isEmpty()){
                edFrom.setError("Introduceti expeditorul");
                edFrom.requestFocus();
                return;
            }

            if(to.isEmpty()){
                edTo.setError("Introduceti destinatarul");
                edTo.requestFocus();
                return;
            }

            Date dataTrimitere;
            try{
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                dataTrimitere = sdf.parse(dateStr);
            } catch (ParseException e) {
                dataTrimitere = new Date();
            }

            Boolean citit = swCitit.isChecked();
            int imagine = R.drawable.ic_launcher_foreground;

            Email email = new Email(from, to, imagine, dataTrimitere, citit);

            Intent returnIntent = new Intent();
            returnIntent.putExtra("email", (Parcelable) email);
            setResult(RESULT_OK, returnIntent);
            finish();
        });
    }
}