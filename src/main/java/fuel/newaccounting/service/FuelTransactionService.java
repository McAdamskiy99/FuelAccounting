package fuel.newaccounting.service;

import fuel.newaccounting.entity.Car;
import fuel.newaccounting.entity.Fuel;
import fuel.newaccounting.repository.CarRepository;
import fuel.newaccounting.repository.FuelRepository;
import fuel.newaccounting.repository.FuelTransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FuelTransactionService {

    private final FuelTransactionRepository fuelTransactionRepository;
    private final CarRepository carRepository;
    private final FuelRepository fuelRepository;

    @Transactional
    public String newTransaction(Long carId, Long amount, Long balance, Long odometrCurrent){

        Car car = carRepository.findById(carId).orElse(null);
        Fuel fuel = fuelRepository.findById(car.getModel().getFuel().getId()).orElse(null);

        // 1. To`g`ri keladigan yoqilg`idan yetarli miqdorda mavjud emasligi holati
        if(fuel.getFuelQuantity() < amount) return "ERROR: The required fuel type is not available in sufficient quantity";

        fuel.setFuelQuantity(fuel.getFuelQuantity() - amount);
        fuelRepository.save(fuel);

        // 2. Ko'rsatilgan miqdordagi yoqilg‘i yoqilg‘i bakiga sig'masligi holati
        if(amount + balance > car.getModel().getTankCapacity()) return "ERROR: The specified fuel amount exceeds the tank capacity";

        car.setAvaibleFuel(amount+balance);

        // 3. Odometr ko'rsatkichi xato kiritilgan
        if(odometrCurrent - car.getOdometrCurrent() < 0) return "ERROR: Invalid odometer reading";

        car.setOdometrCurrent(odometrCurrent);
        carRepository.save(car);















        return "Fueling completed successfully";
    }



}
