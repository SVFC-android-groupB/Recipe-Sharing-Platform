package com.sandro.hobayan.recipesharingplatform.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sandro.hobayan.recipesharingplatform.ApiClient;
import com.sandro.hobayan.recipesharingplatform.R;
import com.sandro.hobayan.recipesharingplatform.recipeAdapterRecyclerView.RecipeAdapter;
import com.sandro.hobayan.recipesharingplatform.recipe_API.RecipeApiService;
import com.sandro.hobayan.recipesharingplatform.recipe_API.RecipeModel;
import com.sandro.hobayan.recipesharingplatform.signin_signup.SignIn;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ImageButton account, refresh;
    RecyclerView rvMain;

    List<RecipeModel> recipeList = new ArrayList<>();
    RecipeModel recipeModel;
    RecipeAdapter recipeAdapter;


    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        account = findViewById(R.id.btn_account);
        refresh = findViewById(R.id.btn_refresh);
        rvMain = findViewById(R.id.recyclerView);



        recipeAdapter = new RecipeAdapter(recipeList);
        rvMain.setAdapter(recipeAdapter);
        rvMain.setLayoutManager(new LinearLayoutManager(this));




        account.setOnClickListener(v -> toSignin());// to sign in page

        sessionManager = new SessionManager(getApplicationContext());
        if(sessionManager.isLoggedIn()){
            updateAccountButtonToMenu();    // method to update account btn when somenoe is logged in

        }

    }




    private void toSignin(){
        Intent signin = new Intent(MainActivity.this, SignIn.class);
        startActivity(signin);
    }

    private void updateAccountButtonToMenu(){
        account.setBackground(null);    // remove the background drawable of account button
        account.setImageResource(R.drawable.btn_menu);  // set ung image ng account btn sa menu btn na nasa drawable
        account.setContentDescription(getString(R.string.Menu_description)); // pinalitan ung description ng account btn galing sa String
        account.setOnClickListener(v -> {
            Intent menu = new Intent(MainActivity.this, MainActivityMenu.class);
            startActivity(menu);
        });
    }

    private void getRecipes() {
        RecipeApiService apiService = ApiClient.getRetrofitInstance().create(RecipeApiService.class);
        Call<List<RecipeModel>> call = apiService.getFilipinoRecipes();
        call.enqueue(new Callback<List<RecipeModel>>() {
            @Override
            public void onResponse(Call<List<RecipeModel>> call, Response<List<RecipeModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    recipeList.clear();
                    recipeList.addAll(response.body());
                    recipeAdapter.notifyDataSetChanged();
                    System.out.println("API Response: " + response.body().toString()); // Debug log
                } else {
                    System.out.println("API Error: " + response.message()); // Debug log
                    Toast.makeText(MainActivity.this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<RecipeModel>> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
                Toast.makeText(MainActivity.this, "API call failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}