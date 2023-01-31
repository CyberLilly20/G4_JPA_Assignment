package se.lexicon.g4_jpa_assignment.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
public class RecipeCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String category;
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name= "recipe_recipe_category", joinColumns = @JoinColumn(name = "recipe_category_id")
            ,inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    private Set<Recipe> recipes = new HashSet<>();

    public void addRecipe(Recipe recipe){
        if(recipe == null) throw new IllegalArgumentException("Recipe was null");
        recipes.add(recipe);

    }

    public void removeRecipe(Recipe recipe){
        if (recipe == null) throw new IllegalArgumentException("Recipe is null");
        recipes.remove(recipe);
    }


}
