package fuel.newaccounting.controller;

import fuel.newaccounting.entity.Fuel;
import fuel.newaccounting.repository.FuelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fuel")
@RequiredArgsConstructor
public class FuelController {

    private final FuelRepository fuelRepository;

    @PostMapping("/add")
    public Fuel addFuel(@RequestBody Fuel fuel) {
        return fuelRepository.save(fuel);
    }

    @GetMapping("/all")
    public List<Fuel> getAllFuel() {
        return fuelRepository.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteFuelById(@PathVariable long id) {
        fuelRepository.deleteById(id);
        return "Fuel has been deleted";
    }



}
