package se.lexicon.g4_jpa_assignment.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import se.lexicon.g4_jpa_assignment.Enum.Measurement;
import se.lexicon.g4_jpa_assignment.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class RecipeRepositoryTest {


    @Autowired
    IRecipeRepository testRecipeRepository;
    @Autowired
    RecipeCategoryRepository testRecipeCategoryRepository;
    @Autowired
    RecipeInstructionRepository testRecipeInstructionRepository;

    @Autowired
    RecipeIngredientRepository testRecipeIngredientRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    Recipe createdRecipe;
    RecipeInstruction createdRecipeInstruction;

    RecipeCategory createdRecipeCategory, createdRecipeCategory1;

    RecipeIngredient createdRecipeIngredient;


    @BeforeEach
    public void setup() {

        Ingredient ingredient = new Ingredient("Cumin");
        Ingredient ingredient1 = ingredientRepository.save(ingredient);

        RecipeIngredient recipeIngredient = new RecipeIngredient(1, Measurement.MEASUREMENT_G);
        createdRecipeIngredient = testRecipeIngredientRepository.save(recipeIngredient);
        createdRecipeIngredient.setIngredient(ingredient1);

        testRecipeIngredientRepository.save(createdRecipeIngredient);


        createdRecipeIngredient.setIngredient(ingredient1);
        testRecipeIngredientRepository.save(createdRecipeIngredient);


        RecipeCategory recipeCategory = new RecipeCategory("Vegetarian");
        createdRecipeCategory = testRecipeCategoryRepository.save(recipeCategory);


        RecipeCategory recipeCategory1 = new RecipeCategory("chicken");
        createdRecipeCategory1 = testRecipeCategoryRepository.save(recipeCategory1);

        RecipeInstruction recipeInstruction = new RecipeInstruction("Cook the Lentil with rice");
        createdRecipeInstruction = testRecipeInstructionRepository.save(recipeInstruction);


        Recipe recipe = new Recipe("Pizza");
        createdRecipe = testRecipeRepository.save(recipe);


        createdRecipe.addCategory(createdRecipeCategory);
        createdRecipe.setInstruction(createdRecipeInstruction);
        createdRecipe.addRecipeIngredient(createdRecipeIngredient);

        testRecipeRepository.save(createdRecipe);

        createdRecipeIngredient.setRecipe(createdRecipe);
        testRecipeIngredientRepository.save(createdRecipeIngredient);
    }


    @Test
    public void test_findById() {
        Optional<Recipe> recipeTest = testRecipeRepository.findById(createdRecipe.getId());
        assertTrue(recipeTest.isPresent());
        Recipe actualData = recipeTest.get();
        Recipe expectedData = createdRecipe;
        assertEquals(expectedData, actualData);
    }


    @Test
    public void test_findAll() {


        Iterable recipe1 = testRecipeRepository.findAll();
        assertEquals(createdRecipe, recipe1.iterator().next());

    }


    @Test
    public void test_Update() {

        assertEquals(1, testRecipeRepository.count());
    }


    @Test
    public void selectByRecipeName() {

        List<Recipe> recipeList;
        recipeList = testRecipeRepository.findByRecipeNameContaining("Pizza");

        for (Recipe recipe : recipeList) {

            assertEquals(recipe.getId(), createdRecipeCategory.getId());
        }

    }

    @Test
    public void selectByIngredient() {

        // Select by recipe category


        List<Recipe> recipeList;

        recipeList = testRecipeRepository.selectByIngredient(createdRecipeIngredient.getIngredient());


        for (Recipe recipe : recipeList) {

            assertEquals(recipe.getId(), createdRecipeCategory.getId());
        }


    }

    @Test
    public void selectByJoinTable() {

        // Select by recipe category

        List<Recipe> recipeList;

        recipeList = testRecipeRepository.selectByCategory(createdRecipeCategory.getCategory());

        System.out.println(recipeList);

        for (Recipe recipe : recipeList) {

            assertEquals(recipe.getId(), createdRecipeCategory.getId());
        }
    }

    @Test
    public void selectByRecipe() {

        // Select by list of recipe category

        List<String> list = new ArrayList<>();
        list.add(createdRecipeCategory.getCategory());
        list.add(createdRecipeCategory1.getCategory());

        System.out.println("list      " + list);

        List<Recipe> recipeList = testRecipeRepository.selectByRecipe(list);


        for (Recipe recipe : recipeList) {

            assertEquals(recipe.getId(), createdRecipeCategory.getId());
        }


    }


    @Test
    public void test_delete() {

        testRecipeRepository.delete(createdRecipe);
        Optional<Recipe> recipeTest = testRecipeRepository.findById(createdRecipe.getId());
        assertTrue(!recipeTest.isPresent());

    }


}
