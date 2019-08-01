package sia.tacocloud.repositories.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import sia.tacocloud.dto.Order;
import sia.tacocloud.repositories.OrderRepository;

@Repository
public class JdbcOrderRepository implements OrderRepository {

  private SimpleJdbcInsert orderInserter;
  private SimpleJdbcInsert orderTacoInserter;
  private ObjectMapper objectMapper;

  @Autowired
  public JdbcOrderRepository(JdbcTemplate jdbcTemplate) {
    this.orderInserter = new SimpleJdbcInsert(jdbcTemplate)
            .withTableName("taco_order")
            .usingGeneratedKeyColumns("id");

    this.orderTacoInserter = new SimpleJdbcInsert(jdbcTemplate)
            .withTableName("taco_order_tacos");

    this.objectMapper = new ObjectMapper();
  }

  @Override
  public Order save(Order order) {
    throw new UnsupportedOperationException();
  }
}
