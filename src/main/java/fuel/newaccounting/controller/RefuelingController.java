package fuel.newaccounting.controller;


import fuel.newaccounting.dto.reponse.RefuelingResponse;
import fuel.newaccounting.dto.request.RefuelingRequest;
import fuel.newaccounting.entity.Refueling;
import fuel.newaccounting.repository.CarRepository;
import fuel.newaccounting.repository.FuelRepository;
import fuel.newaccounting.repository.RefuelingRepository;
import fuel.newaccounting.service.RefuelingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class RefuelingController {

    private final RefuelingService refService;

    @PostMapping("/create")
    public ResponseEntity<?> newRefueling(@RequestBody RefuelingRequest request){
        return refService.newRefueling(request);
    }

    @GetMapping("/history")
    public List<RefuelingResponse> history(){
        return refService.allRefuelings();
    }

}
