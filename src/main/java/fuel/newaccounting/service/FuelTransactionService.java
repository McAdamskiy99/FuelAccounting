package fuel.newaccounting.service;

import fuel.newaccounting.entity.Car;
import fuel.newaccounting.entity.Fuel;
import fuel.newaccounting.entity.FuelTransaction;
import fuel.newaccounting.repository.CarRepository;
import fuel.newaccounting.repository.FuelRepository;
import fuel.newaccounting.repository.FuelTransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FuelTransactionService {

    private final FuelTransactionRepository fuelTransactionRepository;
    private final CarRepository carRepository;
    private final FuelRepository fuelRepository;

    @Transactional
    public FuelTransaction newTransaction(Long carId, Long amount, Long balance, Long odometrCurrent){

        Car car = carRepository.findById(carId).orElse(null);
        Fuel fuel = fuelRepository.findById(car.getModel().getFuel().getId()).orElse(null);
        FuelTransaction transaction = new FuelTransaction();

        // --- Avtomobilning 100 km ga o'rtacha yoqilg'i sarfini hisoblaymiz
        double distanse =car.getOdometrCurrent() - odometrCurrent;
        double LAFC = distanse / (car.getAvaibleFuel() - balance);
        int persent;

        // |---> Oshgan bo'lsa
        if(LAFC > car.getModel().getAver_fuel()) {
            persent = (int) ((LAFC - car.getModel().getAver_fuel()) / car.getModel().getAver_fuel() * 100);
            System.out.println("The car's average fuel consumption has increased by " + (persent > 1 ? persent : "<1") + "%");
        }

        // | ---> Kamaygan bo'lsa
        else if(LAFC < car.getModel().getAver_fuel()) {
            persent = (int) ((car.getModel().getAver_fuel() - LAFC) / car.getModel().getAver_fuel() * 100);
            System.out.println("The car's average fuel consumption has decreased  by " + (persent > 1 ? persent : "<1") + "%");
        }

        // |---> meyorida bo'lsa
        else System.out.println("Average fuel consumption is at standard levels");

        // --- Avtomobilning 100 km ga o'rtacha yoqilg'i sarfini yangilaymiz
        car.setAverageFuel(LAFC);

        // 1. To`g`ri keladigan yoqilg`idan yetarli miqdorda mavjud emasligi holati
        if(fuel.getFuelQuantity() < amount) {
            System.out.println("ERROR: The required fuel type is not available in sufficient quantity\nThere are currently " + fuel.getFuelQuantity() +" liters of " + fuel.getFuelName() + "diesel fuel left.");
            return null;
        }

        fuel.setFuelQuantity(fuel.getFuelQuantity() - amount);
        fuelRepository.save(fuel);

        // 2. Ko'rsatilgan miqdordagi yoqilg‘i avtomobil yoqilg‘i bakiga sig'masligi holati
        if(amount + balance > car.getModel().getTankCapacity()) {
            System.out.println("ERROR: The specified fuel amount exceeds the tank capacity\nThe car's fuel tank has a capacity of " + (car.getModel().getTankCapacity() - balance) + "10 liters");
            return null;
        }

        car.setAvaibleFuel(amount+balance);

        // 3. Odometr ko'rsatkichi xato kiritilgan holat
        if(odometrCurrent < car.getOdometrCurrent()) {
            System.out.println("ERROR: Invalid odometer reading");
            return null;
        }


        car.setOdometrCurrent(odometrCurrent);
        carRepository.save(car);

        // 4. Avtomobilning yillik bosib o'tishi mumkin bo'lgan limit tugagan holat
        if(car.getOdometrBegin() - odometrCurrent >= car.getModel().getAnnualMileage()) {
            System.out.println("ERROR: The annual mileage limit for the car has been reached");
            return null;
        }

        // ---> Bugungi sana va hozirgi vaqtni olamiz
        LocalDateTime currentDate = LocalDateTime.now();

        // Yoqilg'i quyish muvaffaqiyatli amalga oshirilsa saqlaymiz
        transaction.setCar(car);
        transaction.setAmount(amount);
        transaction.setBalance(balance);
        transaction.setOdometr(odometrCurrent);
        transaction.setCurrentDate(currentDate);


        System.out.println("Fueling completed successfully");

        return fuelTransactionRepository.save(transaction);
    }



}
