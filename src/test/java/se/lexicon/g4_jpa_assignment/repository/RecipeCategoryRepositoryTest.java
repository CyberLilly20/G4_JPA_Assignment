package se.lexicon.g4_jpa_assignment.repository;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.g4_jpa_assignment.entity.RecipeCategory;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class RecipeCategoryRepositoryTest {

    @Autowired
    RecipeCategoryRepository testObject;

    RecipeCategory createdRecipeCategory;

    @BeforeEach
    public void setup() {
        RecipeCategory recipeCategory = new RecipeCategory("Vegan");
        createdRecipeCategory = testObject.save(recipeCategory);
        assertNotNull(createdRecipeCategory);

    }
    @Test
    public void testFindById(){
        Optional<RecipeCategory> recipeCategoryOptional = testObject.findById(createdRecipeCategory.getId());
        assertTrue(recipeCategoryOptional.isPresent());
        RecipeCategory actualData = recipeCategoryOptional.get();
        RecipeCategory expectedData = createdRecipeCategory;
        assertEquals(expectedData, actualData);
    }
    @Test
    public void testRemove(){
        Optional<RecipeCategory> recipeCategoryOptional = testObject.findById(createdRecipeCategory.getId());
        assertTrue(recipeCategoryOptional.isPresent());
        testObject.delete(recipeCategoryOptional.get());
    }

}
