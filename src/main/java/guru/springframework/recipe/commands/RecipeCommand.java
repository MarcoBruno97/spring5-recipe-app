package guru.springframework.recipe.commands;

import guru.springframework.recipe.domain.Difficulty;

import java.util.HashSet;
import java.util.Set;

public class RecipeCommand {
    //Command Object is a JavaBean which will be populated with the data
    // from your forms. When the form is submitted, all the individual
    // attributes are mapped/bound to this object.
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private Difficulty difficulty;
    private NotesCommand notes;
    private Set<CategoryCommand> categories = new HashSet<>();
    private Byte [] image;

    public RecipeCommand() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(Integer prepTime) {
        this.prepTime = prepTime;
    }

    public Integer getCookTime() {
        return cookTime;
    }

    public void setCookTime(Integer cookTime) {
        this.cookTime = cookTime;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public Set<IngredientCommand> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<IngredientCommand> ingredients) {
        this.ingredients = ingredients;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public NotesCommand getNotes() {
        return notes;
    }

    public void setNotes(NotesCommand notes) {
        this.notes = notes;
    }

    public Set<CategoryCommand> getCategories() {
        return categories;
    }

    public void setCategories(Set<CategoryCommand> categories) {
        this.categories = categories;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }
}
