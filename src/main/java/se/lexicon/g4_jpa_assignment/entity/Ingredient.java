package se.lexicon.g4_jpa_assignment.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Ingredient {

    @Id
    @GeneratedValue
    @Column(updatable = false)
    private int id;
    @Column(nullable = false, unique = true)
    private String ingredientName;


    public Ingredient(int id, String ingredientName) {
        this.id = id;
        this.ingredientName = ingredientName;
    }

    public Ingredient() {
    }
}
