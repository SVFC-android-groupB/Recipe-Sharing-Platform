package com.sandro.hobayan.recipesharingplatform.recipeAdapterRecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sandro.hobayan.recipesharingplatform.R;
import com.sandro.hobayan.recipesharingplatform.recipe_API.RecipeRequestModel;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    List<RecipeRequestModel> recipeRequestModelList;

    public RecipeAdapter(List<RecipeRequestModel> recipeRequestModelList) {
        this.recipeRequestModelList = recipeRequestModelList;
    }

    @NonNull
    @Override
    public RecipeAdapter.RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recipe_card, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.RecipeViewHolder holder, int position) {

        RecipeRequestModel recipeRequestModel = recipeRequestModelList.get(position);
        holder.tvRecipeName.setText(recipeRequestModel.getRecipes());
        holder.ivRecipe.setImageResource(recipeRequestModel.getRecipeImage());

    }

    @Override
    public int getItemCount() {
        return recipeRequestModelList.size();
    }

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {

        TextView tvRecipeName;
        ImageView ivRecipe;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);

            tvRecipeName = itemView.findViewById(R.id.tv_recipe_name);
            ivRecipe = itemView.findViewById(R.id.iv_recipe);
        }
    }
}
