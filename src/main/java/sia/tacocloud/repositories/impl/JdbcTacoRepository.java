package sia.tacocloud.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import sia.tacocloud.dto.Ingredient;
import sia.tacocloud.dto.Taco;
import sia.tacocloud.repositories.TacoRepository;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

@Repository
public class JdbcTacoRepository implements TacoRepository {

  private JdbcTemplate jdbcTemplate;

  @Autowired
  public JdbcTacoRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Taco save(Taco taco) {
    long tacoId = saveTacoInfo(taco);
    taco.setId(tacoId);
    for (Ingredient ingredient : taco.getIngredients()) {
      saveIngredientToTaco(ingredient, tacoId);
    }

    return taco;
  }

  private long saveTacoInfo(Taco taco) {
    taco.setCreatedAt(new Date());
    PreparedStatementCreator creator =
            new PreparedStatementCreatorFactory(
                    "insert into taco (name, created_at) value (?, ?)",
                    Types.VARCHAR,
                    Types.TIMESTAMP
            ).newPreparedStatementCreator(
                    Arrays.asList(taco.getName(), new Timestamp(taco.getCreatedAt().getTime()))
            );

    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(creator, keyHolder);

    return keyHolder.getKey().longValue();
  }

  private void saveIngredientToTaco(Ingredient ingredient, long tacoId) {
    jdbcTemplate.update("insert into taco_ingredients (taco, ingredient) value (?, ?)", tacoId, ingredient.getId());
  }
}
