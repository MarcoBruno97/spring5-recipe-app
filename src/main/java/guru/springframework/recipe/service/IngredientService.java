package guru.springframework.recipe.service;

import guru.springframework.recipe.commands.IngredientCommand;
import guru.springframework.recipe.domain.Ingredient;

public interface IngredientService {

    IngredientCommand getIngredient(Long ingredientId, Long recipeId);
}
