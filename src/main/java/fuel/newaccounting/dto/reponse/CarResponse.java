package fuel.newaccounting.dto.reponse; // Paket nomidagi xatoni to'g'rilashni unutmang

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarResponse {

    private Long id;

    private String regNum;

    private Long modelId;
    private String model;

    private Long driverId;
    private String driverFullName;

    private double avaibleFuel;

    private double odometrBegin;

    private double odometrCurrent;

    private double averageFuel;
}
