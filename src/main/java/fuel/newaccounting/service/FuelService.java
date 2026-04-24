package fuel.newaccounting.service;

import fuel.newaccounting.entity.Fuel;
import fuel.newaccounting.repository.FuelRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class FuelService {

    private final FuelRepository fuelRepository;


}
