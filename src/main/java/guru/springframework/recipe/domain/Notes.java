package guru.springframework.recipe.domain;

import javax.persistence.*;

@Entity
public class Notes {

    @Id//id will be generated in automatic
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne//here we dont want cascade property, because if we delete notes, recipe dont have to be eliminated
    private Recipe recipe;

    @Lob//with this, JPA is going to store in a clob filed (i.e. a filed character large object) in the db
    private String recipeNotes;

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getRecipeNotes() {
        return recipeNotes;
    }

    public void setRecipeNotes(String description) {
        this.recipeNotes = description;
    }
}
