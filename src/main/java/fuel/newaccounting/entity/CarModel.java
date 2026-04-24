package fuel.newaccounting.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;    // model nomlanishi

    @ManyToOne
    @JoinColumn(name = "fuel_id")
    private Fuel fuel;      // mos keladigan yoqilg'i turi


    private Category category;  // kategoriyasi (A, B, C, D)

    private double tankCapacity;    // bak sig'imi

    private double annualMileage;   // yillik probeg limiti

    private double aver_fuel;   // 100 km uchun o'rtacha yoqilg'i sarfi (model uchun)

}
