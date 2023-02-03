package se.lexicon.g4_jpa_assignment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Setter
@Getter
@AllArgsConstructor
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(nullable = false, length = 50)
    private String recipeName;


    @OneToMany(mappedBy = "recipe", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    private List<RecipeIngredient> recipeIngredients;

// Todo changes done here
    @OneToOne(cascade = {CascadeType.DETACH,CascadeType.REFRESH,CascadeType.PERSIST})
    @JoinColumn(name = "recipe_instruction_id")
    private RecipeInstruction instruction;


    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "recipe_recipe_category", joinColumns = @JoinColumn(name = "recipe_id")
            , inverseJoinColumns = @JoinColumn(name = "recipe_category_id"))
    private Set<RecipeCategory> categories;


    // TODO added a constructor for testing purpose

    public Recipe() {
    }

    public Recipe(String recipeName) {
        this.recipeName = recipeName;

    }



//  private List<RecipeIngredient> recipeIngredients;
// Todo Changes done to handle null pointer exception
    public void addRecipeIngredient(RecipeIngredient recipeIngredient) {

        if (recipeIngredient == null) throw new IllegalArgumentException("Recipe Ingredient cannot be null");
        if (recipeIngredients == null) recipeIngredients= new ArrayList<>();
        if (!recipeIngredients.contains(recipeIngredient)) recipeIngredients.add(recipeIngredient);
        else
            System.out.println("Duplicate !! Cannot Add the Ingredient ");

    }

    public void removeRecipeIngredient(RecipeIngredient recipeIngredient) {

        if (recipeIngredient == null) throw new IllegalArgumentException("Recipe Ingredient cannot be null");
        if (recipeIngredients != null) recipeIngredients.remove(recipeIngredient);

        else
            System.out.println("Nothing found to remove !!");

    }

    public void addCategory(RecipeCategory recipeCategory) {

        if (recipeCategory == null) throw new IllegalArgumentException("Recipe category is null");
        if (categories == null) categories = new HashSet<>();
        categories.add(recipeCategory);
    }


    public void removeCategory(RecipeCategory recipeCategory) {

        if (recipeCategory == null) throw new IllegalArgumentException("Recipe category is null");
        categories.remove(recipeCategory);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return id == recipe.id && Objects.equals(recipeName, recipe.recipeName) && Objects.equals(recipeIngredients, recipe.recipeIngredients) && Objects.equals(instruction, recipe.instruction) && Objects.equals(categories, recipe.categories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, recipeName, recipeIngredients, instruction, categories);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", recipeName='" + recipeName + '\'' +
                ", instruction=" + instruction +
                ", categories=" + categories +
                '}';
    }


}
