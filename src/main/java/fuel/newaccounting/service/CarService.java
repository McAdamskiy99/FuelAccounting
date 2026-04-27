package fuel.newaccounting.service;

import fuel.newaccounting.dto.reponse.CarResponse;
import fuel.newaccounting.dto.request.CarRequest;
import fuel.newaccounting.entity.Car;
import fuel.newaccounting.repository.CarRepository;
import fuel.newaccounting.repository.DriverRepository;
import fuel.newaccounting.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class CarService {

    private final CarRepository carRep;
    private final ModelRepository modelRep;
    private final DriverRepository driverRep;

    public ResponseEntity<?> newCar(CarRequest request) {

        if(request.getRegNum().isEmpty() || request.getRegNum().trim().length()==0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Car registration number cannot be empty");
        }
        if(request.getModelId() <= 0 || request.getModelId() > modelRep.count()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid model id.\nThere are " + modelRep.count() + " models.");
        }

        if(request.getDriverId() <= 0 || request.getDriverId() > driverRep.count()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid driver id.\nThere are " + driverRep.count() + " drivers.");
        }

        if(request.getOdometrBegin() < 0){return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Odometr reading cannot be negative.");}

        if(request.getAvaibleFuel() < 0){return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Avaible fuel reading cannot be negative.");}

        Car car = new Car();
        car.setRegNum(request.getRegNum());
        car.setModel(modelRep.getById(request.getModelId()));
        car.setDriver(driverRep.getById(request.getDriverId()));
        car.setAvaibleFuel(request.getAvaibleFuel());
        car.setOdometrBegin(request.getOdometrBegin());
        car.setOdometrCurrent(request.getOdometrBegin());
        car.setAverFuel(car.getModel().getAverFuelModel());
        carRep.save(car);

        return ResponseEntity.status(HttpStatus.CREATED).body("New car added.");
    }

    public List<CarResponse> allCars() {
        List<Car> cars = carRep.findAll();
        List<CarResponse> carResponses = new ArrayList<CarResponse>();
        for (Car car : cars) {
            CarResponse response = new CarResponse();
            response.setId(car.getId());
            response.setRegNum(car.getRegNum());
            response.setModelId(car.getModel().getId());
            response.setModel(car.getModel().getName());
            response.setDriverId(car.getDriver().getId());
            response.setDriverFullName(car.getDriver().getName() + " " + car.getDriver().getLastname());
            response.setAvaibleFuel(car.getAvaibleFuel());
            response.setOdometrBegin(car.getOdometrBegin());
            response.setOdometrCurrent(car.getOdometrBegin());
            response.setAverageFuel(car.getAverFuel());
            carResponses.add(response);
        }
        return carResponses;
    }







}
