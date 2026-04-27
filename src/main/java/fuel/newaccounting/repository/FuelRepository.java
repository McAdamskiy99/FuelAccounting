package fuel.newaccounting.repository;

import fuel.newaccounting.entity.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FuelRepository extends JpaRepository<Fuel, Long> {

//    List<Fuel> id(long id);
    boolean existsByName(String name);


}
