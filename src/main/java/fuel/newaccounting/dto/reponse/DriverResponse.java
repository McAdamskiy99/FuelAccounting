package fuel.newaccounting.dto.reponse;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DriverResponse {

    private int id;

    private String name;

    private String lastname;

    private String phone;

    private String license;
}
