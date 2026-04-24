package fuel.newaccounting.service;

import fuel.newaccounting.repository.DriverRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class DriverService {

    private final DriverRepository driverRepository;

}
