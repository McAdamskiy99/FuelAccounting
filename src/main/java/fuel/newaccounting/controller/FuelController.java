package fuel.newaccounting.controller;

import fuel.newaccounting.dto.reponse.FuelResponse;
import fuel.newaccounting.dto.request.FuelRequest;
import fuel.newaccounting.service.FuelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<FuelResponse> fuelList() {
        return fuelService.allFuels();
    }


}
