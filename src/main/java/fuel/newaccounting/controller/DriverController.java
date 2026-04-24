package fuel.newaccounting.controller;

import fuel.newaccounting.entity.Driver;
import fuel.newaccounting.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverRepository driverRepository;

    @PostMapping("/add")
    public Driver addDriver(@RequestBody Driver driver) {
        return driverRepository.save(driver);
    }

    @GetMapping("/all")
    public List<Driver> getAllDriver() {
        return driverRepository.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteDriver(@PathVariable long id) {
        driverRepository.deleteById(id);
        return "Driver has been deleted";
    }



}
