package sia.tacocloud.repositories;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloud.dao.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}
