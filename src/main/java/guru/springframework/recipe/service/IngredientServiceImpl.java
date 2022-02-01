package guru.springframework.recipe.service;

import guru.springframework.recipe.commands.IngredientCommand;
import guru.springframework.recipe.converters.IngredientCommandToIngredient;
import guru.springframework.recipe.converters.IngredientToIngredientCommand;
import guru.springframework.recipe.domain.Ingredient;
import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.repositories.IngredientRepository;
import guru.springframework.recipe.repositories.RecipeRepository;
import guru.springframework.recipe.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final IngredientRepository ingredientRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final RecipeRepository recipeRepository;
    private final RecipeService recipeService;

    public IngredientServiceImpl(IngredientCommandToIngredient ingredientCommandToIngredient, UnitOfMeasureRepository unitOfMeasureRepository, IngredientRepository ingredientRepository, IngredientToIngredientCommand ingredientToIngredientCommand, RecipeRepository recipeRepository, RecipeService recipeService) {
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientRepository = ingredientRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.recipeRepository = recipeRepository;
        this.recipeService = recipeService;
    }


    @Override
    public IngredientCommand getIngredient(Long ingredientId, Long recipeId) {
        Ingredient ingredient = ingredientRepository.findByIdAndRecipeId(ingredientId, recipeId).orElse(null);
        IngredientCommand ingredientCommand = ingredientToIngredientCommand.convert(ingredient) ;
        return ingredientCommand;
    }

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

        if (!recipeOptional.isPresent()) {

            //todo toss error if not found!
            //log.error("Recipe not found for id: " + command.getRecipeId());
            return new IngredientCommand();
        } else {
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(command.getId()))
                    .findFirst();

            if (ingredientOptional.isPresent()) {
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(command.getDescription());
                ingredientFound.setAmount(command.getAmount());
                ingredientFound.setUom(unitOfMeasureRepository
                        .findById(command.getUom().getId())
                        .orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); //todo address this
            } else {
                //add new Ingredient
                recipe.addIngredient(ingredientCommandToIngredient.convert(command));
            }

            Recipe savedRecipe = recipeRepository.save(recipe);

            //to do check for fail
            return ingredientToIngredientCommand.convert(savedRecipe.getIngredients().stream()
                    .filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
                    .findFirst()
                    .get());
        }



    }
    @Transactional
    @Override
    public void deleteIngredient(Long ingredientId, Long recipeId) {
        Recipe recipeToDelete = recipeService.getRecipeById(recipeId);
        Ingredient ingredientToDelete = recipeToDelete.getIngredients().stream().filter(
                ingredient -> ingredient.getId().equals(ingredientId)).findFirst().get();
        if(ingredientToDelete.getId().equals(ingredientId) && recipeToDelete.getId().equals(recipeId)) {
            recipeToDelete.getIngredients().remove(ingredientToDelete);
            ingredientRepository.deleteIngredientByIdAndRecipeId(ingredientId, recipeId);
        } else {
            throw new RuntimeException("Could not find ingredient " + ingredientId + " on recipe " + recipeId + " to delete");
        }

    }


}
