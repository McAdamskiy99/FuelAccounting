package fuel.newaccounting.service;

import fuel.newaccounting.dto.reponse.ModelResponse;
import fuel.newaccounting.dto.request.ModelRequest;
import fuel.newaccounting.entity.Category;
import fuel.newaccounting.entity.Fuel;
import fuel.newaccounting.entity.Model;
import fuel.newaccounting.repository.FuelRepository;
import fuel.newaccounting.repository.ModelRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor

public class ModelService {

    private final ModelRepository modelRep;
    private final FuelRepository fuelRep;

    @Transactional
    public ResponseEntity<?> newModel(ModelRequest request) {

        if(request.getName().trim().isEmpty()){return ResponseEntity.status(HttpStatus.CONFLICT).body("Fuel name cannot be empty");}
        if(modelRep.existsByName(request.getName())){return ResponseEntity.status(HttpStatus.CONFLICT).body("Fuel name already exists");}

        if(request.getFuelId()<1){return ResponseEntity.status(HttpStatus.CONFLICT).body("Fuel id cannot be empty or less than 1");}
        if(!fuelRep.existsById(request.getFuelId())){return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fuel not exists");}

        if(!Arrays.stream(Category.values()).anyMatch(request.getCategory()::equals)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car model category not founded");
        }

        if(request.getTankCap()<=0){return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tank capacity id cannot be empty");}

        if(request.getAnMile() <= 0){return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Annual mileage cannot be empty");}

        if(request.getAverFuelModel() <= 0){return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Average fuel of model cannot be empty ");}

        Model model = new Model();

        model.setName(request.getName());
        model.setFuel(fuelRep.getById(request.getFuelId()));
        model.setCategory(Category.valueOf(request.getCategory()));
        model.setTankCap(request.getTankCap());
        model.setAnMile(request.getAnMile());
        model.setAverFuelModel(request.getAverFuelModel());
        modelRep.save(model);

        return ResponseEntity.status(201).build();
    }

    public ResponseEntity<?> allModels(){
        List<Model> models = modelRep.findAll();
        List<ModelResponse> modelResponses = new ArrayList<>();
        for(Model model : models){
            ModelResponse response = new ModelResponse();

            response.setId(model.getId());
            response.setName(model.getName());
            response.setFuel(model.getFuel().getName());
            response.setCategory(model.getCategory().toString());
            response.setTankCap(model.getTankCap());
            response.setAnMile(model.getAnMile());
            response.setAverFuelModel(model.getAverFuelModel());
            modelResponses.add(response);
        }
        return ResponseEntity.status(200).body(modelResponses);
    }

    @Transactional
    public ResponseEntity<?> updateModel(Long id, ModelRequest request) {

        if(!modelRep.existsById(id)){return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Model not found");}

        if(modelRep.existsByName(request.getName())){return ResponseEntity.status(HttpStatus.CONFLICT).body("Fuel name already exists");}

        if(request.getFuelId()<1){return ResponseEntity.status(HttpStatus.CONFLICT).body("Fuel id cannot be empty or less than 1");}
        if(!fuelRep.existsById(request.getFuelId())){return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fuel not exists");}

        if(!Arrays.stream(Category.values()).anyMatch(request.getCategory()::equals)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car model category not founded");
        }

        if(request.getTankCap()<=0){return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tank capacity id cannot be empty");}

        if(request.getAnMile() <= 0){return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Annual mileage cannot be empty");}

        if(request.getAverFuelModel() <= 0){return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Average fuel of model cannot be empty ");}

        Model model = modelRep.findById(id).get();

        model.setName(request.getName());
        model.setFuel(fuelRep.getById(request.getFuelId()));
        model.setCategory(Category.valueOf(request.getCategory()));
        model.setTankCap(request.getTankCap());
        model.setAnMile(request.getAnMile());
        model.setAverFuelModel(request.getAverFuelModel());
        modelRep.save(model);

        return ResponseEntity.status(201).build();
    }

    @Transactional
    public ResponseEntity<?> deleteModel(Long id) {
        if(!modelRep.existsById(id)){return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Model not found");}
        modelRep.deleteById(id);
        return ResponseEntity.status(200).body("Car model was deleted");
    }

    public ResponseEntity<?> getModel(Long id) {
        if(!modelRep.existsById(id)){return   ResponseEntity.status(HttpStatus.NOT_FOUND).body("Model not found");}

        Model model = modelRep.findById(id).get();
        ModelResponse response = new ModelResponse();

        response.setId(id);
        response.setName(model.getName());
        response.setFuelId(model.getFuel().getId());
        response.setFuel(model.getFuel().getName());
        response.setCategory(model.getCategory().toString());
        response.setTankCap(model.getTankCap());
        response.setAnMile(model.getAnMile());
        response.setAverFuelModel(model.getAverFuelModel());
        return ResponseEntity.status(200).body(response);
    }
}
