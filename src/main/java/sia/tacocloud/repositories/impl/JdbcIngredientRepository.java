package sia.tacocloud.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sia.tacocloud.dto.Ingredient;
import sia.tacocloud.repositories.IngredientRepository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {

  private JdbcTemplate jdbcTemplate;

  @Autowired
  public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Iterable<Ingredient> findAll() {
    return jdbcTemplate.query("select id, name, type from ingredients", this::mapRowToIngredient);
  }

  @Override
  public Ingredient findOne(String id) {
    return jdbcTemplate.queryForObject("select id, name, type from ingredients where id = ?",
            this::mapRowToIngredient, id);
  }

  @Override
  public Ingredient save(Ingredient ingredient) {
    jdbcTemplate.update("insert into ingredients (id. name, type) values (?, ?, ?)",
            ingredient.getId(), ingredient.getName(), ingredient.getType().toString());

    return ingredient;
  }

  private Ingredient mapRowToIngredient(ResultSet resultSet, int rowNum) throws SQLException {
    return new Ingredient(
            resultSet.getString("id"),
            resultSet.getString("name"),
            Ingredient.Type.valueOf(resultSet.getString("type"))
    );
  }
}
