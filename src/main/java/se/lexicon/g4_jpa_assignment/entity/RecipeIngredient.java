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
    private Ingredient ingredient;
    private double amount;
    private Measurement measurement;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Recipe recipe;

    public RecipeIngredient() {
    }
}

