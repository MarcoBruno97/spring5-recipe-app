package guru.springframework.recipe.controllers;

import guru.springframework.recipe.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"recipe/{id}/show"})// @PathVariable means that in id is gonna be the variable passed by the URL
    public String getInfo(@PathVariable Long id, Model model){
        model.addAttribute("recipe", recipeService.getRecipeById(id));
        return "recipe/show";
    }

}
