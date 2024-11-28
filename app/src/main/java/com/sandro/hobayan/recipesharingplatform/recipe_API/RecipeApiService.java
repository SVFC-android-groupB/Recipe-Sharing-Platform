package com.sandro.hobayan.recipesharingplatform.recipe_API;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public class RecipeApiService {
    @GET("filter.php?a=Filipino")
    public Call<List<RecipeRequestModel>> getFilipinoRecipes() {
        return null;
    }
}
