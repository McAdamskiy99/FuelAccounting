package fuel.newaccounting.controller;


import fuel.newaccounting.dto.request.RefuelingRequest;
import fuel.newaccounting.service.RefuelingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> history() {
        return refService.getAllRefuelings();
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<?> getRefueling(@PathVariable Long id){
        return refService.getRefueling(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRefueling(@PathVariable Long id, @RequestBody RefuelingRequest request){
        return refService.updateRefueling(id, request);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRefueling(@PathVariable Long id){
        return refService.deleteRefueling(id);
    }


}
