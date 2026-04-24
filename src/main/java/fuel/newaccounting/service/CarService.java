package fuel.newaccounting.service;

import fuel.newaccounting.repository.CarRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CarService {

    private final CarRepository carRepository;





}
