package com.sandro.hobayan.recipesharingplatform.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.sandro.hobayan.recipesharingplatform.R;

public class MainActivityMenu extends AppCompatActivity {

    ListView  lvMenu;
    private SessionManager sessionManager;

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        sessionManager = new SessionManager(getApplicationContext());

        lvMenu = findViewById(R.id.lv_menu);

        String[] menuItems = {"Home", "Own Recipe", "Settings", "Logout"};

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, menuItems);
        lvMenu.setAdapter(adapter);

        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                // perform action kapag na clicked ung mga item
                switch (selectedItem){
                    case "Home":
                        toHome();   // action for home
                        break;
                        case "Own Recipe":
                        // action for own recipe
                        break;
                    case "Settings":
                        // action for settings
                        break;
                    case "Logout":
                        logout();    // action for logout
                        break;
                }
            }
        });

    }

    private void toHome(){
        Intent home = new Intent(MainActivityMenu.this, MainActivity.class);
        startActivity(home);
    }

    private void logout(){
        sessionManager.logoutUser();
        Intent home = new Intent(MainActivityMenu.this, MainActivity.class);
        startActivity(home);
    }

}