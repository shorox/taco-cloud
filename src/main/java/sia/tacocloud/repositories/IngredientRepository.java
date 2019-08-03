package sia.tacocloud.repositories;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.dao.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
