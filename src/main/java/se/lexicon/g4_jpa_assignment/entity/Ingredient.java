package se.lexicon.g4_jpa_assignment.entity;

import lombok.*;

import javax.persistence.*;
@AllArgsConstructor
@Data
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private int id;
    @Column(nullable = false, unique = true)
    private String ingredientName;


    public Ingredient() {
    }

    public Ingredient(String ingredientName) {
        this.ingredientName = ingredientName;
    }
}
