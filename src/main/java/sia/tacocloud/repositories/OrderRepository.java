package sia.tacocloud.repositories;

import sia.tacocloud.dto.Order;

public interface OrderRepository {

  Order save(Order order);
}
