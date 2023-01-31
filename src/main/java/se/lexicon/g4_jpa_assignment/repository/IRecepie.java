package se.lexicon.g4_jpa_assignment.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.g4_jpa_assignment.entity.Recipe;

public interface IRecepie extends CrudRepository<Recipe, Integer> {
}
