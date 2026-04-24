package fuel.newaccounting.controller;


import fuel.newaccounting.repository.CarRepository;
import fuel.newaccounting.repository.FuelRepository;
import fuel.newaccounting.repository.FuelTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class FuelTransactionController {

    private final FuelTransactionRepository fuelTransactionRepository;
    private final CarRepository carRepository;
    private final FuelRepository fuelRepository;


}
