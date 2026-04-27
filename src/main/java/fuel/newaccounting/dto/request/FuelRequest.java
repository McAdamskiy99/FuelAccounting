package fuel.newaccounting.dto.request;

import jakarta.validation.constraints.Min;
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

public class FuelRequest {

//    @NotBlank(message = "Fuel name is empty, please enter the fuel name.")
    private String name;

//    @Min(value = 1, message = "Incorrect fuel price, please enter a correct value.")
    private Double price;


//    @Min(value = 1, message = "Incorrect fuel quantity, please enter a valid number.")
    private double quantity;

//    @Min(value = 1, message = "Incorrect total fuel capacity, please check and try again.")
    private double totalCap;

}
