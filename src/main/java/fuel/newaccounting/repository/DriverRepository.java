package fuel.newaccounting.repository;

import fuel.newaccounting.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {

    boolean existsByPhone(String phone);

    boolean existsByLicense(String license);
}
