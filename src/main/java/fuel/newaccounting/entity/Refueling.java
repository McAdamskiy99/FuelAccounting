package fuel.newaccounting.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "refueling")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Refueling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car  car;   // mashina id

    @Column
    private double amount;  // quyish kerak bo'lgan yoqilg'i miqdori

    @Column
    private double balance; // yoqilg'i bakidagi qoldiq

    @Column
    private double odometr;     // odometr ko'rsatkichi

    @Column(name = "refueling_date")
    private LocalDateTime currentDate;

}
