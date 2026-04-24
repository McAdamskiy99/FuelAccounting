package fuel.newaccounting.controller;


import fuel.newaccounting.entity.CarModel;
import fuel.newaccounting.repository.CarModelRepository;
import fuel.newaccounting.repository.FuelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/models")
@RequiredArgsConstructor
public class CarModelController {

    private final CarModelRepository carModelRepository;

   @PostMapping("/add")
    public CarModel addCarModel(@RequestBody CarModel carModel) {
        return carModelRepository.save(carModel);
    }

    @GetMapping("/all")
    public List<CarModel> getAllCarModel() {
        return carModelRepository.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCarModel(@PathVariable long id) {
       carModelRepository.deleteById(id);
       return "Car Model has been deleted";
    }

}
