package com.nina.mobile.uitmattendance;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText userid, password;
    private Button btn_login;
    private ProgressBar loading;
    private static String URL_LOGIN = "http://172.16.25.145/attendance/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loading = findViewById(R.id.loading);
        userid = findViewById(R.id.userid);
        password = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onClick(View v) {
                String mMatrixnum = userid.getText().toString().trim();
                String mPass = password.getText().toString().trim();

                if(!mMatrixnum.isEmpty() || !mPass.isEmpty()) {
                    Login(mMatrixnum, mPass);
                }
                else {
                    userid.setError("Please insert ID number");
                    password.setError("Please insert password");
                }
            }
        });
    }

    private void Login(final String userid, final String password) {

        loading.setVisibility(View.VISIBLE);
        btn_login.setVisibility(View.GONE);

        StringRequest request = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    //public TextView name;

                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(getApplicationContext(), " this response : "+response, Toast.LENGTH_SHORT).show();

                        if (response.contains("1")) {

                            //final String name = this.name.toString().trim();
//                            startActivity(new Intent(getApplicationContext(), HomeuserActivity.class));

//                            Intent intent = new Intent(LoginActivity.this, HomeuserActivity.class);
//                            startActivity(intent);

                            loading.setVisibility(View.GONE);

                        }
                        else if (response.contains("2")) {

                            //final String name = this.name.toString().trim();
//                            startActivity(new Intent(getApplicationContext(), HomeStaffActivity.class));

//                            Intent intent = new Intent(LoginActivity.this, HomeStaffActivity.class);
//                            startActivity(intent);

                            loading.setVisibility(View.GONE);

                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Wrong ID Number or Password ", Toast.LENGTH_SHORT).show();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("ID number", userid);
                params.put("Password",password);
                return params;
            }

        };

        Volley.newRequestQueue(this).add(request);
    }
}
