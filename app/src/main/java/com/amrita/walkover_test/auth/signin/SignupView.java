package com.amrita.walkover_test.auth.signin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amrita.walkover_test.R;
import com.amrita.walkover_test.auth.login.LoginView;
import com.amrita.walkover_test.utils.DBHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupView extends AppCompatActivity {

    @BindView(R.id.name_et)
    EditText name;

    @BindView(R.id.email_et)
    EditText email;

    @BindView(R.id.username_et)
    EditText username;

    @BindView(R.id.phone_et)
    EditText phone;

    @BindView(R.id.password_et)
    EditText password;

    @BindView(R.id.re_password_et)
    EditText repassword;

    @BindView(R.id.signup_button)
    Button signup;

    @BindView(R.id.go_to_login)
    TextView goToLogin;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_view);
        ButterKnife.bind(this);

        dbHelper = new DBHelper(this);
        try{
            dbHelper.openDatabase();
        } catch (SQLException e){
            e.printStackTrace();
        }

        setOnClickListeners();
    }

    private void setOnClickListeners() {

        signup.setOnClickListener( v -> {
            if(name.getText().toString().isEmpty()){
                name.setError("Enter your name");
            } else if(email.getText().toString().isEmpty()){
                email.setError("Enter your email");
            } else if(username.getText().toString().isEmpty()){
                username.setError("Enter your username");
            } else if(phone.getText().toString().isEmpty()){
                phone.setError("Enter your phone");
            } else if(password.getText().toString().isEmpty()){
                password.setError("Enter your password");
            } else if(repassword.getText().toString().isEmpty()){
                repassword.setError("Re enter your password");
            } else if(!password.getText().toString().equals(repassword.getText().toString())){
                repassword.setError("Enter the same password");
            } else {
                String stringName = name.getText().toString();
                String stringEmail = email.getText().toString();
                String stringUsername = username.getText().toString();
                String stringPhone = phone.getText().toString();
                String stringPassword = password.getText().toString();

                //DB Logic here
                if(dbHelper.checkUserExists(stringUsername)){
                    Toast.makeText(this, "This user already exists, please login", Toast.LENGTH_SHORT).show();
                } else {
                    dbHelper.insertInUserTable(stringName, stringUsername, stringEmail, stringPhone,
                            stringPassword);
                }
            }
        });

        goToLogin.setOnClickListener( v -> {
            startActivity(new Intent(SignupView.this, LoginView.class));
            finish();
        });
    }
}