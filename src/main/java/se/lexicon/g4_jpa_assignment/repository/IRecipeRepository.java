package se.lexicon.g4_jpa_assignment.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.lexicon.g4_jpa_assignment.entity.Ingredient;
import se.lexicon.g4_jpa_assignment.entity.Recipe;

import java.util.List;

// Todo added this annotation
@Repository
public interface IRecipeRepository extends CrudRepository<Recipe, Integer> {

    List<Recipe> findByRecipeNameContaining(String name);

    @Query("select a from Recipe  a inner join a.recipeIngredients b where b.ingredient =:to")

    List<Recipe> selectByIngredient(@Param("to") Ingredient name);

    @Query("select a from Recipe  a inner join a.categories b where b.category =:to")

    List<Recipe> selectByCategory(@Param("to") String name);



    @Query("select a from Recipe  a inner join a.categories b where b.category in(:to)")

    List<Recipe> selectByRecipe(@Param("to") List<String> category);
}
