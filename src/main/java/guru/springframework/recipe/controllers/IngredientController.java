package guru.springframework.recipe.controllers;

import guru.springframework.recipe.commands.RecipeCommand;
import guru.springframework.recipe.domain.Ingredient;
import guru.springframework.recipe.service.IngredientService;
import guru.springframework.recipe.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class IngredientController {
    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }
    @GetMapping({"recipe/{id}/ingredients"})
    public String getIngredients(@PathVariable Long id, Model model){
        RecipeCommand recipe = recipeService.findCommandById(id);//used Command to avoid lazy load errors in thymeleaf
        model.addAttribute("recipe", recipe);
        return "recipe/ingredient/list";
    }
    @GetMapping("recipe/{recipeId}/ingredient/{id}/show")
    public String showIngredient(@PathVariable Long recipeId, @PathVariable Long id, Model model){

        model.addAttribute("ingredient", ingredientService.getIngredient(id, recipeId));

        return "recipe/ingredient/show";

    }
}
