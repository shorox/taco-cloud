package sia.tacocloud.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.dao.Order;
import sia.tacocloud.dao.User;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

  List<Order> findByZip(String zip);

  List<Order> readOrdersByZipAndCreatedAtBetween(String zip, Date startDate, Date endDate);

  List<Order> findByUserOrderByCreatedAtDesc(User user, Pageable pageable);
}
