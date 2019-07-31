package sia.tacocloud.repositories;

import sia.tacocloud.dto.Ingredient;

public interface IngredientRepository {

  Iterable<Ingredient> findAll();

  Ingredient findOne(String id);

  Ingredient save(Ingredient ingredient);
}
