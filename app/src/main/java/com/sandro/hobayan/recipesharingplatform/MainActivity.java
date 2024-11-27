package com.sandro.hobayan.recipesharingplatform;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.sandro.hobayan.recipesharingplatform.signin_signup.SignIn;

public class MainActivity extends AppCompatActivity {

    ImageButton account, refresh;

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        account = findViewById(R.id.btn_account);
        refresh = findViewById(R.id.btn_refresh);

        account.setOnClickListener(v -> toSignin());// to sign in page

        sessionManager = new SessionManager(getApplicationContext());
        if(sessionManager.isLoggedIn()){
            updateAccountButtonToMenu();
        }

    }

    private void toSignin(){
        Intent signin = new Intent(MainActivity.this, SignIn.class);
        startActivity(signin);
    }

    private void updateAccountButtonToMenu(){
        account.setBackground(null);
        account.setImageResource(R.drawable.btn_menu);
        account.setContentDescription(getString(R.string.Menu_description));
        account.setOnClickListener(v -> {
            Intent menu = new Intent(MainActivity.this, MainActivityMenu.class);
            startActivity(menu);
        });
    }

}