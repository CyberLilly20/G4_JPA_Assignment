package se.lexicon.g4_jpa_assignment.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.g4_jpa_assignment.entity.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {


    Optional<Ingredient> findByIngredientName(String name);


    List<Ingredient> findIngredientsByIngredientNameContains(String name);
}
