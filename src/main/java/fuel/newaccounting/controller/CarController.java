package fuel.newaccounting.controller;


import fuel.newaccounting.entity.Car;
import fuel.newaccounting.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car")
@RequiredArgsConstructor

public class CarController {

    private final CarRepository carRepository;

    @PostMapping("/add")
    public Car addCar(@RequestBody Car car) {
        return carRepository.save(car);
    }

    @GetMapping("/all")
    public List<Car> getAllCar() {
        return carRepository.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCar(@PathVariable long id) {
        carRepository.deleteById(id);
        return "Car has been deleted";
    }
}
