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

public class CarRequest {

//    @NotBlank(message = "Car registration number is empty, please check and enter the registration number.")
    private String regNum;

//    @Positive(message = "Incorrect Model ID, please check and enter a valid ID.")
    private Long modelId;

//    @Positive(message = "Incorrect Driver ID, please check and enter a valid ID.")
    private Long driverId;

//    @Positive(message = "Incorrect initial odometer value, please enter a valid number.")
    private double odometrBegin;

    private double avaibleFuel;

}
