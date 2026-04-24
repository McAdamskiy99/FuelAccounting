package fuel.newaccounting.repository;

import fuel.newaccounting.entity.FuelTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuelTransactionRepository extends JpaRepository<FuelTransaction, Long> {

}
