package com.sandro.hobayan.recipesharingplatform.signin_signup;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sandro.hobayan.recipesharingplatform.R;

public class SignUp extends AppCompatActivity {

    EditText username, email, password;
    Button Signup, toSignin;

    FirebaseAuth mAuth;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //init firebase
        mAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        username = findViewById(R.id.et_username_signup);
        email = findViewById(R.id.et_email_signup);
        password = findViewById(R.id.et_password_signup);
        Signup = findViewById(R.id.btn_signup);
        toSignin = findViewById(R.id.btn_to_signin_page);

        Signup.setOnClickListener(v-> signUp());
        toSignin.setOnClickListener(v-> toSigninPage());

    }

    private void toSigninPage(){
        Intent tosigninPage = new Intent(SignUp.this, SignIn.class);
        startActivity(tosigninPage);
    }

    private void signUp(){
        String emailStr = email.getText().toString();
        String usernameStr = username.getText().toString();
        String passwordStr = password.getText().toString();

        if(emailStr.isEmpty() || usernameStr.isEmpty() || passwordStr.isEmpty()){
            Toast.makeText(SignUp.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(emailStr, passwordStr).addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // signing up ay success
                        FirebaseUser user = mAuth.getCurrentUser();
                        saveUserData(user, usernameStr);
                        Toast.makeText(SignUp.this, "Sign up successful", Toast.LENGTH_SHORT).show();
                        toLogin();
                    } else {
                        // kapag nagfailed ung signing up
                        Toast.makeText(SignUp.this, "Sign up failed", Toast.LENGTH_SHORT).show();
                    }
        });
    }

    private void saveUserData(FirebaseUser firebaseUser, String username){
        UserAccount userAccount = new UserAccount(username, firebaseUser.getEmail());
        firestore.collection("Users").document(firebaseUser.getUid()).set(userAccount)
                .addOnSuccessListener(asdf ->{
                    //data successfully writen
                    Toast.makeText(SignUp.this, "User data saved", Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(e -> {
                    //error saving user data
                    Toast.makeText(SignUp.this, "Error saving user data", Toast.LENGTH_SHORT).show();
                });
    }

    private void toLogin(){
        Intent toLoginPage = new Intent(SignUp.this, SignIn.class);
        startActivity(toLoginPage);
    }

}