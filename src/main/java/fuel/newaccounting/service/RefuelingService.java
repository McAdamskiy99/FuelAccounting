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

    public ResponseEntity<?> getAllRefuelings() {
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
        return ResponseEntity.ok().body(refuelingResponses);
    }


    public ResponseEntity<?> updateRefueling(Long id, RefuelingRequest request) {

        if(!fuelRep.existsById(id)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Refueling id not found.");

        Refueling refueling = refRep.findById(id).get();

        if (request.getCarId() == null || request.getCarId() <= 0 || request.getCarId() > carRep.count()) {
            return ResponseEntity.badRequest().body("Car Id can't be empty.\nThere are " + carRep.count() + " cars.");
        }

        Car car = carRep.findById(request.getCarId()).orElse(null);
        Fuel fuel = car.getModel().getFuel();

        if(request.getBalance() < 0 || request.getBalance() > car.getAvaibleFuel())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Balance must be between 0 and " + car.getAvaibleFuel() + ".");

        if(request.getAmount() <= 0) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Amount must be greater than 0.");
        if(request.getAmount() > fuel.getQuantity()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not enough fuel, remaining amount " + fuel.getQuantity() + " l");

        if(request.getAmount() > car.getModel().getTankCap()-request.getBalance())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Amount must be between 0 and " + (car.getModel().getTankCap() - request.getBalance())  + ".");

        if(request.getOdometr() < car.getOdometrCurrent()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Current odometr reading is wrong.");

        double currentAverFuel = (int) ((car.getAvaibleFuel() - request.getBalance()) / (car.getOdometrCurrent() - request.getOdometr())) * 100;
        int percent =0;
        if(currentAverFuel > car.getAverFuel()) percent = (int) ((currentAverFuel - car.getAverFuel()) / car.getAverFuel() * 100);
        else if(currentAverFuel < car.getAverFuel()) percent = (int) ((car.getAverFuel() - currentAverFuel) / car.getAverFuel() * 100);


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


    public ResponseEntity<?> deleteRefueling(Long id) {
        if(!fuelRep.existsById(id)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Refueling id not found.");
        refRep.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> getRefueling(Long id) {
        if(!refRep.existsById(id)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Refueling id not found.");

        Refueling refueling = refRep.findById(id).get();
        Car car = refueling.getCar();
        Fuel fuel = car.getModel().getFuel();
        RefuelingResponse response = new RefuelingResponse();

        response.setId(id);
        response.setCarId(refueling.getCar().getId());
        response.setCarRegNum(car.getRegNum());
        response.setDriverId(car.getDriver().getId());
        response.setDriverFullName(car.getDriver().getName() + " " + car.getDriver().getLastname());
        response.setFuelId(fuel.getId());
        response.setFuelName(fuel.getName());
        response.setAmount(refueling.getAmount());
        response.setBalance(refueling.getBalance());
        response.setOdometr(refueling.getOdometr());
        response.setCurrentDate(refueling.getCurrentDate());

        return ResponseEntity.ok().body(response);
    }
}