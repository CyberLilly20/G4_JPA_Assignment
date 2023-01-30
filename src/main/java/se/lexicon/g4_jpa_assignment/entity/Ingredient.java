package se.lexicon.g4_jpa_assignment.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Setter
@Getter
@EqualsAndHashCode
@ToString
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
