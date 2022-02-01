package guru.springframework.recipe.controllers;

import guru.springframework.recipe.commands.RecipeCommand;
import guru.springframework.recipe.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeform";

    }

    @RequestMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return  "recipe/recipeform";
    }


    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand recipe){
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(recipe);
        return "redirect:/recipe/" +  savedCommand.getId() + "/show";
    }

    @RequestMapping("/recipe/{id}/delete")
    public String delete(@PathVariable Long id){
        recipeService.deleteById(id);
        return "redirect:/";
    }



}
