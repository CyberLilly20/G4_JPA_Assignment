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
public class recipeRepositoryTest {


    @Autowired
    IRecipeRepository testRecepieRepository;
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
        createdRecipe = testRecepieRepository.save(recipe);


        createdRecipe.addCategory(createdRecipeCategory);
        createdRecipe.setInstruction(createdRecipeInstruction);
        createdRecipe.addRecipeIngredient(createdRecipeIngredient);

        testRecepieRepository.save(createdRecipe);

        createdRecipeIngredient.setRecipe(createdRecipe);
        testRecipeIngredientRepository.save(createdRecipeIngredient);
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
        assertEquals(createdRecipe, recepie1.iterator().next());

    }


    @Test
    public void test_Update() {

        assertEquals(1, testRecepieRepository.count());
    }


    @Test
    public void selectbyRecipeName() {

        List<Recipe> recipeList;
        recipeList = testRecepieRepository.findByRecipeNameContaining("Pizza");

        for (Recipe recipe : recipeList) {

            assertEquals(recipe.getId(), createdRecipeCategory.getId());
        }

    }

    @Test
    public void selectByIngredient() {

        // Select by recipe category


        List<Recipe> recipeList;

        recipeList = testRecepieRepository.selectByIngr(createdRecipeIngredient.getIngredient());


        for (Recipe recipe : recipeList) {

            assertEquals(recipe.getId(), createdRecipeCategory.getId());
        }


    }

    @Test
    public void selectByJoinTable() {

        // Select by recipe category

        List<Recipe> recipeList;

        recipeList = testRecepieRepository.selectByCategory(createdRecipeCategory.getCategory());

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

        List<Recipe> recipeList = testRecepieRepository.selectByRecipe(list);


        for (Recipe recipe : recipeList) {

            assertEquals(recipe.getId(), createdRecipeCategory.getId());
        }


        // Recipe recipe11= recipeList.get(0);
        // int id=recipe11.getId();

        //  assertEquals(id,createdRecipe.getId());

    }


    // selectByRecipe(@Param("to") int id);

/*
@Test
    public void selectByRecipe(RecipeCategory category){

        List<RecipeCategory> recipeList;

        recipeList= testRecepieRepository.selectByRecipe(createdRecipeCategory);
        System.out.println("Inside recipe list"+recipeList);

        //assertEquals(id,createdRecipe.getId());

    }
*/


    @Test
    public void test_delete() {

        testRecepieRepository.delete(createdRecipe);
        Optional<Recipe> recipeTest = testRecepieRepository.findById(createdRecipe.getId());
        assertTrue(!recipeTest.isPresent());

    }


}
