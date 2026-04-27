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

public class DriverRequest {

//    @NotBlank(message = "Driver name is empty. Please enter the driver name")
    private String name;

//    @NotBlank(message = "Driver lastname is empty. Please enter the driver lastname")
    private String lastname;

//    @NotBlank(message = "Driver phone number is empty. Please enter the driver phone number")
    private String phone;

//    @NotBlank(message = "Driver license serial number is empty. Please enter the driver license serial number")
    private String license;
}
