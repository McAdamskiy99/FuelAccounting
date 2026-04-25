package fuel.newaccounting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class DriverService {

    private final fuel.newaccounting.repository.DriverService driverRepository;

}
