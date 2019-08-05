package sia.tacocloud.repositories;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.dao.User;

public interface UserRepository extends CrudRepository<User, Long> {

  User findByUsername(String username);
}
