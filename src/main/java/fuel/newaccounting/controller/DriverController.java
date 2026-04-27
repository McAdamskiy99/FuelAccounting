package fuel.newaccounting.controller;

import fuel.newaccounting.dto.reponse.DriverResponse;
import fuel.newaccounting.dto.request.DriverRequest;
import fuel.newaccounting.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @PostMapping("/create")
    public ResponseEntity<?> newDriver(@RequestBody DriverRequest request){
        return driverService.newDriver(request);
    }

    @GetMapping("/list")
    public List<DriverResponse> listDrivers(){
        return driverService.allDrivers();
    }



}
