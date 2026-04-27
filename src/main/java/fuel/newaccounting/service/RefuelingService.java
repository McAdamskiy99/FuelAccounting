package fuel.newaccounting.service;

import fuel.newaccounting.dto.reponse.RefuelingResponse;
import fuel.newaccounting.dto.request.RefuelingRequest;
import fuel.newaccounting.entity.Car;
import fuel.newaccounting.entity.Fuel;
import fuel.newaccounting.entity.Refueling;
import fuel.newaccounting.repository.CarRepository;
import fuel.newaccounting.repository.FuelRepository;
import fuel.newaccounting.repository.RefuelingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RefuelingService {

    private final RefuelingRepository refRep;
    private final CarRepository carRep;
    private final FuelRepository fuelRep;

    @Transactional
    public ResponseEntity<?> newRefueling(RefuelingRequest request) {

        // carId
        if (request.getCarId() == null || request.getCarId() <= 0 || request.getCarId() > carRep.count()) {
            return ResponseEntity.badRequest().body("Car Id can't be empty.\nThere are " + carRep.count() + " cars.");
        }

        Car car = carRep.findById(request.getCarId()).orElse(null);
        Fuel fuel = car.getModel().getFuel();

        // balance(bakdagi yoqilg'i qoldig'i)
        if(request.getBalance() < 0 || request.getBalance() > car.getAvaibleFuel())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Balance must be between 0 and " + car.getAvaibleFuel() + ".");

        // amount(quyiladigan yoqilg'i miqdori)
        if(request.getAmount() <= 0) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Amount must be greater than 0.");
        if(request.getAmount() > fuel.getQuantity()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not enough fuel, remaining amount " + fuel.getQuantity() + " l");

        if(request.getAmount() > car.getModel().getTankCap()-request.getBalance())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Amount must be between 0 and " + (car.getModel().getTankCap() - request.getBalance())  + ".");

        if(request.getOdometr() < car.getOdometrCurrent()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Current odometr reading is wrong.");

        double currentAverFuel = (int) ((car.getAvaibleFuel() - request.getBalance()) / (car.getOdometrCurrent() - request.getOdometr())) * 100;
        int percent =0;
        if(currentAverFuel > car.getAverFuel()) percent = (int) ((currentAverFuel - car.getAverFuel()) / car.getAverFuel() * 100);
        else if(currentAverFuel < car.getAverFuel()) percent = (int) ((car.getAverFuel() - currentAverFuel) / car.getAverFuel() * 100);

        // yoqilg'i quyish
        Refueling refueling = new Refueling();
        refueling.setCar(car);
        refueling.setAmount(request.getAmount());
        refueling.setBalance(request.getBalance());
        refueling.setOdometr(request.getOdometr());
        refueling.setCurrentDate(LocalDateTime.now());
        refRep.save(refueling);

        // mashina ko'rsatkichlarini yangilash
        car.setAverFuel(currentAverFuel);
        car.setAvaibleFuel(request.getAmount() + request.getBalance());
        car.setOdometrCurrent(request.getOdometr());
        carRep.save(car);

        // yoqilg'i qoldig'ini yangilaymiz
        fuel.setQuantity(fuel.getQuantity() - request.getAmount());
        fuelRep.save(fuel);

        return ResponseEntity.ok().build();
    }

    public List<RefuelingResponse> allRefuelings() {
        List<Refueling> refuelings = refRep.findAll();
        List<RefuelingResponse> refuelingResponses = new ArrayList<>();
        for (Refueling refueling : refuelings) {
            RefuelingResponse response = new RefuelingResponse();
            response.setId(refueling.getId());
            response.setCarId(refueling.getCar().getId());
            response.setCarRegNum(refueling.getCar().getRegNum());
            response.setDriverId(refueling.getCar().getDriver().getId());
            response.setDriverFullName(refueling.getCar().getDriver().getName() + " " + refueling.getCar().getDriver().getLastname());
            response.setFuelId(refueling.getCar().getModel().getFuel().getId());
            response.setFuelName(refueling.getCar().getModel().getFuel().getName());
            response.setAmount(refueling.getAmount());
            response.setBalance(refueling.getBalance());
            response.setOdometr(refueling.getOdometr());
            response.setCurrentDate(refueling.getCurrentDate());
            refuelingResponses.add(response);
        }
        return refuelingResponses;
    }


}



//    @Transactional
//    public Refueling newTransaction(Long carId, Long amount, Long balance, Long odometrCurrent){
//
//        Car car = carRepository.findById(carId).orElse(null);
//        Fuel fuel = fuelRepository.findById(car.getModel().getFuel().getId()).orElse(null);
//        Refueling refueling = new Refueling();
//
//        // --- Avtomobilning 100 km ga o'rtacha yoqilg'i sarfini hisoblaymiz
//        double distanse =car.getOdometrCurrent() - odometrCurrent;
//        double LAFC = (car.getAvaibleFuel() - balance) / distanse * 100;
//        int persent;
//
//        // |---> Sarf oshgan bo'lsa
//        if(LAFC > car.getModel().getAverFuelModel()) {
//            persent = (int) ((LAFC - car.getModel().getAverFuelModel()) / car.getModel().getAverFuelModel() * 100);
//            System.out.println("The car's average fuel consumption has increased by " + (persent > 1 ? persent : "<1") + "%");
//        }
//
//        // | ---> Sarf kamaygan bo'lsa
//        else if(LAFC < car.getModel().getAverFuelModel()) {
//            persent = (int) ((car.getModel().getAverFuelModel() - LAFC) / car.getModel().getAverFuelModel() * 100);
//            System.out.println("The car's average fuel consumption has decreased  by " + (persent > 1 ? persent : "<1") + "%");
//        }
//
//        // |---> Sarf me'yorida bo'lsa
//        else System.out.println("Average fuel consumption is at standard levels");
//
//        // --- Avtomobilning 100 km ga o'rtacha yoqilg'i sarfini yangilaymiz
//        car.setAverageFuel(LAFC);
//
//        // 1. To`g`ri keladigan yoqilg`idan yetarli miqdorda mavjud emasligi holati
//        if(fuel.getQuantity() < amount) {
//            throw new RuntimeException("ERROR: The required fuel type is not available in sufficient quantity\nThere are currently " + fuel.getQuantity() +" liters of " + fuel.getName() + "diesel fuel left.");
//        }
//
//        fuel.setQuantity(fuel.getQuantity() - amount);
//        fuelRepository.save(fuel);
//
//        // 2. Ko'rsatilgan miqdordagi yoqilg‘i avtomobil yoqilg‘i bakiga sig'masligi holati
//        if(amount + balance > car.getModel().getTankCap()) {
//            throw new RuntimeException("ERROR: The specified fuel amount exceeds the tank capacity\nThe car's fuel tank has a capacity of " + (car.getModel().getTankCap() - balance) + "10 liters");
//        }
//
//        car.setAvaibleFuel(amount+balance);
//
//        // 3. Odometr ko'rsatkichi xato kiritilgan holat
//        if(odometrCurrent < car.getOdometrCurrent()) {
//            throw new RuntimeException("ERROR: Invalid odometer reading");
//        }
//
//
//        car.setOdometrCurrent(odometrCurrent);
//        carRepository.save(car);
//
//        // 4. Avtomobilning yillik bosib o'tishi mumkin bo'lgan limit tugagan holat
//        if(car.getOdometrBegin() - odometrCurrent >= car.getModel().getAnMile()) {
//            throw new RuntimeException("ERROR: The annual mileage limit for the car has been reached");
//        }
//
//        // ---> Bugungi sana va hozirgi vaqtni olamiz
//        LocalDateTime currentDate = LocalDateTime.now();
//
//        // Yoqilg'i quyish muvaffaqiyatli amalga oshirilsa saqlaymiz
//        refueling.setCar(car);
//        refueling.setAmount(amount);
//        refueling.setBalance(balance);
//        refueling.setOdometr(odometrCurrent);
//        refueling.setCurrentDate(currentDate);
//
//
//        System.out.println("Fueling completed successfully");
//
//        return refuelingRepository.save(refueling);
//    }