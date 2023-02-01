package se.lexicon.g4_jpa_assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.lexicon.g4_jpa_assignment.Enum.Measurement;
import se.lexicon.g4_jpa_assignment.entity.Recipe;
import se.lexicon.g4_jpa_assignment.entity.RecipeCategory;
import se.lexicon.g4_jpa_assignment.entity.RecipeIngredient;
import se.lexicon.g4_jpa_assignment.entity.RecipeInstruction;
import se.lexicon.g4_jpa_assignment.repository.IRecepie;
import se.lexicon.g4_jpa_assignment.repository.RecipeCategoryRepository;
import se.lexicon.g4_jpa_assignment.repository.RecipeIngredientRepository;
import se.lexicon.g4_jpa_assignment.repository.RecipeInstructionRepository;

@Component
public class AppCommandLineRunner implements CommandLineRunner {
    @Autowired
    IRecepie recepieRepository;
    @Autowired
    RecipeCategoryRepository recipeCategoryRepository;
    @Autowired
    RecipeInstructionRepository recipeInstructionRepository;

    @Autowired
    RecipeIngredientRepository recipeIngredientRepository;


    @Override
    public void run(String... args) throws Exception {

        RecipeIngredient recipeIngredient = new RecipeIngredient(1, Measurement.MEASUREMENT_G);
        RecipeIngredient createdRecipeIngredient = recipeIngredientRepository.save(recipeIngredient);


        RecipeCategory recipeCategory = new RecipeCategory("Vegetarian");
        RecipeCategory createdRecipeCategory = recipeCategoryRepository.save(recipeCategory);

        RecipeInstruction recipeInstruction = new RecipeInstruction("Cook the Lentil with rice");
        RecipeInstruction createdRecipeInstruction = recipeInstructionRepository.save(recipeInstruction);


        Recipe recipe = new Recipe("Name1");
        Recipe createdRecipe = recepieRepository.save(recipe);



        createdRecipe.addCategory(createdRecipeCategory);
        createdRecipe.setInstruction(createdRecipeInstruction);
        createdRecipe.addRecipeIngredient(createdRecipeIngredient);

        recepieRepository.save(createdRecipe);

    }
}
