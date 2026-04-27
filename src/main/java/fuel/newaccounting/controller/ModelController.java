package fuel.newaccounting.controller;


import fuel.newaccounting.dto.reponse.ModelResponse;
import fuel.newaccounting.dto.request.ModelRequest;
import fuel.newaccounting.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<ModelResponse> allModels(){
        return modelService.allModels();
    }

}
