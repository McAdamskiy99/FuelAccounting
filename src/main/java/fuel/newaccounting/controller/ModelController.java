package fuel.newaccounting.controller;


import fuel.newaccounting.dto.request.ModelRequest;
import fuel.newaccounting.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/models")
@RequiredArgsConstructor
public class ModelController {

    private final ModelService modelService;

    @PostMapping("/create")
    public ResponseEntity<?> newModel(@RequestBody ModelRequest request){
        return modelService.newModel(request);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllModels(){
        return modelService.getAllModels();
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<?> getModel(@PathVariable Long id){
        return modelService.getModel(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateModel(@PathVariable Long id, @RequestBody ModelRequest request){
        return modelService.updateModel(id, request);
    }
    @DeleteMapping("/dlete/{id}")
    public ResponseEntity<?> deleteModel(@PathVariable Long id){
        return modelService.deleteModel(id);
    }

}
