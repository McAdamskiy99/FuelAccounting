package fuel.newaccounting.service;

import fuel.newaccounting.entity.Car;
import fuel.newaccounting.entity.Fuel;
import fuel.newaccounting.entity.Transaction;
import fuel.newaccounting.repository.CarRepository;
import fuel.newaccounting.repository.FuelRepository;
import fuel.newaccounting.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CarRepository carRepository;
    private final FuelRepository fuelRepository;

    @Transactional
    public Transaction newTransaction(Long carId, Long amount, Long balance, Long odometrCurrent){

        Car car = carRepository.findById(carId).orElse(null);
        Fuel fuel = fuelRepository.findById(car.getModel().getFuel().getId()).orElse(null);
        Transaction transaction = new Transaction();

        // --- Avtomobilning 100 km ga o'rtacha yoqilg'i sarfini hisoblaymiz
        double distanse =car.getOdometrCurrent() - odometrCurrent;
        double LAFC = (car.getAvaibleFuel() - balance) / distanse * 100;
        int persent;

        // |---> Sarf oshgan bo'lsa
        if(LAFC > car.getModel().getAver_fuel()) {
            persent = (int) ((LAFC - car.getModel().getAver_fuel()) / car.getModel().getAver_fuel() * 100);
            System.out.println("The car's average fuel consumption has increased by " + (persent > 1 ? persent : "<1") + "%");
        }

        // | ---> Sarf kamaygan bo'lsa
        else if(LAFC < car.getModel().getAver_fuel()) {
            persent = (int) ((car.getModel().getAver_fuel() - LAFC) / car.getModel().getAver_fuel() * 100);
            System.out.println("The car's average fuel consumption has decreased  by " + (persent > 1 ? persent : "<1") + "%");
        }

        // |---> Sarf me'yorida bo'lsa
        else System.out.println("Average fuel consumption is at standard levels");

        // --- Avtomobilning 100 km ga o'rtacha yoqilg'i sarfini yangilaymiz
        car.setAverageFuel(LAFC);

        // 1. To`g`ri keladigan yoqilg`idan yetarli miqdorda mavjud emasligi holati
        if(fuel.getFuelQuantity() < amount) {
            throw new RuntimeException("ERROR: The required fuel type is not available in sufficient quantity\nThere are currently " + fuel.getFuelQuantity() +" liters of " + fuel.getFuelName() + "diesel fuel left.");
        }

        fuel.setFuelQuantity(fuel.getFuelQuantity() - amount);
        fuelRepository.save(fuel);

        // 2. Ko'rsatilgan miqdordagi yoqilg‘i avtomobil yoqilg‘i bakiga sig'masligi holati
        if(amount + balance > car.getModel().getTankCapacity()) {
            throw new RuntimeException("ERROR: The specified fuel amount exceeds the tank capacity\nThe car's fuel tank has a capacity of " + (car.getModel().getTankCapacity() - balance) + "10 liters");
        }

        car.setAvaibleFuel(amount+balance);

        // 3. Odometr ko'rsatkichi xato kiritilgan holat
        if(odometrCurrent < car.getOdometrCurrent()) {
            throw new RuntimeException("ERROR: Invalid odometer reading");
        }


        car.setOdometrCurrent(odometrCurrent);
        carRepository.save(car);

        // 4. Avtomobilning yillik bosib o'tishi mumkin bo'lgan limit tugagan holat
        if(car.getOdometrBegin() - odometrCurrent >= car.getModel().getAnnualMileage()) {
            throw new RuntimeException("ERROR: The annual mileage limit for the car has been reached");
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

        return transactionRepository.save(transaction);
    }



}
