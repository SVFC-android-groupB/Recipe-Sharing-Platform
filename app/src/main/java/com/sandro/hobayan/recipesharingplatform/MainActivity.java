package com.sandro.hobayan.recipesharingplatform;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.sandro.hobayan.recipesharingplatform.signin_signup.SignIn;

public class MainActivity extends AppCompatActivity {

    ImageButton account, refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        account = findViewById(R.id.btn_account);
        refresh = findViewById(R.id.btn_refresh);

        account.setOnClickListener(v -> toSignin());// to sign in page
    }

    private void toSignin(){
        Intent signin = new Intent(MainActivity.this, SignIn.class):
        startActivity(signin);
    }
}