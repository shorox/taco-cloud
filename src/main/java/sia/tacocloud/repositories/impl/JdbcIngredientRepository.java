package sia.tacocloud.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sia.tacocloud.dto.Ingredient;
import sia.tacocloud.repositories.IngredientRepository;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {

  private JdbcTemplate jdbcTemplate;

  @Autowired
  public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Iterable<Ingredient> findAll() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Ingredient findOne(String id) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Ingredient save(Ingredient ingredient) {
    throw new UnsupportedOperationException();
  }
}
