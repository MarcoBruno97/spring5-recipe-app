package guru.springframework.recipe.service;

import guru.springframework.recipe.commands.IngredientCommand;
import guru.springframework.recipe.commands.UnitOfMeasureCommand;
import guru.springframework.recipe.domain.Ingredient;

import java.util.Set;

public interface IngredientService {

    IngredientCommand getIngredient(Long ingredientId, Long recipeId);

    IngredientCommand saveIngredientCommand(IngredientCommand command);

    void deleteIngredient(Long IngredientId, Long recipeId);
}
