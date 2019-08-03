package com.nina.mobile.uitmattendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {

    private TextView idUser, nameUser;
    private Button logout_btn, scan_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        nameUser = findViewById(R.id.nameUser);
        idUser = findViewById(R.id.idUser);
        logout_btn = findViewById(R.id.logout_btn);
        scan_btn = findViewById(R.id.scan_btn);

        Intent intent = getIntent();
        String extraName = intent.getStringExtra("name");
        String extraId= intent.getStringExtra("ID number");

        nameUser.setText(extraName);
        idUser.setText(extraId);

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserActivity.this, LoginActivity.class));
                finish();
            }
        });

        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(UserActivity.this, QRScan.class));
            }
        });
    }
}
