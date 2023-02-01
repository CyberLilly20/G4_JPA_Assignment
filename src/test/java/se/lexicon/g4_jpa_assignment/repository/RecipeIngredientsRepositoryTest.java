package se.lexicon.g4_jpa_assignment.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.g4_jpa_assignment.Enum.Measurement;
import se.lexicon.g4_jpa_assignment.entity.Ingredient;
import se.lexicon.g4_jpa_assignment.entity.RecipeIngredient;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class RecipeIngredientsRepositoryTest {
    @Autowired
    RecipeIngredientRepository testObject;
    RecipeIngredient createdRecipeIngredient;

    @BeforeEach
    public void setup() {
        Ingredient ingredient = new Ingredient("Suger");
        RecipeIngredient recipeIngredient = new RecipeIngredient(ingredient, 3, Measurement.MEASUREMENT_G);
        createdRecipeIngredient = testObject.save(recipeIngredient);
        assertNotNull(createdRecipeIngredient);
    }

    @Test
    public void findByIdTest() {
        Optional<RecipeIngredient> recipeIngredientOptional = testObject.findById(createdRecipeIngredient.getId());
        assertTrue(recipeIngredientOptional.isPresent());
        RecipeIngredient actualData = recipeIngredientOptional.get();
        RecipeIngredient expectedData = createdRecipeIngredient;
        assertEquals(expectedData, actualData);
    }

    @Test
    public void removeTest() {
        Optional<RecipeIngredient> recipeIngredientOptional = testObject.findById(createdRecipeIngredient.getId());
        assertTrue(recipeIngredientOptional.isPresent());
        testObject.delete(recipeIngredientOptional.get());
    }
}
