package com.ase.exercitii_pentru_test;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public ArrayList<Email> Emailuri = new ArrayList<>();
    private ActivityResultLauncher<Intent> emailLauncher;
    private EmailAdapter adapter;
    private EmailCustomAdapter customAdapter;

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

//        ListView lvEmailuri = findViewById(R.id.lvEmailuri);
//        adapter = new EmailAdapter(this, R.layout.item_email, Emailuri);
//        lvEmailuri.setAdapter(adapter);

        ListView lvEmailuri = findViewById(R.id.lvEmailuri);
        customAdapter = new EmailCustomAdapter(this, R.layout.item_email, Emailuri);
        lvEmailuri.setAdapter(customAdapter);

        emailLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == RESULT_OK && result.getData() != null){
                        Email emailPrimit;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            emailPrimit = result.getData().getParcelableExtra("email", Email.class);
                        }
                        else{
                            emailPrimit = result.getData().getParcelableExtra("email");
                        }
                        if(emailPrimit!=null){
                            Emailuri.add(emailPrimit);
                        }
                    }
                }
        );

        Button btnAdauga = findViewById(R.id.button);
        btnAdauga.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEmailActivity.class);
            emailLauncher.launch(intent);
        });

        lvEmailuri.setOnItemClickListener((parent, view, position, id) -> {
            Email email = Emailuri.get(position);
            Toast.makeText(MainActivity.this, email.getFrom(), Toast.LENGTH_SHORT).show();
        });
    }
}