package fuel.newaccounting.service;

import fuel.newaccounting.dto.reponse.FuelResponse;
import fuel.newaccounting.dto.request.FuelRequest;
import fuel.newaccounting.entity.Fuel;
import fuel.newaccounting.repository.FuelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class FuelService {

    private final FuelRepository fuelRep;

    public ResponseEntity<?> newFuel(FuelRequest request){

        if(request.getName().isEmpty()){return ResponseEntity.status(HttpStatus.CONFLICT).body("Fuel name cannot be empty");}
        if(fuelRep.existsByName(request.getName())){return ResponseEntity.status(HttpStatus.CONFLICT).body("Fuel already exists");}

        if(request.getPrice() <= 0) {return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Price must be greater than 0");}

        if(request.getQuantity() <= 0) {return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Quantity must be greater than 0");}

        if(request.getTotalCap()<request.getQuantity()) {return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Total cap must be greater than quantity");}

        Fuel fuel = new Fuel();
        fuel.setName(request.getName());
        fuel.setPrice(request.getPrice());
        fuel.setQuantity(request.getQuantity());
        fuel.setTotalCap(request.getTotalCap());
        fuelRep.save(fuel);

        return ResponseEntity.ok().build();
    }

    public List<FuelResponse> allFuels(){
        List<Fuel> fuels = fuelRep.findAll();
        List<FuelResponse> fuelResponses = new ArrayList<>();
        for (Fuel fuel : fuels) {
            FuelResponse fuelResponse = new FuelResponse();
            fuelResponse.setName(fuel.getName());
            fuelResponse.setPrice(fuel.getPrice());
            fuelResponse.setQuantity(fuel.getQuantity());
            fuelResponse.setTotalCap(fuel.getTotalCap());
            fuelResponses.add(fuelResponse);
        }
        return fuelResponses;
    }





}
