package se.lexicon.g4_jpa_assignment.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.g4_jpa_assignment.entity.Ingredient;

import javax.persistence.EntityManager;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@AutoConfigureTestEntityManager
@DirtiesContext
public class IngredientRepositoryTest {

    @Autowired
    IngredientRepository ingredientRepository;


    @Test
    public void findByIngredientName() {
        Ingredient ingredient = new Ingredient("Chicken");
        ingredientRepository.save(ingredient);

        assertNotNull(ingredientRepository.findByIngredientName("Chicken"));


    }

    @Test
    public void findByIngredientNameContains() {
        Ingredient ingredient = new Ingredient("Chicken");
        ingredientRepository.save(ingredient);

        assertNotNull(ingredientRepository.findIngredientsByIngredientNameContains("Chi"));

    }

    @Test
    public void findAll() {
        Ingredient ingredient1 = new Ingredient("Chicken");
        Ingredient ingredient2 = new Ingredient("Salmon");
        Ingredient ingredient3 = new Ingredient("Turkey");
        ingredientRepository.save(ingredient1);
        ingredientRepository.save(ingredient2);
        ingredientRepository.save(ingredient3);

        assertNotNull(ingredientRepository.findAll());


    }

    @Test
    public void count() {
        Ingredient ingredient1 = new Ingredient("Chicken");
        Ingredient ingredient2 = new Ingredient("Salmon");
        ingredientRepository.save(ingredient1);
        ingredientRepository.save(ingredient2);


        assertEquals(ingredientRepository.count(), 2);

    }

    @Test
    public void save() {
        Ingredient ingredient1 = new Ingredient("Chicken");
        Ingredient ingredient2 = new Ingredient("Salmon");
        Ingredient ingredient3 = new Ingredient("Turkey");
        ingredientRepository.save(ingredient1);
        ingredientRepository.save(ingredient2);
        ingredientRepository.save(ingredient3);


        assertNotNull(ingredientRepository.findAll());


    }

    @Test
    public void findById() {
        Ingredient ingredient1 = new Ingredient("Chicken");
        Ingredient ingredient2 = new Ingredient("Salmon");
        Ingredient ingredient3 = new Ingredient("Turkey");
        ingredientRepository.save(ingredient1);
        ingredientRepository.save(ingredient2);
        ingredientRepository.save(ingredient3);

        assertNotNull(ingredientRepository.findById(1));


    }

    @Test
    public void existsById() {
        Ingredient ingredient1 = new Ingredient("Chicken");
        Ingredient ingredient2 = new Ingredient("Salmon");
        Ingredient ingredient3 = new Ingredient("Turkey");
        ingredientRepository.save(ingredient1);
        ingredientRepository.save(ingredient2);
        ingredientRepository.save(ingredient3);

        assertTrue(ingredientRepository.existsById(1));


    }


}
