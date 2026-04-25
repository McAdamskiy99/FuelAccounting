package fuel.newaccounting.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "fuel")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fuel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String name;    // nomlanishi

    @Column
    private double price;   // 1 litri uchun narx

    @Column
    private double quantity;   // mavjud miqdori

    @Column
    private double totalCap;   // maksimal miqdori (sisterna sig‘imi)


}
