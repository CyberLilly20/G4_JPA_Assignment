package se.lexicon.g4_jpa_assignment.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.lexicon.g4_jpa_assignment.entity.Recipe;

import java.util.List;

// Todo added this annotation
@Repository
public interface IRecepie extends CrudRepository<Recipe, Integer> {
    @Modifying
    @Query("select a from Recipe  a inner join a.catogries where a.id =:to")

    List<Recipe> selectByJoinTable(@Param("to") int id);
}
