package fuel.newaccounting.dto.reponse;

import fuel.newaccounting.entity.Category;
import fuel.newaccounting.entity.Fuel;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModelResponse {

    private Long id;

    private String name;

    private String fuel;

    private String category;

    private double tankCap;

    private double anMile;

    private double averFuelModel;
}
