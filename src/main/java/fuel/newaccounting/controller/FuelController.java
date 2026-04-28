package fuel.newaccounting.controller;

import fuel.newaccounting.dto.request.FuelRequest;
import fuel.newaccounting.service.FuelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fuel")
@RequiredArgsConstructor
public class FuelController {

    private final FuelService fuelService;

    @PostMapping("/create")
    public ResponseEntity<?> newFuel(@RequestBody FuelRequest request) {
        return fuelService.newFuel(request);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllFuel() {
        return fuelService.getAllFuel();
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<?> getFuel(@PathVariable Long id){
        return fuelService.getFuel(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateFuel(@PathVariable Long id, @RequestBody  FuelRequest request) {
        return fuelService.updateFuel(id, request);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFuel(@PathVariable Long id) {
        return fuelService.fuelDeleteById(id);
    }


}
