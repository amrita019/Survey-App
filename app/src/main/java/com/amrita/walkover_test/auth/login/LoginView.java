package com.amrita.walkover_test.auth.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amrita.walkover_test.R;
import com.amrita.walkover_test.auth.signin.SignupView;
import com.amrita.walkover_test.home.HomeView;
import com.amrita.walkover_test.utils.DBHelper;
import com.amrita.walkover_test.utils.SharedPrefs;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginView extends AppCompatActivity {

    @BindView(R.id.username_et)
    EditText username;

    @BindView(R.id.password_et)
    EditText password;

    @BindView(R.id.login_button)
    Button login;

    @BindView(R.id.go_to_signup)
    TextView goToSignup;

    private DBHelper dbHelper;
    private SharedPrefs sharedPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        sharedPrefs = new SharedPrefs(this);

        if(sharedPrefs.getIsLoggedIn()){
            startActivity(new Intent(LoginView.this, HomeView.class));
            finish();
        }

        dbHelper = new DBHelper(this);
        try{
            dbHelper.openDatabase();
        } catch (SQLException e){
            e.printStackTrace();
        }

        setOnClickListeners();
    }

    private void setOnClickListeners() {

            login.setOnClickListener( v -> {
                if(username.getText().toString().isEmpty() || username.getText().toString().equals(null)){
                    username.setError("Enter your username");
                } else if(password.getText().toString().isEmpty() || password.getText().toString().equals(null)){
                    password.setError("Enter your password");
                } else {
                    String stringUsername = username.getText().toString();
                    String stringPassword = password.getText().toString();

                    //DB Logic here

                    if(dbHelper.checkUserLogin(stringUsername, stringPassword)){
                        //move to home screen
                        sharedPrefs.setUserName(stringUsername);
                        sharedPrefs.setIsLoggedIn(true);
                        startActivity(new Intent(LoginView.this, HomeView.class));
                        finish();
                    } else {
                        Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }


                }
            });

            goToSignup.setOnClickListener( v -> {
                startActivity(new Intent(LoginView.this, SignupView.class));
                finish();
            });
    }
}