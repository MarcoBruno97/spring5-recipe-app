package guru.springframework.recipe.service;

import guru.springframework.recipe.commands.IngredientCommand;
import guru.springframework.recipe.converters.IngredientToIngredientCommand;
import guru.springframework.recipe.domain.Ingredient;
import guru.springframework.recipe.repositories.IngredientRepository;
import org.springframework.stereotype.Service;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    public IngredientServiceImpl(IngredientRepository ingredientRepository, IngredientToIngredientCommand ingredientToIngredientCommand) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    }


    @Override
    public IngredientCommand getIngredient(Long ingredientId, Long recipeId) {
        Ingredient ingredient = ingredientRepository.findByIdAndRecipeId(ingredientId, recipeId).orElse(null);
        IngredientCommand ingredientCommand = ingredientToIngredientCommand.convert(ingredient) ;
        return ingredientCommand;
    }
}
