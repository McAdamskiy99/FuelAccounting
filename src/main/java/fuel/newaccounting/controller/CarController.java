package fuel.newaccounting.controller;


import fuel.newaccounting.dto.reponse.CarResponse;
import fuel.newaccounting.dto.request.CarRequest;
import fuel.newaccounting.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car")
@RequiredArgsConstructor

public class CarController {

    private final CarService carService;

    @PostMapping("/create")
    public ResponseEntity<?> newCar(@RequestBody CarRequest request){
        return carService.newCar(request);
    }

    @GetMapping("/list")
    public ResponseEntity<?> allCars(){
        return carService.allCars();
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<?> getCar(@PathVariable Long id){
        return carService.getCar(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCar(@PathVariable Long id, @RequestBody CarRequest request){
        return carService.updateCar(id, request);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable Long id){
        return carService.deleteCar(id);
    }
}
