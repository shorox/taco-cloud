package sia.tacocloud.repositories;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.dao.Order;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

  List<Order> findByZip(String zip);

  List<Order> readOrdersByZipAndCreatedAtBetween(String zip, Date startDate, Date endDate);
}
