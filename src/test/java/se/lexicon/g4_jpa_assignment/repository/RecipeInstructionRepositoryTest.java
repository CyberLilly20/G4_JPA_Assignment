package se.lexicon.g4_jpa_assignment.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.g4_jpa_assignment.repository.RecipeInstructionRepository;
import se.lexicon.g4_jpa_assignment.entity.RecipeInstruction;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
public class RecipeInstructionRepositoryTest {


    @Autowired
    RecipeInstructionRepository testObject;

    RecipeInstruction createdRecipeInstructions;

    @BeforeEach
    public void setup() {
        RecipeInstruction recipeInstruction = new RecipeInstruction("Test");
        createdRecipeInstructions = testObject.save(recipeInstruction);
        assertNotNull(createdRecipeInstructions);

    }

    @Test
    public void testFindById() {
        Optional<RecipeInstruction> recipeInstructionOptional = testObject.findById(createdRecipeInstructions.getId());
        assertTrue(recipeInstructionOptional.isPresent());
        RecipeInstruction actualData = recipeInstructionOptional.get();
        RecipeInstruction expectedData = createdRecipeInstructions;
        assertEquals(expectedData, actualData);

    }

    @Test
    public void testRemove() {
        Optional<RecipeInstruction> recipeInstructionOptional = testObject.findById(createdRecipeInstructions.getId());
        assertTrue(recipeInstructionOptional.isPresent());
        testObject.delete(recipeInstructionOptional.get());
    }
}
