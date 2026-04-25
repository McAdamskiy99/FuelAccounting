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
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String regNum;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private CarModel model;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    private double avaibleFuel;     // bakdagi yoqilg'i miqdori

    private double odometrBegin;    // yil boshidagi odometr ko'rsatkichi

    private double odometrCurrent;  // hozirgi odometr ko'rsatkichi

    private double averageFuel;     // 100 km ga o'rtacha yoqilg'i sarfi


}
