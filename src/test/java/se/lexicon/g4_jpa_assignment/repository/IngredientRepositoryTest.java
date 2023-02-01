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

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
@AutoConfigureTestEntityManager
@DirtiesContext
public class IngredientRepositoryTest {

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    EntityManager entityManager;



    @Test
    public void findByIngredientName() {
        Ingredient ingredient = new Ingredient("Chicken");

        assertNotNull(ingredientRepository.findByIngredientName("Chicken"));


    }

    @Test
    public void findByIngredientNameContains(){
        Ingredient ingredient = new Ingredient("Chicken");

        assertNotNull(ingredientRepository.findIngredientsByIngredientNameContains("Chi"));

    }

    @Test
    public void findAll(){
        Ingredient ingredient1 = new Ingredient("Chicken");
        Ingredient ingredient2 = new Ingredient("Salmon");
        Ingredient ingredient3 = new Ingredient("Turkey");



    }



}
