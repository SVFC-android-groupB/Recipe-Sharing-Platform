package com.sandro.hobayan.recipesharingplatform.recipeAdapterRecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sandro.hobayan.recipesharingplatform.R;
import com.sandro.hobayan.recipesharingplatform.recipeModel.RecipeModel;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    List<RecipeModel> recipeModelList;

    public RecipeAdapter(List<RecipeModel> recipeModelList) {
        this.recipeModelList = recipeModelList;
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

        RecipeModel recipeModel = recipeModelList.get(position);
        holder.tvRecipeName.setText(recipeModel.getRecipes());
        holder.ivRecipe.setImageResource(recipeModel.getRecipeImage());

    }

    @Override
    public int getItemCount() {
        return recipeModelList.size();
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
