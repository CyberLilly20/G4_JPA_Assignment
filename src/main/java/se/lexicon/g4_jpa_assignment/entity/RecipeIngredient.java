package se.lexicon.g4_jpa_assignment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import se.lexicon.g4_jpa_assignment.Enum.Measurement;

import javax.persistence.*;


@AllArgsConstructor
@Data
@Entity
public class RecipeIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;
    private double amount;
    private Measurement measurement;
    @ManyToOne
    private Recipe recipe;

    public RecipeIngredient() {
    }
}
