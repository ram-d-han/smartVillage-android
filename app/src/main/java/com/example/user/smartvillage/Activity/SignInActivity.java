package com.example.user.smartvillage.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.smartvillage.Activity.dashboard_user.MainActivity;
import com.example.user.smartvillage.Controller.SessionManager;
import com.example.user.smartvillage.Model.User;
import com.example.user.smartvillage.R;
import com.example.user.smartvillage.service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;

public class SignInActivity extends AppCompatActivity {

    Button button_signin, button_linksignup;
    EditText et_username, et_password;
    String susername, spassword;
    Intent afterlogin;
    ProgressDialog pDialog;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        et_username = (EditText) findViewById(R.id.username);
        et_password = (EditText) findViewById(R.id.password);
        button_signin = (Button) findViewById(R.id.button_signin);
        button_linksignup = (Button) findViewById(R.id.button_link_signup);
        sessionManager = new SessionManager(getApplicationContext());
        button_linksignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO PINDAH INTENT KE SINGUP
                Intent activityselanjutnya = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(activityselanjutnya);
            }
        });

        button_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO LOGIN
                susername = et_username.getText().toString().trim();
                spassword = et_password.getText().toString().trim();
                if (!susername.isEmpty() && !spassword.isEmpty())
                {
                    doSignIn(susername,spassword);
                }else {
                    Toast.makeText(SignInActivity.this, "data kosong", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void doSignIn(final String username, final String password) {
        pDialog = new ProgressDialog(SignInActivity.this, R.style.Theme_AppCompat_DayNight_Dialog);
        pDialog.setMessage("Authenticating....");
        pDialog.show();

        ApiService.service_post.postSignIn(username, password).enqueue(new Callback<User> () {
            @Override
            public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                System.out.println(username+"  " + response);
                System.out.println(response.body().isStatus());
                assert response.body() != null;
                if (response.body().isStatus()){
                        System.out.println("response success");
                        pDialog.dismiss();
                        Intent dashboard = new Intent(SignInActivity.this, MainActivity.class);
                        startActivity(dashboard);
                        finish();
                    }else{
                        Toast.makeText(SignInActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        pDialog.dismiss();
                    }
                }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println("response failed");
                pDialog.dismiss();
                Toast.makeText(SignInActivity.this, "Username atau password tidak sesuai!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
