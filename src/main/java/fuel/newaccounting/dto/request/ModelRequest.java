package fuel.newaccounting.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ModelRequest {

//    @NotBlank(message = "Model name is empty, please check and enter the model name.")
    private String name;

//    @Positive(message = "Incorrect Fuel ID, please check and enter a valid ID.")
    private Long fuelId;

//    @NotBlank(message = "Model category is empty, please check and enter the model category.")
    private String category;

//    @Positive(message = "Incorrect tank capacity for the selected model, please check and enter a valid value.")
    private double tankCap;

//    @Positive(message = "Incorrect annual mileage limit, please check and enter a valid value.")
    private double anMile;

//    @Positive(message = "Invalid average fuel consumption (per 100 km), please check and enter a valid value.")
    private double averFuelModel;

}
