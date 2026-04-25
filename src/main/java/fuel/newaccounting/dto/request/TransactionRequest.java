package fuel.newaccounting.dto.request;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class TransactionRequest {

    @Positive(message = "Incorrect Car ID, please check and enter a valid ID.")
    private Long carId;

    @Positive(message = "Incorrect fuel input amount, please check the limit and enter a valid number.")
    private double amount;

    @Positive(message = "Incorrect fuel balance, please check and enter a valid number.")
    private double balance;

    @Positive(message = "Incorrect odometer value, please check and enter a valid number.")
    private double odometr;

}
