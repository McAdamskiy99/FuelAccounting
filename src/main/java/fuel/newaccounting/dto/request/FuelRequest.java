package fuel.newaccounting.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class FuelRequest {

    @NotBlank(message = "Fuel name is empty, please enter the fuel name.")
    private String name;

    @NotBlank(message = "Fuel price is empty, please enter the fuel price.")
    private Double price;

    @NotBlank(message = "Fuel quantity is empty, please enter the fuel quantity.")
    private double quantity;

    @NotBlank(message = "Fuel total capacity is empty, please enter the fuel total capacity.")
    private double totalCap;

}
