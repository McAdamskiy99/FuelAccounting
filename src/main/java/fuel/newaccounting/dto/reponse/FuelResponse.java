package fuel.newaccounting.dto.reponse;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FuelResponse {

    private Long id;

    private String name;

    private Double price;

    private Double quantity;

    private Double totalCap;
}
