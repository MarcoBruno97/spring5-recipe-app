package guru.springframework.recipe.repositories;

import guru.springframework.recipe.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

    Optional<Ingredient> findByIdAndRecipeId(Long ingredientId, Long recipeId);

}
