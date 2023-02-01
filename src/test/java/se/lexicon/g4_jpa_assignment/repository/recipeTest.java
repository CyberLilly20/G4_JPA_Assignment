package se.lexicon.g4_jpa_assignment.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.g4_jpa_assignment.Enum.Measurement;
import se.lexicon.g4_jpa_assignment.entity.Recipe;
import se.lexicon.g4_jpa_assignment.entity.RecipeCategory;
import se.lexicon.g4_jpa_assignment.entity.RecipeIngredient;
import se.lexicon.g4_jpa_assignment.entity.RecipeInstruction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class recipeTest {


    @Autowired
    IRecepie testRecepieRepository;
    @Autowired
    RecipeCategoryRepository testRecipeCategoryRepository;
    @Autowired
    RecipeInstructionRepository testRecipeInstructionRepository;

    @Autowired
    RecipeIngredientRepository testRecipeIngredientRepository;

    Recipe createdRecipe;
    RecipeInstruction createdRecipeInstruction;

    RecipeCategory createdRecipeCategory;

    RecipeIngredient createdRecipeIngredient;


    @BeforeEach
    public void setup() {

        RecipeIngredient recipeIngredient = new RecipeIngredient(1, Measurement.MEASUREMENT_G);
        createdRecipeIngredient = testRecipeIngredientRepository.save(recipeIngredient);


        RecipeCategory recipeCategory = new RecipeCategory("Vegetarian");
        createdRecipeCategory = testRecipeCategoryRepository.save(recipeCategory);

        RecipeInstruction recipeInstruction = new RecipeInstruction("Cook the Lentil with rice");
        createdRecipeInstruction = testRecipeInstructionRepository.save(recipeInstruction);


        Recipe recipe = new Recipe("Name1");
        createdRecipe = testRecepieRepository.save(recipe);


        createdRecipe.addCategory(createdRecipeCategory);
        createdRecipe.setInstruction(createdRecipeInstruction);
        createdRecipe.addRecipeIngredient(createdRecipeIngredient);

        testRecepieRepository.save(createdRecipe);
    }


    @Test
    public void test_findById() {
        Optional<Recipe> recipeTest = testRecepieRepository.findById(createdRecipe.getId());
        assertTrue(recipeTest.isPresent());
        Recipe actualData = recipeTest.get();
        Recipe expectedData = createdRecipe;
        assertEquals(expectedData, actualData);
    }


    @Test
    public void test_findAll() {


        Iterable recepie1 = testRecepieRepository.findAll();
        assertEquals(createdRecipe,recepie1.iterator().next());

    }


    @Test
    public void test_Update(){

        assertEquals(1, testRecepieRepository.count());
    }

   @Test
    public void selectByJoinTable(){

       List<Recipe> recipeList;

        recipeList= testRecepieRepository.selectByJoinTable(1);
      Recipe recipe11= recipeList.get(0);
      int id=recipe11.getId();

       assertEquals(id,createdRecipe.getId());

    }


    @Test
    public void test_delete(){

         testRecepieRepository.delete(createdRecipe);
        Optional<Recipe> recipeTest = testRecepieRepository.findById(createdRecipe.getId());
        assertTrue(!recipeTest.isPresent());

    }










}
