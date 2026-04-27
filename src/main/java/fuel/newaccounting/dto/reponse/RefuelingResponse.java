package fuel.newaccounting.dto.reponse;

import fuel.newaccounting.entity.Car;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RefuelingResponse {

    private Long id;

    private Long carId;
    private String carRegNum;

    private Long driverId;
    private String driverFullName;

    private Long fuelId;
    private String fuelName;

    private double amount;
    private double balance;

    private double odometr;

    private LocalDateTime currentDate;
}
