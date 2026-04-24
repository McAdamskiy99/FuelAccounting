package fuel.newaccounting.service;

import fuel.newaccounting.repository.CarModelRepository;
import fuel.newaccounting.repository.FuelRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CarModelService {

    private final CarModelRepository carModelRepository;




}
