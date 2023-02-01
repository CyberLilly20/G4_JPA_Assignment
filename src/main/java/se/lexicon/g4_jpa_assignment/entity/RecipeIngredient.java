package se.lexicon.g4_jpa_assignment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import se.lexicon.g4_jpa_assignment.Enum.Measurement;

import javax.persistence.*;


@AllArgsConstructor
@Data
@Entity
public class RecipeIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;
    private double amount;
    private Measurement measurement;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "recipe_recipe_id")
    private Recipe recipe;

    public RecipeIngredient() {
    }

    public RecipeIngredient( Ingredient ingredient, double amount, Measurement measurement ) {
        this.amount = amount;
        this.ingredient = ingredient;
        this.measurement = measurement;
    }
}

