package fuel.newaccounting.service;

import fuel.newaccounting.dto.reponse.DriverResponse;
import fuel.newaccounting.dto.request.DriverRequest;
import fuel.newaccounting.entity.Driver;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import fuel.newaccounting.repository.DriverRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class DriverService {

    private DriverRepository driverRep;

    public ResponseEntity<?> newDriver(DriverRequest request) {

        if(request.getName().trim().isEmpty()){return ResponseEntity.status(HttpStatus.CONFLICT).body("Fuel name cannot be empty");}
        if(request.getLastname().trim().isEmpty()){return ResponseEntity.status(HttpStatus.CONFLICT).body("Last name cannot be empty");}

        if(request.getPhone().trim().isEmpty()){return ResponseEntity.status(HttpStatus.CONFLICT).body("Phone number cannot be empty");}
        if(driverRep.existsByPhone(request.getPhone())){return ResponseEntity.status(HttpStatus.CONFLICT).body("Phone already exists");}

        if(request.getLicense().trim().isEmpty()){return ResponseEntity.status(HttpStatus.CONFLICT).body("License serial number cannot be empty");}
        if(driverRep.existsByLicense(request.getLicense())){return ResponseEntity.status(HttpStatus.CONFLICT).body("License serial number already exists");}

        Driver driver = new Driver();

        driver.setName(request.getName());
        driver.setLastname(request.getLastname());
        driver.setPhone(request.getPhone());
        driver.setLicense(request.getLicense());
        driverRep.save(driver);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<?> allDrivers(){
        List<Driver> drivers = driverRep.findAll();
        List<DriverResponse> driverResponseList = new ArrayList<>();
        for(Driver driver : drivers){
            DriverResponse driverResponse = new DriverResponse();
            driverResponse.setName(driver.getName());
            driverResponse.setLastname(driver.getLastname());
            driverResponse.setPhone(driver.getPhone());
            driverResponse.setLicense(driver.getLicense());
            driverResponseList.add(driverResponse);
        }
        return ResponseEntity.status(HttpStatus.OK).body(driverResponseList);
    }


    public ResponseEntity<?> updateDriver(Long id, DriverRequest request) {

        if(!driverRep.existsById(id)){return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Driver not found");}

        if(request.getName().trim().isEmpty()){return ResponseEntity.status(HttpStatus.CONFLICT).body("Fuel name cannot be empty");}
        if(request.getLastname().trim().isEmpty()){return ResponseEntity.status(HttpStatus.CONFLICT).body("Last name cannot be empty");}

        if(request.getPhone().trim().isEmpty()){return ResponseEntity.status(HttpStatus.CONFLICT).body("Phone number cannot be empty");}
        if(driverRep.existsByPhone(request.getPhone())){return ResponseEntity.status(HttpStatus.CONFLICT).body("Phone already exists");}

        if(request.getLicense().isEmpty()){return ResponseEntity.status(HttpStatus.CONFLICT).body("License serial number cannot be empty");}
        if(driverRep.existsByLicense(request.getLicense())){return ResponseEntity.status(HttpStatus.CONFLICT).body("License serial number already exists");}

        Driver driver = driverRep.findById(id).get();

        driver.setName(request.getName());
        driver.setLastname(request.getLastname());
        driver.setPhone(request.getPhone());
        driver.setLicense(request.getLicense());
        driverRep.save(driver);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<?> deleteDriver(Long id) {
        if(driverRep.findById(id).isEmpty()){return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Driver not found");}
        driverRep.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity<?> getDriver(Long id) {
        if(!driverRep.existsById(id)){return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Driver not found");}

        Driver driver = driverRep.findById(id).get();

        DriverResponse response = new DriverResponse();
        response.setId(driver.getId());
        response.setName(driver.getName());
        response.setLastname(driver.getLastname());
        response.setPhone(driver.getPhone());
        response.setLicense(driver.getLicense());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
