package fuel.newaccounting.repository;

import fuel.newaccounting.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Long> {

    boolean existsByName(String name);

}
