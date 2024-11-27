package com.sandro.hobayan.recipesharingplatform.signin_signup;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.sandro.hobayan.recipesharingplatform.MainActivity;
import com.sandro.hobayan.recipesharingplatform.R;
import com.sandro.hobayan.recipesharingplatform.SessionManager;

public class SignIn extends AppCompatActivity {

    EditText email, password;
    Button signIn, toSignUp;

    private FirebaseAuth mAuth;

    private SessionManager sessionManager;  // connected sa SessionManager.java para ma import

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        email = findViewById(R.id.et_email_login);
        password = findViewById(R.id.et_password_login);
        signIn = findViewById(R.id.btn_signin);
        toSignUp = findViewById(R.id.btn_to_signup_page);

        mAuth = FirebaseAuth.getInstance();
        sessionManager = new SessionManager(getApplicationContext()); // sesssion para still naka login

        signIn.setOnClickListener(v-> signIn());
        toSignUp.setOnClickListener(v-> toSignUpPage());

    }

    private void signIn(){
        String emailStr = email.getText().toString();
        String passwordStr = password.getText().toString();

        if(emailStr.isEmpty() || passwordStr.isEmpty()){
            Toast.makeText(SignIn.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(emailStr, passwordStr)
                .addOnCompleteListener(this, task ->{
            if(task.isSuccessful()){
                //edi i-login
                sessionManager.createLogInSession(emailStr);
                Toast.makeText(SignIn.this, "Login successful", Toast.LENGTH_SHORT).show();
                Intent main = new Intent(SignIn.this, MainActivity.class);
                startActivity(main);
                finish();
            }else{
                // pag di naka login display message
                Toast.makeText(SignIn.this, "Login failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void toSignUpPage(){
        Intent toSignUpPage = new Intent(SignIn.this, SignUp.class);
        startActivity(toSignUpPage);
    }

}