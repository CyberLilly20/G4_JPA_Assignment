package se.lexicon.g4_jpa_assignment;

import org.springframework.boot.CommandLineRunner;
import se.lexicon.g4_jpa_assignment.Enum.Measurement;
import se.lexicon.g4_jpa_assignment.entity.Ingredient;
import se.lexicon.g4_jpa_assignment.entity.Recipe;
import se.lexicon.g4_jpa_assignment.entity.RecipeCategory;
import se.lexicon.g4_jpa_assignment.entity.RecipeIngredient;

public class AppCommandLineRunner implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {
        Ingredient ingredient= new Ingredient("Cumin");

        Recipe recipe= new Recipe("Rice Platter",);


        RecipeIngredient recipeIngredient= new RecipeIngredient(1, Measurement.MEASUREMENT_TSP);



        RecipeCategory recepieCategory = new RecipeCategory("MainCourse",);

    }
}
